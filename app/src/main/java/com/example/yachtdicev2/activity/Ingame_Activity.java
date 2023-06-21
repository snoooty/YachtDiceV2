package com.example.yachtdicev2.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yachtdicev2.R;

import java.util.Random;

public class Ingame_Activity extends AppCompatActivity {

    String TAG = "Ingame_Activity";
    String loginUserNickName;
    View player1View,cpuView;
    int viewLeft,viewRight,viewTop,viewBottom;
    AnimatorSet animatorSet;
    AnimationDrawable rolldice_1,rolldice_2,rolldice_3,rolldice_4,rolldice_5;
    ImageView keepDice1,keepDice2,keepDice3,keepDice4,keepDice5;
    ImageView cpuKeepDice1,cpuKeepDice2,cpuKeepDice3,cpuKeepDice4,cpuKeepDice5;
    boolean dice1Keep_move,dice2Keep_move,dice3Keep_move,dice4Keep_move,dice5Keep_move;
    boolean cpuDice1Keep_move,cpuDice2Keep_move,cpuDice3Keep_move,cpuDice4Keep_move,cpuDice5Keep_move;
    int dice1eye,dice2eye,dice3eye,dice4eye,dice5eye;
    int cpuDice1eye,cpuDice2eye,cpuDice3eye,cpuDice4eye,cpuDice5eye;
    int p1_roll,cpu_roll;
    boolean p1_turnEnd,p1_rollturn;
    boolean p2_turnEnd = true,cpu_rollturn;
    boolean cpu_Aces,cpu_Twos,cpu_Threes,cpu_Fours,cpu_Fives,cpu_Sixes;
    boolean cpu_TOAK,cpu_FOAK,cpu_FH,cpu_SS,cpu_LS,cpu_Chance,cpu_YACHTZEE;

    ImageView dice1,dice2,dice3,dice4,dice5;
    ImageView cpuDice1,cpuDice2,cpuDice3,cpuDice4,cpuDice5;
    Drawable dice_1,dice_2,dice_3,dice_4,dice_5,dice_6;
    Drawable rolldice_1xml,rolldice_2xml,rolldice_3xml,rolldice_4xml,rolldice_5xml;
    Button getScore,rollDice;

    SharedPreferences sharedPreferences,sharedPreferences2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingame);

        sharedPreferences = getSharedPreferences("P1Score",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("p1_Aces");
        editor.remove("p1_Twos");
        editor.remove("p1_Threes");
        editor.remove("p1_Fours");
        editor.remove("p1_Fives");
        editor.remove("p1_Sixes");
        editor.remove("p1_TOAK");
        editor.remove("p1_FOAK");
        editor.remove("p1_FH");
        editor.remove("p1_SS");
        editor.remove("p1_LS");
        editor.remove("p1_Chance");
        editor.remove("p1_YACHTZEE");
        editor.remove("p1_TOAK_YACHTZEE");
        editor.remove("p1_FOAK_YACHTZEE");
        editor.remove("p1_FH_YACHTZEE");
        editor.remove("p1_SS_YACHTZEE");
        editor.remove("p1_LS_YACHTZEE");
        editor.remove("p1_Chance_YACHTZEE");
        editor.remove("p1_TotalScore");
        editor.commit();

        rollDice = (Button) findViewById(R.id.player_1_rollDice);
        getScore = (Button) findViewById(R.id.getScore);
        keepDice1 = (ImageView) findViewById(R.id.keep_Dice_player1_1);
        keepDice2 = (ImageView) findViewById(R.id.keep_Dice_player1_2);
        keepDice3 = (ImageView) findViewById(R.id.keep_Dice_player1_3);
        keepDice4 = (ImageView) findViewById(R.id.keep_Dice_player1_4);
        keepDice5 = (ImageView) findViewById(R.id.keep_Dice_player1_5);
        cpuKeepDice1 = (ImageView) findViewById(R.id.keep_Dice_player2_1);
        cpuKeepDice2 = (ImageView) findViewById(R.id.keep_Dice_player2_2);
        cpuKeepDice3 = (ImageView) findViewById(R.id.keep_Dice_player2_3);
        cpuKeepDice4 = (ImageView) findViewById(R.id.keep_Dice_player2_4);
        cpuKeepDice5 = (ImageView) findViewById(R.id.keep_Dice_player2_5);
        dice1 = (ImageView) findViewById(R.id.player_1_dice_1);
        dice2 = (ImageView) findViewById(R.id.player_1_dice_2);
        dice3 = (ImageView) findViewById(R.id.player_1_dice_3);
        dice4 = (ImageView) findViewById(R.id.player_1_dice_4);
        dice5 = (ImageView) findViewById(R.id.player_1_dice_5);
        cpuDice1 = (ImageView) findViewById(R.id.cpu_dice_1);
        cpuDice2 = (ImageView) findViewById(R.id.cpu_dice_2);
        cpuDice3 = (ImageView) findViewById(R.id.cpu_dice_3);
        cpuDice4 = (ImageView) findViewById(R.id.cpu_dice_4);
        cpuDice5 = (ImageView) findViewById(R.id.cpu_dice_5);
        dice_1 = (Drawable)  getResources().getDrawable(R.drawable.dice_1);
        dice_2 = (Drawable)  getResources().getDrawable(R.drawable.dice_2);
        dice_3 = (Drawable)  getResources().getDrawable(R.drawable.dice_3);
        dice_4 = (Drawable)  getResources().getDrawable(R.drawable.dice_4);
        dice_5 = (Drawable)  getResources().getDrawable(R.drawable.dice_5);
        dice_6 = (Drawable)  getResources().getDrawable(R.drawable.dice_6);
        rolldice_1xml = (Drawable) getResources().getDrawable(R.drawable.rolldice_1);
        rolldice_2xml = (Drawable) getResources().getDrawable(R.drawable.rolldice_2);
        rolldice_3xml = (Drawable) getResources().getDrawable(R.drawable.rolldice_3);
        rolldice_4xml = (Drawable) getResources().getDrawable(R.drawable.rolldice_4);
        rolldice_5xml = (Drawable) getResources().getDrawable(R.drawable.rolldice_5);
        player1View = (View) findViewById(R.id.player1_view);
        cpuView = (View) findViewById(R.id.player2_view);
        TextView playerNickName = (TextView) findViewById(R.id.PLAYER_1);

        Intent intent = getIntent();
        loginUserNickName = intent.getStringExtra("loginUserNickName");
        Log.e(TAG,"loginUserNickName : " + loginUserNickName);

        playerNickName.setText(loginUserNickName);

        // 주사위 굴리기 버튼
        rollDice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!p1_rollturn) {
                        Log.e(TAG, "rollDice 버튼 클릭되나?");

                        p1_roll += 1;
                        Log.e(TAG, "p1_roll : " + p1_roll);

                        if (p1_roll >= 3) {
                            p1_rollturn = true;
                            Log.e(TAG, "p1_rollturn : " + p1_rollturn);
                        }

                        // 주사위 크기 138차이
                        Log.e(TAG, "" + dice1.getLeft());
                        Log.e(TAG, "" + dice1.getRight());
                        Log.e(TAG, "" + dice1.getTop());
                        Log.e(TAG, "" + dice1.getBottom());


                        Random rollRnd = new Random();

                        // dice1 굴리기
                        int dice1Left = rollRnd.nextInt(360 - 138);
                        int dice1Top = rollRnd.nextInt(481 - 234);

                        if (dice1Top < 234) {
                            dice1Top = dice1Top + 234;

                            if (dice1Top > 495 - 138) {
                                dice1Top = dice1Top - 138;
                            }
                        }
                        Log.e(TAG, "dice1Top : " + dice1Top);

                        // dice2 굴리기
                        int dice2Left = rollRnd.nextInt(360 - 138);
                        int dice2Top = rollRnd.nextInt(481 - 234);

                        if (dice2Left < 361) {
                            dice2Left = dice2Left + 360;
                        }

                        if (dice2Top < 234) {
                            dice2Top = dice2Top + 234;

                            if (dice2Top > 495 - 138) {
                                dice2Top = dice2Top - 138;
                            }
                        }
                        Log.e(TAG, "dice2Top : " + dice2Top);

                        // dice3 굴리기
                        int dice3Left = rollRnd.nextInt(360 - 138);
                        int dice3Top = rollRnd.nextInt(481 - 234);

                        if (dice3Left < 721) {
                            dice3Left = dice3Left + 720;
                        }

                        if (dice3Top < 234) {
                            dice3Top = dice3Top + 234;

                            if (dice3Top > 495 - 138) {
                                dice3Top = dice3Top - 138;
                            }
                        }
                        Log.e(TAG, "dice3Top : " + dice3Top);

                        // dice4 굴리기
                        int dice4Left = rollRnd.nextInt(540 - 138);
                        int dice4Top = rollRnd.nextInt(234);

                        if (dice4Top < 495) {
                            dice4Top = dice4Top + 495;

                            if (dice4Top > 729 - 138) {
                                dice4Top = dice4Top - 138;
                            }
                        }
                        Log.e(TAG, "dice4Top : " + dice4Top);

                        // dice5 굴리기
                        int dice5Left = rollRnd.nextInt(540 - 138);
                        int dice5Top = rollRnd.nextInt(234);

                        if (dice5Left < 541) {
                            dice5Left = dice5Left + 540;
                        }

                        if (dice5Top < 495) {
                            dice5Top = dice5Top + 495;

                            if (dice5Top > 720 - 138) {
                                dice5Top = dice5Top - 138;
                            }
                        }
                        Log.e(TAG, "dice5Top : " + dice5Top);


                        ObjectAnimator animaX_dice1 = ObjectAnimator.ofFloat(dice1, "translationX", dice1Left);
                        ObjectAnimator animaY_dice1 = ObjectAnimator.ofFloat(dice1, "translationY", dice1Top);
                        animaX_dice1.setDuration(400);
                        animaY_dice1.setDuration(400);

                        ObjectAnimator animaX_dice2 = ObjectAnimator.ofFloat(dice2, "translationX", dice2Left);
                        ObjectAnimator animaY_dice2 = ObjectAnimator.ofFloat(dice2, "translationY", dice2Top);
                        animaX_dice2.setDuration(400);
                        animaY_dice2.setDuration(400);

                        ObjectAnimator animaX_dice3 = ObjectAnimator.ofFloat(dice3, "translationX", dice3Left);
                        ObjectAnimator animaY_dice3 = ObjectAnimator.ofFloat(dice3, "translationY", dice3Top);
                        animaX_dice3.setDuration(400);
                        animaY_dice3.setDuration(400);

                        ObjectAnimator animaX_dice4 = ObjectAnimator.ofFloat(dice4, "translationX", dice4Left);
                        ObjectAnimator animaY_dice4 = ObjectAnimator.ofFloat(dice4, "translationY", dice4Top);
                        animaX_dice4.setDuration(400);
                        animaY_dice4.setDuration(400);

                        ObjectAnimator animaX_dice5 = ObjectAnimator.ofFloat(dice5, "translationX", dice5Left);
                        ObjectAnimator animaY_dice5 = ObjectAnimator.ofFloat(dice5, "translationY", dice5Top);
                        animaX_dice5.setDuration(400);
                        animaY_dice5.setDuration(400);

                        animatorSet = new AnimatorSet();

                        if (!dice1Keep_move) {
                            // dice1 생성
                            dice1.setImageResource(0);
                            dice1.setImageDrawable(rolldice_1xml);
                            rolldice_1 = new AnimationDrawable();
                            rolldice_1 = (AnimationDrawable) dice1.getDrawable();
                            rolldice_1.setOneShot(true);

                            rolldice_1.stop();
                            rolldice_1.start();

                            dice1.setVisibility(View.VISIBLE);

                            // dice1 이동
                            animatorSet.play(animaX_dice1);
                            animatorSet.play(animaY_dice1);
                        }

                        if (!dice2Keep_move) {
                            // dice2 생성
                            dice2.setImageResource(0);
                            dice2.setImageDrawable(rolldice_2xml);
                            rolldice_2 = new AnimationDrawable();
                            rolldice_2 = (AnimationDrawable) dice2.getDrawable();
                            rolldice_2.setOneShot(true);

                            rolldice_2.stop();
                            rolldice_2.start();

                            dice2.setVisibility(View.VISIBLE);

                            // dice2 이동
                            animatorSet.play(animaX_dice2);
                            animatorSet.play(animaY_dice2);

                        }

                        if (!dice3Keep_move) {
                            // dice3 생성
                            dice3.setImageResource(0);
                            dice3.setImageDrawable(rolldice_3xml);
                            rolldice_3 = new AnimationDrawable();
                            rolldice_3 = (AnimationDrawable) dice3.getDrawable();
                            rolldice_3.setOneShot(true);

                            rolldice_3.stop();
                            rolldice_3.start();

                            dice3.setVisibility(View.VISIBLE);

                            // dice3 이동
                            animatorSet.play(animaX_dice3);
                            animatorSet.play(animaY_dice3);

                        }

                        if (!dice4Keep_move) {
                            // dice4 생성
                            dice4.setImageResource(0);
                            dice4.setImageDrawable(rolldice_4xml);
                            rolldice_4 = new AnimationDrawable();
                            rolldice_4 = (AnimationDrawable) dice4.getDrawable();
                            rolldice_4.setOneShot(true);

                            rolldice_4.stop();
                            rolldice_4.start();

                            dice4.setVisibility(View.VISIBLE);

                            // dice4 이동
                            animatorSet.play(animaX_dice4);
                            animatorSet.play(animaY_dice4);
                        }

                        if (!dice5Keep_move) {
                            // dice5 생성
                            dice5.setImageResource(0);
                            dice5.setImageDrawable(rolldice_5xml);
                            rolldice_5 = new AnimationDrawable();
                            rolldice_5 = (AnimationDrawable) dice5.getDrawable();
                            rolldice_5.setOneShot(true);

                            rolldice_5.stop();
                            rolldice_5.start();

                            dice5.setVisibility(View.VISIBLE);

                            // dice5 이동
                            animatorSet.play(animaX_dice5);
                            animatorSet.play(animaY_dice5);
                        }


                        animatorSet.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);


                                Random rnd1 = new Random();

                                // dice1 랜덤
                                int roll1 = rnd1.nextInt(6);

                                if (!dice1Keep_move) {
                                    switch (roll1) {
                                        case 0:
                                            dice1.setImageDrawable(dice_1);
                                            dice1eye = 1;
                                            break;
                                        case 1:
                                            dice1.setImageDrawable(dice_2);
                                            dice1eye = 2;
                                            break;
                                        case 2:
                                            dice1.setImageDrawable(dice_3);
                                            dice1eye = 3;
                                            break;
                                        case 3:
                                            dice1.setImageDrawable(dice_4);
                                            dice1eye = 4;
                                            break;
                                        case 4:
                                            dice1.setImageDrawable(dice_5);
                                            dice1eye = 5;
                                            break;
                                        case 5:
                                            dice1.setImageDrawable(dice_6);
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
                                            dice2.setImageDrawable(dice_1);
                                            dice2eye = 1;
                                            break;
                                        case 1:
                                            dice2.setImageDrawable(dice_2);
                                            dice2eye = 2;
                                            break;
                                        case 2:
                                            dice2.setImageDrawable(dice_3);
                                            dice2eye = 3;
                                            break;
                                        case 3:
                                            dice2.setImageDrawable(dice_4);
                                            dice2eye = 4;
                                            break;
                                        case 4:
                                            dice2.setImageDrawable(dice_5);
                                            dice2eye = 5;
                                            break;
                                        case 5:
                                            dice2.setImageDrawable(dice_6);
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
                                            dice3.setImageDrawable(dice_1);
                                            dice3eye = 1;
                                            break;
                                        case 1:
                                            dice3.setImageDrawable(dice_2);
                                            dice3eye = 2;
                                            break;
                                        case 2:
                                            dice3.setImageDrawable(dice_3);
                                            dice3eye = 3;
                                            break;
                                        case 3:
                                            dice3.setImageDrawable(dice_4);
                                            dice3eye = 4;
                                            break;
                                        case 4:
                                            dice3.setImageDrawable(dice_5);
                                            dice3eye = 5;
                                            break;
                                        case 5:
                                            dice3.setImageDrawable(dice_6);
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
                                            dice4.setImageDrawable(dice_1);
                                            dice4eye = 1;
                                            break;
                                        case 1:
                                            dice4.setImageDrawable(dice_2);
                                            dice4eye = 2;
                                            break;
                                        case 2:
                                            dice4.setImageDrawable(dice_3);
                                            dice4eye = 3;
                                            break;
                                        case 3:
                                            dice4.setImageDrawable(dice_4);
                                            dice4eye = 4;
                                            break;
                                        case 4:
                                            dice4.setImageDrawable(dice_5);
                                            dice4eye = 5;
                                            break;
                                        case 5:
                                            dice4.setImageDrawable(dice_6);
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
                                            dice5.setImageDrawable(dice_1);
                                            dice5eye = 1;
                                            break;
                                        case 1:
                                            dice5.setImageDrawable(dice_2);
                                            dice5eye = 2;
                                            break;
                                        case 2:
                                            dice5.setImageDrawable(dice_3);
                                            dice5eye = 3;
                                            break;
                                        case 3:
                                            dice5.setImageDrawable(dice_4);
                                            dice5eye = 4;
                                            break;
                                        case 4:
                                            dice5.setImageDrawable(dice_5);
                                            dice5eye = 5;
                                            break;
                                        case 5:
                                            dice5.setImageDrawable(dice_6);
                                            dice5eye = 6;
                                            break;
                                    }
                                }

                            }
                        });

                        animatorSet.start();

                        Log.e(TAG, "dice sum : " + dice1eye + dice2eye + dice3eye + dice4eye + dice5eye);

                    } else if (p1_rollturn) {
                        Log.e(TAG,"p1의 턴이 끝났습니다.");

                        AlertDialog.Builder builder = new AlertDialog.Builder(Ingame_Activity.this);
                        builder.setTitle("주사위 굴리기가 끝났습니다.");
                        builder.show();
                    }
                }
            });

        // dice1 킵
        dice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG,"dice1 클릭되나요?");

                animatorSet = new AnimatorSet();

                if(!dice1Keep_move){
                    ObjectAnimator animaX_dice1 = ObjectAnimator.ofFloat(dice1,"translationX",keepDice1.getLeft());
                    ObjectAnimator animaY_dice1 = ObjectAnimator.ofFloat(dice1,"translationY",keepDice1.getTop());
                    animaX_dice1.setDuration(600);
                    animaY_dice1.setDuration(600);

                    animatorSet.play(animaX_dice1);
                    animatorSet.play(animaY_dice1);

                    animatorSet.start();

                    dice1Keep_move = true;
                }else if(dice1Keep_move){

                    Random rollRnd = new Random();

                    // dice1 굴리기
                    int dice1Left = rollRnd.nextInt(360-138);
                    int dice1Top = rollRnd.nextInt(481-234);

                    if(dice1Top < 234){
                        dice1Top = dice1Top + 234;

                        if(dice1Top > 495 - 138){
                            dice1Top = dice1Top - 138;
                        }
                    }
                    Log.e(TAG,"dice1Top : " + dice1Top);

                    ObjectAnimator animaX_dice1 = ObjectAnimator.ofFloat(dice1,"translationX",dice1Left);
                    ObjectAnimator animaY_dice1 = ObjectAnimator.ofFloat(dice1,"translationY",dice1Top);
                    animaX_dice1.setDuration(600);
                    animaY_dice1.setDuration(600);

                    animatorSet.play(animaX_dice1);
                    animatorSet.play(animaY_dice1);

                    animatorSet.start();

                    dice1Keep_move = false;
                }
            }
        });

        // dice2 킵
        dice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG,"dice2 클릭되나요?");

                animatorSet = new AnimatorSet();

                if(!dice2Keep_move){
                    ObjectAnimator animaX_dice2 = ObjectAnimator.ofFloat(dice2,"translationX",keepDice2.getLeft());
                    ObjectAnimator animaY_dice2 = ObjectAnimator.ofFloat(dice2,"translationY",keepDice2.getTop());
                    animaX_dice2.setDuration(600);
                    animaY_dice2.setDuration(600);

                    animatorSet.play(animaX_dice2);
                    animatorSet.play(animaY_dice2);

                    animatorSet.start();

                    dice2Keep_move = true;
                }else if(dice2Keep_move){

                    Random rollRnd = new Random();

                    // dice2 굴리기
                    int dice2Left = rollRnd.nextInt(360-138);
                    int dice2Top = rollRnd.nextInt(481-234);

                    if (dice2Left < 361) {
                        dice2Left = dice2Left + 360;
                    }

                    if(dice2Top < 234){
                        dice2Top = dice2Top + 234;

                        if(dice2Top > 495 - 138){
                            dice2Top = dice2Top - 138;
                        }
                    }
                    Log.e(TAG,"dice2Top : " + dice2Top);

                    ObjectAnimator animaX_dice2 = ObjectAnimator.ofFloat(dice2,"translationX",dice2Left);
                    ObjectAnimator animaY_dice2 = ObjectAnimator.ofFloat(dice2,"translationY",dice2Top);
                    animaX_dice2.setDuration(600);
                    animaY_dice2.setDuration(600);

                    animatorSet.play(animaX_dice2);
                    animatorSet.play(animaY_dice2);

                    animatorSet.start();

                    dice2Keep_move = false;

                }
            }
        });

        // dice3 킵
        dice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG,"dice3 클릭되나요?");

                animatorSet = new AnimatorSet();

                if(!dice3Keep_move){
                    ObjectAnimator animaX_dice3 = ObjectAnimator.ofFloat(dice3,"translationX",keepDice3.getLeft());
                    ObjectAnimator animaY_dice3 = ObjectAnimator.ofFloat(dice3,"translationY",keepDice3.getTop());
                    animaX_dice3.setDuration(600);
                    animaY_dice3.setDuration(600);

                    animatorSet.play(animaX_dice3);
                    animatorSet.play(animaY_dice3);

                    animatorSet.start();

                    dice3Keep_move = true;
                }else if(dice3Keep_move){

                    Random rollRnd = new Random();

                    // dice3 굴리기
                    int dice3Left = rollRnd.nextInt(360-138);
                    int dice3Top = rollRnd.nextInt(481-234);

                    if(dice3Left < 721){
                        dice3Left = dice3Left + 720;
                    }

                    if(dice3Top < 234){
                        dice3Top = dice3Top + 234;

                        if(dice3Top > 495 - 138){
                            dice3Top = dice3Top - 138;
                        }
                    }
                    Log.e(TAG,"dice3Top : " + dice3Top);

                    ObjectAnimator animaX_dice3 = ObjectAnimator.ofFloat(dice3,"translationX",dice3Left);
                    ObjectAnimator animaY_dice3 = ObjectAnimator.ofFloat(dice3,"translationY",dice3Top);
                    animaX_dice3.setDuration(600);
                    animaY_dice3.setDuration(600);

                    animatorSet.play(animaX_dice3);
                    animatorSet.play(animaY_dice3);

                    animatorSet.start();

                    dice3Keep_move = false;
                }
            }
        });

        // dice4 킵
        dice4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG,"dice4 클릭되나요?");

                animatorSet = new AnimatorSet();

                if(!dice4Keep_move){
                    ObjectAnimator animaX_dice4 = ObjectAnimator.ofFloat(dice4,"translationX",keepDice4.getLeft());
                    ObjectAnimator animaY_dice4 = ObjectAnimator.ofFloat(dice4,"translationY",keepDice4.getTop());
                    animaX_dice4.setDuration(600);
                    animaY_dice4.setDuration(600);

                    animatorSet.play(animaX_dice4);
                    animatorSet.play(animaY_dice4);

                    animatorSet.start();

                    dice4Keep_move = true;
                }else if(dice4Keep_move){

                    Random rollRnd = new Random();

                    // dice4 굴리기
                    int dice4Left = rollRnd.nextInt(540-138);
                    int dice4Top = rollRnd.nextInt(234);

                    if(dice4Top < 495){
                        dice4Top = dice4Top + 495;

                        if(dice4Top > 729 - 138){
                            dice4Top = dice4Top - 138;
                        }
                    }
                    Log.e(TAG,"dice4Top : " + dice4Top);

                    ObjectAnimator animaX_dice4 = ObjectAnimator.ofFloat(dice4,"translationX",dice4Left);
                    ObjectAnimator animaY_dice4 = ObjectAnimator.ofFloat(dice4,"translationY",dice4Top);
                    animaX_dice4.setDuration(600);
                    animaY_dice4.setDuration(600);

                    animatorSet.play(animaX_dice4);
                    animatorSet.play(animaY_dice4);

                    animatorSet.start();

                    dice4Keep_move = false;
                }
            }
        });

        // dice5 킵
        dice5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG,"dice5 클릭되나요?");

                animatorSet = new AnimatorSet();

                if(!dice5Keep_move){
                    ObjectAnimator animaX_dice5 = ObjectAnimator.ofFloat(dice5,"translationX",keepDice5.getLeft());
                    ObjectAnimator animaY_dice5 = ObjectAnimator.ofFloat(dice5,"translationY",keepDice5.getTop());
                    animaX_dice5.setDuration(600);
                    animaY_dice5.setDuration(600);

                    animatorSet.play(animaX_dice5);
                    animatorSet.play(animaY_dice5);

                    animatorSet.start();

                    dice5Keep_move = true;
                }else if(dice5Keep_move){

                    Random rollRnd = new Random();

                    // dice5 굴리기
                    int dice5Left = rollRnd.nextInt(540-138);
                    int dice5Top = rollRnd.nextInt(234);

                    if(dice5Left < 541){
                        dice5Left = dice5Left + 540;
                    }

                    if(dice5Top < 495){
                        dice5Top = dice5Top + 495;

                        if(dice5Top > 720 - 138){
                            dice5Top = dice5Top - 138;
                        }
                    }
                    Log.e(TAG,"dice5Top : " + dice5Top);

                    ObjectAnimator animaX_dice5 = ObjectAnimator.ofFloat(dice5,"translationX",dice5Left);
                    ObjectAnimator animaY_dice5 = ObjectAnimator.ofFloat(dice5,"translationY",dice5Top);
                    animaX_dice5.setDuration(600);
                    animaY_dice5.setDuration(600);

                    animatorSet.play(animaX_dice5);
                    animatorSet.play(animaY_dice5);

                    animatorSet.start();

                    dice5Keep_move = false;
                }
            }
        });

        // 클릭 시 점수화면으로
        getScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!p1_turnEnd){
                    Intent intent = new Intent(Ingame_Activity.this, ScorePage.class);
                    intent.putExtra("dice1eye",dice1eye);
                    intent.putExtra("dice2eye",dice2eye);
                    intent.putExtra("dice3eye",dice3eye);
                    intent.putExtra("dice4eye",dice4eye);
                    intent.putExtra("dice5eye",dice5eye);
                    intent.putExtra("CPU","CPU");
                    intent.putExtra("loginUserNickName",loginUserNickName);
                    startActivityForResult(intent, 2);
                }else {
                    Intent intent = new Intent(Ingame_Activity.this,ScorePage.class);
                    intent.putExtra("dice1eye",0);
                    intent.putExtra("dice2eye",0);
                    intent.putExtra("dice3eye",0);
                    intent.putExtra("dice4eye",0);
                    intent.putExtra("dice5eye",0);
                    intent.putExtra("CPU","CPU");
                    intent.putExtra("loginUserNickName",loginUserNickName);
                    startActivityForResult(intent, 2);
                }
            }
        });


        sharedPreferences2 = getSharedPreferences("CPUScore",MODE_PRIVATE);
        SharedPreferences.Editor editor2 = sharedPreferences2.edit();
        editor2.remove("cpu_Aces");
        editor2.remove("cpu_Twos");
        editor2.remove("cpu_Threes");
        editor2.remove("cpu_Fours");
        editor2.remove("cpu_Fives");
        editor2.remove("cpu_Sixes");
        editor2.remove("cpu_TOAK");
        editor2.remove("cpu_FOAK");
        editor2.remove("cpu_FH");
        editor2.remove("cpu_SS");
        editor2.remove("cpu_LS");
        editor2.remove("cpu_Chance");
        editor2.remove("cpu_YACHTZEE");
        editor2.remove("cpu_TOAK_YACHTZEE");
        editor2.remove("cpu_FOAK_YACHTZEE");
        editor2.remove("cpu_FH_YACHTZEE");
        editor2.remove("cpu_SS_YACHTZEE");
        editor2.remove("cpu_LS_YACHTZEE");
        editor2.remove("cpu_Chance_YACHTZEE");
        editor2.commit();



    }

    // 화면이 그려지면 player1View 의 화면에서의 위치값 가져오기
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        // View의 사이즈
//        Log.e(TAG,"View 의 left : " +player1View.getLeft());
//        Log.e(TAG,"View 의 right : " + player1View.getRight());
//        Log.e(TAG,"View 의 top : " + player1View.getTop());
//        Log.e(TAG,"View 의 bottom : " + player1View.getBottom());

        // keepDice1의 사이즈
