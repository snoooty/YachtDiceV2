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

    public String diceKeepClick(String clickName,String userName,int keepDice,boolean booleanKeep){
        try{
            jsonObject.put("clickName",clickName);
            jsonObject.put("userName",userName);
            jsonObject.put("keepDice",keepDice);
            jsonObject.put("booleanKeep",booleanKeep);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return jsonObject.toString();
    }

    public String socreClick(String clickName,String userName,String scoreName,int score){
        try {
            jsonObject.put("clickName",clickName);
            jsonObject.put("userName",userName);
            jsonObject.put("socreName",scoreName);
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
