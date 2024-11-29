package com.example.healthconnectapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class HomeFragment extends Fragment {
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        TextView welcome = view.findViewById(R.id.welcomeMsg);
        Button searchBtn = view.findViewById(R.id.searchBtn);
        Button storeBtn = view.findViewById(R.id.storeBtn);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSearchButtonClick(searchBtn);
            }
        });
        storeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStoreButtonClick(storeBtn);
            }
        });

        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("user_pref", Context.MODE_PRIVATE);
        String userEmail = sharedPreferences.getString("user_email", "");

        String welcomeMessage = "Welcome, " + userEmail;
        welcome.setText(welcomeMessage);
        return view;
    }
    public void onSearchButtonClick(View view) {
        Fragment searchFragment = new SearchFragment();
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, searchFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void onStoreButtonClick(View view) {
        Fragment storeFragment = new StoreFragment();
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, storeFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}