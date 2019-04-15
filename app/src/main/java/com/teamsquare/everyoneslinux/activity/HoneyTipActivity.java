package com.teamsquare.everyoneslinux.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.teamsquare.everyoneslinux.R;
import com.teamsquare.everyoneslinux.adapter.CommandAdapter;
import com.teamsquare.everyoneslinux.adapter.HoneyTipAdapter;
import com.teamsquare.everyoneslinux.item.CommandData;
import com.teamsquare.everyoneslinux.item.HoneyTipData;

import java.util.ArrayList;

public class HoneyTipActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private HoneyTipAdapter honeytipAdapter;
    private ArrayList<HoneyTipData> mArrayList = new ArrayList<>();

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


    }

    public void getData() {

        HoneyTipData data = new HoneyTipData();
        data.setTitle("cd"); // 제목 추가
        data.setContent("디렉토리를 이동하는 코드 입니다."); // 내용란 추가
        data.setResId(R.drawable.android_sample); // 이미지 추가


        HoneyTipData data2 = new HoneyTipData();
        data2.setTitle("ls"); // 제목 추가
        data2.setContent("현재경로의 폴더 또는 파일 목록을 보여줍니다."); // 내용란 추가
        data2.setResId(R.drawable.android_sample); // 이미지 추가

        mArrayList.add(data);
        mArrayList.add(data2);
        honeytipAdapter = new HoneyTipAdapter(mArrayList);
        recyclerView.setAdapter(honeytipAdapter);

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
                honeytipAdapter.getFilter().filter(newText);
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
