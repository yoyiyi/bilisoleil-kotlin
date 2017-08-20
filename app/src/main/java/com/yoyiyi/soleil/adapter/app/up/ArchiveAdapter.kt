package com.yoyiyi.soleil.adapter.app.up

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yoyiyi.soleil.R
import com.yoyiyi.soleil.bean.user.MulUpDetail
import com.yoyiyi.soleil.utils.AppUtils
import com.yoyiyi.soleil.utils.NumberUtils
import com.yoyiyi.soleil.utils.SpanUtils

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/6/17 16:06
 * * 描述:
 */

class ArchiveAdapter(data: List<MulUpDetail>) : BaseMultiItemQuickAdapter<MulUpDetail, BaseViewHolder>(data) {
    init {
        addItemType(MulUpDetail.TYPE_ARCHIVE_LIVE, R.layout.layout_item_up_detail_archive_live)
        addItemType(MulUpDetail.TYPE_ARCHIVE_HEAD, R.layout.layout_item_up_detail_archive_head)
        addItemType(MulUpDetail.TYPE_ARCHIVE_ALL_SUBMIT_VIDEO, R.layout.layout_item_up_detail_archive_submited_video_item)
        addItemType(MulUpDetail.TYPE_ARCHIVE_FAVOURITE, R.layout.layout_item_up_detail_archive_favourite)

    }


    override fun convert(holder: BaseViewHolder, mulUpDetail: MulUpDetail) {
        when (mulUpDetail.itemType) {
            MulUpDetail.TYPE_ARCHIVE_LIVE -> holder.setText(R.id.tv_live_state, "正在轮播: ${mulUpDetail.live?.title}")
            MulUpDetail.TYPE_ARCHIVE_HEAD -> {
                val span = SpanUtils()
                if (mulUpDetail.state == 0) {
                    span.append("${mulUpDetail.title}")
                            .appendSpace(10)
                            .append("${mulUpDetail.count}")
                            .setForegroundColor(AppUtils.getColor(R.color.font_gray))
                            .appendSpace(10)
                            .appendImage(R.drawable.ic_invisible)
                            .append("未公开")
                            .setForegroundColor(AppUtils.getColor(R.color.font_gray))
                } else {
                    span.append("${mulUpDetail.title}")
                            .append("${mulUpDetail.count}")
                            .setForegroundColor(AppUtils.getColor(R.color.font_gray))
                }
                holder.setText(R.id.tv_title, span.create())
                holder.setVisible(R.id.tv_more, mulUpDetail.count != 0)
            }

            MulUpDetail.TYPE_ARCHIVE_ALL_SUBMIT_VIDEO//全部投稿
            -> {
                mulUpDetail.archiveBean?.let {
                    holder.setText(R.id.tv_video_title, it.title)
                            .setText(R.id.tv_video_play_num, NumberUtils.format("${it.play}"))
                            .setText(R.id.tv_video_favourite, NumberUtils.format("${it.danmaku}"))
                    Glide.with(mContext)
                            .load(it.cover)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .placeholder(R.drawable.bili_default_image_tv)
                            .dontAnimate()
                            .into(holder.getView<ImageView>(R.id.iv_video_preview))

                    if (mulUpDetail.position % 2 == 0) {
                        val params = holder.itemView.layoutParams as ViewGroup.MarginLayoutParams
                        params.setMargins(
                                mContext.resources.getDimension(R.dimen.dp10).toInt(),
                                mContext.resources.getDimension(R.dimen.dp10).toInt(),
                                mContext.resources.getDimension(R.dimen.dp5).toInt(),
                                mContext.resources.getDimension(R.dimen.dp10).toInt())
                        holder.itemView.layoutParams = params
                    } else {
                        val params = holder.itemView.layoutParams as ViewGroup.MarginLayoutParams
                        params.setMargins(
                                mContext.resources.getDimension(R.dimen.dp5).toInt(),
                                mContext.resources.getDimension(R.dimen.dp10).toInt(),
                                mContext.resources.getDimension(R.dimen.dp10).toInt(),
                                mContext.resources.getDimension(R.dimen.dp10).toInt())
                        holder.itemView.layoutParams = params
                    }


                }

            }

            MulUpDetail.TYPE_ARCHIVE_FAVOURITE -> {
                val recyclerView = holder.getView<RecyclerView>(R.id.recycler)
                recyclerView.setHasFixedSize(true)
                val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
                recyclerView.layoutManager = layoutManager
                mulUpDetail.favourite?.let {
                    recyclerView.adapter = ArchiveFavouriteAdapter(it.item)
                }
            }
        }

    }
}
