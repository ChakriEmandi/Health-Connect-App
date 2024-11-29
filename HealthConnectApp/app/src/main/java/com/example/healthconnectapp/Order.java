package com.example.healthconnectapp;
import java.util.Date;

public class Order {
    private String itemName;
    private int quantity;
    private Date orderDate;

    public Order(String itemName, int quantity, Date orderDate) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.orderDate = orderDate;
    }


    public String getItemName() {
        return itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public Date getOrderDate() {
        return orderDate;
    }
}
