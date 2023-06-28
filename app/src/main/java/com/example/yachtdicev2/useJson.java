package com.example.yachtdicev2;


import org.json.JSONException;
import org.json.JSONObject;

public class useJson {

    JSONObject jsonObject = new JSONObject();

    public String diceRollClick(String clickName,String userName,int count
            ,boolean dice1Keep,boolean dice2Keep,boolean dice3Keep,boolean dice4Keep,boolean dice5Keep){

        try {

            jsonObject.put("clickName",clickName);
            jsonObject.put("userName",userName);
            jsonObject.put("count",count);
            jsonObject.put("dice1Keep",dice1Keep);
            jsonObject.put("dice2Keep",dice2Keep);
            jsonObject.put("dice3Keep",dice3Keep);
            jsonObject.put("dice4Keep",dice4Keep);
            jsonObject.put("dice5Keep",dice5Keep);

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return jsonObject.toString();
    }

    public String diceKeepClick(String clickName,String userName,int keepDice,boolean booleanKeep1,
                                boolean booleanKeep2,boolean booleanKeep3,boolean booleanKeep4,boolean booleanKeep5){
        try{
            jsonObject.put("clickName",clickName);
            jsonObject.put("userName",userName);
            jsonObject.put("keepDice",keepDice);
            jsonObject.put("booleanKeep1",booleanKeep1);
            jsonObject.put("booleanKeep2",booleanKeep2);
            jsonObject.put("booleanKeep3",booleanKeep3);
            jsonObject.put("booleanKeep4",booleanKeep4);
            jsonObject.put("booleanKeep5",booleanKeep5);

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return jsonObject.toString();
    }

    public String scoreClick(String clickName,String player,String scoreName,int score){
        try {
            jsonObject.put("clickName",clickName);
            jsonObject.put("player",player);
            jsonObject.put("scoreName",scoreName);
            jsonObject.put("score",score);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return jsonObject.toString();
    }

    public String startUser(String userName){

        try{
            jsonObject.put("clickName","currentUser");
            jsonObject.put("userName",userName);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return jsonObject.toString();
    }
}
