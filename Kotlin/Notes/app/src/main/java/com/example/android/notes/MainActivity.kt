package com.example.android.notes

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.notes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    companion object {
        var notes = arrayListOf<Note>()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (notes.isEmpty()) {
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

        val adapter = NotesAdapter(notes)

        /**
         * @param LinearLayoutManager(this) Создает вертикальный LinearLayout
         * @param LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false) Создает горизонтальный LinearLayout
         * @param GridLayoutManager(this,3(количество столбцов)) Создает  LinearLayout сеткой
         *
         */
        binding.recyclerViewNotes.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewNotes.adapter = adapter

    }

    fun onClickAddNote(view: View) {
        intent = Intent(this, NoteAddActivity::class.java)
        startActivity(intent)
    }
}