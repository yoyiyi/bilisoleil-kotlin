package com.yoyiyi.soleil.adapter.search

import android.view.View
import android.widget.ImageView

import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yoyiyi.soleil.R
import com.yoyiyi.soleil.bean.search.MulSearchArchive
import com.yoyiyi.soleil.utils.AppUtils
import com.yoyiyi.soleil.utils.NumberUtils
import com.yoyiyi.soleil.utils.SpanUtils

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/6/19 9:56
 * * 描述:
 */

class MulArchiveAdapter(data: List<MulSearchArchive>) : BaseMultiItemQuickAdapter<MulSearchArchive, BaseViewHolder>(data) {

    init {
        addItemType(MulSearchArchive.TYPE_SEASON, R.layout.layout_item_search_archive_season)
        addItemType(MulSearchArchive.TYPE_SEASON_MORE, R.layout.layout_item_search_archive_seanson_more)
        addItemType(MulSearchArchive.TYPE_MOVIE, R.layout.layout_item_search_archive_movie)
        addItemType(MulSearchArchive.TYPE_MOVIE_MORE, R.layout.layout_item_search_archive_movie_more)
        addItemType(MulSearchArchive.TYPE_ARCHIVE, R.layout.layout_item_search_archive_video)

    }

    override fun convert(holder: BaseViewHolder, mulSearchArchive: MulSearchArchive) {
        when (mulSearchArchive.itemType) {
            MulSearchArchive.TYPE_SEASON -> {
                mulSearchArchive.season?.let {

                    holder.setText(R.id.tv_video_title, it.title)
                    val des: String
                    if (it.finish == 1) {
                        des = "${it.newest_season} ·  ${it.total_count} 话全"
                    } else {
                        des = "${it.newest_season} · 更新至第 ${it.index} 话"
                    }
                    holder.setText(R.id.tv_video_des, des)
                            .setText(R.id.tv_video_newest_season, it.cat_desc)
                    Glide.with(mContext)
                            .load(it.cover)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .placeholder(R.drawable.bili_default_image_tv)
                            .dontAnimate()
                            .into(holder.getView<ImageView>(R.id.iv_video_preview))


                }


            }
            MulSearchArchive.TYPE_SEASON_MORE -> {
                val seasonCount = mulSearchArchive.seasonCount
                if (seasonCount == 0) {
                    holder.itemView.visibility = View.GONE
                } else {
                    holder.itemView.visibility = View.VISIBLE
                    holder.setText(R.id.tv_more, "更多番剧($seasonCount) >>")
                }
            }
            MulSearchArchive.TYPE_MOVIE -> {
                mulSearchArchive.movie?.let {
                    val split = it.screen_date.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                    val spanUtils = SpanUtils().append(it.title)
                            .appendSpace(10)
                            .append(split[0].substring(0, 4))
                            .setFontSize(mContext.resources.getDimension(R.dimen.text_size_12).toInt())
                            .setForegroundColor(AppUtils.getColor(R.color.font_gray))
                    holder.setText(R.id.tv_video_title, spanUtils.create())
                            .setText(R.id.tv_video_area, "地区:${it.area}")
                            .setText(R.id.tv_video_staff, it.staff)
                            .setText(R.id.tv_video_actors, it.actors)
                    Glide.with(mContext)
                            .load(it.cover)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .placeholder(R.drawable.bili_default_image_tv)
                            .dontAnimate()
                            .into(holder.getView<ImageView>(R.id.iv_video_preview))


                }
            }
            MulSearchArchive.TYPE_MOVIE_MORE -> {
                val movieCount = mulSearchArchive.movieCount
                if (movieCount == 0) {
                    holder.itemView.visibility = View.GONE
                } else {
                    holder.itemView.visibility = View.VISIBLE
                    holder.setText(R.id.tv_more, "更多影视($movieCount) >>")
                }
            }
            MulSearchArchive.TYPE_ARCHIVE -> {
                mulSearchArchive.archive?.let {
                    Glide.with(mContext)
                            .load(it.cover)
                            .centerCrop()
                            .placeholder(R.drawable.bili_default_image_tv)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .dontAnimate()
                            .into(holder.getView<View>(R.id.iv_video_preview) as ImageView)
                    holder.setText(R.id.tv_video_title, it.title)
                            .setText(R.id.tv_video_up, it.author)
                            .setText(R.id.tv_duration, it.duration)
                            .setText(R.id.tv_video_play, NumberUtils.format("${it.play}"))
                            .setText(R.id.tv_video_danmaku, NumberUtils.format("${it.danmaku}"))

                }

            }
        }

    }
}
