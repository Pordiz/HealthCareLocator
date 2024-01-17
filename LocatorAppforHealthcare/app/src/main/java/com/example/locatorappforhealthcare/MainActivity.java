package com.example.locatorappforhealthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.view.animation.AnimationUtils;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
public class MainActivity extends AppCompatActivity {

    Handler h = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView appLogo = findViewById(R.id.app_logo);

        AlphaAnimation fadeInAnimation = (AlphaAnimation) AnimationUtils.loadAnimation(this, R.anim.logo_fade_in);
        ScaleAnimation scaleAnimation = (ScaleAnimation) AnimationUtils.loadAnimation(this, R.anim.logo_scale);

        AnimationSet animationSet = new AnimationSet(false);
        animationSet.addAnimation(fadeInAnimation);
        animationSet.addAnimation(scaleAnimation);
        appLogo.startAnimation(animationSet);

        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(MainActivity.this, OnboardingActivity.class);
                startActivity(i);
                finish();
            }
        }, 3000);
    }
}