package com.example.android.notes

import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.core.view.get
import androidx.lifecycle.ViewModelProvider
import com.example.android.notes.databinding.ActivityNoteAddBinding

class NoteAddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNoteAddBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_add)

        binding = ActivityNoteAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this,MainFactory(this.application)).get(MainViewModel::class.java)

    }

    /**
     * Добавление в БД новой заметки
     */
    fun onClickButtonSave(view: View) {
        val contentValues = ContentValues()

        val title = binding.editTextTextTitle.text.toString().trim()
        val description = binding.editTextDescription.text.toString().trim()
        val dayOfWeek = binding.spinner?.selectedItemPosition
        val priority = checkButton()

        if (isFilled(title, description)) {
            val note = Note(title,description, dayOfWeek!!,priority)
            viewModel.insertNote(note)
            intent = Intent(this,MainActivity::class.java)
            startActivity(intent)

        } else {
            Toast.makeText(this, getString(R.string.warning_fill_fileds), Toast.LENGTH_SHORT).show()
        }

    }

    private fun checkButton(): Int {
        val id = binding.radioGroup.checkedRadioButtonId
        val radioButton: RadioButton = findViewById(id)
        return radioButton.text.toString().toInt()
    }

    private fun isFilled(title: String, description: String): Boolean {
        return title.isNotEmpty() and description.isNotEmpty()
    }
}