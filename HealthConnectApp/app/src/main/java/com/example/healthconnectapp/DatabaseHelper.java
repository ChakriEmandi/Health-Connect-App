package com.example.healthconnectapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "healthConnect.db";
    public static final int DATABASE_VERSION = 1;
    public static final String USERS_TABLE = "users";
    public static final String USER_ID = "id";
    public static final String USER_EMAIL = "email";
    public static final String USER_PASSWORD = "password";
    public static final String FEEDBACKS_TABLE = "feedbacks";
    public static final String FEEDBACK_ID = "id";
    public static final String FEEDBACK_USER_EMAIL = "user_email";
    public static final String FEEDBACK_TYPE = "feedback_type";
    public static final String FEEDBACK_TEXT = "feedback_text";
    public static final String PROFILE_TABLE = "profile";
    public static final String PROFILE_ID = "id";
    public static final String PROFILE_USER_EMAIL = "user_email";
    public static final String PROFILE_NAME = "name";
    public static final String PROFILE_DOB = "dob";
    public static final String PROFILE_GENDER = "gender";
    public static final String PROFILE_BLOOD_GRP = "blood_group";
    public static final String PROFILE_WEIGHT = "weight";
    public static final String PROFILE_HEIGHT = "height";
    public static final String PROFILE_BMI = "bmi";


    private static final String TABLE_CREATE = "CREATE TABLE " + USERS_TABLE + " (" + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + USER_EMAIL + " TEXT, " + USER_PASSWORD + " TEXT);";

    private static final String TABLE_CREATE_FEEDBACKS = "CREATE TABLE " + FEEDBACKS_TABLE + " (" + FEEDBACK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FEEDBACK_USER_EMAIL + " TEXT, " + FEEDBACK_TYPE + " TEXT, " + FEEDBACK_TEXT + " TEXT);";

    private static final String TABLE_CREATE_PROFILE = "CREATE TABLE " + PROFILE_TABLE + " (" +
            PROFILE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            PROFILE_USER_EMAIL + " TEXT, " +
            PROFILE_NAME + " TEXT, " +
            PROFILE_DOB + " TEXT, " +
            PROFILE_GENDER + " TEXT, " +
            PROFILE_BLOOD_GRP + " TEXT, " +
            PROFILE_WEIGHT + " TEXT, " +
            PROFILE_HEIGHT + " TEXT, " +
            PROFILE_BMI + " REAL);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(TABLE_CREATE);
            db.execSQL(TABLE_CREATE_FEEDBACKS);
            db.execSQL(TABLE_CREATE_PROFILE);
            Log.d("DatabaseHelper", "Tables created successfully");
        } catch (SQLException e) {
            Log.e("DatabaseHelper", "Error creating tables: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL("DROP TABLE IF EXISTS " + USERS_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + FEEDBACKS_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + PROFILE_TABLE);
            onCreate(db);
        } catch (SQLException e) {
            Log.e("DatabaseHelper", "Error upgrading tables: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
