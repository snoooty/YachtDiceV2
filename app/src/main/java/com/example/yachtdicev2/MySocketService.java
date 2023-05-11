package com.example.yachtdicev2;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

public class MySocketService extends Service {

    private Socket sock;
    private String TAG = "MySocketService";
    IBinder mbinder =  new MySocketBind();
    String user_name;
    String ms;
    BufferedReader in = null;
    PrintWriter out = null;
    private ArrayList<user_chat_item> serverDataList;

    class MySocketBind extends Binder {
        MySocketService getService(){
            return MySocketService.this;
        }
    }

    public MySocketService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ms = "";
        new Thread(() -> {
            try {
                // 집 와이파이 사용할때..
//              sock = new Socket("172.30.1.17",6000);

                // 학원 와이파이 사용할때..
//                sock    = new Socket("172.30.1.12",6000);
//                sock = new Socket("172.30.1.100",6000);
                sock = new Socket("172.30.1.98",6000);

                // 예빈이네 와이파이 사용할때...
//                sock = new Socket("192.168.35.179",6000);

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

        try {
            in.close();
            out.close();
            sock.close();
            Log.e(TAG,"종료되나?");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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


    public void receiveMessage(ArrayList<user_chat_item> serverDataList){
        Log.e(TAG,"서버에서 메세지 받기");

        new Thread(() -> {
            this.serverDataList = serverDataList;
            try {
                in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
                while (ms != null) {
                    ms = in.readLine();
                    if (ms == null) break;
                    serverDataList.add(new user_chat_item("Server 알림",ms));
                    Log.e(TAG,"null이 아니면 : " + ms);
                }
                Log.e(TAG,"수신 끊겼나?");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }).start();
    }
}