//        Log.e(TAG,"keepDice1 의 left : " + keepDice1.getLeft());
//        Log.e(TAG,"keepDice1 의 right : " + keepDice1.getRight());
//        Log.e(TAG,"keepDice1 의 top : " + keepDice1.getTop());
//        Log.e(TAG,"keepDice1 의 bottom : " + keepDice1.getBottom());

        // keepDice2의 사이즈
//        Log.e(TAG,"keepDice2 의 left : " + keepDice2.getLeft());
//        Log.e(TAG,"keepDice2 의 right : " + keepDice2.getRight());
//        Log.e(TAG,"keepDice2 의 top : " + keepDice2.getTop());
//        Log.e(TAG,"keepDice2 의 bottom : " + keepDice2.getBottom());

        // keepDice3의 사이즈
//        Log.e(TAG,"keepDice3 의 left : " + keepDice3.getLeft());
//        Log.e(TAG,"keepDice3 의 right : " + keepDice3.getRight());
//        Log.e(TAG,"keepDice3 의 top : " + keepDice3.getTop());
//        Log.e(TAG,"keepDice3 의 bottom : " + keepDice3.getBottom());

        // keepDice4의 사이즈
//        Log.e(TAG,"keepDice4 의 left : " + keepDice4.getLeft());
//        Log.e(TAG,"keepDice4 의 right : " + keepDice4.getRight());
//        Log.e(TAG,"keepDice4 의 top : " + keepDice4.getTop());
//        Log.e(TAG,"keepDice4 의 bottom : " + keepDice4.getBottom());

        // keepDice5의 사이즈
//        Log.e(TAG,"keepDice1 의 left : " + keepDice5.getLeft());
//        Log.e(TAG,"keepDice1 의 right : " + keepDice5.getRight());
//        Log.e(TAG,"keepDice1 의 top : " + keepDice5.getTop());
//        Log.e(TAG,"keepDice1 의 bottom : " + keepDice5.getBottom());

        // CPU View의 사이즈
