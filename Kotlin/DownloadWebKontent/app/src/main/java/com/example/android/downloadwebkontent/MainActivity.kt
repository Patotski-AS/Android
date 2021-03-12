package com.example.android.downloadwebkontent

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.text.StringBuilder as StringBuilder


class MainActivity : AppCompatActivity() {

    private val mailRu = "https://mail.ru/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val task = DownloadTask()

        val result = task.execute(mailRu).get()
        Log.i("URL", result)

    }

    class DownloadTask : AsyncTask<String, Unit, String>() {

        override fun doInBackground(vararg params: String?): String {
            val result = StringBuilder()
            var url: URL? = null
            var urlConnection: HttpURLConnection? = null
            try {
                url = URL(params[0])
                urlConnection = url.openConnection() as HttpURLConnection
                val reader = InputStreamReader(urlConnection.inputStream)
                val bufferReader = BufferedReader(reader)
                var line = bufferReader.readLine()
                while (line != null) {
                    result.append(line)
                    line = bufferReader.readLine()
                }
            } catch (e: IOException) {
                e.printStackTrace()
            } finally {
                urlConnection?.disconnect()

                return result.toString()
            }
        }
    }
}
