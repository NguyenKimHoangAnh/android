package com.example.android;

public class Cart {
    private final String name;
    private final String price;
    private final String imageUrl;
    private int quantity;

    public Cart(String name, String price, String imageUrl) {
        this.name     = name;
        this.price    = price;
        this.imageUrl = imageUrl;
        this.quantity = 1;
    }

    public String getName()       { return name; }
    public String getPrice()      { return price; }
    public String getImageUrl()   { return imageUrl; }
    public int    getQuantity()   { return quantity; }

    public void increaseQuantity() { quantity++; }
    public void decreaseQuantity() { if (quantity > 1) quantity--; }

    public int getPriceInt() {
        try {
            String clean = price.replace(".", "").replace("đ", "").trim();
            return Integer.parseInt(clean);
        } catch (Exception e) {
            return 0;
        }
    }
}
