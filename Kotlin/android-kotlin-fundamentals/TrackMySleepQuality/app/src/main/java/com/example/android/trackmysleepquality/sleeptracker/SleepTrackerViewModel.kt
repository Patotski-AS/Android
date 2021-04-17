/*
 * Copyright 2019, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.trackmysleepquality.sleeptracker

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.example.android.trackmysleepquality.database.SleepDatabaseDao
import com.example.android.trackmysleepquality.database.SleepNight
import com.example.android.trackmysleepquality.formatNights
import kotlinx.coroutines.launch

/**
 * ViewModel for SleepTrackerFragment.
 * AndroidViewModel. Этот класс аналогичен классу ViewModel,
 * но он принимает контекст приложения как параметр конструктора и делает его доступным как свойство
 */
class SleepTrackerViewModel(
        val database: SleepDatabaseDao,
        application: Application) : AndroidViewModel(application) {
    /**
     * Определите переменную, вызываемую tonight для хранения текущей ночи.
     * Сделайте переменную MutableLiveData, потому что вам нужно иметь
     * возможность наблюдать за данными и изменять их.
     */
    private var tonight = MutableLiveData<SleepNight?>()

    /**
     * Получите все ночи из базы данных и присвойте их nights переменной.
     */
    private val nights = database.getAllNights()

    /**
     * код для преобразования nights в nightsString
     * Переводим nights в map()функцию из Transformations класса.
     * Чтобы получить доступ к вашим строковым ресурсам, определите функцию сопоставления
     * как вызывающую formatNights(). добавлем nights и Resources объекты.
     *
     */
    val nightsString = Transformations.map(nights) { nights ->
        formatNights(nights, application.resources)
    }

    init {
        initializeTonight()
    }

    /**
     * viewModelScope.launch - запуск сопрораммы
     * Внутри фигурных скобок получите значение для tonight
     * из базы данных путем вызова getTonightFromDatabase() и присвойте значение tonight.value.
     * Фигурные скобки для launch. Они определяют лямбда-выражение,
     * которое представляет собой функцию без имени. В этом примере вы передаете лямбду launch
     * конструктору сопрограмм. Этот конструктор создает сопрограмму и назначает
     * выполнение этой лямбды соответствующему диспетчеру.
     */
    private fun initializeTonight() {
        viewModelScope.launch {
            tonight.value = getTonightFromDatabase()
        }
    }

    /**
     * Внутри тела функции getTonightFromDatabase() получить tonight (последнюю ночь) из базы данных.
     * Если время начала и окончания не совпадают, что означает, что ночь уже завершилась,
     * вернитесь null. В противном случае верните ночь.
     */
    private suspend fun getTonightFromDatabase(): SleepNight? {
        var night = database.getTonight()
        if (night?.endTimeMilli != night?.startTimeMilli) {
            night = null
        }
        return night
    }

    /**
     * обработчик щелчка для кнопки « Пуск»
     * Вам нужно создать новый SleepNight, вставить его в базу данных и присвоить ему tonight
     *
     */
    fun onStartTracking() {
        viewModelScope.launch {
            val newNight = SleepNight()
            insert(newNight)
            tonight.value = getTonightFromDatabase()
        }
    }

    /**
     * вставка в DB
     */
    private suspend fun insert(night: SleepNight) {
        database.insert(night)
    }

}

