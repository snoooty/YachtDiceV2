package com.example.yachtdicev2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class chatingAdapter extends RecyclerView.Adapter<chatingAdapter.UserViewHolder> {

    private ArrayList<user_chat_item> adapterChatList;
    private  String TAG = "Adapter";
    String login_user;

    public chatingAdapter (String login_user){
        this.login_user = login_user;
    }

    // 리스트 아이템을 가져와줌
    public chatingAdapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        Log.e(TAG,"현재유저이름 : " + login_user);
        Log.e(TAG,"getViewSrc : " + getViewSrc(viewType));
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(getViewSrc(viewType), parent, false);
        Log.e(TAG,"onCreateViewHolder");
        return new UserViewHolder(view, viewType);
    }

    // 받아온 데이터 바인딩
    @Override
    public void onBindViewHolder(@NonNull chatingAdapter.UserViewHolder holder, int position) {
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
        adapterChatList = serverDataList;
        notifyDataSetChanged();
    }

    // 뷰홀더 생성.
    class UserViewHolder extends RecyclerView.ViewHolder{
        TextView user_name,user_content;
        TextView my_name,my_content;
        TextView server_name,server_content;
        private int viewType;
        public UserViewHolder(@NonNull View itemView, int viewType){
            super(itemView);
            this.viewType = viewType;
            Log.e(TAG,"ViewHolder");
        }

        void onBind(user_chat_item item){
            Log.e(TAG,"onBind");
            if (viewType == TYPE_SENT_MESSAGE) {
                bindMyChat(item);
            } else if (viewType == TYPE_SERVER_NTF) {
                bindServerNTF(item);
            } else if (viewType == TYPE_RECEIVED_MESSAGE){
                bindUserChat(item);
            }
        }

        public void bindMyChat(user_chat_item item){
            my_name = itemView.findViewById(R.id.my_ID_text);
            my_content = itemView.findViewById(R.id.my_content);
            my_name.setText(item.getUser_name());
            my_content.setText(item.getUser_content());
        }

        public void bindUserChat(user_chat_item item){
            user_name = itemView.findViewById(R.id.user_ID_text);
            user_content = itemView.findViewById(R.id.user_content);
            user_name.setText(item.getUser_name());
            user_content.setText(item.getUser_content());
        }

        public void bindServerNTF(user_chat_item item){
            server_name = itemView.findViewById(R.id.server_ID_text);
            server_content = itemView.findViewById(R.id.server_content);
            server_name.setText(item.getUser_name());
            server_content.setText(item.getUser_content());
        }
    }

    // view type
    private int TYPE_SENT_MESSAGE = 101;
    private int TYPE_RECEIVED_MESSAGE = 102;
    private int TYPE_SERVER_NTF = 103;
    private int getViewSrc(int viewType){
        if (viewType == TYPE_RECEIVED_MESSAGE){
            Log.e(TAG,"타입 c 연결");
            return R.layout.chat_item;
        } else if(viewType == TYPE_SENT_MESSAGE){
            Log.e(TAG,"타입 a 연결");
            return R.layout.my_chat_item;
        } else {
            Log.e(TAG,"타입 b 연결");
            return R.layout.server_notification;
        }
    }

    @Override
    public int getItemViewType(int position) {
        Log.e(TAG,"Type 받기전 닉네임 : " + login_user);
        Log.e(TAG,"boolean login_user : " + adapterChatList.get(adapterChatList.size() - 1).getUser_name().equals(login_user));
        Log.e(TAG,"boolean Server 알림 : " + adapterChatList.get(adapterChatList.size() - 1).getUser_name().equals("Server 알림"));
        Log.e(TAG,"ArrayList name : " + adapterChatList.get(adapterChatList.size() - 1).getUser_name());
        Log.e(TAG,"position : " + position);
        if (adapterChatList.get(position).getUser_name().equals(login_user)){
            Log.e(TAG,"타입 a");
            return TYPE_SENT_MESSAGE;
        } else if (adapterChatList.get(position).getUser_name().equals("Server알림")){
            Log.e(TAG,"타입 b");
            return TYPE_SERVER_NTF;
        }else {
            Log.e(TAG,"타입 c");
            return TYPE_RECEIVED_MESSAGE;
        }
    }
}
