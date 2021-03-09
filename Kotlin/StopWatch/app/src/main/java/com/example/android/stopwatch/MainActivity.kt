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
    private var wasRunning = false

    /**
     * onCreate - вызывается при создании активности.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState!=null){
            second=savedInstanceState.getInt("second")
            isRunning=savedInstanceState.getBoolean("isRunning")
            wasRunning=savedInstanceState.getBoolean("wasRunning")
        }
        runTimer()
    }

    /**
     * onSaveInstanceState - вызывается при уничтожении активности, например при смене конфигурации, или повороте
     * экрана.
     * Сохраняем в Bundle необходимые переменные.
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("second",second)
        outState.putBoolean("isRunning",isRunning)
        outState.putBoolean("wasRunning",wasRunning)
    }
    /**
     * onStop - вызывается, когда активность становится невидимой для пользователя
     */
    override fun onStop() {
        super.onStop()
        wasRunning=isRunning
        isRunning=false
    }

    /**
     * onStart - вызывается, когда активность становится видимой для пользователя
     */
    override fun onStart() {
        super.onStart()
        isRunning=wasRunning
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
