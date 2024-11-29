package com.example.healthconnectapp;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.Date;
import java.util.List;

public class CartFragment extends Fragment {
    private RecyclerView cartRecyclerView;
    private Button placeOrderButton;
    private OrdersDatabase dbHelper;
    public CartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_cart, container, false);

        dbHelper = new OrdersDatabase(requireContext());
        cartRecyclerView = view.findViewById(R.id.cartRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
        cartRecyclerView.setLayoutManager(layoutManager);

        List<CartItem> cartItems = CartManager.getCartItems();
        CartItemAdapter adapter = new CartItemAdapter(cartItems);
        cartRecyclerView.setAdapter(adapter);

        placeOrderButton = view.findViewById(R.id.place_order);
        placeOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertCartItemsToDatabase();
                List<CartItem> cartItems = CartManager.getCartItems();
                Date currentDate = new Date();
                for (CartItem cartItem : cartItems) {
                    OrderManager.getInstance().addOrder(new Order(cartItem.getItemName(), cartItem.getQuantity(), currentDate));
                }
                CartManager.clearCart();
                adapter.notifyDataSetChanged();
                Toast.makeText(requireContext(), "Order placed successfully", Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }
    private void insertCartItemsToDatabase() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        List<CartItem> cartItems = CartManager.getCartItems();

        for (CartItem cartItem : cartItems) {
            ContentValues values = new ContentValues();
            values.put("Item_Name", cartItem.getItemName());
            values.put("Quantity", cartItem.getQuantity());
            values.put("Date", System.currentTimeMillis());
            db.insert("my_orders", null, values);
        }

        db.close();
    }
}