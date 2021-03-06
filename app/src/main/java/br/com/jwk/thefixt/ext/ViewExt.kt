package br.com.jwk.thefixt.ext

import android.content.res.Resources
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

var View.isVisible: Boolean
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if (value) View.VISIBLE else View.GONE
    }


fun ImageView.loadImage(photoUrl: String) {
    Glide.with(context)
            .applyDefaultRequestOptions(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.DATA))
            .load(photoUrl)
//            .apply { if (circle) this.apply(RequestOptions.circleCropTransform()) }
            .into(this)
}
val Int.px: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()
