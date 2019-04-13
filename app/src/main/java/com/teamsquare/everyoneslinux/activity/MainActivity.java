package com.teamsquare.everyoneslinux.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.teamsquare.everyoneslinux.fragment.FragHome;
import com.teamsquare.everyoneslinux.fragment.FragLike;
import com.teamsquare.everyoneslinux.fragment.FragSearch;
import com.teamsquare.everyoneslinux.fragment.FragSetting;
import com.teamsquare.everyoneslinux.R;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private BottomNavigationView bottomNavigationView; // 바텀 네비게이션 뷰
    /* 프래그먼트 관련 */
    private FragmentManager fm;
    private FragmentTransaction ft;
    private FragHome fragHome;
    private FragSearch fragSearch;
    private FragLike fragLike;
    private FragSetting fragSetting;
    /* 프래그먼트 관련.끝 */

    private DrawerLayout drawerLayout;
    private ImageView iv_navi;
    private ImageView setting_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawerLayout);
        iv_navi = findViewById(R.id.iv_navi);
        setting_image = findViewById(R.id.settings);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        fragHome = new FragHome();
        fragSearch = new FragSearch();
        fragLike = new FragLike();
        fragSetting = new FragSetting();
        setFrag(0);




        // 하단 네비게이션 바 셀렉트 이벤트 리스너
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        setFrag(0);
                        break;
                    case R.id.action_search:
                        setFrag(1);
                        break;
                    case R.id.action_like:
                        setFrag(2);
                        break;
                    case R.id.action_setting:
                        setFrag(3);
                        break;
                }
                return true;
            }
        });


        iv_navi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        
        setting_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this, "SettingActivity is called.", Toast.LENGTH_SHORT).show();
            }
        });

    }



    // 백버튼을 눌러도 네비게이션 메뉴가 닫히게 구현.
    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawers();
        } else {
            super.onBackPressed();
        }
    }

    public void setFrag(int n){    //프래그먼트를 교체하는 작업을 하는 메소드를 만들었습니다
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        switch (n){
            case 0:
                ft.replace(R.id.main_frame, fragHome);  //replace의 매개변수는 (프래그먼트를 담을 영역 id, 프래그먼트 객체) 입니다.
                ft.commit();
                break;
            case 1:
                ft.replace(R.id.main_frame, fragSearch);  //replace의 매개변수는 (프래그먼트를 담을 영역 id, 프래그먼트 객체) 입니다.
                ft.commit();
                break;
            case 2:
                ft.replace(R.id.main_frame, fragLike);  //replace의 매개변수는 (프래그먼트를 담을 영역 id, 프래그먼트 객체) 입니다.
                ft.commit();
                break;
            case 3:
                ft.replace(R.id.main_frame, fragSetting);  //replace의 매개변수는 (프래그먼트를 담을 영역 id, 프래그먼트 객체) 입니다.
                ft.commit();
                break;
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.menu_intro:
                break;
            case R.id.menu_order:
                break;
            case R.id.menu_event:
                break;
            case R.id.menu_pray:
                break;
            case R.id.menu_man:
                break;
            case R.id.menu_woman:
                break;

        }
        drawerLayout.closeDrawers();
        return false;
    }

}
