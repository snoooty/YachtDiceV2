package com.example.yachtdicev2.gameLogic;

import android.util.Log;

import androidx.annotation.NonNull;

public class ScoreCalculation {

    String TAG = "ScoreCalculation";

    int aces,twos,threes,fours,fives,sixes,sum,bonus,threeOfAKind,fourOfaKind,fullHouse;
    int smallStraight,largeStraight,chance,YACHTZEE,YACHTZEEBonus;

    // aces socre
    @NonNull
    public int calcAce(int dice1eye, int dice2eye, int dice3eye, int dice4eye, int dice5eye){

        int eye1, eye2, eye3, eye4, eye5;

        if (dice1eye != 1) {
            eye1 = 0;
        } else {
            eye1 = dice1eye;
        }

        if (dice2eye != 1) {
            eye2 = 0;
        } else {
            eye2 = dice2eye;
        }

        if (dice3eye != 1) {
            eye3 = 0;
        } else {
            eye3 = dice3eye;
        }

        if (dice4eye != 1) {
            eye4 = 0;
        } else {
            eye4 = dice4eye;
        }

        if (dice5eye != 1) {
            eye5 = 0;
        } else {
            eye5 = dice5eye;
        }

        aces = eye1 + eye2 + eye3 + eye4 + eye5;

        return aces;
    }

    @NonNull
    public int calcTwo(int dice1eye, int dice2eye, int dice3eye, int dice4eye, int dice5eye){

        int eye1, eye2, eye3, eye4, eye5;

        if (dice1eye != 2) {
            eye1 = 0;
        } else {
            eye1 = dice1eye;
        }

        if (dice2eye != 2) {
            eye2 = 0;
        } else {
            eye2 = dice2eye;
        }

        if (dice3eye != 2) {
            eye3 = 0;
        } else {
            eye3 = dice3eye;
        }

        if (dice4eye != 2) {
            eye4 = 0;
        } else {
            eye4 = dice4eye;
        }

        if (dice5eye != 2) {
            eye5 = 0;
        } else {
            eye5 = dice5eye;
        }

        twos = eye1 + eye2 + eye3 + eye4 + eye5;

        return twos;
    }

    @NonNull
    public int calcThree(int dice1eye, int dice2eye, int dice3eye, int dice4eye, int dice5eye){

        int eye1, eye2, eye3, eye4, eye5;

        if (dice1eye != 3) {
            eye1 = 0;
        } else {
            eye1 = dice1eye;
        }
        if (dice2eye != 3) {
            eye2 = 0;
        } else {
            eye2 = dice2eye;
        }
        if (dice3eye != 3) {
            eye3 = 0;
        } else {
            eye3 = dice3eye;
        }
        if (dice4eye != 3) {
            eye4 = 0;
        } else {
            eye4 = dice4eye;
        }
        if (dice5eye != 3) {
            eye5 = 0;
        } else {
            eye5 = dice5eye;
        }
        threes = eye1 + eye2 + eye3 + eye4 + eye5;

        return threes;
    }

    @NonNull
    public int calcFour(int dice1eye, int dice2eye, int dice3eye, int dice4eye, int dice5eye){

        int eye1, eye2, eye3, eye4, eye5;

        if (dice1eye != 4) {
            eye1 = 0;
        } else {
            eye1 = dice1eye;
        }
        if (dice2eye != 4) {
            eye2 = 0;
        } else {
            eye2 = dice2eye;
        }
        if (dice3eye != 4) {
            eye3 = 0;
        } else {
            eye3 = dice3eye;
        }
        if (dice4eye != 4) {
            eye4 = 0;
        } else {
            eye4 = dice4eye;
        }
        if (dice5eye != 4) {
            eye5 = 0;
        } else {
            eye5 = dice5eye;
        }
        fours = eye1 + eye2 + eye3 + eye4 + eye5;

        return fours;
    }

