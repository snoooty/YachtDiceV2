package com.example.yachtdicev2;

public class user_chat_item {

    String user_name;

    String user_content;

    public user_chat_item(String user_name, String user_content){

        this.user_name = user_name;
        this.user_content = user_content;
    }

    public String getUser_name(){
        return user_name;
    }

    public void setUser_name(String user_name){
        this.user_name = user_name;
    }

    public String getUser_content(){
        return user_content;
    }

    public void setUser_content(String user_content){
        this.user_content = user_content;
    }

}
