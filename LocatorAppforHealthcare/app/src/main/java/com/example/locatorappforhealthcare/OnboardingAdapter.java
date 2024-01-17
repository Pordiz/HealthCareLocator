package com.example.locatorappforhealthcare;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OnboardingAdapter extends RecyclerView.Adapter<OnboardingAdapter.OnboardingViewHolder> {

    private List<OnboardingItem> onboardingItems;

    public OnboardingAdapter(List<OnboardingItem> onboardingItems) {
        this.onboardingItems = onboardingItems;
    }

    @NonNull
    @Override
    public OnboardingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OnboardingViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.onboarding_item, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull OnboardingViewHolder holder, int position) {
        OnboardingItem onboardingItem = onboardingItems.get(position);
        holder.title.setText(onboardingItem.getTitle());
        holder.description.setText(onboardingItem.getDescription());
        holder.image.setImageResource(onboardingItem.getImageResource());

        if (position == onboardingItems.size() - 1) {
            holder.startButton.setVisibility(View.VISIBLE);
            holder.startButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(holder.itemView.getContext(), MainPage.class);
                    holder.itemView.getContext().startActivity(intent);
                }
            });
        } else {
            holder.startButton.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return onboardingItems.size();
    }

    class OnboardingViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView description;
        private ImageView image;
        private Button startButton;

        OnboardingViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.onboarding_title);
            description = itemView.findViewById(R.id.onboarding_description);
            image = itemView.findViewById(R.id.onboarding_image);
            startButton = itemView.findViewById(R.id.start_button);
        }
    }
}
