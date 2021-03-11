package com.example.android.toolsshop


//import android.content.Intent
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.view.View
//import android.widget.Adapter
//import android.widget.AdapterView
//import androidx.appcompat.app.ActionBar
//import com.example.android.toolsshop.databinding.ActivityMainBinding
//
//class MainActivity : AppCompatActivity() {
//    private lateinit var binding: ActivityMainBinding
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        /**
//         * supportActionBar - ссылка на ActionBar
//         * actionBar?.hide() - скрыаем ActionBar
//         */
//        val actionBar = supportActionBar
//        actionBar?.hide()
//
//        /**
//         * setOnItemClickListener() - добавить слушателя кликов на путкты элементов списка
//         * параметром принимает объект класса, который реализует интерфейс
//         */
//
//    }
//}
import android.os.Bundle
import android.widget.AdapterView.OnItemClickListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.android.toolsshop.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
        private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        /**
         * supportActionBar - ссылка на ActionBar
         * actionBar?.hide() - скрыаем ActionBar
         */
        val actionBar = supportActionBar
        actionBar?.hide()

        /**
         * setOnItemClickListener() - добавить слушателя кликов на путкты элементов списка
         * параметром принимает объект класса, который реализует интерфейс OnItemClickListener
         * @param parent Объект AdapterView, в котором произошел щелчок.
         * @param view - представление в AdapterView, по которому был выполнен щелчок (это
         * будет вид, предоставленный адаптером)
         * @param position Позиция представления в адаптере.
         * @param id Идентификатор строки элемента, по которому был выполнен щелчок.
         */
        binding.listViewTools.onItemClickListener = OnItemClickListener { parent, view, position, id ->
            Toast.makeText(applicationContext,"Позиция: $position",Toast.LENGTH_SHORT).show()

        }
    }
}
