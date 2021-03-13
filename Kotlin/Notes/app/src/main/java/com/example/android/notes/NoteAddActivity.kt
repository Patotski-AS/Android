package com.example.android.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import androidx.core.view.get
import com.example.android.notes.databinding.ActivityNoteAddBinding

class NoteAddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNoteAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_add)

        binding = ActivityNoteAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun onClickButtonSave(view: View) {
        val note = Note(
            binding.editTextTextTitle.text.toString(),
            binding.editTextDescription.text.toString(),
            binding.spinner?.selectedItem.toString(),
            checkButton()
        )

        MainActivity.notes.add(note)

        intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun checkButton(): Int {
        val id = binding.radioGroup.checkedRadioButtonId
        val radioButton: RadioButton = findViewById(id)
        return radioButton.text.toString().toInt()
    }
}