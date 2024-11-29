package com.example.healthconnectapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class OrderItemAdapter extends RecyclerView.Adapter<OrderItemAdapter.ViewHolder>{
    private List<Order> orders;

    public OrderItemAdapter(List<Order> orders) {
        this.orders = orders;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Order order = orders.get(position);

        holder.orderItemNameTextView.setText(order.getItemName());
        holder.quantityTextView.setText(String.valueOf(order.getQuantity()));

        // Format date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String formattedDate = sdf.format(order.getOrderDate());
        holder.orderDateTextView.setText("Order Placed on: " + formattedDate);
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView orderItemNameTextView;
        TextView quantityTextView;
        TextView orderDateTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            orderItemNameTextView = itemView.findViewById(R.id.order_item_name);
            quantityTextView = itemView.findViewById(R.id.item_qty);
            orderDateTextView = itemView.findViewById(R.id.order_date);
        }
    }
}
