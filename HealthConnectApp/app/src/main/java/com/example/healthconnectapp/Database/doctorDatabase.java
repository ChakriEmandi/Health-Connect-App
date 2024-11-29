package com.example.healthconnectapp.Database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.example.healthconnectapp.Model.doctor;
import com.example.healthconnectapp.SearchFragment;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class doctorDatabase extends SQLiteAssetHelper{
    private static final String DB_NAME = "doctors.db";
    private static final int DB_VERSION = 1;
    public doctorDatabase(SearchFragment context) {
        super(context.getContext(), DB_NAME, null, DB_VERSION);
    }

    public List<doctor> getDoctors () {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String[] sqlSelect = {"Id", "Name","Gender", "Specialization","Email"};
        String tableName = "doctor_table";

        qb.setTables(tableName);
        Cursor cursor = qb.query(db,sqlSelect,null,null,null,null,null);
        List<doctor> result = new ArrayList<>();

        if(cursor.moveToFirst()) {
            do {
                doctor doc = new doctor();
                int columnId = cursor.getColumnIndex("Id");
                int columnName = cursor.getColumnIndex("Name");
                int columnGender = cursor.getColumnIndex("Gender");
                int columnSpecialization = cursor.getColumnIndex("Specialization");
                int columnEmail = cursor.getColumnIndex("Email");
                doc.setId(cursor.getInt(columnId));
                doc.setName(cursor.getString(columnName));
                doc.setGender(cursor.getString(columnGender));
                doc.setSpecialization(cursor.getString(columnSpecialization));
                doc.setEmail(cursor.getString(columnEmail));

                result.add(doc);
            }
            while (cursor.moveToNext());
        }
        return result;
    }

    public List<String> getNames() {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String[] sqlSelect = {"Name"};
        String tableName = "doctor_table";

        qb.setTables(tableName);
        Cursor cursor = qb.query(db,sqlSelect,null,null,null,null,null);
        List<String> result = new ArrayList<>();

        if(cursor.moveToFirst()) {
            do {
                int columnName = cursor.getColumnIndex("Name");
                result.add(cursor.getString(columnName));
            }
            while (cursor.moveToNext());
        }
        return result;
    }

    public List<doctor> getDoctorByName(String name) {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String[] sqlSelect = {"Id", "Name","Gender", "Specialization","Email"};
        String tableName = "doctor_table";

        qb.setTables(tableName);
        Cursor cursor = qb.query(db,sqlSelect,"Name LIKE ?",new String[] {"%" + name + "%"},null,null,null);
        List<doctor> result = new ArrayList<>();

        if(cursor.moveToFirst()) {
            do {
                doctor doc = new doctor();
                int columnId = cursor.getColumnIndex("Id");
                int columnName = cursor.getColumnIndex("Name");
                int columnGender = cursor.getColumnIndex("Gender");
                int columnSpecialization = cursor.getColumnIndex("Specialization");
                int columnEmail = cursor.getColumnIndex("Email");
                doc.setId(cursor.getInt(columnId));
                doc.setName(cursor.getString(columnName));
                doc.setGender(cursor.getString(columnGender));
                doc.setSpecialization(cursor.getString(columnSpecialization));
                doc.setEmail(cursor.getString(columnEmail));

                result.add(doc);
            }
            while (cursor.moveToNext());
        }
        return result;
    }
}
