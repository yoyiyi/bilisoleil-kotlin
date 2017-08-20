package com.yoyiyi.soleil.adapter.home.section.region

import android.content.Intent
import android.text.TextUtils
import android.view.animation.LinearInterpolator
import android.widget.Button
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.yoyiyi.soleil.R
import com.yoyiyi.soleil.bean.region.Region
import com.yoyiyi.soleil.module.bangumi.BangumiDetailActivity
import com.yoyiyi.soleil.module.discover.GameCenterActivity
import com.yoyiyi.soleil.utils.AppUtils
import com.yoyiyi.soleil.utils.NumberUtils
import com.yoyiyi.soleil.widget.section.StatelessSection
import com.yoyiyi.soleil.widget.section.ViewHolder
import java.util.*

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/21 11:57
 * * 描述:首页直播推荐主播section
 */
class RegionSection(private val title: String, data: List<Region.Body>) : StatelessSection<Region.Body>(R.layout.layout_item_home_region_head, R.layout.layout_item_home_region_footer, R.layout.layout_item_home_region_body, data) {
    private val mRandom: Random = Random()


    override fun onBindHeaderViewHolder(holder: ViewHolder) {
        //设置标题图片
        setTypeIcon(holder, title)
        holder.setText(R.id.tv_title, title)
    }


    override fun convert(holder: ViewHolder, bodyBean: Region.Body, position: Int) {
        Glide.with(mContext)
                .load(bodyBean.cover)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.bili_default_image_tv)
                .dontAnimate()
                .into(holder.getView<ImageView>(R.id.iv_video_preview))
        holder.setText(R.id.tv_video_title, bodyBean.title)
        holder.setText(R.id.tv_video_play_num, NumberUtils.format("${bodyBean.play}"))
        if (TextUtils.equals("番剧区", title)) {
            holder.setVisible(R.id.iv_video_online_region, false)
                    .setVisible(R.id.iv_video_online, true)
                    .setText(R.id.tv_video_favourite, NumberUtils.format("${bodyBean.favourite}"))
        } else {
            holder.setVisible(R.id.iv_video_online_region, true)
                    .setVisible(R.id.iv_video_online, false)
                    .setText(R.id.tv_video_favourite, NumberUtils.format("${bodyBean.danmaku}"))
        }
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
        holder.itemView.setOnClickListener { mContext.startActivity(Intent(mContext, BangumiDetailActivity::class.java)) }
    }

    override fun onBindFooterViewHolder(holder: ViewHolder) {
        setButtonMore(holder, title)
        if (TextUtils.equals("游戏区", title)) {
            holder.setVisible(R.id.bt_more, false)
                    .setVisible(R.id.bt_more_game, true)
                    .setVisible(R.id.bt_game_center, true)
            //跳转到游戏中心
            holder.getView<Button>(R.id.bt_game_center)
                    .setOnClickListener({ mContext.startActivity(Intent(mContext, GameCenterActivity::class.java)) })
        } else {
            holder.setVisible(R.id.bt_more, true)
                    .setVisible(R.id.bt_more_game, false)
                    .setVisible(R.id.bt_game_center, false)
        }
        holder.setText(R.id.tv_dynamic, "${mRandom.nextInt(200)}条新动态，点击这里刷新")
        holder.getView<ImageView>(R.id.iv_refresh).setOnClickListener({ view ->
            view.animate()
                    .rotation(360f)
                    .setInterpolator(LinearInterpolator())
                    .setDuration(1000).start()
        })
        holder.getView<ImageView>(R.id.iv_refresh).setOnClickListener({ view ->
            view.animate()
                    .rotation(360f)
                    .setInterpolator(LinearInterpolator())
                    .setDuration(1000).start()
        })
    }

    private fun setTypeIcon(holder: ViewHolder, title: String) {
        when (title) {
            "番剧区" -> holder.setImageResource(R.id.iv_icon, R.mipmap.ic_category_t13)
            "动画区" -> holder.setImageResource(R.id.iv_icon, R.mipmap.ic_category_t1)
            "国创区" -> holder.setImageResource(R.id.iv_icon, R.mipmap.ic_category_t167)
            "音乐区" -> holder.setImageResource(R.id.iv_icon, R.mipmap.ic_category_t3)
            "舞蹈区" -> holder.setImageResource(R.id.iv_icon, R.mipmap.ic_category_t129)
            "游戏区" -> holder.setImageResource(R.id.iv_icon, R.mipmap.ic_category_t4)
            "科技区" -> holder.setImageResource(R.id.iv_icon, R.mipmap.ic_category_t36)
            "生活区" -> holder.setImageResource(R.id.iv_icon, R.mipmap.ic_category_t160)
            "鬼畜区" -> holder.setImageResource(R.id.iv_icon, R.mipmap.ic_category_t13)
            "时尚区" -> holder.setImageResource(R.id.iv_icon, R.mipmap.ic_category_t155)
            "广告区" -> holder.setImageResource(R.id.iv_icon, R.mipmap.ic_category_t165)
            "娱乐区" -> holder.setImageResource(R.id.iv_icon, R.mipmap.ic_category_t5)
            "电影区" -> holder.setImageResource(R.id.iv_icon, R.mipmap.ic_category_t23)
            "电视剧区" -> holder.setImageResource(R.id.iv_icon, R.mipmap.ic_category_t11)
        }
    }

    private fun setButtonMore(holder: ViewHolder, title: String) {
        when (title) {
            "番剧区" -> holder.setText(R.id.bt_more, "更多番剧")
            "动画区" -> holder.setText(R.id.bt_more, "更多动画")
            "国创区" -> holder.setText(R.id.bt_more, "更多国创")
            "音乐区" -> holder.setText(R.id.bt_more, "更多音乐")
            "舞蹈区" -> holder.setText(R.id.bt_more, "更多舞蹈")
            "游戏区" -> holder.setText(R.id.bt_more_game, "更多游戏")
            "科技区" -> holder.setText(R.id.bt_more, "更多科技")
            "生活区" -> holder.setText(R.id.bt_more, "更多生活")
            "鬼畜区" -> holder.setText(R.id.bt_more, "更多鬼畜")
            "时尚区" -> holder.setText(R.id.bt_more, "更多时尚")
            "广告区" -> holder.setText(R.id.bt_more, "更多广告")
            "娱乐区" -> holder.setText(R.id.bt_more, "更多娱乐")
            "电影区" -> holder.setText(R.id.bt_more, "更多电影")
            "电视剧区" -> holder.setText(R.id.bt_more, "更多电视剧")
        }
    }
}
