package com.example.healthconnectapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

public class RegisterActivity extends AppCompatActivity {
    private EditText Email, Password, ConfirmPassword;
    private Button signUpBtn;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Email = findViewById(R.id.email);
        Password = findViewById(R.id.password);
        ConfirmPassword = findViewById(R.id.confirmPassword);
        signUpBtn = findViewById(R.id.signUpButton);

        databaseHelper = new DatabaseHelper(this);
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = Email.getText().toString();
                String password = Password.getText().toString();
                String confirmPassword = ConfirmPassword.getText().toString();
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
                    if (password.equals(confirmPassword)) {
                        saveUser(email, password);
                        navigateToLoginPage();
                    }
                    else {
                        ConfirmPassword.setError("Passwords do not match");
                    }
                }
            }
        });
    }
    private void saveUser(String email, String password) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.USER_EMAIL, email);
        values.put(DatabaseHelper.USER_PASSWORD, password);
        db.insert(DatabaseHelper.USERS_TABLE, null, values);
    }
    private void navigateToLoginPage() {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}