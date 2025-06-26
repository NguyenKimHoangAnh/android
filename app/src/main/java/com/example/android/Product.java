package com.example.android;

/**
 * Model dữ liệu sản phẩm.
 * Ta để giá dạng String cho đơn giản ("150.000 đ").
 */
public class Product {

    private final String name;
    private final String price;
    private final int imageResId;

    public Product(String name, String price, int imageResId) {
        this.name = name;
        this.price = price;
        this.imageResId = imageResId;
    }

    public String getName()        { return name; }
    public String getPrice()       { return price; }
    public int    getImageResId()  { return imageResId; }
}
