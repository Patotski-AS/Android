package com.example.android.colordiscriptor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.android.colordiscriptor.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
    }

    fun showDescription(view: View) {
        var position = binding.spinnerColors.selectedItemPosition
        var description = getDescriptionByPosition(position)
        binding.textViewDescriptionTemp.text = description
    }

    fun getDescriptionByPosition (position:Int) :String{
        val descriptions  = resources.getStringArray(R.array.description_of_temp)
        return descriptions[position]
    }
}