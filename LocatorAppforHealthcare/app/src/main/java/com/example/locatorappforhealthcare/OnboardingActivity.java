package com.example.locatorappforhealthcare;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class OnboardingActivity extends AppCompatActivity {

    private ViewPager2 onboardingViewPager;
    private LinearLayout dotsLayout;
    private ImageView[] dots;
    private Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);
        showTermsAndConditionsDialog();
        onboardingViewPager = findViewById(R.id.onboarding_view_pager);
        dotsLayout = findViewById(R.id.dots_layout);
        startButton = findViewById(R.id.start_button);

        setupOnboardingItems();

        onboardingViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                updateDots(position);

                if (position == onboardingViewPager.getAdapter().getItemCount() - 1) {
                    startButton.setVisibility(View.VISIBLE);
                    startButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(OnboardingActivity.this, MainPage.class);
                            startActivity(intent);
                        }
                    });
                } else {
                    startButton.setVisibility(View.GONE);
                }
            }
        });

        createDots();
    }

    private void setupOnboardingItems() {
        List<OnboardingItem> onboardingItems = new ArrayList<>();

        onboardingItems.add(new OnboardingItem(R.drawable.on_plain, "Title 1", "Description 1"));
        onboardingItems.add(new OnboardingItem(R.drawable.on1_plain, "Title 2", "Description 2"));
        onboardingItems.add(new OnboardingItem(R.drawable.on2_plain, "Title 3", "Description 3"));
        onboardingItems.add(new OnboardingItem(R.drawable.on3_plain, "Title 4", "Description 4"));

        OnboardingAdapter onboardingAdapter = new OnboardingAdapter(onboardingItems);
        onboardingViewPager.setAdapter(onboardingAdapter);
    }

    private void createDots() {
        int count = onboardingViewPager.getAdapter().getItemCount();
        dots = new ImageView[count];

        for (int i = 0; i < count; i++) {
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.default_dot));
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(10, 0, 10, 0);
            dotsLayout.addView(dots[i], layoutParams);
        }

        if (dots.length > 0) {
            dots[0].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.selected_dot));
        }
    }

    private void updateDots(int currentPosition) {
        for (int i = 0; i < dots.length; i++) {
            if (i == currentPosition) {
                dots[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.selected_dot));
            } else {
                dots[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.default_dot));
            }
        }
    }
    private void showTermsAndConditionsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.terms_and_conditions_dialog, null);
        CheckBox termsCheckBox = view.findViewById(R.id.terms_and_conditions_checkbox);
        CheckBox privacyCheckBox = view.findViewById(R.id.privacy_policy_checkbox);

        builder.setView(view)
                .setCancelable(false);

        AlertDialog dialog = builder.create();
        dialog.show();

        MaterialButton declineButton = view.findViewById(R.id.decline_button);
        MaterialButton acceptButton = view.findViewById(R.id.accept_button);
        acceptButton.setVisibility(View.GONE);

        CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (termsCheckBox.isChecked() && privacyCheckBox.isChecked()) {
                    declineButton.setVisibility(View.GONE);
                    acceptButton.setVisibility(View.VISIBLE);
                } else {
                    declineButton.setVisibility(View.VISIBLE);
                    acceptButton.setVisibility(View.GONE);
                }
            }
        };

        termsCheckBox.setOnCheckedChangeListener(onCheckedChangeListener);
        privacyCheckBox.setOnCheckedChangeListener(onCheckedChangeListener);

        declineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // User declined the terms, close the app
                finish();
            }
        });

        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // User accepted the terms, continue with the onboarding
                dialog.dismiss();
            }
        });
    }



}
