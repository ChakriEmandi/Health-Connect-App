package com.example.healthconnectapp;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AppointmentFragment extends Fragment {

    public AppointmentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_appointment, container, false);
        Spinner doctorSpinner = view.findViewById(R.id.doctorSpinner);
        EditText dateEditText = view.findViewById(R.id.dateEditText);
        Spinner timeSpinner = view.findViewById(R.id.timeSpinner);
        Button submitButton = view.findViewById(R.id.submitButton);

        ArrayAdapter<String> doctorAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.doctor_names));
        doctorSpinner.setAdapter(doctorAdapter);

        ArrayAdapter<String> timeAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.time_slots));
        timeSpinner.setAdapter(timeAdapter);

        dateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(dateEditText);
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedDoctor = doctorSpinner.getSelectedItem().toString();
                String selectedDate = dateEditText.getText().toString();
                String selectedTime = timeSpinner.getSelectedItem().toString();

                doctorSpinner.setAdapter(null);
                dateEditText.setText("");
                timeSpinner.setAdapter(null);

                String message = "Appointment booked with " + selectedDoctor + " on " + selectedDate + " at " + selectedTime;
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
                navigateToHome(view);
            }
        });

        return view;
    }
    private void showDatePickerDialog(final EditText dateEditText) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                requireContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month++;
                        String selectedDate = String.format(Locale.getDefault(), "%02d/%02d/%04d", month, dayOfMonth, year);
                        dateEditText.setText(selectedDate);
                    }
                },
                year, month, day
        );
        datePickerDialog.show();
    }
    public void navigateToHome(View view) {
        Fragment homeFragment = new HomeFragment();
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, homeFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}