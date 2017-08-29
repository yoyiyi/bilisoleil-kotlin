package com.yoyiyi.soleil.adapter.home

import android.content.Intent
import android.widget.ImageView
import android.widget.RelativeLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yoyiyi.soleil.R
import com.yoyiyi.soleil.bean.dynamic.MulDynamic
import com.yoyiyi.soleil.module.app.video.VideoDetailActivity
import com.yoyiyi.soleil.utils.AppUtils
import com.yoyiyi.soleil.utils.NumberUtils
import com.yoyiyi.soleil.utils.time.FormatUtils
import com.yoyiyi.soleil.utils.time.TimeUtils
import com.yoyiyi.soleil.widget.CircleImageView

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/6/13 16:09
 * * 描述:动态Adapter
 */

class DynamicAdapter(data: List<MulDynamic>?) : BaseMultiItemQuickAdapter<MulDynamic, BaseViewHolder>(data) {
    init {
        addItemType(MulDynamic.TYPE_LV0, R.layout.item_home_dynamic)
        addItemType(MulDynamic.TYPE_LV1, R.layout.item_home_dynamic)
    }

    override fun convert(holder: BaseViewHolder, mulDynamic: MulDynamic) {
        holder.itemView.setOnClickListener { mContext.startActivity(Intent(mContext, VideoDetailActivity::class.java)) }
        when (holder.itemViewType) {
            MulDynamic.TYPE_LV0 -> {
                val itemBean = mulDynamic.group
                holder.setVisible(R.id.fl_recent, itemBean?.isRecent == 1)
                holder.getView<RelativeLayout>(R.id.fl_recent)
                        .setOnClickListener {
                            mulDynamic.flag = false
                            val pos = holder.adapterPosition
                            notifyItemChanged(pos)
                            expand(pos, false)

                        }
                if (mulDynamic.flag) {
                    holder.setVisible(R.id.fl_recent, true)
                    holder.setText(R.id.tv_recent, "还有${itemBean?.recent_count}个视频被隐藏")
                } else {
                    holder.setVisible(R.id.fl_recent, false)
                }
                when (itemBean?.type) {
                    0//关注up
                    -> {
                        holder.setVisible(R.id.iv_avatar, true)
                                .setVisible(R.id.tv_tag, false)
                                .setVisible(R.id.tv_title_time, true)
                                .setVisible(R.id.tv_title_tag_time, false)
                                .setVisible(R.id.tv_title, true)
                                .setText(R.id.tv_title_time,
                                        TimeUtils.getFriendlyTimeSpanByNow((itemBean.ctime * Math.pow(10.0, 3.0)).toLong()))
                                .setText(R.id.tv_title, itemBean.name)
                                .setText(R.id.tv_video_title, itemBean.title)
                                .setText(R.id.tv_duration, FormatUtils.formatDuration("${itemBean.duration}"))
                                .setVisible(R.id.tv_duration, true)
                                .setVisible(R.id.iv_video_play_num, true)
                                .setVisible(R.id.tv_video_play_num, true)
                                .setVisible(R.id.tv_video_favourite, true)
                                .setVisible(R.id.iv_video_online_region, true)
                                .setText(R.id.tv_video_play_num, " " + NumberUtils.format(itemBean.play.toString() + ""))
                                .setText(R.id.tv_video_favourite, " " + NumberUtils.format(itemBean.favorite.toString() + ""))
                                .setVisible(R.id.iv_tag_video_play_num, false)
                                .setVisible(R.id.tv_tag_video_favourite, false)
                                .setVisible(R.id.iv_tag_video_online_region, false)
                                .setText(R.id.tv_tag_video_play_num,
                                        "${itemBean.name}${itemBean.tag.let { "·${it.tag_name}" }}")

                        Glide.with(mContext)
                                .load(itemBean.face)
                                .centerCrop()
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .placeholder(R.drawable.bili_default_avatar)
                                .dontAnimate()
                                .into(holder.getView<CircleImageView>(R.id.iv_avatar))
                        Glide.with(mContext)
                                .load(itemBean.cover)
                                .centerCrop()
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .placeholder(R.drawable.bili_default_image_tv)
                                .dontAnimate()
                                .into(holder.getView<ImageView>(R.id.iv_preview))
                    }
                    2//国产动画
                    -> {
                        holder.setVisible(R.id.iv_avatar, false)
                                .setVisible(R.id.tv_title, false)
                                .setVisible(R.id.tv_title_time, false)
                                .setVisible(R.id.tv_title_tag_time, true).setVisible(R.id.tv_duration, false)
                                .setText(R.id.tv_title_tag_time, TimeUtils.getFriendlyTimeSpanByNow((itemBean.ctime.times(Math.pow(10.0, 3.0))).toLong()))
                                .setVisible(R.id.tv_tag, true)
                                .setText(R.id.tv_tag, "国产动画")
                                .setBackgroundColor(R.id.tv_tag, AppUtils.getColor(R.color.yellow_30))
                                .setText(R.id.tv_video_title, itemBean.title)
                                .setVisible(R.id.iv_video_play_num, false)
                                .setVisible(R.id.tv_video_play_num, true)
                                .setVisible(R.id.tv_video_favourite, false)
                                .setVisible(R.id.iv_video_online_region, false)
                                .setText(R.id.tv_video_play_num, "第${itemBean.index}话${itemBean.index_title}")
                                .setText(R.id.tv_tag_video_play_num, NumberUtils.format("${itemBean.play}"))
                                .setVisible(R.id.iv_tag_video_play_num, true)
                                .setVisible(R.id.tv_tag_video_play_num, true)
                                .setVisible(R.id.iv_tag_video_online_region, true)
                                .setVisible(R.id.tv_tag_video_favourite, true)
                                .setText(R.id.tv_tag_video_favourite, NumberUtils.format("${itemBean.danmaku}"))
                        Glide.with(mContext)
                                .load(itemBean.cover)
                                .centerCrop()
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .placeholder(R.drawable.bili_default_image_tv)
                                .dontAnimate()
                                .into(holder.getView<ImageView>(R.id.iv_preview))
                    }

                    1//可能是番剧 不知道参数意思
                    -> {
                        holder.setVisible(R.id.iv_avatar, false)
                                .setVisible(R.id.tv_tag, true)
                                .setVisible(R.id.tv_title, false)
                                .setVisible(R.id.tv_title_time, false)
                                .setVisible(R.id.tv_title_tag_time, true)
                                .setVisible(R.id.tv_duration, false)
                                .setText(R.id.tv_title_tag_time, TimeUtils.getFriendlyTimeSpanByNow((itemBean.ctime.times(Math.pow(10.0, 3.0))).toLong()))
                                .setText(R.id.tv_tag, "番剧")
                                .setBackgroundColor(R.id.tv_tag, AppUtils.getColor(R.color.pink_text_color))
                                .setText(R.id.tv_video_title, itemBean.title)
                                .setVisible(R.id.iv_video_play_num, false)
                                .setVisible(R.id.tv_video_play_num, true)
                                .setVisible(R.id.tv_video_favourite, false)
                                .setVisible(R.id.iv_video_online_region, false)
                                .setText(R.id.tv_video_play_num, "第${itemBean.index}话${itemBean.index_title}")
                                .setText(R.id.tv_tag_video_play_num, NumberUtils.format("${itemBean.play}"))
                                .setVisible(R.id.iv_tag_video_play_num, true)
                                .setVisible(R.id.tv_tag_video_play_num, true)
                                .setVisible(R.id.iv_tag_video_online_region, true)
                                .setVisible(R.id.tv_tag_video_favourite, true)
                                .setText(R.id.tv_tag_video_favourite, NumberUtils.format("${itemBean.danmaku}"))
                        Glide.with(mContext)
                                .load(itemBean.cover)
                                .centerCrop()
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .placeholder(R.drawable.bili_default_image_tv)
                                .dontAnimate()
                                .into(holder.getView<ImageView>(R.id.iv_preview))
                    }
                }
            }
            MulDynamic.TYPE_LV1 -> {
                mulDynamic.recent?.let {
                    holder.setVisible(R.id.iv_avatar, true)
                            .setVisible(R.id.tv_tag, false)
                            .setText(R.id.tv_title, it.name)
                            .setText(R.id.tv_title_time, TimeUtils.getFriendlyTimeSpanByNow((it.ctime.times(Math.pow(10.0, 3.0))).toLong()))
                            .setVisible(R.id.fl_recent, false)
                            .setText(R.id.tv_video_title, it.title)
                            .setText(R.id.tv_duration, FormatUtils.formatDuration("${it.duration}"))
                            .setVisible(R.id.tv_duration, true)
                            .setVisible(R.id.iv_video_play_num, true)
                            .setVisible(R.id.tv_video_play_num, true)
                            .setVisible(R.id.tv_video_favourite, true)
                            .setVisible(R.id.iv_video_online_region, true)
                            .setText(R.id.tv_video_play_num, " " + NumberUtils.format("${it.play}"))
                            .setText(R.id.tv_video_favourite, " " + NumberUtils.format("${it.favorite}"))
                            .setVisible(R.id.iv_tag_video_play_num, false)
                            .setVisible(R.id.tv_tag_video_favourite, false)
                            .setVisible(R.id.iv_tag_video_online_region, false)
                            .setText(R.id.tv_tag_video_play_num,
                                    "${it.name}${it.tag.let { "·${it.tag_name}" }}")
                    Glide.with(mContext)
                            .load(it.face)
                            .centerCrop()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .placeholder(R.drawable.bili_default_avatar)
                            .dontAnimate()
                            .into(holder.getView<CircleImageView>(R.id.iv_avatar))
                    Glide.with(mContext)
                            .load(it.cover)
                            .centerCrop()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .placeholder(R.drawable.bili_default_image_tv)
                            .dontAnimate()
                            .into(holder.getView<ImageView>(R.id.iv_preview))


                }

            }
        }
    }
}
