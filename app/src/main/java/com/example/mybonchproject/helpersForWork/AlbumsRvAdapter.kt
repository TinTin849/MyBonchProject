package com.example.mybonchproject.helpersForWork

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mybonchproject.R

class AlbumsRvAdapter:RecyclerView.Adapter<AlbumsRvAdapter.ViewHolder>() {

    private lateinit var data: List<AlbumsDataClass>

    fun setData(list: List<AlbumsDataClass>){
        data = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumsRvAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.networking_rv_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlbumsRvAdapter.ViewHolder, position: Int) {
        holder.bind(data)
    }

    override fun getItemCount() = data.size

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        private val userId: TextView? = itemView.findViewById(R.id.firstLine)
        private val justId: TextView? = itemView.findViewById(R.id.secondLine)
        private val titleText: TextView? = itemView.findViewById(R.id.thirdLine)
        fun bind(albumsDataClass: List<AlbumsDataClass>){
            userId?.text = albumsDataClass[adapterPosition].userId.toString()
            justId?.text = albumsDataClass[adapterPosition].id.toString()
            titleText?.text = albumsDataClass[adapterPosition].title
        }
    }
}