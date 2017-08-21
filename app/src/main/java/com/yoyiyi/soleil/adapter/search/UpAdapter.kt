package com.yoyiyi.soleil.adapter.search

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yoyiyi.soleil.R
import com.yoyiyi.soleil.bean.search.Up
import com.yoyiyi.soleil.utils.NumberUtils

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/6/19 20:45
 * * 描述:
 */
class UpAdapter(data: List<Up.DataBean.ItemsBean>?) : BaseQuickAdapter<Up.DataBean.ItemsBean, BaseViewHolder>(R.layout.item_search_up, data) {

    override fun convert(holder: BaseViewHolder, item: Up.DataBean.ItemsBean) {
        holder.setText(R.id.tv_uname, item.title)
                .setText(R.id.tv_des, if (item.sign.isEmpty()) item.sign else "")
                .setText(R.id.tv_fans, "粉丝数: ${NumberUtils.format("${item.fans}")}")
                .setText(R.id.tv_videos, "视频数: ${NumberUtils.format("${item.archives}")}")
        Glide.with(mContext)
                .load(item.cover)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.bili_default_avatar)
                .dontAnimate()
                .into(holder.getView<ImageView>(R.id.iv_avatar))

    }
}
