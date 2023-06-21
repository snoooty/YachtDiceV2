package com.example.yachtdicev2.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;


import com.example.yachtdicev2.chating.chatingAdapter;
import com.example.yachtdicev2.ip.GetIP;
import com.example.yachtdicev2.chating.user_chat_item;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

public class MySocketService extends Service {

    private Socket sock;
    private String TAG = "MySocketService";
    public IBinder mbinder =  new MySocketBind();
    String vs_user_name;
    String rm,sm;
    String[] splitMS;
    BufferedReader in = null;
    PrintWriter out = null;
    private ArrayList<user_chat_item> serverDataList;
    Handler handler;
    GetIP getIp;

    public class MySocketBind extends Binder {
        public MySocketService getService(){
            return MySocketService.this;
        }
    }

    public MySocketService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        getIp = new GetIP();

        rm = "";
        sm = "";
        vs_user_name = "";
        new Thread(() -> {
            try {
                Log.e(TAG,"ip주소 : " + getIp.getIp());
                Log.e(TAG,"ip주소2 : " + getIp.getIp2());


                sock = new Socket(getIp.homeWifi,6000);

                Log.e(TAG,"서버와 연결되었습니다.");
                Log.e(TAG,"소켓 바인딩 체크 : " + sock.isBound());

            } catch (SocketException e){
                Log.e(TAG,"서버와 연결이 끊어졌습니다.");
                e.printStackTrace();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }).start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.e(TAG,"Service Destroy");
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG,"bind되나?");
        // TODO: Return the communication channel to the service.
        return mbinder;
    }

    public boolean getSockBind(){
        Log.e(TAG,"isBound : " + sock.isBound());
        return sock.isBound();
    }

    public void outNickName(String s){

        Log.e(TAG,"닉네임 : " + s);
        try {
            out = new PrintWriter(new OutputStreamWriter(sock.getOutputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Log.e(TAG,"String s : " + s);
        out.println(s);
        out.flush();
    }

    public void sendMessage(String s) {

        Log.e(TAG, "서버로 메세지 보내기");
        new Thread(() -> {
        try {
            out = new PrintWriter(new OutputStreamWriter(sock.getOutputStream()));
            out.println(s);
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        }).start();
    }


    public void receiveMessage(ArrayList<user_chat_item> serverDataList, String login_user_name, chatingAdapter adapter){
        Log.e(TAG,"서버에서 메세지 받기");

        new Thread(() -> {
            this.serverDataList = serverDataList;
            handler = new Handler(Looper.getMainLooper());
            try {
                in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
                while (true) {
                    rm = in.readLine();
                    if (rm != null) {
                        Log.e(TAG,"받은 메세지 : " + rm);
                        if (rm == null) break;
                        splitMS = rm.split("//");//받은 메세지 자르고
                        vs_user_name = splitMS[0];// 자르고 나온 유저의 이름
                        Log.e(TAG,"받은 유저 아이디 : " + vs_user_name);
                        Log.e(TAG,"현재 유저 아이디 : " + login_user_name);
                        if (vs_user_name.equals(login_user_name)) {
                            Log.e(TAG,"내메세지는 안받아");
                        }else {
                            sm = splitMS[1];// 자르고 나온 유저의 메세지
                            serverDataList.add(new user_chat_item(vs_user_name,sm));
                            Log.e(TAG,"null이 아니면 : " + sm);
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    adapter.notifyDataSetChanged();
                                }
                            });
                            Thread.sleep(500);
                        }
                    }
                }
                Log.e(TAG,"수신 끊겼나?");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}