package com.yoyiyi.soleil.adapter.app.up

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yoyiyi.soleil.R
import com.yoyiyi.soleil.bean.user.MulUpDetail
import com.yoyiyi.soleil.utils.NumberUtils
import com.yoyiyi.soleil.utils.time.FormatUtils
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/6/17 13:23
 * * 描述:
 */

class SubmitedVideoAdapter(data: List<MulUpDetail>) : BaseMultiItemQuickAdapter<MulUpDetail, BaseViewHolder>(data) {

    init {
        addItemType(MulUpDetail.TYPE_SUBMITED_VIDEO_ELEC, R.layout.layout_item_up_submited_video_electricize)
        addItemType(MulUpDetail.TYPE_SUBMITED_VIDEO_ITEM, R.layout.layout_item_up_submited_video_item)

    }

    override fun convert(holder: BaseViewHolder, mulUpDetail: MulUpDetail) {
        when (mulUpDetail.itemType) {
            MulUpDetail.TYPE_SUBMITED_VIDEO_ELEC -> {
            }

            MulUpDetail.TYPE_SUBMITED_VIDEO_ITEM -> {
                mulUpDetail.archiveBean?.let {
                    Glide.with(mContext)
                            .load(it.cover)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .placeholder(R.drawable.bili_default_image_tv)
                            .bitmapTransform(RoundedCornersTransformation(mContext, 5, 0))
                            .dontAnimate()
                            .into(holder.getView<ImageView>(R.id.iv_video_preview))

                    holder.setText(R.id.tv_video_title, it.title)
                            .setText(R.id.tv_video_play, NumberUtils.format("${it.play}"))
                            .setText(R.id.tv_video_danmaku, NumberUtils.format("${it.danmaku}"))
                            .setText(R.id.tv_duration, FormatUtils.formatDuration("${it.duration}"))

                }

            }
        }
    }
}
