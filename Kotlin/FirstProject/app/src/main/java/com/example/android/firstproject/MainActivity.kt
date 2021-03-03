package com.example.android.firstproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var textViewCount: TextView
    private lateinit var myObserver:MyObserver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewCount = findViewById(R.id.textViewCount)
        
        /**
         * использование MyObserver()
         * в котором логируются состояния активити
         */
        myObserver= MyObserver()
        lifecycle.addObserver(myObserver)
    }

    fun buttonCount(view: View) {
        var count = textViewCount.text.toString().toInt()
        textViewCount.text = (++count).toString()
    }

    fun buttonToast(view: View) {
        val myToast = Toast.makeText(this, "Привет, Натали", Toast.LENGTH_LONG)
        myToast.show()
    }

    fun buttonRandom(view: View) {
        val intentRandom = Intent(this, SecondActivity::class.java)
        val count = textViewCount.text.toString().toInt()
        intentRandom.putExtra(SecondActivity.TOTAL_COUNT,count)
        startActivity(intentRandom)
    }

    /**
     * onSaveInstanceState - сохранение данных
     * Bundle - ключ-значение
     */
    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            putString("KEY",textViewCount.text.toString())
        }
        super.onSaveInstanceState(outState)
    }

    /**
     * onRestoreInstanceState - использование сохранненых ранее данных
     * вызывается при стартеактивити, если ранее был создан Bundle
     * функцей onSaveInstanceState
     * Bundle - ключ-значение
     */
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        textViewCount.text=savedInstanceState.getString("KEY")
    }
}