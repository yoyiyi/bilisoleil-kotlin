package com.yoyiyi.soleil.adapter.app.up

import android.widget.ImageView

import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yoyiyi.soleil.R
import com.yoyiyi.soleil.bean.user.MulUpDetail

import jp.wasabeef.glide.transformations.RoundedCornersTransformation

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/6/17 16:06
 * * 描述:
 */

class FavouriteAdapter(data: List<MulUpDetail>) : BaseMultiItemQuickAdapter<MulUpDetail, BaseViewHolder>(data) {

    init {
        addItemType(MulUpDetail.TYPE_FAVOURITE_ITEM, R.layout.item_up_detail_favourite)
    }


    override fun convert(holder: BaseViewHolder, mulUpDetail: MulUpDetail) {
        when (mulUpDetail.itemType) {
            MulUpDetail.TYPE_FAVOURITE_ITEM -> {
                mulUpDetail.favouriteBean?.let {

                    Glide.with(mContext)
                            .load<Any>(it.cover[0].pic)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .placeholder(R.drawable.bili_default_image_tv)
                            .bitmapTransform(RoundedCornersTransformation(mContext, 5, 0))
                            .dontAnimate()
                            .into(holder.getView<ImageView>(R.id.iv_video_preview))

                    holder.setText(R.id.tv_video_title, it.name)
                            .setText(R.id.tv_favourite_count, "${it.cur_count}")
                            .setText(R.id.tv_video_state, if (it.state == 2) "公开 · ${it.cur_count} 个内容" else "私密")

                }


            }
        }

    }
}
