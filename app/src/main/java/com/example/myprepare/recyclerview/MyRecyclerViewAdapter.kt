package com.example.myprepare.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myprepare.R
import com.example.myprepare.module.RouterDetail

class MyRecyclerViewAdapter(private val routerDetailList: MutableList<RouterDetail>) :
    RecyclerView.Adapter<MyRecyclerViewViewHolder>() {

    private var onItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyRecyclerViewViewHolder {
        return MyRecyclerViewViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_application_my, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return routerDetailList.size
    }

    override fun onBindViewHolder(holder: MyRecyclerViewViewHolder, position: Int) {
        val title: String = routerDetailList[position].title
        holder.mainTitle.text = title
        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClick(title, position)
        }
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    interface OnItemClickListener {
        fun onItemClick(title: String, position: Int)
    }
}