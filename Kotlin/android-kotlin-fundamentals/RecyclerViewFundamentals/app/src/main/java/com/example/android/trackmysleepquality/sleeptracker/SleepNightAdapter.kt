package com.example.android.trackmysleepquality.sleeptracker

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.TextItemViewHolder
import com.example.android.trackmysleepquality.database.SleepNight

class SleepNightAdapter : RecyclerView.Adapter<TextItemViewHolder>() {
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

    /**
     * вызывается, когда RecyclerView требуется держатель представления.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
                .inflate(R.layout.text_item_view, parent, false) as TextView
        return TextItemViewHolder(view)
    }

    /**
     * onBindViewHolder() Функция вызывается RecyclerView для отображения данных
     * для одного элемента списка в заданном положении. Таким образом, onBindViewHolder() метод принимает
     * два аргумента: держатель представления и позицию данных для привязки.
     */
    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
        val item = data[position]
        holder.textView.text = item.sleepQuality.toString()

    }

    /**
     * переопределить, getItemCount()чтобы вернуть размер списка ночей сна data. Он RecyclerView
     * должен знать, сколько элементов у адаптера для отображения, и он делает это путем вызова getItemCount()
     */
    override fun getItemCount() = data.size

}