//        Log.e(TAG,"View 의 left : " +cpuView.getLeft());
//        Log.e(TAG,"View 의 right : " + cpuView.getRight());
//        Log.e(TAG,"View 의 top : " + cpuView.getTop());
//        Log.e(TAG,"View 의 bottom : " + cpuView.getBottom());

    }

    // onCreate 이후 바로 사용하면 모든값이 0이 됨
    public void getViewLocation(){
//        Log.e(TAG,"lfet : " + viewLeft);
        // left 값 : 0
//        Log.e(TAG,"right : " + viewRight);
        // right 값 : 1080
//        Log.e(TAG,"top : " + viewTop);
        // top 값 : 234
//        Log.e(TAG,"bottom : " + viewBottom);
        // bottom 값 : 729

//        viewLeft = player1View.getLeft();
//        viewRight = player1View.getRight();
//        viewTop = player1View.getTop();
//        viewBottom = player1View.getBottom();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    // CallBack 결과값 가져오기
    @Override
    protected void onActivityResult(int requewstCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requewstCode, resultCode, data);

        if(requewstCode == 2){
            if(resultCode == RESULT_OK){
                p1_turnEnd = data.getExtras().getBoolean("p1_turnEnd");
                p2_turnEnd = data.getExtras().getBoolean("p2_turnEnd");
                Log.e(TAG,"p1_turnEnd : " + p1_turnEnd);

                if (!p1_turnEnd) {
                    p1_roll = 0;
                    p1_rollturn = false;

                }
                if (p1_turnEnd) {
                    p1_roll = 3;
                    p1_rollturn = true;
                    cpu_roll = 0;
                    cpu_rollturn = false;

                    dice1.setVisibility(View.INVISIBLE);
                    dice2.setVisibility(View.INVISIBLE);
                    dice3.setVisibility(View.INVISIBLE);
                    dice4.setVisibility(View.INVISIBLE);
                    dice5.setVisibility(View.INVISIBLE);

                    player_1_reset();
                }

                Log.e(TAG,"p2_turnEnd : " + p2_turnEnd);
                Log.e(TAG,"player1_score() : " + player1_score());
                if (!p2_turnEnd && player1_score()) {

                    cpuGetScore();
                    new Thread(){
                        public void run(){
                            try {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        cpu_rolldice();
                                    }
                                });
                                while (!cpu_rollturn && !p2_turnEnd) {
                                    Log.e(TAG,"cpu_roll : " + cpu_roll);
                                    Thread.sleep(2000);
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            cpuCalculation();
                                        }
                                    });

                                }
                            }catch (InterruptedException e){
                                e.printStackTrace();
                            }
                        }
                    }.start();

                }
            }
        }

    }

    // player1의 스코어 체크
    public boolean player1_score(){

        if (sharedPreferences.getString("p1_Aces","") != ""){
            return true;
        }
        if (sharedPreferences.getString("p1_Twos","") != ""){
            return true;
        }
        if (sharedPreferences.getString("p1_Threes","") != ""){
            return true;
        }
        if (sharedPreferences.getString("p1_Fours","") != ""){
            return true;
        }
        if (sharedPreferences.getString("p1_Fives","") != ""){
            return true;
        }
        if (sharedPreferences.getString("p1_Sixes","") != ""){
            return true;
        }
        if (sharedPreferences.getString("p1_TOAK","") != ""){
            return true;
        }
        if (sharedPreferences.getString("p1_FOAK","") != ""){
            return true;
        }
        if (sharedPreferences.getString("p1_FH","") != ""){
            return true;
        }
        if (sharedPreferences.getString("p1_SS","") != ""){
            return true;
        }
        if (sharedPreferences.getString("p1_LS","") != ""){
            return true;
        }
        if (sharedPreferences.getString("p1_Chance","") != ""){
            return true;
        }
        if (sharedPreferences.getString("p1_YACHTZEE","") != ""){
            return true;
        }
        return false;
    }

    // cpu가 주사위 굴림
    public void cpu_rolldice(){

        if (!cpu_rollturn) {

            Log.e(TAG, "CPU 주사위 굴리나?");

            cpu_roll += 1;
            Log.e(TAG, "cpu_roll : " + cpu_roll);

            if (cpu_roll >= 3) {
                cpu_rollturn = true;
                Log.e(TAG, "cpu_rollturn : " + cpu_rollturn);
            }
        }



        Random rollRnd = new Random();

        // dice1 굴리기
        int dice1Left = rollRnd.nextInt(360 - 138);
        int dice1Top = rollRnd.nextInt(1393 - 1146);

        if (dice1Top < 1146) {
            dice1Top = dice1Top + 1146;

            if (dice1Top > 1393 - 138) {
                dice1Top = dice1Top - 138;
            }
        }
        Log.e(TAG, "cpuDice1Top : " + dice1Top);

        // dice2 굴리기
        int dice2Left = rollRnd.nextInt(360 - 138);
        int dice2Top = rollRnd.nextInt(1393 - 1146);

        if (dice2Left < 361) {
            dice2Left = dice2Left + 360;
        }

        if (dice2Top < 1146) {
            dice2Top = dice2Top + 1146;

            if (dice2Top > 1393 - 138) {
                dice2Top = dice2Top - 138;
            }
        }
        Log.e(TAG, "cpuDice2Top : " + dice2Top);

        // dice3 굴리기
        int dice3Left = rollRnd.nextInt(360 - 138);
        int dice3Top = rollRnd.nextInt(1393 - 1146);

        if (dice3Left < 721) {
            dice3Left = dice3Left + 720;
        }

        if (dice3Top < 1146) {
            dice3Top = dice3Top + 1146;

            if (dice3Top > 1393 - 138) {
                dice3Top = dice3Top - 138;
            }
        }
        Log.e(TAG, "cpuDice3Top : " + dice3Top);

        // dice4 굴리기
        int dice4Left = rollRnd.nextInt(540 - 138);
        int dice4Top = rollRnd.nextInt(1641 - 1394);

        if (dice4Top < 1394) {
            dice4Top = dice4Top + 1394;

            if (dice4Top > 1641 - 138) {
                dice4Top = dice4Top - 138;
            }
        }
        Log.e(TAG, "cpuDice4Top : " + dice4Top);

        // dice5 굴리기
        int dice5Left = rollRnd.nextInt(540 - 138);
        int dice5Top = rollRnd.nextInt(1641 - 1394);

        if (dice5Left < 541) {
            dice5Left = dice5Left + 540;
        }

        if (dice5Top < 1394) {
            dice5Top = dice5Top + 1394;

            if (dice5Top > 1641 - 138) {
                dice5Top = dice5Top - 138;
            }
        }
        Log.e(TAG, "cpuDice5Top : " + dice5Top);


        ObjectAnimator animaX_dice1 = ObjectAnimator.ofFloat(cpuDice1, "translationX", dice1Left);
        ObjectAnimator animaY_dice1 = ObjectAnimator.ofFloat(cpuDice1, "translationY", dice1Top);
        animaX_dice1.setDuration(400);
        animaY_dice1.setDuration(400);

        ObjectAnimator animaX_dice2 = ObjectAnimator.ofFloat(cpuDice2, "translationX", dice2Left);
        ObjectAnimator animaY_dice2 = ObjectAnimator.ofFloat(cpuDice2, "translationY", dice2Top);
        animaX_dice2.setDuration(400);
        animaY_dice2.setDuration(400);

        ObjectAnimator animaX_dice3 = ObjectAnimator.ofFloat(cpuDice3, "translationX", dice3Left);
        ObjectAnimator animaY_dice3 = ObjectAnimator.ofFloat(cpuDice3, "translationY", dice3Top);
        animaX_dice3.setDuration(400);
        animaY_dice3.setDuration(400);

        ObjectAnimator animaX_dice4 = ObjectAnimator.ofFloat(cpuDice4, "translationX", dice4Left);
        ObjectAnimator animaY_dice4 = ObjectAnimator.ofFloat(cpuDice4, "translationY", dice4Top);
        animaX_dice4.setDuration(400);
        animaY_dice4.setDuration(400);

        ObjectAnimator animaX_dice5 = ObjectAnimator.ofFloat(cpuDice5, "translationX", dice5Left);
        ObjectAnimator animaY_dice5 = ObjectAnimator.ofFloat(cpuDice5, "translationY", dice5Top);
        animaX_dice5.setDuration(400);
        animaY_dice5.setDuration(400);

        animatorSet = new AnimatorSet();

        if (!cpuDice1Keep_move) {
            // dice1 생성
            cpuDice1.setImageResource(0);
            cpuDice1.setImageDrawable(rolldice_1xml);
            rolldice_1 = new AnimationDrawable();
            rolldice_1 = (AnimationDrawable) cpuDice1.getDrawable();
            rolldice_1.setOneShot(true);

            rolldice_1.stop();
            rolldice_1.start();

            cpuDice1.setVisibility(View.VISIBLE);

            // dice1 이동
            animatorSet.play(animaX_dice1);
            animatorSet.play(animaY_dice1);
        }

        if (!cpuDice2Keep_move) {
            // dice2 생성
            cpuDice2.setImageResource(0);
            cpuDice2.setImageDrawable(rolldice_2xml);
            rolldice_2 = new AnimationDrawable();
            rolldice_2 = (AnimationDrawable) cpuDice2.getDrawable();
            rolldice_2.setOneShot(true);

            rolldice_2.stop();
            rolldice_2.start();

            cpuDice2.setVisibility(View.VISIBLE);

            // dice2 이동
            animatorSet.play(animaX_dice2);
            animatorSet.play(animaY_dice2);

        }

        if (!cpuDice3Keep_move) {
            // dice3 생성
            cpuDice3.setImageResource(0);
            cpuDice3.setImageDrawable(rolldice_3xml);
            rolldice_3 = new AnimationDrawable();
            rolldice_3 = (AnimationDrawable) cpuDice3.getDrawable();
            rolldice_3.setOneShot(true);

            rolldice_3.stop();
            rolldice_3.start();

            cpuDice3.setVisibility(View.VISIBLE);

            // dice3 이동
            animatorSet.play(animaX_dice3);
            animatorSet.play(animaY_dice3);

        }

        if (!cpuDice4Keep_move) {
            // dice4 생성
            cpuDice4.setImageResource(0);
            cpuDice4.setImageDrawable(rolldice_4xml);
            rolldice_4 = new AnimationDrawable();
            rolldice_4 = (AnimationDrawable) cpuDice4.getDrawable();
            rolldice_4.setOneShot(true);

            rolldice_4.stop();
            rolldice_4.start();

            cpuDice4.setVisibility(View.VISIBLE);

            // dice4 이동
            animatorSet.play(animaX_dice4);
            animatorSet.play(animaY_dice4);
        }

        if (!cpuDice5Keep_move) {
            // dice5 생성
            cpuDice5.setImageResource(0);
            cpuDice5.setImageDrawable(rolldice_5xml);
            rolldice_5 = new AnimationDrawable();
            rolldice_5 = (AnimationDrawable) cpuDice5.getDrawable();
            rolldice_5.setOneShot(true);

            rolldice_5.stop();
            rolldice_5.start();

            cpuDice5.setVisibility(View.VISIBLE);

            // dice5 이동
            animatorSet.play(animaX_dice5);
            animatorSet.play(animaY_dice5);
        }


        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);


                Random rnd1 = new Random();

                // dice1 랜덤
                int roll1 = rnd1.nextInt(6);

                if (!cpuDice1Keep_move) {
                    switch (roll1) {
                        case 0:
                            cpuDice1.setImageDrawable(dice_1);
                            cpuDice1eye = 1;
                            break;
                        case 1:
                            cpuDice1.setImageDrawable(dice_2);
                            cpuDice1eye = 2;
                            break;
                        case 2:
                            cpuDice1.setImageDrawable(dice_3);
                            cpuDice1eye = 3;
                            break;
                        case 3:
                            cpuDice1.setImageDrawable(dice_4);
                            cpuDice1eye = 4;
                            break;
                        case 4:
                            cpuDice1.setImageDrawable(dice_5);
                            cpuDice1eye = 5;
                            break;
                        case 5:
                            cpuDice1.setImageDrawable(dice_6);
                            cpuDice1eye = 6;
                            break;
                    }
                }

                Random rnd2 = new Random();

                // dice2 랜덤
                int roll2 = rnd2.nextInt(6);

                if (!cpuDice2Keep_move) {
                    switch (roll2) {
                        case 0:
                            cpuDice2.setImageDrawable(dice_1);
                            cpuDice2eye = 1;
                            break;
                        case 1:
                            cpuDice2.setImageDrawable(dice_2);
                            cpuDice2eye = 2;
                            break;
                        case 2:
                            cpuDice2.setImageDrawable(dice_3);
                            cpuDice2eye = 3;
                            break;
                        case 3:
                            cpuDice2.setImageDrawable(dice_4);
                            cpuDice2eye = 4;
                            break;
                        case 4:
                            cpuDice2.setImageDrawable(dice_5);
                            cpuDice2eye = 5;
                            break;
                        case 5:
                            cpuDice2.setImageDrawable(dice_6);
                            cpuDice2eye = 6;
                            break;
                    }
                }

                Random rnd3 = new Random();

                // dice3 랜덤
                int roll3 = rnd3.nextInt(6);

                if (!cpuDice3Keep_move) {
                    switch (roll3) {
                        case 0:
                            cpuDice3.setImageDrawable(dice_1);
                            cpuDice3eye = 1;
                            break;
                        case 1:
                            cpuDice3.setImageDrawable(dice_2);
                            cpuDice3eye = 2;
                            break;
                        case 2:
                            cpuDice3.setImageDrawable(dice_3);
                            cpuDice3eye = 3;
                            break;
                        case 3:
                            cpuDice3.setImageDrawable(dice_4);
                            cpuDice3eye = 4;
                            break;
                        case 4:
                            cpuDice3.setImageDrawable(dice_5);
                            cpuDice3eye = 5;
                            break;
                        case 5:
                            cpuDice3.setImageDrawable(dice_6);
                            cpuDice3eye = 6;
                            break;
                    }
                }

                Random rnd4 = new Random();

                // dice4 랜덤
                int roll4 = rnd4.nextInt(6);

                if (!cpuDice4Keep_move) {
                    switch (roll4) {
                        case 0:
                            cpuDice4.setImageDrawable(dice_1);
                            cpuDice4eye = 1;
                            break;
                        case 1:
                            cpuDice4.setImageDrawable(dice_2);
                            cpuDice4eye = 2;
                            break;
                        case 2:
                            cpuDice4.setImageDrawable(dice_3);
                            cpuDice4eye = 3;
                            break;
                        case 3:
                            cpuDice4.setImageDrawable(dice_4);
                            cpuDice4eye = 4;
                            break;
                        case 4:
                            cpuDice4.setImageDrawable(dice_5);
                            cpuDice4eye = 5;
                            break;
                        case 5:
                            cpuDice4.setImageDrawable(dice_6);
                            cpuDice4eye = 6;
                            break;
                    }
                }

                Random rnd5 = new Random();

                // dice5 랜덤
                int roll5 = rnd5.nextInt(6);

                if (!cpuDice5Keep_move) {
                    switch (roll5) {
                        case 0:
                            cpuDice5.setImageDrawable(dice_1);
                            cpuDice5eye = 1;
                            break;
                        case 1:
                            cpuDice5.setImageDrawable(dice_2);
                            cpuDice5eye = 2;
                            break;
                        case 2:
                            cpuDice5.setImageDrawable(dice_3);
                            cpuDice5eye = 3;
                            break;
                        case 3:
                            cpuDice5.setImageDrawable(dice_4);
                            cpuDice5eye = 4;
                            break;
                        case 4:
                            cpuDice5.setImageDrawable(dice_5);
                            cpuDice5eye = 5;
                            break;
                        case 5:
                            cpuDice5.setImageDrawable(dice_6);
                            cpuDice5eye = 6;
                            break;
                    }
                }

            }
        });

        animatorSet.start();

        Log.e(TAG, "cpuDice sum : " + cpuDice1eye + cpuDice2eye + cpuDice3eye + cpuDice4eye + cpuDice5eye);
    }

    // cpu의 주사위 점수 계산
    public void cpuCalculation(){
        Log.e(TAG,"cpu_roll ca: " + cpu_roll);
        SharedPreferences.Editor editor = sharedPreferences2.edit();

        // 야추가 맞았을 때 점수넣기
        Log.e(TAG,"yachtzee() : " + yachtzee());
        if (yachtzee() && !cpu_YACHTZEE) {
            Log.e(TAG,"YACHTZEE계산에 진입");
            int cpu_YACHTZEEScore = 50;

            editor.putString("cpu_YACHTZEE",String.valueOf(cpu_YACHTZEEScore));
            editor.commit();
            cpuDiceKeepEnd();

        // 야추가 안맞을 때, LS가 맞았을 때 계산
        } else if (!yachtzee() || cpu_YACHTZEE) {
            Log.e(TAG,"LS() : " + largeStraight());

            if (largeStraight() && !cpu_LS) {
                Log.e(TAG,"LS계산에 진입");
                int cpu_LSScore = 40;

                editor.putString("cpu_LS",String.valueOf(cpu_LSScore));
                editor.commit();
                cpuDiceKeepEnd();

            // LS가 안맞을 때, SS가 맞았을 때 계산
            } else if (!largeStraight() || cpu_LS) {
                Log.e(TAG,"SS() : " + smallStraight());

                if (smallStraight() && !cpu_SS) {

                    Log.e(TAG,"SS계산에 진입");

                    // 굴릴 기회가 남지 않았을 때 점수넣기
                    if (cpu_roll >= 3) {
                        Log.e(TAG,"SS에 점수 들어감");
                        int cpu_SSScore = 30;

                        editor.putString("cpu_SS",String.valueOf(cpu_SSScore));
                        editor.commit();
                        cpuDiceKeepEnd();

                        // 굴릴 기회가 남았으면 맞은 주사위 킵하고 나머지 주사위 굴리기
                    }else {
                        Log.e(TAG,"SS 굴릴기회 남았으니까 굴려");
                        SS_Calculation();
                        cpuDiceKeep();
                        cpu_rolldice();
                    }

                    // SS가 안맞을 때, FAOK 가 맞았을 때 계산
                } else if (!smallStraight() || cpu_SS) {
                    Log.e(TAG,"FOAK() : " + fourEyeCheck());
                    if (fourEyeCheck() && !cpu_FOAK) {

                        Log.e(TAG,"FOAK에 진입");

                        // 굴릴 기회가 남지 않았을 때 점수넣기
                        if (cpu_roll >= 3) {

                            int cpu_FOAKScore = cpuDice1eye + cpuDice2eye + cpuDice3eye + cpuDice4eye + cpuDice5eye;

                            editor.putString("cpu_FOAK",String.valueOf(cpu_FOAKScore));
                            editor.commit();
                            cpuDiceKeepEnd();

                            // 굴릴 기회가 남았으면 맞은 주사위 킵하고 나머지 주사위 굴리기
                        }else {
                            Log.e(TAG,"FOAK 굴릴 기회 남았으니까 굴려");
                            FOAK_Calculation();
                            cpuDiceKeep();
                            cpu_rolldice();
                        }

                        // FOAK 가 안맞을 때, FH 가 맞았을 때 계산
                    } else if (!fourEyeCheck() || cpu_FOAK) {
                        Log.e(TAG,"FH() : " + fullHouseEyeCheck());
                        if (fullHouseEyeCheck() && !cpu_FH) {

                            Log.e(TAG,"FH에 진입");

                            int cpu_FHScore = 25;

                            editor.putString("cpu_FH",String.valueOf(cpu_FHScore));
                            editor.commit();
                            cpuDiceKeepEnd();

                            // FH 가 안맞을 때, TOAK 가 맞았을 때 계산
                        } else if (!fullHouseEyeCheck() || cpu_FH) {

                            Log.e(TAG,"TOAK() : " + threeEyeCheck());
                            if (threeEyeCheck() && !cpu_TOAK) {

                                Log.e(TAG,"TOAK에 진입");

                                // 굴릴 기회가 남지 않았을 때 점수넣기
                                if (cpu_roll >= 3) {

                                    int cpu_TOAKScore = cpuDice1eye + cpuDice2eye + cpuDice3eye + cpuDice4eye + cpuDice5eye;
                                    Log.e(TAG,"cpu_TOAK 값 : " + cpu_TOAKScore);
                                    editor.putString("cpu_TOAK",String.valueOf(cpu_TOAKScore));
                                    editor.commit();
                                    cpuDiceKeepEnd();

                                    // 굴릴 기회가 남았으면 맞은 주사위 킵하고 나머지 주사위 굴리기
                                }else {
                                    Log.e(TAG,"TOAK 굴릴 기회 남앗으니까 굴려");
                                    TOAK_Calculation();
                                    cpuDiceKeep();
                                    cpu_rolldice();
                                }

                                // TOAK 가 안맞을 때, 6이 2개 이상일 때 계산
                            } else if (!threeEyeCheck() || cpu_TOAK) {
                                Log.e(TAG,"Sixes() : " + sixesEyeCheck());
                                if (sixesEyeCheck() && !cpu_Sixes) {

                                    Log.e(TAG,"Sixes에 진입");

                                    // 굴릴 기회가 남지 않았을 때 점수넣기
                                    if (cpu_roll >= 3) {

                                        if (cpuDice1eye != 6) {
                                            cpuDice1eye = 0;
                                        }else {
                                            cpuDice1eye = 6;
                                        }

                                        if (cpuDice2eye != 6){
                                            cpuDice2eye = 0;
                                        }else{
                                            cpuDice2eye = 6;
                                        }

                                        if (cpuDice3eye != 6) {
                                            cpuDice3eye = 0;
                                        }else {
                                            cpuDice3eye = 6;
                                        }

                                        if (cpuDice4eye != 6) {
                                            cpuDice4eye = 0;
                                        }else {
                                            cpuDice4eye = 6;
                                        }

                                        if (cpuDice5eye != 6) {
                                            cpuDice5eye = 0;
                                        }else{
                                            cpuDice5eye = 6;
                                        }

                                        int cpu_SixesScore = cpuDice1eye + cpuDice2eye + cpuDice3eye + cpuDice4eye + cpuDice5eye;

                                        editor.putString("cpu_Sixes",String.valueOf(cpu_SixesScore));
                                        editor.commit();
                                        cpuDiceKeepEnd();

                                        // 굴릴 기회가 남앗으면 맞은 주사위 킵하고 나머지 주사위 굴리기
                                    }else {
                                        Log.e(TAG,"Sixes 굴릴기회 남앗으니까 굴려");
                                        Sixes_Calculation();
                                        cpuDiceKeep();
                                        cpu_rolldice();
                                    }

                                    // Sixes 가 안맞을 때, 5가 2개이상일 때 계산
                                } else if (!sixesEyeCheck() || cpu_Sixes) {
                                    Log.e(TAG,"Fives" + fivesEyeCheck());

                                    if (fivesEyeCheck() && !cpu_Fives) {
                                        Log.e(TAG,"Fives에 진입");

                                        // 굴릴 기회가 남지 않았을 때 점수 넣기
                                        if (cpu_roll >= 3) {

                                            if (cpuDice1eye != 5) {
                                                cpuDice1eye = 0;
                                            }else {
                                                cpuDice1eye = 5;
                                            }

                                            if (cpuDice2eye != 5){
                                                cpuDice2eye = 0;
                                            }else{
                                                cpuDice2eye = 5;
                                            }

                                            if (cpuDice3eye != 5) {
                                                cpuDice3eye = 0;
                                            }else {
                                                cpuDice3eye = 5;
                                            }

                                            if (cpuDice4eye != 5) {
                                                cpuDice4eye = 0;
                                            }else {
                                                cpuDice4eye = 5;
                                            }

                                            if (cpuDice5eye != 5) {
                                                cpuDice5eye = 0;
                                            }else{
                                                cpuDice5eye = 5;
                                            }

                                            int cpu_FivesScore = cpuDice1eye + cpuDice2eye + cpuDice3eye + cpuDice4eye + cpuDice5eye;

                                            editor.putString("cpu_Fives",String.valueOf(cpu_FivesScore));
                                            editor.commit();
                                            cpuDiceKeepEnd();

                                            // 굴릴 기회가 남앗으면 맞은 주사위 킵하고 나머지 주사위 굴리기
                                        }else {
                                            Log.e(TAG,"Fives 굴릴기회 남앗으니까 굴려");
                                            Fives_Calculation();
                                            cpuDiceKeep();
                                            cpu_rolldice();
                                        }

                                        // Fives 가 안맞을 때, 4가 2개이상일 때 계산
                                    }else if (!fivesEyeCheck() || cpu_Fives){
                                        Log.e(TAG,"Fours" + foursEyeCheck());

                                        if (foursEyeCheck() && !cpu_Fours) {
                                            Log.e(TAG,"Fours 에 진입");

                                            // 굴릴 기회가 남지 않았을 때 점수넣기
                                            if (cpu_roll >= 3) {

                                                if (cpuDice1eye != 4) {
                                                    cpuDice1eye = 0;
                                                } else {
                                                    cpuDice1eye = 4;
                                                }

                                                if (cpuDice2eye != 4) {
                                                    cpuDice2eye = 0;
                                                } else {
                                                    cpuDice2eye = 4;
                                                }

                                                if (cpuDice3eye != 4) {
                                                    cpuDice3eye = 0;
                                                } else {
                                                    cpuDice3eye = 4;
                                                }

                                                if (cpuDice4eye != 4) {
                                                    cpuDice4eye = 0;
                                                } else {
                                                    cpuDice4eye = 4;
                                                }

                                                if (cpuDice5eye != 4) {
                                                    cpuDice5eye = 0;
                                                } else {
                                                    cpuDice5eye = 4;
                                                }

                                                int cpu_FoursScore = cpuDice1eye + cpuDice2eye + cpuDice3eye + cpuDice4eye + cpuDice5eye;

                                                editor.putString("cpu_Fours", String.valueOf(cpu_FoursScore));
                                                editor.commit();
                                                cpuDiceKeepEnd();

                                                // 굴릴 기회가 남앗으면 맞은 주사위 킵하고 나머지 주사위 굴리기
                                            }else{
                                                Log.e(TAG,"Fours 굴릴기회 남앗으니까 굴려");
                                                Fours_Calculation();
                                                cpuDiceKeep();
                                                cpu_rolldice();
                                            }

                                            // Fours 가 안맞았을 때, 3이 2개이상일 때
                                        } else if (!foursEyeCheck() || cpu_Fours) {
                                            Log.e(TAG,"Threes" + threesEyeCheck());

                                            if (threesEyeCheck() && !cpu_Threes) {
                                                Log.e(TAG,"Threes 에 진입");

                                                // 굴릴 기회가 남지 않았을 때 점수넣기
                                                if (cpu_roll >= 3) {

                                                    if (cpuDice1eye != 3) {
                                                        cpuDice1eye = 0;
                                                    } else {
                                                        cpuDice1eye = 3;
                                                    }

                                                    if (cpuDice2eye != 3) {
                                                        cpuDice2eye = 0;
                                                    } else {
                                                        cpuDice2eye = 3;
                                                    }

                                                    if (cpuDice3eye != 3) {
                                                        cpuDice3eye = 0;
                                                    } else {
                                                        cpuDice3eye = 3;
                                                    }

                                                    if (cpuDice4eye != 3) {
                                                        cpuDice4eye = 0;
                                                    } else {
                                                        cpuDice4eye = 3;
                                                    }

                                                    if (cpuDice5eye != 3) {
                                                        cpuDice5eye = 0;
                                                    } else {
                                                        cpuDice5eye = 3;
                                                    }

                                                    int cpu_ThreesScore = cpuDice1eye + cpuDice2eye + cpuDice3eye + cpuDice4eye + cpuDice5eye;

                                                    editor.putString("cpu_Threes", String.valueOf(cpu_ThreesScore));
                                                    editor.commit();
                                                    cpuDiceKeepEnd();

                                                    // 굴릴 기회가 남았으면 맞은 주사위 킵하고 나머지 주사위 굴리기
                                                }else{
                                                    Log.e(TAG,"Threes 굴릴기회 남앗으니까 굴려");
                                                    Threes_Calculation();
                                                    cpuDiceKeep();
                                                    cpu_rolldice();
                                                }

                                                // Threes 가 안맞았을 때, 2가 2개 이상일 때
                                            } else if (!threesEyeCheck() || cpu_Threes) {
                                                Log.e(TAG,"Twos" + twosEyeCheck());

                                                if (twosEyeCheck() && !cpu_Twos) {
                                                    Log.e(TAG,"Twos 에 진입");

                                                    // 굴릴 기회가 남지 않았을 때 점수넣기
                                                    if (cpu_roll >= 3) {

                                                        if (cpuDice1eye != 2) {
                                                            cpuDice1eye = 0;
                                                        } else {
                                                            cpuDice1eye = 2;
                                                        }

                                                        if (cpuDice2eye != 2) {
                                                            cpuDice2eye = 0;
                                                        } else {
                                                            cpuDice2eye = 2;
                                                        }

                                                        if (cpuDice3eye != 2) {
                                                            cpuDice3eye = 0;
                                                        } else {
                                                            cpuDice3eye = 2;
                                                        }

                                                        if (cpuDice4eye != 2) {
                                                            cpuDice4eye = 0;
                                                        } else {
                                                            cpuDice4eye = 2;
                                                        }

                                                        if (cpuDice5eye != 2) {
                                                            cpuDice5eye = 0;
                                                        } else {
                                                            cpuDice5eye = 2;
                                                        }

                                                        int cpu_TwosScore = cpuDice1eye + cpuDice2eye + cpuDice3eye + cpuDice4eye + cpuDice5eye;

                                                        editor.putString("cpu_Twos", String.valueOf(cpu_TwosScore));
                                                        editor.commit();
                                                        cpuDiceKeepEnd();

                                                        // 굴릴 기회가 남앗으면 맞은 주사위 킵하고 나머지 주사위 굴리기
                                                    }else{
                                                        Log.e(TAG,"Twos 굴릴 기회 남았으니까 굴려");
                                                        Twos_Calculation();
                                                        cpuDiceKeep();
                                                        cpu_rolldice();
                                                    }

                                                    // Twos 가 안맞았을 때, 1이 2개 이상일 때
                                                } else if (!twosEyeCheck() || cpu_Twos) {
                                                    Log.e(TAG,"Aces" + acesEyeCheck());

                                                    if (acesEyeCheck() && !cpu_Aces) {
                                                        Log.e(TAG,"Aces 에 진입");

                                                        // 굴릴 기회가 남지 않았을 때 점수넣기
                                                        if (cpu_roll >= 3) {

                                                            if (cpuDice1eye != 1) {
                                                                cpuDice1eye = 0;
                                                            } else {
                                                                cpuDice1eye = 1;
                                                            }

                                                            if (cpuDice2eye != 1) {
                                                                cpuDice2eye = 0;
                                                            } else {
                                                                cpuDice2eye = 1;
                                                            }

                                                            if (cpuDice3eye != 1) {
                                                                cpuDice3eye = 0;
                                                            } else {
                                                                cpuDice3eye = 1;
                                                            }

                                                            if (cpuDice4eye != 1) {
                                                                cpuDice4eye = 0;
                                                            } else {
                                                                cpuDice4eye = 1;
                                                            }

                                                            if (cpuDice5eye != 1) {
                                                                cpuDice5eye = 0;
                                                            } else {
                                                                cpuDice5eye = 1;
                                                            }

                                                            int cpu_AcesScore = cpuDice1eye + cpuDice2eye + cpuDice3eye + cpuDice4eye + cpuDice5eye;

                                                            editor.putString("cpu_Aces", String.valueOf(cpu_AcesScore));
                                                            editor.commit();
                                                            cpuDiceKeepEnd();

                                                            // 굴릴 기회가 남았으면 맞은 주사위 킵하고 나머지 주사위 굴리기
                                                        }else{
                                                            Log.e(TAG,"Aces 굴릴 기회 남앗으니까 굴려");
                                                            Aces_Calculation();
                                                            cpuDiceKeep();
                                                            cpu_rolldice();
                                                        }

                                                        // Aces 가 안맞앗을 때
                                                    } else if (!acesEyeCheck() || cpu_Aces) {

                                                        // 굴릴 기회가 남지 않았을 때 점수넣기
                                                        if (cpu_roll >= 3) {

                                                            // Chance 가 비어있을 때
                                                            if (!cpu_Chance) {
                                                                int cpu_ChanceScore = cpuDice1eye + cpuDice2eye + cpuDice3eye + cpuDice4eye + cpuDice5eye;
                                                                editor.putString("cpu_Chance",String.valueOf(cpu_ChanceScore));
                                                                editor.commit();
                                                                cpuDiceKeepEnd();

                                                                // Chance 가 있고, Aces 가 없을 때
                                                            } else if (cpu_Chance && !cpu_Aces) {
                                                                if (cpuDice1eye != 1) {
                                                                    cpuDice1eye = 0;
                                                                } else {
                                                                    cpuDice1eye = 1;
                                                                }

                                                                if (cpuDice2eye != 1) {
                                                                    cpuDice2eye = 0;
                                                                } else {
                                                                    cpuDice2eye = 1;
                                                                }

                                                                if (cpuDice3eye != 1) {
                                                                    cpuDice3eye = 0;
                                                                } else {
                                                                    cpuDice3eye = 1;
                                                                }

                                                                if (cpuDice4eye != 1) {
                                                                    cpuDice4eye = 0;
                                                                } else {
                                                                    cpuDice4eye = 1;
                                                                }

                                                                if (cpuDice5eye != 1) {
                                                                    cpuDice5eye = 0;
                                                                } else {
                                                                    cpuDice5eye = 1;
                                                                }

                                                                int cpu_AcesScore = cpuDice1eye + cpuDice2eye + cpuDice3eye + cpuDice4eye + cpuDice5eye;
                                                                editor.putString("cpu_Aces",String.valueOf(cpu_AcesScore));
                                                                editor.commit();
                                                                cpuDiceKeepEnd();

                                                                // Aces 가 있고, YACHTZEE 가 없을 때
                                                            } else if (cpu_Aces && !cpu_YACHTZEE) {
                                                                int cpu_YACHTZEEScore = 0;
                                                                editor.putString("cpu_YACHTZEE",String.valueOf(cpu_YACHTZEEScore));
                                                                editor.commit();
                                                                cpuDiceKeepEnd();

                                                                // YACHTZEE 가 있고, Twos 가 없을 때
                                                            } else if (cpu_YACHTZEE && !cpu_Twos) {
                                                                if (cpuDice1eye != 2) {
                                                                    cpuDice1eye = 0;
                                                                } else {
                                                                    cpuDice1eye = 2;
                                                                }

                                                                if (cpuDice2eye != 2) {
                                                                    cpuDice2eye = 0;
                                                                } else {
                                                                    cpuDice2eye = 2;
                                                                }

                                                                if (cpuDice3eye != 2) {
                                                                    cpuDice3eye = 0;
                                                                } else {
                                                                    cpuDice3eye = 2;
                                                                }

                                                                if (cpuDice4eye != 2) {
                                                                    cpuDice4eye = 0;
                                                                } else {
                                                                    cpuDice4eye = 2;
                                                                }

                                                                if (cpuDice5eye != 2) {
                                                                    cpuDice5eye = 0;
                                                                } else {
                                                                    cpuDice5eye = 2;
                                                                }

                                                                int cpu_TwosScore = cpuDice1eye + cpuDice2eye + cpuDice3eye + cpuDice4eye + cpuDice5eye;
                                                                editor.putString("cpu_Twos",String.valueOf(cpu_TwosScore));
                                                                editor.commit();
                                                                cpuDiceKeepEnd();

                                                                // Twos 가 있고, FOAK 가 없을 때
                                                            } else if (cpu_Twos && !cpu_FOAK) {
                                                                int cpu_FOAKScore = 0;
                                                                editor.putString("cpu_FOAK",String.valueOf(cpu_FOAKScore));
                                                                editor.commit();
                                                                cpuDiceKeepEnd();

                                                                // FOAK 가 있고, LS 가 없을 때
                                                            } else if (cpu_FOAK && !cpu_LS) {
                                                                int cpu_LSScore = 0;
                                                                editor.putString("cpu_LS",String.valueOf(cpu_LSScore));
                                                                editor.commit();
                                                                cpuDiceKeepEnd();

                                                                // LS 가 있고, Threes 가 없을 때
                                                            }else if (cpu_LS && !cpu_Threes){
                                                                if (cpuDice1eye != 3) {
                                                                    cpuDice1eye = 0;
                                                                } else {
                                                                    cpuDice1eye = 3;
                                                                }

                                                                if (cpuDice2eye != 3) {
                                                                    cpuDice2eye = 0;
                                                                } else {
                                                                    cpuDice2eye = 3;
                                                                }

                                                                if (cpuDice3eye != 3) {
                                                                    cpuDice3eye = 0;
                                                                } else {
                                                                    cpuDice3eye = 3;
                                                                }

                                                                if (cpuDice4eye != 3) {
                                                                    cpuDice4eye = 0;
                                                                } else {
                                                                    cpuDice4eye = 3;
                                                                }

                                                                if (cpuDice5eye != 3) {
                                                                    cpuDice5eye = 0;
                                                                } else {
                                                                    cpuDice5eye = 3;
                                                                }

                                                                int cpu_ThreesScore = cpuDice1eye + cpuDice2eye + cpuDice3eye + cpuDice4eye + cpuDice5eye;

                                                                editor.putString("cpu_Threes",String.valueOf(cpu_ThreesScore));
                                                                editor.commit();
                                                                cpuDiceKeepEnd();

                                                                // Threes 가 있고, Fours 가 없을 때
                                                            } else if (cpu_Threes && !cpu_Fours) {
                                                                if (cpuDice1eye != 4) {
                                                                    cpuDice1eye = 0;
                                                                } else {
                                                                    cpuDice1eye = 4;
                                                                }

                                                                if (cpuDice2eye != 4) {
                                                                    cpuDice2eye = 0;
                                                                } else {
                                                                    cpuDice2eye = 4;
                                                                }

                                                                if (cpuDice3eye != 4) {
                                                                    cpuDice3eye = 0;
                                                                } else {
                                                                    cpuDice3eye = 4;
                                                                }

                                                                if (cpuDice4eye != 4) {
                                                                    cpuDice4eye = 0;
                                                                } else {
                                                                    cpuDice4eye = 4;
                                                                }

                                                                if (cpuDice5eye != 4) {
                                                                    cpuDice5eye = 0;
                                                                } else {
                                                                    cpuDice5eye = 4;
                                                                }

                                                                int cpu_FoursScore = cpuDice1eye + cpuDice2eye + cpuDice3eye + cpuDice4eye + cpuDice5eye;

                                                                editor.putString("cpu_Fours", String.valueOf(cpu_FoursScore));
                                                                editor.commit();
                                                                cpuDiceKeepEnd();

                                                                // Fours 가 있고, Fives 가 없을 때
                                                            } else if (cpu_Fours && !cpu_Fives) {
                                                                if (cpuDice1eye != 5) {
                                                                    cpuDice1eye = 0;
                                                                }else {
                                                                    cpuDice1eye = 5;
                                                                }

                                                                if (cpuDice2eye != 5){
                                                                    cpuDice2eye = 0;
                                                                }else{
                                                                    cpuDice2eye = 5;
                                                                }

                                                                if (cpuDice3eye != 5) {
                                                                    cpuDice3eye = 0;
                                                                }else {
                                                                    cpuDice3eye = 5;
                                                                }

                                                                if (cpuDice4eye != 5) {
                                                                    cpuDice4eye = 0;
                                                                }else {
                                                                    cpuDice4eye = 5;
                                                                }

                                                                if (cpuDice5eye != 5) {
                                                                    cpuDice5eye = 0;
                                                                }else{
                                                                    cpuDice5eye = 5;
                                                                }

                                                                int cpu_FivesScore = cpuDice1eye + cpuDice2eye + cpuDice3eye + cpuDice4eye + cpuDice5eye;

                                                                editor.putString("cpu_Fives",String.valueOf(cpu_FivesScore));
                                                                editor.commit();
                                                                cpuDiceKeepEnd();

                                                                // Fives 가 있고, Sixes 가 없을 때
                                                            } else if (cpu_Fives && !cpu_Sixes) {
                                                                if (cpuDice1eye != 6) {
                                                                    cpuDice1eye = 0;
                                                                }else {
                                                                    cpuDice1eye = 6;
                                                                }

                                                                if (cpuDice2eye != 6){
                                                                    cpuDice2eye = 0;
                                                                }else{
                                                                    cpuDice2eye = 6;
                                                                }

                                                                if (cpuDice3eye != 6) {
                                                                    cpuDice3eye = 0;
                                                                }else {
                                                                    cpuDice3eye = 6;
                                                                }

                                                                if (cpuDice4eye != 6) {
                                                                    cpuDice4eye = 0;
                                                                }else {
                                                                    cpuDice4eye = 6;
                                                                }

                                                                if (cpuDice5eye != 6) {
                                                                    cpuDice5eye = 0;
                                                                }else{
                                                                    cpuDice5eye = 6;
                                                                }

                                                                int cpu_SixesScore = cpuDice1eye + cpuDice2eye + cpuDice3eye + cpuDice4eye + cpuDice5eye;

                                                                editor.putString("cpu_Sixes",String.valueOf(cpu_SixesScore));
                                                                editor.commit();
                                                                cpuDiceKeepEnd();

                                                                // Sixes 가 있고, FH 가 없을 때
                                                            } else if (cpu_Sixes && !cpu_FH) {
                                                                int cpu_FHScore = 0;
                                                                editor.putString("cpu_FH",String.valueOf(cpu_FHScore));
                                                                editor.commit();
                                                                cpuDiceKeepEnd();

                                                                // FH 가 있고, SL 이 없을 때
                                                            } else if (cpu_FH && !cpu_SS) {
                                                                int cpu_SSScore = 0;
                                                                editor.putString("cpu_SS",String.valueOf(cpu_SSScore));
                                                                editor.commit();
                                                                cpuDiceKeepEnd();

                                                                // SL 이 있고, TOAK 가 없을 때
                                                            } else if (cpu_SS && !cpu_TOAK) {
                                                                int cpu_TOAKScore = 0;
                                                                editor.putString("cpu_TOAK",String.valueOf(cpu_TOAKScore));
                                                                editor.commit();
                                                                cpuDiceKeepEnd();
                                                            }

                                                            // 굴릴 기회가 남앗으면 주사위 굴리기
                                                       }else {
                                                            Log.e(TAG,"해당하는 값이 없음");
                                                            cpu_rolldice();
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }

                                }
                            }
                        }
                    }
                }
            }
        }
    }

    // Sixes 가 주사위 눈 두개이상 같을 때
    public boolean sixesEyeCheck(){

        if (cpuDice1eye == 6 && cpuDice2eye == 6 || cpuDice1eye == 6 && cpuDice3eye == 6
        || cpuDice1eye == 6 && cpuDice4eye == 6 || cpuDice1eye == 6 && cpuDice5eye == 6
        || cpuDice2eye == 6 && cpuDice3eye == 6 || cpuDice2eye == 6 && cpuDice4eye == 6
        || cpuDice2eye == 6 && cpuDice5eye == 6
        || cpuDice3eye == 6 && cpuDice4eye == 6 || cpuDice3eye == 6 && cpuDice5eye == 6
        || cpuDice4eye == 6 && cpuDice5eye == 6) {
            return true;
        }

        return false;
    }

    public void Sixes_Calculation(){

        if (cpuDice1eye == 6) {
            cpuDice1Keep_move = true;
            if (cpuDice2eye == 6) {
                cpuDice2Keep_move = true;
            } else if (cpuDice3eye == 6) {
                cpuDice3Keep_move = true;
            } else if (cpuDice4eye == 6) {
                cpuDice4Keep_move = true;
            } else if (cpuDice5eye == 6) {
                cpuDice5Keep_move = true;
            }
        } else if (cpuDice2eye == 6) {
            cpuDice2Keep_move = true;
            if (cpuDice3eye == 6) {
                cpuDice3Keep_move = true;
            } else if (cpuDice4eye == 6) {
                cpuDice4Keep_move = true;
            } else if (cpuDice5eye == 6) {
                cpuDice5Keep_move = true;
            }
        } else if (cpuDice3eye == 6) {
            cpuDice3Keep_move = true;
            if (cpuDice4eye == 6) {
                cpuDice4Keep_move = true;
            } else if (cpuDice5eye == 6) {
                cpuDice5Keep_move = true;
            }
        } else if (cpuDice4eye == 6) {
            cpuDice4Keep_move = true;
            if (cpuDice5eye == 6) {
                cpuDice5Keep_move = true;
            }
        }

    }

    // Fives 가 주사위 눈 두개이상 같을 때
    public boolean fivesEyeCheck(){
        if (cpuDice1eye == 5 && cpuDice2eye == 5 || cpuDice1eye == 5 && cpuDice3eye == 5
                || cpuDice1eye == 5 && cpuDice4eye == 5 || cpuDice1eye == 5 && cpuDice5eye == 5
                || cpuDice2eye == 5 && cpuDice3eye == 5 || cpuDice2eye == 5 && cpuDice4eye == 5
                || cpuDice2eye == 5 && cpuDice5eye == 5
                || cpuDice3eye == 5 && cpuDice4eye == 5 || cpuDice3eye == 5 && cpuDice5eye == 5
                || cpuDice4eye == 5 && cpuDice5eye == 5) {
            return true;
        }

        return false;
    }

    public void Fives_Calculation(){
        if (cpuDice1eye == 5) {
            cpuDice1Keep_move = true;
            if (cpuDice2eye == 5) {
                cpuDice2Keep_move = true;
            } else if (cpuDice3eye == 5) {
                cpuDice3Keep_move = true;
            } else if (cpuDice4eye == 5) {
                cpuDice4Keep_move = true;
            } else if (cpuDice5eye == 5) {
                cpuDice5Keep_move = true;
            }
        } else if (cpuDice2eye == 5) {
            cpuDice2Keep_move = true;
            if (cpuDice3eye == 5) {
                cpuDice3Keep_move = true;
            } else if (cpuDice4eye == 5) {
                cpuDice4Keep_move = true;
            } else if (cpuDice5eye == 5) {
                cpuDice5Keep_move = true;
            }
        } else if (cpuDice3eye == 5) {
            cpuDice3Keep_move = true;
            if (cpuDice4eye == 5) {
                cpuDice4Keep_move = true;
            } else if (cpuDice5eye == 5) {
                cpuDice5Keep_move = true;
            }
        } else if (cpuDice4eye == 5) {
            cpuDice4Keep_move = true;
            if (cpuDice5eye == 5) {
                cpuDice5Keep_move = true;
            }
        }
    }

    // Fours 가 주사위 눈 두개이상 같을 때
    public boolean foursEyeCheck(){
        if (cpuDice1eye == 4 && cpuDice2eye == 4 || cpuDice1eye == 4 && cpuDice3eye == 4
                || cpuDice1eye == 4 && cpuDice4eye == 4 || cpuDice1eye == 4 && cpuDice4eye == 4
                || cpuDice2eye == 4 && cpuDice3eye == 4 || cpuDice2eye == 4 && cpuDice4eye == 4
                || cpuDice2eye == 4 && cpuDice5eye == 4
                || cpuDice3eye == 4 && cpuDice4eye == 4 || cpuDice3eye == 4 && cpuDice5eye == 4
                || cpuDice4eye == 4 && cpuDice5eye == 4) {
            return true;
        }

        return false;
    }

    public void Fours_Calculation(){
        if (cpuDice1eye == 4) {
            cpuDice1Keep_move = true;
            if (cpuDice2eye == 4) {
                cpuDice2Keep_move = true;
            } else if (cpuDice3eye == 4) {
                cpuDice3Keep_move = true;
            } else if (cpuDice4eye == 4) {
                cpuDice4Keep_move = true;
            } else if (cpuDice5eye == 4) {
                cpuDice5Keep_move = true;
            }
        } else if (cpuDice2eye == 4) {
            cpuDice2Keep_move = true;
            if (cpuDice3eye == 4) {
                cpuDice3Keep_move = true;
            } else if (cpuDice4eye == 4) {
                cpuDice4Keep_move = true;
            } else if (cpuDice5eye == 4) {
                cpuDice5Keep_move = true;
            }
        } else if (cpuDice3eye == 4) {
            cpuDice3Keep_move = true;
            if (cpuDice4eye == 4) {
                cpuDice4Keep_move = true;
            } else if (cpuDice5eye == 4) {
                cpuDice5Keep_move = true;
            }
        } else if (cpuDice4eye == 4) {
            cpuDice4Keep_move = true;
            if (cpuDice5eye == 4) {
                cpuDice5Keep_move = true;
            }
        }
    }

    // Threes 가 주사위 눈 두개이상 같을 때
    public boolean threesEyeCheck(){
        if (cpuDice1eye == 3 && cpuDice2eye == 3 || cpuDice1eye == 3 && cpuDice3eye == 3
                || cpuDice1eye == 3 && cpuDice4eye == 3 || cpuDice1eye == 3 && cpuDice5eye == 3
                || cpuDice2eye == 3 && cpuDice3eye == 3 || cpuDice2eye == 3 && cpuDice4eye == 3
                || cpuDice2eye == 3 && cpuDice5eye == 3
                || cpuDice3eye == 3 && cpuDice4eye == 3 || cpuDice3eye == 3 && cpuDice5eye == 3
                || cpuDice4eye == 3 && cpuDice5eye == 3) {
            return true;
        }

        return false;
    }

    public void Threes_Calculation(){
        if (cpuDice1eye == 3) {
            cpuDice1Keep_move = true;
            if (cpuDice2eye == 3) {
                cpuDice2Keep_move = true;
            } else if (cpuDice3eye == 3) {
                cpuDice3Keep_move = true;
            } else if (cpuDice4eye == 3) {
                cpuDice4Keep_move = true;
            } else if (cpuDice5eye == 3) {
                cpuDice5Keep_move = true;
            }
        } else if (cpuDice2eye == 3) {
            cpuDice2Keep_move = true;
            if (cpuDice3eye == 3) {
                cpuDice3Keep_move = true;
            } else if (cpuDice4eye == 3) {
                cpuDice4Keep_move = true;
            } else if (cpuDice5eye == 3) {
                cpuDice5Keep_move = true;
            }
        } else if (cpuDice3eye == 3) {
            cpuDice3Keep_move = true;
            if (cpuDice4eye == 3) {
                cpuDice4Keep_move = true;
            } else if (cpuDice5eye == 3) {
                cpuDice5Keep_move = true;
            }
        } else if (cpuDice4eye == 3) {
            cpuDice4Keep_move = true;
            if (cpuDice5eye == 3) {
                cpuDice5Keep_move = true;
            }
        }
    }

    // Twos 가 주사위 눈 두개이상 같을 때
    public boolean twosEyeCheck(){
        if (cpuDice1eye == 2 && cpuDice2eye == 2 || cpuDice1eye == 2 && cpuDice3eye == 2
                || cpuDice1eye == 2 && cpuDice4eye == 2 || cpuDice1eye == 2 && cpuDice5eye == 2
                || cpuDice2eye == 2 && cpuDice3eye == 2 || cpuDice2eye == 2 && cpuDice4eye == 2
                || cpuDice2eye == 2 && cpuDice5eye == 2
                || cpuDice3eye == 2 && cpuDice4eye == 2 || cpuDice3eye == 2 && cpuDice5eye == 2
                || cpuDice4eye == 2 && cpuDice5eye == 2) {
            return true;
        }

        return false;
    }

    public void Twos_Calculation(){
        if (cpuDice1eye == 2) {
            cpuDice1Keep_move = true;
            if (cpuDice2eye == 2) {
                cpuDice2Keep_move = true;
            } else if (cpuDice3eye == 2) {
                cpuDice3Keep_move = true;
            } else if (cpuDice4eye == 2) {
                cpuDice4Keep_move = true;
            } else if (cpuDice5eye == 2) {
                cpuDice5Keep_move = true;
            }
        } else if (cpuDice2eye == 2) {
            cpuDice2Keep_move = true;
            if (cpuDice3eye == 2) {
                cpuDice3Keep_move = true;
            } else if (cpuDice4eye == 2) {
                cpuDice4Keep_move = true;
            } else if (cpuDice5eye == 2) {
                cpuDice5Keep_move = true;
            }
        } else if (cpuDice3eye == 2) {
            cpuDice3Keep_move = true;
            if (cpuDice4eye == 2) {
                cpuDice4Keep_move = true;
            } else if (cpuDice5eye == 2) {
                cpuDice5Keep_move = true;
            }
        } else if (cpuDice4eye == 2) {
            cpuDice4Keep_move = true;
            if (cpuDice5eye == 2) {
                cpuDice5Keep_move = true;
            }
        }
    }

    // Aces 가 주사위 눈 두개이상 같을 때
    public boolean acesEyeCheck(){
        if (cpuDice1eye == 1 && cpuDice2eye == 1 || cpuDice1eye == 1 && cpuDice3eye == 1
                || cpuDice1eye == 1 && cpuDice4eye == 1 || cpuDice1eye == 1 && cpuDice5eye == 1
                || cpuDice2eye == 1 && cpuDice3eye == 1 || cpuDice2eye == 1 && cpuDice4eye == 1
                || cpuDice2eye == 1 && cpuDice5eye == 1
                || cpuDice3eye == 1 && cpuDice4eye == 1 || cpuDice3eye == 1 && cpuDice5eye == 1
                || cpuDice4eye == 1 && cpuDice5eye == 1) {
            return true;
        }

        return false;
    }

    public void Aces_Calculation(){
        if (cpuDice1eye == 1) {
            cpuDice1Keep_move = true;
            if (cpuDice2eye == 1) {
                cpuDice2Keep_move = true;
            } else if (cpuDice3eye == 1) {
                cpuDice3Keep_move = true;
            } else if (cpuDice4eye == 1) {
                cpuDice4Keep_move = true;
            } else if (cpuDice5eye == 1) {
                cpuDice5Keep_move = true;
            }
        } else if (cpuDice2eye == 1) {
            cpuDice2Keep_move = true;
            if (cpuDice3eye == 1) {
                cpuDice3Keep_move = true;
            } else if (cpuDice4eye == 1) {
                cpuDice4Keep_move = true;
            } else if (cpuDice5eye == 1) {
                cpuDice5Keep_move = true;
            }
        } else if (cpuDice3eye == 1) {
            cpuDice3Keep_move = true;
            if (cpuDice4eye == 1) {
                cpuDice4Keep_move = true;
            } else if (cpuDice5eye == 1) {
                cpuDice5Keep_move = true;
            }
        } else if (cpuDice4eye == 1) {
            cpuDice4Keep_move = true;
            if (cpuDice5eye == 1) {
                cpuDice5Keep_move = true;
            }
        }
    }

    // Three Of A Kind 에서 주사위 눈 세개이상 같을 때
    public boolean threeEyeCheck(){

        if (cpuDice2eye == cpuDice1eye && cpuDice3eye == cpuDice1eye
                || cpuDice2eye == cpuDice1eye && cpuDice4eye == cpuDice1eye
                || cpuDice2eye == cpuDice1eye && cpuDice5eye == cpuDice1eye
                || cpuDice3eye == cpuDice1eye && cpuDice4eye == cpuDice1eye
                || cpuDice3eye == cpuDice1eye && cpuDice5eye == cpuDice1eye
                || cpuDice4eye == cpuDice1eye && cpuDice5eye == cpuDice1eye

                || cpuDice1eye == cpuDice2eye && cpuDice3eye == cpuDice2eye
                || cpuDice1eye == cpuDice2eye && cpuDice4eye == cpuDice2eye
                || cpuDice1eye == cpuDice2eye && cpuDice5eye == cpuDice2eye
                || cpuDice3eye == cpuDice2eye && cpuDice4eye == cpuDice2eye
                || cpuDice3eye == cpuDice2eye && cpuDice5eye == cpuDice2eye
                || cpuDice4eye == cpuDice2eye && cpuDice5eye == cpuDice2eye

                || cpuDice1eye == cpuDice3eye && cpuDice2eye == cpuDice3eye
                || cpuDice1eye == cpuDice3eye && cpuDice4eye == cpuDice3eye
                || cpuDice1eye == cpuDice3eye && cpuDice5eye == cpuDice3eye
                || cpuDice2eye == cpuDice3eye && cpuDice4eye == cpuDice3eye
                || cpuDice2eye == cpuDice3eye && cpuDice5eye == cpuDice3eye
                || cpuDice4eye == cpuDice3eye && cpuDice5eye == cpuDice3eye

                || cpuDice1eye == cpuDice4eye && cpuDice2eye == cpuDice4eye
                || cpuDice1eye == cpuDice4eye && cpuDice3eye == cpuDice4eye
                || cpuDice1eye == cpuDice4eye && cpuDice5eye == cpuDice4eye
                || cpuDice2eye == cpuDice4eye && cpuDice3eye == cpuDice4eye
                || cpuDice2eye == cpuDice4eye && cpuDice5eye == cpuDice4eye
                || cpuDice3eye == cpuDice4eye && cpuDice5eye == cpuDice4eye

                || cpuDice1eye == cpuDice5eye && cpuDice2eye == cpuDice5eye
                || cpuDice1eye == cpuDice5eye && cpuDice3eye == cpuDice5eye
                || cpuDice1eye == cpuDice5eye && cpuDice4eye == cpuDice5eye
                || cpuDice2eye == cpuDice5eye && cpuDice3eye == cpuDice5eye
                || cpuDice2eye == cpuDice5eye && cpuDice4eye == cpuDice5eye
                || cpuDice3eye == cpuDice5eye && cpuDice4eye == cpuDice5eye){
            return true;
        }

        return false;
    }

    public void TOAK_Calculation(){

        // 주사위 123이 같을 때
        if (cpuDice1eye <= 6 && cpuDice1eye == cpuDice2eye && cpuDice2eye == cpuDice3eye) {
            cpuDice1Keep_move = true;
            cpuDice2Keep_move = true;
            cpuDice3Keep_move = true;

            // 주사위 124 가 같을 때
        }else if (cpuDice1eye <= 6 && cpuDice1eye == cpuDice2eye && cpuDice2eye == cpuDice4eye){
            cpuDice1Keep_move = true;
            cpuDice2Keep_move = true;
            cpuDice4Keep_move = true;

            // 주사위 125 가 같을 때
        }else if (cpuDice1eye <= 6 && cpuDice1eye == cpuDice2eye && cpuDice2eye == cpuDice5eye) {
            cpuDice1Keep_move = true;
            cpuDice2Keep_move = true;
            cpuDice5Keep_move = true;

            // 주사위 134 가 같을 때
        }else if (cpuDice1eye <= 6 && cpuDice1eye == cpuDice3eye && cpuDice3eye == cpuDice4eye) {
            cpuDice1Keep_move = true;
            cpuDice3Keep_move = true;
            cpuDice4Keep_move = true;

            // 주사위 135 가 같을 때
        }else if (cpuDice1eye <= 6 && cpuDice1eye == cpuDice3eye && cpuDice3eye == cpuDice5eye) {
            cpuDice1Keep_move = true;
            cpuDice3Keep_move = true;
            cpuDice5Keep_move = true;

            // 주사위 145 가 같을 때
        }else if (cpuDice1eye <= 6 && cpuDice1eye == cpuDice4eye && cpuDice4eye == cpuDice5eye) {
            cpuDice1Keep_move = true;
            cpuDice4Keep_move = true;
            cpuDice5Keep_move = true;

            // 주사위 234 가 같을 때
        }else if (cpuDice2eye <= 6 && cpuDice2eye == cpuDice3eye && cpuDice3eye == cpuDice4eye) {
            cpuDice2Keep_move = true;
            cpuDice3Keep_move = true;
            cpuDice4Keep_move = true;

            // 주사위 235 가 같을 때
        }else if (cpuDice2eye <= 6 && cpuDice2eye == cpuDice3eye && cpuDice3eye == cpuDice5eye) {
            cpuDice2Keep_move = true;
            cpuDice3Keep_move = true;
            cpuDice5Keep_move = true;

            // 주사위 245 가 같을 때
        }else if (cpuDice2eye <= 6 && cpuDice2eye == cpuDice4eye && cpuDice4eye == cpuDice5eye) {
            cpuDice2Keep_move = true;
            cpuDice4Keep_move = true;
            cpuDice5Keep_move = true;

            // 주사위 345 가 같을 때
        }else if (cpuDice3eye <= 6 && cpuDice3eye == cpuDice4eye && cpuDice4eye == cpuDice5eye) {
            cpuDice3Keep_move = true;
            cpuDice4Keep_move = true;
            cpuDice5Keep_move = true;
        }

    }

    // Four Of A Kind 에서 주사위 눈 네개이상 같을 때
    public boolean fourEyeCheck(){

        if(cpuDice2eye == cpuDice1eye && cpuDice3eye == cpuDice1eye && cpuDice4eye == cpuDice1eye
                || cpuDice2eye == cpuDice1eye && cpuDice3eye == cpuDice1eye && cpuDice5eye == cpuDice1eye
                || cpuDice2eye == cpuDice1eye && cpuDice4eye == cpuDice1eye && cpuDice5eye == cpuDice1eye
                || cpuDice3eye == cpuDice1eye && cpuDice4eye == cpuDice1eye && cpuDice5eye == cpuDice1eye

                || cpuDice1eye == cpuDice2eye && cpuDice3eye == cpuDice2eye && cpuDice4eye == cpuDice2eye
                || cpuDice1eye == cpuDice2eye && cpuDice3eye == cpuDice2eye && cpuDice5eye == cpuDice2eye
                || cpuDice1eye == cpuDice2eye && cpuDice4eye == cpuDice2eye && cpuDice5eye == cpuDice2eye
                || cpuDice3eye == cpuDice2eye && cpuDice4eye == cpuDice2eye && cpuDice5eye == cpuDice2eye

                || cpuDice1eye == cpuDice3eye && cpuDice2eye == cpuDice3eye && cpuDice4eye == cpuDice3eye
                || cpuDice1eye == cpuDice3eye && cpuDice2eye == cpuDice3eye && cpuDice5eye == cpuDice3eye
                || cpuDice1eye == cpuDice3eye && cpuDice4eye == cpuDice3eye && cpuDice5eye == cpuDice3eye
                || cpuDice2eye == cpuDice3eye && cpuDice4eye == cpuDice3eye && cpuDice5eye == cpuDice3eye

                || cpuDice1eye == cpuDice4eye && cpuDice2eye == cpuDice4eye && cpuDice3eye == cpuDice4eye
                || cpuDice1eye == cpuDice4eye && cpuDice2eye == cpuDice4eye && cpuDice5eye == cpuDice4eye
                || cpuDice1eye == cpuDice4eye && cpuDice3eye == cpuDice4eye && cpuDice5eye == cpuDice4eye
                || cpuDice2eye == cpuDice4eye && cpuDice3eye == cpuDice4eye && cpuDice5eye == cpuDice4eye

                || cpuDice1eye == cpuDice5eye && cpuDice2eye == cpuDice5eye && cpuDice3eye == cpuDice5eye
                || cpuDice1eye == cpuDice5eye && cpuDice2eye == cpuDice5eye && cpuDice4eye == cpuDice5eye
                || cpuDice1eye == cpuDice5eye && cpuDice3eye == cpuDice5eye && cpuDice4eye == cpuDice5eye
                || cpuDice2eye == cpuDice5eye && cpuDice3eye == cpuDice5eye && cpuDice4eye == cpuDice5eye){
            return true;
        }

        return false;

    }

    public void FOAK_Calculation(){

        // 주사위 1234 가 같을 때
        if (cpuDice1eye <= 6 && cpuDice1eye == cpuDice2eye && cpuDice2eye == cpuDice3eye
                && cpuDice3eye == cpuDice4eye) {
            cpuDice1Keep_move = true;
            cpuDice2Keep_move = true;
            cpuDice3Keep_move = true;
            cpuDice4Keep_move = true;

            // 주사위 1235 가 같을 때
        }else if (cpuDice1eye <= 6 && cpuDice1eye == cpuDice2eye && cpuDice2eye == cpuDice3eye
                && cpuDice3eye == cpuDice5eye){
            cpuDice1Keep_move = true;
            cpuDice2Keep_move = true;
            cpuDice3Keep_move = true;
            cpuDice5Keep_move = true;

            // 주사위 1245 가 같을 때
        }else if (cpuDice1eye <= 6 && cpuDice1eye == cpuDice2eye && cpuDice2eye == cpuDice5eye
                && cpuDice5eye == cpuDice4eye){
            cpuDice1Keep_move = true;
            cpuDice2Keep_move = true;
            cpuDice5Keep_move = true;
            cpuDice4Keep_move = true;

            // 주사위 1345 가 같을 때
        }else if (cpuDice1eye <= 6 && cpuDice1eye == cpuDice5eye && cpuDice5eye == cpuDice3eye
                && cpuDice3eye == cpuDice4eye) {
            cpuDice1Keep_move = true;
            cpuDice5Keep_move = true;
            cpuDice3Keep_move = true;
            cpuDice4Keep_move = true;

            // 주사위 2345 가 같을 때
        }else if (cpuDice5eye <= 6 && cpuDice5eye == cpuDice2eye && cpuDice2eye == cpuDice3eye
                && cpuDice3eye == cpuDice4eye) {
            cpuDice5Keep_move = true;
            cpuDice2Keep_move = true;
            cpuDice3Keep_move = true;
            cpuDice4Keep_move = true;
        }

    }

    // FullHouse 주사위 눈 체크
    public boolean fullHouseEyeCheck(){

        if        (cpuDice2eye == cpuDice1eye && cpuDice3eye == cpuDice1eye && cpuDice4eye != cpuDice1eye && cpuDice4eye == cpuDice5eye
                || cpuDice2eye == cpuDice1eye && cpuDice4eye == cpuDice1eye && cpuDice3eye != cpuDice1eye && cpuDice3eye == cpuDice5eye
                || cpuDice2eye == cpuDice1eye && cpuDice5eye == cpuDice1eye && cpuDice3eye != cpuDice1eye && cpuDice3eye == cpuDice4eye
                || cpuDice3eye == cpuDice1eye && cpuDice4eye == cpuDice1eye && cpuDice2eye != cpuDice1eye && cpuDice2eye == cpuDice5eye
                || cpuDice3eye == cpuDice1eye && cpuDice5eye == cpuDice1eye && cpuDice2eye != cpuDice1eye && cpuDice2eye == cpuDice4eye
                || cpuDice4eye == cpuDice1eye && cpuDice5eye == cpuDice1eye && cpuDice2eye != cpuDice1eye && cpuDice2eye == cpuDice3eye

                || cpuDice1eye == cpuDice2eye && cpuDice3eye == cpuDice2eye && cpuDice4eye != cpuDice2eye && cpuDice4eye == cpuDice5eye
                || cpuDice1eye == cpuDice2eye && cpuDice4eye == cpuDice2eye && cpuDice3eye != cpuDice2eye && cpuDice3eye == cpuDice5eye
                || cpuDice1eye == cpuDice2eye && cpuDice5eye == cpuDice2eye && cpuDice3eye != cpuDice2eye && cpuDice3eye == cpuDice4eye
                || cpuDice3eye == cpuDice2eye && cpuDice4eye == cpuDice2eye && cpuDice1eye != cpuDice2eye && cpuDice1eye == cpuDice5eye
                || cpuDice3eye == cpuDice2eye && cpuDice5eye == cpuDice2eye && cpuDice1eye != cpuDice2eye && cpuDice1eye == cpuDice4eye
                || cpuDice4eye == cpuDice2eye && cpuDice5eye == cpuDice2eye && cpuDice1eye != cpuDice2eye && cpuDice1eye == cpuDice3eye

                || cpuDice1eye == cpuDice3eye && cpuDice2eye == cpuDice3eye && cpuDice4eye != cpuDice3eye && cpuDice4eye == cpuDice5eye
                || cpuDice1eye == cpuDice3eye && cpuDice4eye == cpuDice3eye && cpuDice2eye != cpuDice3eye && cpuDice2eye == cpuDice5eye
                || cpuDice1eye == cpuDice3eye && cpuDice5eye == cpuDice3eye && cpuDice2eye != cpuDice3eye && cpuDice2eye == cpuDice4eye
                || cpuDice2eye == cpuDice3eye && cpuDice4eye == cpuDice3eye && cpuDice1eye != cpuDice3eye && cpuDice1eye == cpuDice5eye
                || cpuDice2eye == cpuDice3eye && cpuDice5eye == cpuDice3eye && cpuDice1eye != cpuDice3eye && cpuDice1eye == cpuDice4eye
                || cpuDice4eye == cpuDice3eye && cpuDice5eye == cpuDice3eye && cpuDice1eye != cpuDice3eye && cpuDice1eye == cpuDice2eye

                || cpuDice1eye == cpuDice4eye && cpuDice2eye == cpuDice4eye && cpuDice3eye != cpuDice4eye && cpuDice3eye == cpuDice5eye
                || cpuDice1eye == cpuDice4eye && cpuDice3eye == cpuDice4eye && cpuDice2eye != cpuDice4eye && cpuDice2eye == cpuDice5eye
                || cpuDice1eye == cpuDice4eye && cpuDice5eye == cpuDice4eye && cpuDice2eye != cpuDice4eye && cpuDice2eye == cpuDice3eye
                || cpuDice2eye == cpuDice4eye && cpuDice3eye == cpuDice4eye && cpuDice1eye != cpuDice4eye && cpuDice1eye == cpuDice5eye
                || cpuDice2eye == cpuDice4eye && cpuDice5eye == cpuDice4eye && cpuDice1eye != cpuDice4eye && cpuDice1eye == cpuDice3eye
                || cpuDice3eye == cpuDice4eye && cpuDice5eye == cpuDice4eye && cpuDice1eye != cpuDice4eye && cpuDice1eye == cpuDice2eye

                || cpuDice1eye == cpuDice5eye && cpuDice2eye == cpuDice5eye && cpuDice3eye != cpuDice5eye && cpuDice3eye == cpuDice4eye
                || cpuDice1eye == cpuDice5eye && cpuDice3eye == cpuDice5eye && cpuDice2eye != cpuDice5eye && cpuDice2eye == cpuDice4eye
                || cpuDice1eye == cpuDice5eye && cpuDice4eye == cpuDice5eye && cpuDice2eye != cpuDice5eye && cpuDice2eye == cpuDice3eye
                || cpuDice2eye == cpuDice5eye && cpuDice3eye == cpuDice5eye && cpuDice1eye != cpuDice5eye && cpuDice1eye == cpuDice4eye
                || cpuDice2eye == cpuDice5eye && cpuDice4eye == cpuDice5eye && cpuDice1eye != cpuDice5eye && cpuDice1eye == cpuDice3eye
                || cpuDice3eye == cpuDice5eye && cpuDice4eye == cpuDice5eye && cpuDice1eye != cpuDice5eye && cpuDice1eye == cpuDice2eye){
            return true;
        }

        // 주사위가 야추일때 점수 넣을수 있게 추가해야함

        return false;
    }

    // SmallStraight 주사위 눈 체크
    public boolean smallStraight(){

        if(cpuDice1eye != cpuDice2eye && cpuDice2eye != cpuDice3eye && cpuDice3eye != cpuDice4eye
                && cpuDice1eye != cpuDice3eye && cpuDice1eye != cpuDice4eye
                && cpuDice2eye != cpuDice4eye){

            if        (cpuDice1eye == 1 && cpuDice2eye <= 4 && cpuDice3eye <= 4 && cpuDice4eye <= 4
                    || cpuDice1eye == 2 && cpuDice2eye <= 5 && cpuDice3eye <= 5 && cpuDice4eye <= 5
                    && cpuDice2eye > 2 && cpuDice3eye > 2 && cpuDice4eye > 2
                    || cpuDice1eye == 3 && cpuDice2eye <= 6 && cpuDice3eye <= 6 && cpuDice4eye <= 6
                    && cpuDice2eye > 3 && cpuDice3eye > 3 && cpuDice4eye > 3

                    || cpuDice2eye == 1 && cpuDice1eye <= 4 && cpuDice3eye <= 4 && cpuDice4eye <= 4
                    || cpuDice2eye == 2 && cpuDice1eye <= 5 && cpuDice3eye <= 5 && cpuDice4eye <= 5
                    && cpuDice1eye > 2 && cpuDice3eye > 2 && cpuDice4eye > 2
                    || cpuDice2eye == 3 && cpuDice1eye <= 6 && cpuDice3eye <= 6 && cpuDice4eye <= 6
                    && cpuDice1eye > 3 && cpuDice3eye > 3 && cpuDice4eye > 3

                    || cpuDice3eye == 1 && cpuDice1eye <= 4 && cpuDice2eye <= 4 && cpuDice4eye <= 4
                    || cpuDice3eye == 2 && cpuDice1eye <= 5 && cpuDice2eye <= 5 && cpuDice4eye <= 5
                    && cpuDice2eye > 2 && cpuDice1eye > 2 && cpuDice4eye > 2
                    || cpuDice3eye == 3 && cpuDice1eye <= 6 && cpuDice2eye <= 6 && cpuDice4eye <= 6
                    && cpuDice2eye > 3 && cpuDice1eye > 3 && cpuDice4eye > 3

                    || cpuDice4eye == 1 && cpuDice1eye <= 4 && cpuDice3eye <= 4 && cpuDice2eye <= 4
                    || cpuDice4eye == 2 && cpuDice1eye <= 5 && cpuDice3eye <= 5 && cpuDice2eye <= 5
                    && cpuDice2eye > 2 && cpuDice1eye > 2 && cpuDice3eye > 2
                    || cpuDice4eye == 3 && cpuDice1eye <= 6 && cpuDice3eye <= 6 && cpuDice2eye <= 6
                    && cpuDice2eye > 3 && cpuDice1eye > 3 && cpuDice3eye > 3){

                return true;

            }

        }

        if( cpuDice1eye != cpuDice2eye && cpuDice2eye != cpuDice3eye && cpuDice3eye != cpuDice5eye
                && cpuDice1eye != cpuDice3eye && cpuDice1eye != cpuDice5eye
                && cpuDice2eye != cpuDice5eye){

            if        (cpuDice1eye == 1 && cpuDice2eye <= 4 && cpuDice3eye <= 4 && cpuDice5eye <= 4
                    || cpuDice1eye == 2 && cpuDice2eye <= 5 && cpuDice3eye <= 5 && cpuDice5eye <= 5
                    && cpuDice2eye > 2 && cpuDice3eye > 2 && cpuDice5eye > 2
                    || cpuDice1eye == 3 && cpuDice2eye <= 6 && cpuDice3eye <= 6 && cpuDice5eye <= 6
                    && cpuDice2eye > 3 && cpuDice3eye > 3 && cpuDice5eye > 3

                    || cpuDice2eye == 1 && cpuDice1eye <= 4 && cpuDice3eye <= 4 && cpuDice5eye <= 4
                    || cpuDice2eye == 2 && cpuDice1eye <= 5 && cpuDice3eye <= 5 && cpuDice5eye <= 5
                    && cpuDice1eye > 2 && cpuDice3eye > 2 && cpuDice5eye > 2
                    || cpuDice2eye == 3 && cpuDice1eye <= 6 && cpuDice3eye <= 6 && cpuDice5eye <= 6
                    && cpuDice1eye > 3 && cpuDice3eye > 3 && cpuDice5eye > 3

                    || cpuDice3eye == 1 && cpuDice1eye <= 4 && cpuDice2eye <= 4 && cpuDice5eye <= 4
                    || cpuDice3eye == 2 && cpuDice1eye <= 5 && cpuDice2eye <= 5 && cpuDice5eye <= 5
                    && cpuDice2eye > 2 && cpuDice1eye > 2 && cpuDice5eye > 2
                    || cpuDice3eye == 3 && cpuDice1eye <= 6 && cpuDice2eye <= 6 && cpuDice5eye <= 6
                    && cpuDice2eye > 3 && cpuDice1eye > 3 && cpuDice5eye > 3

                    || cpuDice5eye == 1 && cpuDice1eye <= 4 && cpuDice3eye <= 4 && cpuDice2eye <= 4
                    || cpuDice5eye == 2 && cpuDice1eye <= 5 && cpuDice3eye <= 5 && cpuDice2eye <= 5
                    && cpuDice2eye > 2 && cpuDice3eye > 2 && cpuDice1eye > 2
                    || cpuDice5eye == 3 && cpuDice1eye <= 6 && cpuDice3eye <= 6 && cpuDice2eye <= 6
                    && cpuDice2eye > 3 && cpuDice3eye > 3 && cpuDice1eye > 3){

                return true;

            }

        }

        if( cpuDice1eye != cpuDice2eye && cpuDice2eye != cpuDice4eye && cpuDice4eye != cpuDice5eye
                && cpuDice1eye != cpuDice4eye && cpuDice1eye != cpuDice5eye
                && cpuDice2eye != cpuDice5eye){

            if        (cpuDice1eye == 1 && cpuDice2eye <= 4 && cpuDice5eye <= 4 && cpuDice4eye <= 4
                    || cpuDice1eye == 2 && cpuDice2eye <= 5 && cpuDice5eye <= 5 && cpuDice4eye <= 5
                    && cpuDice2eye > 2 && cpuDice5eye > 2 && cpuDice4eye > 2
                    || cpuDice1eye == 3 && cpuDice2eye <= 6 && cpuDice5eye <= 6 && cpuDice4eye <= 6
                    && cpuDice2eye > 3 && cpuDice5eye > 3 && cpuDice4eye > 3

                    || cpuDice2eye == 1 && cpuDice1eye <= 4 && cpuDice5eye <= 4 && cpuDice4eye <= 4
                    || cpuDice2eye == 2 && cpuDice1eye <= 5 && cpuDice5eye <= 5 && cpuDice4eye <= 5
                    && cpuDice1eye > 2 && cpuDice5eye > 2 && cpuDice4eye > 2
                    || cpuDice2eye == 3 && cpuDice1eye <= 6 && cpuDice5eye <= 6 && cpuDice4eye <= 6
                    && cpuDice1eye > 3 && cpuDice5eye > 3 && cpuDice4eye > 3

                    || cpuDice5eye == 1 && cpuDice1eye <= 4 && cpuDice2eye <= 4 && cpuDice4eye <= 4
                    || cpuDice5eye == 2 && cpuDice1eye <= 5 && cpuDice2eye <= 5 && cpuDice4eye <= 5
                    && cpuDice2eye > 2 && cpuDice1eye > 2 && cpuDice4eye > 2
                    || cpuDice5eye == 3 && cpuDice1eye <= 6 && cpuDice2eye <= 6 && cpuDice4eye <= 6
                    && cpuDice2eye > 3 && cpuDice1eye > 3 && cpuDice4eye > 3

                    || cpuDice4eye == 1 && cpuDice1eye <= 4 && cpuDice5eye <= 4 && cpuDice2eye <= 4
                    || cpuDice4eye == 2 && cpuDice1eye <= 5 && cpuDice5eye <= 5 && cpuDice2eye <= 5
                    && cpuDice2eye > 2 && cpuDice5eye > 2 && cpuDice1eye > 2
                    || cpuDice4eye == 3 && cpuDice1eye <= 6 && cpuDice5eye <= 6 && cpuDice2eye <= 6
                    && cpuDice2eye > 3 && cpuDice5eye > 3 && cpuDice1eye > 3){

                return true;

            }

        }

        if( cpuDice1eye != cpuDice3eye && cpuDice3eye != cpuDice4eye && cpuDice4eye != cpuDice5eye
                && cpuDice1eye != cpuDice4eye && cpuDice1eye != cpuDice5eye
                && cpuDice3eye != cpuDice5eye){

            if        (cpuDice1eye == 1 && cpuDice5eye <= 4 && cpuDice3eye <= 4 && cpuDice4eye <= 4
                    || cpuDice1eye == 2 && cpuDice5eye <= 5 && cpuDice3eye <= 5 && cpuDice4eye <= 5
                    && cpuDice3eye > 2 && cpuDice5eye > 2 && cpuDice4eye > 2
                    || cpuDice1eye == 3 && cpuDice5eye <= 6 && cpuDice3eye <= 6 && cpuDice4eye <= 6
                    && cpuDice3eye > 3 && cpuDice5eye > 3 && cpuDice4eye > 3

                    || cpuDice5eye == 1 && cpuDice1eye <= 4 && cpuDice3eye <= 4 && cpuDice4eye <= 4
                    || cpuDice5eye == 2 && cpuDice1eye <= 5 && cpuDice3eye <= 5 && cpuDice4eye <= 5
                    && cpuDice3eye > 2 && cpuDice1eye > 2 && cpuDice4eye > 2
                    || cpuDice5eye == 3 && cpuDice1eye <= 6 && cpuDice3eye <= 6 && cpuDice4eye <= 6
                    && cpuDice3eye > 3 && cpuDice1eye > 3 && cpuDice4eye > 3

                    || cpuDice3eye == 1 && cpuDice1eye <= 4 && cpuDice5eye <= 4 && cpuDice4eye <= 4
                    || cpuDice3eye == 2 && cpuDice1eye <= 5 && cpuDice5eye <= 5 && cpuDice4eye <= 5
                    && cpuDice1eye > 2 && cpuDice5eye > 2 && cpuDice4eye > 2
                    || cpuDice3eye == 3 && cpuDice1eye <= 6 && cpuDice5eye <= 6 && cpuDice4eye <= 6
                    && cpuDice1eye > 3 && cpuDice5eye > 3 && cpuDice4eye > 3

                    || cpuDice4eye == 1 && cpuDice1eye <= 4 && cpuDice3eye <= 4 && cpuDice5eye <= 4
                    || cpuDice4eye == 2 && cpuDice1eye <= 5 && cpuDice3eye <= 5 && cpuDice5eye <= 5
                    && cpuDice3eye > 2 && cpuDice5eye > 2 && cpuDice1eye > 2
                    || cpuDice4eye == 3 && cpuDice1eye <= 6 && cpuDice3eye <= 6 && cpuDice5eye <= 6
                    && cpuDice3eye > 3 && cpuDice5eye > 3 && cpuDice1eye > 3){

                return true;

            }

        }

        if( cpuDice2eye != cpuDice3eye && cpuDice3eye != cpuDice4eye && cpuDice4eye != cpuDice5eye
                && cpuDice2eye != cpuDice4eye && cpuDice2eye != cpuDice5eye
                && cpuDice3eye != cpuDice5eye){

            if        (cpuDice5eye == 1 && cpuDice2eye <= 4 && cpuDice3eye <= 4 && cpuDice4eye <= 4
                    || cpuDice5eye == 2 && cpuDice2eye <= 5 && cpuDice3eye <= 5 && cpuDice4eye <= 5
                    && cpuDice3eye > 2 && cpuDice2eye > 2 && cpuDice4eye > 2
                    || cpuDice5eye == 3 && cpuDice2eye <= 6 && cpuDice3eye <= 6 && cpuDice4eye <= 6
                    && cpuDice3eye > 3 && cpuDice2eye > 3 && cpuDice4eye > 3

                    || cpuDice2eye == 1 && cpuDice5eye <= 4 && cpuDice3eye <= 4 && cpuDice4eye <= 4
                    || cpuDice2eye == 2 && cpuDice5eye <= 5 && cpuDice3eye <= 5 && cpuDice4eye <= 5
                    && cpuDice3eye > 2 && cpuDice5eye > 2 && cpuDice4eye > 2
                    || cpuDice2eye == 3 && cpuDice5eye <= 6 && cpuDice3eye <= 6 && cpuDice4eye <= 6
                    && cpuDice3eye > 3 && cpuDice5eye > 3 && cpuDice4eye > 3

                    || cpuDice3eye == 1 && cpuDice5eye <= 4 && cpuDice2eye <= 4 && cpuDice4eye <= 4
                    || cpuDice3eye == 2 && cpuDice5eye <= 5 && cpuDice2eye <= 5 && cpuDice4eye <= 5
                    && cpuDice5eye > 2 && cpuDice2eye > 2 && cpuDice4eye > 2
                    || cpuDice3eye == 3 && cpuDice5eye <= 6 && cpuDice2eye <= 6 && cpuDice4eye <= 6
                    && cpuDice5eye > 3 && cpuDice2eye > 3 && cpuDice4eye > 3

                    || cpuDice4eye == 1 && cpuDice5eye <= 4 && cpuDice3eye <= 4 && cpuDice2eye <= 4
                    || cpuDice4eye == 2 && cpuDice5eye <= 5 && cpuDice3eye <= 5 && cpuDice2eye <= 5
                    && cpuDice3eye > 2 && cpuDice2eye > 2 && cpuDice5eye > 2
                    || cpuDice4eye == 3 && cpuDice5eye <= 6 && cpuDice3eye <= 6 && cpuDice2eye <= 6
                    && cpuDice3eye > 3 && cpuDice2eye > 3 && cpuDice5eye > 3){

                return true;

            }

        }

        // 주사위가 야추일때 점수 넣을수 있게 해야함

        return false;
    }

    // SmallStraight 킵 체크
    public void SS_Calculation(){

        // 주사위 1234가 연속되는 숫자일 경우(1)
        if (cpuDice1eye <= 4 && cpuDice2eye <= 4 && cpuDice3eye <= 4 && cpuDice4eye <= 4
        || cpuDice1eye >= 2 && cpuDice1eye <= 5 && cpuDice2eye >= 2 && cpuDice2eye <= 5
        && cpuDice3eye >= 2 && cpuDice3eye <= 5 && cpuDice4eye >= 2 && cpuDice4eye <= 5
        || cpuDice1eye >= 3 && cpuDice1eye <= 6 && cpuDice2eye >= 3 && cpuDice2eye <= 6
        && cpuDice3eye >= 3 && cpuDice3eye <= 6 && cpuDice4eye >= 3 && cpuDice4eye <= 6){

            // 주사위 1234가 전부 달라야 함, 이 때 1234주사위 킵하기
            if (cpuDice1eye != cpuDice2eye && cpuDice2eye != cpuDice3eye && cpuDice3eye != cpuDice4eye
            && cpuDice1eye != cpuDice3eye && cpuDice1eye != cpuDice4eye && cpuDice2eye != cpuDice4eye){
                cpuDice1Keep_move = true;
                cpuDice2Keep_move = true;
                cpuDice3Keep_move = true;
                cpuDice4Keep_move = true;
            }
        } else

        // 주사위 2345가 연속되는 숫자일 경우(2)
        if (cpuDice5eye <= 4 && cpuDice2eye <= 4 && cpuDice3eye <= 4 && cpuDice4eye <= 4
                || cpuDice5eye >= 2 && cpuDice5eye <= 5 && cpuDice2eye >= 2 && cpuDice2eye <= 5
                && cpuDice3eye >= 2 && cpuDice3eye <= 5 && cpuDice4eye >= 2 && cpuDice4eye <= 5
                || cpuDice5eye >= 3 && cpuDice5eye <= 6 && cpuDice2eye >= 3 && cpuDice2eye <= 6
                && cpuDice3eye >= 3 && cpuDice3eye <= 6 && cpuDice4eye >= 3 && cpuDice4eye <= 6){

            // 주사위 2345가 전부 달라야 함, 이 때 2345주사위 킵하기
            if (cpuDice5eye != cpuDice2eye && cpuDice2eye != cpuDice3eye && cpuDice3eye != cpuDice4eye
                    && cpuDice5eye != cpuDice3eye && cpuDice5eye != cpuDice4eye && cpuDice2eye != cpuDice4eye){
                cpuDice5Keep_move = true;
                cpuDice2Keep_move = true;
                cpuDice3Keep_move = true;
                cpuDice4Keep_move = true;
            }
        } else

        // 주사위 1235가 연속되는 숫자일 경우(3)
        if (cpuDice1eye <= 4 && cpuDice2eye <= 4 && cpuDice3eye <= 4 && cpuDice5eye <= 4
                || cpuDice1eye >= 2 && cpuDice1eye <= 5 && cpuDice2eye >= 2 && cpuDice2eye <= 5
                && cpuDice3eye >= 2 && cpuDice3eye <= 5 && cpuDice5eye >= 2 && cpuDice5eye <= 5
                || cpuDice1eye >= 3 && cpuDice1eye <= 6 && cpuDice2eye >= 3 && cpuDice2eye <= 6
                && cpuDice3eye >= 3 && cpuDice3eye <= 6 && cpuDice5eye >= 3 && cpuDice5eye <= 6){

            // 주사위 1235가 전부 달라야 함, 이 때 1235주사위 킵하기
            if (cpuDice1eye != cpuDice2eye && cpuDice2eye != cpuDice3eye && cpuDice3eye != cpuDice5eye
                    && cpuDice1eye != cpuDice3eye && cpuDice1eye != cpuDice5eye && cpuDice2eye != cpuDice5eye){
                cpuDice1Keep_move = true;
                cpuDice2Keep_move = true;
                cpuDice3Keep_move = true;
                cpuDice5Keep_move = true;
            }
        } else

        // 주사위 1245가 연속되는 숫자일 경우(4)
        if (cpuDice1eye <= 4 && cpuDice2eye <= 4 && cpuDice5eye <= 4 && cpuDice4eye <= 4
                || cpuDice1eye >= 2 && cpuDice1eye <= 5 && cpuDice2eye >= 2 && cpuDice2eye <= 5
                && cpuDice5eye >= 2 && cpuDice5eye <= 5 && cpuDice4eye >= 2 && cpuDice4eye <= 5
                || cpuDice1eye >= 3 && cpuDice1eye <= 6 && cpuDice2eye >= 3 && cpuDice2eye <= 6
                && cpuDice5eye >= 3 && cpuDice5eye <= 6 && cpuDice4eye >= 3 && cpuDice4eye <= 6){

            // 주사위 1245가 전부 달라야 함, 이 때 1245주사위 킵하기
            if (cpuDice1eye != cpuDice2eye && cpuDice2eye != cpuDice5eye && cpuDice5eye != cpuDice4eye
                    && cpuDice1eye != cpuDice5eye && cpuDice1eye != cpuDice4eye && cpuDice2eye != cpuDice4eye){
                cpuDice1Keep_move = true;
                cpuDice2Keep_move = true;
                cpuDice5Keep_move = true;
                cpuDice4Keep_move = true;
            }
        } else

        // 주사위 1345가 연속되는 숫자일 경우(5)
        if (cpuDice1eye <= 4 && cpuDice5eye <= 4 && cpuDice3eye <= 4 && cpuDice4eye <= 4
                || cpuDice1eye >= 2 && cpuDice1eye <= 5 && cpuDice5eye >= 2 && cpuDice5eye <= 5
                && cpuDice3eye >= 2 && cpuDice3eye <= 5 && cpuDice4eye >= 2 && cpuDice4eye <= 5
                || cpuDice1eye >= 3 && cpuDice1eye <= 6 && cpuDice5eye >= 3 && cpuDice5eye <= 6
                && cpuDice3eye >= 3 && cpuDice3eye <= 6 && cpuDice4eye >= 3 && cpuDice4eye <= 6){

            // 주사위 1345가 전부 달라야 함, 이 때 1345주사위 킵하기
            if (cpuDice1eye != cpuDice5eye && cpuDice5eye != cpuDice3eye && cpuDice3eye != cpuDice4eye
                    && cpuDice1eye != cpuDice3eye && cpuDice1eye != cpuDice4eye && cpuDice5eye != cpuDice4eye){
                cpuDice1Keep_move = true;
                cpuDice5Keep_move = true;
                cpuDice3Keep_move = true;
                cpuDice4Keep_move = true;
            }
        }

    }

    // LargeStraight 주사위 눈 체크
    public boolean largeStraight(){

        if(cpuDice1eye != cpuDice2eye && cpuDice1eye != cpuDice3eye && cpuDice1eye != cpuDice4eye && cpuDice1eye != cpuDice5eye
                && cpuDice2eye != cpuDice3eye && cpuDice2eye != cpuDice4eye && cpuDice2eye != cpuDice5eye
                && cpuDice3eye != cpuDice4eye && cpuDice3eye != cpuDice5eye
                && cpuDice4eye != cpuDice5eye){

            if(cpuDice1eye == 1 && cpuDice2eye <= 5 && cpuDice3eye <= 5 && cpuDice4eye <= 5 && cpuDice5eye <= 5
                    || cpuDice1eye == 2 && cpuDice2eye <= 6 && cpuDice3eye <= 6 && cpuDice4eye <= 6 && cpuDice5eye <= 6
                    && cpuDice2eye > 1 && cpuDice3eye > 1 && cpuDice4eye > 1 && cpuDice5eye > 1

                    || cpuDice2eye == 1 && cpuDice1eye <= 5 && cpuDice3eye <= 5 && cpuDice4eye <= 5 && cpuDice5eye <= 5
                    || cpuDice2eye == 2 && cpuDice1eye <= 6 && cpuDice3eye <= 6 && cpuDice4eye <= 6 && cpuDice5eye <= 6
                    && cpuDice1eye > 1 && cpuDice3eye > 1 && cpuDice4eye > 1 && cpuDice5eye > 1

                    || cpuDice3eye == 1 && cpuDice2eye <= 5 && cpuDice1eye <= 5 && cpuDice4eye <= 5 && cpuDice5eye <= 5
                    || cpuDice3eye == 2 && cpuDice2eye <= 6 && cpuDice1eye <= 6 && cpuDice4eye <= 6 && cpuDice5eye <= 6
                    && cpuDice2eye > 1 && cpuDice1eye > 1 && cpuDice4eye > 1 && cpuDice5eye > 1

                    || cpuDice4eye == 1 && cpuDice2eye <= 5 && cpuDice3eye <= 5 && cpuDice1eye <= 5 && cpuDice5eye <= 5
                    || cpuDice4eye == 2 && cpuDice2eye <= 6 && cpuDice3eye <= 6 && cpuDice1eye <= 6 && cpuDice5eye <= 6
                    && cpuDice2eye > 1 && cpuDice3eye > 1 && cpuDice1eye > 1 && cpuDice5eye > 1

                    || cpuDice5eye == 1 && cpuDice2eye <= 5 && cpuDice3eye <= 5 && cpuDice4eye <= 5 && cpuDice1eye <= 5
                    || cpuDice5eye == 2 && cpuDice2eye <= 6 && cpuDice3eye <= 6 && cpuDice4eye <= 6 && cpuDice1eye <= 6
                    && cpuDice2eye > 1 && cpuDice3eye > 1 && cpuDice4eye > 1 && cpuDice1eye > 1){

                return true;

            }

        }

        // 주사위가 야추일때 점수 넣을수 있게 해야함

        return false;
    }

    // YACHTZEE 주사위 눈 체크
    public boolean yachtzee(){

        if(cpuDice1eye == cpuDice2eye && cpuDice2eye == cpuDice3eye && cpuDice3eye == cpuDice4eye && cpuDice4eye == cpuDice5eye){
            return true;
        }

        return false;
    }

    // cpu 주사위 킵하기
    public void cpuDiceKeep(){

        // 주사위 1 킵하기
        if (cpuDice1Keep_move) {
            ObjectAnimator animaX_dice1 = ObjectAnimator.ofFloat(cpuDice1,"translationX",cpuKeepDice1.getLeft());
            ObjectAnimator animaY_dice1 = ObjectAnimator.ofFloat(cpuDice1,"translationY",cpuKeepDice1.getTop());
            animaX_dice1.setDuration(600);
            animaY_dice1.setDuration(600);

            animatorSet.play(animaX_dice1);
            animatorSet.play(animaY_dice1);

            animatorSet.start();
        }

        // 주사위 2 킵하기
        if (cpuDice2Keep_move) {
            ObjectAnimator animaX_dice2 = ObjectAnimator.ofFloat(cpuDice2,"translationX",cpuKeepDice2.getLeft());
            ObjectAnimator animaY_dice2 = ObjectAnimator.ofFloat(cpuDice2,"translationY",cpuKeepDice2.getTop());
            animaX_dice2.setDuration(600);
            animaY_dice2.setDuration(600);

            animatorSet.play(animaX_dice2);
            animatorSet.play(animaY_dice2);

            animatorSet.start();
        }

        // 주사위 3 킵하기
        if (cpuDice3Keep_move) {
            ObjectAnimator animaX_dice3 = ObjectAnimator.ofFloat(cpuDice3,"translationX",cpuKeepDice3.getLeft());
            ObjectAnimator animaY_dice3 = ObjectAnimator.ofFloat(cpuDice3,"translationY",cpuKeepDice3.getTop());
            animaX_dice3.setDuration(600);
            animaY_dice3.setDuration(600);

            animatorSet.play(animaX_dice3);
            animatorSet.play(animaY_dice3);

            animatorSet.start();
        }

        // 주사위 4 킵하기
        if (cpuDice4Keep_move) {
            ObjectAnimator animaX_dice4 = ObjectAnimator.ofFloat(cpuDice4,"translationX",cpuKeepDice4.getLeft());
            ObjectAnimator animaY_dice4 = ObjectAnimator.ofFloat(cpuDice4,"translationY",cpuKeepDice4.getTop());
            animaX_dice4.setDuration(600);
            animaY_dice4.setDuration(600);

            animatorSet.play(animaX_dice4);
            animatorSet.play(animaY_dice4);

            animatorSet.start();
        }

        // 주사위 5 킵하기
        if (cpuDice5Keep_move) {
            ObjectAnimator animaX_dice5 = ObjectAnimator.ofFloat(cpuDice5,"translationX",cpuKeepDice5.getLeft());
            ObjectAnimator animaY_dice5 = ObjectAnimator.ofFloat(cpuDice5,"translationY",cpuKeepDice5.getTop());
            animaX_dice5.setDuration(600);
            animaY_dice5.setDuration(600);

            animatorSet.play(animaX_dice5);
            animatorSet.play(animaY_dice5);

            animatorSet.start();
        }

    }

    // cpu 주사위 킵해제하기, 주사위 안보이게 바꾸기
    public void cpuDiceKeepEnd(){
        cpuDice1Keep_move = false;
        cpuDice2Keep_move = false;
        cpuDice3Keep_move = false;
        cpuDice4Keep_move = false;
        cpuDice5Keep_move = false;

        cpuDice1.setVisibility(View.INVISIBLE);
        cpuDice2.setVisibility(View.INVISIBLE);
        cpuDice3.setVisibility(View.INVISIBLE);
        cpuDice4.setVisibility(View.INVISIBLE);
        cpuDice5.setVisibility(View.INVISIBLE);

        dice1.setVisibility(View.VISIBLE);
        dice2.setVisibility(View.VISIBLE);
        dice3.setVisibility(View.VISIBLE);
        dice4.setVisibility(View.VISIBLE);
        dice5.setVisibility(View.VISIBLE);


        p2_turnEnd = true;
        p1_turnEnd = false;
        p1_roll = 0;
        p1_rollturn = false;

        Log.e(TAG,sharedPreferences.getString("p1_TotalScore",""));
        Log.e(TAG,sharedPreferences2.getString("cpu_TotalScore",""));

        if (sharedPreferences.getString("p1_TotalScore", "") != ""
                && sharedPreferences2.getString("cpu_TotalScore","") != ""){
            gameEnd();
        }else{
            AlertDialog.Builder builder = new AlertDialog.Builder(Ingame_Activity.this);
            builder.setTitle("CPU의 턴이 끝났습니다.");
            builder.show();
        }
    }

    // player_1 주사위 점수 초기화
    public void player_1_reset(){
        dice1eye = 0;
        dice2eye = 0;
        dice3eye = 0;
        dice4eye = 0;
        dice5eye = 0;

        dice1Keep_move = false;
        dice2Keep_move = false;
        dice3Keep_move = false;
        dice4Keep_move = false;
        dice5Keep_move = false;
    }

    public void cpuGetScore(){

        // Aces
        if(sharedPreferences2.getString("cpu_Aces","") != ""){
            cpu_Aces = true;
        }else {
            cpu_Aces = false;
        }

        // Twos
        if (sharedPreferences2.getString("cpu_Twos", "") != "") {
            cpu_Twos = true;
        }else {
            cpu_Twos = false;
        }

        // Threes
        if (sharedPreferences2.getString("cpu_Threes", "") != "") {
            cpu_Threes = true;
        }else {
            cpu_Threes = false;
        }

        // Fours
        if (sharedPreferences2.getString("cpu_Fours", "") != "") {
            cpu_Fours = true;
        }else {
            cpu_Fours = false;
        }

        // Fives
        if (sharedPreferences2.getString("cpu_Fives", "") != "") {
            cpu_Fives = true;
        }else {
            cpu_Fives = false;
        }

        // Sixes
        if (sharedPreferences2.getString("cpu_Sixes", "") != "") {
            cpu_Sixes = true;
        }else {
            cpu_Sixes = false;
        }

        // TOAK
        if (sharedPreferences2.getString("cpu_TOAK", "") != "") {
            cpu_TOAK = true;
        }else {
            cpu_TOAK = false;
        }

        // FOAK
        if (sharedPreferences2.getString("cpu_FOAK", "") != "") {
            cpu_FOAK = true;
        }else {
            cpu_FOAK = false;
        }

        // FH
        if (sharedPreferences2.getString("cpu_FH", "") != "") {
            cpu_FH = true;
        }else {
            cpu_FH = false;
        }

        // SS
        if (sharedPreferences2.getString("cpu_SS", "") != "") {
            cpu_SS = true;
        }else {
            cpu_SS = false;
        }

        // LS
        if (sharedPreferences2.getString("cpu_LS", "") != "") {
            cpu_LS = true;
        }else {
            cpu_LS = false;
        }

        // Chance
        if (sharedPreferences2.getString("cpu_Chance", "") != "") {
            cpu_Chance = true;
        }else {
            cpu_Chance = false;
        }

        // YACHTZEE
        if (sharedPreferences2.getString("cpu_YACHTZEE", "") != "") {
            cpu_YACHTZEE = true;
        }else {
            cpu_YACHTZEE = false;
        }
    }

    public void
    gameEnd(){
        Log.e(TAG,"gameEnd 실행되나?");

            int p1_TotalScore = 0,cpu_TotalScore = 0;

            if (sharedPreferences.getString("p1_TotalScore", "") != "") {

                try {
                    p1_TotalScore = Integer.parseInt(sharedPreferences.getString("p1_TotalScore",""));
                    cpu_TotalScore = Integer.parseInt(sharedPreferences2.getString("cpu_TotalScore",""));
                }catch (NumberFormatException e){

                }catch (Exception e){

                }
            }

            AlertDialog.Builder builder = new AlertDialog.Builder(Ingame_Activity.this);
            builder.setTitle("게임이 끝났습니다.");
            builder.setMessage(loginUserNickName + " : " + p1_TotalScore);
            builder.setMessage("CPU : " + cpu_TotalScore);
            if (p1_TotalScore > cpu_TotalScore){
                builder.setMessage(loginUserNickName + "의 승리 입니다.");
            } else if (p1_TotalScore < cpu_TotalScore) {
                builder.setMessage("컴퓨터의 승리입니다.");
            }
            builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            }).setCancelable(false);
            builder.show();
        }


}