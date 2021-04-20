package com.example.android.trackmysleepquality.sleeptracker

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.TextItemViewHolder
import com.example.android.trackmysleepquality.convertDurationToFormatted
import com.example.android.trackmysleepquality.convertNumericQualityToString
import com.example.android.trackmysleepquality.database.SleepNight

class SleepNightAdapter : RecyclerView.Adapter<SleepNightAdapter.ViewHolder>() {
    /**
     * Чтобы сообщить, RecyclerView когда данные, которые он отображает, были изменены,
     * добавьте настраиваемый установщик к data переменной, которая находится в верхней
     * части SleepNightAdapter класса. В установщике задайте data новое значение,
     * затем вызовите, notifyDataSetChanged() чтобы запустить перерисовку списка с новыми данными.
     */
    var data = listOf<SleepNight>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val sleepLength: TextView = itemView.findViewById(R.id.sleep_length)
        val quality: TextView = itemView.findViewById(R.id.quality_string)
        val qualityImage: ImageView = itemView.findViewById(R.id.quality_image)
    }

    /**
     * вызывается, когда RecyclerView требуется держатель представления.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
                .inflate(R.layout.list_item_sleep_night, parent, false)
        return ViewHolder(view)
    }


    /**
     * onBindViewHolder() Функция вызывается RecyclerView для отображения данных
     * для одного элемента списка в заданном положении. Таким образом, onBindViewHolder() метод принимает
     * два аргумента: держатель представления и позицию данных для привязки.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        val res = holder.itemView.context.resources
        holder.sleepLength.text = convertDurationToFormatted(item.startTimeMilli, item.endTimeMilli, res)
        holder.quality.text= convertNumericQualityToString(item.sleepQuality, res)
        holder.qualityImage.setImageResource(when (item.sleepQuality) {
            0 -> R.drawable.ic_sleep_0
            1 -> R.drawable.ic_sleep_1
            2 -> R.drawable.ic_sleep_2
            3 -> R.drawable.ic_sleep_3
            4 -> R.drawable.ic_sleep_4
            5 -> R.drawable.ic_sleep_5
            else -> R.drawable.ic_sleep_active
        })
    }


    /**
     * переопределить, getItemCount()чтобы вернуть размер списка ночей сна data. Он RecyclerView
     * должен знать, сколько элементов у адаптера для отображения, и он делает это путем вызова getItemCount()
     */
    override fun getItemCount() = data.size

}