package com.example.android.viewmodel

/**
 * Унаследуем наш адаптер от RecyclerView.Adapter и указываем наш собственный ViewHolder,
 * который предоставит доступ к View-компонентам. Далее инициализируем список.
 * Функция onCreateViewHolder создает ViewHolder и инициализирует View-компоненты для списка.
 * Функция onBindViewHolder связывает View-компоненты с содержимым.
 * В функции refreshUsers передаем данные и оповещаем адаптер о
 * необходимости обновления списка вызовом notifyDataSetChanged().
 * Внутренний класс ViewHolder описывает View-компоненты списка и привязку их к RecyclerView.
 */
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class UserAdapter :RecyclerView.Adapter<UserAdapter.UserHolder>() {
    private var users: List<User> = ArrayList()

    /**
     * onCreateViewHolder: возвращает объект ViewHolder, который будет хранить данные по одному объекту
     * //создает ViewHolder и инициализирует views для списка.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.UserHolder {
        return UserHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.user_item,parent,false)
        )
    }

    /**
     * связываем используемые текстовые метки с данными
     * связывает views с содержимым
     */
    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        holder.bind(users[position])
    }

    /**
     *     getItemCount: возвращает количество объектов в списке
     */
    override fun getItemCount() = users.size

    /**
     * передаем данные
     * notifyDataSetChanged() - оповещаем адаптер о необходимости обновления списка
     */
    fun refreshUsers(users: List<User>) {
        this.users = users
        notifyDataSetChanged()
    }

    /**
     * внутренний класс ViewHolder описывает элементы представления списка и привязку их к RecyclerView
     * Класс MyViewHolder служит для оптимизации ресурсов.
     * Нужно просто перечислить используемые компоненты из макета для отдельного элемента списка.
     */
    class UserHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var userName:TextView = itemView.findViewById(R.id.userName)
        private var userDescription:TextView = itemView.findViewById(R.id.userDescription)

        fun bind(user: User) = with(itemView) {
            userName.text = user.name

            userDescription.text = user.description
        }
    }
}