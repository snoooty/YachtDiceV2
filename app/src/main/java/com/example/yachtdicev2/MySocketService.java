package com.example.yachtdicev2;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class MySocketService extends Service {

    private Socket sock;
    private String TAG = "MySocketService";

    IBinder mbinder =  new MySocketBind();

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
                sock = new Socket("172.30.1.17",6000);

                // 학원 와이파이 사용할때..
//                    sk = new Socket("172.30.1.12",6000);

                Log.e(TAG,"서버와 연결되었습니다.");
                Log.e(TAG,"소켓 바인딩 체크 : " + sock.isBound());

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
        PrintStream out = null;
        Log.e(TAG,"닉네임 : " + s);
        try {
            out = new PrintStream(sock.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        out.println(s);
        out.flush();
    }
}