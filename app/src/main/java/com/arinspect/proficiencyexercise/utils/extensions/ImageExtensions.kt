package com.arinspect.proficiencyexercise.utils.extensions

import android.graphics.drawable.Drawable
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter(value = ["imageUrl", "placeholder"], requireAll = false)
fun setImageUrl(imageView: AppCompatImageView, url: String?, placeHolder: Drawable) {
    if (url == null || url == "") {
        imageView.setImageDrawable(placeHolder)
    } else {
        Picasso.get().load(url).into(imageView)
    }
}