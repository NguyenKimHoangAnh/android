package com.example.android;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Arrays;
import java.util.List;

public class ProductDetailActivity extends AppCompatActivity {

    ImageView imgProduct;
    TextView txtName, txtPrice, txtDescription;
    Button btnAddToCart;
    RecyclerView recyclerRelated;
    BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        imgProduct = findViewById(R.id.imgProductDetail);
        txtName = findViewById(R.id.txtProductName);
        txtPrice = findViewById(R.id.txtProductPrice);
        txtDescription = findViewById(R.id.txtProductDescription);
        btnAddToCart = findViewById(R.id.btnAddToCart);
        recyclerRelated = findViewById(R.id.recyclerRelated);
        bottomNav = findViewById(R.id.bottomNav);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String price = intent.getStringExtra("price");
        String imageUrl = intent.getStringExtra("image");

        txtName.setText(name);
        txtPrice.setText(price);

        Glide.with(this)
                .load(imageUrl)
                .into(imgProduct);

        txtDescription.setText("Chi tiết sản phẩm: " + name +
                "\nThời trang không chỉ đơn thuần là việc chọn lựa trang phục, mà còn là một cuộc hành trình khám phá bản thân. " +
                "Hãy để thời trang trở thành một phần của cuộc sống bạn, giúp bạn tự tin và nổi bật hơn trong đám đông!\n");

        btnAddToCart.setOnClickListener(v -> {
            CartManager.addToCart(new Cart(name, price, imageUrl));
            Toast.makeText(this, name + " đã thêm vào giỏ!", Toast.LENGTH_SHORT).show();
        });

        // Sản phẩm liên quan: (vẫn dùng ảnh nội bộ nếu bạn chưa cập nhật thành URL)
        List<Product> related = Arrays.asList(
                new Product("Balo", "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg", 99000),
                new Product("Áo khoác", "https://fakestoreapi.com/img/71li-ujtlUL._AC_UX679_.jpg", 350000),
                new Product("Áo khoác", "https://fakestoreapi.com/img/51Y5NI-I5jL._AC_UX679_.jpg", 299000)
        );
        RelatedProductAdapter adapter = new RelatedProductAdapter(this, related);
        recyclerRelated.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerRelated.setAdapter(adapter);

        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.menu_home) {
                startActivity(new Intent(this, MainActivity.class));
                return true;
            } else if (id == R.id.menu_cart) {
                startActivity(new Intent(this, CartActivity.class));
                return true;
            }
            return false;
        });
    }
}
