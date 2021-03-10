package com.example.android.cafeorder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import androidx.core.view.isVisible
import com.example.android.cafeorder.databinding.ActivityCreateOrderBinding
import java.lang.StringBuilder
import java.util.*

class CreateOrderActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateOrderBinding
    private lateinit var name: String
    private lateinit var password: String
    private lateinit var drink: String
    private lateinit var stringBuilder: StringBuilder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_order)
        binding = ActivityCreateOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        drink = getString(R.string.tea).toLowerCase(Locale.ROOT)
        binding.textViewAdditions.text =
            String.format(getString(R.string.text_view_additions), drink)
        name = intent.getStringExtra("editTextName").toString().trim()
        password = intent.getStringExtra("editTextPassword").toString().trim()
        binding.textViewHello.text = String.format(getString(R.string.text_view_hello), name)
        stringBuilder=StringBuilder()

    }

    fun onClickChanceDrink(view: View) {
        val button = view as RadioButton
        drink = button.text.toString().toLowerCase(Locale.ROOT)
        binding.textViewAdditions.text =
            String.format(getString(R.string.text_view_additions), drink)
        if (button.id == binding.radioButtonCoffee.id) {
            binding.checkBoxLemon.isVisible = false
            binding.spinnerCoffee.isVisible = true
            binding.spinnerTea.isVisible = false
        } else {
            binding.checkBoxLemon.isVisible = true
            binding.spinnerCoffee.isVisible = false
            binding.spinnerTea.isVisible = true
        }
    }

    fun onClickSendOrder(view: View) {
    }
}