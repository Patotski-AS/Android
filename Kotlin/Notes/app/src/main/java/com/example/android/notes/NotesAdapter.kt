package com.example.android.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotesAdapter(val notes:ArrayList<Note>) : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {


    class NotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
         val textViewTitle: TextView = itemView.findViewById(R.id.textViewTitle)
         val textViewDescription: TextView = itemView.findViewById(R.id.textViewDescription)
         val textViewDayOfWeek: TextView = itemView.findViewById(R.id.textViewDayOfWeek)
         val textViewPriority: TextView = itemView.findViewById(R.id.textViewPriority)
    }

    /**
     * Вызывается, когда RecyclerView требуется новый ViewHolder заданного типа для представления
     *  @param parent Группа ViewGroup, в которую будет добавлен новый View после привязки к
     * положение адаптера.
     * @param viewType Тип представления нового представления.
     *
     * @return Новый ViewHolder, который содержит представление данного типа представления.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
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
        val note = notes[position]
        holder.textViewTitle.text = note.title
        holder.textViewDescription.text = note.description
        holder.textViewDayOfWeek.text = note.dayOfWeek
        holder.textViewPriority.text = note.priority.toString()
    }

    /**
     * Возвращает общее количество элементов в наборе данных, хранящемся в адаптере.
     *
     * @return Общее количество элементов в этом адаптере.
     */
    override fun getItemCount(): Int {
        return  notes.size
    }
}