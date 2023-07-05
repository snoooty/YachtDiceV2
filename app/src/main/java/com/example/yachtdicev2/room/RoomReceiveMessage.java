package com.example.yachtdicev2.room;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class RoomReceiveMessage {

    BufferedReader in = null;
    PrintWriter out = null;
    String TAG = "RoomReceiveMessage";
    String msg;
    Handler handler;
    JSONObject jsonObject;

    public void RRMsg(Socket sock,ArrayList<room_item> list,roomAdapter adapter){

        Log.e(TAG,"메세지 받기");

        new Thread(() -> {

            handler = new Handler(Looper.getMainLooper());


            try {
                in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
                msg = in.readLine();
                jsonObject = new JSONObject(msg);
                String category = (String) jsonObject.get("category");

                if (category.equals("createRoom")) {
                    int roomNum = jsonObject.getInt("roomNum");
                    String mainUser = jsonObject.getString("userName");
                    int personnel = jsonObject.getInt("personnel");

                    list.add(new room_item(mainUser,roomNum,personnel));
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            adapter.notifyDataSetChanged();
                        }
                    });
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

        }).start();

    }
}
