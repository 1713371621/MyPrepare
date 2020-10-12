package com.example.myprepare.recyclerview

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myprepare.R

class MyRecyclerViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val mainTitle: TextView = itemView.findViewById(R.id.main_title)
    val subtitle: TextView = itemView.findViewById(R.id.subtitle)
}