package com.example.android.messenger

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android.messenger.databinding.ActivityReceivedMessageBinding

class ReceivedMessageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReceivedMessageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_received_message)

        val intent  = intent
        val msg = intent.getStringExtra("msg")

        binding = ActivityReceivedMessageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textViewReceivedMessage.text = msg
    }
}