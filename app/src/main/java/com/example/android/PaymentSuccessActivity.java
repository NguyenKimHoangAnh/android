package com.example.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.*;

public class PaymentSuccessActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_success);

        TextView text = findViewById(R.id.txtSuccess);
        text.setText("Thanh toán thành công!\nCảm ơn bạn đã mua hàng.");

        Button btnBack = findViewById(R.id.btnBackHome);
        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(PaymentSuccessActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }
}
