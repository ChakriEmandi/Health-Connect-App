package com.example.healthconnectapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class ContactFragment extends Fragment {
    public ContactFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_contact, container, false);

        TextView contactNumberTextView = view.findViewById(R.id.phone);
        contactNumberTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri phoneNumber = Uri.parse("tel:9876543210");
                Intent dialIntent = new Intent(Intent.ACTION_DIAL, phoneNumber);
                startActivity(dialIntent);
            }
        });

        TextView emailTextView = view.findViewById(R.id.email);
        emailTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse("mailto:support.services@HealthConnect.com"));
                startActivity(emailIntent);
            }
        });

        ImageView twitterIcon = view.findViewById(R.id.twitter);
        ImageView instagramIcon = view.findViewById(R.id.instagram);

        View.OnClickListener socialMediaClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "";
                if(v.getId() == R.id.twitter) {
                    message = "Twitter handle clicked";
                } else if (v.getId() == R.id.instagram) {
                    message = "Instagram handle clicked";
                }

                Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
                snackbar.setAction("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
                snackbar.show();
            }
        };

        twitterIcon.setOnClickListener(socialMediaClickListener);
        instagramIcon.setOnClickListener(socialMediaClickListener);

        return view;
    }
}