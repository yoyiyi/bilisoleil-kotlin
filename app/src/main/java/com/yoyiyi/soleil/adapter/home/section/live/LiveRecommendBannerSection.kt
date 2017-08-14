package com.yoyiyi.soleil.adapter.home.section.live

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.yoyiyi.soleil.R
import com.yoyiyi.soleil.bean.live.LiveRecommend
import com.yoyiyi.soleil.utils.AppUtils
import com.yoyiyi.soleil.utils.SpanUtils
import com.yoyiyi.soleil.widget.section.StatelessSection
import com.yoyiyi.soleil.widget.section.ViewHolder

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/21 11:57
 * * 描述:首页直播推荐主播section（音乐台）
 */
class LiveRecommendBannerSection(private val mData: LiveRecommend.RecommendDataBean.BannerDataBean) : StatelessSection<Nothing>(R.layout.layout_item_home_live_body, R.layout.layout_empty) {

    override fun onBindHeaderViewHolder(holder: ViewHolder) {
        Glide.with(mContext)
                .load(mData.cover?.src)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.bili_default_image_tv)
                .dontAnimate()
                .into(holder.getView<ImageView>(R.id.iv_video_preview))

        holder.setText(R.id.tv_video_live_up, if (mData.owner?.name.isNullOrEmpty()) "未知" else mData.owner?.name)//up
                .setText(R.id.tv_video_online, "${mData.online}")//在线人数;

        holder.setText(R.id.tv_video_title, SpanUtils()
                .append("#${mData.area}#")
                .append("${mData.title}")
                .setForegroundColor(AppUtils.getColor(R.color.pink_text_color))
                .create())

        setMargins(holder.itemView, AppUtils.getDimension(R.dimen.dp10).toInt(),
                AppUtils.getDimension(R.dimen.dp5).toInt(),
                AppUtils.getDimension(R.dimen.dp10).toInt(),
                AppUtils.getDimension(R.dimen.dp5).toInt())
    }

}
