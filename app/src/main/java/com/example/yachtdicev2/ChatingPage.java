package com.example.yachtdicev2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.WindowManager;

import java.util.ArrayList;

public class ChatingPage extends AppCompatActivity {

    private RecyclerView recyclerView;

    private chatingAdapter adapter;

    private ArrayList<user_chat_item> chatActivityDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chating_page);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        if(Build.VERSION.SDK_INT < 30){
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        } else{
            getWindow().setDecorFitsSystemWindows(true);
        }


        Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);

        int pointWidth = point.x; // 가로
        int pointHeight = point.y; // 세로

        int width = (int) (pointWidth * 0.7); // Display 가로 사이즈
        int height = (int) (pointHeight * 0.9);  // Display 높이 사이즈

        getWindow().getAttributes().width = width; // 가로 크기
        getWindow().getAttributes().height = height; // 세로 크기
        getWindow().getAttributes().gravity = Gravity.LEFT; // 위치 설정


        recyclerView = findViewById(R.id.chat_recyclerview);

        adapter = new chatingAdapter();

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        chatActivityDataList = new ArrayList<>();

        chatActivityDataList.add(new user_chat_item("누티","이거슨 내용입니다"));

        adapter.setAdapterChatList(chatActivityDataList);
    }
}