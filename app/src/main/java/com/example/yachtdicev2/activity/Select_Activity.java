package com.example.yachtdicev2.activity;

import static android.graphics.Color.RED;
import static android.text.InputType.TYPE_CLASS_NUMBER;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.yachtdicev2.room.room_item;
import com.example.yachtdicev2.service.MyGameServerService;
import com.example.yachtdicev2.service.MySocketService;
import com.example.yachtdicev2.R;
import com.example.yachtdicev2.useJson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Select_Activity extends AppCompatActivity {

    String loginUserNickname;
    String TAG = "Select_Activity";
    MySocketService mss;
    MyGameServerService gss;
    boolean isMSS = false;
    boolean isGSS = false;
    useJson useJson = new useJson();
    BufferedReader in = null;
    JSONObject jsonObject;
    boolean roomStatus;
    boolean getList = true;

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
                MyGameServerService.GameServerBind gsb = (MyGameServerService.GameServerBind) service;
                gss = gsb.getService();
                isGSS = true;
                Log.e(TAG,"gss 실행되나?");

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                new Thread(() -> {

                    try {

                        while (getList){
                            in = new BufferedReader(new InputStreamReader(gss.gameSock.getInputStream()));
                            String msg = in.readLine();
                            jsonObject = new JSONObject(msg);
                            if (!getList){
                                break;
                            }
                            JSONArray jsonArray = jsonObject.getJSONArray("List");
                            Log.e(TAG,"msg : " + msg);
                            Log.e(TAG,"json 값 : " + jsonObject);

                            String category = jsonObject.getString("category");

                            if (category.equals("getRoomList")){

                                String roomList = jsonObject.getString("List");

                                gss.serverList.clear();

                                if (roomList.equals("[]")){
                                    roomStatus = false;
                                }else {
                                    roomStatus = true;
                                    JSONObject object;
                                    for (int i = 0; i < jsonArray.length(); i++){
                                        object = (JSONObject) jsonArray.opt(i);
                                        String roomName = object.optString("roomName");
                                        int roomNum = object.optInt("roomNum");
                                        int personner = object.optInt("personner");

                                        if (gss.serverList.contains(new room_item(roomName,0,0))){

                                        }else {
                                            Log.e(TAG,"방 리스트 추가되나?");
                                            gss.serverList.add(new room_item(roomName,roomNum,personner));
                                        }
                                    }
                                }
                            }
                        }

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                }).start();

                gss.sendMessage(useJson.getRoomList());
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
                    Handler handler = new Handler(Looper.getMainLooper());
                    if (mss.getSockBind()){
                        Log.e(TAG,"닉네임 보내기 : " + loginUserNickname);
                        mss.outNickName(loginUserNickname);
                    }

                    gss.sendMessage(useJson.getRoomList());

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    if (!roomStatus){
                        handler.post(new Runnable() {
                                         @Override
                                         public void run() {
                                             AlertDialog.Builder builder = new AlertDialog.Builder(Select_Activity.this);
                                             builder.setTitle("방이 없거나 서버에 문제가 있습니다.");
                                             builder.setMessage("방을 생성하거나 재시도 해주십시오.");
                                             builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                                 @Override
                                                 public void onClick(DialogInterface dialog, int which) {
                                                     dialog.dismiss();
                                                 }
                                             }).setCancelable(false);
                                             builder.show();
                                         }
                                     });
                    }else {
                        // 액티비티 전환
                        if (mss.getSockBind()) {
                            Log.e(TAG,"액티비티 옮겨지나?");

                            getList = false;

                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }

                            Intent intent = new Intent(Select_Activity.this, VsRoomList.class);
                            intent.putExtra("loginUserNickName",loginUserNickname);
                            startActivity(intent);
                        }else {
                            Log.e(TAG,"서버와 연결이 되지 않았습니다.");
                        }
                    }

                }).start();
            }
        });

        createRoom_bt.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View v) {

                                                 AlertDialog.Builder builder = new AlertDialog.Builder(Select_Activity.this);
                                                 builder.setTitle("방만들기");
                                                 builder.setMessage("방번호 입력");
                                                 EditText roomNum = new EditText(Select_Activity.this);
                                                 roomNum.setInputType(TYPE_CLASS_NUMBER);
                                                 roomNum.setHint("숫자만 입력가능합니다");
                                                 roomNum.setHintTextColor(RED);
                                                 roomNum.addTextChangedListener(new TextWatcher() {
                                                                                    @Override
                                                                                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                                                                    }

                                                                                    @Override
                                                                                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                                                                                        if (s.length() > 5){
                                                                                            roomNum.setText(s.subSequence(0,5));
                                                                                            roomNum.setSelection(5);
                                                                                        }

                                                                                    }

                                                                                    @Override
                                                                                    public void afterTextChanged(Editable s) {


                                                                                    }
                                                                                });
                                                         builder.setView(roomNum);

                                                 builder.setPositiveButton("생성", new DialogInterface.OnClickListener() {
                                                             @Override
                                                             public void onClick(DialogInterface dialog, int which) {

                                                                 Log.e(TAG,"roomNum getText() : " + roomNum.getText());
                                                                 if (roomNum.getText() != null){
                                                                     roomNum.setText(String.valueOf(0));
                                                                 }
                                                                 Log.e(TAG,"roomNum getText() : " + roomNum.getText());

                                                                 try {
                                                                     Thread.sleep(500);
                                                                 } catch (InterruptedException e) {
                                                                     throw new RuntimeException(e);
                                                                 }

                                                                 for (int i = 0; i < gss.serverList.size(); i++){
                                                                     Log.e(TAG,"방리스트 들어가있나? : " + gss.serverList.get(i).getRoom_Num());
                                                                 }

                                                                 if (!gss.serverList.isEmpty() && gss.serverList.contains(new room_item("",Integer.parseInt(roomNum.getText().toString()),0))){

                                                                     AlertDialog.Builder builder1 = new AlertDialog.Builder(Select_Activity.this);
                                                                     builder1.setTitle("이미 존재하는 방번호입니다.");
                                                                     builder1.setMessage("방번호를 새로 입력해주세요.");
                                                                     builder1.show();

                                                                 }else {
                                                                     getList = false;

                                                                     try {
                                                                         Thread.sleep(500);
                                                                     } catch (InterruptedException e) {
                                                                         throw new RuntimeException(e);
                                                                     }

                                                                     gss.sendMessage(useJson.createRoom(Integer.parseInt(roomNum.getText().toString()),loginUserNickname));

                                                                     Intent intent = new Intent(Select_Activity.this, VsUserInGame.class);
                                                                     intent.putExtra("loginUserNickName", loginUserNickname);
                                                                     startActivity(intent);
                                                                     dialog.dismiss();
                                                                 }
                                                             }
                                                         }).setCancelable(false);

                                                 builder.setNeutralButton("취소", new DialogInterface.OnClickListener() {
                                                             @Override
                                                             public void onClick(DialogInterface dialog, int which) {

                                                                 dialog.dismiss();

                                                             }
                                                         }).setCancelable(false);

                                                         builder.show();
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