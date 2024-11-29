package com.example.healthconnectapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class StoreFragment extends Fragment {
    private Spinner medicineSpinner;
    private Button minusButton, plusButton, addToCartButton;
    private TextView quantityTextView;
    private int quantity = 1;

    public StoreFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_store, container, false);

        medicineSpinner = view.findViewById(R.id.medicineSpinner);
        minusButton = view.findViewById(R.id.minusButton);
        plusButton = view.findViewById(R.id.plusButton);
        addToCartButton = view.findViewById(R.id.addToCartButton);
        quantityTextView = view.findViewById(R.id.quantityTextView);

        ArrayAdapter<String> medicineAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.medicine_names));
        medicineAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        medicineSpinner.setAdapter(medicineAdapter);

        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantity > 1) {
                    quantity--;
                    updateQuantity();
                }
            }
        });

        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quantity < 10) {
                    quantity++;
                    updateQuantity();
                }
            }
        });

        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedMedicine = medicineSpinner.getSelectedItem().toString();
                CartItem cartItem = new CartItem(selectedMedicine, quantity);
                CartManager.addToCart(cartItem);
                String message = "Added " + quantity + " " + selectedMedicine + " to cart";
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
    private void updateQuantity() {
        quantityTextView.setText(String.valueOf(quantity));
    }
}