package com.example.android.firstkonlin

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.android.firstkonlin.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
private lateinit var binding:ActivityMainBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         * Вызовите статический inflate()метод, включенный в сгенерированный класс привязки.
         * Это создает экземпляр класса привязки для использования действием.
         */
        binding = ActivityMainBinding.inflate(layoutInflater)
        /**
         * Получите ссылку на корневое представление,
         * вызвав getRoot()метод или используя синтаксис свойств Kotlin .
         */
        val view = binding.root
        /**
         * Передайте корневое представление в,
         * setContentView() чтобы сделать его активным представлением на экране.
         */
        setContentView(view)

        binding.buttonSave.setOnClickListener {
            binding.textView.text = "Привет, ${binding.etPersonName.text.toString().trim()}"
        }
    }
}