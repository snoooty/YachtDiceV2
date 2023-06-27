package com.example.yachtdicev2.gameLogic;

import android.util.Log;

import java.util.Random;

public class RollDice {

    String TAG = "RollDice";
    int dice1Top,dice2Top,dice3Top,dice4Top,dice5Top;
    int dice1Left,dice2Left,dice3Left,dice4Left,dice5Left;

    public RollDice(){

    }

    public void rollDice(int top, int left, int bottom, int right, int diceSize, int vsTop, boolean userTurn){

        Random rollRnd = new Random();
        Log.e(TAG,"넘어온 top : " + top);
        Log.e(TAG,"넘어온 bottom : " + bottom);
        Log.e(TAG,"넘어온 left : " + left);
        Log.e(TAG,"넘어온 right : " + right);


        int range1_V = (right - left)/3; // P1 View 윗구역 가로범위
        int range2_V = (right - left)/2; // P1 View 아랫구역 가로범위
        int range_H = (bottom - top)/2; // P1 View 전구역 세로범위
        int range_vs = vsTop - top;

        // dice1 굴릴 1구역의 좌표범위 구하기
        dice1Left = rollRnd.nextInt(range1_V);
        dice1Top = rollRnd.nextInt(range_H);

        if (dice1Top < top) {// view의 top보다 높으면 view 값을 더해서 안으로 들어가게 하기
            dice1Top = dice1Top + top;

            if (dice1Top > bottom - range_H - diceSize) {// view 의 중간 보다 주사위가 내려갈수 있으므로 주사위 크기 만큼 올리기
                dice1Top = dice1Top - diceSize;

                if (dice1Top < top) {
                    dice1Top = dice1Top + 10;
                }
            }
        }

        if (!userTurn){
            dice1Top = dice1Top + range_vs;
        }

        if(dice1Left > range1_V - diceSize){// 1구역을 넘어서 2구역을 침범하면 주사위 크기만큼 1구역으로 밀어넣기
            dice1Left = dice1Left - diceSize;
        }
        Log.e(TAG,"dice1Top : " + dice1Top);
        Log.e(TAG,"dice1Left : " + dice1Left);

        // dice2 굴릴 2구역의 좌표범위 구하기
        dice2Left = rollRnd.nextInt(range1_V);
        dice2Top = rollRnd.nextInt(range_H);

        if (dice2Left < range1_V) {// 2구역보다 작아서 1구역에 들어가면 2구역으로 밀어넣기
            dice2Left = dice2Left + range1_V;

            if(dice2Left > range1_V * 2 - diceSize){// 3구역을 넘어서면 2구역으로 밀어넣기
                dice2Left = dice2Left - diceSize;
            }
        }

        if (dice2Top < top) {// view의 top보다 높으면 view 값을 더해서 안으로 들어가게 하기
            dice2Top = dice2Top + top;

            if (dice2Top > bottom - range_H - diceSize) {//view 의 중간 보다 주사위가 내려갈수 있으므로 주사위 크기 만큼 올리기
                dice2Top = dice2Top - diceSize;

                if (dice2Top < top) {
                    dice2Top = dice2Top + 10;
                }
            }
        }
        if (!userTurn){
            dice2Top = dice2Top + range_vs;
        }
        Log.e(TAG,"dice2Top : " + dice2Top);
        Log.e(TAG,"dice2Left : " + dice2Left);

        // dice3 굴릴 3구역의 좌표범위 구하기
        dice3Left = rollRnd.nextInt(range1_V);
        dice3Top = rollRnd.nextInt(range_H);

        if (dice3Left < range1_V * 2) {// 3구역으로 밀어넣기
            dice3Left = dice3Left + (range1_V * 2);

            if (dice3Left > right - diceSize) {// 화면 밖으로 나가지 않게 하기
                dice3Left = dice3Left - diceSize;
            }
        }

        if (dice3Top < top) {// view의 top보다 높으면 view 값을 더해서 안으로 들어가게 하기
            dice3Top = dice3Top + top;

            if (dice3Top > bottom - range_H - diceSize) {//view 의 중간 보다 주사위가 내려갈수 있으므로 주사위 크기 만큼 올리기
                dice3Top = dice3Top - diceSize;

                if (dice3Top < top) {
                    dice3Top = dice3Top + 10;
                }
            }
        }
        if (!userTurn){
            dice3Top = dice3Top + range_vs;
        }
        Log.e(TAG,"dice3Top : " + dice3Top);
        Log.e(TAG,"dice3Left : " + dice3Left);

        // dice4 굴릴 4구역의 좌표범위 구하기
        dice4Left = rollRnd.nextInt(range2_V);
        dice4Top = rollRnd.nextInt(range_H);

        if (dice4Top < top + range_H) {// 4구역으로 밀어넣기
            dice4Top = dice4Top + top + range_H;

            if (dice4Top > bottom - diceSize) {// 넘어가지 않게하기
                dice4Top = dice4Top - diceSize;
            }
        }
        if (!userTurn){
            dice4Top = dice4Top + range_vs;
        }

        if (dice4Left > range2_V - diceSize) {// 넘어가지 않게하기
            dice4Left = dice4Left - diceSize;
        }
        Log.e(TAG,"dice4Top : " + dice4Top);
        Log.e(TAG,"dice4Left : " + dice4Left);

        // dice5 굴릴 5구역의 좌표범위 구하기
        dice5Left = rollRnd.nextInt(range2_V);
        dice5Top = rollRnd.nextInt(range_H);

        if (dice5Left < range2_V) {// 5구역으로 밀어넣기
            dice5Left = dice5Left + range2_V;

            if (dice5Left > right - diceSize) {// 넘어가지 않게하기
                dice5Left = dice5Left - diceSize;
            }
        }

        if (dice5Top < top + range_H) {// 5구역으로 밀어넣기
            dice5Top = dice5Top + top + range_H;

            if (dice5Top > bottom - diceSize) {// 넘어가지 않게하기
                dice5Top = dice5Top - diceSize;
            }
        }
        if (!userTurn){
            dice5Top = dice5Top + range_vs;
        }
        Log.e(TAG,"dice5Top : " + dice5Top);
        Log.e(TAG,"dice5Left : " + dice5Left);
    }

