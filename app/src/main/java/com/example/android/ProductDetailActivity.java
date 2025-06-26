package com.example.android;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
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
        int imageRes = intent.getIntExtra("image", R.drawable.shirt);

        txtName.setText(name);
        txtPrice.setText(price);
        imgProduct.setImageResource(imageRes);
        txtDescription.setText("Chi tiết sản phẩm: " + name+"Thời trang không chỉ đơn thuần là việc chọn lựa trang phục, mà còn là một cuộc hành trình khám phá bản thân. Hãy để thời trang trở thành một phần của cuộc sống bạn, giúp bạn tự tin và nổi bật hơn trong đám đông!\n" +
                "\n");

        btnAddToCart.setOnClickListener(v -> {
            CartManager.addToCart(new Cart(name, price, imageRes));
            Toast.makeText(this, name + " đã thêm vào giỏ!", Toast.LENGTH_SHORT).show();
        });

        // Sản phẩm liên quan (demo)
        List<Product> related = Arrays.asList(
                new Product("Mũ len", "99.000đ", R.drawable.hat),
                new Product("Balo", "350.000đ", R.drawable.bag),
                new Product("Áo khoác", "299.000đ", R.drawable.shirt)
        );
        RelatedProductAdapter adapter = new RelatedProductAdapter(this, related);
        recyclerRelated.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerRelated.setAdapter(adapter);

        // Thanh điều hướng
        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.menu_home) {
                startActivity(new Intent(this, MainActivity.class));
                return true;
            } else if (id == R.id.menu_cart) {
                startActivity(new Intent(this, CartActivity.class));
                return true;
            } else if (id == R.id.menu_login) {
                startActivity(new Intent(this, LoginActivity.class));
                return true;
            }
            return false;
        });
    }
}
