package com.yoyiyi.soleil.adapter.home.section.live

import android.view.animation.LinearInterpolator
import android.widget.Button
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.yoyiyi.soleil.R
import com.yoyiyi.soleil.bean.live.LivePartition
import com.yoyiyi.soleil.utils.AppUtils
import com.yoyiyi.soleil.utils.NumberUtils
import com.yoyiyi.soleil.utils.SpanUtils
import com.yoyiyi.soleil.widget.section.StatelessSection
import com.yoyiyi.soleil.widget.section.ViewHolder
import java.util.*

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/21 11:57
 * * 描述:首页直播分区ection
 */
class LiveRecommendPartitionSection(private val mTitle: String,
                                    private val mUrl: String,
                                    private val mCount: String,
                                    data: List<LivePartition.Partitions.Lives>) : StatelessSection<LivePartition.Partitions.Lives>(R.layout.layout_item_home_live_head, R.layout.layout_item_home_live_footer, R.layout.layout_item_home_live_body, data) {
    private val mRandom: Random = Random()
    private var mhasMore = false

    constructor(hasMore: Boolean, title: String, url: String, count: String,
                data: List<LivePartition.Partitions.Lives>) : this(title, url, count, data) {
        this.mhasMore = hasMore
    }

    override fun onBindHeaderViewHolder(holder: ViewHolder) {
        Glide.with(mContext)
                .load(mUrl)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
                .into(holder.getView<ImageView>(R.id.iv_icon))
        holder.setText(R.id.tv_title, mTitle)
        holder.setText(R.id.tv_online, SpanUtils()
                .append("当前")
                .append("$mCount")
                .setForegroundColor(AppUtils.getColor(R.color.pink_text_color))
                .append("个直播")
                .create())


    }

    override fun convert(holder: ViewHolder, livesBean: LivePartition.Partitions.Lives, position: Int) {
        Glide.with(mContext)
                .load(livesBean.cover?.src)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.bili_default_image_tv)
                .dontAnimate()
                .into(holder.getView<ImageView>(R.id.iv_video_preview) as ImageView)
        holder.setText(R.id.tv_video_live_up, livesBean.owner?.name)//up
                .setText(R.id.tv_video_online, NumberUtils.format("${livesBean.online}"))//在线人数;
        holder.setText(R.id.tv_video_title, livesBean.title)
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
    }

    override fun onBindFooterViewHolder(holder: ViewHolder) {
        if (mhasMore) {
            holder.setVisible(R.id.bt_more_live, true)
        } else {
            holder.setVisible(R.id.bt_more_live, false)
            holder.getView<Button>(R.id.bt_more_live).setOnClickListener({ view ->

            })

        }
        holder.setText(R.id.tv_dynamic, (mRandom.nextInt(200).toString() + "条新动态，点击这里刷新").toString())
        holder.getView<Button>(R.id.iv_refresh).setOnClickListener({ view ->
            view.animate()
                    .rotation(360f)
                    .setInterpolator(LinearInterpolator())
                    .setDuration(1000).start()
        })
    }
}
