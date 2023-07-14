package com.example.yachtdicev2.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.example.yachtdicev2.ip.GetIP;
import com.example.yachtdicev2.room.RoomReceiveMessage;
import com.example.yachtdicev2.room.roomAdapter;
import com.example.yachtdicev2.room.room_item;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

public class MyGameServerService extends Service {

    private String TAG = "MyGameServerService";
    GetIP getIp;
    BufferedReader in = null;
    PrintWriter out = null;
    public Socket gameSock;
    IBinder mBinder = new GameServerBind();
    public ArrayList<room_item> serverList;
    RoomReceiveMessage RRM = new RoomReceiveMessage();
    public roomAdapter adapter;

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

        getIp = new GetIP();
        serverList = new ArrayList<>();
        adapter = new roomAdapter(serverList);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

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
        Log.e(TAG,"보내는 메세지 : " + s);
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