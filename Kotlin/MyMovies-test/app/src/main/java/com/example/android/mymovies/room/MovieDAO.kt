package com.example.android.mymovies.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.android.mymovies.AppConstants
import com.example.android.mymovies.pogo.Movie

@Dao
interface MovieDAO {

    /**
     * Когда вы вызываете insert()из своего кода Kotlin,
     * Room выполняет SQL-запрос для вставки объекта в базу данных.
     */
    @Insert
    fun insert(night: Movie)

    /**
     * Добавьте @Update аннотацию с update() функцией для одного Movie.
     * Обновляемая сущность - это сущность, имеющая тот же ключ, что и переданная
     */
    @Update
    fun update(night: Movie)

    /**
     * @Query- запрос SQLite для извлечения всех столбцов из определенной DB.
     * Возвращает позиицию по ID
     */
    @Query("SELECT * FROM ${AppConstants.DB_NAME} WHERE id = :id")
    fun getMovieById(id: Int): Movie?

    @Query("SELECT * FROM ${AppConstants.DB_NAME}")
    fun getAllMovies(): LiveData<List<Movie>>

    @Query("DELETE FROM ${AppConstants.DB_NAME} ")
    fun deleteALL()

    @Query("SELECT * FROM ${AppConstants.DB_NAME} ORDER BY id DESC LIMIT 1 ")
    fun getMovie () : Movie?


}