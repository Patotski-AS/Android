package com.example.android.stopwatch

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.android.stopwatch.databinding.ActivityMainBinding
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var second = 0
    private var isRunning = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        runTimer()
    }

    fun clickButtonStart(view: View) {
        isRunning = true
    }

    fun clickButtonPause(view: View) {
        isRunning = false
    }

    fun clickButtonRest(view: View) {
        isRunning = false
        second = 0
    }

    private fun runTimer() {
        val handler = Handler()
        handler.post(object : Runnable {
            override fun run() {
                val hours: Int = second / 3600
                val minutes: Int = second % 3600 / 60
                val secs: Int = second % 60
                val time = java.lang.String.format(
                    Locale.getDefault(),
                    "%d:%02d:%02d",
                    hours,
                    minutes,
                    secs
                )
                binding.textViewTimer.text = time
                if (isRunning)
                    second++
                handler.postDelayed(this, 1000)
            }
        })
    }
}
