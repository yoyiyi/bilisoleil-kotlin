package com.yoyiyi.soleil.adapter.discover

import android.widget.ImageView

import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yoyiyi.soleil.R
import com.yoyiyi.soleil.bean.discover.InterestCategrory

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/6/6 12:57
 * * 描述:
 */

class IntersetCategroyAdapter(data: List<InterestCategrory.ResultBean>?) : BaseQuickAdapter<InterestCategrory.ResultBean, BaseViewHolder>(R.layout.item_interest_category, data) {

    override fun convert(holder: BaseViewHolder, interestCategrory: InterestCategrory.ResultBean) {
        Glide.with(mContext)
                .load(interestCategrory.avatar)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.bili_default_image_tv)
                .dontAnimate()
                .into(holder.getView<ImageView>(R.id.iv_icon))
        holder.setText(R.id.tv_title, interestCategrory.name)
    }
}
