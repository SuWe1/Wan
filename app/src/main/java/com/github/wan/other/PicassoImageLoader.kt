package com.github.wan.other

import android.content.Context
import android.widget.ImageView
import com.github.wan.extentions.loadingImage
import com.youth.banner.loader.ImageLoader

class PicassoImageLoader : ImageLoader() {
    override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
        imageView!!.loadingImage((path as String?)!!)
    }
}