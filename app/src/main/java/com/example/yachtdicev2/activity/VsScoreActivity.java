package com.example.yachtdicev2.activity;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.RED;

import androidx.activity.result.ActivityResultLauncher;
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

import com.example.yachtdicev2.R;
import com.example.yachtdicev2.gameLogic.ScoreCalculation;

public class VsScoreActivity extends AppCompatActivity {

    TextView P1Ace,P1Two,P1Three,P1Four,P1Five,P1Six,P1Sum,P1Bonus;
    TextView P1ThreeOfAKind,P1FourOfAKind,P1FullHouse;
    TextView P1SmallStraight,P1LargeStraight,P1Chance;
    TextView P1YACHT,P1YACHTBonus;
    TextView P2Ace,P2Two,P2Three,P2Four,P2Five,P2Six,P2Sum,P2Bonus;
    TextView P2ThreeOfAKind,P2FourOfAKind,P2FullHouse;
    TextView P2SmallStraight,P2LargeStraight,P2Chance;
    TextView P2YACHT,P2YACHTBonus;
    Button closeButton;

    int dice1eye,dice2eye,dice3eye,dice4eye,dice5eye;
    boolean userTurn;
    boolean P1AceSC,P1TwoSC,P1ThreeSC,P1FourSC,P1FiveSC,P1SixSC,P1TOAKSC,P1FOAKSC,P1FHSC,P1SSSC,P1LSSC,P1ChanceSC,P1YACHTSC;
    boolean P2AceSC,P2TwoSC,P2ThreeSC,P2FourSC,P2FiveSC,P2SixSC,P2TOAKSC,P2FOAKSC,P2FHSC,P2SSSC,P2LSSC,P2ChanceSC,P2YACHTSC;
    ScoreCalculation scoreCalc;
    public SharedPreferences sharedPreferences,sharedPreferences2;
    String status;
    String TAG = "VsScoreActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vs_score);

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

        scoreCalc = new ScoreCalculation();

        sharedPreferences = getSharedPreferences("vsP1Score",MODE_PRIVATE);
        sharedPreferences2 = getSharedPreferences("vsP2Score",MODE_PRIVATE);

        P1Ace = findViewById(R.id.acesScore_P1_vs);
        P1Two = findViewById(R.id.twosScore_P1_vs);
        P1Three = findViewById(R.id.threesScore_P1_vs);
        P1Four = findViewById(R.id.foursScore_P1_vs);
        P1Five = findViewById(R.id.fivesScore_P1_vs);
        P1Six = findViewById(R.id.sixesScore_P1_vs);
        P1Sum = findViewById(R.id.sumScore_P1_vs);
        P1Bonus = findViewById(R.id.bonusScore_P1_vs);
        P1ThreeOfAKind = findViewById(R.id.threeOfAkindScore_P1_vs);
        P1FourOfAKind = findViewById(R.id.fourOfAkindScore_P1_vs);
        P1FullHouse = findViewById(R.id.fullHouseScore_P1_vs);
        P1SmallStraight = findViewById(R.id.smallStraightScore_P1_vs);
        P1LargeStraight = findViewById(R.id.largeStraightScore_P1_vs);
        P1Chance = findViewById(R.id.chanceScore_P1_vs);
        P1YACHT = findViewById(R.id.yachtzeeScore_P1_vs);
        P1YACHTBonus = findViewById(R.id.yachtBonusScore_P1_vs);
        P2Ace = findViewById(R.id.acesScore_P2_vs);
        P2Two = findViewById(R.id.twosScore_P2_vs);
        P2Three = findViewById(R.id.threesScore_P2_vs);
        P2Four = findViewById(R.id.foursScore_P2_vs);
        P2Five = findViewById(R.id.fivesScore_P2_vs);
        P2Six = findViewById(R.id.sixesScore_P2_vs);
        P2Sum = findViewById(R.id.sumScore_P2_vs);
        P2Bonus = findViewById(R.id.bonusScore_P2_vs);
        P2ThreeOfAKind = findViewById(R.id.threeOfAkindScore_P2_vs);
        P2FourOfAKind = findViewById(R.id.fourOfAkindScore_P2_vs);
        P2FullHouse = findViewById(R.id.fullHouseScore_P2_vs);
        P2SmallStraight = findViewById(R.id.smallStraightScore_P2_vs);
        P2LargeStraight = findViewById(R.id.largeStraightScore_P2_vs);
        P2Chance = findViewById(R.id.chanceScore_P2_vs);
        P2YACHT = findViewById(R.id.yachtzeeScore_P2_vs);
        P2YACHTBonus = findViewById(R.id.yachtBonusScore_P2_vs);
        closeButton = findViewById(R.id.closeScore_vs);

        status = getIntent().getStringExtra("status");
        userTurn = getIntent().getBooleanExtra("userTurn",false);
        dice1eye = getIntent().getIntExtra("dice1eye",0);
        dice2eye = getIntent().getIntExtra("dice2eye",0);
        dice3eye = getIntent().getIntExtra("dice3eye",0);
        dice4eye = getIntent().getIntExtra("dice4eye",0);
        dice5eye = getIntent().getIntExtra("dice5eye",0);
        Log.e(TAG,"status : " + status);
        Log.e(TAG,"userTurn : " + userTurn);

