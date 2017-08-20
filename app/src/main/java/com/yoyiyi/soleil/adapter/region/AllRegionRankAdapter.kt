package com.yoyiyi.soleil.adapter.region

import android.content.Intent
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yoyiyi.soleil.R
import com.yoyiyi.soleil.bean.region.AllRegionRank
import com.yoyiyi.soleil.module.app.video.VideoDetailActivity
import com.yoyiyi.soleil.utils.AppUtils
import com.yoyiyi.soleil.utils.SpanUtils
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/6/5 14:09
 * * 描述: 全区排行
 */

class AllRegionRankAdapter(data: List<AllRegionRank.RankBean.ListBean>?) : BaseQuickAdapter<AllRegionRank.RankBean.ListBean, BaseViewHolder>(R.layout.item_all_region, data) {

    override fun convert(holder: BaseViewHolder, allRegionRank: AllRegionRank.RankBean.ListBean) {
        Glide.with(mContext)
                .load(allRegionRank.pic)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .bitmapTransform(RoundedCornersTransformation(mContext, 5, 0))
                .placeholder(R.drawable.bili_default_image_tv)
                .dontAnimate()
                .into(holder.getView<ImageView>(R.id.iv_video_preview))

        val position = holder.adapterPosition
        if (position < 3) {
            holder.setText(R.id.tv_rank, SpanUtils()
                    .append("${position + 1}")
                    .setForegroundColor(AppUtils.getColor(R.color.pink_text_color))
                    .create())
        } else {
            holder.setText(R.id.tv_rank, "${position + 1}")
        }

        holder.setText(R.id.tv_video_title, allRegionRank.title)
                .setText(R.id.tv_video_up, allRegionRank.author)
                .setText(R.id.tv_video_play, "${allRegionRank.play}")
                .setText(R.id.tv_video_danmaku, "${allRegionRank.favorites}")
         holder.itemView.setOnClickListener { mContext.startActivity(Intent(mContext, VideoDetailActivity::class.java)) }

    }
}
