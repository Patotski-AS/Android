package com.example.android.trackmysleepquality.sleeptracker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.database.SleepNight
import com.example.android.trackmysleepquality.databinding.ListItemSleepNightBinding

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

    class ViewHolder private constructor(val binding: ListItemSleepNightBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: SleepNight) {
            /**
             * назначьте sleep для item, потому что вам нужно сообщить объекту привязки о вашем новом SleepNight.
             */
            binding.sleep = item

            /**
             * Этот вызов является оптимизацией, которая требует от привязки данных
             * немедленно выполнить любые ожидающие привязки. executePendingBindings()
             * При использовании адаптеров привязки  всегда рекомендуется вызывать RecyclerView,
             * потому что это может немного ускорить изменение размеров представлений.
             */
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding =
                        ListItemSleepNightBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
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