package com.example.android;

import java.util.*;

public class CartManager {
    private static final List<Cart> cartList = new ArrayList<>();

    public static void addToCart(Cart item) {
        for (Cart c : cartList) {
            if (c.getName().equals(item.getName())) {
                c.increaseQuantity();
                return;
            }
        }
        cartList.add(item);
    }

    public static List<Cart> getCartItems() {
        return cartList;
    }

    public static void removeItem(int position) {
        if (position >= 0 && position < cartList.size()) {
            cartList.remove(position);
        }
    }

    public static void clearCart() {
        cartList.clear();
    }

    public static void increaseQty(int position) {
        if (position >= 0 && position < cartList.size()) cartList.get(position).increaseQuantity();
    }
    public static void decreaseQty(int position) {
        if (position >= 0 && position < cartList.size()) cartList.get(position).decreaseQuantity();
    }

    public static int getTotalPrice() {
        int total = 0;
        for (Cart c : cartList) {
            total += c.getPriceInt() * c.getQuantity();
        }
        return total;
    }
}
