package com.example.android.cryptoapp

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.example.android.cryptoapp.api.ApiFactory
import com.example.android.cryptoapp.database.AppDatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class CoinViewModel(application: Application) : AndroidViewModel(application) {
    /**
     * Поля:
     * db-экземпляр базы данных
     * compositeDisposable - Одноразовый контейнер, в который можно положить несколько других одноразовых предметов
     *
     * Функции:
     * priceList - объект  LiveData на который будем подписываться
     * loadData -  загружает данные из сети
     * onCleared - Этот метод будет вызван, когда эта ViewModel больше не будет использоваться
     */
    private val db = AppDatabase.getInstance(application)
    private val compositeDisposable = CompositeDisposable()

    val priceList = db.coinPriceInfoDao().getPriceList()

    fun loadData(){
        val disposable =  ApiFactory.apiService.getTopCoinsInfo()
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


override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}