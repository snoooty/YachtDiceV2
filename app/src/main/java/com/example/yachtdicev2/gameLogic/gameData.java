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
    }

    public void player2dataReset(){
        SharedPreferences.Editor editor2 = sharedPreferences2.edit();
        editor2.remove("p2_Aces");
        editor2.remove("p2_Twos");
        editor2.remove("p2_Threes");
        editor2.remove("p2_Fours");
        editor2.remove("p2_Fives");
        editor2.remove("p2_Sixes");
        editor2.remove("p2_TOAK");
        editor2.remove("p2_FOAK");
        editor2.remove("p2_FH");
        editor2.remove("p2_SS");
        editor2.remove("p2_LS");
        editor2.remove("p2_Chance");
        editor2.remove("p2_YACHTZEE");
        editor2.remove("p2_TOAK_YACHTZEE");
        editor2.remove("p2_FOAK_YACHTZEE");
        editor2.remove("p2_FH_YACHTZEE");
        editor2.remove("p2_SS_YACHTZEE");
        editor2.remove("p2_LS_YACHTZEE");
        editor2.remove("p2_Chance_YACHTZEE");
        editor2.commit();
    }


}
