package com.example.yachtdicev2.gameLogic;

import static android.graphics.Color.GRAY;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.media.audiofx.AudioEffect;
import android.media.audiofx.LoudnessEnhancer;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.yachtdicev2.activity.VsUserInGame;
import com.example.yachtdicev2.service.MyGameServerService;
import com.example.yachtdicev2.useJson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ReceiveMessage {


    BufferedReader in = null;
    PrintWriter out = null;
    String msg;
    String TAG = "ReceiveMsg";
    JSONObject jsonObject;
    int dice1,dice2,dice3,dice4,dice5;
    int vsP1ViewTop,vsP1ViewBottom,vsP1ViewLeft,vsP1ViewRight,diceSize;
    int vsP2ViewTop;
    public int turnTimer = 30;
    ImageView vs_dice1,vs_dice2,vs_dice3,vs_dice4,vs_dice5;
    ImageView vsP1KeepDice1,vsP1KeepDice2,vsP1KeepDice3,vsP1KeepDice4,vsP1KeepDice5;
    ImageView vsP2KeepDice1,vsP2KeepDice2,vsP2KeepDice3,vsP2KeepDice4,vsP2KeepDice5;
    ImageView playerTurn,diceBox;
    TextView user1,user2;
    TextView vsPlayer1View,vsPlayer2View;
    TextView player1imoji,player2imoji;
    RollDice rollDice;
    AnimatorSet vsAnimatorSet;
    AnimationDrawable vsRolldice_1,vsRolldice_2,vsRolldice_3,vsRolldice_4,vsRolldice_5;
    Drawable vs_dice_1,vs_dice_2,vs_dice_3,vs_dice_4,vs_dice_5,vs_dice_6;
    Drawable vs_rolldice_1xml,vs_rolldice_2xml,vs_rolldice_3xml,vs_rolldice_4xml,vs_rolldice_5xml;
    public boolean dice1Keep_move,dice2Keep_move,dice3Keep_move,dice4Keep_move,dice5Keep_move;
    public boolean userTurn;
    public int dice1eye,dice2eye,dice3eye,dice4eye,dice5eye;
    public int vs_roll;
    public boolean vs_rollTurn;
    String diceSum;
    public String myStatus;
    public SharedPreferences sharedPreferences,sharedPreferences2;
    public VsUserInGame vsUserInGame;
    public String player1Name,player2Name;
    String loginUserNickName;
    String my_RPS,opponent_RPS,opponent;
    MyGameServerService gss;
    useJson useJson = new useJson();
    public int player1totalScore,player2totalScore;
    private TimerData timerData;

    public ReceiveMessage(RollDice rollDice, ImageView vs_dice1, ImageView vs_dice2, ImageView vs_dice3, ImageView vs_dice4
    , ImageView vs_dice5, boolean dice1Keep_move, boolean dice2Keep_move, boolean dice3Keep_move, boolean dice4Keep_move
    , boolean dice5Keep_move, AnimationDrawable vsRolldice_1, AnimationDrawable vsRolldice_2, AnimationDrawable vsRolldice_3
    , AnimationDrawable vsRolldice_4, AnimationDrawable vsRolldice_5, Drawable vs_rolldice_1xml, Drawable vs_rolldice_2xml
    , Drawable vs_rolldice_3xml, Drawable vs_rolldice_4xml, Drawable vs_rolldice_5xml, Drawable vs_dice_1
    , Drawable vs_dice_2, Drawable vs_dice_3, Drawable vs_dice_4, Drawable vs_dice_5, Drawable vs_dice_6, int vsP1ViewTop
    , int vsP1ViewBottom, int vsP1ViewLeft, int vsP1ViewRight, int diceSize, boolean userTurn, ImageView vsP1KeepDice1
    , ImageView vsP1KeepDice2, ImageView vsP1KeepDice3, ImageView vsP1KeepDice4, ImageView vsP1KeepDice5, TextView user1
    , TextView user2, int vsP2ViewTop, ImageView vsP2KeepDice1, ImageView vsP2KeepDice2, ImageView vsP2KeepDice3
    , ImageView vsP2KeepDice4, ImageView vsP2KeepDice5, int vs_roll, boolean vs_rollTurn, SharedPreferences sharedPreferences
    , SharedPreferences sharedPreferences2, Activity activity, ImageView playerTurn, ImageView diceBox, MyGameServerService gss
    , String loginUserNickName,int player1totalScore,int player2totalScore, TextView vsPlayer1View, TextView vsPlayer2View
    , TimerData timerData, TextView player1imoji, TextView player2imoji){

        this.rollDice = rollDice;
        this.vs_dice1 = vs_dice1;
        this.vs_dice2 = vs_dice2;
        this.vs_dice3 = vs_dice3;
        this.vs_dice4 = vs_dice4;
        this.vs_dice5 = vs_dice5;
        this.dice1Keep_move = dice1Keep_move;
        this.dice2Keep_move = dice2Keep_move;
        this.dice3Keep_move = dice3Keep_move;
        this.dice4Keep_move = dice4Keep_move;
        this.dice5Keep_move = dice5Keep_move;
        this.vsRolldice_1 = vsRolldice_1;
        this.vsRolldice_2 = vsRolldice_2;
        this.vsRolldice_3 = vsRolldice_3;
        this.vsRolldice_4 = vsRolldice_4;
        this.vsRolldice_5 = vsRolldice_5;
        this.vs_rolldice_1xml = vs_rolldice_1xml;
        this.vs_rolldice_2xml = vs_rolldice_2xml;
        this.vs_rolldice_3xml = vs_rolldice_3xml;
        this.vs_rolldice_4xml = vs_rolldice_4xml;
        this.vs_rolldice_5xml = vs_rolldice_5xml;
        this.vs_dice_1 = vs_dice_1;
        this.vs_dice_2 = vs_dice_2;
        this.vs_dice_3 = vs_dice_3;
        this.vs_dice_4 = vs_dice_4;
        this.vs_dice_5 = vs_dice_5;
        this.vs_dice_6 = vs_dice_6;
        this.vsP1ViewTop = vsP1ViewTop;
        this.vsP1ViewLeft = vsP1ViewLeft;
        this.vsP1ViewRight = vsP1ViewRight;
        this.vsP1ViewBottom = vsP1ViewBottom;
        this.diceSize = diceSize;
        this.userTurn = userTurn;
        this.vsP1KeepDice1 = vsP1KeepDice1;
        this.vsP1KeepDice2 = vsP1KeepDice2;
        this.vsP1KeepDice3 = vsP1KeepDice3;
        this.vsP1KeepDice4 = vsP1KeepDice4;
        this.vsP1KeepDice5 = vsP1KeepDice5;
        this.user1 = user1;
        this.user2 = user2;
        this.vsP2ViewTop = vsP2ViewTop;
        this.vsP2KeepDice1 = vsP2KeepDice1;
        this.vsP2KeepDice2 = vsP2KeepDice2;
        this.vsP2KeepDice3 = vsP2KeepDice3;
        this.vsP2KeepDice4 = vsP2KeepDice4;
        this.vsP2KeepDice5 = vsP2KeepDice5;
        this.vs_roll = vs_roll;
        this.vs_rollTurn = vs_rollTurn;
        this.sharedPreferences = sharedPreferences;
        this.sharedPreferences2 = sharedPreferences2;
        this.vsUserInGame = (VsUserInGame) activity;
        this.playerTurn = playerTurn;
        this.diceBox = diceBox;
        this.gss = gss;
        this.loginUserNickName = loginUserNickName;
        this.player1totalScore = player1totalScore;
        this.player2totalScore = player2totalScore;
        this.vsPlayer1View = vsPlayer1View;
        this.vsPlayer2View = vsPlayer2View;
        this.timerData = timerData;
        this.player1imoji = player1imoji;
        this.player2imoji = player2imoji;
    }

    public void receiveMsg(Socket gameSock){
            Log.e(TAG,"receiveMsg 실행됨?");

        new Thread(() -> {
            Handler handler = new Handler(Looper.getMainLooper());
            Log.e(TAG,"핸들러되냐?");
            try{
                while (true){
                    in = new BufferedReader(new InputStreamReader(gameSock.getInputStream()));
                    msg = in.readLine();
                    jsonObject = new JSONObject(msg);
                    Log.e(TAG,"받은 메세지 : " + jsonObject.toString());
                    String receiveName = (String) jsonObject.optString("clickName","값이없음");

                    // 처음에 유저의 턴 배분
                    if (receiveName.equals("대기")){
                        userTurn = false;
                        myStatus = "player1";

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                user1.setText((String) jsonObject.optString("player1","대기중"));
                                user2.setText((String) jsonObject.optString("player2","대기중"));

                                player1Name = (String) jsonObject.optString("player1","대기중");
                                player2Name = (String) jsonObject.optString("player2","대기중");
                            }
                        });
                    }else if (receiveName.equals("입장완료")){

                        userTurn = false;
                        myStatus = "player2";

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                user1.setText((String) jsonObject.optString("player2","대기중"));
                                user2.setText((String) jsonObject.optString("player1","대기중"));

                                player1Name = (String) jsonObject.optString("player1","대기중");
                                player2Name = (String) jsonObject.optString("player2","대기중");
                            }
                        });
                    }

                    if (receiveName.equals("RPSstart")){
                        Log.e(TAG,"가위바위보 대결 시작");

                        handler.post(new Runnable() {
                            @Override
                            public void run() {

                                AlertDialog.Builder builder = new AlertDialog.Builder(vsUserInGame);
                                builder.setTitle("가위바위보로 선을 정합니다.");

                                builder.setPositiveButton("보", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        my_RPS = "보";
                                        gss.sendMessage(useJson.useRPS("보",loginUserNickName));
                                        dialog.dismiss();
                                    }
                                }).setCancelable(false);

                                builder.setNeutralButton("바위", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        my_RPS = "바위";
                                        gss.sendMessage(useJson.useRPS("바위",loginUserNickName));
                                        dialog.dismiss();
                                    }
                                }).setCancelable(false);

                                builder.setNegativeButton("가위", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        my_RPS = "가위";
                                        gss.sendMessage(useJson.useRPS("가위",loginUserNickName));
                                        dialog.dismiss();
                                    }
                                }).setCancelable(false);
                                builder.show();
                            }
                        });
                    }

                    if (receiveName.equals("가위바위보대결")){

                        if (my_RPS != null){

                            opponent = jsonObject.getString("userName");

                            if (!loginUserNickName.equals(opponent)){

                                Log.e(TAG,"승패계산 들어왓나?");

                                opponent_RPS = jsonObject.getString("RPS");

                                if (my_RPS.equals("바위") && opponent_RPS.equals("가위") ||
                                        my_RPS.equals("가위") && opponent_RPS.equals("보") ||
                                        my_RPS.equals("보") && opponent_RPS.equals("바위")){

                                    handler.post(new Runnable() {
                                        @Override
                                        public void run() {

                                            userTurn = true;
                                            AlertDialog.Builder builder = new AlertDialog.Builder(vsUserInGame);
                                            builder.setTitle("당신은 이겼습니다. 선 입니다.");
                                            builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {

                                                    gss.sendMessage(useJson.youWin(loginUserNickName));
                                                    dialog.dismiss();

                                                    turnTimerStart();
                                                }
                                            }).setCancelable(false);
                                            builder.show();

                                            playerTurn.setVisibility(View.VISIBLE);

                                            vsAnimatorSet = new AnimatorSet();

                                            ObjectAnimator animaX_turn = ObjectAnimator.ofFloat(playerTurn, "translationX", user1.getLeft());
                                            ObjectAnimator animaY_turn = ObjectAnimator.ofFloat(playerTurn, "translationY", user1.getTop());
                                            animaX_turn.setDuration(1000);
                                            animaY_turn.setDuration(1000);

                                            vsAnimatorSet.play(animaX_turn);
                                            vsAnimatorSet.play(animaY_turn);

                                            vsAnimatorSet.start();
                                        }
                                    });
//                                    turnTimerStart();
                                }

                                if (my_RPS.equals(opponent_RPS)){

                                    handler.post(new Runnable() {
                                        @Override
                                        public void run() {

                                            AlertDialog.Builder builder = new AlertDialog.Builder(vsUserInGame);
                                            builder.setTitle("당신은 비겼습니다. 재경기를 해주십시오.");
                                            builder.setMessage("당신은 : " + my_RPS + ", 상대는 : " + opponent_RPS);
                                            builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {

                                                    gss.sendMessage(useJson.youTie(loginUserNickName));
                                                    dialog.dismiss();
                                                }
                                            }).setCancelable(false);
                                            builder.show();

                                            my_RPS = null;
                                            opponent_RPS = null;
                                        }
                                    });
                                }

                                if (my_RPS.equals("바위") && opponent_RPS.equals("보") ||
                                        my_RPS.equals("가위") && opponent_RPS.equals("바위") ||
                                        my_RPS.equals("보") && opponent_RPS.equals("가위")){

                                    handler.post(new Runnable() {
                                        @Override
                                        public void run() {

                                            userTurn = false;
                                            AlertDialog.Builder builder = new AlertDialog.Builder(vsUserInGame);
                                            builder.setTitle("당신은 졌습니다. 후 입니다.");
                                            builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {

                                                    gss.sendMessage(useJson.youLose(loginUserNickName));
                                                    dialog.dismiss();
                                                }
                                            }).setCancelable(false);
                                            builder.show();

                                            playerTurn.setVisibility(View.VISIBLE);

                                            vsAnimatorSet = new AnimatorSet();

                                            ObjectAnimator animaX_turn = ObjectAnimator.ofFloat(playerTurn, "translationX", user2.getLeft());
                                            ObjectAnimator animaY_turn = ObjectAnimator.ofFloat(playerTurn, "translationY", user2.getTop());
                                            animaX_turn.setDuration(1000);
                                            animaY_turn.setDuration(1000);

                                            vsAnimatorSet.play(animaX_turn);
                                            vsAnimatorSet.play(animaY_turn);

                                            vsAnimatorSet.start();
                                        }
                                    });
                                }
                            }
                        }
                    }

                    if (receiveName.equals("가위바위보승패")){

                        String RPSres = jsonObject.getString("result");
                        String resUser = jsonObject.getString("userName");

                        if (RPSres.equals("tie")){

                            handler.post(new Runnable() {
                                @Override
                                public void run() {

                                    AlertDialog.Builder builder = new AlertDialog.Builder(vsUserInGame);
                                    builder.setTitle("재경기를 진행합니다.");

                                    my_RPS = null;
                                    opponent_RPS = null;

                                    builder.setPositiveButton("보", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            my_RPS = "보";
                                            gss.sendMessage(useJson.useRPS("보",loginUserNickName));
                                            dialog.dismiss();
                                        }
                                    }).setCancelable(false);

                                    builder.setNeutralButton("바위", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            my_RPS = "바위";
                                            gss.sendMessage(useJson.useRPS("바위",loginUserNickName));
                                            dialog.dismiss();
                                        }
                                    }).setCancelable(false);

                                    builder.setNegativeButton("가위", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            my_RPS = "가위";
                                            gss.sendMessage(useJson.useRPS("가위",loginUserNickName));
                                            dialog.dismiss();
                                        }
                                    }).setCancelable(false);
                                    builder.show();
                                }
                            });

                        }else if (RPSres.equals("win")){

                            if (!resUser.equals(loginUserNickName)){

                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {

                                        userTurn = false;
                                        AlertDialog.Builder builder = new AlertDialog.Builder(vsUserInGame);
                                        builder.setTitle("당신은 졌습니다. 후 입니다.");
                                        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                                dialog.dismiss();
                                            }
                                        }).setCancelable(false);
                                        builder.show();

                                        playerTurn.setVisibility(View.VISIBLE);

                                        vsAnimatorSet = new AnimatorSet();

                                        ObjectAnimator animaX_turn = ObjectAnimator.ofFloat(playerTurn, "translationX", user2.getLeft());
                                        ObjectAnimator animaY_turn = ObjectAnimator.ofFloat(playerTurn, "translationY", user2.getTop());
                                        animaX_turn.setDuration(1000);
                                        animaY_turn.setDuration(1000);

                                        vsAnimatorSet.play(animaX_turn);
                                        vsAnimatorSet.play(animaY_turn);

                                        vsAnimatorSet.start();
                                    }
                                });
                            }

                        }else if (RPSres.equals("lose")){

                            if (!resUser.equals(loginUserNickName)){

                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {

                                        userTurn = true;
                                        AlertDialog.Builder builder = new AlertDialog.Builder(vsUserInGame);
                                        builder.setTitle("당신은 이겼습니다. 선 입니다.");
                                        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                                dialog.dismiss();

                                                turnTimerStart();
                                            }
                                        }).setCancelable(false);
                                        builder.show();

                                        playerTurn.setVisibility(View.VISIBLE);

                                        vsAnimatorSet = new AnimatorSet();

                                        ObjectAnimator animaX_turn = ObjectAnimator.ofFloat(playerTurn, "translationX", user1.getLeft());
                                        ObjectAnimator animaY_turn = ObjectAnimator.ofFloat(playerTurn, "translationY", user1.getTop());
                                        animaX_turn.setDuration(1000);
                                        animaY_turn.setDuration(1000);

                                        vsAnimatorSet.play(animaX_turn);
                                        vsAnimatorSet.play(animaY_turn);

                                        vsAnimatorSet.start();
                                    }
                                });
                            }

                        }
                    }

                    // 주사위 굴리기
                    if(receiveName.equals("diceRollClick")){
                        dice1 = (Integer) jsonObject.get("dice1");
                        dice2 = (Integer) jsonObject.get("dice2");
                        dice3 = (Integer) jsonObject.get("dice3");
                        dice4 = (Integer) jsonObject.get("dice4");
                        dice5 = (Integer) jsonObject.get("dice5");

                        Log.e(TAG,"dice : " + dice1 + dice2 + dice3 + dice4 + dice5);

                        // 주사위 좌표구하기
                        rollDice.rollDice(vsP1ViewTop,vsP1ViewLeft,vsP1ViewBottom,vsP1ViewRight,diceSize,vsP2ViewTop,userTurn);


                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                diceAnimation();
                            }
                        });
                        Thread.sleep(500);
                    }

                    if (receiveName.equals("DiceKeepClick")){

                        String diceClick = (String) jsonObject.get("diceClick");

                        Log.e(TAG,"각 boolean 값 : " + dice1Keep_move + " | " + dice2Keep_move + " | " + dice3Keep_move
                                + " | " + dice4Keep_move + " | " + dice5Keep_move);

                        if (diceClick.equals("dice1")){
                            dice1Keep_move = (boolean) jsonObject.get("dice1Keep");
                            if (jsonObject.get("dice1Keep").equals(true)
                                    || jsonObject.get("dice1Keep").equals(false)){
                                Log.e(TAG,"dice1 의 상태가 true 이거나 false 일때 실행");

                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        dice1KeepAnimation();
                                    }
                                });
                                Thread.sleep(500);
                            }
                        }

                        if (diceClick.equals("dice2")){
                            dice2Keep_move = (boolean) jsonObject.get("dice2Keep");
                            if (jsonObject.get("dice2Keep").equals(true)
                                    || jsonObject.get("dice2Keep").equals(false)){

                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        dice2KeepAnimation();
                                    }
                                });
                                Thread.sleep(500);

                            }
                        }

                        if (diceClick.equals("dice3")){
                            dice3Keep_move = (boolean) jsonObject.get("dice3Keep");
                            if (jsonObject.get("dice3Keep").equals(true)
                                    || jsonObject.get("dice3Keep").equals(false)){

                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        dice3KeepAnimation();
                                    }
                                });
                                Thread.sleep(500);

                            }
                        }

                        if (diceClick.equals("dice4")){
                            dice4Keep_move = (boolean) jsonObject.get("dice4Keep");
                            if (jsonObject.get("dice4Keep").equals(true)
                                    || jsonObject.get("dice4Keep").equals(false)){

                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        dice4KeepAnimation();
                                    }
                                });
                                Thread.sleep(500);

                            }
                        }

                        if (diceClick.equals("dice5")){
                            dice5Keep_move = (boolean) jsonObject.get("dice5Keep");
                            if (jsonObject.get("dice5Keep").equals(true)
                                    || jsonObject.get("dice5Keep").equals(false)){

                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        dice5KeepAnimation();
                                    }
                                });
                                Thread.sleep(500);
                            }
                        }
                    }

                    if (receiveName.equals("ScoreClick")){

                        Log.e(TAG,"받은 점수 추가");

                        String player = (String) jsonObject.get("player");
                        String scoreName = (String) jsonObject.get("scoreName");
                        int score = Integer.parseInt(String.valueOf(jsonObject.get("score")));

                        if (scoreName.contains("P1")){
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString(scoreName,String.valueOf(score));
                            editor.commit();
                            Log.e(TAG,"P1 점수 추가됨");
                        }
                        if (scoreName.contains("P2")){
                            SharedPreferences.Editor editor = sharedPreferences2.edit();
                            editor.putString(scoreName,String.valueOf(score));
                            editor.commit();
                            Log.e(TAG,"P2 점수 추가됨");
                        }

                        if (myStatus.equals(player)){
                            userTurn = false;
                            dice1Keep_move = true;
                            dice2Keep_move = true;
                            dice3Keep_move = true;
                            dice4Keep_move = true;
                            dice5Keep_move = true;

                            handler.post(new Runnable() {
                                @Override
                                public void run() {

                                    vsAnimatorSet = new AnimatorSet();

                                    ObjectAnimator animaX_turn = ObjectAnimator.ofFloat(playerTurn, "translationX", user2.getLeft());
                                    ObjectAnimator animaY_turn = ObjectAnimator.ofFloat(playerTurn, "translationY", user2.getTop());
                                    animaX_turn.setDuration(1000);
                                    animaY_turn.setDuration(1000);

                                    vsAnimatorSet.play(animaX_turn);
                                    vsAnimatorSet.play(animaY_turn);

                                    vsAnimatorSet.start();

                                    diceReset();

                                }
                            });
                        }else {
                            userTurn = true;
                            vs_roll = 0;
                            vs_rollTurn = false;
                            dice1eye = 0;
                            dice2eye = 0;
                            dice3eye = 0;
                            dice4eye = 0;
                            dice5eye = 0;
                            dice1Keep_move = true;
                            dice2Keep_move = true;
                            dice3Keep_move = true;
                            dice4Keep_move = true;
                            dice5Keep_move = true;

                            Log.e(TAG,"gameEnd() : " + gameEnd());

                            if (userTurn && gameEnd() == false){

                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(vsUserInGame);
                                        builder.setTitle("상대가 " + scoreName + "에 점수를 넣었습니다.")
                                                .setMessage("당신의 턴입니다.");
                                        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        dialog.dismiss();

                                                        turnTimerStart();
                                                    }
                                                }).setCancelable(false);
                                        builder.show();

                                        vsAnimatorSet = new AnimatorSet();

                                        ObjectAnimator animaX_turn = ObjectAnimator.ofFloat(playerTurn, "translationX", user1.getLeft());
                                        ObjectAnimator animaY_turn = ObjectAnimator.ofFloat(playerTurn, "translationY", user1.getTop());
                                        animaX_turn.setDuration(1000);
                                        animaY_turn.setDuration(1000);

                                        vsAnimatorSet.play(animaX_turn);
                                        vsAnimatorSet.play(animaY_turn);

                                        vsAnimatorSet.start();

                                        diceReset();
                                    }
                                });
