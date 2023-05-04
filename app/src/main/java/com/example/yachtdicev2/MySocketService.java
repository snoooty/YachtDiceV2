package com.example.yachtdicev2;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;

public class MySocketService extends Service {

    private Socket sock;
    private String TAG = "MySocketService";
    IBinder mbinder =  new MySocketBind();
    String user_name;
    BufferedReader in = null;

    class MySocketBind extends Binder {
        MySocketService getService(){
            return MySocketService.this;
        }
    }

    public MySocketService() {
    }

    @Override
    public void onCreate() {
        new Thread(() -> {
            try {

                // 집 와이파이 사용할때..
//              sock = new Socket("172.30.1.17",6000);

                // 학원 와이파이 사용할때..
//                sock    = new Socket("172.30.1.12",6000);
                sock = new Socket("172.30.1.100",6000);

                Log.e(TAG,"서버와 연결되었습니다.");
                Log.e(TAG,"소켓 바인딩 체크 : " + sock.isBound());
                serverNickData();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }).start();
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        sock.isClosed();
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
//        PrintStream out = null;
        PrintWriter out = null;
        Log.e(TAG,"닉네임 : " + s);
        try {
            out = new PrintWriter(new OutputStreamWriter(sock.getOutputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Log.e(TAG,"String s : " + s);
        out.println(s);
        out.flush();
        out.close();
    }

    public String serverNickData(){

        Log.e(TAG,"닉네임 읽어오기는 함?");
        new Thread(() -> {
            try {
                in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
                while (user_name != null) {
                    Log.e(TAG,"null이 아니면 : " + user_name);
                    user_name = in.readLine();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }).start();
        return user_name;
    }
}