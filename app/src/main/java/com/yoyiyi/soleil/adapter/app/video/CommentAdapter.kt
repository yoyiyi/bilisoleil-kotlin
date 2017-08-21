package com.yoyiyi.soleil.adapter.app.video

import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yoyiyi.soleil.R
import com.yoyiyi.soleil.bean.app.video.MulComment
import com.yoyiyi.soleil.utils.AppUtils
import com.yoyiyi.soleil.utils.SpanUtils
import com.yoyiyi.soleil.utils.time.TimeUtils
import com.yoyiyi.soleil.widget.CircleImageView

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/6/15 10:07
 * * 描述:
 */

class CommentAdapter(data: List<MulComment>) : BaseMultiItemQuickAdapter<MulComment, BaseViewHolder>(data) {
    init {
        addItemType(MulComment.TYPE_COMMENT_HOT_ITEM, R.layout.layout_item_video_detail_comment)
        addItemType(MulComment.TYPE_COMMENT_MORE, R.layout.layout_item_video_detail_more)
        addItemType(MulComment.TYPE_COMMENT_NOMAL_ITEM, R.layout.layout_item_video_detail_comment)

    }

    override fun convert(holder: BaseViewHolder, mulComment: MulComment) {
        when (mulComment.itemType) {

            MulComment.TYPE_COMMENT_HOT_ITEM -> {
                mulComment.hotsBean?.let {

                    holder.setText(R.id.tv_uname, SpanUtils()
                            .append(it.member.uname)
                            .setForegroundColor(AppUtils.getColor(R.color.gray_20))
                            .appendSpace(10)
                            .appendImage(getIdRes(it.member.level_info.current_level), SpanUtils.ALIGN_CENTER)
                            .create())
                            .setText(R.id.tv_like, "${it.like}")
                            .setText(R.id.tv_floor, "#${it.floor}")
                            .setText(R.id.tv_time, TimeUtils.millis2String((it.ctime * Math.pow(10.0, 3.0)).toLong()))
                            .setText(R.id.tv_message, it.content.message)
                            .setText(R.id.tv_rcount, "共有${it.rcount}条回复 >")
                    Glide.with(mContext)
                            .load(it.member.avatar)
                            .centerCrop()
                            .placeholder(R.drawable.bili_default_avatar)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .dontAnimate()
                            .into(holder.getView<CircleImageView>(R.id.iv_avatar))
                }

            }
            MulComment.TYPE_COMMENT_MORE -> {
            }

            MulComment.TYPE_COMMENT_NOMAL_ITEM -> {
                mulComment.repliesBean?.let {
                    holder.setVisible(R.id.tv_rcount, false)
                            .setText(R.id.tv_like, "${it.like}")
                            .setText(R.id.tv_uname, SpanUtils()
                                    .append(it.member.uname)
                                    .setForegroundColor(AppUtils.getColor(R.color.gray_20))
                                    .appendSpace(10)
                                    .appendImage(getIdRes(it.member.level_info.current_level), SpanUtils.ALIGN_CENTER)
                                    .create())
                            .setText(R.id.tv_floor, "#${it.floor}")
                            .setText(R.id.tv_time, com.yoyiyi.soleil.utils.time.TimeUtils.millis2String((it.ctime * Math.pow(10.0, 3.0)).toLong()))
                            .setText(R.id.tv_message, it.content.message)
                    Glide.with(mContext)
                            .load(it.member.avatar)
                            .centerCrop()
                            .placeholder(R.drawable.bili_default_avatar)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .dontAnimate()
                            .into(holder.getView<CircleImageView>(R.id.iv_avatar))
                }

            }
        }
    }

    private fun getIdRes(lv: Int): Int = when (lv) {
        1 -> R.drawable.ic_lv1
        2 -> R.drawable.ic_lv2
        3 -> R.drawable.ic_lv3
        4 -> R.drawable.ic_lv4
        5 -> R.drawable.ic_lv5
        6 -> R.drawable.ic_lv6
        7 -> R.drawable.ic_lv7
        8 -> R.drawable.ic_lv8
        9 -> R.drawable.ic_lv9
        else -> R.drawable.ic_lv0

    }
}
