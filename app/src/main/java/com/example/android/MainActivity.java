package com.example.android;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewProducts;
    private ProductAdapter productAdapter;
    private List<Product> productList;
    private BottomNavigationView bottomNav;
    private ViewPager2 viewPagerSlider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initSlider();
        initData();
        initBottomNav();
    }

    private void initViews() {
        recyclerViewProducts = findViewById(R.id.recyclerViewProducts);
        recyclerViewProducts.setLayoutManager(new LinearLayoutManager(this));
        viewPagerSlider = findViewById(R.id.viewPagerSlider);
    }

    private void initSlider() {
        List<Integer> bannerList = Arrays.asList(
                R.drawable.banner1,
                R.drawable.banner2,
                R.drawable.banner3
        );
        SliderAdapter sliderAdapter = new SliderAdapter(this, bannerList);
        viewPagerSlider.setAdapter(sliderAdapter);
    }

    private void initData() {
        productList = new ArrayList<>();
        productList.add(new Product("Áo thun", "150.000 đ", R.drawable.shirt));
        productList.add(new Product("Quần jean", "250.000 đ", R.drawable.jeans));
        productList.add(new Product("Giày sneaker", "499.000 đ", R.drawable.shoes));

        productAdapter = new ProductAdapter(this, productList);
        recyclerViewProducts.setAdapter(productAdapter);
    }

    private void initBottomNav() {
        bottomNav = findViewById(R.id.bottomNav);

        bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull android.view.MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.menu_home) {
                    Toast.makeText(MainActivity.this, "Trang chủ", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (id == R.id.menu_all_products) {
                    Toast.makeText(MainActivity.this, "Tất cả sản phẩm", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (id == R.id.menu_cart) {
                    startActivity(new Intent(MainActivity.this, CartActivity.class));
                    return true;
                } else if (id == R.id.menu_login) {
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    return true;
                }
                return false;
            }
        });
    }
}
