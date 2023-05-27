package com.example.yachtdicev2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class VsUserInGame extends AppCompatActivity {

    Button chat_bt;
    String TAG = "VsUser";
    String loginUserNickName;
    MySocketService mss;
    boolean isMSS = false;
    boolean vs_p1_rollTurn,vs_p2_rollTurn;
    int vs_p1_roll;
    View vsPlayer1View,vsPlayer2View;
    int vsP1ViewTop,vsP1ViewBottom,vsP1ViewLeft,vsP1ViewRight;
    ImageView vsP1KeepDice1,vsP1KeepDice2,vsP1KeepDice3,vsP1KeepDice4,vsP1KeepDice5;
    ImageView vsP2KeepDice1,vsP2KeepDice2,vsP2KeepDice3,vsP2KeepDice4,vsP2KeepDice5;
    Button vsGetScore,vsRollDice;
    ImageView vs_dice1,vs_dice2,vs_dice3,vs_dice4,vs_dice5;
    ImageView vs_P2Dice1,vs_P2Dice2,vs_P2Dice3,vs_P2Dice4,vs_P2Dice5;
    Drawable vs_dice_1,vs_dice_2,vs_dice_3,vs_dice_4,vs_dice_5,vs_dice_6;
    Drawable vs_rolldice_1xml,vs_rolldice_2xml,vs_rolldice_3xml,vs_rolldice_4xml,vs_rolldice_5xml;
    TextView player1_name,player2_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vs_user_in_game);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        ServiceConnection sconn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.e(TAG,"실행되나?");
                MySocketService.MySocketBind msb = (MySocketService.MySocketBind) service;
                mss = msb.getService();
                isMSS = true;
                Log.e(TAG,"sockBindCheck : " + mss.getSockBind());
            }
            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.e(TAG,"실행안되나?");
                isMSS = false;
            }
        };

        vsPlayer1View = findViewById(R.id.vs_player1_view);
        vsPlayer2View = findViewById(R.id.vs_player2_view);
        player1_name = findViewById(R.id.vs_PLAYER_1);
        player2_name = findViewById(R.id.vs_PLAYER_2);
        vsP1KeepDice1 = findViewById(R.id.vs_keep_Dice_player1_1);
        vsP1KeepDice2 = findViewById(R.id.vs_keep_Dice_player1_2);
        vsP1KeepDice3 = findViewById(R.id.vs_keep_Dice_player1_3);
        vsP1KeepDice4 = findViewById(R.id.vs_keep_Dice_player1_4);
        vsP1KeepDice5 = findViewById(R.id.vs_keep_Dice_player1_5);
        vsP2KeepDice1 = findViewById(R.id.vs_keep_Dice_player2_1);
        vsP2KeepDice2 = findViewById(R.id.vs_keep_Dice_player2_2);
        vsP2KeepDice3 = findViewById(R.id.vs_keep_Dice_player2_3);
        vsP2KeepDice4 = findViewById(R.id.vs_keep_Dice_player2_4);
        vsP2KeepDice5 = findViewById(R.id.vs_keep_Dice_player2_5);
        vsGetScore = findViewById(R.id.vs_getScore);
        vsRollDice = findViewById(R.id.vs_player_1_rollDice);
        vs_dice1 = (ImageView) findViewById(R.id.vs_player_1_dice_1);
        vs_dice2 = (ImageView) findViewById(R.id.vs_player_1_dice_2);
        vs_dice3 = (ImageView) findViewById(R.id.vs_player_1_dice_3);
        vs_dice4 = (ImageView) findViewById(R.id.vs_player_1_dice_4);
        vs_dice5 = (ImageView) findViewById(R.id.vs_player_1_dice_5);
        vs_P2Dice1 = (ImageView) findViewById(R.id.vs_player_2_dice_1);
        vs_P2Dice2 = (ImageView) findViewById(R.id.vs_player_2_dice_2);
        vs_P2Dice3 = (ImageView) findViewById(R.id.vs_player_2_dice_3);
        vs_P2Dice4 = (ImageView) findViewById(R.id.vs_player_2_dice_4);
        vs_P2Dice5 = (ImageView) findViewById(R.id.vs_player_2_dice_5);
        vs_dice_1 = (Drawable)  getResources().getDrawable(R.drawable.dice_1);
        vs_dice_2 = (Drawable)  getResources().getDrawable(R.drawable.dice_2);
        vs_dice_3 = (Drawable)  getResources().getDrawable(R.drawable.dice_3);
        vs_dice_4 = (Drawable)  getResources().getDrawable(R.drawable.dice_4);
        vs_dice_5 = (Drawable)  getResources().getDrawable(R.drawable.dice_5);
        vs_dice_6 = (Drawable)  getResources().getDrawable(R.drawable.dice_6);
        vs_rolldice_1xml = (Drawable) getResources().getDrawable(R.drawable.rolldice_1);
        vs_rolldice_2xml = (Drawable) getResources().getDrawable(R.drawable.rolldice_2);
        vs_rolldice_3xml = (Drawable) getResources().getDrawable(R.drawable.rolldice_3);
        vs_rolldice_4xml = (Drawable) getResources().getDrawable(R.drawable.rolldice_4);
        vs_rolldice_5xml = (Drawable) getResources().getDrawable(R.drawable.rolldice_5);


        Intent intent = new Intent(VsUserInGame.this, MySocketService.class);
        bindService(intent, sconn, Context.BIND_AUTO_CREATE);

        // 닉네임 가져오기
        Intent intentNick = getIntent();
        loginUserNickName = intentNick.getStringExtra("loginUserNickName");
        Log.e(TAG,"loginUserNickName : " + loginUserNickName);

        // 주사위 굴리기
        vsRollDice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG,"roll클릭되나?");
                if (!vs_p1_rollTurn) {// 주사위 굴릴 수 있을지 없을지

//                    vs_p1_roll += 1;
//                    Log.e(TAG, "p1_roll : " + vs_p1_roll);
//
//                    if (vs_p1_roll >= 3) {// 주사위 세번 굴리면 못굴리게
//                        vs_p1_rollTurn = true;
//                        Log.e(TAG, "p1_rollturn : " + vs_p1_rollTurn);
//                    }

                }
            }
        });

        chat_bt = findViewById(R.id.chat_bt);

        chat_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG,"채팅 클릭되나?");

                Intent intent = new Intent(VsUserInGame.this,ChatingPage.class);
                intent.putExtra("loginUserNickName",loginUserNickName);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        Log.e(TAG,"P1 view Display top 값 : " + vsPlayer1View.getTop());
        Log.e(TAG,"P1 view Display bottom 값 : " + vsPlayer1View.getBottom());
        Log.e(TAG,"P1 view Display left 값 : " + vsPlayer1View.getLeft());
        Log.e(TAG,"P1 view Display right 값 : " + vsPlayer1View.getRight());
        // Player1 의 view 좌표 구하기
        vsP1ViewTop = vsPlayer1View.getTop();
        vsP1ViewBottom = vsPlayer1View.getBottom();
        vsP1ViewLeft = vsPlayer1View.getLeft();
        vsP1ViewRight = vsPlayer1View.getRight();
    }
}