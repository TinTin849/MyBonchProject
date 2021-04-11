package com.example.mybonchproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class RvAdapter:RecyclerView.Adapter<RvAdapter.ViewHolder>() {

    var array = arrayListOf<String>()

    fun setData(arr:ArrayList<String>){
        array = arr
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = if (viewType == 0){
            LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        }else{
            LayoutInflater.from(parent.context).inflate(R.layout.item2, parent, false)
        }

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(array[position])
    }

    override fun getItemViewType(position: Int) = position%2

    override fun getItemCount() = array.size

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val textView = itemView.findViewById<TextView>(R.id.textOfItem)
        fun bind(str:String){
            textView.text = str

        }
    }
}