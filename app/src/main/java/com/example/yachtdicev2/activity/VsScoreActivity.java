package com.example.yachtdicev2.activity;

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
    ScoreCalculation scoreCalc;
    public SharedPreferences sharedPreferences,sharedPreferences2;
    String status;

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
        P2Two = findViewById(R.id.twosScore_P1_vs);
        P2Three = findViewById(R.id.threesScore_P2_vs);
        P2Four = findViewById(R.id.foursScore_P2_vs);
        P2Five = findViewById(R.id.fivesScore_P2_vs);
        P2Six = findViewById(R.id.sixesScore_P2_vs);
        P2Sum = findViewById(R.id.sumScore_P2_vs);
        P2Bonus = findViewById(R.id.bonusScore_P2_vs);
        P2ThreeOfAKind = findViewById(R.id.threeOfAkindScore_P2_vs);
        P2FourOfAKind = findViewById(R.id.fourOfAkindScore_P2_vs);
        P2FullHouse = findViewById(R.id.fullHouseScore_P2_vs);
        P2SmallStraight = findViewById(R.id.fullHouseScore_P2_vs);
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

        if (status.equals("player1")){

            // ace 값이 0보다 클 때, 저장된 값이 없을 때 붉은색으로 표시
            if (scoreCalc.calcAce(dice1eye, dice2eye, dice3eye, dice4eye, dice5eye) > 0) {
                P1Ace.setText(String.valueOf(scoreCalc.calcAce(dice1eye, dice2eye, dice3eye, dice4eye, dice5eye)));
                P1Ace.setTextColor(RED);
            }

            // two 값이 0보다 클 때, 저장된 값이 없을 때 붉은색으로 표시
            if (scoreCalc.calcTwo(dice1eye, dice2eye, dice3eye, dice4eye, dice5eye) > 0) {
                P1Two.setText(String.valueOf(scoreCalc.calcTwo(dice1eye, dice2eye, dice3eye, dice4eye, dice5eye)));
                P1Two.setTextColor(RED);
            }

            // three 값이 0보다 클 때, 저장된 값이 없을 때 붉은색으로 표시
            if (scoreCalc.calcThree(dice1eye, dice2eye, dice3eye, dice4eye, dice5eye) > 0) {
                P1Three.setText(String.valueOf(scoreCalc.calcThree(dice1eye, dice2eye, dice3eye, dice4eye, dice5eye)));
                P1Three.setTextColor(RED);
            }

            // four 값이 0보다 클 때, 저장된 값이 없을 때 붉은색으로 표시
            if (scoreCalc.calcFour(dice1eye, dice2eye, dice3eye, dice4eye, dice5eye) > 0) {
                P1Four.setText(String.valueOf(scoreCalc.calcFour(dice1eye, dice2eye, dice3eye, dice4eye, dice5eye)));
                P1Four.setTextColor(RED);
            }

            // five 값이 0보다 클 때, 저장된 값이 없을 때 붉은색으로 표시
            if (scoreCalc.calcFive(dice1eye, dice2eye, dice3eye, dice4eye, dice5eye) > 0) {
                P1Five.setText(String.valueOf(scoreCalc.calcFive(dice1eye, dice2eye, dice3eye, dice4eye, dice5eye)));
                P1Five.setTextColor(RED);
            }

            // six 값이 0보다 클 때, 저장된 값이 없을 때 붉은색으로 표시
            if (scoreCalc.calcSix(dice1eye, dice2eye, dice3eye, dice4eye, dice5eye) > 0) {
                P1Six.setText(String.valueOf(scoreCalc.calcSix(dice1eye, dice2eye, dice3eye, dice4eye, dice5eye)));
                P1Six.setTextColor(RED);
            }

            // TOAK 값이 0보다 클 때, 저장된 값이 없을 때 붉은색으로 표시
            if (scoreCalc.calcThreeOfAKind(dice1eye, dice2eye, dice3eye, dice4eye, dice5eye) > 0) {
                P1ThreeOfAKind.setText(String.valueOf(scoreCalc.calcThreeOfAKind(dice1eye, dice2eye, dice3eye, dice4eye, dice5eye)));
                P1ThreeOfAKind.setTextColor(RED);
            }

            // FOAK 값이 0보다 클 때, 저장된 값이 없을 때 붉은색으로 표시
            if (scoreCalc.calcFourOfAKind(dice1eye, dice2eye, dice3eye, dice4eye, dice5eye) > 0) {
                P1FourOfAKind.setText(String.valueOf(scoreCalc.calcFourOfAKind(dice1eye, dice2eye, dice3eye, dice4eye, dice5eye)));
                P1FourOfAKind.setTextColor(RED);
            }

            // FullHouse 값이 0보다 클 때, 저장된 값이 없을 때 붉은색으로 표시
            if (scoreCalc.calcFullHouse(dice1eye, dice2eye, dice3eye, dice4eye, dice5eye) > 0) {
                P1FullHouse.setText(String.valueOf(scoreCalc.calcFullHouse(dice1eye, dice2eye, dice3eye, dice4eye, dice5eye)));
                P1FullHouse.setTextColor(RED);
            }

            // SmallStraight 값이 0보다 클 때, 저장된 값이 없을 때 붉은색으로 표시
            if (scoreCalc.calcSmallStraight(dice1eye, dice2eye, dice3eye, dice4eye, dice5eye) > 0) {
                P1SmallStraight.setText(String.valueOf(scoreCalc.calcSmallStraight(dice1eye, dice2eye, dice3eye, dice4eye, dice5eye)));
                P1SmallStraight.setTextColor(RED);
            }

            // LargeStraight 값이 0보다 클 때, 저장된 값이 없을 때 붉은색으로 표시
            if (scoreCalc.calcLargeStraight(dice1eye, dice2eye, dice3eye, dice4eye, dice5eye) > 0) {
                P1LargeStraight.setText(String.valueOf(scoreCalc.calcLargeStraight(dice1eye, dice2eye, dice3eye, dice4eye, dice5eye)));
                P1LargeStraight.setTextColor(RED);
            }

            // Chance 값이 0보다 클 때, 저장된 값이 없을 때 붉은색으로 표시
            if (scoreCalc.calcChance(dice1eye, dice2eye, dice3eye, dice4eye, dice5eye) > 0) {
                P1Chance.setText(String.valueOf(scoreCalc.calcChance(dice1eye, dice2eye, dice3eye, dice4eye, dice5eye)));
                P1Chance.setTextColor(RED);
            }

            // YACHT 값이 0보다 클 때, 저장된 값이 없을 때 붉은색으로 표시
            if (scoreCalc.calcYACHT(dice1eye, dice2eye, dice3eye, dice4eye, dice5eye) > 0) {
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

                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putInt("vsP1Aces", scoreCalc.calcAce(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye));
                                editor.commit();

                                Intent intent = new Intent();
                                intent.putExtra("name","Aces");
                                intent.putExtra("score",scoreCalc.calcAce(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye));
                                setResult(RESULT_OK,intent);
                                dialog.dismiss();
                                finish();
                            }
                        });
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
}