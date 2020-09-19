package com.dino.something.utility

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.dino.something.R

object ImageHandler{

    /**
     * Load the image from the url to image view using Glide for circle shade views
     */
    fun setGlideRoundImage(imageUrl: String?, imageView: ImageView) {
        Glide.with(imageView.context)
            .load(imageUrl)
            .apply(
                RequestOptions()
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .circleCrop())
            .error(R.drawable.ic_launcher_background)
            .into(imageView)
    }
}