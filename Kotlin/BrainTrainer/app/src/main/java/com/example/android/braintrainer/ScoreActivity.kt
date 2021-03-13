package com.example.android.braintrainer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import com.example.android.braintrainer.databinding.ActivityScoreBinding

class ScoreActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScoreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        binding = ActivityScoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val preferences =  PreferenceManager.getDefaultSharedPreferences(this)
        val max = preferences.getInt("max",0)

        binding.textView.text = String.format("Ваш результат:  %s \nМаксимальный результат: %s ",intent.getStringExtra("score"),max)
    }

    fun clickButtonNewGame(view: View) {
        intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }
}