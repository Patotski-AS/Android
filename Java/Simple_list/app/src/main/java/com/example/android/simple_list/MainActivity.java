package com.example.android.simple_list;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView text_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text_list = findViewById(R.id.text_list);

        String[] strings = {"one", "two", "three", "four", "five", "sex", "seven", "eight", "nine", "ten"};

        text_list.setText("");
        for (String x : strings)
            text_list.append(x + "\n");
    }
}