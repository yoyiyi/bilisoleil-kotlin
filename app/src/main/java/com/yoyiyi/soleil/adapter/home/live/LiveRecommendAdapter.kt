package com.yoyiyi.soleil.adapter.home.live

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yoyiyi.soleil.R
import com.yoyiyi.soleil.bean.live.LiveRecommend
import com.yoyiyi.soleil.utils.NumberUtils

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * @date 创建时间：2017/8/14 15:38
 * 描述:
 */
class LiveRecommendAdapter(data: List<LiveRecommend.RecommendData.Lives>)
    : BaseQuickAdapter<LiveRecommend.RecommendData.Lives, BaseViewHolder>(R.layout.layout_item_home_live_body_mul, data) {
    override fun convert(holder: BaseViewHolder, item: LiveRecommend.RecommendData.Lives) {
        Glide.with(mContext)
                .load(item.cover.src)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.bili_default_image_tv)
                .dontAnimate()
                .into(holder.getView<ImageView>(R.id.iv_video_preview))

        holder.setText(R.id.tv_video_live_up, item.owner.name)//up
                .setText(R.id.tv_video_online, NumberUtils.format("${item.online}"))//在线人数;

        holder.setText(R.id.tv_video_title, item.title)
    }

}