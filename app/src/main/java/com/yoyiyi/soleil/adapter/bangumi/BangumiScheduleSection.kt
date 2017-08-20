package com.yoyiyi.soleil.adapter.bangumi

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Build
import android.support.annotation.RequiresApi
import android.text.TextUtils
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.yoyiyi.soleil.R
import com.yoyiyi.soleil.bean.bangumi.BangumiSchedule
import com.yoyiyi.soleil.module.bangumi.BangumiDetailActivity
import com.yoyiyi.soleil.utils.AppUtils
import com.yoyiyi.soleil.widget.section.StatelessSection
import com.yoyiyi.soleil.widget.section.ViewHolder
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/6/8 17:45
 * * 描述:
 */

class BangumiScheduleSection(private val mWeek: String, list: List<BangumiSchedule>, private val mDate: String) : StatelessSection<BangumiSchedule>(R.layout.layout_item_bangumi_schedule_head, R.layout.layout_item_bangumi_schedule_body, list) {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    override fun onBindHeaderViewHolder(holder: ViewHolder) {
        when (mWeek) {
            "周一" -> setWeekData(holder, R.drawable.bangumi_timeline_weekday_1)
            "周二" -> setWeekData(holder, R.drawable.bangumi_timeline_weekday_2)
            "周三" -> setWeekData(holder, R.drawable.bangumi_timeline_weekday_3)
            "周四" -> setWeekData(holder, R.drawable.bangumi_timeline_weekday_4)
            "周五" -> setWeekData(holder, R.drawable.bangumi_timeline_weekday_5)
            "周六" -> setWeekData(holder, R.drawable.bangumi_timeline_weekday_6)
            "周日" -> setWeekData(holder, R.drawable.bangumi_timeline_weekday_7)
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private fun setWeekData(holder: ViewHolder, iconRes: Int) {
        val nowDate = com.yoyiyi.soleil.utils.time.TimeUtils.getNowDate()
        val date2String = com.yoyiyi.soleil.utils.time.TimeUtils.date2String(nowDate, SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()))
        if (TextUtils.equals(mDate, date2String /*TimeUtils.formatDate(TimeUtils.getCurrentTime("yyyy-MM-dd")))*/)) {
            holder.setText(R.id.tv_date, "今天")
                    .setTextColor(R.id.tv_date, AppUtils.getColor(R.color.colorPrimary))
                    .setTextColor(R.id.tv_title, AppUtils.getColor(R.color.colorPrimary))
            val icon = holder.getView<ImageView>(R.id.iv_icon)
            icon.imageTintList = ColorStateList.valueOf(AppUtils.getColor(R.color.colorPrimary))
        } else {
            holder.setText(R.id.tv_date, mDate)
                    .setTextColor(R.id.tv_date, AppUtils.getColor(R.color.black_alpha_30))
                    .setTextColor(R.id.tv_title, AppUtils.getColor(R.color.gray_80))
            val icon = holder.getView<ImageView>(R.id.iv_icon)
            icon.imageTintList = ColorStateList.valueOf(AppUtils.getColor(R.color.gray_80))
        }
        holder.setImageResource(R.id.iv_icon, iconRes)
                .setText(R.id.tv_title, mWeek)
    }

    override fun convert(holder: ViewHolder, schedule: BangumiSchedule, position: Int) {
        Glide.with(mContext)
                .load(schedule.cover)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.bili_default_image_tv)
                .dontAnimate()
                .into(holder.getView<ImageView>(R.id.iv_video_preview))
        holder.setText(R.id.tv_video_title, schedule.title)
                .setText(R.id.tv_video_time, schedule.ontime)
                .setText(R.id.tv_video_update, "第 ${schedule.ep_index} 话")
        holder.itemView.setOnClickListener { mContext.startActivity(Intent(mContext, BangumiDetailActivity::class.java)) }

    }
}
