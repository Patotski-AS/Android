package com.example.android.downloadwebkontent

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import java.io.IOException
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.concurrent.ExecutionException


class MainActivity : AppCompatActivity() {
    private var imageView: ImageView? = null
    private val url =
        "https://avatars.mds.yandex.net/get-pdb/27625/d756a5bd-6821-4969-865f-839e5d6615ca/s1200"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imageView = findViewById(R.id.imageView2)
    }

    fun onClickDownloadImage(view: View?) {
        val task = DownloadImageTask()
        var bitmap: Bitmap? = null
        try {
            bitmap = task.execute(url).get()
        } catch (e: ExecutionException) {
            e.printStackTrace()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        imageView!!.setImageBitmap(bitmap)
    }

    class DownloadImageTask :
        AsyncTask<String?, Void?, Bitmap?>() {
        override fun doInBackground(vararg params: String?): Bitmap? {
            var url: URL? = null
            var urlConnection: HttpURLConnection? = null
            try {
                url = URL(params[0])
                urlConnection = url.openConnection() as HttpURLConnection
                val inputStream = urlConnection.inputStream
                return BitmapFactory.decodeStream(inputStream)
            } catch (e: MalformedURLException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            } finally {
                urlConnection?.disconnect()
            }
            return null

        }

    }
}


//class MainActivity : AppCompatActivity() {
//    private lateinit var binding: ActivityMainBinding
//    private lateinit var url:String
//
//
//    private val mailRu = "https://avatars.mds.yandex.net/get-pdb/27625/d756a5bd-6821-4969-865f-839e5d6615ca/s1200"
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        val task = DownloadImageTask()
//        val bitmap = task.execute(mailRu).get()
//        binding.run {
//            imageView2.setImageBitmap(bitmap)
//        }
//
//
////        val task = DownloadTask()
////        val result = task.execute(mailRu).get()
////        Log.i("URL", result)
//
//
//    }
//
//    class DownloadImageTask : AsyncTask<String, Unit, Bitmap>() {
//        override fun doInBackground(vararg params: String?): Bitmap? {
//            var url: URL? = null
//            var urlConnection: HttpURLConnection? = null
//            try {
//                url = URL(params[0])
//                urlConnection = url.openConnection() as HttpURLConnection
//
//                return BitmapFactory.decodeStream(urlConnection.inputStream)
//
//            } catch (e: IOException) {
//                e.printStackTrace()
//            } finally {
//                urlConnection?.disconnect()
//
//                return null
//            }
//        }
//    }
//
//
//    class DownloadTask : AsyncTask<String, Unit, String>() {
//        override fun doInBackground(vararg params: String?): String {
//            val result = StringBuilder()
//            var url: URL? = null
//            var urlConnection: HttpURLConnection? = null
//            try {
//                url = URL(params[0])
//                urlConnection = url.openConnection() as HttpURLConnection
//                val reader = InputStreamReader(urlConnection.inputStream)
//                val bufferReader = BufferedReader(reader)
//                var line = bufferReader.readLine()
//                while (line != null) {
//                    result.append(line)
//                    line = bufferReader.readLine()
//                }
//            } catch (e: IOException) {
//                e.printStackTrace()
//            } finally {
//                urlConnection?.disconnect()
//
//                return result.toString()
//            }
//        }
//    }
//
//    fun clickDownload(view: View) {
//        url = binding.editTextTextAddress.text.toString().trim()
//        val task = DownloadImageTask()
//        val bitmap = task.execute(url).get()
//        binding.run {
//            imageView2.setImageBitmap(bitmap)
//        }
//
//    }
//}

