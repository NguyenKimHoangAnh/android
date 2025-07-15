package com.example.android;

public class Product {
    private final String name;
    private final String price;
    private final String imageUrl;

    public Product(String name, String imageUrl, double price) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.price = String.format("%.0f đ", price);
    }

    public String getName()       { return name; }
    public String getPrice()      { return price; }
    public String getImageUrl()   { return imageUrl; }
}
