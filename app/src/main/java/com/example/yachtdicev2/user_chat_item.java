package com.example.yachtdicev2;

import java.util.Objects;

public class user_chat_item {

    String user_name;
    String user_content;

    public user_chat_item(String user_name, String user_content){

        this.user_name = user_name;
        this.user_content = user_content;
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_name, user_content);
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

    @Override
    public boolean equals(Object o){
        if(this == o) return true; // 자신과 비교하면 true
        if(o == null || getClass() != o.getClass()) return false; // null값이 들어오거나 다른 클래스가 들어오면 false
        user_chat_item userChatItem = (user_chat_item) o;
        return Objects.equals(user_name, userChatItem.user_name) &&
                Objects.equals(user_content, userChatItem.user_content); // 들어온 객체의 값 하나하나 비교
    }
}
