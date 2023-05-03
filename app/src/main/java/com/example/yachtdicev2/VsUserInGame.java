package com.example.yachtdicev2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class VsUserInGame extends AppCompatActivity {

    Button chat_bt;
    String TAG = "VsUser";
    String loginUserNickName;
    MySocketService mss;
    boolean isMSS = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vs_user_in_game);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        ServiceConnection sconn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.e(TAG,"실행되나?");
                MySocketService.MySocketBind msb = (MySocketService.MySocketBind) service;
                mss = msb.getService();
                isMSS = true;
            }
            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.e(TAG,"실행안되나?");
                isMSS = false;
            }
        };

        Intent intent = new Intent(VsUserInGame.this, MySocketService.class);
        bindService(intent, sconn, Context.BIND_AUTO_CREATE);

        // 닉네임 가져오기
        Intent intentNick = getIntent();
        loginUserNickName = intentNick.getStringExtra("loginUserNickName");
        Log.e(TAG,"loginUserNickName : " + loginUserNickName);

        chat_bt = findViewById(R.id.chat_bt);

        chat_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG,"채팅 클릭되나?");

                Intent intent = new Intent(VsUserInGame.this,ChatingPage.class);
                intent.putExtra("loginUserNickName",loginUserNickName);
                startActivity(intent);
            }
        });
    }
}