package com.example.yachtdicev2;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.RED;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class ScorePage extends AppCompatActivity {

    String TAG = "ScorePage";
    String loginUserNickName;
    String cpu;
    int player1_Aces,player1_Twos,player1_Threes,player1_Fours,player1_Fives,player1_Sixes;
    int player1_sum,player1_Bonus,player1_ThreeOfAKind,player1_FourOfAKind,player1_FullHouse;
    int player1_SmallStraight,player1_LargeStraight,player1_Chance,player1_YACHTZEE;
    int player1_YACHTBonus,player1_TotalScore;
    int cpuAcesYA,cpuTwosYA,cpuThreesYA,cpuFoursYA,cpuFivesYA,cpuSixesYA;
    int cpuTOAKYA,cpuFOAKYA,cpuFHYA,cpuSSYA,cpuLSYA,cpuChanceYA;
    int cpu_Bonus,cpu_Sum,cpu_YACHT;
    int cpu_YACHTBonus,cpu_TotalScore;
    int dice1eye,dice2eye,dice3eye,dice4eye,dice5eye;
    int acesYACHTZEE,twosYACHTZEE,threesYACHTZEE,foursYACHTZEE,fivesYACHTZEE,sixesYACHTZEE;
    int TOAKYACHTZEE,FOAKYACHTZEE,FHYACHTZEE,SSYACHTZEE,LSYACHTZEE,chanceYACHTZEE;
    boolean p1_Aces,p1_Twos,p1_Threes,p1_Fours,p1_Fives,p1_Sixes;
    boolean cpu_Aces,cpu_Twos,cpu_Threes,cpu_Fours,cpu_Fives,cpu_Sixes;
    boolean p1_TOAK,p1_FOAK,p1_FH,p1_SS,p1_LS,p1_Chance,p1_YACHTZEE;
    boolean cpu_TOAK,cpu_FOAK,cpu_FH,cpu_SS,cpu_LS,cpu_Chance,cpu_YACHTZEE;
    boolean p1_turnEnd,p2_turnEnd;

    SharedPreferences sharedPreferences,sharedPreferences2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_page);

        sharedPreferences = getSharedPreferences("P1Score",MODE_PRIVATE);
        Log.e(TAG,sharedPreferences.getString("p1_Aces",""));
        sharedPreferences2 = getSharedPreferences("CPUScore",MODE_PRIVATE);

        getScore();
        cpuGetScore();

        Intent intent = getIntent();
        dice1eye = intent.getIntExtra("dice1eye",0);
        dice2eye = intent.getIntExtra("dice2eye",0);
        dice3eye = intent.getIntExtra("dice3eye",0);
        dice4eye = intent.getIntExtra("dice4eye",0);
        dice5eye = intent.getIntExtra("dice5eye",0);

        if (dice1eye == 0 || dice2eye == 0 || dice3eye == 0 || dice4eye == 0 || dice5eye == 0) {
            p1_turnEnd = true;
        }

        cpu = intent.getStringExtra("CPU");
        loginUserNickName = intent.getStringExtra("loginUserNickName");

        Log.e(TAG,"dice1eye 값 : " + dice1eye);
        Log.e(TAG,"dice2eye 값 : " + dice2eye);
        Log.e(TAG,"dice3eye 값 : " + dice3eye);
        Log.e(TAG,"dice4eye 값 : " + dice4eye);
        Log.e(TAG,"dice5eye 값 : " + dice5eye);
        Log.e(TAG,"CPU : " + cpu);
        Log.e(TAG,"loginUserNickName : " + loginUserNickName);

        // player_1 의 NickName 가져오기
        TextView player1 = (TextView) findViewById(R.id.player_1_score);
        player1.setText(loginUserNickName);

        // cpu의 이름 가져오기
        TextView cpuName = (TextView) findViewById(R.id.player_2_score);
        cpuName.setText(cpu);

        // player_1 의 Aces 점수
        TextView player_1_Aces = (TextView) findViewById(R.id.acesScore_P1);
        if(!p1_Aces) {
            if (dice1eye != 0 && dice2eye != 0 && dice3eye != 0 && dice4eye != 0 && dice5eye != 0) {
                int eye1, eye2, eye3, eye4, eye5;

                if (dice1eye != 1) {
                    eye1 = 0;
                } else {
                    eye1 = dice1eye;
                }
                Log.e(TAG, "Aces eye1 : " + eye1);

                if (dice2eye != 1) {
                    eye2 = 0;
                } else {
                    eye2 = dice2eye;
                }
                Log.e(TAG, "Aces eye2 : " + eye2);

                if (dice3eye != 1) {
                    eye3 = 0;
                } else {
                    eye3 = dice3eye;
                }
                Log.e(TAG, "Aces eye3 : " + eye3);

                if (dice4eye != 1) {
                    eye4 = 0;
                } else {
                    eye4 = dice4eye;
                }
                Log.e(TAG, "Aces eye4 : " + eye4);

                if (dice5eye != 1) {
                    eye5 = 0;
                } else {
                    eye5 = dice5eye;
                }
                Log.e(TAG, "Aces eye5 : " + eye5);

                player1_Aces = eye1 + eye2 + eye3 + eye4 + eye5;

                if (player1_Aces != 0) {
                    player_1_Aces.setText(String.valueOf(player1_Aces));
                    player_1_Aces.setTextColor(RED);
                }
            }
        }else if(p1_Aces){
            player_1_Aces.setText(sharedPreferences.getString("p1_Aces",""));
            player1_Aces = Integer.valueOf(sharedPreferences.getString("p1_Aces",""));
            Log.e(TAG,"player1_Aces : " + player1_Aces);
            player_1_Aces.setTextColor(BLACK);
        }

        // player_1 의 Twos 점수
        TextView player_1_Twos = (TextView) findViewById(R.id.twosScore_P1);
        if(!p1_Twos) {
            if (dice1eye != 0 && dice2eye != 0 && dice3eye != 0 && dice4eye != 0 && dice5eye != 0) {
                int eye1, eye2, eye3, eye4, eye5;

                if (dice1eye != 2) {
                    eye1 = 0;
                } else {
                    eye1 = dice1eye;
                }
                Log.e(TAG, "Twos eye1 : " + eye1);

                if (dice2eye != 2) {
                    eye2 = 0;
                } else {
                    eye2 = dice2eye;
                }
                Log.e(TAG, "Twos eye2 : " + eye2);

                if (dice3eye != 2) {
                    eye3 = 0;
                } else {
                    eye3 = dice3eye;
                }
                Log.e(TAG, "Twos eye3 : " + eye3);

                if (dice4eye != 2) {
                    eye4 = 0;
                } else {
                    eye4 = dice4eye;
                }
                Log.e(TAG, "Twos eye4 : " + eye4);

                if (dice5eye != 2) {
                    eye5 = 0;
                } else {
                    eye5 = dice5eye;
                }
                Log.e(TAG, "Twos eye5 : " + eye5);

                player1_Twos = eye1 + eye2 + eye3 + eye4 + eye5;

                if (player1_Twos != 0) {
                    player_1_Twos.setText(String.valueOf(player1_Twos));
                    player_1_Twos.setTextColor(RED);
                }

            }
        }else if(p1_Twos){
            player_1_Twos.setText(sharedPreferences.getString("p1_Twos",""));
            player1_Twos = Integer.valueOf(sharedPreferences.getString("p1_Twos",""));
            Log.e(TAG,"player1_Twos : " + player1_Twos);
            player_1_Twos.setTextColor(BLACK);
        }

        // player_1 의 Threes 점수
        TextView player_1_Threes = (TextView) findViewById(R.id.threesScore_P1);
        if(!p1_Threes) {
            if (dice1eye != 0 && dice2eye != 0 && dice3eye != 0 && dice4eye != 0 && dice5eye != 0) {
                int eye1, eye2, eye3, eye4, eye5;

                if (dice1eye != 3) {
                    eye1 = 0;
                } else {
                    eye1 = dice1eye;
                }
                Log.e(TAG, "Twos eye1 : " + eye1);

                if (dice2eye != 3) {
                    eye2 = 0;
                } else {
                    eye2 = dice2eye;
                }
                Log.e(TAG, "Twos eye2 : " + eye2);

                if (dice3eye != 3) {
                    eye3 = 0;
                } else {
                    eye3 = dice3eye;
                }
                Log.e(TAG, "Twos eye3 : " + eye3);

                if (dice4eye != 3) {
                    eye4 = 0;
                } else {
                    eye4 = dice4eye;
                }
                Log.e(TAG, "Twos eye4 : " + eye4);

                if (dice5eye != 3) {
                    eye5 = 0;
                } else {
                    eye5 = dice5eye;
                }
                Log.e(TAG, "Twos eye5 : " + eye5);

                player1_Threes = eye1 + eye2 + eye3 + eye4 + eye5;

                if (player1_Threes != 0) {
                    player_1_Threes.setText(String.valueOf(player1_Threes));
                    player_1_Threes.setTextColor(RED);
                }
            }
        } else if (p1_Threes) {
            player_1_Threes.setText(sharedPreferences.getString("p1_Threes",""));
            player1_Threes = Integer.valueOf(sharedPreferences.getString("p1_Threes",""));
            Log.e(TAG,"player1_Threes : " + player1_Threes);
            player_1_Threes.setTextColor(BLACK);
        }

        // player_1 의 Fours 점수
        TextView player_1_Fours = (TextView) findViewById(R.id.foursScore_P1);
        if(!p1_Fours) {
            if (dice1eye != 0 && dice2eye != 0 && dice3eye != 0 && dice4eye != 0 && dice5eye != 0) {
                int eye1, eye2, eye3, eye4, eye5;

                if (dice1eye != 4) {
                    eye1 = 0;
                } else {
                    eye1 = dice1eye;
                }
                Log.e(TAG, "Twos eye1 : " + eye1);

                if (dice2eye != 4) {
                    eye2 = 0;
                } else {
                    eye2 = dice2eye;
                }
                Log.e(TAG, "Twos eye2 : " + eye2);

                if (dice3eye != 4) {
                    eye3 = 0;
                } else {
                    eye3 = dice3eye;
                }
                Log.e(TAG, "Twos eye3 : " + eye3);

                if (dice4eye != 4) {
                    eye4 = 0;
                } else {
                    eye4 = dice4eye;
                }
                Log.e(TAG, "Twos eye4 : " + eye4);

                if (dice5eye != 4) {
                    eye5 = 0;
                } else {
                    eye5 = dice5eye;
                }
                Log.e(TAG, "Twos eye5 : " + eye5);

                player1_Fours = eye1 + eye2 + eye3 + eye4 + eye5;

                if (player1_Fours != 0) {
                    player_1_Fours.setText(String.valueOf(player1_Fours));
                    player_1_Fours.setTextColor(RED);
                }
            }
        } else if (p1_Fours) {
            player_1_Fours.setText(sharedPreferences.getString("p1_Fours",""));
            player1_Fours = Integer.valueOf(sharedPreferences.getString("p1_Fours",""));
            Log.e(TAG,"player1_Fours : " + player1_Fours);
            player_1_Fours.setTextColor(BLACK);
        }

        // player_1 의 Fives 점수
        TextView player_1_Fives = (TextView) findViewById(R.id.fivesScore_P1);
        if(!p1_Fives) {
            if (dice1eye != 0 && dice2eye != 0 && dice3eye != 0 && dice4eye != 0 && dice5eye != 0) {
                int eye1, eye2, eye3, eye4, eye5;

                if (dice1eye != 5) {
                    eye1 = 0;
                } else {
                    eye1 = dice1eye;
                }
                Log.e(TAG, "Twos eye1 : " + eye1);

                if (dice2eye != 5) {
                    eye2 = 0;
                } else {
                    eye2 = dice2eye;
                }
                Log.e(TAG, "Twos eye2 : " + eye2);

                if (dice3eye != 5) {
                    eye3 = 0;
                } else {
                    eye3 = dice3eye;
                }
                Log.e(TAG, "Twos eye3 : " + eye3);

                if (dice4eye != 5) {
                    eye4 = 0;
                } else {
                    eye4 = dice4eye;
                }
                Log.e(TAG, "Twos eye4 : " + eye4);

                if (dice5eye != 5) {
                    eye5 = 0;
                } else {
                    eye5 = dice5eye;
                }
                Log.e(TAG, "Twos eye5 : " + eye5);

                player1_Fives = eye1 + eye2 + eye3 + eye4 + eye5;

                if (player1_Fives != 0) {
                    player_1_Fives.setText(String.valueOf(player1_Fives));
                    player_1_Fives.setTextColor(RED);
                }
            }
        }else if(p1_Fives){
            player_1_Fives.setText(sharedPreferences.getString("p1_Fives",""));
            player1_Fives = Integer.valueOf(sharedPreferences.getString("p1_Fives",""));
            Log.e(TAG,"player1_Fives : " + player1_Fives);
            player_1_Fives.setTextColor(BLACK);
        }

        // player_1 의 Sixes 점수
        TextView player_1_Sixes = (TextView) findViewById(R.id.sixesScore_P1);
        if(!p1_Sixes) {
            if (dice1eye != 0 && dice2eye != 0 && dice3eye != 0 && dice4eye != 0 && dice5eye != 0) {
                int eye1, eye2, eye3, eye4, eye5;

                if (dice1eye != 6) {
                    eye1 = 0;
                } else {
                    eye1 = dice1eye;
                }
                Log.e(TAG, "Twos eye1 : " + eye1);

                if (dice2eye != 6) {
                    eye2 = 0;
                } else {
                    eye2 = dice2eye;
                }
                Log.e(TAG, "Twos eye2 : " + eye2);

                if (dice3eye != 6) {
                    eye3 = 0;
                } else {
                    eye3 = dice3eye;
                }
                Log.e(TAG, "Twos eye3 : " + eye3);

                if (dice4eye != 6) {
                    eye4 = 0;
                } else {
                    eye4 = dice4eye;
                }
                Log.e(TAG, "Twos eye4 : " + eye4);

                if (dice5eye != 6) {
                    eye5 = 0;
                } else {
                    eye5 = dice5eye;
                }
                Log.e(TAG, "Twos eye5 : " + eye5);

                player1_Sixes = eye1 + eye2 + eye3 + eye4 + eye5;

                if (player1_Sixes != 0) {
                    player_1_Sixes.setText(String.valueOf(player1_Sixes));
                    player_1_Sixes.setTextColor(RED);
                }
            }
        }else if(p1_Sixes){
            player_1_Sixes.setText(sharedPreferences.getString("p1_Sixes",""));
            player1_Sixes = Integer.valueOf(sharedPreferences.getString("p1_Sixes",""));
            Log.e(TAG,"player1_Sixes : " + player1_Sixes);
            player_1_Sixes.setTextColor(BLACK);
        }

        // player_1 의 Bonus 점수
        TextView player_1_Bonus = (TextView) findViewById(R.id.bonusScore_P1);
        if (p1_Aces && p1_Twos && p1_Threes && p1_Fours && p1_Fives && p1_Sixes) {
                int aces, twos, threes, fours, fives, sixes;

                if (player_1_Aces.getText().toString() != null
                        && player_1_Twos.getText().toString() != null
                        && player_1_Threes.getText().toString() != null
                        && player_1_Fours.getText().toString() != null
                        && player_1_Fives.getText().toString() != null
                        && player_1_Sixes.getText().toString() != null) {

                    try {
                        aces = Integer.parseInt(player_1_Aces.getText().toString());
                        twos = Integer.parseInt(player_1_Twos.getText().toString());
                        threes = Integer.parseInt(player_1_Threes.getText().toString());
                        fours = Integer.parseInt(player_1_Fours.getText().toString());
                        fives = Integer.parseInt(player_1_Fives.getText().toString());
                        sixes = Integer.parseInt(player_1_Sixes.getText().toString());

                        if (aces + twos + threes + fours + fives + sixes >= 63) {
                            player1_Bonus = 35;
                            player_1_Bonus.setText(String.valueOf(player1_Bonus));
                            player_1_Bonus.setTextColor(BLACK);
                        }else {
                            player1_Bonus = 0;
                            player_1_Bonus.setText(String.valueOf(player1_Bonus));
                            player_1_Bonus.setTextColor(BLACK);
                        }

                    } catch (NumberFormatException e) {

                    } catch (Exception e) {

                    }
                }
            }


        // player_1 의 Sum 함수
        TextView player_1_Sum = (TextView) findViewById(R.id.sumScore_P1);
        if(p1_Aces && p1_Twos && p1_Threes && p1_Fours && p1_Fives && p1_Sixes){
            player1_sum = player1_Aces + player1_Twos + player1_Threes + player1_Fours + player1_Fives + player1_Sixes + player1_Bonus;
            player_1_Sum.setText(String.valueOf(player1_sum));
            player_1_Sum.setTextColor(BLACK);
        }

        // player_1 의 Three Of A Kind 점수
        TextView player_1_TOAK = (TextView) findViewById(R.id.threeOfAkindScore_P1);
        if(!p1_TOAK) {
            if (dice1eye != 0 && dice2eye != 0 && dice3eye != 0 && dice4eye != 0 && dice5eye != 0) {
                if (threeEyeCheck()) {
                    player1_ThreeOfAKind = dice1eye + dice2eye + dice3eye + dice4eye + dice5eye;
                    player_1_TOAK.setText(String.valueOf(player1_ThreeOfAKind));
                    player_1_TOAK.setTextColor(RED);
                }
            }
        } else if (p1_TOAK) {
            player_1_TOAK.setText(sharedPreferences.getString("p1_TOAK",""));
            player1_ThreeOfAKind = Integer.valueOf(sharedPreferences.getString("p1_TOAK",""));
            Log.e(TAG,"player1_TOAK : " + player1_ThreeOfAKind);
            player_1_TOAK.setTextColor(BLACK);
        }

        // player_1 의 Four Of A Kind 점수
        TextView player_1_FOAK = (TextView) findViewById(R.id.fourOfAkindScore_P1);
        if (!p1_FOAK) {
            if (dice1eye != 0 && dice2eye != 0 && dice3eye != 0 && dice4eye != 0 && dice5eye != 0) {
                if (fourEyeCheck()) {
                    player1_FourOfAKind = dice1eye + dice2eye + dice3eye + dice4eye + dice5eye;
                    player_1_FOAK.setText(String.valueOf(player1_FourOfAKind));
                    player_1_FOAK.setTextColor(RED);
                }
            }
        }else if(p1_FOAK){
            player_1_FOAK.setText(sharedPreferences.getString("p1_FOAK",""));
            player1_FourOfAKind = Integer.valueOf(sharedPreferences.getString("p1_FOAK",""));
            Log.e(TAG,"player1_FOAK : " + player1_FourOfAKind);
            player_1_FOAK.setTextColor(BLACK);
        }

        // player_1 의 FullHouse 점수
        TextView player_1_FH = (TextView) findViewById(R.id.fullHouseScore_P1);
        if (!p1_FH) {
            if (dice1eye != 0 && dice2eye != 0 && dice3eye != 0 && dice4eye != 0 && dice5eye != 0) {
                if (fullHouseEyeCheck()) {
                    player1_FullHouse = 25;
                    player_1_FH.setText(String.valueOf(player1_FullHouse));
                    player_1_FH.setTextColor(RED);
                }
            }
        } else if (p1_FH) {
            player_1_FH.setText(sharedPreferences.getString("p1_FH",""));
            player1_FullHouse = Integer.valueOf(sharedPreferences.getString("p1_FH",""));
            Log.e(TAG,"player1_FH : " + player1_FullHouse);
            player_1_FH.setTextColor(BLACK);
        }

        // player_1 의 SmallStraight 점수
        TextView player_1_SS = (TextView) findViewById(R.id.smallStraightScore_P1);
        if (!p1_SS) {
            if (dice1eye != 0 && dice2eye != 0 && dice3eye != 0 && dice4eye != 0 && dice5eye != 0) {
                if (smallStraight()) {
                    player1_SmallStraight = 30;
                    player_1_SS.setText(String.valueOf(player1_SmallStraight));
                    player_1_SS.setTextColor(RED);
                }
            }
        } else if (p1_SS) {
            player_1_SS.setText(sharedPreferences.getString("p1_SS",""));
            player1_SmallStraight = Integer.valueOf(sharedPreferences.getString("p1_SS",""));
            Log.e(TAG,"player1_SS : " + player1_SmallStraight);
            player_1_SS.setTextColor(BLACK);
        }

        // player_1 의 LargeStraight 점수
        TextView player_1_LS = (TextView) findViewById(R.id.largeStraightScore_P1);
        if (!p1_LS) {
            if (dice1eye != 0 && dice2eye != 0 && dice3eye != 0 && dice4eye != 0 && dice5eye != 0) {
                if (largeStraight()) {
                    player1_LargeStraight = 40;
                    player_1_LS.setText(String.valueOf(player1_LargeStraight));
                    player_1_LS.setTextColor(RED);
                }
            }
        } else if (p1_LS) {
            player_1_LS.setText(sharedPreferences.getString("p1_LS",""));
            player1_LargeStraight = Integer.valueOf(sharedPreferences.getString("p1_LS",""));
            Log.e(TAG,"player1_LS : " + player1_LargeStraight);
            player_1_LS.setTextColor(BLACK);
        }

        // player_1 의 Chance 점수
        TextView player_1_Chance = (TextView) findViewById(R.id.chanceScore_P1);
        if (!p1_Chance) {
            if (dice1eye != 0 && dice2eye != 0 && dice3eye != 0 && dice4eye != 0 && dice5eye != 0) {
                player1_Chance = dice1eye + dice2eye + dice3eye + dice4eye + dice5eye;
                player_1_Chance.setText(String.valueOf(player1_Chance));
                player_1_Chance.setTextColor(RED);
            }
        } else if (p1_Chance) {
            player_1_Chance.setText(sharedPreferences.getString("p1_Chance",""));
            player1_Chance = Integer.valueOf(sharedPreferences.getString("p1_Chance",""));
            Log.e(TAG,"player1_Chance : " + player1_Chance);
            player_1_Chance.setTextColor(BLACK);
        }

        // player_1 의 YACHTZEE 점수
        TextView player_1_YACHTZEE = (TextView) findViewById(R.id.yachtzeeScore_P1);
        if (!p1_YACHTZEE) {
            if (dice1eye != 0 && dice2eye != 0 && dice3eye != 0 && dice4eye != 0 && dice5eye != 0) {
                if (yachtzee()) {
                    player1_YACHTZEE = 50;
                    player_1_YACHTZEE.setText(String.valueOf(player1_YACHTZEE));
                    player_1_YACHTZEE.setTextColor(RED);
                }
            }
        } else if (p1_YACHTZEE) {
            player_1_YACHTZEE.setText(sharedPreferences.getString("p1_YACHTZEE",""));
            player1_YACHTZEE = Integer.valueOf(sharedPreferences.getString("p1_YACHTZEE",""));
            Log.e(TAG,"player1_YACHTZEE : " + player1_YACHTZEE);
            player_1_YACHTZEE.setTextColor(BLACK);
        }

        // player_1 의 YACHTBonus 점수
        TextView player_1_YACHTBonus = (TextView) findViewById(R.id.yachtBonusScore_P1);
        if(p1_YACHTZEE && player1_YACHTZEE != 0){

            if (player1_Aces == 5 && p1_Aces) {
                acesYACHTZEE = 50;
            }
            if (player1_Twos == 10 && p1_Twos) {
                twosYACHTZEE = 50;
            }
            if (player1_Threes == 15 && p1_Threes) {
                threesYACHTZEE = 50;
            }
            if (player1_Fours == 20 && p1_Fours) {
                foursYACHTZEE = 50;
            }
            if (player1_Fives == 25 && p1_Fives) {
                fivesYACHTZEE = 50;
            }
            if (player1_Sixes == 30 && p1_Sixes) {
                sixesYACHTZEE = 50;
            }
            if (sharedPreferences.getString("TOAK_YACHTZEE", "") == "TOAK_YACHTZEE") {
                TOAKYACHTZEE = 50;
            }
            if (sharedPreferences.getString("FOAK_YACHTZEE", "") == "FOAK_YACHTZEE") {
                FOAKYACHTZEE = 50;
            }
            if (sharedPreferences.getString("FH_YACHTZEE", "") == "FH_YACHTZEE") {
                FHYACHTZEE = 50;
            }
            if (sharedPreferences.getString("SS_YACHTZEE", "") == "SS_YACHTZEE") {
                SSYACHTZEE = 50;
            }
            if (sharedPreferences.getString("LS_YACHTZEE", "") == "LS_YACHTZEE") {
                LSYACHTZEE = 50;
            }
            if (sharedPreferences.getString("Chance_YACHTZEE", "") == "Chance_YACHTZEE") {
                chanceYACHTZEE = 50;
            }

            player1_YACHTBonus = acesYACHTZEE + twosYACHTZEE + threesYACHTZEE + foursYACHTZEE + fivesYACHTZEE + sixesYACHTZEE +
                    TOAKYACHTZEE + FOAKYACHTZEE + FHYACHTZEE + SSYACHTZEE + LSYACHTZEE + chanceYACHTZEE;
            if (player1_YACHTBonus != 0) {
                player_1_YACHTBonus.setText(String.valueOf(player1_YACHTBonus));
                player_1_YACHTBonus.setTextColor(BLACK);
            }else {
                player_1_YACHTBonus.setText(String.valueOf(0));
                player_1_YACHTBonus.setTextColor(BLACK);
            }
        }else if (player1_YACHTZEE == 0 && p1_YACHTZEE) {
            player_1_YACHTBonus.setText(String.valueOf(0));
            player_1_YACHTBonus.setTextColor(BLACK);
        }

        // player_1 의 TotalScore 점수
        TextView player_1_TotalScore = (TextView) findViewById(R.id.totalScore_P1);
        if(p1_Aces && p1_Twos && p1_Threes && p1_Fours && p1_Fives && p1_Sixes
        && p1_TOAK && p1_FOAK && p1_FH && p1_SS && p1_LS && p1_Chance && p1_YACHTZEE){
            player1_TotalScore = player1_sum + player1_ThreeOfAKind + player1_FourOfAKind + player1_FullHouse
                    + player1_SmallStraight + player1_LargeStraight + player1_Chance + player1_YACHTZEE + player1_YACHTBonus;
            player_1_TotalScore.setText(String.valueOf(player1_TotalScore));
            player_1_TotalScore.setTextColor(BLACK);

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("p1_TotalScore",String.valueOf(player1_TotalScore));
            editor.commit();
        }

        // p1_Aces 클릭 시 점수 고정하기
        if(!p1_Aces && !p1_turnEnd) {
            // player_1_Aces 클릭 시
            player_1_Aces.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e(TAG, "p1_acesScore 클릭되나?");
                    AlertDialog.Builder builder = new AlertDialog.Builder(ScorePage.this);
                    builder.setTitle("Aces 점수").setMessage(String.valueOf(player1_Aces));
                    builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            p1_turnEnd = true;
                            p2_turnEnd = false;

                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("p1_Aces",String.valueOf(player1_Aces));
                            editor.commit();

                            player_1_Aces.setText(String.valueOf(player1_Aces));
                            player_1_Aces.setTextColor(BLACK);
                            p1_Aces = true;

                            Intent intent = new Intent();
                            intent.putExtra("p1_turnEnd",p1_turnEnd);
                            intent.putExtra("p2_turnEnd",p2_turnEnd);
                            setResult(RESULT_OK,intent);

                            dialogInterface.dismiss();
                            finish();
                        }
                    }).setCancelable(false);
                    builder.setNeutralButton("취소", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }).setCancelable(false);

                    builder.show();
                }
            });
        }

        // p1_Twos 클릭 시 점수 고정하기
        if(!p1_Twos && !p1_turnEnd){
            player_1_Twos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e(TAG, "p1_TwosScore 클릭되나?");
                    AlertDialog.Builder builder = new AlertDialog.Builder(ScorePage.this);
                    builder.setTitle("Twos 점수").setMessage(String.valueOf(player1_Twos));
                    builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            p1_turnEnd = true;
                            p2_turnEnd = false;

                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("p1_Twos",String.valueOf(player1_Twos));
                            editor.commit();

                            player_1_Twos.setText(String.valueOf(player1_Twos));
                            player_1_Twos.setTextColor(BLACK);
                            p1_Twos = true;

                            Intent intent = new Intent();
                            intent.putExtra("p1_turnEnd",p1_turnEnd);
                            intent.putExtra("p2_turnEnd",p2_turnEnd);
                            setResult(RESULT_OK,intent);

                            dialogInterface.dismiss();
                            finish();
                        }
                    }).setCancelable(false);
                    builder.setNeutralButton("취소", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }).setCancelable(false);
                    builder.show();
                }
            });
        }

        // p1_Threes 클릭 시 점수 고정하기
        if (!p1_Threes && !p1_turnEnd) {
            player_1_Threes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e(TAG, "p1_ThreesScore 클릭되나?");
                    AlertDialog.Builder builder = new AlertDialog.Builder(ScorePage.this);
                    builder.setTitle("Threes 점수").setMessage(String.valueOf(player1_Threes));
                    builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            p1_turnEnd = true;
                            p2_turnEnd = false;

                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("p1_Threes",String.valueOf(player1_Threes));
                            editor.commit();

                            player_1_Threes.setText(String.valueOf(player1_Threes));
                            player_1_Threes.setTextColor(BLACK);
                            p1_Threes = true;

                            Intent intent = new Intent();
                            intent.putExtra("p1_turnEnd",p1_turnEnd);
                            intent.putExtra("p2_turnEnd",p2_turnEnd);
                            setResult(RESULT_OK,intent);

                            dialogInterface.dismiss();
                            finish();
                        }
                    }).setCancelable(false);
                    builder.setNeutralButton("취소", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }).setCancelable(false);
                    builder.show();
                }
            });
        }

        // p1_Fours 클릭 시 점수 고정하기
        if (!p1_Fours && !p1_turnEnd) {
            player_1_Fours.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e(TAG, "p1_FoursScore 클릭되나?");
                    AlertDialog.Builder builder = new AlertDialog.Builder(ScorePage.this);
                    builder.setTitle("Fours 점수").setMessage(String.valueOf(player1_Fours));
                    builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            p1_turnEnd = true;
                            p2_turnEnd = false;

                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("p1_Fours",String.valueOf(player1_Fours));
                            editor.commit();

                            player_1_Fours.setText(String.valueOf(player1_Fours));
                            player_1_Fours.setTextColor(BLACK);
                            p1_Fours = true;

                            Intent intent = new Intent();
                            intent.putExtra("p1_turnEnd",p1_turnEnd);
                            intent.putExtra("p2_turnEnd",p2_turnEnd);
                            setResult(RESULT_OK,intent);

                            dialogInterface.dismiss();
                            finish();
                        }
                    }).setCancelable(false);
                    builder.setNeutralButton("취소", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }).setCancelable(false);
                    builder.show();
                }
            });
        }

        // p1_Fives 클릭 시 점수 고정하기
        if(!p1_Fives && !p1_turnEnd){
            player_1_Fives.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e(TAG, "p1_FivesScore 클릭되나?");
                    AlertDialog.Builder builder = new AlertDialog.Builder(ScorePage.this);
                    builder.setTitle("Fives 점수").setMessage(String.valueOf(player1_Fives));
                    builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            p1_turnEnd = true;
                            p2_turnEnd = false;

                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("p1_Fives",String.valueOf(player1_Fives));
                            editor.commit();

                            player_1_Fives.setText(String.valueOf(player1_Fives));
                            player_1_Fives.setTextColor(BLACK);
                            p1_Fives = true;

                            Intent intent = new Intent();
                            intent.putExtra("p1_turnEnd",p1_turnEnd);
                            intent.putExtra("p2_turnEnd",p2_turnEnd);
                            setResult(RESULT_OK,intent);

                            dialogInterface.dismiss();
                            finish();
                        }
                    }).setCancelable(false);
                    builder.setNeutralButton("취소", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }).setCancelable(false);
                    builder.show();
                }
            });
        }

        // p1_Sixes 클릭 시 점수 고정하기
        if(!p1_Sixes && !p1_turnEnd){
            player_1_Sixes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e(TAG, "p1_SixesScore 클릭되나?");
                    AlertDialog.Builder builder = new AlertDialog.Builder(ScorePage.this);
                    builder.setTitle("Sixes 점수").setMessage(String.valueOf(player1_Sixes));
                    builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            p1_turnEnd = true;
                            p2_turnEnd = false;

                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("p1_Sixes",String.valueOf(player1_Sixes));
                            editor.commit();

                            player_1_Sixes.setText(String.valueOf(player1_Sixes));
                            player_1_Sixes.setTextColor(BLACK);
                            p1_Sixes = true;

                            Intent intent = new Intent();
                            intent.putExtra("p1_turnEnd",p1_turnEnd);
                            intent.putExtra("p2_turnEnd",p2_turnEnd);
                            setResult(RESULT_OK,intent);

                            dialogInterface.dismiss();
                            finish();
                        }
                    }).setCancelable(false);
                    builder.setNeutralButton("취소", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }).setCancelable(false);
                    builder.show();
                }
            });
        }

        // p1_TOAK 클릭 시 점수 고정하기
        if (!p1_TOAK && !p1_turnEnd){
            player_1_TOAK.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e(TAG, "p1_TOAKScore 클릭되나?");
                    AlertDialog.Builder builder = new AlertDialog.Builder(ScorePage.this);
                    builder.setTitle("Three Of A Kind 점수").setMessage(String.valueOf(player1_ThreeOfAKind));
                    builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            p1_turnEnd = true;
                            p2_turnEnd = false;

                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("p1_TOAK",String.valueOf(player1_ThreeOfAKind));
                            if (p1_YACHTZEE) {
                                if (dice1eye == dice2eye && dice2eye == dice3eye && dice3eye == dice4eye && dice4eye == dice5eye) {
                                    String TOAK_YACHTZEE = "TOAK_YACHTZEE";
                                    editor.putString("p1_TOAK_YACHTZEE", TOAK_YACHTZEE);
                                }
                            }

                            editor.commit();

                            player_1_TOAK.setText(String.valueOf(player1_ThreeOfAKind));
                            player_1_TOAK.setTextColor(BLACK);
                            p1_TOAK = true;

                            Intent intent = new Intent();
                            intent.putExtra("p1_turnEnd",p1_turnEnd);
                            intent.putExtra("p2_turnEnd",p2_turnEnd);
                            setResult(RESULT_OK,intent);

                            dialogInterface.dismiss();
                            finish();
                        }
                    }).setCancelable(false);
                    builder.setNeutralButton("취소", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }).setCancelable(false);
                    builder.show();
                }
            });
        }

        // p1_FOAK 클릭 시 점수 고정하기
        if (!p1_FOAK && !p1_turnEnd) {
            player_1_FOAK.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e(TAG, "p1_FOAKScore 클릭되나?");
                    AlertDialog.Builder builder = new AlertDialog.Builder(ScorePage.this);
                    builder.setTitle("Four Of A Kind 점수").setMessage(String.valueOf(player1_FourOfAKind));
                    builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            p1_turnEnd = true;
                            p2_turnEnd = false;

                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("p1_FOAK",String.valueOf(player1_FourOfAKind));
                            if (p1_YACHTZEE) {
                                if (dice1eye == dice2eye && dice2eye == dice3eye && dice3eye == dice4eye && dice4eye == dice5eye) {
                                    String FOAK_YACHTZEE = "FOAK_YACHTZEE";
                                    editor.putString("p1_FOAK_YACHTZEE", FOAK_YACHTZEE);
                                }
                            }
                            editor.commit();

                            player_1_FOAK.setText(String.valueOf(player1_FourOfAKind));
                            player_1_FOAK.setTextColor(BLACK);
                            p1_FOAK = true;

                            Intent intent = new Intent();
                            intent.putExtra("p1_turnEnd",p1_turnEnd);
                            intent.putExtra("p2_turnEnd",p2_turnEnd);
                            setResult(RESULT_OK,intent);

                            dialogInterface.dismiss();
                            finish();
                        }
                    }).setCancelable(false);
                    builder.setNeutralButton("취소", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }).setCancelable(false);
                    builder.show();
                }
            });
        }

        // p1_FH 클릭 시 점수 고정하기
        if (!p1_FH && !p1_turnEnd) {
            player_1_FH.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e(TAG, "p1_FHScore 클릭되나?");
                    AlertDialog.Builder builder = new AlertDialog.Builder(ScorePage.this);
                    builder.setTitle("FullHouse 점수").setMessage(String.valueOf(player1_FullHouse));
                    builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            p1_turnEnd = true;
                            p2_turnEnd = false;

                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("p1_FH",String.valueOf(player1_FullHouse));
                            if (p1_YACHTZEE) {
                                if (p1_Aces || p1_Twos || p1_Threes || p1_Fours || p1_Fives || p1_Sixes) {
                                    if (dice1eye == dice2eye && dice2eye == dice3eye && dice3eye == dice4eye && dice4eye == dice5eye) {
                                        String FH_YACHTZEE = "FH_YACHTZEE";
                                        editor.putString("p1_FH_YACHTZEE", FH_YACHTZEE);
                                    }
                                }
                            }
                            editor.commit();

                            player_1_FH.setText(String.valueOf(player1_FullHouse));
                            player_1_FH.setTextColor(BLACK);
                            p1_FH = true;

                            Intent intent = new Intent();
                            intent.putExtra("p1_turnEnd",p1_turnEnd);
                            intent.putExtra("p2_turnEnd",p2_turnEnd);
                            setResult(RESULT_OK,intent);

                            dialogInterface.dismiss();
                            finish();
                        }
                    }).setCancelable(false);
                    builder.setNeutralButton("취소", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }).setCancelable(false);
                    builder.show();
                }
            });
        }

        // p1_SS 클릭 시 점수 고정하기
        if (!p1_SS && !p1_turnEnd) {
            player_1_SS.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e(TAG, "p1_SSScore 클릭되나?");
                    AlertDialog.Builder builder = new AlertDialog.Builder(ScorePage.this);
                    builder.setTitle("SmallStraight 점수").setMessage(String.valueOf(player1_SmallStraight));
                    builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            p1_turnEnd = true;
                            p2_turnEnd = false;

                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("p1_SS",String.valueOf(player1_SmallStraight));
                            if (p1_YACHTZEE) {
                                if (p1_Aces || p1_Twos || p1_Threes || p1_Fours || p1_Fives || p1_Sixes) {
                                    if (dice1eye == dice2eye && dice2eye == dice3eye && dice3eye == dice4eye && dice4eye == dice5eye) {
                                        String SS_YACHTZEE = "SS_YACHTZEE";
                                        editor.putString("p1_SS_YACHTZEE", SS_YACHTZEE);
                                    }
                                }
                            }
                            editor.commit();

                            player_1_SS.setText(String.valueOf(player1_SmallStraight));
                            player_1_SS.setTextColor(BLACK);
                            p1_SS = true;

                            Intent intent = new Intent();
                            intent.putExtra("p1_turnEnd",p1_turnEnd);
                            intent.putExtra("p2_turnEnd",p2_turnEnd);
                            setResult(RESULT_OK,intent);

                            dialogInterface.dismiss();
                            finish();
                        }
                    }).setCancelable(false);
                    builder.setNeutralButton("취소", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }).setCancelable(false);
                    builder.show();
                }
            });
        }

        // p1_LS 클릭 시 점수 고정하기
        if (!p1_LS && !p1_turnEnd) {
            player_1_LS.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e(TAG, "p1_LSScore 클릭되나?");
                    AlertDialog.Builder builder = new AlertDialog.Builder(ScorePage.this);
                    builder.setTitle("LargeStraight 점수").setMessage(String.valueOf(player1_LargeStraight));
                    builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            p1_turnEnd = true;
                            p2_turnEnd = false;

                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("p1_LS",String.valueOf(player1_LargeStraight));
                            if (p1_YACHTZEE){
                                if (p1_Aces || p1_Twos || p1_Threes || p1_Fours || p1_Fives || p1_Sixes) {
                                    if (dice1eye == dice2eye && dice2eye == dice3eye && dice3eye == dice4eye && dice4eye == dice5eye) {
                                        String LS_YACHTZEE = "LS_YACHTZEE";
                                        editor.putString("p1_LS_YACHTZEE", LS_YACHTZEE);
                                    }
                                }
                            }
                            editor.commit();

                            player_1_LS.setText(String.valueOf(player1_LargeStraight));
                            player_1_LS.setTextColor(BLACK);
                            p1_LS = true;

                            Intent intent = new Intent();
                            intent.putExtra("p1_turnEnd",p1_turnEnd);
                            intent.putExtra("p2_turnEnd",p2_turnEnd);
                            setResult(RESULT_OK,intent);

                            dialogInterface.dismiss();
                            finish();
                        }
                    }).setCancelable(false);
                    builder.setNeutralButton("취소", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }).setCancelable(false);
                    builder.show();
                }
            });
        }

        // p1_Chance 클릭 시 점수 고정하기
        if (!p1_Chance && !p1_turnEnd) {
            player_1_Chance.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e(TAG, "p1_ChanceScore 클릭되나?");
                    AlertDialog.Builder builder = new AlertDialog.Builder(ScorePage.this);
                    builder.setTitle("Chance 점수").setMessage(String.valueOf(player1_Chance));
                    builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            p1_turnEnd = true;
                            p2_turnEnd = false;

                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("p1_Chance",String.valueOf(player1_Chance));
                            if (p1_YACHTZEE) {
                                if (dice1eye == dice2eye && dice2eye == dice3eye && dice3eye == dice4eye && dice4eye == dice5eye) {
                                    String Chance_YACHTZEE = "Chance_YACHTZEE";
                                    editor.putString("p1_Chance_YACHTZEE", Chance_YACHTZEE);
                                }
                            }
                            editor.commit();

                            player_1_Chance.setText(String.valueOf(player1_Chance));
                            player_1_Chance.setTextColor(BLACK);
                            p1_LS = true;

                            Intent intent = new Intent();
                            intent.putExtra("p1_turnEnd",p1_turnEnd);
                            intent.putExtra("p2_turnEnd",p2_turnEnd);
                            setResult(RESULT_OK,intent);

                            dialogInterface.dismiss();
                            finish();
                        }
                    }).setCancelable(false);
                    builder.setNeutralButton("취소", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }).setCancelable(false);
                    builder.show();
                }
            });
        }

        // p1_YACHTZEE 클릭 시 점수 고정하기
        if (!p1_YACHTZEE && !p1_turnEnd) {
            player_1_YACHTZEE.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e(TAG, "p1_YACHTZEE 클릭되나?");
                    AlertDialog.Builder builder = new AlertDialog.Builder(ScorePage.this);
                    builder.setTitle("YACHTZEE 점수").setMessage(String.valueOf(player1_YACHTZEE));
                    builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            p1_turnEnd = true;
                            p2_turnEnd = false;

                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("p1_YACHTZEE",String.valueOf(player1_YACHTZEE));
                            editor.commit();

                            player_1_YACHTZEE.setText(String.valueOf(player1_YACHTZEE));
                            player_1_YACHTZEE.setTextColor(BLACK);
                            p1_YACHTZEE = true;

                            Intent intent = new Intent();
                            intent.putExtra("p1_turnEnd",p1_turnEnd);
                            intent.putExtra("p2_turnEnd",p2_turnEnd);
                            setResult(RESULT_OK,intent);

                            dialogInterface.dismiss();
                            finish();
                        }
                    }).setCancelable(false);
                    builder.setNeutralButton("취소", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }).setCancelable(false);
                    builder.show();
                }
            });
        }


        // p2_Aces 점수 가져오기
        TextView player_2_Aces = (TextView) findViewById(R.id.acesScore_P2);
        if (cpu_Aces) {
            player_2_Aces.setText(sharedPreferences2.getString("cpu_Aces", ""));
            player_2_Aces.setTextColor(BLACK);
        }

        // p2_Twos 점수 가져오기
        TextView player_2_Twos = (TextView) findViewById(R.id.twosScore_P2);
        if (cpu_Twos) {
            player_2_Twos.setText(sharedPreferences2.getString("cpu_Twos", ""));
            player_2_Twos.setTextColor(BLACK);
        }

        // p2_Threes 점수 가져오기
        TextView player_2_Threes = (TextView) findViewById(R.id.threesScore_P2);
        if (cpu_Threes) {
            player_2_Threes.setText(sharedPreferences2.getString("cpu_Threes", ""));
            player_2_Threes.setTextColor(BLACK);
        }

        // p2_Fours 점수 가져오기
        TextView player_2_Fours = (TextView) findViewById(R.id.foursScore_P2);
        if (cpu_Fours) {
            player_2_Fours.setText(sharedPreferences2.getString("cpu_Fours", ""));
            player_2_Fours.setTextColor(BLACK);
        }

        // p2_Fives 점수 가져오기
        TextView player_2_Fives = (TextView) findViewById(R.id.fivesScore_P2);
        if (cpu_Fives) {
            player_2_Fives.setText(sharedPreferences2.getString("cpu_Fives", ""));
            player_2_Fives.setTextColor(BLACK);
        }

        // p2_Sixes 점수 가져오기
        TextView player_2_Sixes = (TextView) findViewById(R.id.sixesScore_P2);
        if (cpu_Sixes) {
            player_2_Sixes.setText(sharedPreferences2.getString("cpu_Sixes", ""));
            player_2_Sixes.setTextColor(BLACK);
        }

        // p2_Bonus 점수 가져오기
        TextView player_2_Bonus = (TextView) findViewById(R.id.bonusScore_P2);
        if (cpu_Aces && cpu_Twos && cpu_Threes && cpu_Fours && cpu_Fives && cpu_Sixes) {
            int aces, twos, threes, fours, fives, sixes;

            if (player_2_Aces.getText().toString() != null
                    && player_2_Twos.getText().toString() != null
                    && player_2_Threes.getText().toString() != null
                    && player_2_Fours.getText().toString() != null
                    && player_2_Fives.getText().toString() != null
                    && player_2_Sixes.getText().toString() != null) {

                try {
                    aces = Integer.parseInt(player_2_Aces.getText().toString());
                    twos = Integer.parseInt(player_2_Twos.getText().toString());
                    threes = Integer.parseInt(player_2_Threes.getText().toString());
                    fours = Integer.parseInt(player_2_Fours.getText().toString());
                    fives = Integer.parseInt(player_2_Fives.getText().toString());
                    sixes = Integer.parseInt(player_2_Sixes.getText().toString());

                    if (aces + twos + threes + fours + fives + sixes >= 63) {
                        cpu_Bonus = 35;
                        player_2_Bonus.setText(String.valueOf(cpu_Bonus));
                        player_2_Bonus.setTextColor(BLACK);
                    }else {
                        cpu_Bonus = 0;
                        player_2_Bonus.setText(String.valueOf(cpu_Bonus));
                        player_2_Bonus.setTextColor(BLACK);
                    }

                } catch (NumberFormatException e) {

                } catch (Exception e) {

                }
            }
        }

        // p2_Sum 점수 가져오기
        TextView player_2_Sum = (TextView) findViewById(R.id.sumScore_P2);
        if (cpu_Aces && cpu_Twos && cpu_Threes && cpu_Fours && cpu_Fives && cpu_Sixes) {
            int aces, twos, threes, fours, fives, sixes;

            if (player_2_Aces.getText().toString() != null
                    && player_2_Twos.getText().toString() != null
                    && player_2_Threes.getText().toString() != null
                    && player_2_Fours.getText().toString() != null
                    && player_2_Fives.getText().toString() != null
                    && player_2_Sixes.getText().toString() != null) {

                try {
                    aces = Integer.parseInt(player_2_Aces.getText().toString());
                    twos = Integer.parseInt(player_2_Twos.getText().toString());
                    threes = Integer.parseInt(player_2_Threes.getText().toString());
                    fours = Integer.parseInt(player_2_Fours.getText().toString());
                    fives = Integer.parseInt(player_2_Fives.getText().toString());
                    sixes = Integer.parseInt(player_2_Sixes.getText().toString());

                    cpu_Sum = aces + twos + threes + fours + fives + sixes;
                    player_2_Sum.setText(String.valueOf(cpu_Sum));
                    player_2_Sum.setTextColor(BLACK);

                } catch (NumberFormatException e) {

                } catch (Exception e) {

                }
            }
        }

        // p2_TOAK 점수 가져오기
        TextView player_2_TOAK = (TextView)findViewById(R.id.threeOfAkindScore_P2);
        if (cpu_TOAK) {
            player_2_TOAK.setText(sharedPreferences2.getString("cpu_TOAK", ""));
            player_2_TOAK.setTextColor(BLACK);
        }

        // p2_FOAK 점수 가져오기
        TextView player_2_FOAK = (TextView)findViewById(R.id.fourOfAkindScore_P2);
        if (cpu_FOAK) {
            player_2_FOAK.setText(sharedPreferences2.getString("cpu_FOAK", ""));
            player_2_FOAK.setTextColor(BLACK);
        }

        // p2_FH 점수 가져오기
        TextView player_2_FH = (TextView)findViewById(R.id.fullHouseScore_P2);
        if (cpu_FH) {
            player_2_FH.setText(sharedPreferences2.getString("cpu_FH", ""));
            player_2_FH.setTextColor(BLACK);
        }

        // p2_SS 점수 가져오기
        TextView player_2_SS = (TextView)findViewById(R.id.smallStraightScore_P2);
        if (cpu_SS) {
            player_2_SS.setText(sharedPreferences2.getString("cpu_SS", ""));
            player_2_SS.setTextColor(BLACK);
        }

        // p2_LS 점수 가져오기
        TextView player_2_LS = (TextView)findViewById(R.id.largeStraightScore_P2);
        if (cpu_LS) {
            player_2_LS.setText(sharedPreferences2.getString("cpu_LS", ""));
            player_2_LS.setTextColor(BLACK);
        }

        // p2_Chance 점수 가져오기
        TextView player_2_Chance = (TextView)findViewById(R.id.chanceScore_P2);
        if (cpu_Chance) {
            player_2_Chance.setText(sharedPreferences2.getString("cpu_Chance", ""));
            player_2_Chance.setTextColor(BLACK);
        }

        // p2_YACHTZEE 점수 가져오기
        TextView player_2_YACHTZEE = (TextView)findViewById(R.id.yachtzeeScore_P2);
        if (cpu_YACHTZEE) {
            cpu_YACHT = 50;
            player_2_YACHTZEE.setText(sharedPreferences2.getString("cpu_YACHTZEE", ""));
            player_2_YACHTZEE.setTextColor(BLACK);
        }

        // p2_YACHTBonus 점수 가져오기
        TextView player_2_YACHTBonus = (TextView)findViewById(R.id.yachtBonusScore_P2);
        if (cpu_YACHTZEE && cpu_YACHT != 0) {
            int aces,twos,threes,fours,fives,sixes;


            if (player_2_Aces.getText().toString() != null
                    && player_2_Twos.getText().toString() != null
                    && player_2_Threes.getText().toString() != null
                    && player_2_Fours.getText().toString() != null
                    && player_2_Fives.getText().toString() != null
                    && player_2_Sixes.getText().toString() != null) {
                try {

                    aces = Integer.parseInt(player_2_Aces.getText().toString());
                    twos = Integer.parseInt(player_2_Twos.getText().toString());
                    threes = Integer.parseInt(player_2_Threes.getText().toString());
                    fours = Integer.parseInt(player_2_Fours.getText().toString());
                    fives = Integer.parseInt(player_2_Fives.getText().toString());
                    sixes = Integer.parseInt(player_2_Sixes.getText().toString());


                    if (aces == 5 && cpu_Aces) {
                        cpuAcesYA = 50;
                    }
                    if (twos == 10 && p1_Twos) {
                        cpuTwosYA = 50;
                    }
                    if (threes == 15 && p1_Threes) {
                        cpuThreesYA = 50;
                    }
                    if (fours == 20 && p1_Fours) {
                        cpuFoursYA = 50;
                    }
                    if (fives == 25 && p1_Fives) {
                        cpuFivesYA = 50;
                    }
                    if (sixes == 30 && p1_Sixes) {
                        cpuSixesYA = 50;
                    }
                    if (sharedPreferences2.getString("TOAK_YACHTZEE", "") == "TOAK_YACHTZEE") {
                        cpuTOAKYA = 50;
                    }
                    if (sharedPreferences2.getString("FOAK_YACHTZEE", "") == "FOAK_YACHTZEE") {
                        cpuFOAKYA = 50;
                    }
                    if (sharedPreferences2.getString("FH_YACHTZEE", "") == "FH_YACHTZEE") {
                        cpuFHYA = 50;
                    }
                    if (sharedPreferences2.getString("SS_YACHTZEE", "") == "SS_YACHTZEE") {
                        cpuSSYA = 50;
                    }
                    if (sharedPreferences2.getString("LS_YACHTZEE", "") == "LS_YACHTZEE") {
                        cpuLSYA = 50;
                    }
                    if (sharedPreferences2.getString("Chance_YACHTZEE", "") == "Chance_YACHTZEE") {
                        cpuChanceYA = 50;
                    }

                    cpu_YACHTBonus = cpuAcesYA + cpuTwosYA + cpuThreesYA + cpuFoursYA + cpuFivesYA + cpuSixesYA
                            + cpuTOAKYA + cpuFOAKYA + cpuFHYA + cpuSSYA + cpuLSYA + cpuChanceYA;

                    if (cpu_YACHTBonus != 0) {
                        player_2_YACHTBonus.setText(String.valueOf(cpu_YACHTBonus));
                        player_2_YACHTBonus.setTextColor(BLACK);
                    }else {
                        player_2_YACHTBonus.setText(String.valueOf(0));
                        player_2_YACHTBonus.setTextColor(BLACK);
                    }

                } catch (NumberFormatException e) {

                } catch (Exception e) {

                }
            }
        }else if (cpu_YACHT == 0 && cpu_YACHTZEE) {
            player_2_YACHTBonus.setText(String.valueOf(0));
            player_2_YACHTBonus.setTextColor(BLACK);
        }

        // p2_TotalScore 점수 가져오기
        TextView player_2_TotalScore = (TextView)findViewById(R.id.totalScore_P2);
        if(cpu_Aces && cpu_Twos && cpu_Threes && cpu_Fours && cpu_Fives && cpu_Sixes
                && cpu_TOAK && cpu_FOAK && cpu_FH && cpu_SS && cpu_LS && cpu_Chance
                && sharedPreferences2.getString("cpu_YACHTZEE","") != ""){
            int aces,twos,threes,fours,fives,sixes;
            int toak,foak,fh,ss,ls,chance;

            if (player_2_Aces.getText().toString() != null
                    && player_2_Twos.getText().toString() != null
                    && player_2_Threes.getText().toString() != null
                    && player_2_Fours.getText().toString() != null
                    && player_2_Fives.getText().toString() != null
                    && player_2_Sixes.getText().toString() != null){

                try {

                    aces = Integer.parseInt(player_2_Aces.getText().toString());
                    twos = Integer.parseInt(player_2_Twos.getText().toString());
                    threes = Integer.parseInt(player_2_Threes.getText().toString());
                    fours = Integer.parseInt(player_2_Fours.getText().toString());
                    fives = Integer.parseInt(player_2_Fives.getText().toString());
                    sixes = Integer.parseInt(player_2_Sixes.getText().toString());
                    toak = Integer.parseInt(player_2_TOAK.getText().toString());
                    foak = Integer.parseInt(player_2_FOAK.getText().toString());
                    fh = Integer.parseInt(player_2_FH.getText().toString());
                    ss = Integer.parseInt(player_2_SS.getText().toString());
                    ls = Integer.parseInt(player_2_LS.getText().toString());
                    chance = Integer.parseInt(player_2_Chance.getText().toString());

                    cpu_TotalScore = aces + twos + threes + fours + fives + sixes + cpu_Bonus + toak + foak + fh + ss + ls + chance + cpu_YACHT + cpu_YACHTBonus;

                }catch (NumberFormatException e){

                }catch (Exception e){

                }

            }

            player_2_TotalScore.setText(String.valueOf(cpu_TotalScore));
            player_2_TotalScore.setTextColor(BLACK);

            SharedPreferences.Editor editor = sharedPreferences2.edit();
            editor.putString("cpu_TotalScore",String.valueOf(cpu_TotalScore));
            editor.commit();
        }



        Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);

        int pointWidth = point.x; // 가로
        int pointHeight = point.y; // 세로

        int width = (int) (pointWidth * 0.9); // Display 가로 사이즈
        int height = (int) (pointHeight * 1.0);  // Display 높이 사이즈

        getWindow().getAttributes().width = width; // 가로 크기
        getWindow().getAttributes().height = height; // 세로 크기
        getWindow().getAttributes().gravity = Gravity.RIGHT; // 위치 설정

        // 닫기
        Button close_bt = (Button) findViewById(R.id.closeScore);
        close_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    // Three Of A Kind 에서 주사위 눈 세개이상 같을 때
    public boolean threeEyeCheck(){

        if (dice2eye == dice1eye && dice3eye == dice1eye
        || dice2eye == dice1eye && dice4eye == dice1eye
        || dice2eye == dice1eye && dice5eye == dice1eye
        || dice3eye == dice1eye && dice4eye == dice1eye
        || dice3eye == dice1eye && dice5eye == dice1eye
        || dice4eye == dice1eye && dice5eye == dice1eye

        || dice1eye == dice2eye && dice3eye == dice2eye
        || dice1eye == dice2eye && dice4eye == dice2eye
        || dice1eye == dice2eye && dice5eye == dice2eye
        || dice3eye == dice2eye && dice4eye == dice2eye
        || dice3eye == dice2eye && dice5eye == dice2eye
        || dice4eye == dice2eye && dice5eye == dice2eye

        || dice1eye == dice3eye && dice2eye == dice3eye
        || dice1eye == dice3eye && dice4eye == dice3eye
        || dice1eye == dice3eye && dice5eye == dice3eye
        || dice2eye == dice3eye && dice4eye == dice3eye
        || dice2eye == dice3eye && dice5eye == dice3eye
        || dice4eye == dice3eye && dice5eye == dice3eye

        || dice1eye == dice4eye && dice2eye == dice4eye
        || dice1eye == dice4eye && dice3eye == dice4eye
        || dice1eye == dice4eye && dice5eye == dice4eye
        || dice2eye == dice4eye && dice3eye == dice4eye
        || dice2eye == dice4eye && dice5eye == dice4eye
        || dice3eye == dice4eye && dice5eye == dice4eye

        || dice1eye == dice5eye && dice2eye == dice5eye
        || dice1eye == dice5eye && dice3eye == dice5eye
        || dice1eye == dice5eye && dice4eye == dice5eye
        || dice2eye == dice5eye && dice3eye == dice5eye
        || dice2eye == dice5eye && dice4eye == dice5eye
        || dice3eye == dice5eye && dice4eye == dice5eye){
            return true;
        }

        return false;
    }

    // Four Of A Kind 에서 주사위 눈 네개이상 같을 때
    public boolean fourEyeCheck(){

        if(dice2eye == dice1eye && dice3eye == dice1eye && dice4eye == dice1eye
        || dice2eye == dice1eye && dice3eye == dice1eye && dice5eye == dice1eye
        || dice2eye == dice1eye && dice4eye == dice1eye && dice5eye == dice1eye
        || dice3eye == dice1eye && dice4eye == dice1eye && dice5eye == dice1eye

        || dice1eye == dice2eye && dice3eye == dice2eye && dice4eye == dice2eye
        || dice1eye == dice2eye && dice3eye == dice2eye && dice5eye == dice2eye
        || dice1eye == dice2eye && dice4eye == dice2eye && dice5eye == dice2eye
        || dice3eye == dice2eye && dice4eye == dice2eye && dice5eye == dice2eye

        || dice1eye == dice3eye && dice2eye == dice3eye && dice4eye == dice3eye
        || dice1eye == dice3eye && dice2eye == dice3eye && dice5eye == dice3eye
        || dice1eye == dice3eye && dice4eye == dice3eye && dice5eye == dice3eye
        || dice2eye == dice3eye && dice4eye == dice3eye && dice5eye == dice3eye

        || dice1eye == dice4eye && dice2eye == dice4eye && dice3eye == dice4eye
        || dice1eye == dice4eye && dice2eye == dice4eye && dice5eye == dice4eye
        || dice1eye == dice4eye && dice3eye == dice4eye && dice5eye == dice4eye
        || dice2eye == dice4eye && dice3eye == dice4eye && dice5eye == dice4eye

        || dice1eye == dice5eye && dice2eye == dice5eye && dice3eye == dice5eye
        || dice1eye == dice5eye && dice2eye == dice5eye && dice4eye == dice5eye
        || dice1eye == dice5eye && dice3eye == dice5eye && dice4eye == dice5eye
        || dice2eye == dice5eye && dice3eye == dice5eye && dice4eye == dice5eye){
            return true;
        }

        return false;

    }

    // FullHouse 주사위 눈 체크
    public boolean fullHouseEyeCheck(){

        if        (dice2eye == dice1eye && dice3eye == dice1eye && dice4eye != dice1eye && dice4eye == dice5eye
                || dice2eye == dice1eye && dice4eye == dice1eye && dice3eye != dice1eye && dice3eye == dice5eye
                || dice2eye == dice1eye && dice5eye == dice1eye && dice3eye != dice1eye && dice3eye == dice4eye
                || dice3eye == dice1eye && dice4eye == dice1eye && dice2eye != dice1eye && dice2eye == dice5eye
                || dice3eye == dice1eye && dice5eye == dice1eye && dice2eye != dice1eye && dice2eye == dice4eye
                || dice4eye == dice1eye && dice5eye == dice1eye && dice2eye != dice1eye && dice2eye == dice3eye

                || dice1eye == dice2eye && dice3eye == dice2eye && dice4eye != dice2eye && dice4eye == dice5eye
                || dice1eye == dice2eye && dice4eye == dice2eye && dice3eye != dice2eye && dice3eye == dice5eye
                || dice1eye == dice2eye && dice5eye == dice2eye && dice3eye != dice2eye && dice3eye == dice4eye
                || dice3eye == dice2eye && dice4eye == dice2eye && dice1eye != dice2eye && dice1eye == dice5eye
                || dice3eye == dice2eye && dice5eye == dice2eye && dice1eye != dice2eye && dice1eye == dice4eye
                || dice4eye == dice2eye && dice5eye == dice2eye && dice1eye != dice2eye && dice1eye == dice3eye

                || dice1eye == dice3eye && dice2eye == dice3eye && dice4eye != dice3eye && dice4eye == dice5eye
                || dice1eye == dice3eye && dice4eye == dice3eye && dice2eye != dice3eye && dice2eye == dice5eye
                || dice1eye == dice3eye && dice5eye == dice3eye && dice2eye != dice3eye && dice2eye == dice4eye
                || dice2eye == dice3eye && dice4eye == dice3eye && dice1eye != dice3eye && dice1eye == dice5eye
                || dice2eye == dice3eye && dice5eye == dice3eye && dice1eye != dice3eye && dice1eye == dice4eye
                || dice4eye == dice3eye && dice5eye == dice3eye && dice1eye != dice3eye && dice1eye == dice2eye

                || dice1eye == dice4eye && dice2eye == dice4eye && dice3eye != dice4eye && dice3eye == dice5eye
                || dice1eye == dice4eye && dice3eye == dice4eye && dice2eye != dice4eye && dice2eye == dice5eye
                || dice1eye == dice4eye && dice5eye == dice4eye && dice2eye != dice4eye && dice2eye == dice3eye
                || dice2eye == dice4eye && dice3eye == dice4eye && dice1eye != dice4eye && dice1eye == dice5eye
                || dice2eye == dice4eye && dice5eye == dice4eye && dice1eye != dice4eye && dice1eye == dice3eye
                || dice3eye == dice4eye && dice5eye == dice4eye && dice1eye != dice4eye && dice1eye == dice2eye

                || dice1eye == dice5eye && dice2eye == dice5eye && dice3eye != dice5eye && dice3eye == dice4eye
                || dice1eye == dice5eye && dice3eye == dice5eye && dice2eye != dice5eye && dice2eye == dice4eye
                || dice1eye == dice5eye && dice4eye == dice5eye && dice2eye != dice5eye && dice2eye == dice3eye
                || dice2eye == dice5eye && dice3eye == dice5eye && dice1eye != dice5eye && dice1eye == dice4eye
                || dice2eye == dice5eye && dice4eye == dice5eye && dice1eye != dice5eye && dice1eye == dice3eye
                || dice3eye == dice5eye && dice4eye == dice5eye && dice1eye != dice5eye && dice1eye == dice2eye){
            return true;
        } else if (p1_YACHTZEE) {
            if (p1_Aces) {
                if (dice1eye == 1 && dice1eye == dice2eye && dice2eye == dice3eye && dice3eye == dice4eye && dice4eye == dice5eye) {
                    return true;
                }
            } else if (p1_Twos) {
                if (dice1eye == 2 && dice1eye == dice2eye && dice2eye == dice3eye && dice3eye == dice4eye && dice4eye == dice5eye) {
                    return true;
                }
            } else if (p1_Threes) {
                if (dice1eye == 3 && dice1eye == dice2eye && dice2eye == dice3eye && dice3eye == dice4eye && dice4eye == dice5eye) {
                    return true;
                }
            } else if (p1_Fours) {
                if (dice1eye == 4 && dice1eye == dice2eye && dice2eye == dice3eye && dice3eye == dice4eye && dice4eye == dice5eye) {
                    return true;
                }
            } else if (p1_Fives) {
                if (dice1eye == 5 && dice1eye == dice2eye && dice2eye == dice3eye && dice3eye == dice4eye && dice4eye == dice5eye) {
                    return true;
                }
            } else if (p1_Sixes) {
                if (dice1eye == 6 && dice1eye == dice2eye && dice2eye == dice3eye && dice3eye == dice4eye && dice4eye == dice5eye) {
                    return true;
                }
            }
        }

        return false;
    }

    // SmallStraight 주사위 눈 체크
    public boolean smallStraight(){

        if(dice1eye != dice2eye && dice2eye != dice3eye && dice3eye != dice4eye
        && dice1eye != dice3eye && dice1eye != dice4eye
        && dice2eye != dice4eye){

            if        (dice1eye == 1 && dice2eye <= 4 && dice3eye <= 4 && dice4eye <= 4
                    || dice1eye == 2 && dice2eye <= 5 && dice3eye <= 5 && dice4eye <= 5
                    && dice2eye > 2 && dice3eye > 2 && dice4eye > 2
                    || dice1eye == 3 && dice2eye <= 6 && dice3eye <= 6 && dice4eye <= 6
                    && dice2eye > 3 && dice3eye > 3 && dice4eye > 3

                    || dice2eye == 1 && dice1eye <= 4 && dice3eye <= 4 && dice4eye <= 4
                    || dice2eye == 2 && dice1eye <= 5 && dice3eye <= 5 && dice4eye <= 5
                    && dice1eye > 2 && dice3eye > 2 && dice4eye > 2
                    || dice2eye == 3 && dice1eye <= 6 && dice3eye <= 6 && dice4eye <= 6
                    && dice1eye > 3 && dice3eye > 3 && dice4eye > 3

                    || dice3eye == 1 && dice1eye <= 4 && dice2eye <= 4 && dice4eye <= 4
                    || dice3eye == 2 && dice1eye <= 5 && dice2eye <= 5 && dice4eye <= 5
                    && dice2eye > 2 && dice1eye > 2 && dice4eye > 2
                    || dice3eye == 3 && dice1eye <= 6 && dice2eye <= 6 && dice4eye <= 6
                    && dice2eye > 3 && dice1eye > 3 && dice4eye > 3

                    || dice4eye == 1 && dice1eye <= 4 && dice3eye <= 4 && dice2eye <= 4
                    || dice4eye == 2 && dice1eye <= 5 && dice3eye <= 5 && dice2eye <= 5
                    && dice2eye > 2 && dice1eye > 2 && dice3eye > 2
                    || dice4eye == 3 && dice1eye <= 6 && dice3eye <= 6 && dice2eye <= 6
                    && dice2eye > 3 && dice1eye > 3 && dice3eye > 3){

                return true;

            }

        }

        if( dice1eye != dice2eye && dice2eye != dice3eye && dice3eye != dice5eye
        && dice1eye != dice3eye && dice1eye != dice5eye
        && dice2eye != dice5eye){

            if        (dice1eye == 1 && dice2eye <= 4 && dice3eye <= 4 && dice5eye <= 4
                    || dice1eye == 2 && dice2eye <= 5 && dice3eye <= 5 && dice5eye <= 5
                    && dice2eye > 2 && dice3eye > 2 && dice5eye > 2
                    || dice1eye == 3 && dice2eye <= 6 && dice3eye <= 6 && dice5eye <= 6
                    && dice2eye > 3 && dice3eye > 3 && dice5eye > 3

                    || dice2eye == 1 && dice1eye <= 4 && dice3eye <= 4 && dice5eye <= 4
                    || dice2eye == 2 && dice1eye <= 5 && dice3eye <= 5 && dice5eye <= 5
                    && dice1eye > 2 && dice3eye > 2 && dice5eye > 2
                    || dice2eye == 3 && dice1eye <= 6 && dice3eye <= 6 && dice5eye <= 6
                    && dice1eye > 3 && dice3eye > 3 && dice5eye > 3

                    || dice3eye == 1 && dice1eye <= 4 && dice2eye <= 4 && dice5eye <= 4
                    || dice3eye == 2 && dice1eye <= 5 && dice2eye <= 5 && dice5eye <= 5
                    && dice2eye > 2 && dice1eye > 2 && dice5eye > 2
                    || dice3eye == 3 && dice1eye <= 6 && dice2eye <= 6 && dice5eye <= 6
                    && dice2eye > 3 && dice1eye > 3 && dice5eye > 3

                    || dice5eye == 1 && dice1eye <= 4 && dice3eye <= 4 && dice2eye <= 4
                    || dice5eye == 2 && dice1eye <= 5 && dice3eye <= 5 && dice2eye <= 5
                    && dice2eye > 2 && dice3eye > 2 && dice1eye > 2
                    || dice5eye == 3 && dice1eye <= 6 && dice3eye <= 6 && dice2eye <= 6
                    && dice2eye > 3 && dice3eye > 3 && dice1eye > 3){

                return true;

            }

        }

        if( dice1eye != dice2eye && dice2eye != dice4eye && dice4eye != dice5eye
        && dice1eye != dice4eye && dice1eye != dice5eye
        && dice2eye != dice5eye){

            if        (dice1eye == 1 && dice2eye <= 4 && dice5eye <= 4 && dice4eye <= 4
                    || dice1eye == 2 && dice2eye <= 5 && dice5eye <= 5 && dice4eye <= 5
                    && dice2eye > 2 && dice5eye > 2 && dice4eye > 2
                    || dice1eye == 3 && dice2eye <= 6 && dice5eye <= 6 && dice4eye <= 6
                    && dice2eye > 3 && dice5eye > 3 && dice4eye > 3

                    || dice2eye == 1 && dice1eye <= 4 && dice5eye <= 4 && dice4eye <= 4
                    || dice2eye == 2 && dice1eye <= 5 && dice5eye <= 5 && dice4eye <= 5
                    && dice1eye > 2 && dice5eye > 2 && dice4eye > 2
                    || dice2eye == 3 && dice1eye <= 6 && dice5eye <= 6 && dice4eye <= 6
                    && dice1eye > 3 && dice5eye > 3 && dice4eye > 3

                    || dice5eye == 1 && dice1eye <= 4 && dice2eye <= 4 && dice4eye <= 4
                    || dice5eye == 2 && dice1eye <= 5 && dice2eye <= 5 && dice4eye <= 5
                    && dice2eye > 2 && dice1eye > 2 && dice4eye > 2
                    || dice5eye == 3 && dice1eye <= 6 && dice2eye <= 6 && dice4eye <= 6
                    && dice2eye > 3 && dice1eye > 3 && dice4eye > 3

                    || dice4eye == 1 && dice1eye <= 4 && dice5eye <= 4 && dice2eye <= 4
                    || dice4eye == 2 && dice1eye <= 5 && dice5eye <= 5 && dice2eye <= 5
                    && dice2eye > 2 && dice5eye > 2 && dice1eye > 2
                    || dice4eye == 3 && dice1eye <= 6 && dice5eye <= 6 && dice2eye <= 6
                    && dice2eye > 3 && dice5eye > 3 && dice1eye > 3){

                return true;

            }

        }

        if( dice1eye != dice3eye && dice3eye != dice4eye && dice4eye != dice5eye
        && dice1eye != dice4eye && dice1eye != dice5eye
        && dice3eye != dice5eye){

            if        (dice1eye == 1 && dice5eye <= 4 && dice3eye <= 4 && dice4eye <= 4
                    || dice1eye == 2 && dice5eye <= 5 && dice3eye <= 5 && dice4eye <= 5
                    && dice3eye > 2 && dice5eye > 2 && dice4eye > 2
                    || dice1eye == 3 && dice5eye <= 6 && dice3eye <= 6 && dice4eye <= 6
                    && dice3eye > 3 && dice5eye > 3 && dice4eye > 3

                    || dice5eye == 1 && dice1eye <= 4 && dice3eye <= 4 && dice4eye <= 4
                    || dice5eye == 2 && dice1eye <= 5 && dice3eye <= 5 && dice4eye <= 5
                    && dice3eye > 2 && dice1eye > 2 && dice4eye > 2
                    || dice5eye == 3 && dice1eye <= 6 && dice3eye <= 6 && dice4eye <= 6
                    && dice3eye > 3 && dice1eye > 3 && dice4eye > 3

                    || dice3eye == 1 && dice1eye <= 4 && dice5eye <= 4 && dice4eye <= 4
                    || dice3eye == 2 && dice1eye <= 5 && dice5eye <= 5 && dice4eye <= 5
                    && dice1eye > 2 && dice5eye > 2 && dice4eye > 2
                    || dice3eye == 3 && dice1eye <= 6 && dice5eye <= 6 && dice4eye <= 6
                    && dice1eye > 3 && dice5eye > 3 && dice4eye > 3

                    || dice4eye == 1 && dice1eye <= 4 && dice3eye <= 4 && dice5eye <= 4
                    || dice4eye == 2 && dice1eye <= 5 && dice3eye <= 5 && dice5eye <= 5
                    && dice3eye > 2 && dice5eye > 2 && dice1eye > 2
                    || dice4eye == 3 && dice1eye <= 6 && dice3eye <= 6 && dice5eye <= 6
                    && dice3eye > 3 && dice5eye > 3 && dice1eye > 3){

                return true;

            }

        }

        if( dice2eye != dice3eye && dice3eye != dice4eye && dice4eye != dice5eye
        && dice2eye != dice4eye && dice2eye != dice5eye
        && dice3eye != dice5eye){

            if        (dice5eye == 1 && dice2eye <= 4 && dice3eye <= 4 && dice4eye <= 4
                    || dice5eye == 2 && dice2eye <= 5 && dice3eye <= 5 && dice4eye <= 5
                    && dice3eye > 2 && dice2eye > 2 && dice4eye > 2
                    || dice5eye == 3 && dice2eye <= 6 && dice3eye <= 6 && dice4eye <= 6
                    && dice3eye > 3 && dice2eye > 3 && dice4eye > 3

                    || dice2eye == 1 && dice5eye <= 4 && dice3eye <= 4 && dice4eye <= 4
                    || dice2eye == 2 && dice5eye <= 5 && dice3eye <= 5 && dice4eye <= 5
                    && dice3eye > 2 && dice5eye > 2 && dice4eye > 2
                    || dice2eye == 3 && dice5eye <= 6 && dice3eye <= 6 && dice4eye <= 6
                    && dice3eye > 3 && dice5eye > 3 && dice4eye > 3

                    || dice3eye == 1 && dice5eye <= 4 && dice2eye <= 4 && dice4eye <= 4
                    || dice3eye == 2 && dice5eye <= 5 && dice2eye <= 5 && dice4eye <= 5
                    && dice5eye > 2 && dice2eye > 2 && dice4eye > 2
                    || dice3eye == 3 && dice5eye <= 6 && dice2eye <= 6 && dice4eye <= 6
                    && dice5eye > 3 && dice2eye > 3 && dice4eye > 3

                    || dice4eye == 1 && dice5eye <= 4 && dice3eye <= 4 && dice2eye <= 4
                    || dice4eye == 2 && dice5eye <= 5 && dice3eye <= 5 && dice2eye <= 5
                    && dice3eye > 2 && dice2eye > 2 && dice5eye > 2
                    || dice4eye == 3 && dice5eye <= 6 && dice3eye <= 6 && dice2eye <= 6
                    && dice3eye > 3 && dice2eye > 3 && dice5eye > 3){

                return true;

            }

        }
        if (p1_YACHTZEE) {
            if (p1_Aces) {
                if (dice1eye == 1 && dice1eye == dice2eye && dice2eye == dice3eye && dice3eye == dice4eye && dice4eye == dice5eye) {
                    return true;
                }
            } else if (p1_Twos) {
                if (dice1eye == 2 && dice1eye == dice2eye && dice2eye == dice3eye && dice3eye == dice4eye && dice4eye == dice5eye) {
                    return true;
                }
            } else if (p1_Threes) {
                if (dice1eye == 3 && dice1eye == dice2eye && dice2eye == dice3eye && dice3eye == dice4eye && dice4eye == dice5eye) {
                    return true;
                }
            } else if (p1_Fours) {
                if (dice1eye == 4 && dice1eye == dice2eye && dice2eye == dice3eye && dice3eye == dice4eye && dice4eye == dice5eye) {
                    return true;
                }
            } else if (p1_Fives) {
                if (dice1eye == 5 && dice1eye == dice2eye && dice2eye == dice3eye && dice3eye == dice4eye && dice4eye == dice5eye) {
                    return true;
                }
            } else if (p1_Sixes) {
                if (dice1eye == 6 && dice1eye == dice2eye && dice2eye == dice3eye && dice3eye == dice4eye && dice4eye == dice5eye) {
                    return true;
                }
            }
        }

        return false;
    }

    // LargeStraight 주사위 눈 체크
    public boolean largeStraight(){

        if(dice1eye != dice2eye && dice1eye != dice3eye && dice1eye != dice4eye && dice1eye != dice5eye
        && dice2eye != dice3eye && dice2eye != dice4eye && dice2eye != dice5eye
        && dice3eye != dice4eye && dice3eye != dice5eye
        && dice4eye != dice5eye){

            if(dice1eye == 1 && dice2eye <= 5 && dice3eye <= 5 && dice4eye <= 5 && dice5eye <= 5
            || dice1eye == 2 && dice2eye <= 6 && dice3eye <= 6 && dice4eye <= 6 && dice5eye <= 6
                             && dice2eye > 1 && dice3eye > 1 && dice4eye > 1 && dice5eye > 1

            || dice2eye == 1 && dice1eye <= 5 && dice3eye <= 5 && dice4eye <= 5 && dice5eye <= 5
            || dice2eye == 2 && dice1eye <= 6 && dice3eye <= 6 && dice4eye <= 6 && dice5eye <= 6
                             && dice1eye > 1 && dice3eye > 1 && dice4eye > 1 && dice5eye > 1

            || dice3eye == 1 && dice2eye <= 5 && dice1eye <= 5 && dice4eye <= 5 && dice5eye <= 5
            || dice3eye == 2 && dice2eye <= 6 && dice1eye <= 6 && dice4eye <= 6 && dice5eye <= 6
                             && dice2eye > 1 && dice1eye > 1 && dice4eye > 1 && dice5eye > 1

            || dice4eye == 1 && dice2eye <= 5 && dice3eye <= 5 && dice1eye <= 5 && dice5eye <= 5
            || dice4eye == 2 && dice2eye <= 6 && dice3eye <= 6 && dice1eye <= 6 && dice5eye <= 6
                             && dice2eye > 1 && dice3eye > 1 && dice1eye > 1 && dice5eye > 1

            || dice5eye == 1 && dice2eye <= 5 && dice3eye <= 5 && dice4eye <= 5 && dice1eye <= 5
            || dice5eye == 2 && dice2eye <= 6 && dice3eye <= 6 && dice4eye <= 6 && dice1eye <= 6
                             && dice2eye > 1 && dice3eye > 1 && dice4eye > 1 && dice1eye > 1){

                return true;

            }

        }
        if (p1_YACHTZEE) {
            if (p1_Aces) {
                if (dice1eye == 1 && dice1eye == dice2eye && dice2eye == dice3eye && dice3eye == dice4eye && dice4eye == dice5eye) {
                    return true;
                }
            } else if (p1_Twos) {
                if (dice1eye == 2 && dice1eye == dice2eye && dice2eye == dice3eye && dice3eye == dice4eye && dice4eye == dice5eye) {
                    return true;
                }
            } else if (p1_Threes) {
                if (dice1eye == 3 && dice1eye == dice2eye && dice2eye == dice3eye && dice3eye == dice4eye && dice4eye == dice5eye) {
                    return true;
                }
            } else if (p1_Fours) {
                if (dice1eye == 4 && dice1eye == dice2eye && dice2eye == dice3eye && dice3eye == dice4eye && dice4eye == dice5eye) {
                    return true;
                }
            } else if (p1_Fives) {
                if (dice1eye == 5 && dice1eye == dice2eye && dice2eye == dice3eye && dice3eye == dice4eye && dice4eye == dice5eye) {
                    return true;
                }
            } else if (p1_Sixes) {
                if (dice1eye == 6 && dice1eye == dice2eye && dice2eye == dice3eye && dice3eye == dice4eye && dice4eye == dice5eye) {
                    return true;
                }
            }
        }

        return false;
    }

    // YACHTZEE 주사위 눈 체크
    public boolean yachtzee(){

        if(dice1eye == dice2eye && dice2eye == dice3eye && dice3eye == dice4eye && dice4eye == dice5eye){
            return true;
        }

        return false;
    }

    @Override
    public void onBackPressed() {
       //super.onBackPressed();
    }

    // Shared 에 점수가 있으면 true, 없으면 false
    public void getScore(){

        // Aces
        if(sharedPreferences.getString("p1_Aces","") != ""){
            p1_Aces = true;
        }else {
            p1_Aces = false;
        }

        // Twos
        if (sharedPreferences.getString("p1_Twos", "") != "") {
            p1_Twos = true;
        }else {
            p1_Twos = false;
        }

        // Threes
        if (sharedPreferences.getString("p1_Threes", "") != "") {
            p1_Threes = true;
        }else {
            p1_Threes = false;
        }

        // Fours
        if (sharedPreferences.getString("p1_Fours", "") != "") {
            p1_Fours = true;
        }else {
            p1_Fours = false;
        }

        // Fives
        if (sharedPreferences.getString("p1_Fives", "") != "") {
            p1_Fives = true;
        }else {
            p1_Fives = false;
        }

        // Sixes
        if (sharedPreferences.getString("p1_Sixes", "") != "") {
            p1_Sixes = true;
        }else {
            p1_Sixes = false;
        }

        // TOAK
        if (sharedPreferences.getString("p1_TOAK", "") != "") {
            p1_TOAK = true;
        }else {
            p1_TOAK = false;
        }

        // FOAK
        if (sharedPreferences.getString("p1_FOAK", "") != "") {
            p1_FOAK = true;
        }else {
            p1_FOAK = false;
        }

        // FH
        if (sharedPreferences.getString("p1_FH", "") != "") {
            p1_FH = true;
        }else {
            p1_FH = false;
        }

        // SS
        if (sharedPreferences.getString("p1_SS", "") != "") {
            p1_SS = true;
        }else {
            p1_SS = false;
        }

        // LS
        if (sharedPreferences.getString("p1_LS", "") != "") {
            p1_LS = true;
        }else {
            p1_LS = false;
        }

        // Chance
        if (sharedPreferences.getString("p1_Chance", "") != "") {
            p1_Chance = true;
        }else {
            p1_Chance = false;
        }

        // YACHTZEE
        if (sharedPreferences.getString("p1_YACHTZEE", "") != "") {
            p1_YACHTZEE = true;
        }else {
            p1_YACHTZEE = false;
        }
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

}