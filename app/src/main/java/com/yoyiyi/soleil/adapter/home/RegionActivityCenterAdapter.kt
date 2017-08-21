package com.yoyiyi.soleil.adapter.home

import android.widget.ImageView

import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yoyiyi.soleil.R
import com.yoyiyi.soleil.bean.region.Region

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/27 16:06
 * * 描述: 分区界面活动中心
 */

class RegionActivityCenterAdapter(data: List<Region.Body>?) : BaseQuickAdapter<Region.Body, BaseViewHolder>(R.layout.item_home_region_activity_center, data) {

    override fun convert(holder: BaseViewHolder, bodyBean: Region.Body) {
        Glide.with(mContext)
                .load(bodyBean.cover)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.bili_default_image_tv)
                .dontAnimate()
                .into(holder.getView<ImageView>(R.id.iv_video_preview))
        holder.setText(R.id.tv_video_title, bodyBean.title)
        val position = holder.adapterPosition
        if (position == itemCount - 1) {
            holder.setVisible(R.id.space, true)
        } else {
            holder.setVisible(R.id.space, false)
        }

    }
}
