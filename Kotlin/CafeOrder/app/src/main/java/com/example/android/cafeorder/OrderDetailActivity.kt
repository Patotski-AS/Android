package com.example.android.cafeorder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android.cafeorder.databinding.ActivityCreateOrderBinding
import com.example.android.cafeorder.databinding.ActivityOrderDetailBinding

class OrderDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOrderDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_detail)
        binding = ActivityOrderDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textViewOrder.text = intent.getStringExtra("fullOrder").toString()
    }

}