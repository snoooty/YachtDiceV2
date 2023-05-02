package com.example.yachtdicev2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class chatingAdapter extends RecyclerView.Adapter<chatingAdapter.ViewHolder> {

    private ArrayList<user_chat_item> adapterChatList;

    // 리스트 아이템을 가져와줌
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item, parent, false);
        return new ViewHolder(view);
    }

    // 받아온 데이터 바인딩
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(adapterChatList.get(position));
    }

    // 리사이클러뷰 리스트 사이즈를 불러옴
    @Override
    public int getItemCount(){
        return adapterChatList.size();
    }

    public void setAdapterChatList(ArrayList<user_chat_item> serverDataList){
        this.adapterChatList = serverDataList;
        notifyDataSetChanged();
    }

    // 뷰홀더 생성.
    class ViewHolder extends RecyclerView.ViewHolder{
        TextView user_name;
        TextView user_content;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            user_name = itemView.findViewById(R.id.user_ID_text);
            user_content = itemView.findViewById(R.id.user_content);
        }

        void onBind(user_chat_item item){
            user_name.setText(item.user_name);
            user_content.setText(item.user_content);
        }
    }
}
