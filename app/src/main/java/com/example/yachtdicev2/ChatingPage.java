package com.example.yachtdicev2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.util.ArrayList;

public class ChatingPage extends AppCompatActivity {

    private RecyclerView recyclerView;
    private chatingAdapter adapter;
    private ArrayList<user_chat_item> chatActivityDataList;
    String loginUserNickName;
    EditText editText;
    String TAG = "ChatingPage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chating_page);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        if(Build.VERSION.SDK_INT < 30){
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        } else{
            getWindow().setDecorFitsSystemWindows(true);
        }

        Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);

        int pointWidth = point.x; // 가로
        int pointHeight = point.y; // 세로

        int width = (int) (pointWidth * 0.7); // Display 가로 사이즈
        int height = (int) (pointHeight * 0.9);  // Display 높이 사이즈

        getWindow().getAttributes().width = width; // 가로 크기
        getWindow().getAttributes().height = height; // 세로 크기
        getWindow().getAttributes().gravity = Gravity.LEFT; // 위치 설정

        Intent intentNick = getIntent();
        loginUserNickName = intentNick.getStringExtra("loginUserNickName");

        editText = findViewById(R.id.inputText);

        recyclerView = findViewById(R.id.chat_recyclerview);

        adapter = new chatingAdapter();

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        chatActivityDataList = new ArrayList<>();

        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                switch (keyCode){
                    case KeyEvent.KEYCODE_ENTER:
                        Log.e(TAG,"키보드 엔터 클릭되나?");
                        Log.e(TAG,"텍스트 입력값 : " + editText.getText().toString());
                        if (editText.getText().toString().length() != 0){
                            chatActivityDataList.add(new user_chat_item(loginUserNickName,editText.getText().toString()));
                            editText.setText(null);
                            adapter.notifyDataSetChanged();
                            break;
//                            hideKeyboard();
                        }else {
//                            hideKeyboard();
                        }
                        break;
                }
                return false;
            }
        });

        adapter.setAdapterChatList(chatActivityDataList);
    }

    // 키보드 내리기
    void hideKeyboard()
    {
        InputMethodManager inputManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }
}