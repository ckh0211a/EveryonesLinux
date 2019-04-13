package com.teamsquare.everyoneslinux.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.teamsquare.everyoneslinux.R;
import com.teamsquare.everyoneslinux.adapter.CommandAdapter;
import com.teamsquare.everyoneslinux.adapter.HoneyTipAdapter;
import com.teamsquare.everyoneslinux.item.CommandData;
import com.teamsquare.everyoneslinux.item.HoneyTipData;

public class HoneyTipActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private HoneyTipAdapter honeytipAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_honey_tip);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerInit();
        getData();

    }


    public void RecyclerInit() { // 리사이클러뷰 연동


        recyclerView = findViewById(R.id.recycler_command);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        honeytipAdapter = new HoneyTipAdapter();
        recyclerView.setAdapter(honeytipAdapter);

    }

    public void getData() {

        HoneyTipData data = new HoneyTipData();
        data.setTitle("cd"); // 제목 추가
        data.setContent("디렉토리를 이동하는 코드 입니다."); // 내용란 추가
        data.setResId(R.drawable.android_sample); // 이미지 추가
        honeytipAdapter.addItem(data); // 리스트 아이템 추가


        HoneyTipData data2 = new HoneyTipData();
        data2.setTitle("ls"); // 제목 추가
        data2.setContent("현재경로의 폴더 또는 파일 목록을 보여줍니다."); // 내용란 추가
        data2.setResId(R.drawable.android_sample); // 이미지 추가
        honeytipAdapter.addItem(data2); // 리스트 아이템 추가


        honeytipAdapter.notifyDataSetChanged(); // 리스트 저장

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.action_search) {

            Toast.makeText(this, "액션을 넣어주세요.", Toast.LENGTH_SHORT).show();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
