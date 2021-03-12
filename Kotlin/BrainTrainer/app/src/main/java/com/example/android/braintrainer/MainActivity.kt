package com.example.android.braintrainer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.android.braintrainer.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var random = Random
    private var operator: Boolean = false
    private val max = 30
    private val min = 5
    private var rightAnswer: Int = 0
    private lateinit var ask: String
    private val opinions = arrayListOf<TextView>()
    private var count = 0
    private var countRight = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            opinions.add(textViewOpinion0)
            opinions.add(textViewOpinion1)
            opinions.add(textViewOpinion2)
            opinions.add(textViewOpinion3)
        }
        generateAsk()
        generateOpinion()
        binding.textViewShore.text =
            String.format(getString(R.string.tv_shore_text), countRight, count)


    }

    private fun generateAsk() {
        operator = random.nextBoolean()
        val a = random.nextInt(max - min) + min
        val b = random.nextInt(max - min) + min
        rightAnswer = if (operator) a - b else a + b
        ask = if (operator) "$a - $b" else "$a + $b"
        binding.textViewAsk.text = ask
    }

    private fun generateOpinion() {
        opinions[random.nextInt(3)].text = rightAnswer.toString()
        for (i in 0 until opinions.size) {
            var x: Int
            if (opinions[i].text != rightAnswer.toString()) {
                do {
                    x = random.nextInt(max * 2) - (max - min)
                } while (x == rightAnswer)
                opinions[i].text = x.toString()
            }
        }
    }

    fun onClickAnswer(view: View) {
        count++
        val answer = (view as TextView).text.toString().toInt()
        if (answer == rightAnswer) {
            Toast.makeText(this, "Верно", Toast.LENGTH_SHORT).show()
            countRight++
        } else
            Toast.makeText(this, "Не верно", Toast.LENGTH_SHORT).show()

        binding.textViewShore.text =
            String.format(getString(R.string.tv_shore_text), countRight, count)

        generateAsk()
        generateOpinion()

    }


}

