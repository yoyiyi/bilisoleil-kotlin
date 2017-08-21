package com.yoyiyi.soleil.adapter.region.sectiton

import android.content.Intent
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.yoyiyi.soleil.R
import com.yoyiyi.soleil.bean.region.RegionRecommend
import com.yoyiyi.soleil.module.app.video.VideoDetailActivity
import com.yoyiyi.soleil.utils.AppUtils
import com.yoyiyi.soleil.utils.NumberUtils
import com.yoyiyi.soleil.widget.section.StatelessSection
import com.yoyiyi.soleil.widget.section.ViewHolder

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/6/4 17:07
 * * 描述:
 */
class  RegionRecommendDynamicSection(dynamicBeanList: List<RegionRecommend.DynamicBean>) : StatelessSection<RegionRecommend.DynamicBean>(R.layout.layout_item_region_head, R.layout.layout_item_region_footer, R.layout.layout_item_region_body, dynamicBeanList) {

    override fun convert(holder: ViewHolder, dynamicBean: RegionRecommend.DynamicBean, position: Int) {
        Glide.with(mContext)
                .load(dynamicBean.cover)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.bili_default_image_tv)
                .dontAnimate()
                .into(holder.getView<ImageView>(R.id.iv_video_preview))
        holder.setText(R.id.tv_video_title, dynamicBean.title)
                .setText(R.id.tv_video_play_num, NumberUtils.format("${dynamicBean.play}"))
                .setText(R.id.tv_video_favourite, NumberUtils.format("${dynamicBean.danmaku}"))
        if (position % 2 == 0) {
            setMargins(holder.itemView, AppUtils.getDimension(R.dimen.dp10).toInt(),
                    AppUtils.getDimension(R.dimen.dp5).toInt(),
                    AppUtils.getDimension(R.dimen.dp5).toInt(),
                    AppUtils.getDimension(R.dimen.dp5).toInt())
        } else {
            setMargins(holder.itemView, AppUtils.getDimension(R.dimen.dp5).toInt(),
                    AppUtils.getDimension(R.dimen.dp5).toInt(),
                    AppUtils.getDimension(R.dimen.dp10).toInt(),
                    AppUtils.getDimension(R.dimen.dp5).toInt())
        }
        holder.itemView.setOnClickListener { mContext.startActivity(Intent(mContext, VideoDetailActivity::class.java)) }


    }

    override fun onBindHeaderViewHolder(holder: ViewHolder) {
        holder.setText(R.id.tv_title, "全区动态")
                .setImageResource(R.id.iv_icon, R.drawable.ic_header_ding)
                .setVisible(R.id.tv_rank, false)
                .setVisible(R.id.tv_look_up, false)
    }
}
