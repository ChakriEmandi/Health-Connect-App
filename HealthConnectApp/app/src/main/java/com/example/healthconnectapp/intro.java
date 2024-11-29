package com.example.healthconnectapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class intro extends AppCompatActivity {
    private static final long INTRO_TIME = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedPreferences = getSharedPreferences("user_pref", MODE_PRIVATE);
                boolean isLoggedIn = sharedPreferences.getBoolean("is_logged_in", false);
                String userEmail = sharedPreferences.getString("user_email", "");
                String userPassword = sharedPreferences.getString("user_password", "");

                if (isLoggedIn) {
                    navigateToHomePage();
                } else {
                    navigateToLoginPage();
                }
            }
        }, INTRO_TIME);
    }
    private void navigateToLoginPage() {
        Intent intent = new Intent(intro.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
    private void navigateToHomePage() {
        Intent intent = new Intent(intro.this, HomePageActivity.class);
        startActivity(intent);
        finish();
    }
}