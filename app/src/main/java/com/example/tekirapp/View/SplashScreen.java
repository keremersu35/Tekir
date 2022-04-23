package com.example.tekirapp.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.tekirapp.MainActivity;
import com.example.tekirapp.R;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        LottieAnimationView lottieAnimationView;    //splash screen
        ImageView tekirPng;

        lottieAnimationView = findViewById(R.id.lottie);
        tekirPng = findViewById(R.id.tekirPng);

        lottieAnimationView.animate().translationY(-1600).setDuration(750).setStartDelay(1400);
        lottieAnimationView.setSpeed((float) 1.25);

        tekirPng.animate().translationY(+1600).setDuration(500).setStartDelay(1400);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreen.this, MainActivity.class));
                finish();
            }
        }, 2100);

    }
}