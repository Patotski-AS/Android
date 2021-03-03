package com.example.android.firstproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlin.random.Random as Random

class SecondActivity : AppCompatActivity() {
    private lateinit var textViewResult: TextView
    private lateinit var textViewInfo: TextView

    companion object {
        const val TOTAL_COUNT = "total_count"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        textViewResult = findViewById(R.id.textViewResult)
        textViewInfo = findViewById(R.id.textViewInfo)

        showRandomCount()
    }

    private fun showRandomCount() {
        val count = intent.getIntExtra(TOTAL_COUNT, 0)
        val random = Random
        var randomInt = 0

        if (count > 0) {
            randomInt = random.nextInt(count + 1)
        }
        textViewResult.text = randomInt.toString()
        textViewInfo.text = getString(R.string.textViewInfo, count)

    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            putString("KeyTextViewResult", textViewResult.text.toString())
        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        textViewResult.text = savedInstanceState.getString("KeyTextViewResult")
    }
}