package com.example.android.messenger

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.android.messenger.databinding.ActivityMessageCreateBinding

class CreateMessageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMessageCreateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message_create)

        binding = ActivityMessageCreateBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    fun onClickSendMsg(view: View) {
        val msg = binding.editTextMessage.text.toString()

        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT,msg)
        val chooseIntent = Intent.createChooser(intent,getString(R.string.chooser_title))
        startActivity(chooseIntent)


//        val intent = Intent(this,ReceivedMessageActivity::class.java)
//        intent.putExtra("msg",msg)
//        startActivity(intent)
    }
}