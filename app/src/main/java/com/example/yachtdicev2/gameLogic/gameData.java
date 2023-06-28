package com.example.yachtdicev2.gameLogic;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;

public class gameData {
    public SharedPreferences sharedPreferences,sharedPreferences2;

    public gameData(SharedPreferences sharedPreferences,SharedPreferences sharedPreferences2){
        this.sharedPreferences = sharedPreferences;
        this.sharedPreferences2 = sharedPreferences2;
    }

    public void player1dataReset(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("P1Aces");
        editor.remove("P1Twos");
        editor.remove("P1Threes");
        editor.remove("P1Fours");
        editor.remove("P1Fives");
        editor.remove("P1Sixes");
        editor.remove("P1TOAK");
        editor.remove("P1FOAK");
        editor.remove("P1FH");
        editor.remove("P1SS");
        editor.remove("P1LS");
        editor.remove("P1Chance");
        editor.remove("P1YACHTZEE");
        editor.remove("P1TOAK_YACHTZEE");
        editor.remove("P1FOAK_YACHTZEE");
        editor.remove("P1FH_YACHTZEE");
        editor.remove("P1SS_YACHTZEE");
        editor.remove("P1LS_YACHTZEE");
        editor.remove("P1Chance_YACHTZEE");
        editor.remove("P1TotalScore");
        editor.commit();
    }

    public void player2dataReset(){
        SharedPreferences.Editor editor2 = sharedPreferences2.edit();
        editor2.remove("P2Aces");
        editor2.remove("P2Twos");
        editor2.remove("P2Threes");
        editor2.remove("P2Fours");
        editor2.remove("P2Fives");
        editor2.remove("P2Sixes");
        editor2.remove("P2TOAK");
        editor2.remove("P2FOAK");
        editor2.remove("P2FH");
        editor2.remove("P2SS");
        editor2.remove("P2LS");
        editor2.remove("P2Chance");
        editor2.remove("P2YACHTZEE");
        editor2.remove("P2TOAK_YACHTZEE");
        editor2.remove("P2FOAK_YACHTZEE");
        editor2.remove("P2FH_YACHTZEE");
        editor2.remove("P2SS_YACHTZEE");
        editor2.remove("P2LS_YACHTZEE");
        editor2.remove("P2Chance_YACHTZEE");
        editor2.commit();
    }


}
