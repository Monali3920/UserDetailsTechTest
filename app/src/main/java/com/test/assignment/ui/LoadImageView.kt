package com.test.assignment.ui

import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide

object LoadImageView {

    @JvmStatic fun loadImage(view: ImageView, url: String?) {
        if(url?.isNotEmpty() == true){
            Glide.with(view.context)
                .load(Uri.parse(url))
                .into(view)
        }
    }

}