package com.example.android.timestable

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.appcompat.app.AppCompatActivity
import com.example.android.timestable.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var numbers: ArrayList<Int>
    private val max = 20
    private val min = 1
    private val count = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        numbers = arrayListOf()

        val adapter = ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1, numbers)
        binding.listViewNumbers.adapter = adapter

        /**
         * @param setOnSeekBarChangeListener - наблюдатель для SeekBar,
         * параметром принимает анонимный класс реализующий интерфейс OnSeekBarChangeListener
         * @param notifyDataSetChanged - дает знать адаптеру, что инфа изменилась
         * @param max - устанавливает max значение в SeekBar
         * @param progress - устанавливает текущее значение в SeekBar
         */
        binding.seekBar.max=max
        binding.seekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                numbers.clear()

                if (progress<=0)
                    binding.seekBar.progress = min
                for (i in min..count){
                    numbers.add(i*binding.seekBar.progress)
                }
                adapter.notifyDataSetChanged()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })
        binding.seekBar.progress = min


    }
}