    public void rollDice1(int top, int left, int bottom, int right, int diceSize, int vsTop, boolean userTurn){

        Random rollRnd = new Random();

        int range1_V = (right - left)/3; // P1 View 윗구역 가로범위
        int range_H = (bottom - top)/2; // P1 View 전구역 세로범위
        int range_vs = vsTop - top;

        // dice1 굴릴 1구역의 좌표범위 구하기
        dice1Left = rollRnd.nextInt(range1_V);
        dice1Top = rollRnd.nextInt(range_H);

        if (dice1Top < top) {// view의 top보다 높으면 view 값을 더해서 안으로 들어가게 하기
            dice1Top = dice1Top + top;

            if (dice1Top > bottom - range_H - diceSize) {// view 의 중간 보다 주사위가 내려갈수 있으므로 주사위 크기 만큼 올리기
                dice1Top = dice1Top - diceSize;

                if (dice1Top < top) {
                    dice1Top = dice1Top + 10;
                }
            }
        }
        if (!userTurn){
            dice1Top = dice1Top + range_vs;
        }
        if(dice1Left > range1_V - diceSize){// 1구역을 넘어서 2구역을 침범하면 주사위 크기만큼 1구역으로 밀어넣기
            dice1Left = dice1Left - diceSize;
        }
        Log.e(TAG,"dice1Top : " + dice1Top);
        Log.e(TAG,"dice1Left : " + dice1Left);

    }

    public void rollDice2(int top, int left, int bottom, int right, int diceSize, int vsTop, boolean userTurn){

        Random rollRnd = new Random();

        int range1_V = (right - left)/3; // P1 View 윗구역 가로범위
        int range_H = (bottom - top)/2; // P1 View 전구역 세로범위
        int range_vs = vsTop - top;

        // dice2 굴릴 2구역의 좌표범위 구하기
        dice2Left = rollRnd.nextInt(range1_V);
        dice2Top = rollRnd.nextInt(range_H);

        if (dice2Left < range1_V) {// 2구역보다 작아서 1구역에 들어가면 2구역으로 밀어넣기
            dice2Left = dice2Left + range1_V;

            if(dice2Left > range1_V * 2 - diceSize){// 3구역을 넘어서면 2구역으로 밀어넣기
                dice2Left = dice2Left - diceSize;
            }
        }

        if (dice2Top < top) {// view의 top보다 높으면 view 값을 더해서 안으로 들어가게 하기
            dice2Top = dice2Top + top;

            if (dice2Top > bottom - range_H - diceSize) {//view 의 중간 보다 주사위가 내려갈수 있으므로 주사위 크기 만큼 올리기
                dice2Top = dice2Top - diceSize;

                if (dice2Top < top) {
                    dice2Top = dice2Top + 10;
                }
            }
        }
        if (!userTurn){
            dice2Top = dice2Top + range_vs;
        }
        Log.e(TAG,"dice2Top : " + dice2Top);
        Log.e(TAG,"dice2Left : " + dice2Left);

    }

