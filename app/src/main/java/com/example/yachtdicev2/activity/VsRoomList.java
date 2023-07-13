package com.example.yachtdicev2.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Point;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import com.example.yachtdicev2.R;
import com.example.yachtdicev2.room.roomAdapter;
import com.example.yachtdicev2.room.room_item;
import com.example.yachtdicev2.service.MyGameServerService;
import com.example.yachtdicev2.useJson;

import java.util.ArrayList;

public class VsRoomList extends AppCompatActivity {

    String TAG = "VsRoomList";
    MyGameServerService gss;
    boolean isMSS = false;
    boolean isGSS = false;
    useJson useJson = new useJson();
    String loginUserNickName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vs_room_list);

        Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);

        int pointWidth = point.x; // 가로
        int pointHeight = point.y; // 세로

        int width = (int) (pointWidth * 0.9); // Display 가로 사이즈
        int height = (int) (pointHeight * 0.8);  // Display 높이 사이즈

        getWindow().getAttributes().width = width; // 가로 크기
        getWindow().getAttributes().height = height; // 세로 크기
        getWindow().getAttributes().gravity = Gravity.CENTER; // 위치 설정

        RecyclerView roomView = findViewById(R.id.roomList);

        Intent intentNick = getIntent();
        loginUserNickName = intentNick.getStringExtra("loginUserNickName");
        Log.e(TAG,"loginUserNickName : " + loginUserNickName);

        Intent serverIntent = new Intent(VsRoomList.this,MyGameServerService.class);

        ServiceConnection gsConn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.e(TAG,"gss 실행되나?");
                MyGameServerService.GameServerBind gsb = (MyGameServerService.GameServerBind) service;
                gss = gsb.getService();
                isGSS = true;

                roomView.setAdapter(gss.adapter);

                gss.adapter.setOnItemClickListener(new roomAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View v, int position) {

                        Log.e(TAG,"아이템 클릭되나?");
                        Log.e(TAG,"아이템 포지션 : " + position);

                        int roomNum = gss.serverList.get(position).getRoom_Num();

                        if (gss.serverList.get(position).getPersonnel() == 2){

                            Log.e(TAG,"방이 찼습니다.");

                        }else {
                            gss.sendMessage(useJson.enterRoom(roomNum));

                            Intent startIntent = new Intent(VsRoomList.this, VsUserInGame.class);
                            startIntent.putExtra("loginUserNickName",loginUserNickName);
                            startActivity(startIntent);
                            finish();
                        }
                    }
                });
            }
            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.e(TAG,"gss 실행안되나?");
                isGSS = false;
            }
        };

        bindService(serverIntent, gsConn, Context.BIND_AUTO_CREATE);

        roomView.setLayoutManager(new LinearLayoutManager(this));
        roomView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

    }
}