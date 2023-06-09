package com.example.yachtdicev2.etc;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.example.yachtdicev2.chating.user_chat_item;

public class MyDiffUtil extends DiffUtil.ItemCallback<user_chat_item>{



    @Nullable
    @Override
    public Object getChangePayload(@NonNull user_chat_item oldItem, @NonNull user_chat_item newItem) {
        return super.getChangePayload(oldItem, newItem);
    }

    @Override
    public boolean areItemsTheSame(@NonNull user_chat_item oldItem, @NonNull user_chat_item newItem) {
        return oldItem.equals(newItem);
    }

    @Override
    public boolean areContentsTheSame(@NonNull user_chat_item oldItem, @NonNull user_chat_item newItem) {
        return oldItem.equals(newItem);
    }
}
