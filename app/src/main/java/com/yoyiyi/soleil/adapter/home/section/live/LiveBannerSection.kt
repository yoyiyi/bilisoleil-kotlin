package com.yoyiyi.soleil.adapter.home.section.live

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.youth.banner.Banner
import com.youth.banner.BannerConfig
import com.youth.banner.loader.ImageLoader
import com.yoyiyi.soleil.R
import com.yoyiyi.soleil.bean.live.LivePartition
import com.yoyiyi.soleil.widget.section.StatelessSection
import com.yoyiyi.soleil.widget.section.ViewHolder

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/23 22:27
 * * 描述:首页直播轮播条
 */
class LiveBannerSection(private val list: List<LivePartition.Banner>) : StatelessSection<Nothing>(R.layout.layout_banner, R.layout.layout_empty) {

    override fun onBindHeaderViewHolder(holder: ViewHolder) {
        val bannar = holder.getView<Banner>(R.id.banner)
        val urls = list.map({ bannerBean -> bannerBean.img })
        bannar.setIndicatorGravity(BannerConfig.RIGHT)
                .setImages(urls)
                .setImageLoader(GlideImageLoader())
                .start()
        bannar.setOnBannerListener { i ->
            val bannerBean = list[i]
           // BrowerActivity.startActivity(mContext, bannerBean.link, bannerBean.title, bannerBean.img)
        }

    }

    internal class GlideImageLoader : ImageLoader() {
        override fun displayImage(context: Context, path: Any, imageView: ImageView) {
            Glide.with(context)
                    .load(path as String)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .dontAnimate()
                    .into(imageView)
        }
    }
}