    @NonNull
    public int calcFive(int dice1eye, int dice2eye, int dice3eye, int dice4eye, int dice5eye){

        int eye1, eye2, eye3, eye4, eye5;

        if (dice1eye != 5) {
            eye1 = 0;
        } else {
            eye1 = dice1eye;
        }
        if (dice2eye != 5) {
            eye2 = 0;
        } else {
            eye2 = dice2eye;
        }
        if (dice3eye != 5) {
            eye3 = 0;
        } else {
            eye3 = dice3eye;
        }
        if (dice4eye != 5) {
            eye4 = 0;
        } else {
            eye4 = dice4eye;
        }
        if (dice5eye != 5) {
            eye5 = 0;
        } else {
            eye5 = dice5eye;
        }

        fives = eye1 + eye2 + eye3 + eye4 + eye5;

        return fives;
    }

    @NonNull
    public int calcSix(int dice1eye, int dice2eye, int dice3eye, int dice4eye, int dice5eye){

        int eye1, eye2, eye3, eye4, eye5;

        if (dice1eye != 6) {
            eye1 = 0;
        } else {
            eye1 = dice1eye;
        }
        if (dice2eye != 6) {
            eye2 = 0;
        } else {
            eye2 = dice2eye;
        }
        if (dice3eye != 6) {
            eye3 = 0;
        } else {
            eye3 = dice3eye;
        }
        if (dice4eye != 6) {
            eye4 = 0;
        } else {
            eye4 = dice4eye;
        }
        if (dice5eye != 6) {
            eye5 = 0;
        } else {
            eye5 = dice5eye;
        }

        sixes = eye1 + eye2 + eye3 + eye4 + eye5;

        return sixes;
    }

    @NonNull
    public int calcBonus(int getAces, int getTwos, int getThrees, int getFours, int getFives, int getSixes){

        if (getAces + getTwos + getThrees + getFours + getFives + getSixes >= 63){
            bonus = 35;
        }else {
            bonus = 0;
        }

        return bonus;
    }

    @NonNull
    public int calcSum(int getAces, int getTwos, int getThrees, int getFours, int getFives, int getSixes){

        sum = getAces + getTwos + getThrees + getFours + getFives + getSixes;

        return sum;
    }

    @NonNull
    public int calcThreeOfAKind(int dice1eye, int dice2eye, int dice3eye, int dice4eye, int dice5eye){

        if (boolThreeOfAKind(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye)){
            threeOfAKind = dice1eye + dice2eye + dice3eye + dice4eye + dice5eye;
        }else {
            threeOfAKind = 0;
        }

        return threeOfAKind;
    }

    @NonNull
    public int calcFourOfAKind(int dice1eye, int dice2eye, int dice3eye, int dice4eye, int dice5eye){

        if (boolFourOfAKind(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye)){
            fourOfaKind = dice1eye + dice2eye + dice3eye + dice4eye + dice5eye;
        }else {
            fourOfaKind = 0;
        }

        return fourOfaKind;
    }

    @NonNull
    public int calcFullHouse(int dice1eye, int dice2eye, int dice3eye, int dice4eye, int dice5eye){

        if (boolFullHouse(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye)){
            fullHouse = 25;
        }else {
            fullHouse = 0;
        }

        return fullHouse;
    }

    @NonNull
    public int calcSmallStraight(int dice1eye, int dice2eye, int dice3eye, int dice4eye, int dice5eye){

        if (boolSmallStraight(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye)){
            smallStraight = 30;
        }else {
            smallStraight = 0;
        }

        return smallStraight;
    }

    @NonNull
    public int calcLargeStraight(int dice1eye, int dice2eye, int dice3eye, int dice4eye, int dice5eye){

        if (boolLargeStraight(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye)){
            largeStraight = 40;
        }else {
            largeStraight = 0;
        }

        return largeStraight;
    }

    @NonNull
    public int calcChance(int dice1eye, int dice2eye, int dice3eye, int dice4eye, int dice5eye){

        chance = dice1eye + dice2eye + dice3eye + dice4eye + dice5eye;

        return chance;
    }

