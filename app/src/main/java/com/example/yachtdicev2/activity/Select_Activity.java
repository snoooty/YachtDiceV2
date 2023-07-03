package com.example.yachtdicev2.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.yachtdicev2.service.MyGameServerService;
import com.example.yachtdicev2.service.MySocketService;
import com.example.yachtdicev2.R;

public class Select_Activity extends AppCompatActivity {

    String loginUserNickname;
    String TAG = "Select_Activity";
    MySocketService mss;
    MyGameServerService gss;
    boolean isMSS = false;
    boolean isGSS = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
//        startService(new Intent(Select_Activity.this, MySocketService.class));

        Intent serverIntent = new Intent(Select_Activity.this,MyGameServerService.class);
        startService(serverIntent);

        ServiceConnection sconn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.e(TAG,"mss 실행되나?");
                MySocketService.MySocketBind msb = (MySocketService.MySocketBind) service;
                mss = msb.getService();
                isMSS = true;
            }
            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.e(TAG,"mss 실행안되나?");
                isMSS = false;

            }
        };

        ServiceConnection gsConn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.e(TAG,"gss 실행되나?");
                MyGameServerService.GameServerBind gsb = (MyGameServerService.GameServerBind) service;
                gss = gsb.getService();
                isGSS = true;
            }
            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.e(TAG,"gss 실행안되나?");
                isGSS = false;
            }
        };

        Intent intent = new Intent(Select_Activity.this, MySocketService.class);
        bindService(intent, sconn, Context.BIND_AUTO_CREATE);

        bindService(serverIntent, gsConn, Context.BIND_AUTO_CREATE);

        TextView userNickname = (TextView) findViewById(R.id.User_nickname);
        Button vsUser_bt = (Button) findViewById(R.id.VS_USER);
        Button createRoom_bt = (Button) findViewById(R.id.create_room);
        Button vsCPU_bt = (Button) findViewById(R.id.VS_CPU);
        Button ranking_bt = (Button) findViewById(R.id.RANKING);

        Intent selectIntent = getIntent();
        loginUserNickname = selectIntent.getStringExtra("loginNickname");
        Log.e(TAG,"get 값 : " + loginUserNickname);

        userNickname.setText(loginUserNickname);


        userNickname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Select_Activity.this, Mypage_Activity.class);
                startActivityForResult(intent, 1);
            }
        });

        vsUser_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG,"1:1 대전 클릭되나?");

                new Thread(() -> {

                    if (mss.getSockBind()){
                        Log.e(TAG,"닉네임 보내기 : " + loginUserNickname);
                        mss.outNickName(loginUserNickname);
                    }

                    // 액티비티 전환
                    if (mss.getSockBind()) {
                        Log.e(TAG,"액티비티 옮겨지나?");
                        Intent intent = new Intent(Select_Activity.this, VsUserInGame.class);
                        intent.putExtra("loginUserNickName",loginUserNickname);
                        startActivity(intent);
                    }else {
                        Log.e(TAG,"서버와 연결이 되지 않았습니다.");
                    }

                }).start();
            }
        });

        createRoom_bt.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View v) {

                                                 AlertDialog.Builder builder = new AlertDialog.Builder(Select_Activity.this);
                                                 builder.setTitle("방만들기");
                                                 final EditText roomNum = new EditText(Select_Activity.this);
                                                 builder.setView(roomNum);

                                                 gss.sendMessage("s");

                                             }
                                         });

                vsCPU_bt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(Select_Activity.this, Ingame_Activity.class);
                        intent.putExtra("loginUserNickName", loginUserNickname);
                        startActivity(intent);
                    }
                });


    }
}