package com.example.yachtdicev2;


import org.json.JSONException;
import org.json.JSONObject;

public class useJson {

    JSONObject jsonObject = new JSONObject();

    public String diceRollClick(String clickName,String userName,int count
            ,boolean dice1Keep,boolean dice2Keep,boolean dice3Keep,boolean dice4Keep,boolean dice5Keep){

        try {
            jsonObject = new JSONObject();

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
            jsonObject = new JSONObject();

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
            jsonObject = new JSONObject();
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
            jsonObject = new JSONObject();
            jsonObject.put("clickName","currentUser");
            jsonObject.put("userName",userName);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return jsonObject.toString();
    }

    public String userRPS(String category,String player1,String player2){

        try{
            jsonObject = new JSONObject();
            jsonObject.put("category",category);
            jsonObject.put("player1",player1);
            jsonObject.put("player2",player2);
        }catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return jsonObject.toString();
    }

    public String useRPS(String RPS,String userName){

        try{
            jsonObject = new JSONObject();
            jsonObject.put("category","가위바위보대결");
            jsonObject.put("RPS",RPS);
            jsonObject.put("userName",userName);
        }catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return jsonObject.toString();
    }

    public String youWin(String userName){

        try{
            jsonObject = new JSONObject();
            jsonObject.put("category","가위바위보승패");
            jsonObject.put("result","win");
            jsonObject.put("userName",userName);
        }catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return jsonObject.toString();
    }

    public String youLose(String userName){

        try{
            jsonObject = new JSONObject();
            jsonObject.put("category","가위바위보승패");
            jsonObject.put("result","lose");
            jsonObject.put("userName",userName);
        }catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return jsonObject.toString();
    }

    public String youTie(String userName){

        try{
            jsonObject = new JSONObject();
            jsonObject.put("category","가위바위보승패");
            jsonObject.put("result","tie");
            jsonObject.put("userName",userName);
        }catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return jsonObject.toString();
    }

    public String createRoom(int num,String userName){
        try {
            jsonObject = new JSONObject();
            jsonObject.put("category","createRoom");
            jsonObject.put("roomNum",num);
            jsonObject.put("userName",userName);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return jsonObject.toString();
    }

    public String getRoomList(){

        try{
            jsonObject = new JSONObject();
            jsonObject.put("category","getRoomList");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return jsonObject.toString();
    }

    public String enterRoom(int roomNum){

        try {
            jsonObject = new JSONObject();
            jsonObject.put("category","enterRoom");
            jsonObject.put("roomNum",roomNum);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return jsonObject.toString();
    }

    public String gameEnd(int player1totalScore,int player2totalScore){

        try{
            jsonObject = new JSONObject();
            jsonObject.put("category","End");
            jsonObject.put("player1totalScore",player1totalScore);
            jsonObject.put("player2totalScore",player2totalScore);

        }catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return jsonObject.toString();
    }
}
