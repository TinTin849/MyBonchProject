package com.example.mybonchproject.helpersForWork

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mybonchproject.R
import kotlin.collections.ArrayList

//Адаптер для чата
class MessagingAppRvAdapter:RecyclerView.Adapter<MessagingAppRvAdapter.ViewHolder>() {
    //Массив хранения всех сообщений
    private var array = arrayListOf<String>()

    fun setData(array:ArrayList<String>){
        this.array = array
        notifyItemInserted(array.size-1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //Разделение на сообщения пользователя и бота
        val view = if (viewType == 0){
            LayoutInflater.from(parent.context).inflate(R.layout.messaging_app_answer_one, parent, false)
        }else{
            LayoutInflater.from(parent.context).inflate(R.layout.messaging_app_answer_two, parent, false)
        }
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {holder.bind(array[position])}

    //Деление сообщений на типы (отправленные и полученные)
    override fun getItemViewType(position: Int) = position%2

    override fun getItemCount() = array.size

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        private val messageTextView: TextView? = itemView.findViewById<TextView>(R.id.textOfItem)
        fun bind(str:String){
            messageTextView?.text = str
        }
    }
}