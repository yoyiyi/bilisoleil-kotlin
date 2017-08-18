package com.yoyiyi.soleil.ext

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.youth.banner.Banner
import com.youth.banner.BannerConfig
import com.youth.banner.loader.ImageLoader

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * @date 创建时间：2017/8/18 15:26
 * 描述:
 */

fun Banner.startAnim(urls: List<String>?) {
    setIndicatorGravity(BannerConfig.RIGHT)
            .setImages(urls)
            .setImageLoader(GlideImageLoader())
            .start()

}


private class GlideImageLoader : ImageLoader() {
    override fun displayImage(context: Context, path: Any, imageView: ImageView) {
        Glide.with(context)
                .load(path as String)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
                .into(imageView)
    }
}

