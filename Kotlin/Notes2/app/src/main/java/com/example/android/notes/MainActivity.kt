package com.example.android.notes

import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.provider.BaseColumns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.notes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: NotesAdapter
    private lateinit var itemTouchHelper: ItemTouchHelper
    private lateinit var notes: ArrayList<Note>
    private lateinit var database: NotesDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = NotesDatabase.getInstance(this)

        notes = ArrayList()

        /**
         * Создание RecyclerView
         * @param LinearLayoutManager(this) Создает вертикальный LinearLayout
         * @param LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false) Создает горизонтальный LinearLayout
         * @param GridLayoutManager(this,3(количество столбцов)) Создает  LinearLayout сеткой
         */
        binding.recyclerViewNotes.layoutManager = LinearLayoutManager(this)
        adapter = NotesAdapter(notes)
        getData()
        binding.recyclerViewNotes.adapter = adapter
        adapter.clickNodeListener =
            object : NotesAdapter.ClickNodeListener {
                override fun onNodeClick(position: Int) {
                    Toast.makeText(
                        applicationContext,
                        "ceck $position",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onNodeLongClick(position: Int) {
                    removeNote(position)
                }
            }

        /**
         * ItemTouchHelper - класс для создания свайпов
         * itemTouchHelper.attachToRecyclerView(binding.recyclerViewNotes) - применить
         */
        itemTouchHelper = ItemTouchHelper(object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                TODO("Not yet implemented")
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                removeNote(viewHolder.adapterPosition)
            }
        })
        itemTouchHelper.attachToRecyclerView(binding.recyclerViewNotes)

    }

    /**
     * Удаление Заметки
     * @param adapter.notifyDataSetChanged() - обновление RecyclerView
     */
    fun removeNote(position: Int) {
        val note = notes[position]
        database.notesDAO().deleteNote(note)
        getData()
        adapter.notifyDataSetChanged()
    }

    fun onClickAddNote(view: View) {
        intent = Intent(this, NoteAddActivity::class.java)
        startActivity(intent)
    }

    private fun getData(){
       val notesFromDB = database.notesDAO().getAllNotes()
        notes.clear()
        notes.addAll(notesFromDB)
    }

}