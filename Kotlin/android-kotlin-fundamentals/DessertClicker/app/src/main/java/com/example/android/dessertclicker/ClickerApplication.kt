package com.example.android.dessertclicker

import android.app.Application
import timber.log.Timber

class ClickerApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        //Эта строка кода инициализирует Timber библиотеку для вашего приложения,
        // чтобы вы могли использовать ее в своих действиях.
        Timber.plant(Timber.DebugTree())
    }

}