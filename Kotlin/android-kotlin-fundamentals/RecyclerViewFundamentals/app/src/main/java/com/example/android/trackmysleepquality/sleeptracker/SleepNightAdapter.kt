package com.example.android.trackmysleepquality.sleeptracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.convertDurationToFormatted
import com.example.android.trackmysleepquality.convertNumericQualityToString
import com.example.android.trackmysleepquality.database.SleepNight

/**
 * import: androidx.recyclerview.widget.ListAdapter
 */
class SleepNightAdapter : ListAdapter<SleepNight, SleepNightAdapter.ViewHolder>(SleepNightDiffCallback()) {




    /**
     * onBindViewHolder() Функция вызывается RecyclerView для отображения данных
     * для одного элемента списка в заданном положении. Таким образом, onBindViewHolder() метод принимает
     * два аргумента: держатель представления и позицию данных для привязки.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    /**
     * вызывается, когда RecyclerView требуется держатель представления.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val sleepLength: TextView = itemView.findViewById(R.id.sleep_length)
        val quality: TextView = itemView.findViewById(R.id.quality_string)
        val qualityImage: ImageView = itemView.findViewById(R.id.quality_image)

        fun bind(item: SleepNight) {
            val res = itemView.context.resources
            sleepLength.text = convertDurationToFormatted(item.startTimeMilli, item.endTimeMilli, res)
            quality.text = convertNumericQualityToString(item.sleepQuality, res)
            qualityImage.setImageResource(when (item.sleepQuality) {
                0 -> R.drawable.ic_sleep_0
                1 -> R.drawable.ic_sleep_1
                2 -> R.drawable.ic_sleep_2
                3 -> R.drawable.ic_sleep_3
                4 -> R.drawable.ic_sleep_4
                5 -> R.drawable.ic_sleep_5
                else -> R.drawable.ic_sleep_active
            })
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater
                        .inflate(R.layout.list_item_sleep_night, parent, false)

                return ViewHolder(view)
            }
        }
    }
}

class SleepNightDiffCallback: DiffUtil.ItemCallback<SleepNight>() {

    /**
     * проверяет, являются ли два переданных SleepNight элемента, oldItem и newItem, одинаковыми.
     * Если у предметов одинаковые nightId, это один и тот же предмет, поэтому верните их true.
     * В противном случае верните false. DiffUtil использует этот тест,
     * чтобы определить, был ли элемент добавлен, удален или перемещен.
     */
    override fun areItemsTheSame(oldItem: SleepNight, newItem: SleepNight): Boolean {
        return oldItem.nightId == newItem.nightId
    }

    /**
     * проверьте, содержатся ли oldItem и newItem совпадают ли данные;
     * то есть равны ли они. Эта проверка на равенство проверяет все поля,
     * потому что SleepNight это класс данных. Data классы автоматически определяют equals
     * и несколько других методов для вас. Если есть различия между oldItem
     * и newItem, этот код сообщает, DiffUtil что элемент был обновлен.
     */
    override fun areContentsTheSame(oldItem: SleepNight, newItem: SleepNight): Boolean {
        return oldItem == newItem
    }

}