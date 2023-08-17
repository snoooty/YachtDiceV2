package com.example.yachtdicev2.activity;

import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.TextView;

import com.example.yachtdicev2.R;
import com.example.yachtdicev2.service.MyGameServerService;
import com.example.yachtdicev2.useJson;

public class Imoji_Activity extends AppCompatActivity {

    TextView grinningFace,smilingFaceWithSunglasses,faceWithStuckOutTongue,grimacingFace,sleepingFace;
    String TAG = "Imoji_Activity";
    useJson useJson = new useJson();
    MyGameServerService gss = new MyGameServerService();
    String loginUserName;
    boolean isGSS = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imoji);

        Intent serverIntent = new Intent(Imoji_Activity.this,MyGameServerService.class);

        Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);

        int pointWidth = point.x; // 가로
        int pointHeight = point.y; // 세로

        int width = (int) (pointWidth * 1.0); // Display 가로 사이즈
        int height = (int) (pointHeight * 0.1);  // Display 높이 사이즈

        getWindow().getAttributes().width = width; // 가로 크기
        getWindow().getAttributes().height = height; // 세로 크기
        getWindow().getAttributes().gravity = Gravity.RIGHT; // 위치 설정

        loginUserName = getIntent().getStringExtra("loginUserNickName");
        Log.e(TAG,"loginName : " + loginUserName);

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

        bindService(serverIntent, gsConn, Context.BIND_AUTO_CREATE);

        grinningFace = findViewById(R.id.grinningFace);
        smilingFaceWithSunglasses = findViewById(R.id.smilingFaceWithSunglasses);
        faceWithStuckOutTongue = findViewById(R.id.faceWithStuckOutTongue);
        grimacingFace = findViewById(R.id.grimacingFace);
        sleepingFace = findViewById(R.id.sleepingFace);

        int unicodeGrinningFace = 0x1F600;
        grinningFace.setText(new String(Character.toChars(unicodeGrinningFace)));
        grinningFace.setTextSize(25);
        grinningFace.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {

                                                Log.e(TAG,"grinningFace 클릭");
                                                gss.sendMessage(useJson.imoji(unicodeGrinningFace,loginUserName));
                                                finish();

                                            }
                                        });

        int unicodeSmilingFaceWithSunglasses = 0x1F60E;
        smilingFaceWithSunglasses.setText(new String(Character.toChars(unicodeSmilingFaceWithSunglasses)));
        smilingFaceWithSunglasses.setTextSize(25);
        smilingFaceWithSunglasses.setOnClickListener(new View.OnClickListener() {
                                                         @Override
                                                         public void onClick(View v) {

                                                             Log.e(TAG,"SFWS 클릭");
                                                             gss.sendMessage(useJson.imoji(unicodeSmilingFaceWithSunglasses,loginUserName));
                                                             finish();
                                                         }
                                                     });

        int unicodeFaceWithStuckOutTongue = 0x1F61B;
        faceWithStuckOutTongue.setText(new String(Character.toChars(unicodeFaceWithStuckOutTongue)));
        faceWithStuckOutTongue.setTextSize(25);
        faceWithStuckOutTongue.setOnClickListener(new View.OnClickListener() {
                                                      @Override
                                                      public void onClick(View v) {

                                                          Log.e(TAG,"FWSOT 클릭");
                                                          gss.sendMessage(useJson.imoji(unicodeFaceWithStuckOutTongue,loginUserName));
                                                          finish();
                                                      }
                                                  });

        int unicodeGrimacingFace = 0x1F62C;
        grimacingFace.setText(new String(Character.toChars(unicodeGrimacingFace)));
        grimacingFace.setTextSize(25);
        grimacingFace.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View v) {

                                                 Log.e(TAG,"grimacingFace 클릭");
                                                 gss.sendMessage(useJson.imoji(unicodeGrimacingFace,loginUserName));
                                                 finish();
                                             }
                                         });

        int unicodeSleepingFace = 0x1F634;
        sleepingFace.setText(new String(Character.toChars(unicodeSleepingFace)));
        sleepingFace.setTextSize(25);
        sleepingFace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e(TAG,"sleepingFace 클릭");
                gss.sendMessage(useJson.imoji(unicodeSleepingFace,loginUserName));
                finish();
            }
        });


    }
}