package com.example.mybonchproject.helpersForWork

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mybonchproject.R

class UsersRvAdapter:RecyclerView.Adapter<UsersRvAdapter.ViewHolder>() {
    private lateinit var data: List<UsersDataClass>

    fun setData(list: List<UsersDataClass>){
        data = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersRvAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.networking_rv_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: UsersRvAdapter.ViewHolder, position: Int) {
        holder.bind(data)
    }

    override fun getItemCount() = data.size

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        private val nameOfUser: TextView? = itemView.findViewById(R.id.firstLine)
        private val emailOfUser: TextView? = itemView.findViewById(R.id.secondLine)
        private val addressOdUser: TextView? = itemView.findViewById(R.id.thirdLine)
        fun bind(usersDataClass: List<UsersDataClass>){
            val firstLineString = usersDataClass[adapterPosition].name + ", " + usersDataClass[adapterPosition].username
            nameOfUser?.text = firstLineString
            emailOfUser?.text = usersDataClass[adapterPosition].email
            val thirdLineString = usersDataClass[adapterPosition].address.street + ", " + usersDataClass[adapterPosition].address.suite + ", " + usersDataClass[adapterPosition].address.city
            addressOdUser?.text = thirdLineString
        }
    }
}