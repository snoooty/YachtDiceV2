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
import android.os.IBinder;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vs_user_in_game);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

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

        new Thread(() -> {
            try {
                gameSock = new Socket("172.30.1.17",9000);
            } catch (SocketException e){
                Log.e(TAG,"서버와 연결이 끊어졌습니다.");
                e.printStackTrace();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();

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

        // 주사위 굴리기
        vsRollDice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG,"roll클릭되나?");
                if (!vs_p1_rollTurn) {// 주사위 굴릴 수 있을지 없을지

                    vs_p1_roll += 1;
                    Log.e(TAG, "p1_roll : " + vs_p1_roll);

                    if (vs_p1_roll >= 3) {// 주사위 세번 굴리면 못굴리게
                        vs_p1_rollTurn = true;
                        Log.e(TAG, "p1_rollturn : " + vs_p1_rollTurn);
                    }

                    sendMessage(useJson.diceRollClick("DiceRollClick",loginUserNickName,vs_p1_roll));

                    // 주사위 좌표구하기
                    rollDice.rollDice(vsP1ViewTop,vsP1ViewLeft,vsP1ViewBottom,vsP1ViewRight,diceSize);

                    // 주사위 애니메이션 효과
                    diceAnimation();
                }
//                else if (vs_p1_rollTurn) {
//                    Log.e(TAG,"P1의 턴이 끝났습니다.");
//
//                    AlertDialog.Builder builder = new AlertDialog.Builder(VsUserInGame.this);
//                    builder.setTitle("주사위 굴리기가 끝났습니다.");
//                    builder.show();
//                }
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
    }

    public void diceAnimation(){
        // 주사위 애니메이션
        ObjectAnimator animaX_dice1 = ObjectAnimator.ofFloat(vs_dice1, "translationX", rollDice.dice1Left);
        ObjectAnimator animaY_dice1 = ObjectAnimator.ofFloat(vs_dice1, "translationY", rollDice.dice1Top);
        animaX_dice1.setDuration(400);
        animaY_dice1.setDuration(400);

        ObjectAnimator animaX_dice2 = ObjectAnimator.ofFloat(vs_dice2, "translationX", rollDice.dice2Left);
        ObjectAnimator animaY_dice2 = ObjectAnimator.ofFloat(vs_dice2, "translationY", rollDice.dice2Top);
        animaX_dice2.setDuration(400);
        animaY_dice2.setDuration(400);

        ObjectAnimator animaX_dice3 = ObjectAnimator.ofFloat(vs_dice3, "translationX", rollDice.dice3Left);
        ObjectAnimator animaY_dice3 = ObjectAnimator.ofFloat(vs_dice3, "translationY", rollDice.dice3Top);
        animaX_dice3.setDuration(400);
        animaY_dice3.setDuration(400);

        ObjectAnimator animaX_dice4 = ObjectAnimator.ofFloat(vs_dice4, "translationX", rollDice.dice4Left);
        ObjectAnimator animaY_dice4 = ObjectAnimator.ofFloat(vs_dice4, "translationY", rollDice.dice4Top);
        animaX_dice4.setDuration(400);
        animaY_dice4.setDuration(400);

        ObjectAnimator animaX_dice5 = ObjectAnimator.ofFloat(vs_dice5, "translationX", rollDice.dice5Left);
        ObjectAnimator animaY_dice5 = ObjectAnimator.ofFloat(vs_dice5, "translationY", rollDice.dice5Top);
        animaX_dice5.setDuration(400);
        animaY_dice5.setDuration(400);

        vsAnimatorSet = new AnimatorSet();

        if (!dice1Keep_move) {
            // dice1 생성
            vs_dice1.setImageResource(0);
            vs_dice1.setImageDrawable(vs_rolldice_1xml);
            vsRolldice_1 = new AnimationDrawable();
            vsRolldice_1 = (AnimationDrawable) vs_dice1.getDrawable();
            vsRolldice_1.setOneShot(true);

            vsRolldice_1.stop();
            vsRolldice_1.start();

            vs_dice1.setVisibility(View.VISIBLE);

            // dice1 이동
            vsAnimatorSet.play(animaX_dice1);
            vsAnimatorSet.play(animaY_dice1);
        }

        if (!dice2Keep_move) {
            // dice2 생성
            vs_dice2.setImageResource(0);
            vs_dice2.setImageDrawable(vs_rolldice_2xml);
            vsRolldice_2 = new AnimationDrawable();
            vsRolldice_2 = (AnimationDrawable) vs_dice2.getDrawable();
            vsRolldice_2.setOneShot(true);

            vsRolldice_2.stop();
            vsRolldice_2.start();

            vs_dice2.setVisibility(View.VISIBLE);

            // dice1 이동
            vsAnimatorSet.play(animaX_dice2);
            vsAnimatorSet.play(animaY_dice2);
        }

        if (!dice3Keep_move) {
            // dice3 생성
            vs_dice3.setImageResource(0);
            vs_dice3.setImageDrawable(vs_rolldice_3xml);
            vsRolldice_3 = new AnimationDrawable();
            vsRolldice_3 = (AnimationDrawable) vs_dice3.getDrawable();
            vsRolldice_3.setOneShot(true);

            vsRolldice_3.stop();
            vsRolldice_3.start();

            vs_dice3.setVisibility(View.VISIBLE);

            // dice1 이동
            vsAnimatorSet.play(animaX_dice3);
            vsAnimatorSet.play(animaY_dice3);
        }

        if (!dice4Keep_move) {
            // dice4 생성
            vs_dice4.setImageResource(0);
            vs_dice4.setImageDrawable(vs_rolldice_4xml);
            vsRolldice_4 = new AnimationDrawable();
            vsRolldice_4 = (AnimationDrawable) vs_dice4.getDrawable();
            vsRolldice_4.setOneShot(true);

            vsRolldice_4.stop();
            vsRolldice_4.start();

            vs_dice4.setVisibility(View.VISIBLE);

            // dice1 이동
            vsAnimatorSet.play(animaX_dice4);
            vsAnimatorSet.play(animaY_dice4);
        }

        if (!dice5Keep_move) {
            // dice5 생성
            vs_dice5.setImageResource(0);
            vs_dice5.setImageDrawable(vs_rolldice_5xml);
            vsRolldice_5 = new AnimationDrawable();
            vsRolldice_5 = (AnimationDrawable) vs_dice5.getDrawable();
            vsRolldice_5.setOneShot(true);

            vsRolldice_5.stop();
            vsRolldice_5.start();

            vs_dice5.setVisibility(View.VISIBLE);

            // dice1 이동
            vsAnimatorSet.play(animaX_dice5);
            vsAnimatorSet.play(animaY_dice5);
        }

        vsAnimatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

                Random rnd1 = new Random();

                // dice1 랜덤
                int roll1 = rnd1.nextInt(6);

                if (!dice1Keep_move) {
                    switch (roll1) {
                        case 0:
                            vs_dice1.setImageDrawable(vs_dice_1);
                            dice1eye = 1;
                            break;
                        case 1:
                            vs_dice1.setImageDrawable(vs_dice_2);
                            dice1eye = 2;
                            break;
                        case 2:
                            vs_dice1.setImageDrawable(vs_dice_3);
                            dice1eye = 3;
                            break;
                        case 3:
                            vs_dice1.setImageDrawable(vs_dice_4);
                            dice1eye = 4;
                            break;
                        case 4:
                            vs_dice1.setImageDrawable(vs_dice_5);
                            dice1eye = 5;
                            break;
                        case 5:
                            vs_dice1.setImageDrawable(vs_dice_6);
                            dice1eye = 6;
                            break;
                    }
                }

                Random rnd2 = new Random();

                // dice2 랜덤
                int roll2 = rnd2.nextInt(6);

                if (!dice2Keep_move) {
                    switch (roll2) {
                        case 0:
                            vs_dice2.setImageDrawable(vs_dice_1);
                            dice2eye = 1;
                            break;
                        case 1:
                            vs_dice2.setImageDrawable(vs_dice_2);
                            dice2eye = 2;
                            break;
                        case 2:
                            vs_dice2.setImageDrawable(vs_dice_3);
                            dice2eye = 3;
                            break;
                        case 3:
                            vs_dice2.setImageDrawable(vs_dice_4);
                            dice2eye = 4;
                            break;
                        case 4:
                            vs_dice2.setImageDrawable(vs_dice_5);
                            dice2eye = 5;
                            break;
                        case 5:
                            vs_dice2.setImageDrawable(vs_dice_6);
                            dice2eye = 6;
                            break;
                    }
                }

                Random rnd3 = new Random();

                // dice3 랜덤
                int roll3 = rnd3.nextInt(6);

                if (!dice3Keep_move) {
                    switch (roll3) {
                        case 0:
                            vs_dice3.setImageDrawable(vs_dice_1);
                            dice3eye = 1;
                            break;
                        case 1:
                            vs_dice3.setImageDrawable(vs_dice_2);
                            dice3eye = 2;
                            break;
                        case 2:
                            vs_dice3.setImageDrawable(vs_dice_3);
                            dice3eye = 3;
                            break;
                        case 3:
                            vs_dice3.setImageDrawable(vs_dice_4);
                            dice3eye = 4;
                            break;
                        case 4:
                            vs_dice3.setImageDrawable(vs_dice_5);
                            dice3eye = 5;
                            break;
                        case 5:
                            vs_dice3.setImageDrawable(vs_dice_6);
                            dice3eye = 6;
                            break;
                    }
                }

                Random rnd4 = new Random();

                // dice4 랜덤
                int roll4 = rnd4.nextInt(6);

                if (!dice4Keep_move) {
                    switch (roll4) {
                        case 0:
                            vs_dice4.setImageDrawable(vs_dice_1);
                            dice4eye = 1;
                            break;
                        case 1:
                            vs_dice4.setImageDrawable(vs_dice_2);
                            dice4eye = 2;
                            break;
                        case 2:
                            vs_dice4.setImageDrawable(vs_dice_3);
                            dice4eye = 3;
                            break;
                        case 3:
                            vs_dice4.setImageDrawable(vs_dice_4);
                            dice4eye = 4;
                            break;
                        case 4:
                            vs_dice4.setImageDrawable(vs_dice_5);
                            dice4eye = 5;
                            break;
                        case 5:
                            vs_dice4.setImageDrawable(vs_dice_6);
                            dice4eye = 6;
                            break;
                    }
                }

                Random rnd5 = new Random();

                // dice5 랜덤
                int roll5 = rnd5.nextInt(6);

                if (!dice5Keep_move) {
                    switch (roll5) {
                        case 0:
                            vs_dice5.setImageDrawable(vs_dice_1);
                            dice5eye = 1;
                            break;
                        case 1:
                            vs_dice5.setImageDrawable(vs_dice_2);
                            dice5eye = 2;
                            break;
                        case 2:
                            vs_dice5.setImageDrawable(vs_dice_3);
                            dice5eye = 3;
                            break;
                        case 3:
                            vs_dice5.setImageDrawable(vs_dice_4);
                            dice5eye = 4;
                            break;
                        case 4:
                            vs_dice5.setImageDrawable(vs_dice_5);
                            dice5eye = 5;
                            break;
                        case 5:
                            vs_dice5.setImageDrawable(vs_dice_6);
                            dice5eye = 6;
                            break;
                    }
                }
                Log.e(TAG, "dice sum : " + dice1eye + dice2eye + dice3eye + dice4eye + dice5eye);
                diceSum = String.valueOf(dice1eye) + String.valueOf(dice2eye) + String.valueOf(dice3eye)
                        + String.valueOf(dice4eye) + String.valueOf(dice5eye);
                Log.e(TAG,"diceSum : " + diceSum);
            }
        });
        vsAnimatorSet.start();
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