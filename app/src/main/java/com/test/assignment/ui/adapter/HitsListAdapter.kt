package com.test.assignment.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.test.assignment.R
import com.test.assignment.domain.entities.Hits

class HitsListAdapter : RecyclerView.Adapter<HitsViewHolder>() {

    var onItemClick: ((Hits) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        HitsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.list_item_hits_list,
                    parent,
                    false
                ), onItemClick
        )

    override fun onBindViewHolder(holder: HitsViewHolder, position: Int) =
        holder.bindTo(differ.currentList[position])

    override fun getItemCount() = differ.currentList.size

    private val differCallback = object : DiffUtil.ItemCallback<Hits>() {
        override fun areItemsTheSame(oldItem: Hits, newItem: Hits): Boolean =
            oldItem.id == newItem.id

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Hits, newItem: Hits): Boolean =
            oldItem == newItem

    }
    val differ = AsyncListDiffer(this, differCallback)
}