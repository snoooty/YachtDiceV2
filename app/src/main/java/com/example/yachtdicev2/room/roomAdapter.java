package com.example.yachtdicev2.room;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yachtdicev2.R;
import com.example.yachtdicev2.chating.user_chat_item;

import java.util.ArrayList;

public class roomAdapter extends RecyclerView.Adapter<roomAdapter.ViewHolder>{

    private ArrayList<room_item> roomItemArrayList = null;
    String TAG = "roomAdapter";

    public roomAdapter(){

    }

    public roomAdapter(ArrayList<room_item> list){
        roomItemArrayList = list;
    }

    @NonNull
    @Override
    public roomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.roomlist_item, parent, false) ;
        roomAdapter.ViewHolder vh = new roomAdapter.ViewHolder(view) ;

        return vh ;
    }

    @Override
    public void onBindViewHolder(@NonNull roomAdapter.ViewHolder holder, int position) {

        holder.onBind(roomItemArrayList.get(position));

    }

    @Override
    public int getItemCount(){

        if (roomItemArrayList == null){
            return 0;
        }

        Log.e(TAG,"getItemCount : " + roomItemArrayList.size());
        return roomItemArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView roomNum,mainUser,personnel;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        void onBind(room_item item){
            roomNum = itemView.findViewById(R.id.roomNum);
            mainUser = itemView.findViewById(R.id.userName);
            personnel = itemView.findViewById(R.id.personnel);

            roomNum.setText(String.valueOf(item.getRoom_Num()));
            mainUser.setText(item.getUser_Name());
            personnel.setText(String.valueOf(item.getPersonnel()));
        }
    }

}


