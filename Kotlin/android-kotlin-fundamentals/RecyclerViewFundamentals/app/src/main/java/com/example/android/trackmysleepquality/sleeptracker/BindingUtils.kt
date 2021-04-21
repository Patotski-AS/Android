package com.example.android.trackmysleepquality.sleeptracker

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.convertDurationToFormatted
import com.example.android.trackmysleepquality.convertNumericQualityToString
import com.example.android.trackmysleepquality.database.SleepNight

/**
 * адаптер для расчета и форматирования продолжительности сна.
 * Чтобы сообщить привязке данных об этом адаптере привязки, аннотируйте функцию с помощью @BindingAdapter.
 * Эта функция является адаптером для sleepDurationFormatted атрибута,
 * поэтому передайте ее sleepDurationFormatted в качестве аргумента @BindingAdapter.
 */
@BindingAdapter("sleepDurationFormatted")
fun TextView.setSleepDurationFormatted(item: SleepNight) {
    text = convertDurationToFormatted(item.startTimeMilli, item.endTimeMilli, context.resources)
}


/**
 * адаптер устанавливает качество сна на основе значения в SleepNightобъекте
 */
@BindingAdapter("sleepQualityString")
fun TextView.setSleepQualityString(item: SleepNight) {
    text = convertNumericQualityToString(item.sleepQuality, context.resources)
}

/**
 * адаптер привязки, который устанавливает изображение в представление изображения
 */
@BindingAdapter("sleepImage")
fun ImageView.setSleepImage(item: SleepNight) {
    setImageResource(when (item.sleepQuality) {
        0 -> R.drawable.ic_sleep_0
        1 -> R.drawable.ic_sleep_1
        2 -> R.drawable.ic_sleep_2
        3 -> R.drawable.ic_sleep_3
        4 -> R.drawable.ic_sleep_4
        5 -> R.drawable.ic_sleep_5
        else -> R.drawable.ic_sleep_active
    })}

