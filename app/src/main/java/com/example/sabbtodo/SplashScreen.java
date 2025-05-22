package com.example.sabbtodo;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
         new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               /* Intent i = new Intent(SplashScreen.this,notifyService.class);
                *//*startForegroundService(i);
                *//*
              */
                startActivity(new Intent(SplashScreen.this, MainActivity.class));
                finish();
            }
        },3000);
    }
}