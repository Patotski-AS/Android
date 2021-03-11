package com.example.android.toolsshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android.toolsshop.databinding.ActivityDrillDetailBinding

class DrillDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDrillDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drill_detail)

        binding = ActivityDrillDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textViewInfo.text = intent.getStringExtra("info")
        binding.textViewTitle.text = intent.getStringExtra("title")
        binding.imageViewConcreteDrill.setImageResource(intent.getIntExtra("imageResourceId",-1))

    }
}