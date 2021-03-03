package com.example.android.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView=findViewById(R.id.textView)
    }

    fun toastMy (view:View){
        val myToast = Toast.makeText(this,"Привет",Toast.LENGTH_SHORT)
        myToast.show()
    }

    fun myCount (view:View){
        var count = textView.text.toString().toInt()
        count++
        textView.text=count.toString()
    }

    fun randomMy(view: View){
        val intentRandom = Intent(this,SecondActivity::class.java)
        startActivity(intentRandom)
    }
}