//                                turnTimerStart();

                                Thread.sleep(500);
                            }else if (gameEnd() == true){

                                Log.e(TAG,"게임 끝남");
                                gss.sendMessage(useJson.gameEnd(player1totalScore,player2totalScore));

                            }
                        }
                    }

                    if(receiveName.equals("imoji")){

                        String imojiUserName = jsonObject.getString("userName");
                        int unicode = jsonObject.getInt("unicode");

                        handler.post(new Runnable() {
                            @Override
                            public void run() {

                                Log.e(TAG,"이모지 나타내기");

                                if(loginUserNickName.equals(imojiUserName)){
                                    Log.e(TAG,"player1 이모지 확인");

                                    player1imoji.setVisibility(View.VISIBLE);
                                    player1imoji.setText(new String(Character.toChars(unicode)));
                                    player1imoji.bringToFront();

                                }else {
                                    Log.e(TAG,"player2 이모지 확인");

                                    player2imoji.setVisibility(View.VISIBLE);
                                    player2imoji.setText(new String(Character.toChars(unicode)));
                                    player2imoji.bringToFront();

                                }
                            }
                        });

                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                player1imoji.setVisibility(View.GONE);
                                player2imoji.setVisibility(View.GONE);

                            }
                        },2000);

                    }

                    if (receiveName.equals("End")){
                        String gameResult;
                        String resultMsg;

                        player1totalScore = jsonObject.getInt("player1totalScore");
                        player2totalScore = jsonObject.getInt("player2totalScore");

                        if (player1totalScore > player2totalScore){

                            gameResult = player1Name + " 승리!";
                            resultMsg = player1Name + " 점수 : " + String.valueOf(player1totalScore) + " " + player2Name + " 점수 : " + String.valueOf(player2totalScore);

                            handler.post(new Runnable() {
                                @Override
                                public void run() {

                                    AlertDialog.Builder builder = new AlertDialog.Builder(vsUserInGame);
                                    builder.setTitle(gameResult);
                                    builder.setMessage(resultMsg);
                                    builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            dialog.dismiss();
                                            vsUserInGame.finish();
                                        }
                                    }).setCancelable(false);

                                    builder.show();
                                }
                            });

                        }else if (player1totalScore == player2totalScore){

                            gameResult = "비겼습니다.";
                            resultMsg = player1Name + " 점수 : " + String.valueOf(player1totalScore) + " " + player2Name + " 점수 : " + String.valueOf(player2totalScore);

                            handler.post(new Runnable() {
                                @Override
                                public void run() {

                                    AlertDialog.Builder builder = new AlertDialog.Builder(vsUserInGame);
                                    builder.setTitle(gameResult);
                                    builder.setMessage(resultMsg);
                                    builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            dialog.dismiss();
                                            vsUserInGame.finish();
                                        }
                                    }).setCancelable(false);

                                    builder.show();
                                }
                            });

                        }else if (player2totalScore > player1totalScore){

                            gameResult = player2Name + " 승리!";
                            resultMsg = player1Name + " 점수 : " + String.valueOf(player1totalScore) + " " + player2Name + " 점수 : " + String.valueOf(player2totalScore);

                            handler.post(new Runnable() {
                                @Override
                                public void run() {

                                    AlertDialog.Builder builder = new AlertDialog.Builder(vsUserInGame);
                                    builder.setTitle(gameResult);
                                    builder.setMessage(resultMsg);
                                    builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            dialog.dismiss();
                                            vsUserInGame.finish();
                                        }
                                    }).setCancelable(false);

                                    builder.show();
                                }
                            });

                        }
                    }


                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    public void diceAnimation(){
        Log.e(TAG,"주사위 애니메이션 들어감?");
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

        if (dice1Keep_move) {
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

        if (dice2Keep_move) {
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

        if (dice3Keep_move) {
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

        if (dice4Keep_move) {
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

        if (dice5Keep_move) {
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

                if (dice1Keep_move) {
                    switch (dice1) {
                        case 1:
                            vs_dice1.setImageDrawable(vs_dice_1);
                            dice1eye = 1;
                            break;
                        case 2:
                            vs_dice1.setImageDrawable(vs_dice_2);
                            dice1eye = 2;
                            break;
                        case 3:
                            vs_dice1.setImageDrawable(vs_dice_3);
                            dice1eye = 3;
                            break;
                        case 4:
                            vs_dice1.setImageDrawable(vs_dice_4);
                            dice1eye = 4;
                            break;
                        case 5:
                            vs_dice1.setImageDrawable(vs_dice_5);
                            dice1eye = 5;
                            break;
                        case 6:
                            vs_dice1.setImageDrawable(vs_dice_6);
                            dice1eye = 6;
                            break;
                    }
                }

                if (dice2Keep_move) {
                    switch (dice2) {
                        case 1:
                            vs_dice2.setImageDrawable(vs_dice_1);
                            dice2eye = 1;
                            break;
                        case 2:
                            vs_dice2.setImageDrawable(vs_dice_2);
                            dice2eye = 2;
                            break;
                        case 3:
                            vs_dice2.setImageDrawable(vs_dice_3);
                            dice2eye = 3;
                            break;
                        case 4:
                            vs_dice2.setImageDrawable(vs_dice_4);
                            dice2eye = 4;
                            break;
                        case 5:
                            vs_dice2.setImageDrawable(vs_dice_5);
                            dice2eye = 5;
                            break;
                        case 6:
                            vs_dice2.setImageDrawable(vs_dice_6);
                            dice2eye = 6;
                            break;
                    }
                }

                if (dice3Keep_move) {
                    switch (dice3) {
                        case 1:
                            vs_dice3.setImageDrawable(vs_dice_1);
                            dice3eye = 1;
                            break;
                        case 2:
                            vs_dice3.setImageDrawable(vs_dice_2);
                            dice3eye = 2;
                            break;
                        case 3:
                            vs_dice3.setImageDrawable(vs_dice_3);
                            dice3eye = 3;
                            break;
                        case 4:
                            vs_dice3.setImageDrawable(vs_dice_4);
                            dice3eye = 4;
                            break;
                        case 5:
                            vs_dice3.setImageDrawable(vs_dice_5);
                            dice3eye = 5;
                            break;
                        case 6:
                            vs_dice3.setImageDrawable(vs_dice_6);
                            dice3eye = 6;
                            break;
                    }
                }

                if (dice4Keep_move) {
                    switch (dice4) {
                        case 1:
                            vs_dice4.setImageDrawable(vs_dice_1);
                            dice4eye = 1;
                            break;
                        case 2:
                            vs_dice4.setImageDrawable(vs_dice_2);
                            dice4eye = 2;
                            break;
                        case 3:
                            vs_dice4.setImageDrawable(vs_dice_3);
                            dice4eye = 3;
                            break;
                        case 4:
                            vs_dice4.setImageDrawable(vs_dice_4);
                            dice4eye = 4;
                            break;
                        case 5:
                            vs_dice4.setImageDrawable(vs_dice_5);
                            dice4eye = 5;
                            break;
                        case 6:
                            vs_dice4.setImageDrawable(vs_dice_6);
                            dice4eye = 6;
                            break;
                    }
                }

                if (dice5Keep_move) {
                    switch (dice5) {
                        case 1:
                            vs_dice5.setImageDrawable(vs_dice_1);
                            dice5eye = 1;
                            break;
                        case 2:
                            vs_dice5.setImageDrawable(vs_dice_2);
                            dice5eye = 2;
                            break;
                        case 3:
                            vs_dice5.setImageDrawable(vs_dice_3);
                            dice5eye = 3;
                            break;
                        case 4:
                            vs_dice5.setImageDrawable(vs_dice_4);
                            dice5eye = 4;
                            break;
                        case 5:
                            vs_dice5.setImageDrawable(vs_dice_5);
                            dice5eye = 5;
                            break;
                        case 6:
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

    public void dice1KeepAnimation(){

        Log.e(TAG,"dice1 킵되나요?");
        Log.e(TAG,"Keep status : " + dice1Keep_move);
        Log.e(TAG,"KeepDice1 Left : " + vsP1KeepDice1.getLeft());
        Log.e(TAG,"KeepDice1 Top : " + vsP1KeepDice1.getTop());

        vsAnimatorSet = new AnimatorSet();

        if(!dice1Keep_move){

            ObjectAnimator animaX_dice;
            ObjectAnimator animaY_dice;

            if (!userTurn){
                animaX_dice = ObjectAnimator.ofFloat(vs_dice1,"translationX",vsP2KeepDice1.getLeft());
                animaY_dice = ObjectAnimator.ofFloat(vs_dice1,"translationY",vsP2KeepDice1.getTop());
                animaX_dice.setDuration(600);
                animaY_dice.setDuration(600);
            }else{
                Log.e(TAG,"dice1 상태가 true일 때 ");
                animaX_dice = ObjectAnimator.ofFloat(vs_dice1,"translationX",vsP1KeepDice1.getLeft());
                animaY_dice = ObjectAnimator.ofFloat(vs_dice1,"translationY",vsP1KeepDice1.getTop());
                animaX_dice.setDuration(600);
                animaY_dice.setDuration(600);
            }

            vsAnimatorSet.play(animaX_dice);
            vsAnimatorSet.play(animaY_dice);

            vsAnimatorSet.start();

        }else if(dice1Keep_move){

            Log.e(TAG,"dice1 상태가 false일 때 ");
            rollDice.rollDice1(vsP1ViewTop,vsP1ViewLeft,vsP1ViewBottom,vsP1ViewRight,diceSize,vsP2ViewTop,userTurn);

            ObjectAnimator animaX_dice = ObjectAnimator.ofFloat(vs_dice1,"translationX",rollDice.dice1Left);
            ObjectAnimator animaY_dice = ObjectAnimator.ofFloat(vs_dice1,"translationY",rollDice.dice1Top);
            animaX_dice.setDuration(600);
            animaY_dice.setDuration(600);

            vsAnimatorSet.play(animaX_dice);
            vsAnimatorSet.play(animaY_dice);

            vsAnimatorSet.start();

        }
    }

    public void dice2KeepAnimation() {

        vsAnimatorSet = new AnimatorSet();

        if (!dice2Keep_move) {

            ObjectAnimator animaX_dice;
            ObjectAnimator animaY_dice;

            if (!userTurn) {
                animaX_dice = ObjectAnimator.ofFloat(vs_dice2, "translationX", vsP2KeepDice2.getLeft());
                animaY_dice = ObjectAnimator.ofFloat(vs_dice2, "translationY", vsP2KeepDice2.getTop());
                animaX_dice.setDuration(600);
                animaY_dice.setDuration(600);
            }else {
                Log.e(TAG, "dice2 상태가 true일 때 ");
                animaX_dice = ObjectAnimator.ofFloat(vs_dice2, "translationX", vsP1KeepDice2.getLeft());
                animaY_dice = ObjectAnimator.ofFloat(vs_dice2, "translationY", vsP1KeepDice2.getTop());
                animaX_dice.setDuration(600);
                animaY_dice.setDuration(600);
            }

            vsAnimatorSet.play(animaX_dice);
            vsAnimatorSet.play(animaY_dice);

            vsAnimatorSet.start();

        } else if (dice2Keep_move) {

            Log.e(TAG, "dice2 상태가 false일 때 ");
            rollDice.rollDice2(vsP1ViewTop, vsP1ViewLeft, vsP1ViewBottom, vsP1ViewRight, diceSize, vsP2ViewTop, userTurn);

            ObjectAnimator animaX_dice = ObjectAnimator.ofFloat(vs_dice2, "translationX", rollDice.dice2Left);
            ObjectAnimator animaY_dice = ObjectAnimator.ofFloat(vs_dice2, "translationY", rollDice.dice2Top);
            animaX_dice.setDuration(600);
            animaY_dice.setDuration(600);

            vsAnimatorSet.play(animaX_dice);
            vsAnimatorSet.play(animaY_dice);

            vsAnimatorSet.start();
        }
    }

    public void dice3KeepAnimation(){

        vsAnimatorSet = new AnimatorSet();

        if (!dice3Keep_move) {

            ObjectAnimator animaX_dice;
            ObjectAnimator animaY_dice;

            if (!userTurn) {
                Log.e(TAG, "dice3 상태가 true일 때 ");
                animaX_dice = ObjectAnimator.ofFloat(vs_dice3, "translationX", vsP2KeepDice3.getLeft());
                animaY_dice = ObjectAnimator.ofFloat(vs_dice3, "translationY", vsP2KeepDice3.getTop());
                animaX_dice.setDuration(600);
                animaY_dice.setDuration(600);
            }else {
                Log.e(TAG, "dice3 상태가 true일 때 ");
                animaX_dice = ObjectAnimator.ofFloat(vs_dice3, "translationX", vsP1KeepDice3.getLeft());
                animaY_dice = ObjectAnimator.ofFloat(vs_dice3, "translationY", vsP1KeepDice3.getTop());
                animaX_dice.setDuration(600);
                animaY_dice.setDuration(600);
            }

            vsAnimatorSet.play(animaX_dice);
            vsAnimatorSet.play(animaY_dice);

            vsAnimatorSet.start();

        } else if (dice3Keep_move) {

            Log.e(TAG, "dice3 상태가 false일 때 ");
            rollDice.rollDice3(vsP1ViewTop, vsP1ViewLeft, vsP1ViewBottom, vsP1ViewRight, diceSize, vsP2ViewTop, userTurn);

            ObjectAnimator animaX_dice = ObjectAnimator.ofFloat(vs_dice3, "translationX", rollDice.dice3Left);
            ObjectAnimator animaY_dice = ObjectAnimator.ofFloat(vs_dice3, "translationY", rollDice.dice3Top);
            animaX_dice.setDuration(600);
            animaY_dice.setDuration(600);

            vsAnimatorSet.play(animaX_dice);
            vsAnimatorSet.play(animaY_dice);

            vsAnimatorSet.start();
        }
    }

    public void dice4KeepAnimation(){

        vsAnimatorSet = new AnimatorSet();

        if (!dice4Keep_move) {

            ObjectAnimator animaX_dice;
            ObjectAnimator animaY_dice;

            if (!userTurn) {
                Log.e(TAG, "dice4 상태가 true일 때 ");
                animaX_dice = ObjectAnimator.ofFloat(vs_dice4, "translationX", vsP2KeepDice4.getLeft());
                animaY_dice = ObjectAnimator.ofFloat(vs_dice4, "translationY", vsP2KeepDice4.getTop());
                animaX_dice.setDuration(600);
                animaY_dice.setDuration(600);
            }else {
                Log.e(TAG, "dice4 상태가 true일 때 ");
                animaX_dice = ObjectAnimator.ofFloat(vs_dice4, "translationX", vsP1KeepDice4.getLeft());
                animaY_dice = ObjectAnimator.ofFloat(vs_dice4, "translationY", vsP1KeepDice4.getTop());
                animaX_dice.setDuration(600);
                animaY_dice.setDuration(600);
            }

            vsAnimatorSet.play(animaX_dice);
            vsAnimatorSet.play(animaY_dice);

            vsAnimatorSet.start();

        } else if (dice4Keep_move) {

            Log.e(TAG, "dice4 상태가 false일 때 ");
            rollDice.rollDice4(vsP1ViewTop, vsP1ViewLeft, vsP1ViewBottom, vsP1ViewRight, diceSize, vsP2ViewTop, userTurn);

            ObjectAnimator animaX_dice = ObjectAnimator.ofFloat(vs_dice4, "translationX", rollDice.dice4Left);
            ObjectAnimator animaY_dice = ObjectAnimator.ofFloat(vs_dice4, "translationY", rollDice.dice4Top);
            animaX_dice.setDuration(600);
            animaY_dice.setDuration(600);

            vsAnimatorSet.play(animaX_dice);
            vsAnimatorSet.play(animaY_dice);

            vsAnimatorSet.start();
        }
    }

    public void dice5KeepAnimation(){

        vsAnimatorSet = new AnimatorSet();

        if (!dice5Keep_move) {

            ObjectAnimator animaX_dice;
            ObjectAnimator animaY_dice;

            if (!userTurn) {
                Log.e(TAG, "dice5 상태가 true일 때 ");
                animaX_dice = ObjectAnimator.ofFloat(vs_dice5, "translationX", vsP2KeepDice5.getLeft());
                animaY_dice = ObjectAnimator.ofFloat(vs_dice5, "translationY", vsP2KeepDice5.getTop());
                animaX_dice.setDuration(600);
                animaY_dice.setDuration(600);
            }else{
                Log.e(TAG, "dice5 상태가 true일 때 ");
                animaX_dice = ObjectAnimator.ofFloat(vs_dice5, "translationX", vsP1KeepDice5.getLeft());
                animaY_dice = ObjectAnimator.ofFloat(vs_dice5, "translationY", vsP1KeepDice5.getTop());
                animaX_dice.setDuration(600);
                animaY_dice.setDuration(600);
            }

            vsAnimatorSet.play(animaX_dice);
            vsAnimatorSet.play(animaY_dice);

            vsAnimatorSet.start();

        } else if (dice5Keep_move) {

            Log.e(TAG, "dice5 상태가 false일 때 ");
            rollDice.rollDice5(vsP1ViewTop, vsP1ViewLeft, vsP1ViewBottom, vsP1ViewRight, diceSize, vsP2ViewTop, userTurn);

            ObjectAnimator animaX_dice = ObjectAnimator.ofFloat(vs_dice5, "translationX", rollDice.dice5Left);
            ObjectAnimator animaY_dice = ObjectAnimator.ofFloat(vs_dice5, "translationY", rollDice.dice5Top);
            animaX_dice.setDuration(600);
            animaY_dice.setDuration(600);

            vsAnimatorSet.play(animaX_dice);
            vsAnimatorSet.play(animaY_dice);

            vsAnimatorSet.start();
        }
    }

    public void diceReset(){

        vsAnimatorSet = new AnimatorSet();

        ObjectAnimator anima_dice1X = ObjectAnimator.ofFloat(vs_dice1, "translationX", diceBox.getLeft() + 50);
        ObjectAnimator anima_dice1Y = ObjectAnimator.ofFloat(vs_dice1, "translationY", diceBox.getTop() + 50);
        ObjectAnimator anima_dice2X = ObjectAnimator.ofFloat(vs_dice2, "translationX", diceBox.getLeft() + 50);
        ObjectAnimator anima_dice2Y = ObjectAnimator.ofFloat(vs_dice2, "translationY", diceBox.getTop() + 50);
        ObjectAnimator anima_dice3X = ObjectAnimator.ofFloat(vs_dice3, "translationX", diceBox.getLeft() + 50);
        ObjectAnimator anima_dice3Y = ObjectAnimator.ofFloat(vs_dice3, "translationY", diceBox.getTop() + 50);
        ObjectAnimator anima_dice4X = ObjectAnimator.ofFloat(vs_dice4, "translationX", diceBox.getLeft() + 50);
        ObjectAnimator anima_dice4Y = ObjectAnimator.ofFloat(vs_dice4, "translationY", diceBox.getTop() + 50);
        ObjectAnimator anima_dice5X = ObjectAnimator.ofFloat(vs_dice5, "translationX", diceBox.getLeft() + 50);
        ObjectAnimator anima_dice5Y = ObjectAnimator.ofFloat(vs_dice5, "translationY", diceBox.getTop() + 50);

        anima_dice1X.setDuration(600);
        anima_dice1Y.setDuration(600);
        anima_dice2X.setDuration(600);
        anima_dice2Y.setDuration(600);
        anima_dice3X.setDuration(600);
        anima_dice3Y.setDuration(600);
        anima_dice4X.setDuration(600);
        anima_dice4Y.setDuration(600);
        anima_dice5X.setDuration(600);
        anima_dice5Y.setDuration(600);

        vsAnimatorSet.play(anima_dice1X);
        vsAnimatorSet.play(anima_dice1Y);
        vsAnimatorSet.play(anima_dice2X);
        vsAnimatorSet.play(anima_dice2Y);
        vsAnimatorSet.play(anima_dice3X);
        vsAnimatorSet.play(anima_dice3Y);
        vsAnimatorSet.play(anima_dice4X);
        vsAnimatorSet.play(anima_dice4Y);
        vsAnimatorSet.play(anima_dice5X);
        vsAnimatorSet.play(anima_dice5Y);

        vsAnimatorSet.start();

    }

    public boolean gameEnd(){

        if (sharedPreferences.getString("P1Aces","") != ""
        &&  sharedPreferences.getString("P1Twos","") != ""
        &&  sharedPreferences.getString("P1Threes","") != ""
        &&  sharedPreferences.getString("P1Fours","") != ""
        &&  sharedPreferences.getString("P1Fives","") != ""
        &&  sharedPreferences.getString("P1Sixes","") != ""
        &&  sharedPreferences.getString("P1TOAK","") != ""
        &&  sharedPreferences.getString("P1FOAK","") != ""
        &&  sharedPreferences.getString("P1FH","") != ""
        &&  sharedPreferences.getString("P1SS","") != ""
        &&  sharedPreferences.getString("P1LS","") != ""
        &&  sharedPreferences.getString("P1Chance","") != ""
        &&  sharedPreferences.getString("P1YACHT","") != ""
        &&  sharedPreferences2.getString("P2Aces","") != ""
        &&  sharedPreferences2.getString("P2Twos","") != ""
        &&  sharedPreferences2.getString("P2Threes","") != ""
        &&  sharedPreferences2.getString("P2Fours","") != ""
        &&  sharedPreferences2.getString("P2Fives","") != ""
        &&  sharedPreferences2.getString("P2Sixes","") != ""
        &&  sharedPreferences2.getString("P2TOAK","") != ""
        &&  sharedPreferences2.getString("P2FOAK","") != ""
        &&  sharedPreferences2.getString("P2FH","") != ""
        &&  sharedPreferences2.getString("P2SS","") != ""
        &&  sharedPreferences2.getString("P2LS","") != ""
        &&  sharedPreferences2.getString("P2Chance","") != ""
        &&  sharedPreferences2.getString("P2YACHT","") != ""){
            return true;
        }

        return false;
    }

    public void timeOver(){

        ScoreCalculation sc = new ScoreCalculation();
        int score = 0;
        String scoreName = null;

        if(userTurn && turnTimer <= 0){

            if (loginUserNickName.equals(player1Name)){

                if (sharedPreferences.getString("P1YACHT","") == ""){
                    score = sc.calcYACHT(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye);
                    scoreName = "P1YACHT";
                }else if (sharedPreferences.getString("P1Chance","") == ""){
                    score = sc.calcChance(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye);
                    scoreName = "P1Chance";
                }else if (sharedPreferences.getString("P1LS","") == ""){
                    score = sc.calcLargeStraight(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye);
                    scoreName = "P1LS";
                }else if (sharedPreferences.getString("P1SS","") == ""){
                    score = sc.calcSmallStraight(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye);
                    scoreName = "P1SS";
                }else if (sharedPreferences.getString("P1FH","") == ""){
                    score = sc.calcFullHouse(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye);
                    scoreName = "P1FH";
                }else if (sharedPreferences.getString("P1FOAK","") == ""){
                    score = sc.calcFourOfAKind(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye);
                    scoreName = "P1FOAK";
                }else if (sharedPreferences.getString("P1TOAK","") == ""){
                    score = sc.calcThreeOfAKind(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye);
                    scoreName = "P1TOAK";
                }else if (sharedPreferences.getString("P1Sixes","") == ""){
                    score = sc.calcSix(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye);
                    scoreName = "P1Sixes";
                }else if (sharedPreferences.getString("P1Fives","") == ""){
                    score = sc.calcFive(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye);
                    scoreName = "P1Fives";
                }else if (sharedPreferences.getString("P1Fours","") == ""){
                    score = sc.calcFour(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye);
                    scoreName = "P1Fours";
                }else if (sharedPreferences.getString("P1Threes","") == ""){
                    score = sc.calcFullHouse(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye);
                    scoreName = "P1Threes";
                }else if (sharedPreferences.getString("P1Twos","") == ""){
                    score = sc.calcTwo(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye);
                    scoreName = "P1Twos";
                }else if (sharedPreferences.getString("P1Aces","") == ""){
                    score = sc.calcAce(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye);
                    scoreName = "P1Aces";
                }

            }else if (loginUserNickName.equals(player2Name)){

                if (sharedPreferences2.getString("P2YACHT","") == ""){
                    score = sc.calcYACHT(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye);
                    scoreName = "P2YACHT";
                }else if (sharedPreferences2.getString("P2Chance","") == ""){
                    score = sc.calcChance(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye);
                    scoreName = "P2Chance";
                }else if (sharedPreferences2.getString("P2LS","") == ""){
                    score = sc.calcLargeStraight(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye);
                    scoreName = "P2LS";
                }else if (sharedPreferences2.getString("P2SS","") == ""){
                    score = sc.calcSmallStraight(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye);
                    scoreName = "P2SS";
                }else if (sharedPreferences2.getString("P2FH","") == ""){
                    score = sc.calcFullHouse(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye);
                    scoreName = "P2FH";
                }else if (sharedPreferences2.getString("P2FOAK","") == ""){
                    score = sc.calcFourOfAKind(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye);
                    scoreName = "P2FOAK";
                }else if (sharedPreferences2.getString("P2TOAK","") == ""){
                    score = sc.calcThreeOfAKind(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye);
                    scoreName = "P2TOAK";
                }else if (sharedPreferences2.getString("P2Sixes","") == ""){
                    score = sc.calcSix(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye);
                    scoreName = "P2Sixes";
                }else if (sharedPreferences2.getString("P2Fives","") == ""){
                    score = sc.calcFive(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye);
                    scoreName = "P2Fives";
                }else if (sharedPreferences2.getString("P2Fours","") == ""){
                    score = sc.calcFour(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye);
                    scoreName = "P2Fours";
                }else if (sharedPreferences2.getString("P2Threes","") == ""){
                    score = sc.calcThree(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye);
                    scoreName = "P2Threes";
                }else if (sharedPreferences2.getString("P2Twos","") == ""){
                    score = sc.calcTwo(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye);
                    scoreName = "P2Twos";
                }else if (sharedPreferences2.getString("P2Aces","") == ""){
                    score = sc.calcAce(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye);
                    scoreName = "P2Aces";
                }

            }

            gss.sendMessage(useJson.scoreClick("ScoreClick",myStatus,scoreName,score));

        }
    }

    public void turnTimerStart(){

        turnTimer = 30;

        new Thread(new Runnable() {
            @Override
            public void run() {

                       while (userTurn && turnTimer > 0){

                           timerData.getTimer().postValue(turnTimer);

                           try {
                               Thread.sleep(1000);
                           } catch (InterruptedException e) {
                               throw new RuntimeException(e);
                           }
                           turnTimer--;
                       }

                       timeOver();

                       turnTimer = 0;
                       timerData.getTimer().postValue(turnTimer);
                   }
        }).start();
    }
}
