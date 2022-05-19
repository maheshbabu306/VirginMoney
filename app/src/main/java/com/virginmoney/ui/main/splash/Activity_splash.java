package com.virginmoney.ui.main.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.virginmoney.MainActivity;
import com.virginmoney.R;

public class Activity_splash extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // This method will be executed once the timer is over
                Intent i = new Intent(Activity_splash.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }, 3000);
    }
}
