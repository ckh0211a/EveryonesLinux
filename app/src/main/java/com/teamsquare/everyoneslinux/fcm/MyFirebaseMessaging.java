package com.teamsquare.everyoneslinux.fcm;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.teamsquare.everyoneslinux.R;
import com.teamsquare.everyoneslinux.activity.MainActivity;

public class MyFirebaseMessaging extends FirebaseMessagingService {

    private static final String TAG = "FirebaseMsgService";

    private String msg, title;

    @Override
    public void onNewToken(String token) {
        super.onNewToken(token);
        Log.d(TAG, token);

    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        // 알람 수신 세팅
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isSetting = sharedPreferences.getBoolean("switch_key", false);
        Log.d(TAG, "isSetting: " + isSetting);

        //알람 수신 off
        if(!isSetting) {
            return;
        }

        title = remoteMessage.getNotification().getTitle();
        msg = remoteMessage.getNotification().getBody();
        Log.d(TAG, "msg: " + msg);

        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        PendingIntent pi = PendingIntent.getActivity(this,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        String NOTIFICATION_CHANNEL_ID = "PRIMARY_CHANNEL_ID";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID,
                    "My Notifications",
                    NotificationManager.IMPORTANCE_HIGH);

            notificationChannel.setDescription("Channel description");
            notificationManager.createNotificationChannel(notificationChannel);
        }

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(msg)
                .setAutoCancel(true)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setVibrate(new long[]{1, 1000})
                .setContentIntent(pi);

        notificationManager.notify(0, mBuilder.build());
    }
}