        getScore();
        scoreCheck();

        if (status.equals("player1") && userTurn){

            // ace 값이 0보다 클 때, 저장된 값이 없을 때 붉은색으로 표시
            if (scoreCalc.calcAce(dice1eye, dice2eye, dice3eye, dice4eye, dice5eye) > 0 && !P1AceSC) {
                P1Ace.setText(String.valueOf(scoreCalc.calcAce(dice1eye, dice2eye, dice3eye, dice4eye, dice5eye)));
                P1Ace.setTextColor(RED);
            }

            // two 값이 0보다 클 때, 저장된 값이 없을 때 붉은색으로 표시
            if (scoreCalc.calcTwo(dice1eye, dice2eye, dice3eye, dice4eye, dice5eye) > 0 && !P1TwoSC) {
                P1Two.setText(String.valueOf(scoreCalc.calcTwo(dice1eye, dice2eye, dice3eye, dice4eye, dice5eye)));
                P1Two.setTextColor(RED);
            }

            // three 값이 0보다 클 때, 저장된 값이 없을 때 붉은색으로 표시
            if (scoreCalc.calcThree(dice1eye, dice2eye, dice3eye, dice4eye, dice5eye) > 0 && !P1ThreeSC) {
                P1Three.setText(String.valueOf(scoreCalc.calcThree(dice1eye, dice2eye, dice3eye, dice4eye, dice5eye)));
                P1Three.setTextColor(RED);
            }

            // four 값이 0보다 클 때, 저장된 값이 없을 때 붉은색으로 표시
            if (scoreCalc.calcFour(dice1eye, dice2eye, dice3eye, dice4eye, dice5eye) > 0 && !P1FourSC) {
                P1Four.setText(String.valueOf(scoreCalc.calcFour(dice1eye, dice2eye, dice3eye, dice4eye, dice5eye)));
                P1Four.setTextColor(RED);
            }

            // five 값이 0보다 클 때, 저장된 값이 없을 때 붉은색으로 표시
            if (scoreCalc.calcFive(dice1eye, dice2eye, dice3eye, dice4eye, dice5eye) > 0 && !P1FiveSC) {
                P1Five.setText(String.valueOf(scoreCalc.calcFive(dice1eye, dice2eye, dice3eye, dice4eye, dice5eye)));
                P1Five.setTextColor(RED);
            }

            // six 값이 0보다 클 때, 저장된 값이 없을 때 붉은색으로 표시
            if (scoreCalc.calcSix(dice1eye, dice2eye, dice3eye, dice4eye, dice5eye) > 0 && !P1SixSC) {
                P1Six.setText(String.valueOf(scoreCalc.calcSix(dice1eye, dice2eye, dice3eye, dice4eye, dice5eye)));
                P1Six.setTextColor(RED);
            }

            // TOAK 값이 0보다 클 때, 저장된 값이 없을 때 붉은색으로 표시
            if (scoreCalc.calcThreeOfAKind(dice1eye, dice2eye, dice3eye, dice4eye, dice5eye) > 0 && !P1TOAKSC) {
                P1ThreeOfAKind.setText(String.valueOf(scoreCalc.calcThreeOfAKind(dice1eye, dice2eye, dice3eye, dice4eye, dice5eye)));
                P1ThreeOfAKind.setTextColor(RED);
            }

            // FOAK 값이 0보다 클 때, 저장된 값이 없을 때 붉은색으로 표시
            if (scoreCalc.calcFourOfAKind(dice1eye, dice2eye, dice3eye, dice4eye, dice5eye) > 0 && !P1FOAKSC) {
                P1FourOfAKind.setText(String.valueOf(scoreCalc.calcFourOfAKind(dice1eye, dice2eye, dice3eye, dice4eye, dice5eye)));
                P1FourOfAKind.setTextColor(RED);
            }

            // FullHouse 값이 0보다 클 때, 저장된 값이 없을 때 붉은색으로 표시
            if (scoreCalc.calcFullHouse(dice1eye, dice2eye, dice3eye, dice4eye, dice5eye) > 0 && !P1FHSC) {
                P1FullHouse.setText(String.valueOf(scoreCalc.calcFullHouse(dice1eye, dice2eye, dice3eye, dice4eye, dice5eye)));
                P1FullHouse.setTextColor(RED);
            }

            // SmallStraight 값이 0보다 클 때, 저장된 값이 없을 때 붉은색으로 표시
            if (scoreCalc.calcSmallStraight(dice1eye, dice2eye, dice3eye, dice4eye, dice5eye) > 0 && !P1SSSC) {
                P1SmallStraight.setText(String.valueOf(scoreCalc.calcSmallStraight(dice1eye, dice2eye, dice3eye, dice4eye, dice5eye)));
                P1SmallStraight.setTextColor(RED);
            }

            // LargeStraight 값이 0보다 클 때, 저장된 값이 없을 때 붉은색으로 표시
            if (scoreCalc.calcLargeStraight(dice1eye, dice2eye, dice3eye, dice4eye, dice5eye) > 0 && !P1LSSC) {
                P1LargeStraight.setText(String.valueOf(scoreCalc.calcLargeStraight(dice1eye, dice2eye, dice3eye, dice4eye, dice5eye)));
                P1LargeStraight.setTextColor(RED);
            }

            // Chance 값이 0보다 클 때, 저장된 값이 없을 때 붉은색으로 표시
            if (scoreCalc.calcChance(dice1eye, dice2eye, dice3eye, dice4eye, dice5eye) > 0 && !P1ChanceSC) {
                P1Chance.setText(String.valueOf(scoreCalc.calcChance(dice1eye, dice2eye, dice3eye, dice4eye, dice5eye)));
                P1Chance.setTextColor(RED);
            }

            // YACHT 값이 0보다 클 때, 저장된 값이 없을 때 붉은색으로 표시
            if (scoreCalc.calcYACHT(dice1eye, dice2eye, dice3eye, dice4eye, dice5eye) > 0 && !P1YACHTSC) {
                P1YACHT.setText(String.valueOf(scoreCalc.calcYACHT(dice1eye, dice2eye, dice3eye, dice4eye, dice5eye)));
                P1YACHT.setTextColor(RED);
            }

            if (userTurn){
                P1Ace.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(VsScoreActivity.this);
                        builder.setTitle("Aces 점수").setMessage(String.valueOf(scoreCalc.calcAce(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye)));
                        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Intent intent = new Intent();
                                intent.putExtra("status",status);
                                intent.putExtra("name","P1Aces");
                                intent.putExtra("score",scoreCalc.calcAce(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye));
                                setResult(RESULT_OK,intent);
                                dialog.dismiss();
                                finish();

                            }
                        }).setCancelable(false);
                        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();
                            }
                        }).setCancelable(false);

                        builder.show();
                    }
                });

                P1Two.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(VsScoreActivity.this);
                        builder.setTitle("Twos 점수").setMessage(String.valueOf(scoreCalc.calcTwo(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye)));
                        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Intent intent = new Intent();
                                intent.putExtra("status",status);
                                intent.putExtra("name","P1Twos");
                                intent.putExtra("score",scoreCalc.calcTwo(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye));
                                setResult(RESULT_OK,intent);
                                dialog.dismiss();
                                finish();

                            }
                        }).setCancelable(false);
                        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();
                            }
                        }).setCancelable(false);

                        builder.show();
                    }
                });

                P1Three.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        AlertDialog.Builder builder = new AlertDialog.Builder(VsScoreActivity.this);
                        builder.setTitle("Threes 점수").setMessage(String.valueOf(scoreCalc.calcThree(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye)));
                        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Intent intent = new Intent();
                                intent.putExtra("status",status);
                                intent.putExtra("name","P1Threes");
                                intent.putExtra("score",scoreCalc.calcThree(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye));
                                setResult(RESULT_OK,intent);
                                dialog.dismiss();
                                finish();

                            }
                        }).setCancelable(false);
                        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();
                            }
                        }).setCancelable(false);

                        builder.show();
                    }
                });

                P1Four.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(VsScoreActivity.this);
                        builder.setTitle("Fours 점수").setMessage(String.valueOf(scoreCalc.calcFour(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye)));
                        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Intent intent = new Intent();
                                intent.putExtra("status",status);
                                intent.putExtra("name","P1Fours");
                                intent.putExtra("score",scoreCalc.calcFour(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye));
                                setResult(RESULT_OK,intent);
                                dialog.dismiss();
                                finish();

                            }
                        }).setCancelable(false);
                        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();
                            }
                        }).setCancelable(false);

                        builder.show();
                    }
                });

                P1Five.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(VsScoreActivity.this);
                        builder.setTitle("Fives 점수").setMessage(String.valueOf(scoreCalc.calcFive(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye)));
                        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Intent intent = new Intent();
                                intent.putExtra("status",status);
                                intent.putExtra("name","P1Fives");
                                intent.putExtra("score",scoreCalc.calcFive(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye));
                                setResult(RESULT_OK,intent);
                                dialog.dismiss();
                                finish();

                            }
                        }).setCancelable(false);
                        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();
                            }
                        }).setCancelable(false);

                        builder.show();
                    }
                });

                P1Six.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(VsScoreActivity.this);
                        builder.setTitle("Sixes 점수").setMessage(String.valueOf(scoreCalc.calcSix(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye)));
                        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Intent intent = new Intent();
                                intent.putExtra("status",status);
                                intent.putExtra("name","P1Sixes");
                                intent.putExtra("score",scoreCalc.calcSix(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye));
                                setResult(RESULT_OK,intent);
                                dialog.dismiss();
                                finish();

                            }
                        }).setCancelable(false);
                        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();
                            }
                        }).setCancelable(false);

                        builder.show();
                    }
                });

                P1ThreeOfAKind.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(VsScoreActivity.this);
                        builder.setTitle("ThreeOfAKind 점수").setMessage(String.valueOf(scoreCalc.calcThreeOfAKind(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye)));
                        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Intent intent = new Intent();
                                intent.putExtra("status",status);
                                intent.putExtra("name","P1TOAK");
                                intent.putExtra("score",scoreCalc.calcThreeOfAKind(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye));
                                setResult(RESULT_OK,intent);
                                dialog.dismiss();
                                finish();

                            }
                        }).setCancelable(false);
                        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();
                            }
                        }).setCancelable(false);

                        builder.show();
                    }
                });

                P1FourOfAKind.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(VsScoreActivity.this);
                        builder.setTitle("FourOfAKind 점수").setMessage(String.valueOf(scoreCalc.calcFourOfAKind(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye)));
                        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Intent intent = new Intent();
                                intent.putExtra("status",status);
                                intent.putExtra("name","P1FOAK");
                                intent.putExtra("score",scoreCalc.calcFourOfAKind(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye));
                                setResult(RESULT_OK,intent);
                                dialog.dismiss();
                                finish();

                            }
                        }).setCancelable(false);
                        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();
                            }
                        }).setCancelable(false);

                        builder.show();
                    }
                });

                P1FullHouse.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(VsScoreActivity.this);
                        builder.setTitle("FullHouse 점수").setMessage(String.valueOf(scoreCalc.calcFullHouse(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye)));
                        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Intent intent = new Intent();
                                intent.putExtra("status",status);
                                intent.putExtra("name","P1FH");
                                intent.putExtra("score",scoreCalc.calcFullHouse(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye));
                                setResult(RESULT_OK,intent);
                                dialog.dismiss();
                                finish();

                            }
                        }).setCancelable(false);
                        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();
                            }
                        }).setCancelable(false);

                        builder.show();
                    }
                });

                P1SmallStraight.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(VsScoreActivity.this);
                        builder.setTitle("SmallStraight 점수").setMessage(String.valueOf(scoreCalc.calcSmallStraight(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye)));
                        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Intent intent = new Intent();
                                intent.putExtra("status",status);
                                intent.putExtra("name","P1SS");
                                intent.putExtra("score",scoreCalc.calcSmallStraight(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye));
                                setResult(RESULT_OK,intent);
                                dialog.dismiss();
                                finish();

                            }
                        }).setCancelable(false);
                        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();
                            }
                        }).setCancelable(false);

                        builder.show();
                    }
                });

                P1LargeStraight.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(VsScoreActivity.this);
                        builder.setTitle("LargeStraight 점수").setMessage(String.valueOf(scoreCalc.calcLargeStraight(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye)));
                        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Intent intent = new Intent();
                                intent.putExtra("status",status);
                                intent.putExtra("name","P1LS");
                                intent.putExtra("score",scoreCalc.calcLargeStraight(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye));
                                setResult(RESULT_OK,intent);
                                dialog.dismiss();
                                finish();

                            }
                        }).setCancelable(false);
                        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();
                            }
                        }).setCancelable(false);

                        builder.show();
                    }
                });

                P1Chance.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(VsScoreActivity.this);
                        builder.setTitle("Chance 점수").setMessage(String.valueOf(scoreCalc.calcChance(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye)));
                        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Intent intent = new Intent();
                                intent.putExtra("status",status);
                                intent.putExtra("name","P1Chance");
                                intent.putExtra("score",scoreCalc.calcChance(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye));
                                setResult(RESULT_OK,intent);
                                dialog.dismiss();
                                finish();

                            }
                        }).setCancelable(false);
                        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();
                            }
                        }).setCancelable(false);

                        builder.show();
                    }
                });

                P1YACHT.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(VsScoreActivity.this);
                        builder.setTitle("YACHT 점수").setMessage(String.valueOf(scoreCalc.calcYACHT(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye)));
                        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Intent intent = new Intent();
                                intent.putExtra("status",status);
                                intent.putExtra("name","P1YACHT");
                                intent.putExtra("score",scoreCalc.calcYACHT(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye));
                                setResult(RESULT_OK,intent);
                                dialog.dismiss();
                                finish();

                            }
                        }).setCancelable(false);
                        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();
                            }
                        }).setCancelable(false);

                        builder.show();
                    }
                });
            }
        }

        if (status.equals("player2") && userTurn){

            if (scoreCalc.calcAce(dice1eye, dice2eye, dice3eye, dice4eye, dice5eye) > 0 && !P2AceSC) {
                P2Ace.setText(String.valueOf(scoreCalc.calcAce(dice1eye, dice2eye, dice3eye, dice4eye, dice5eye)));
                P2Ace.setTextColor(RED);
            }

            if (scoreCalc.calcTwo(dice1eye, dice2eye, dice3eye, dice4eye, dice5eye) > 0 && !P2TwoSC) {
                P2Two.setText(String.valueOf(scoreCalc.calcTwo(dice1eye, dice2eye, dice3eye, dice4eye, dice5eye)));
                P2Two.setTextColor(RED);
            }

            if (scoreCalc.calcThree(dice1eye, dice2eye, dice3eye, dice4eye, dice5eye) > 0 && !P2ThreeSC) {
                P2Three.setText(String.valueOf(scoreCalc.calcThree(dice1eye, dice2eye, dice3eye, dice4eye, dice5eye)));
                P2Three.setTextColor(RED);
            }

            if (scoreCalc.calcFour(dice1eye, dice2eye, dice3eye, dice4eye, dice5eye) > 0 && !P2FourSC) {
                P2Four.setText(String.valueOf(scoreCalc.calcFour(dice1eye, dice2eye, dice3eye, dice4eye, dice5eye)));
                P2Four.setTextColor(RED);
            }

            if (scoreCalc.calcFive(dice1eye, dice2eye, dice3eye, dice4eye, dice5eye) > 0 && !P2FiveSC) {
                P2Five.setText(String.valueOf(scoreCalc.calcFive(dice1eye, dice2eye, dice3eye, dice4eye, dice5eye)));
                P2Five.setTextColor(RED);
            }

            if (scoreCalc.calcSix(dice1eye, dice2eye, dice3eye, dice4eye, dice5eye) > 0 && !P2SixSC) {
                P2Six.setText(String.valueOf(scoreCalc.calcSix(dice1eye, dice2eye, dice3eye, dice4eye, dice5eye)));
                P2Six.setTextColor(RED);
            }

            if (scoreCalc.calcThreeOfAKind(dice1eye, dice2eye, dice3eye, dice4eye, dice5eye) > 0 && !P2TOAKSC) {
                P2ThreeOfAKind.setText(String.valueOf(scoreCalc.calcThreeOfAKind(dice1eye, dice2eye, dice3eye, dice4eye, dice5eye)));
                P2ThreeOfAKind.setTextColor(RED);
            }

            if (scoreCalc.calcFourOfAKind(dice1eye, dice2eye, dice3eye, dice4eye, dice5eye) > 0 && !P2FOAKSC) {
                P2FourOfAKind.setText(String.valueOf(scoreCalc.calcFourOfAKind(dice1eye, dice2eye, dice3eye, dice4eye, dice5eye)));
                P2FourOfAKind.setTextColor(RED);
            }

            if (scoreCalc.calcFullHouse(dice1eye, dice2eye, dice3eye, dice4eye, dice5eye) > 0 && !P2FHSC) {
                P2FullHouse.setText(String.valueOf(scoreCalc.calcFullHouse(dice1eye, dice2eye, dice3eye, dice4eye, dice5eye)));
                P2FullHouse.setTextColor(RED);
            }

            if (scoreCalc.calcSmallStraight(dice1eye, dice2eye, dice3eye, dice4eye, dice5eye) > 0 && !P2SSSC) {
                P2SmallStraight.setText(String.valueOf(scoreCalc.calcSmallStraight(dice1eye, dice2eye, dice3eye, dice4eye, dice5eye)));
                P2SmallStraight.setTextColor(RED);
            }

            if (scoreCalc.calcLargeStraight(dice1eye, dice2eye, dice3eye, dice4eye, dice5eye) > 0 && !P2LSSC) {
                P2LargeStraight.setText(String.valueOf(scoreCalc.calcLargeStraight(dice1eye, dice2eye, dice3eye, dice4eye, dice5eye)));
                P2LargeStraight.setTextColor(RED);
            }

            if (scoreCalc.calcChance(dice1eye, dice2eye, dice3eye, dice4eye, dice5eye) > 0 && !P2ChanceSC) {
                P2Chance.setText(String.valueOf(scoreCalc.calcChance(dice1eye, dice2eye, dice3eye, dice4eye, dice5eye)));
                P2Chance.setTextColor(RED);
            }

            if (scoreCalc.calcYACHT(dice1eye, dice2eye, dice3eye, dice4eye, dice5eye) > 0 && !P2YACHTSC) {
                P2YACHT.setText(String.valueOf(scoreCalc.calcYACHT(dice1eye, dice2eye, dice3eye, dice4eye, dice5eye)));
                P2YACHT.setTextColor(RED);
            }

            if (userTurn){

                P2Ace.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(VsScoreActivity.this);
                        builder.setTitle("Aces 점수").setMessage(String.valueOf(scoreCalc.calcAce(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye)));
                        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Intent intent = new Intent();
                                intent.putExtra("status",status);
                                intent.putExtra("name","P2Aces");
                                intent.putExtra("score",scoreCalc.calcAce(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye));
                                setResult(RESULT_OK,intent);
                                dialog.dismiss();
                                finish();

                            }
                        }).setCancelable(false);
                        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();
                            }
                        }).setCancelable(false);

                        builder.show();

                    }
                });

                P2Two.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(VsScoreActivity.this);
                        builder.setTitle("Twos 점수").setMessage(String.valueOf(scoreCalc.calcTwo(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye)));
                        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Intent intent = new Intent();
                                intent.putExtra("status",status);
                                intent.putExtra("name","P2Twos");
                                intent.putExtra("score",scoreCalc.calcTwo(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye));
                                setResult(RESULT_OK,intent);
                                dialog.dismiss();
                                finish();

                            }
                        }).setCancelable(false);
                        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();
                            }
                        }).setCancelable(false);

                        builder.show();

                    }
                });

                P2Three.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(VsScoreActivity.this);
                        builder.setTitle("Threes 점수").setMessage(String.valueOf(scoreCalc.calcThree(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye)));
                        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Intent intent = new Intent();
                                intent.putExtra("status",status);
                                intent.putExtra("name","P2Threes");
                                intent.putExtra("score",scoreCalc.calcThree(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye));
                                setResult(RESULT_OK,intent);
                                dialog.dismiss();
                                finish();

                            }
                        }).setCancelable(false);
                        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();
                            }
                        }).setCancelable(false);

                        builder.show();

                    }
                });

                P2Four.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(VsScoreActivity.this);
                        builder.setTitle("Fours 점수").setMessage(String.valueOf(scoreCalc.calcFour(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye)));
                        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Intent intent = new Intent();
                                intent.putExtra("status",status);
                                intent.putExtra("name","P2Fours");
                                intent.putExtra("score",scoreCalc.calcFour(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye));
                                setResult(RESULT_OK,intent);
                                dialog.dismiss();
                                finish();

                            }
                        }).setCancelable(false);
                        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();
                            }
                        }).setCancelable(false);

                        builder.show();

                    }
                });

                P2Five.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(VsScoreActivity.this);
                        builder.setTitle("Fives 점수").setMessage(String.valueOf(scoreCalc.calcFive(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye)));
                        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Intent intent = new Intent();
                                intent.putExtra("status",status);
                                intent.putExtra("name","P2Fives");
                                intent.putExtra("score",scoreCalc.calcFive(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye));
                                setResult(RESULT_OK,intent);
                                dialog.dismiss();
                                finish();

                            }
                        }).setCancelable(false);
                        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();
                            }
                        }).setCancelable(false);

                        builder.show();

                    }
                });

                P2Six.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(VsScoreActivity.this);
                        builder.setTitle("Sixes 점수").setMessage(String.valueOf(scoreCalc.calcSix(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye)));
                        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Intent intent = new Intent();
                                intent.putExtra("status",status);
                                intent.putExtra("name","P2Sixes");
                                intent.putExtra("score",scoreCalc.calcSix(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye));
                                setResult(RESULT_OK,intent);
                                dialog.dismiss();
                                finish();

                            }
                        }).setCancelable(false);
                        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();
                            }
                        }).setCancelable(false);

                        builder.show();

                    }
                });

                P2ThreeOfAKind.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(VsScoreActivity.this);
                        builder.setTitle("ThreeOfAKind 점수").setMessage(String.valueOf(scoreCalc.calcThreeOfAKind(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye)));
                        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Intent intent = new Intent();
                                intent.putExtra("status",status);
                                intent.putExtra("name","P2TOAK");
                                intent.putExtra("score",scoreCalc.calcThreeOfAKind(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye));
                                setResult(RESULT_OK,intent);
                                dialog.dismiss();
                                finish();

                            }
                        }).setCancelable(false);
                        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();
                            }
                        }).setCancelable(false);

                        builder.show();

                    }
                });

                P2FourOfAKind.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(VsScoreActivity.this);
                        builder.setTitle("FourOfAKind 점수").setMessage(String.valueOf(scoreCalc.calcFourOfAKind(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye)));
                        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Intent intent = new Intent();
                                intent.putExtra("status",status);
                                intent.putExtra("name","P2FOAK");
                                intent.putExtra("score",scoreCalc.calcFourOfAKind(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye));
                                setResult(RESULT_OK,intent);
                                dialog.dismiss();
                                finish();

                            }
                        }).setCancelable(false);
                        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();
                            }
                        }).setCancelable(false);

                        builder.show();

                    }
                });

                P2FullHouse.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(VsScoreActivity.this);
                        builder.setTitle("FullHouse 점수").setMessage(String.valueOf(scoreCalc.calcFullHouse(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye)));
                        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Intent intent = new Intent();
                                intent.putExtra("status",status);
                                intent.putExtra("name","P2FH");
                                intent.putExtra("score",scoreCalc.calcFullHouse(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye));
                                setResult(RESULT_OK,intent);
                                dialog.dismiss();
                                finish();

                            }
                        }).setCancelable(false);
                        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();
                            }
                        }).setCancelable(false);

                        builder.show();

                    }
                });

                P2SmallStraight.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(VsScoreActivity.this);
                        builder.setTitle("SmallStraight 점수").setMessage(String.valueOf(scoreCalc.calcSmallStraight(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye)));
                        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Intent intent = new Intent();
                                intent.putExtra("status",status);
                                intent.putExtra("name","P2SS");
                                intent.putExtra("score",scoreCalc.calcSmallStraight(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye));
                                setResult(RESULT_OK,intent);
                                dialog.dismiss();
                                finish();

                            }
                        }).setCancelable(false);
                        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();
                            }
                        }).setCancelable(false);

                        builder.show();

                    }
                });

                P2LargeStraight.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(VsScoreActivity.this);
                        builder.setTitle("LargeStraight 점수").setMessage(String.valueOf(scoreCalc.calcLargeStraight(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye)));
                        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Intent intent = new Intent();
                                intent.putExtra("status",status);
                                intent.putExtra("name","P2LS");
                                intent.putExtra("score",scoreCalc.calcLargeStraight(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye));
                                setResult(RESULT_OK,intent);
                                dialog.dismiss();
                                finish();

                            }
                        }).setCancelable(false);
                        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();
                            }
                        }).setCancelable(false);

                        builder.show();

                    }
                });

                P2Chance.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(VsScoreActivity.this);
                        builder.setTitle("Chance 점수").setMessage(String.valueOf(scoreCalc.calcChance(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye)));
                        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Intent intent = new Intent();
                                intent.putExtra("status",status);
                                intent.putExtra("name","P2Chance");
                                intent.putExtra("score",scoreCalc.calcChance(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye));
                                setResult(RESULT_OK,intent);
                                dialog.dismiss();
                                finish();

                            }
                        }).setCancelable(false);
                        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();
                            }
                        }).setCancelable(false);

                        builder.show();

                    }
                });

                P2YACHT.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(VsScoreActivity.this);
                        builder.setTitle("YACHT 점수").setMessage(String.valueOf(scoreCalc.calcYACHT(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye)));
                        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Intent intent = new Intent();
                                intent.putExtra("status",status);
                                intent.putExtra("name","P2YACHT");
                                intent.putExtra("score",scoreCalc.calcYACHT(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye));
                                setResult(RESULT_OK,intent);
                                dialog.dismiss();
                                finish();

                            }
                        }).setCancelable(false);
                        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();
                            }
                        }).setCancelable(false);

                        builder.show();

                    }
                });
            }
        }


        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void getScore(){

        if (sharedPreferences.getString("P1Aces","") != ""){
            P1AceSC = true;
            P1Ace.setText(sharedPreferences.getString("P1Aces",""));
            P1Ace.setTextColor(BLACK);
        }

        if (sharedPreferences.getString("P1Twos","") != ""){
            P1TwoSC = true;
            P1Two.setText(sharedPreferences.getString("P1Twos",""));
            P1Two.setTextColor(BLACK);
        }

        if (sharedPreferences.getString("P1Threes","") != ""){
            P1ThreeSC = true;
            P1Three.setText(sharedPreferences.getString("P1Threes",""));
            P1Three.setTextColor(BLACK);
        }

        if (sharedPreferences.getString("P1Fours","") != ""){
            P1FourSC = true;
            P1Four.setText(sharedPreferences.getString("P1Fours",""));
            P1Four.setTextColor(BLACK);
        }

        if (sharedPreferences.getString("P1Fives","") != ""){
            P1FiveSC = true;
            P1Five.setText(sharedPreferences.getString("P1Fives",""));
            P1Five.setTextColor(BLACK);
        }

        if (sharedPreferences.getString("P1Sixes","") != ""){
            P1SixSC = true;
            P1Six.setText(sharedPreferences.getString("P1Sixes",""));
            P1Six.setTextColor(BLACK);
        }

        if (P1AceSC && P1TwoSC && P1ThreeSC && P1FourSC && P1FiveSC && P1SixSC){

            int ace,two,three,four,five,six,sum,bonus;

            ace = Integer.parseInt(P1Ace.getText().toString());
            two = Integer.parseInt(P1Two.getText().toString());
            three = Integer.parseInt(P1Three.getText().toString());
            four = Integer.parseInt(P1Four.getText().toString());
            five = Integer.parseInt(P1Five.getText().toString());
            six = Integer.parseInt(P1Six.getText().toString());

            sum = ace + two + three + four + five + six;

            if (sum >= 63){
                bonus = 35;
            }else {
                bonus = 0;
            }

            P1Sum.setText(sum + bonus);
            P1Sum.setTextColor(BLACK);
        }

        if (sharedPreferences.getString("P1TOAK","") != ""){
            P1TOAKSC = true;
            P1ThreeOfAKind.setText(sharedPreferences.getString("P1TOAK",""));
            P1ThreeOfAKind.setTextColor(BLACK);
        }

        if (sharedPreferences.getString("P1FOAK","") != ""){
            P1FOAKSC = true;
            P1FourOfAKind.setText(sharedPreferences.getString("P1FOAK",""));
            P1FourOfAKind.setTextColor(BLACK);
        }

        if (sharedPreferences.getString("P1FH","") != ""){
            P1FHSC = true;
            P1FullHouse.setText(sharedPreferences.getString("P1FH",""));
            P1FullHouse.setTextColor(BLACK);
        }

        if (sharedPreferences.getString("P1SS","") != ""){
            P1SSSC = true;
            P1SmallStraight.setText(sharedPreferences.getString("P1SS",""));
            P1SmallStraight.setTextColor(BLACK);
        }

        if (sharedPreferences.getString("P1LS","") != ""){
            P1LSSC = true;
            P1LargeStraight.setText(sharedPreferences.getString("P1LS",""));
            P1LargeStraight.setTextColor(BLACK);
        }

        if (sharedPreferences.getString("P1Chance","") != ""){
            P1ChanceSC = true;
            P1Chance.setText(sharedPreferences.getString("P1Chance",""));
            P1Chance.setTextColor(BLACK);
        }

        if (sharedPreferences.getString("P1YACHT","") != ""){
            P1YACHTSC = true;
            P1YACHT.setText(sharedPreferences.getString("P1YACHT",""));
            P1YACHT.setTextColor(BLACK);
        }

        if (sharedPreferences2.getString("P2Aces","") != ""){
            P2AceSC = true;
            P2Ace.setText(sharedPreferences2.getString("P2Aces",""));
            P2Ace.setTextColor(BLACK);
        }

        if (sharedPreferences2.getString("P2Twos","") != ""){
            P2TwoSC = true;
            P2Two.setText(sharedPreferences2.getString("P2Twos",""));
            P2Two.setTextColor(BLACK);
        }

        if (sharedPreferences2.getString("P2Threes","") != ""){
            P2ThreeSC = true;
            P2Three.setText(sharedPreferences2.getString("P2Threes",""));
            P2Three.setTextColor(BLACK);
        }

        if (sharedPreferences2.getString("P2Fours","") != ""){
            P2FourSC = true;
            P2Four.setText(sharedPreferences2.getString("P2Fours",""));
            P2Four.setTextColor(BLACK);
        }

        if (sharedPreferences2.getString("P2Fives","") != ""){
            P2FiveSC = true;
            P2Five.setText(sharedPreferences2.getString("P2Fives",""));
            P2Five.setTextColor(BLACK);
        }

        if (sharedPreferences2.getString("P2Sixes","") != ""){
            P2SixSC = true;
            P2Six.setText(sharedPreferences2.getString("P2Sixes",""));
            P2Six.setTextColor(BLACK);
        }

        if (P2AceSC && P2TwoSC && P2ThreeSC && P2FourSC && P2FiveSC && P2SixSC){

            int ace,two,three,four,five,six,sum,bonus;

            ace = Integer.parseInt(P2Ace.getText().toString());
            two = Integer.parseInt(P2Two.getText().toString());
            three = Integer.parseInt(P2Three.getText().toString());
            four = Integer.parseInt(P2Four.getText().toString());
            five = Integer.parseInt(P2Five.getText().toString());
            six = Integer.parseInt(P2Six.getText().toString());

            sum = ace + two + three + four + five + six;

            if (sum >= 63){
                bonus = 35;
            }else {
                bonus = 0;
            }

            P2Sum.setText(sum + bonus);
            P2Sum.setTextColor(BLACK);
        }

        if (sharedPreferences2.getString("P2TOAK","") != ""){
            P2TOAKSC = true;
            P2ThreeOfAKind.setText(sharedPreferences2.getString("P2TOAK",""));
            P2ThreeOfAKind.setTextColor(BLACK);
        }

        if (sharedPreferences2.getString("P2FOAK","") != ""){
            P2FOAKSC = true;
            P2FourOfAKind.setText(sharedPreferences2.getString("P2FOAK",""));
            P2FourOfAKind.setTextColor(BLACK);
        }

        if (sharedPreferences2.getString("P2FH","") != ""){
            P2FHSC = true;
            P2FullHouse.setText(sharedPreferences2.getString("P2FH",""));
            P2FullHouse.setTextColor(BLACK);
        }

        if (sharedPreferences2.getString("P2SS","") != ""){
            P2SSSC = true;
            P2SmallStraight.setText(sharedPreferences2.getString("P2SS",""));
            P2SmallStraight.setTextColor(BLACK);
        }

        if (sharedPreferences2.getString("P2LS","") != ""){
            P2LSSC = true;
            P2LargeStraight.setText(sharedPreferences2.getString("P2LS",""));
            P2LargeStraight.setTextColor(BLACK);
        }

        if (sharedPreferences2.getString("P2Chance","") != ""){
            P2ChanceSC = true;
            P2Chance.setText(sharedPreferences2.getString("P2Chance",""));
            P2Chance.setTextColor(BLACK);
        }

        if (sharedPreferences2.getString("P2YACHT","") != ""){
            P2YACHTSC = true;
            P2YACHT.setText(sharedPreferences2.getString("P2YACHT",""));
            P2YACHT.setTextColor(BLACK);
        }

    }

    public void scoreCheck(){

    }
}