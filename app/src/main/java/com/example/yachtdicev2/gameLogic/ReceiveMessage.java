package com.example.yachtdicev2.gameLogic;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

public class ReceiveMessage {


    BufferedReader in = null;
    PrintWriter out = null;
    String msg;
    String TAG = "ReceiveMsg";
    JSONObject jsonObject;
    int dice1,dice2,dice3,dice4,dice5;
    int vsP1ViewTop,vsP1ViewBottom,vsP1ViewLeft,vsP1ViewRight,diceSize;
    int vsP2ViewTop;
    ImageView vs_dice1,vs_dice2,vs_dice3,vs_dice4,vs_dice5;
    ImageView vsP1KeepDice1,vsP1KeepDice2,vsP1KeepDice3,vsP1KeepDice4,vsP1KeepDice5;
    ImageView vsP2KeepDice1,vsP2KeepDice2,vsP2KeepDice3,vsP2KeepDice4,vsP2KeepDice5;
    TextView user1,user2;
    RollDice rollDice;
    AnimatorSet vsAnimatorSet;
    AnimationDrawable vsRolldice_1,vsRolldice_2,vsRolldice_3,vsRolldice_4,vsRolldice_5;
    Drawable vs_dice_1,vs_dice_2,vs_dice_3,vs_dice_4,vs_dice_5,vs_dice_6;
    Drawable vs_rolldice_1xml,vs_rolldice_2xml,vs_rolldice_3xml,vs_rolldice_4xml,vs_rolldice_5xml;
    public boolean dice1Keep_move,dice2Keep_move,dice3Keep_move,dice4Keep_move,dice5Keep_move;
    public boolean userTurn;
    public int dice1eye,dice2eye,dice3eye,dice4eye,dice5eye;
    String diceSum;
    public String myStatus;

    public ReceiveMessage(RollDice rollDice, ImageView vs_dice1, ImageView vs_dice2, ImageView vs_dice3, ImageView vs_dice4
    , ImageView vs_dice5, boolean dice1Keep_move, boolean dice2Keep_move, boolean dice3Keep_move, boolean dice4Keep_move
    , boolean dice5Keep_move, AnimationDrawable vsRolldice_1, AnimationDrawable vsRolldice_2, AnimationDrawable vsRolldice_3
    , AnimationDrawable vsRolldice_4, AnimationDrawable vsRolldice_5, Drawable vs_rolldice_1xml, Drawable vs_rolldice_2xml
    , Drawable vs_rolldice_3xml, Drawable vs_rolldice_4xml, Drawable vs_rolldice_5xml, Drawable vs_dice_1
    , Drawable vs_dice_2, Drawable vs_dice_3, Drawable vs_dice_4, Drawable vs_dice_5, Drawable vs_dice_6, int vsP1ViewTop
    , int vsP1ViewBottom, int vsP1ViewLeft, int vsP1ViewRight, int diceSize, boolean userTurn, ImageView vsP1KeepDice1
    , ImageView vsP1KeepDice2, ImageView vsP1KeepDice3, ImageView vsP1KeepDice4, ImageView vsP1KeepDice5, TextView user1
    , TextView user2, int vsP2ViewTop, ImageView vsP2KeepDice1, ImageView vsP2KeepDice2, ImageView vsP2KeepDice3
    , ImageView vsP2KeepDice4, ImageView vsP2KeepDice5){

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

    }

    public void receiveMsg(Socket gameSock){
            Log.e(TAG,"receiveMsg 실행됨?");
        new Thread(() -> {
            Log.e(TAG,"핸들러되냐?");
            Handler handler = new Handler(Looper.getMainLooper());
            Log.e(TAG,"핸들러 넘어가냐?");
            try{
                while (true){
                    in = new BufferedReader(new InputStreamReader(gameSock.getInputStream()));
                    msg = in.readLine();
                    jsonObject = new JSONObject(msg);
                    Log.e(TAG,"받은 메세지 : " + jsonObject.toString());
                    String receiveName = (String) jsonObject.get("clickName");

                    // 처음에 유저의 턴 배분
                    if (receiveName.equals("current")){
                        userTurn = true;
                        myStatus = "player1";

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                user1.setText((String) jsonObject.optString("player1","대기중"));
                                user2.setText((String) jsonObject.optString("player2","대기중"));
                            }
                        });
                    }else if (receiveName.equals("wait")){
                        userTurn = false;
                        myStatus = "player2";
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                user1.setText((String) jsonObject.optString("player2","대기중"));
                                user2.setText((String) jsonObject.optString("player1","대기중"));
                            }
                        });
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
}
