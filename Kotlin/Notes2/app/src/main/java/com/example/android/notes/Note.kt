package com.example.android.notes

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val description: String,
    val dayOfWeek: Int,
    val priority: Int
) {

    @Ignore
    constructor(title: String, description: String, dayOfWeek: Int, priority: Int) : this(
        0,
        title,
        description,
        dayOfWeek,
        priority
    ) {
    }


     fun getDayAsString (position:Int):String{
         return when (position) {
             0 -> "Понедельник"
             1 -> "Вторник"
             2 -> "Среда"
             3 -> "Четвер"
             4 -> "Пятница"
             6 -> "Суббота"
             else -> "Воскресенье"
         }
     }
}

