package com.harbour.gpa_cal;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;


public class GuideActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        Button mBtnRead = findViewById(R.id.Guide_read_btn);
        final CheckBox checkBox = findViewById(R.id.checkBox_guide);
        mBtnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkBox.isChecked()){
                    SharedPreferences sharedPreferences = getSharedPreferences("config",MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("is_user_guide_showed",true);
                    editor.apply();
                }
                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                v.vibrate(8);
                startActivity(new Intent(GuideActivity.this,MainActivity.class));
            }
        });
    }
}