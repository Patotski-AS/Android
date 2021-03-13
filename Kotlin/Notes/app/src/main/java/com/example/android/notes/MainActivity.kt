package com.example.android.notes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.android.notes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private  var notes = arrayListOf<Note>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(notes) {
           add(Note("Парикмахер", "Сделать прическу", "Понедельник", 2))
           add(Note("Баскетбол", "Игра со школьной командой", "Вторник", 3))
           add(Note("Магазин", "Купить новые джинсы", "Понедельник", 3))
           add(Note("Стоматолог", "Вылечить зубы", "Понедельник", 2))
           add(Note("Парикмахер", "Сделать прическу к выпускному", "Среда", 1))
           add(Note("Баскетбол", "Игра со школьной командой", "Вторник", 3))
           add(Note("Магазин", "Купить новые джинсы", "Понедельник", 3))
        }

    }
}