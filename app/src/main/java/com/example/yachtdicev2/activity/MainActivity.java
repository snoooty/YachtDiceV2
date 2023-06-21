package com.example.yachtdicev2.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.yachtdicev2.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 4초후에 로그인 액티비티로 이동하기
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(MainActivity.this, Login_Activity.class);
            startActivity(intent);
            finish();
        }, 4000);
    }
}