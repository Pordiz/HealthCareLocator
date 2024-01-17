package com.example.locatorappforhealthcare;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {
    private List<ChatMessage> chatMessages;
    private Context context;
    private ChatOptionClickListener chatOptionClickListener;

    public ChatAdapter(List<ChatMessage> chatMessages, Context context, ChatOptionClickListener chatOptionClickListener) {
        this.chatMessages = chatMessages;
        this.context = context;
        this.chatOptionClickListener = chatOptionClickListener;
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == ChatMessage.Type.USER.ordinal()) {
            View view = inflater.inflate(R.layout.user_message_item, parent, false);
            return new ChatViewHolder(view);
        } else {
            View view = inflater.inflate(R.layout.chat_item, parent, false);
            return new ChatViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        ChatMessage chatMessage = chatMessages.get(position);

        if (chatMessage.getType() == ChatMessage.Type.NORMAL) {
            holder.chatbotIcon.setVisibility(View.VISIBLE);
            holder.messageTextView.setText(chatMessage.getMessage());
            holder.messageTextView.setTextColor(Color.BLACK);
            holder.messageTextView.setVisibility(View.VISIBLE);
            holder.messageTextView.setBackgroundResource(R.drawable.rounded_chat_bubble_bot);
            holder.optionButton.setVisibility(View.GONE);
            holder.optionButton.setOnClickListener(null);
        } else if (chatMessage.getType() == ChatMessage.Type.USER) {
            holder.messageTextView.setText(chatMessage.getMessage());
            holder.messageTextView.setVisibility(View.VISIBLE);
            holder.messageTextView.setBackgroundResource(R.drawable.rounded_chat_bubble_user);
            holder.messageTextView.setGravity(Gravity.END);
        } else {
            holder.chatbotIcon.setVisibility(View.GONE);
            holder.optionButton.setVisibility(View.VISIBLE);
            holder.optionButton.setText(chatMessage.getMessage());
            holder.optionButton.setBackgroundResource(R.drawable.rounded_chat_bubble_user);
            holder.messageTextView.setVisibility(View.GONE);
            holder.optionButton.setOnClickListener(v -> {
                chatOptionClickListener.onChatOptionClick(chatMessage.getMessage());
            });
        }
    }

    @Override
    public int getItemCount() {
        return chatMessages.size();
    }

    @Override
    public int getItemViewType(int position) {
        return chatMessages.get(position).getType().ordinal();
    }

    public static class ChatViewHolder extends RecyclerView.ViewHolder {
        TextView messageTextView;
        Button optionButton;
        ImageView chatbotIcon;

        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);
            messageTextView = itemView.findViewById(R.id.message_text_view);
            optionButton = itemView.findViewById(R.id.option_button);
            chatbotIcon = itemView.findViewById(R.id.chatbot_icon);
        }
    }

    public interface ChatOptionClickListener {
        void onChatOptionClick(String option);
    }
}