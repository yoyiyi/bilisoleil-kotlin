package com.yoyiyi.soleil.adapter.bangumi

import android.text.TextUtils
import android.view.View

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yoyiyi.soleil.R
import com.yoyiyi.soleil.bean.bangumi.BangumiDetail

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/6/12 17:31
 * * 描述:分季
 */

class BangumiDetailSeasonAdapter(data: List<BangumiDetail.SeasonsBean>?, private val mSeasonTitle: String) : BaseQuickAdapter<BangumiDetail.SeasonsBean, BaseViewHolder>(R.layout.item_bangumi_detail_seasons, data) {

    private var mOldPos: Int = 0
    private var mNewPos = -1

    private var mFlag = true

    override fun convert(holder: BaseViewHolder, seasonsBean: BangumiDetail.SeasonsBean) {
        holder.setText(R.id.tv_index, seasonsBean.title)
        if (mFlag) {
            if (TextUtils.equals(seasonsBean.title, mSeasonTitle)) {
                mNewPos = holder.adapterPosition
                mOldPos = mNewPos
                mFlag = false
            }
        }
        holder.itemView.setOnClickListener {
            mNewPos = holder.adapterPosition//新位置
            mOldPos = mNewPos
            notifyDataSetChanged()
        }
        if (holder.adapterPosition == mNewPos) {
            holder.getView<View>(R.id.tv_index).isEnabled = true
            holder.getView<View>(R.id.ll_root).isEnabled = true
        } else {
            holder.getView<View>(R.id.tv_index).isEnabled = false
            holder.getView<View>(R.id.ll_root).isEnabled = false
        }
        if (mNewPos != mOldPos) {
            holder.getView<View>(R.id.tv_index).isEnabled = false
            holder.getView<View>(R.id.ll_root).isEnabled = false
        }
        if (holder.adapterPosition == itemCount - 1) {
            holder.setVisible(R.id.space, true)
        } else {
            holder.setVisible(R.id.space, false)
        }
    }


}
