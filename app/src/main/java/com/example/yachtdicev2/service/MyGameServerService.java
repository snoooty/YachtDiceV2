package com.example.yachtdicev2.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.example.yachtdicev2.ip.GetIP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

public class MyGameServerService extends Service {

    private String TAG = "MyGameServerService";
    GetIP getIp;
    BufferedReader in = null;
    PrintWriter out = null;
    public Socket gameSock;
    IBinder mBinder = new GameServerBind();

    public class GameServerBind extends Binder{
        public MyGameServerService getService(){
            return MyGameServerService.this;
        }
    }

    public MyGameServerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate(){
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        getIp = new GetIP();

        new Thread(() -> {

            try {
                gameSock = new Socket(getIp.currentIP(),9000);

                Log.e(TAG,"게임서버와 연결되었습니다.");
            }catch (SocketException e){
                Log.e(TAG,"서버와 연결이 끊어졌습니다.");
                e.printStackTrace();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }).start();

        return super.onStartCommand(intent, flags, startId);
    }

    public void sendMessage(String s) {

        Log.e(TAG, "서버로 메세지 보내기");
        new Thread(() -> {
            try {
                out = new PrintWriter(new OutputStreamWriter(gameSock.getOutputStream()));
                out.println(s);
                out.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    public void createRoom(String s){

        Log.e(TAG, "서버로 방 만든다고 알림 보내기");
        new Thread(() -> {
            try {
                out = new PrintWriter(new OutputStreamWriter(gameSock.getOutputStream()));
                out.println(s);
                out.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }).start();
    }


}