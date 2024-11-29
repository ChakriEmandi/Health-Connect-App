package com.example.healthconnectapp;

import java.util.ArrayList;
import java.util.List;

public class OrderManager {
    private static OrderManager instance;
    private List<Order> ordersList = new ArrayList<>();

    private OrderManager() {

    }

    public static OrderManager getInstance() {
        if (instance == null) {
            instance = new OrderManager();
        }
        return instance;
    }

    public void addOrder(Order order) {
        ordersList.add(order);
    }

    public List<Order> getOrders() {
        return ordersList;
    }

    public void clearOrders() {
        ordersList.clear();
    }
}

