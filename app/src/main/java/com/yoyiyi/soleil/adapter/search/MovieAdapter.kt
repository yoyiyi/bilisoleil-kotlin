package com.yoyiyi.soleil.adapter.search

import android.text.TextUtils
import android.widget.ImageView

import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yoyiyi.soleil.R
import com.yoyiyi.soleil.bean.search.Movie
import com.yoyiyi.soleil.utils.AppUtils
import com.yoyiyi.soleil.utils.SpanUtils

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/6/19 20:49
 * * 描述:
 */
class MovieAdapter(data: List<Movie.DataBean.ItemsBean>?) : BaseQuickAdapter<Movie.DataBean.ItemsBean, BaseViewHolder>(R.layout.item_search_movie, data) {

    override fun convert(holder: BaseViewHolder, itemsBean: Movie.DataBean.ItemsBean) {
        if (!TextUtils.isEmpty(itemsBean.screen_date)) {
            val split = itemsBean.screen_date.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val spanUtils = SpanUtils().append(itemsBean.title)
                    .appendSpace(10)
                    .append(split[0].substring(0, 4))
                    .setFontSize(mContext.resources.getDimension(R.dimen.text_size_12).toInt())
                    .setForegroundColor(AppUtils.getColor(R.color.font_gray))
            holder.setText(R.id.tv_video_title, spanUtils.create())
        } else {
            holder.setText(R.id.tv_video_title, itemsBean.title)
        }
        holder.setText(R.id.tv_video_area, if (itemsBean.area.isEmpty()) "" else "地区:${itemsBean.area}")
                .setText(R.id.tv_video_staff, itemsBean.staff)
                .setText(R.id.tv_video_actors, itemsBean.actors)
        Glide.with(mContext)
                .load(itemsBean.cover)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.bili_default_image_tv)
                .dontAnimate()
                .into(holder.getView<ImageView>(R.id.iv_video_preview))
    }
}
