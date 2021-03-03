package com.example.android.cryptoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.android.cryptoapp.api.ApiFactory
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

      val disposable =  ApiFactory.apiService.getFullPriceList(fSyms= "BTC,ETH,ADA")
            .subscribeOn(Schedulers.io()) // чтобы не делать запросы в интернет на главном потоке
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                       //выполнится, если запрос сработает
                Log.d("TEST_OF_LOADING_DATA", it.toString())
            },{
                //выполнится, если произайдет ошибка запроса
                Log.d("TEST_OF_LOADING_DATA", it.message.toString())
            })
        compositeDisposable.add(disposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

}