    @NonNull
    public int calcYACHT(int dice1eye, int dice2eye, int dice3eye, int dice4eye, int dice5eye){

        if (boolYACHT(dice1eye,dice2eye,dice3eye,dice4eye,dice5eye)){
            YACHTZEE = 50;
        }else {
            YACHTZEE = 0;
        }

        return YACHTZEE;
    }

    @NonNull
    public int calcYACHTBonus(boolean boolAce, boolean boolTwo, boolean boolThree, boolean boolFour,
                              boolean boolFive, boolean boolSix, boolean boolThreeOfAKind,
                              boolean boolFourOfAKind, boolean boolFullHouse, boolean boolSmallStraight,
                              boolean boolLargeStraight, boolean boolChance, boolean boolYACHT){

        int aceBonus, twoBonus, threeBonus, fourBonus, fiveBonus, sixBonus;
        int threeOfAKindBonus, fourOfAKindBonus, fullHouseBonus;
        int smallStraightBonus, largeStraightBonus, chanceBonus, YACHTBonus;

        if (boolYACHT){

            if (boolAce){
                aceBonus = 50;
            }else {
                aceBonus = 0;
            }
            if (boolTwo){
                twoBonus = 50;
            }else {
                twoBonus = 0;
            }
            if (boolThree){
                threeBonus = 50;
            }else {
                threeBonus = 0;
            }
            if (boolFour){
                fourBonus = 50;
            }else {
                fourBonus = 0;
            }
            if (boolFive){
                fiveBonus = 50;
            }else {
                fiveBonus  = 0;
            }
            if (boolSix){
                sixBonus = 50;
            }else {
                sixBonus = 0;
            }
            if (boolThreeOfAKind){
                threeOfAKindBonus = 50;
            }else {
                threeOfAKindBonus = 0;
            }
            if (boolFourOfAKind){
                fourOfAKindBonus = 50;
            }else {
                fourOfAKindBonus = 0;
            }
            if (boolFullHouse){
                fullHouseBonus = 50;
            }else {
                fullHouseBonus = 0;
            }
            if (boolSmallStraight){
                smallStraightBonus = 50;
            }else {
                smallStraightBonus = 0;
            }
            if (boolLargeStraight){
                largeStraightBonus = 50;
            }else {
                largeStraightBonus = 0;
            }
            if (boolChance){
                chanceBonus = 50;
            }else {
                chanceBonus = 0;
            }

            YACHTBonus = 50;

            YACHTZEEBonus = aceBonus + twoBonus + threeBonus + fourBonus + fiveBonus + sixBonus +
                    threeOfAKindBonus + fourOfAKindBonus + fullHouseBonus + smallStraightBonus +
                    largeStraightBonus + chanceBonus + YACHTBonus;
        }



        return YACHTZEEBonus;
    }

    public boolean boolThreeOfAKind(int dice1eye, int dice2eye, int dice3eye, int dice4eye, int dice5eye){

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

    public boolean boolFourOfAKind(int dice1eye, int dice2eye, int dice3eye, int dice4eye, int dice5eye){

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

    public boolean boolFullHouse(int dice1eye, int dice2eye, int dice3eye, int dice4eye, int dice5eye){

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
        }

        return false;
    }

    public boolean boolSmallStraight(int dice1eye, int dice2eye, int dice3eye, int dice4eye, int dice5eye){

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

        return false;
    }

    public boolean boolLargeStraight(int dice1eye, int dice2eye, int dice3eye, int dice4eye, int dice5eye){

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

        return false;
    }

    public boolean boolYACHT(int dice1eye, int dice2eye, int dice3eye, int dice4eye, int dice5eye){

        if(dice1eye == dice2eye && dice2eye == dice3eye && dice3eye == dice4eye && dice4eye == dice5eye){
            return true;
        }

        return false;
    }

}
