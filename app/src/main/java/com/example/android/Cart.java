package com.example.android;

public class Cart {
    private final String name;
    private final String price; // dạng "150000" hoặc "150.000đ"
    private final int imageResId;
    private int quantity;

    public Cart(String name, String price, int imageResId) {
        this.name       = name;
        this.price      = price;
        this.imageResId = imageResId;
        this.quantity   = 1;
    }

    public String getName()      { return name; }
    public String getPrice()     { return price; }
    public int    getImageResId() { return imageResId; }
    public int    getQuantity()  { return quantity; }

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