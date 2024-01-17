package com.example.locatorappforhealthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;

public class TransitionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition);

        LottieAnimationView animationView = findViewById(R.id.animation_view);
        animationView.setAnimation("map.json");
        animationView.setRepeatCount(LottieDrawable.INFINITE);
        animationView.playAnimation();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(TransitionActivity.this, MapActivity.class);
                intent.putExtra("doctorType", getIntent().getStringExtra("doctorType"));
                startActivity(intent);
                finish();
            }
        }, 4000);
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}
