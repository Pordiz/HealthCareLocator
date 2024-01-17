package com.example.locatorappforhealthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainPage extends AppCompatActivity {
    private Button dctorsbtn, srvicebtn, aboutbtn, confirmationButton;
    private FloatingActionButton chatbotButton;
    private ImageView chatbotPulse;
    private TextView chatbotMessage;
    private boolean doctorSelected = false;
    private boolean serviceSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        srvicebtn = findViewById(R.id.srvicebtn);
        dctorsbtn = findViewById(R.id.dctorsbtn);
        aboutbtn = findViewById(R.id.abotbtn);
        chatbotButton = findViewById(R.id.chatbot_button);
        confirmationButton = findViewById(R.id.confirmation_button);
        chatbotPulse = findViewById(R.id.chatbot_pulse);
        chatbotMessage = findViewById(R.id.chatbot_message);

        srvicebtn.setOnClickListener(view -> {
            if (serviceSelected) {
                serviceSelected = false;
            } else {
                serviceSelected = true;
                doctorSelected = false;
            }
            updateButtonStates();
        });

        dctorsbtn.setOnClickListener(view -> {
            if (doctorSelected) {
                doctorSelected = false;
            } else {
                doctorSelected = true;
                serviceSelected = false;
            }
            updateButtonStates();
        });

        aboutbtn.setOnClickListener(view -> openAboutpage());
        chatbotButton.setOnClickListener(view -> openChatbotActivity());
        confirmationButton.setOnClickListener(view -> {
            if (doctorSelected) {
                openDoctorsPage();
            } else if (serviceSelected) {
                openServicePage();
            }
        });

        startPulsatingAnimation();
        startTypingAnimation();
    }

    private void startPulsatingAnimation() {
        ValueAnimator animator = ValueAnimator.ofFloat(0.0f, 1.0f);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(1000L);
        animator.addUpdateListener(animation -> chatbotPulse.setAlpha((Float) animation.getAnimatedValue()));
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationRepeat(Animator animation) {
                super.onAnimationRepeat(animation);
                chatbotPulse.setAlpha(1.0f);
            }
        });
        animator.start();
    }

    private void startTypingAnimation() {
        final Handler handler = new Handler();
        final Runnable typingRunnable = new Runnable() {
            private int charIndex = 0;
            private String message = "Need help?";

            @Override
            public void run() {
                if (charIndex <= message.length()) {
                    chatbotMessage.setText(message.substring(0, charIndex));
                    charIndex++;
                    handler.postDelayed(this, 100);
                } else {
                    charIndex = 0;
                    handler.postDelayed(this, 2000);
                }
            }
        };
        handler.post(typingRunnable);
    }

    private void updateButtonStates() {
        AnimatorSet animatorSetDoctor = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.flip_anim);
        AnimatorSet animatorSetService = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.flip_anim);

        animatorSetDoctor.setTarget(dctorsbtn);
        animatorSetService.setTarget(srvicebtn);
        if (doctorSelected) {
            animatorSetDoctor.start();
            dctorsbtn.setBackgroundResource(R.drawable.doctor_after);
            srvicebtn.setBackgroundResource(R.drawable.healthcare_before);
        } else if (serviceSelected) {
            animatorSetService.start();
            dctorsbtn.setBackgroundResource(R.drawable.doctor_before);
            srvicebtn.setBackgroundResource(R.drawable.healthcare_after);
        } else {
            dctorsbtn.setBackgroundResource(R.drawable.doctor_before);
            srvicebtn.setBackgroundResource(R.drawable.healthcare_before);
        }

        if (doctorSelected || serviceSelected) {
            confirmationButton.setVisibility(View.VISIBLE);
        } else {
            confirmationButton.setVisibility(View.GONE);
        }
    }


    public void openAboutpage(){
        Intent intent = new Intent(this, about.class);
        startActivity(intent);
    }

    public void openServicePage(){
        Intent intent = new Intent(this, servicePage.class);
        startActivity(intent);

    }
    public void openDoctorsPage(){
        Intent intent = new Intent(this, doctorsPage.class);
        startActivity(intent);
    }
    public void openChatbotActivity() {
        Intent intent = new Intent(this, ChatbotActivity.class);
        startActivity(intent);
    }
}