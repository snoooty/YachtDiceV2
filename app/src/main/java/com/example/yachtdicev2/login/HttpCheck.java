package com.example.yachtdicev2.login;

import android.os.StrictMode;
import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpCheck {

    String TAG = "HttpCheck";

    public void httpCheck(URL url, HttpURLConnection connection){

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try{
            connection = (HttpURLConnection) url.openConnection();
            Log.e(TAG,"응답결과 : " + connection.getResponseCode());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
