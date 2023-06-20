package com.example.yachtdicev2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Random;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class VsUserInGame extends AppCompatActivity {

    Button chat_bt;
    String TAG = "VsUser";
    String loginUserNickName;
    MySocketService mss;
    boolean isMSS = false;
    boolean vs_p1_rollTurn,vs_p2_rollTurn;
    boolean userTurn = true;
    boolean start = true;
    int vs_p1_roll;
    View vsPlayer1View,vsPlayer2View;
    int vsP1ViewTop,vsP1ViewBottom,vsP1ViewLeft,vsP1ViewRight;
    int diceTop,diceBottom,diceLeft,diceRight,diceSize;
    ImageView vsP1KeepDice1,vsP1KeepDice2,vsP1KeepDice3,vsP1KeepDice4,vsP1KeepDice5;
    ImageView vsP2KeepDice1,vsP2KeepDice2,vsP2KeepDice3,vsP2KeepDice4,vsP2KeepDice5;
    Button vsGetScore,vsRollDice;
    ImageView vs_dice1,vs_dice2,vs_dice3,vs_dice4,vs_dice5;
    ImageView vs_P2Dice1,vs_P2Dice2,vs_P2Dice3,vs_P2Dice4,vs_P2Dice5;
    Drawable vs_dice_1,vs_dice_2,vs_dice_3,vs_dice_4,vs_dice_5,vs_dice_6;
    Drawable vs_rolldice_1xml,vs_rolldice_2xml,vs_rolldice_3xml,vs_rolldice_4xml,vs_rolldice_5xml;
    TextView player1_name,player2_name;
    RollDice rollDice = new RollDice();
    AnimatorSet vsAnimatorSet;
    AnimationDrawable vsRolldice_1,vsRolldice_2,vsRolldice_3,vsRolldice_4,vsRolldice_5;
    boolean dice1Keep_move,dice2Keep_move,dice3Keep_move,dice4Keep_move,dice5Keep_move;
    int dice1eye,dice2eye,dice3eye,dice4eye,dice5eye;
    String diceSum;
    Socket gameSock;
    PrintWriter out = null;
    useJson useJson;
    GetIP getIP = new GetIP();
    ReceiveMessage receiveMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vs_user_in_game);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

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


        // 닉네임 가져오기
        Intent intentNick = getIntent();
        loginUserNickName = intentNick.getStringExtra("loginUserNickName");
        Log.e(TAG,"loginUserNickName : " + loginUserNickName);

        useJson = new useJson();




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

        Intent intent = new Intent(VsUserInGame.this, MySocketService.class);
        bindService(intent, sconn, Context.BIND_AUTO_CREATE);

        // 주사위 굴리기
        vsRollDice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG,"roll클릭되나?");
                Log.e(TAG,"userTurn : " + receiveMessage.userTurn);

                if(receiveMessage.userTurn){
                    if (!vs_p1_rollTurn) {// 주사위 굴릴 수 있을지 없을지

                        vs_p1_roll += 1;
                        Log.e(TAG, "p1_roll : " + vs_p1_roll);

                        if (vs_p1_roll >= 3) {// 주사위 세번 굴리면 못굴리게
                            vs_p1_rollTurn = true;
                            Log.e(TAG, "p1_rollturn : " + vs_p1_rollTurn);
                        }

                        sendMessage(useJson.diceRollClick("DiceRollClick",loginUserNickName,vs_p1_roll));

                    }
                    else if (vs_p1_rollTurn) {

                        Log.e(TAG,"P1의 주사위 굴리기가 끝났습니다.");

                        AlertDialog.Builder builder = new AlertDialog.Builder(VsUserInGame.this);
                        builder.setTitle("주사위 굴리기가 끝났습니다.");
                        builder.setMessage("점수를 넣어주세요");
                        builder.show();

                    }
                }else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(VsUserInGame.this);
                    builder.setTitle("차례가 아닙니다.");
                    builder.show();
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

        // 주사위 크기 구하기
        diceTop = vs_dice1.getTop();
        diceBottom = vs_dice1.getBottom();
        diceLeft = vs_dice1.getLeft();
        diceRight = vs_dice1.getRight();

        Log.e(TAG,"diceTop : " + diceTop);
        Log.e(TAG,"diceBottom : " + diceBottom);
        Log.e(TAG,"diceLeft : " + diceLeft);
        Log.e(TAG,"diceRight : " + diceRight);

        diceSize = diceBottom - diceTop;

        if (start){
            new Thread(() -> {
                try {
                    gameSock = new Socket(getIP.homeWifi,9000);

                    receiveMessage = new ReceiveMessage(rollDice,vs_dice1,vs_dice2,vs_dice3,vs_dice4,vs_dice5
                            ,dice1Keep_move,dice2Keep_move,dice3Keep_move,dice4Keep_move,dice5Keep_move,vsRolldice_1
                            ,vsRolldice_2,vsRolldice_3,vsRolldice_4,vsRolldice_5,vs_rolldice_1xml,vs_rolldice_2xml
                            ,vs_rolldice_3xml,vs_rolldice_4xml,vs_rolldice_5xml,vs_dice_1,vs_dice_2,vs_dice_3,vs_dice_4
                            ,vs_dice_5,vs_dice_6,vsP1ViewTop,vsP1ViewBottom,vsP1ViewLeft,vsP1ViewRight,diceSize,userTurn);

                    receiveMessage.receiveMsg(gameSock);
                    sendMessage(useJson.startUser(loginUserNickName));

                    Handler handler = new Handler(Looper.getMainLooper());

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (receiveMessage.userTurn) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(VsUserInGame.this);
                                builder.setTitle("선입니다.");
                                builder.show();
                            }else if (!receiveMessage.userTurn){
                                AlertDialog.Builder builder = new AlertDialog.Builder(VsUserInGame.this);
                                builder.setTitle("후입니다.");
                                builder.show();
                            }
                        }
                    });
                    Thread.sleep(500);


                } catch (SocketException e){
                    Log.e(TAG,"서버와 연결이 끊어졌습니다.");
                    e.printStackTrace();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).start();

            start = false;
        }

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
}