package com.teamsquare.everyoneslinux.activity;

import android.support.annotation.NonNull;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.teamsquare.everyoneslinux.R;
import com.teamsquare.everyoneslinux.adapter.CommandAdapter;
import com.teamsquare.everyoneslinux.item.CommandData;
import com.teamsquare.everyoneslinux.item.FirebasePost;

import java.util.ArrayList;
import java.util.Arrays;

public class CommandActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private CommandAdapter commandAdapter;
    private ArrayList<CommandData> mArrayList = new ArrayList<>();

    private String sort_column_name;
    private ArrayList<String> arrayData = new ArrayList<>();
    private ArrayList<String> arrayIndex = new ArrayList<>();
//    private ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_command);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerInit();
        getData();

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        sort_column_name  = "title";
        Query sortbyTitle = FirebaseDatabase.getInstance().getReference().child("cmd_list").orderByChild(sort_column_name);
        sortbyTitle.addListenerForSingleValueEvent(postListener);

    }

    private ValueEventListener postListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//            arrayData.clear();
//            arrayIndex.clear();
            for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                String key = postSnapshot.getKey();
//                FirebasePost get = postSnapshot.getValue(FirebasePost.class);
//                String[] info = {get.title, get.content};
//                String Result = info[0] + info[1];
                Log.e("cmd_list key", String.valueOf(postSnapshot.getKey()));
                Log.e("cmd_list value", String.valueOf(postSnapshot.getValue()));


//                arrayData.add(Result);
//                arrayIndex.add(key);
            }
//            arrayAdapter.clear();
//            arrayAdapter.addAll(arrayData);
//            arrayAdapter.notifyDataSetChanged();
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
            Log.w("getFirebaseDatabase", "loadPost:onCancelled", databaseError.toException());
        }
    };


    public void RecyclerInit() { // 리사이클러뷰 연동


        recyclerView = findViewById(R.id.recycler_command);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

    }

    public void getData() {

        CommandData data = new CommandData();
        data.setTitle("cd"); // 제목 추가
        data.setContent("디렉토리를 이동하는 코드 입니다."); // 내용란 추가
        data.setResId(R.drawable.android_sample); // 이미지 추가


        CommandData data2 = new CommandData();
        data2.setTitle("ls"); // 제목 추가
        data2.setContent("현재경로의 폴더 또는 파일 목록을 보여줍니다."); // 내용란 추가
        data2.setResId(R.drawable.android_sample); // 이미지 추가


        mArrayList.add(data);
        mArrayList.add(data2);
        commandAdapter = new CommandAdapter(mArrayList);
        recyclerView.setAdapter(commandAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search_menu, menu);

        MenuItem menuItem = menu.findItem(R.id.search);
        final SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                commandAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
