package com.test.assignment.ui.adapter

import android.annotation.SuppressLint
import android.net.Uri
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.assignment.R
import com.test.assignment.databinding.ListItemHitsListBinding
import com.test.assignment.domain.entities.Hits

class HitsViewHolder (
    parent: View,
    private val onItemClick: ((Hits) -> Unit)?
) : RecyclerView.ViewHolder(parent) {

    private val binding = ListItemHitsListBinding.bind(parent)

    @SuppressLint("UseCompatLoadingForDrawables", "SetTextI18n")
    fun bindTo(item: Hits) {
        with(binding) {
            item.run {
                txtTitle.text = user
                if (userImageURL?.isNotEmpty() == true) {
                    Glide.with(imageView.context)
                        .load(Uri.parse(userImageURL))
                        .placeholder(imageView.context.getDrawable(R.drawable.ic_launcher_foreground))
                        .into(imageView)
                }
                container.setOnClickListener {
                    onItemClick?.invoke(this)
                }
            }
        }
    }
}