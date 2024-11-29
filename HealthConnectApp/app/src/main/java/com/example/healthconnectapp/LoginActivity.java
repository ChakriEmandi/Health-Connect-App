package com.example.healthconnectapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class LoginActivity extends AppCompatActivity {
    private EditText Email, Password;
    private Button loginBtn;
    private TextView signUpText;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Email = findViewById(R.id.email);
        Password = findViewById(R.id.password);
        loginBtn = findViewById(R.id.loginButton);
        signUpText = findViewById(R.id.signUpText);

        databaseHelper = new DatabaseHelper(this);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = Email.getText().toString();
                String password = Password.getText().toString();
                if (email.isEmpty() || password.isEmpty()) {
                    Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Please fill in Email ID and Password!", Snackbar.LENGTH_SHORT);
                    snackbar.setAction("OK", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            snackbar.dismiss();
                        }
                    });
                    snackbar.show();
                }
                else {
                    if (validateUser(email, password)) {
                        storeUserSession(email,password);
                        navigateToHomePage();
                        Toast.makeText(LoginActivity.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Incorrect Email ID or Password!", Snackbar.LENGTH_SHORT);
                        snackbar.setAction("OK", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                snackbar.dismiss();
                            }
                        });
                        snackbar.show();
                    }
                }
            }
        });
        signUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
    private boolean validateUser(String email, String password) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        String[] columns = {DatabaseHelper.USER_ID};
        String selection = DatabaseHelper.USER_EMAIL + "=? AND " + DatabaseHelper.USER_PASSWORD + "=?";
        String[] selectionArgs = {email, password};
        Cursor cursor = db.query(DatabaseHelper.USERS_TABLE, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        return count > 0;
    }
    private void navigateToHomePage() {
        Intent intent = new Intent(LoginActivity.this, HomePageActivity.class);
        startActivity(intent);
        finish();
    }
    private void storeUserSession(String email, String password) {
        SharedPreferences sharedPreferences = getSharedPreferences("user_pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("is_logged_in", true);
        editor.putString("user_email", email);
        editor.putString("user_password", password);
        editor.apply();
    }
}