package com.harbour.gpa_cal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_activity);
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                SharedPreferences sharedPreferences = getSharedPreferences("config", MODE_PRIVATE);
                boolean userGuide = sharedPreferences.getBoolean("is_user_guide_showed", false);
                if (!userGuide) {
                    startActivity(new Intent(WelcomeActivity.this, GuideActivity.class));
                } else {
                    startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                }
            }
        };
        timer.schedule(timerTask,1000);
    }
}