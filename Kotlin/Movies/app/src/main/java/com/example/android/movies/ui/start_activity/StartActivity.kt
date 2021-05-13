package com.example.android.movies.ui.start_activity


import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.android.movies.R
import com.example.android.movies.R.mipmap.ic_launcher_foreground
import com.example.android.movies.databinding.ActivityMainBinding
import com.example.android.movies.test.Test

class StartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var test: Test

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        test = Test(this,binding.imageView)

        binding.apply {
            test = test
        }
    }

    fun addPicture() {
        binding.imageView.setOnClickListener {
            binding.imageView.setImageResource(ic_launcher_foreground)
        }
    }
}


