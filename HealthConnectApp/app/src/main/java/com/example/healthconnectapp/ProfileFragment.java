package com.example.healthconnectapp;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class ProfileFragment extends Fragment {
    EditText name, dob, bloodGrp, weight, height, bmi;
    RadioGroup gender;
    RadioButton male, female, other;
    Button saveBtn;
    DatabaseHelper databaseHelper;
    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_profile, container, false);
        name = view.findViewById(R.id.name);
        dob = view.findViewById(R.id.DOB);
        gender = view.findViewById(R.id.gender);
        male = view.findViewById(R.id.male);
        female = view.findViewById(R.id.female);
        other = view.findViewById(R.id.other);
        bloodGrp = view.findViewById(R.id.bloodGrp);
        weight = view.findViewById(R.id.weight);
        height = view.findViewById(R.id.height);
        bmi = view.findViewById(R.id.BMI);
        saveBtn = view.findViewById(R.id.saveBtn);

        databaseHelper = new DatabaseHelper(getActivity());

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user_pref", getActivity().MODE_PRIVATE);
        String userEmail = sharedPreferences.getString("user_email", "");

        bmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double weightInKg = Double.parseDouble(weight.getText().toString());
                double heightInCm = Double.parseDouble(height.getText().toString());
                double bmiValue = calculateBMI(weightInKg,heightInCm);
                bmi.setText(String.format("%.2f", bmiValue));
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(name.getText().toString().equals(""))) {
                    saveProfileData(userEmail);
                    Toast.makeText(getActivity(), "Profile data saved successfully!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Snackbar snackbar = Snackbar.make(view.findViewById(android.R.id.content), "Please fill the profile!", Snackbar.LENGTH_SHORT);
                    snackbar.setAction("OK", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            snackbar.dismiss();
                        }
                    });
                    snackbar.show();
                }
            }
        });
        return view;
    }
    private double calculateBMI(double weightKg, double heightCm) {
        double heightM = heightCm / 100.0;
        return weightKg / (heightM * heightM);
    }
    private void saveProfileData(String userEmail) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.PROFILE_USER_EMAIL, userEmail);
        values.put(DatabaseHelper.PROFILE_NAME, name.getText().toString());
        values.put(DatabaseHelper.PROFILE_DOB, dob.getText().toString());
        int selectedGenderId = gender.getCheckedRadioButtonId();
        if (selectedGenderId == R.id.male) {
            values.put(DatabaseHelper.PROFILE_GENDER, "Male");
        } else if (selectedGenderId == R.id.female) {
            values.put(DatabaseHelper.PROFILE_GENDER, "Female");
        } else {
            values.put(DatabaseHelper.PROFILE_GENDER, "Other");
        }
        values.put(DatabaseHelper.PROFILE_BLOOD_GRP, bloodGrp.getText().toString());
        values.put(DatabaseHelper.PROFILE_WEIGHT, weight.getText().toString());
        values.put(DatabaseHelper.PROFILE_HEIGHT, height.getText().toString());
        double bmiValue = calculateBMI(Double.parseDouble(weight.getText().toString()), Double.parseDouble(height.getText().toString()));
        values.put(DatabaseHelper.PROFILE_BMI, bmiValue);
        db.insert(DatabaseHelper.PROFILE_TABLE, null, values);
        db.close();
    }
}