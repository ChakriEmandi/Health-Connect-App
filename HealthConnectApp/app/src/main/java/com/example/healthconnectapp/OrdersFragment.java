package com.example.healthconnectapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class OrdersFragment extends Fragment {
    private RecyclerView orderRecyclerView;
    private OrderItemAdapter orderAdapter;
    private List<Order> ordersList = new ArrayList<>();
    private OrdersDatabase dbHelper;
    Button homeBtn;
    public OrdersFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_orders, container, false);
        homeBtn = view.findViewById(R.id.back_to_home);
        dbHelper = new OrdersDatabase(requireContext());

        orderRecyclerView = view.findViewById(R.id.orderRecyclerView);
        orderRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        orderAdapter = new OrderItemAdapter(ordersList);
        orderRecyclerView.setAdapter(orderAdapter);
        loadOrdersFromManager();
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToHome(view);
            }
        });
        return view;
    }
    private void loadOrdersFromManager() {
        List<Order> orders = OrderManager.getInstance().getOrders();
        ordersList.clear();
        ordersList.addAll(orders);
        orderAdapter.notifyDataSetChanged();
    }
    public void navigateToHome(View view) {
        Fragment homeFragment = new HomeFragment();
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, homeFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}