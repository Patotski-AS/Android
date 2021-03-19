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
    private lateinit var dbHelper: NotesDBHelper
    private lateinit var notes: ArrayList<Note>
    private lateinit var database: SQLiteDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dbHelper = NotesDBHelper(this)


        /**
         * Получаем базу данных
         * writableDatabase - для записи
         * readableDatabase - для чтения
         */
        database = dbHelper.writableDatabase

        /**
         * Очищаем базу данных
         */
//        database.delete(NotesContract.NotesEntry.TABLE_NAME, null, null)

        notes = ArrayList()
//        writeDB()
        readDB()


        /**
         * Создание RecyclerView
         * @param LinearLayoutManager(this) Создает вертикальный LinearLayout
         * @param LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false) Создает горизонтальный LinearLayout
         * @param GridLayoutManager(this,3(количество столбцов)) Создает  LinearLayout сеткой
         */
        binding.recyclerViewNotes.layoutManager = LinearLayoutManager(this)
        adapter = NotesAdapter(notes)
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
     * Чтение из БД в массив
     * cursor - объект для чтения из БД
     * close() - закрываем курсор
     * */
    private fun readDB() {
        notes.clear()
        val cursor =
            database.query(NotesContract.NotesEntry.TABLE_NAME, null, null, null, null, null, null)
        with(cursor) {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(BaseColumns._ID))
                val title = getString(getColumnIndex(NotesContract.NotesEntry.COLUMN_TITLE))
                val description =
                    getString(getColumnIndex(NotesContract.NotesEntry.COLUMN_DESCRIPTION))
                val dayOfWeek =
                    getString(getColumnIndex(NotesContract.NotesEntry.COLUMN_DAY_OF_WEEK))
                val priority = getInt(getColumnIndex(NotesContract.NotesEntry.COLUMN_PRIORITY))
                notes.add(Note(id, title, description, dayOfWeek, priority))
            }
            close()
        }
    }

    /**
     * Вставка данных в БД из массива
     * class ContentValues() - служит для хранения данных (ключ-значение)
     * insert - данных в БД из ContentValues
     */
    private fun writeDB() {

        for (note in notes) {
            val contentValues = ContentValues().apply {
                put(NotesContract.NotesEntry.COLUMN_TITLE, note.title)
                put(NotesContract.NotesEntry.COLUMN_DESCRIPTION, note.description)
                put(NotesContract.NotesEntry.COLUMN_DAY_OF_WEEK, note.dayOfWeek)
                put(NotesContract.NotesEntry.COLUMN_PRIORITY, note.priority)
            }
            database.insert(NotesContract.NotesEntry.TABLE_NAME, null, contentValues)
        }

    }

    /**
     * Удаление Заметки
     * @param adapter.notifyDataSetChanged() - обновление RecyclerView
     */
    fun removeNote(position: Int) {
        val id = notes[position].id
        val where = "${BaseColumns._ID} LIKE ?"
        val whereArgs = arrayOf(id.toString())
        database.delete(NotesContract.NotesEntry.TABLE_NAME, where, whereArgs)
        readDB()
        adapter.notifyDataSetChanged()
    }


    fun onClickAddNote(view: View) {
        intent = Intent(this, NoteAddActivity::class.java)
        startActivity(intent)
    }

}