    public void rollDice3(int top, int left, int bottom, int right, int diceSize, int vsTop, boolean userTurn){

        Random rollRnd = new Random();

        int range1_V = (right - left)/3; // P1 View 윗구역 가로범위
        int range_H = (bottom - top)/2; // P1 View 전구역 세로범위
        int range_vs = vsTop - top;

        // dice3 굴릴 3구역의 좌표범위 구하기
        dice3Left = rollRnd.nextInt(range1_V);
        dice3Top = rollRnd.nextInt(range_H);

        if (dice3Left < range1_V * 2) {// 3구역으로 밀어넣기
            dice3Left = dice3Left + (range1_V * 2);

            if (dice3Left > right - diceSize) {// 화면 밖으로 나가지 않게 하기
                dice3Left = dice3Left - diceSize;
            }
        }

        if (dice3Top < top) {// view의 top보다 높으면 view 값을 더해서 안으로 들어가게 하기
            dice3Top = dice3Top + top;

            if (dice3Top > bottom - range_H - diceSize) {//view 의 중간 보다 주사위가 내려갈수 있으므로 주사위 크기 만큼 올리기
                dice3Top = dice3Top - diceSize;

                if (dice3Top < top) {
                    dice3Top = dice3Top + 10;
                }
            }
        }
        if (!userTurn){
            dice3Top = dice3Top + range_vs;
        }
        Log.e(TAG,"dice3Top : " + dice3Top);
        Log.e(TAG,"dice3Left : " + dice3Left);

    }

    public void rollDice4(int top, int left, int bottom, int right, int diceSize, int vsTop, boolean userTurn){

        Random rollRnd = new Random();

        int range2_V = (right - left)/2; // P1 View 아랫구역 가로범위
        int range_H = (bottom - top)/2; // P1 View 전구역 세로범위
        int range_vs = vsTop - top;

        // dice4 굴릴 4구역의 좌표범위 구하기
        dice4Left = rollRnd.nextInt(range2_V);
        dice4Top = rollRnd.nextInt(range_H);

        if (dice4Top < top + range_H) {// 4구역으로 밀어넣기
            dice4Top = dice4Top + top + range_H;

            if (dice4Top > bottom - diceSize) {// 넘어가지 않게하기
                dice4Top = dice4Top - diceSize;
            }
        }
        if (!userTurn){
            dice4Top = dice4Top + range_vs;
        }

        if (dice4Left > range2_V - diceSize) {// 넘어가지 않게하기
            dice4Left = dice4Left - diceSize;
        }
        Log.e(TAG,"dice4Top : " + dice4Top);
        Log.e(TAG,"dice4Left : " + dice4Left);

    }

    public void rollDice5(int top, int left, int bottom, int right, int diceSize, int vsTop, boolean userTurn){

        Random rollRnd = new Random();

        int range2_V = (right - left)/2; // P1 View 아랫구역 가로범위
        int range_H = (bottom - top)/2; // P1 View 전구역 세로범위
        int range_vs = vsTop - top;

        // dice5 굴릴 5구역의 좌표범위 구하기
        dice5Left = rollRnd.nextInt(range2_V);
        dice5Top = rollRnd.nextInt(range_H);

        if (dice5Left < range2_V) {// 5구역으로 밀어넣기
            dice5Left = dice5Left + range2_V;

            if (dice5Left > right - diceSize) {// 넘어가지 않게하기
                dice5Left = dice5Left - diceSize;
            }
        }

        if (dice5Top < top + range_H) {// 5구역으로 밀어넣기
            dice5Top = dice5Top + top + range_H;

            if (dice5Top > bottom - diceSize) {// 넘어가지 않게하기
                dice5Top = dice5Top - diceSize;
            }
        }
        if (!userTurn){
            dice1Top = dice1Top + range_vs;
        }
        Log.e(TAG,"dice5Top : " + dice5Top);
        Log.e(TAG,"dice5Left : " + dice5Left);

    }
}
