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

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * entities - елемент DB
 * version - Каждый раз, когда вы меняете схему, вам придется увеличивать номер версии.
 * Установите exportSchema значение false, чтобы не сохранять резервные копии истории версий схемы.
 */
@Database(entities = [SleepNight::class], version = 1, exportSchema = false)
abstract class SleepDatabase : RoomDatabase() {

    /**
     * База данных должна знать о DAO. Внутри тела класса объявите абстрактное значение,
     * которое возвращает SleepDatabaseDao. У вас может быть несколько DAO.
     */
    abstract val sleepDatabaseDao: SleepDatabaseDao

    /**
     * companion object  позволяет клиентам получать доступ к методам создания или получения
     * базы данных без создания экземпляра класса
     */
    companion object {

        /**
         * INSTANCE - хранит ссылку на переменную созданной DB
         */
        @Volatile
        private var INSTANCE: SleepDatabase? = null

        /**
         * вызовите Room.databaseBuilderи укажите контекст, который вы передали,
         * класс базы данных и имя для базы данных sleep_history_database.
         * .fallbackToDestructiveMigration() - стратегия миграции,уничтожить и восстановить
         * базу данных, что означает потерю данных
         */
        fun getInstance(context: Context): SleepDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                            context.applicationContext,
                            SleepDatabase::class.java,
                            "sleep_history_database")
                            .fallbackToDestructiveMigration()
                            .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
