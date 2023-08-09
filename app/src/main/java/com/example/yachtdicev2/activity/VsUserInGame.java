package com.example.yachtdicev2.activity;

import static android.graphics.Color.GRAY;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.animation.AnimatorSet;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yachtdicev2.R;
import com.example.yachtdicev2.gameLogic.ReceiveMessage;
import com.example.yachtdicev2.gameLogic.RollDice;
import com.example.yachtdicev2.gameLogic.TimerData;
import com.example.yachtdicev2.gameLogic.gameData;
import com.example.yachtdicev2.ip.GetIP;
import com.example.yachtdicev2.service.MyGameServerService;
import com.example.yachtdicev2.service.MySocketService;
import com.example.yachtdicev2.useJson;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

public class VsUserInGame extends AppCompatActivity {

    Button chat_bt;
    String TAG = "VsUser";
    String loginUserNickName;
    MySocketService mss;
    boolean isMSS = false;
    boolean vs_rollTurn;
    boolean userTurn = true;
    boolean start = true;
    int vs_roll;
    int player1totalScore,player2totalScore;
    TextView vsPlayer1View,vsPlayer2View;
    int vsP1ViewTop,vsP1ViewBottom,vsP1ViewLeft,vsP1ViewRight;
    int vsP2ViewTop,vsP2ViewBottom,vsP2ViewLeft,vsP2ViewRight;
    int diceTop,diceBottom,diceLeft,diceRight,diceSize;
    ImageView vsP1KeepDice1,vsP1KeepDice2,vsP1KeepDice3,vsP1KeepDice4,vsP1KeepDice5;
    ImageView vsP2KeepDice1,vsP2KeepDice2,vsP2KeepDice3,vsP2KeepDice4,vsP2KeepDice5;
    TextView user1,user2;
    Button vsGetScore;
    ImageButton vsRollDice;
    ImageView vs_dice1,vs_dice2,vs_dice3,vs_dice4,vs_dice5;
    ImageView vs_P2Dice1,vs_P2Dice2,vs_P2Dice3,vs_P2Dice4,vs_P2Dice5;
    ImageView playerTurn,diceBox;
    Drawable vs_dice_1,vs_dice_2,vs_dice_3,vs_dice_4,vs_dice_5,vs_dice_6;
    Drawable vs_rolldice_1xml,vs_rolldice_2xml,vs_rolldice_3xml,vs_rolldice_4xml,vs_rolldice_5xml;
    TextView player1_name,player2_name;
    RollDice rollDice = new RollDice();
    AnimationDrawable vsRolldice_1,vsRolldice_2,vsRolldice_3,vsRolldice_4,vsRolldice_5;
    boolean dice1Keep_move = true,dice2Keep_move = true,dice3Keep_move = true,dice4Keep_move = true,dice5Keep_move = true;
    PrintWriter out = null;
    com.example.yachtdicev2.useJson useJson;
    GetIP getIP = new GetIP();
    ReceiveMessage receiveMessage;
    public SharedPreferences sharedPreferences,sharedPreferences2;
    public ActivityResultLauncher<Intent> getResult;
    Activity activity;
    MyGameServerService gss;
    boolean isGSS = false;
    TimerData timerData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vs_user_in_game);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        Intent serverIntent = new Intent(VsUserInGame.this,MyGameServerService.class);

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
        vs_dice1.bringToFront();
        vs_dice2 = (ImageView) findViewById(R.id.vs_player_1_dice_2);
        vs_dice2.bringToFront();
        vs_dice3 = (ImageView) findViewById(R.id.vs_player_1_dice_3);
        vs_dice3.bringToFront();
        vs_dice4 = (ImageView) findViewById(R.id.vs_player_1_dice_4);
        vs_dice4.bringToFront();
        vs_dice5 = (ImageView) findViewById(R.id.vs_player_1_dice_5);
        vs_dice5.bringToFront();
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
        playerTurn = findViewById(R.id.playerTurn);
        diceBox = findViewById(R.id.diceBox);
        user1 = findViewById(R.id.vs_PLAYER_1);
        user2 = findViewById(R.id.vs_PLAYER_2);
        activity = this;

        playerTurn.setVisibility(View.GONE);

        vsPlayer1View.setText(String.valueOf(0));
        vsPlayer1View.setTextSize(150);
        vsPlayer1View.setTextColor(GRAY);
        vsPlayer1View.setGravity(View.TEXT_ALIGNMENT_CENTER);
        vsPlayer1View.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        timerData = new ViewModelProvider(this).get(TimerData.class);
        final Observer<Integer> timerObserver = new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                vsPlayer1View.setText(String.valueOf(integer));
            }
        };
        timerData.getTimer().observe(this,timerObserver);



        getResult = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                            if (result.getResultCode() == Activity.RESULT_OK){
                                Log.e(TAG,"Activity 복귀?");
                                Log.e(TAG,"status : " + result.getData().getStringExtra("status"));
                                Log.e(TAG,"이름 : " + result.getData().getStringExtra("name"));
                                Log.e(TAG,"score : " + result.getData().getIntExtra("score",0));
                                Log.e(TAG,"player1totalScore : " + result.getData().getIntExtra("player1totalScore",0));
                                Log.e(TAG,"player2totalScore : " + result.getData().getIntExtra("player2totalScore",0));

                                String player = result.getData().getStringExtra("status");
                                String scoreName = result.getData().getStringExtra("name");
                                receiveMessage.player1totalScore = result.getData().getIntExtra("player1totalScore",0);
                                receiveMessage.player2totalScore = result.getData().getIntExtra("player2totalScore",0);
                                int score = result.getData().getIntExtra("score",0);

                                sendMessage(useJson.scoreClick("ScoreClick",player,scoreName,score));
                            }
                    }
                });

        ServiceConnection gsConn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.e(TAG,"gss 실행되나?");
                MyGameServerService.GameServerBind gsb = (MyGameServerService.GameServerBind) service;
                gss = gsb.getService();
                isGSS = true;
            }
            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.e(TAG,"gss 실행안되나?");
                isGSS = false;
            }
        };

        bindService(serverIntent, gsConn, Context.BIND_AUTO_CREATE);

                // 닉네임 가져오기
        Intent intentNick = getIntent();
        loginUserNickName = intentNick.getStringExtra("loginUserNickName");
        Log.e(TAG,"loginUserNickName : " + loginUserNickName);

        useJson = new useJson();

        sharedPreferences = getSharedPreferences("vsP1Score",MODE_PRIVATE);
        sharedPreferences2 = getSharedPreferences("vsP2Score",MODE_PRIVATE);

        gameData data = new gameData(sharedPreferences,sharedPreferences2);
        data.player1dataReset();
        data.player2dataReset();


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
                    if (!receiveMessage.vs_rollTurn) {// 주사위 굴릴 수 있을지 없을지

                        receiveMessage.vs_roll += 1;
                        Log.e(TAG, "roll : " + receiveMessage.vs_roll);

                        if (receiveMessage.vs_roll >= 3) {// 주사위 세번 굴리면 못굴리게
                            receiveMessage.vs_rollTurn = true;
                            Log.e(TAG, "rollturn : " + receiveMessage.vs_rollTurn);
                        }

                        gss.sendMessage(useJson.diceRollClick("DiceRollClick",loginUserNickName,vs_roll
                                ,dice1Keep_move,dice2Keep_move,dice3Keep_move,dice4Keep_move,dice5Keep_move));

                    }
                    else if (receiveMessage.vs_rollTurn) {

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

        vs_dice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG,"dice1 클릭되나요?");

                if (receiveMessage.userTurn){

                    sendMessage(useJson.diceKeepClick("DiceKeepClick",loginUserNickName,1,receiveMessage.dice1Keep_move,
                            receiveMessage.dice2Keep_move,receiveMessage.dice3Keep_move,receiveMessage.dice4Keep_move,receiveMessage.dice5Keep_move));

                }else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(VsUserInGame.this);
                    builder.setTitle("차례가 아닙니다.");
                    builder.show();
                }
            }
        });

        vs_dice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (receiveMessage.userTurn){

                    sendMessage(useJson.diceKeepClick("DiceKeepClick",loginUserNickName,2,receiveMessage.dice1Keep_move,
                            receiveMessage.dice2Keep_move,receiveMessage.dice3Keep_move,receiveMessage.dice4Keep_move,receiveMessage.dice5Keep_move));

                }else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(VsUserInGame.this);
                    builder.setTitle("차례가 아닙니다.");
                    builder.show();
                }

            }
        });

        vs_dice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (receiveMessage.userTurn){

                    sendMessage(useJson.diceKeepClick("DiceKeepClick",loginUserNickName,3,receiveMessage.dice1Keep_move,
                            receiveMessage.dice2Keep_move,receiveMessage.dice3Keep_move,receiveMessage.dice4Keep_move,receiveMessage.dice5Keep_move));

                }else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(VsUserInGame.this);
                    builder.setTitle("차례가 아닙니다.");
                    builder.show();
                }

            }
        });

        vs_dice4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (receiveMessage.userTurn){

                    sendMessage(useJson.diceKeepClick("DiceKeepClick",loginUserNickName,4,receiveMessage.dice1Keep_move,
                            receiveMessage.dice2Keep_move,receiveMessage.dice3Keep_move,receiveMessage.dice4Keep_move,receiveMessage.dice5Keep_move));

                }else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(VsUserInGame.this);
                    builder.setTitle("차례가 아닙니다.");
                    builder.show();
                }

            }
        });

        vs_dice5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (receiveMessage.userTurn){

                    sendMessage(useJson.diceKeepClick("DiceKeepClick",loginUserNickName,5,receiveMessage.dice1Keep_move,
                            receiveMessage.dice2Keep_move,receiveMessage.dice3Keep_move,receiveMessage.dice4Keep_move,receiveMessage.dice5Keep_move));

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

                Intent intent = new Intent(VsUserInGame.this, ChatingPage.class);
                intent.putExtra("loginUserNickName",loginUserNickName);
                startActivity(intent);
            }
        });

        vsGetScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG,"점수 클릭되나?");
                Log.e(TAG,"나의 status : " + receiveMessage.myStatus);
                Log.e(TAG,"나의 turn : " + receiveMessage.userTurn);

                Intent intent = new Intent(VsUserInGame.this, VsScoreActivity.class);
                intent.putExtra("status",receiveMessage.myStatus);
                intent.putExtra("userTurn",receiveMessage.userTurn);
                intent.putExtra("dice1eye",receiveMessage.dice1eye);
                intent.putExtra("dice2eye",receiveMessage.dice2eye);
                intent.putExtra("dice3eye",receiveMessage.dice3eye);
                intent.putExtra("dice4eye",receiveMessage.dice4eye);
                intent.putExtra("dice5eye",receiveMessage.dice5eye);
                intent.putExtra("player1Name",receiveMessage.player1Name);
                intent.putExtra("player2Name",receiveMessage.player2Name);
                getResult.launch(intent);
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

        // player2 의 view 좌표 구하기
        vsP2ViewTop = vsPlayer2View.getTop();
        vsP2ViewBottom = vsPlayer2View.getBottom();
        vsP2ViewLeft = vsPlayer2View.getLeft();
        vsP2ViewRight = vsPlayer2View.getRight();

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

                    receiveMessage = new ReceiveMessage(rollDice,vs_dice1,vs_dice2,vs_dice3,vs_dice4,vs_dice5
                            ,dice1Keep_move,dice2Keep_move,dice3Keep_move,dice4Keep_move,dice5Keep_move,vsRolldice_1
                            ,vsRolldice_2,vsRolldice_3,vsRolldice_4,vsRolldice_5,vs_rolldice_1xml,vs_rolldice_2xml
                            ,vs_rolldice_3xml,vs_rolldice_4xml,vs_rolldice_5xml,vs_dice_1,vs_dice_2,vs_dice_3,vs_dice_4
                            ,vs_dice_5,vs_dice_6,vsP1ViewTop,vsP1ViewBottom,vsP1ViewLeft,vsP1ViewRight,diceSize,userTurn
                            ,vsP1KeepDice1,vsP1KeepDice2,vsP1KeepDice3,vsP1KeepDice4,vsP1KeepDice5,user1,user2,vsP2ViewTop
                            ,vsP2KeepDice1,vsP2KeepDice2,vsP2KeepDice3,vsP2KeepDice4,vsP2KeepDice5,vs_roll,vs_rollTurn
                            ,sharedPreferences,sharedPreferences2,activity,playerTurn,diceBox,gss,loginUserNickName
                            ,player1totalScore,player2totalScore,vsPlayer1View,vsPlayer2View, timerData);

                    receiveMessage.receiveMsg(gss.gameSock);
                    gss.sendMessage(useJson.startUser(loginUserNickName));
                    Thread.sleep(500);

                    Handler handler = new Handler(Looper.getMainLooper());

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (receiveMessage.myStatus.equals("player1")) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(VsUserInGame.this);
                                builder.setTitle("상대방을 기다려주세요.");
                                builder.show();
                            }else if (receiveMessage.myStatus.equals("player2")){
                                AlertDialog.Builder builder = new AlertDialog.Builder(VsUserInGame.this);
                                builder.setTitle("선을 정하겠습니다.");
                                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                                gss.sendMessage(useJson.userRPS("가위바위보",receiveMessage.player1Name,receiveMessage.player2Name));
                                            }
                                        }).setCancelable(false);

                                builder.show();
                            }
                        }
                    });
                    Thread.sleep(500);


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
                out = new PrintWriter(new OutputStreamWriter(gss.gameSock.getOutputStream()));
                out.println(s);
                out.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}