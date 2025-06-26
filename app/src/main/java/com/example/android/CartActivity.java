package com.example.android;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.*;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.*;

public class CartActivity extends AppCompatActivity {
    RecyclerView recyclerCart;
    TextView txtTotalPrice;
    CartAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerCart   = findViewById(R.id.recyclerCart);
        txtTotalPrice  = findViewById(R.id.txtTotalPrice);
        Button btnClear= findViewById(R.id.btnClearCart);
        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);

        adapter = new CartAdapter(this, CartManager.getCartItems(), this::updateTotal);
        recyclerCart.setLayoutManager(new LinearLayoutManager(this));
        recyclerCart.setAdapter(adapter);

        updateTotal();

        btnClear.setOnClickListener(v -> {
            CartManager.clearCart();
            adapter.notifyDataSetChanged();
            updateTotal();
        });

        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.menu_home) {
                startActivity(new Intent(this, MainActivity.class));
                return true;
            } else if (id == R.id.menu_cart) {
                Toast.makeText(this, "Bạn đang ở giỏ hàng", Toast.LENGTH_SHORT).show();
                return true;
            } else if (id == R.id.menu_login) {
                startActivity(new Intent(this, LoginActivity.class));
                return true;
            }
            return false;
        });
    }

    private void updateTotal() {
        txtTotalPrice.setText("Tổng: " + CartManager.getTotalPrice() + "đ");
    }
}