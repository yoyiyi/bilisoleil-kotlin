package com.yoyiyi.soleil.adapter.app.up

import android.widget.ImageView

import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yoyiyi.soleil.R
import com.yoyiyi.soleil.bean.user.UpDetail
import com.yoyiyi.soleil.utils.EmptyUtils

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/6/17 19:24
 * * 描述:
 */

class ArchiveFavouriteAdapter(data: List<UpDetail.DataBean.FavouriteBean.ItemBeanX>?) : BaseQuickAdapter<UpDetail.DataBean.FavouriteBean.ItemBeanX, BaseViewHolder>(R.layout.item_up_detail_archive_favourite, data) {

    override fun convert(holder: BaseViewHolder, item: UpDetail.DataBean.FavouriteBean.ItemBeanX) {
        val cover = item.cover
        val coverId = intArrayOf(R.id.iv_view1, R.id.iv_view2, R.id.iv_view3)
        if (EmptyUtils.isNotEmpty(cover)) {
            for (i in cover.indices) {
                Glide.with(mContext)
                        .load(cover[i].pic)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .dontAnimate()
                        .into(holder.getView<ImageView>(coverId[i]))
            }
            holder.setText(R.id.tv_favourite_title, item.name)
                    .setText(R.id.tv_favourite_count, "${item.cur_count}")
            if (holder.adapterPosition == itemCount - 1) {
                holder.setVisible(R.id.space, true)
            } else {
                holder.setVisible(R.id.space, false)
            }
        }
    }
}
