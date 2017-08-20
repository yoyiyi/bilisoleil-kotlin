package com.yoyiyi.soleil.adapter.region.sectiton

import android.content.Intent
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.yoyiyi.soleil.R
import com.yoyiyi.soleil.bean.region.RegionType
import com.yoyiyi.soleil.module.app.video.VideoDetailActivity
import com.yoyiyi.soleil.widget.section.StatelessSection
import com.yoyiyi.soleil.widget.section.ViewHolder
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/6/4 17:07
 * * 描述:最热视频
 */
class RegionTypeNewSection(newBeanList: List<RegionType.NewBean>) : StatelessSection<RegionType.NewBean>(R.layout.layout_item_region_type_head, R.layout.layout_item_region_type_body, newBeanList) {

    override fun convert(holder: ViewHolder, newBean: RegionType.NewBean, position: Int) {
        Glide.with(mContext)
                .load(newBean.cover)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .bitmapTransform(RoundedCornersTransformation(mContext, 5, 0))
                .placeholder(R.drawable.bili_default_image_tv)
                .dontAnimate()
                .into(holder.getView<ImageView>(R.id.iv_video_preview))
        holder.setText(R.id.tv_video_title, newBean.title)
                .setText(R.id.tv_video_up, newBean.name)
                .setText(R.id.tv_video_play,"${newBean.play}")
                .setText(R.id.tv_video_danmaku,"${newBean.danmaku}")
        holder.itemView.setOnClickListener { mContext.startActivity(Intent(mContext, VideoDetailActivity::class.java)) }

    }

    override fun onBindHeaderViewHolder(holder: ViewHolder) {
        holder.setText(R.id.tv_title, "最新视频")
    }
}
