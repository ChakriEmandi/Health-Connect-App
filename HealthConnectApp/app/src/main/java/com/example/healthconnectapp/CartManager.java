package com.example.healthconnectapp;

import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private static List<CartItem> cartItems = new ArrayList<>();

    public static void addToCart(CartItem item) {
        cartItems.add(item);
    }

    public static List<CartItem> getCartItems() {
        return cartItems;
    }

    public static void clearCart() {
        cartItems.clear();
    }
}
