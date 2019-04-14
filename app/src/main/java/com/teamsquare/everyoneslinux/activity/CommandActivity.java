package com.teamsquare.everyoneslinux.activity;

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
import android.widget.Toast;

import com.teamsquare.everyoneslinux.R;
import com.teamsquare.everyoneslinux.adapter.CommandAdapter;
import com.teamsquare.everyoneslinux.item.CommandData;

import java.util.ArrayList;
import java.util.Arrays;

public class CommandActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private CommandAdapter commandAdapter;
    private ArrayList<CommandData> mArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_command);

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
//      commandAdapter = new CommandAdapter();
//      recyclerView.setAdapter(commandAdapter);

    }

    public void getData() {

        CommandData data = new CommandData();
        data.setTitle("cd"); // 제목 추가
        data.setContent("디렉토리를 이동하는 코드 입니다."); // 내용란 추가
        data.setResId(R.drawable.android_sample); // 이미지 추가
//      commandAdapter.addItem(data); // 리스트 아이템 추가
        mArrayList.add(data);


        CommandData data2 = new CommandData();
        data2.setTitle("ls"); // 제목 추가
        data2.setContent("현재경로의 폴더 또는 파일 목록을 보여줍니다."); // 내용란 추가
        data2.setResId(R.drawable.android_sample); // 이미지 추가

//      commandAdapter.addItem(data2); // 리스트 아이템 추가
//      commandAdapter.notifyDataSetChanged(); // 리스트 저장

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
