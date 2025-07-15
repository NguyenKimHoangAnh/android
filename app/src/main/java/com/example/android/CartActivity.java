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
    Button btnCheckout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerCart   = findViewById(R.id.recyclerCart);
        txtTotalPrice  = findViewById(R.id.txtTotalPrice);
        Button btnClear= findViewById(R.id.btnClearCart);
        btnCheckout    = findViewById(R.id.btnCheckout);
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

        btnCheckout.setOnClickListener(v -> {
            if (CartManager.getCartItems().isEmpty()) {
                Toast.makeText(this, "Giỏ hàng đang trống!", Toast.LENGTH_SHORT).show();
                return;
            }

            CartManager.clearCart();
            adapter.notifyDataSetChanged();
            updateTotal();

            Intent intent = new Intent(CartActivity.this, PaymentSuccessActivity.class);
            startActivity(intent);
            finish();
        });

        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.menu_home) {
                startActivity(new Intent(this, MainActivity.class));
                return true;
            } else if (id == R.id.menu_cart) {
                Toast.makeText(this, "Bạn đang ở giỏ hàng", Toast.LENGTH_SHORT).show();
                return true;
            }
            return false;
        });
    }

    private void updateTotal() {
        txtTotalPrice.setText("Tổng: " + CartManager.getTotalPrice() + "đ");
    }
}
