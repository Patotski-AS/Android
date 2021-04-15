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

package com.example.android.trackmysleepquality.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

/**
 * объект доступа к данным (DAO)
 */
@Dao
interface SleepDatabaseDao{

    /**
     * Room выполняет SQL-запрос для вставки объекта в базу данных
     */
    @Insert
    fun insert(night: SleepNight)

    /**
     * Выберите все столбцы из daily_sleep_quality_table, где EnightId соответствует: key аргумент.
     * Для ссылки на аргументы функции в запросе используется двоеточие
     */
    @Query("SELECT * from daily_sleep_quality_table WHERE nightId = :key")
    fun get(key: Long): SleepNight?

    /**
     * Аннотация удаляет один элемент
     * Недостатком является то, что вам нужно получить или узнать, что находится в таблице
     */
    @Query("DELETE FROM daily_sleep_quality_table")
    fun clear()

    /**
     * SleepNight возвращаемое getTonight()функцией nullable,
     * чтобы функция могла обрабатывать случай, когда таблица пуста.
     * (Таблица пуста в начале и после очистки данных.)
     */
    @Query("SELECT * FROM daily_sleep_quality_table ORDER BY nightId DESC LIMIT 1")
    fun getTonight(): SleepNight?

    /**
     * возвращает все столбцы из daily_sleep_quality_table, упорядоченные в порядке убывания.
     *
     */
    @Query("SELECT * FROM daily_sleep_quality_table ORDER BY nightId DESC")
    fun getAllNights(): LiveData<List<SleepNight>>


}
