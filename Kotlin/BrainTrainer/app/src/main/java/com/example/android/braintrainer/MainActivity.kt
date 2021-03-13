package com.example.android.braintrainer

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.preference.PreferenceManager
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
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
    private var gameOver = false

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
        nextPlay()

        gameOver = false

        val timer = object : CountDownTimer(6000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                binding.textViewTimer.text = getTime(millisUntilFinished)
            }

            override fun onFinish() {
                gameOver=true

                val preferences =  PreferenceManager.getDefaultSharedPreferences(applicationContext)
                val max = preferences.getInt("max",0)
                if (countRight>=max){
                    preferences.edit().putInt("max",countRight).apply()
                }


                val intent = Intent(this@MainActivity, ScoreActivity::class.java)
                intent.putExtra("score", binding.textViewScore.text.toString())
                startActivity(intent)
            }

        }
        timer.start()
    }

    private fun getTime(value: Long): String {
        val allSecond = value / 1000
        val minutes = allSecond / 60
        val seconds = allSecond % 60
        return String.format("%02d : %02d", minutes, seconds)
    }


    private fun nextPlay() {
        count++
        generateAsk()
        generateOpinion()
        binding.textViewScore.text =
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
        if (!gameOver) {
            val answer = (view as TextView).text.toString().toInt()
            if (answer == rightAnswer) {
//                Toast.makeText(this, "Верно", Toast.LENGTH_SHORT).show()
                countRight++
            }
//            else
//                Toast.makeText(this, "Не верно", Toast.LENGTH_SHORT).show()

            nextPlay()
        }
    }


}

