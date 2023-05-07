package com.example.yachtdicev2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class chatingAdapter extends RecyclerView.Adapter<chatingAdapter.ViewHolder> {

    private ArrayList<user_chat_item> adapterChatList;
    private  String TAG = "Adapter";

    // 리스트 아이템을 가져와줌
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item, parent, false);
        Log.e(TAG,"onCreateViewHolder");
        return new ViewHolder(view);
    }

    // 받아온 데이터 바인딩
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(adapterChatList.get(position));
        Log.e(TAG,"onBindViewHolder");
    }

    // 리사이클러뷰 리스트 사이즈를 불러옴
    @Override
    public int getItemCount(){
        Log.e(TAG,"getItemCount : " + adapterChatList.size());
        return adapterChatList.size();
    }

    public void setAdapterChatList(ArrayList<user_chat_item> serverDataList){
        Log.e(TAG,"setAdapterChatList");
        this.adapterChatList = serverDataList;
        notifyDataSetChanged();
    }

    @Override
    public void onViewAttachedToWindow(@NonNull ViewHolder holder) {
        Log.e(TAG,"onViewAttachedToWindow");
        super.onViewAttachedToWindow(holder);
    }

    // 뷰홀더 생성.
    class ViewHolder extends RecyclerView.ViewHolder{
        TextView user_name;
        TextView user_content;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            Log.e(TAG,"ViewHolder");

            user_name = itemView.findViewById(R.id.user_ID_text);
            user_content = itemView.findViewById(R.id.user_content);
        }

        void onBind(user_chat_item item){
            Log.e(TAG,"onBind");
            user_name.setText(item.user_name);
            user_content.setText(item.user_content);
        }
    }
}
