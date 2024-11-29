package com.example.healthconnectapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class OrdersDatabase extends SQLiteOpenHelper{
    private static final String TAG = "DatabaseHelper";
    private static final String DATABASE_NAME = "orders.db";
    private static final int DATABASE_VERSION = 1;

    public OrdersDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        boolean copyDbFlag = !checkDatabaseExists(context);
        if (copyDbFlag) {
            copyDatabaseFromAssets(context);
        }
    }
    public void onCreate(SQLiteDatabase db) {

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void copyDatabaseFromAssets(Context context) {
        try {
            String dbPath = context.getDatabasePath(DATABASE_NAME).getPath();
            if (!new File(dbPath).exists()) {
                InputStream assetsDbStream = context.getAssets().open("databases/" + DATABASE_NAME);
                OutputStream dbOutputStream = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    dbOutputStream = Files.newOutputStream(Paths.get(dbPath));
                }
                byte[] buffer = new byte[1024];
                int length;
                while ((length = assetsDbStream.read(buffer)) > 0) {
                    assert dbOutputStream != null;
                    dbOutputStream.write(buffer, 0, length);
                }
                assert dbOutputStream != null;
                dbOutputStream.flush();
                dbOutputStream.close();
                assetsDbStream.close();
                Log.d(TAG, "Database copied successfully.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private boolean checkDatabaseExists(Context context) {
        String dbPath = context.getDatabasePath(DATABASE_NAME).getPath();
        return new File(dbPath).exists();
    }
}

