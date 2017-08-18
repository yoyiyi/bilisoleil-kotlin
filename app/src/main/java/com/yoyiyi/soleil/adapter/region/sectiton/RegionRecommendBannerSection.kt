package com.yoyiyi.soleil.adapter.region.sectiton

import android.content.Context
import android.widget.ImageView

import com.annimon.stream.Collectors
import com.annimon.stream.Stream
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.youth.banner.Banner
import com.youth.banner.BannerConfig
import com.youth.banner.loader.ImageLoader
import com.yoyiyi.soleil.R
import com.yoyiyi.soleil.bean.region.RegionRecommend
import com.yoyiyi.soleil.module.app.BrowerActivity
import com.yoyiyi.soleil.widget.section.StatelessSection
import com.yoyiyi.soleil.widget.section.ViewHolder

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/21 11:57
 * * 描述:分区推荐主播section
 */
class RegionRecommendBannerSection(private val mList: List<RegionRecommend.BannerBean.TopBean>) : StatelessSection<*>(R.layout.layout_banner, R.layout.layout_empty) {

    override fun onBindHeaderViewHolder(holder: ViewHolder) {
        val bannar = holder.getView<Banner>(R.id.banner)
        val urls = Stream.of(mList).map({ bannerBean -> bannerBean.image }).collect(Collectors.toList())
        bannar.setIndicatorGravity(BannerConfig.RIGHT)
                .setImages(urls)
                .setImageLoader(GlideImageLoader())
                .start()
        bannar.setOnBannerListener { i ->
            val bannerBean = mList[i]
            BrowerActivity.startActivity(mContext, bannerBean.uri, bannerBean.title, bannerBean.image)
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
