package com.example.yachtdicev2.room;

import com.example.yachtdicev2.chating.user_chat_item;

import java.util.Objects;

public class room_item {

    String user_Name;
    int room_Num;
    int personnel;

    public room_item(String user_Name,int room_Num,int personnel){
        this.room_Num = room_Num;
        this.user_Name = user_Name;
        this.personnel = personnel;
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_Name,room_Num,personnel);
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

    public int getPersonnel(){
        return personnel;
    }

    public void setPersonnel(int personnel){
        this.personnel = personnel;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true; // 자신과 비교하면 true
        if(o == null) return false; // null값이 들어오거나
        room_item roomItem = (room_item) o;
        if (roomItem.room_Num == this.room_Num){
            return true;
        }
        if (roomItem.user_Name == this.user_Name){
            return true;
        }

        return Objects.equals(user_Name, roomItem.user_Name) ||
                Objects.equals(room_Num, roomItem.room_Num); // 들어온 객체의 값 하나하나 비교
    }
}
