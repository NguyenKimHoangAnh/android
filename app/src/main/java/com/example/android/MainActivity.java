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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
        initBottomNav();
        loadProducts(); // Gọi hàm tải dữ liệu từ API
    }

    private void initViews() {
        recyclerViewProducts = findViewById(R.id.recyclerViewProducts);
        recyclerViewProducts.setLayoutManager(new LinearLayoutManager(this));
        viewPagerSlider = findViewById(R.id.viewPagerSlider);

        productList = new ArrayList<>();
        productAdapter = new ProductAdapter(this, productList);
        recyclerViewProducts.setAdapter(productAdapter);
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

    private void loadProducts() {
        String url = "https://fakestoreapi.com/products";
        RequestQueue queue = Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                response -> {
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject productObj = response.getJSONObject(i);
                            String title = productObj.getString("title");
                            String image = productObj.getString("image");
                            double price = productObj.getDouble("price");

                            productList.add(new Product(title, image, price));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    productAdapter.notifyDataSetChanged();
                },
                error -> error.printStackTrace());

        queue.add(jsonArrayRequest);
    }

    private void initBottomNav() {
        bottomNav = findViewById(R.id.bottomNav);

        bottomNav.setOnItemSelectedListener(item -> {
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
            }

            return false;
        });
    }
}
