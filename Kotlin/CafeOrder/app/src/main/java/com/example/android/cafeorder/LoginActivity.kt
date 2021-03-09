package com.example.android.cafeorder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.android.cafeorder.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun onClickCreateOrder(view: View) {
        /**
         * if - если данные введены:
         * intent - создаем намерение, для вызова новой активности
         * intent.putExtra - сохраняем в intent данные, для передачи в новую активность
         * startActivity(intent) - вызываем новую активность
         * else - если не хватает данных выводим уведомление
         */
        if (binding.editTextName.text.isNotEmpty() && binding.editTextPassword.text.isNotEmpty()) {
            intent = Intent(this, CreateOrderActivity::class.java)
            intent.putExtra("editTextName", binding.editTextName.text.toString().trim())
            intent.putExtra("editTextPassword", binding.editTextPassword.text.toString().trim())
            startActivity(intent)
        }else
            Toast.makeText(this,R.string.warning_fill_fiels,Toast.LENGTH_SHORT).show()
    }
}