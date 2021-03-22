package com.example.android.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView


class NotesAdapter(
    private val notes: ArrayList<Note>
) :
    RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {
    var clickNodeListener: ClickNodeListener? = null


    interface ClickNodeListener {
        fun onNodeClick(position: Int)
        fun onNodeLongClick(position: Int)
    }

    inner class NotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewTitle: TextView = itemView.findViewById(R.id.textViewTitle)
        val textViewDescription: TextView = itemView.findViewById(R.id.textViewDescription)
        val textViewDayOfWeek: TextView = itemView.findViewById(R.id.textViewDayOfWeek)

        init {
            if (clickNodeListener != null) {
                itemView.setOnClickListener(View.OnClickListener {
                    clickNodeListener?.onNodeClick(adapterPosition)
                })
                itemView.setOnLongClickListener(View.OnLongClickListener {
                    clickNodeListener?.onNodeLongClick(adapterPosition)
                    true
                })
            }
        }
    }

    /**
     * Вызывается, когда RecyclerView требуется новый ViewHolder заданного типа для представления
     *  @param parent Группа ViewGroup, в которую будет добавлен новый View после привязки к
     * положение адаптера.
     * @param viewType Тип представления нового представления.
     *
     * @author RecyclerView.NO_POSITION - когда данные обнавлябтся, а позиции адаптера еще не расчитаны
     *
     * @return Новый ViewHolder, который содержит представление данного типа представления.
     */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NotesAdapter.NotesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return NotesViewHolder(view)
    }

    /**
     * Вызывается RecyclerView для отображения данных в указанной позиции. Этот метод должен
     * обновить содержимое  ViewHolder , чтобы отразить элемент
     *
     * @param holder ViewHolder, который должен быть обновлен для представления содержимого
     * элемент в данной позиции в наборе данных.
     * @param position Позиция элемента в наборе данных адаптера.
     */
    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val note = notes[holder.adapterPosition]
        holder.textViewTitle.text = note.title
        holder.textViewDescription.text = note.description
        holder.textViewDayOfWeek.text = note.getDayAsString(note.dayOfWeek)
//        holder.textViewPriority.text = note.priority.toString()
        val colorID = when (note.priority) {
            1 -> holder.itemView.resources.getColor(android.R.color.holo_red_light)
            2 -> holder.itemView.resources.getColor(android.R.color.holo_orange_light)
            3 -> holder.itemView.resources.getColor(android.R.color.holo_green_light)
            else -> holder.itemView.resources.getColor(android.R.color.darker_gray)
        }
        holder.textViewTitle.setBackgroundColor(colorID)
    }

    /**
     * Возвращает общее количество элементов в наборе данных, хранящемся в адаптере.
     *
     * @return Общее количество элементов в этом адаптере.
     */
    override fun getItemCount() = notes.size

}

