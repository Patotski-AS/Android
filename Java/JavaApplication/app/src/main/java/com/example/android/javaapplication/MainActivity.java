package com.example.android.javaapplication;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button button_minus;
    private TextView quantity_text_view;
    private Button button_plus;
    private TextView price_text_view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quantity_text_view = findViewById(R.id.quantity_text_view);
        price_text_view = findViewById(R.id.price_text_view);

        button_minus = findViewById(R.id.button_minus);
        button_minus.setOnClickListener(v -> {
            int x = Integer.parseInt(quantity_text_view.getText().toString());
            if (x <= 0)
                quantity_text_view.setText(String.valueOf(x));
            else {
                x--;
                quantity_text_view.setText(String.valueOf(x));
            }
        });

        button_plus = findViewById(R.id.button_plus);
        button_plus.setOnClickListener(v -> {
            int x = Integer.parseInt(quantity_text_view.getText().toString());
            x++;
            quantity_text_view.setText(String.valueOf(x));

        });

    }

    public void click_order_button(View view) {
            int x = Integer.parseInt(quantity_text_view.getText().toString());
            price_text_view.setText(String.valueOf(x*5));
    }
}