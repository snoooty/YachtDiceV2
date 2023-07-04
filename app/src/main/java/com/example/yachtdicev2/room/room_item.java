package com.example.yachtdicev2.room;

public class room_item {

    String user_Name;
    int room_Num;

    public room_item(String user_Name,int room_Num){
        this.room_Num = room_Num;
        this.user_Name = user_Name;
    }

    public String getUser_Name(){
        return user_Name;
    }

    public void setUser_Name(String user_Name){
        this.user_Name = user_Name;
    }

    public int getRoom_Num(){
        return room_Num;
    }

    public void setRoom_Num(int room_Num){
        this.room_Num = room_Num;
    }
}
