package com.example.healthconnectapp;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class FeedbackFragment extends Fragment {
    private RadioGroup radioGroup;
    private EditText feedbackEditText;
    private Button submitButton;
    public FeedbackFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_feedback, container, false);

        radioGroup = view.findViewById(R.id.radioGroup);
        feedbackEditText = view.findViewById(R.id.feedbackEditText);
        submitButton = view.findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveFeedbackToDatabase();
                feedbackEditText.setText(String.valueOf(""));
                radioGroup.clearCheck();
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                feedbackEditText.setHint(checkedId == R.id.radioExperience ? "Please Enter Your feedback, queries or experience" : "Please enter the bug you entered");
            }
        });
        return  view;
    }
    private void saveFeedbackToDatabase() {
        String feedbackType = radioGroup.getCheckedRadioButtonId() == R.id.radioExperience ? "Experience" : "Bug Report";
        String feedbackText = feedbackEditText.getText().toString();

        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("user_pref", Context.MODE_PRIVATE);
        String userEmail = sharedPreferences.getString("user_email", "");

        DatabaseHelper dbHelper = new DatabaseHelper(requireContext());

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.FEEDBACK_USER_EMAIL, userEmail);
        values.put(DatabaseHelper.FEEDBACK_TYPE, feedbackType);
        values.put(DatabaseHelper.FEEDBACK_TEXT, feedbackText);
        db.insert(DatabaseHelper.FEEDBACKS_TABLE, null, values);
        db.close();

        Toast.makeText(requireContext(), "Feedback submitted", Toast.LENGTH_SHORT).show();
    }
}