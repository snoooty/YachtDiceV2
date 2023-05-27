package com.example.yachtdicev2;

import android.util.Log;

import java.util.Random;

public class RollDice {

    String TAG = "RollDice";
    int dice1Top,dice2Top,dice3Top,dice4Top,dice5Top;
    int dice1Left,dice2Left,dice3Left,dice4Left,dice5Left;

    public RollDice(){

    }

    public void rollDice(int top, int left, int bottom, int right){

        Random rollRnd = new Random();

        // dice1 굴릴 좌표범위 구하기
        dice1Left = rollRnd.nextInt((right - left)/3);
        dice1Top = rollRnd.nextInt(bottom - top);

        if (dice1Top < top) {// view의 top보다 높으면 view 값을 더해서 안으로 들어가게 하기
            dice1Top = dice1Top + top;

            if (dice1Top > bottom - 138) {
                dice1Top = dice1Top - 138;
            }
        }
        Log.e(TAG, "dice1Top : " + dice1Top);

        // dice2 굴리기
        dice2Left = rollRnd.nextInt(360 - 138);
        dice2Top = rollRnd.nextInt(481 - 234);

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
        dice3Left = rollRnd.nextInt(360 - 138);
        dice3Top = rollRnd.nextInt(481 - 234);

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
        dice4Left = rollRnd.nextInt(540 - 138);
        dice4Top = rollRnd.nextInt(234);

        if (dice4Top < 495) {
            dice4Top = dice4Top + 495;

            if (dice4Top > 729 - 138) {
                dice4Top = dice4Top - 138;
            }
        }
        Log.e(TAG, "dice4Top : " + dice4Top);

        // dice5 굴리기
        dice5Left = rollRnd.nextInt(540 - 138);
        dice5Top = rollRnd.nextInt(234);

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
    }
}
