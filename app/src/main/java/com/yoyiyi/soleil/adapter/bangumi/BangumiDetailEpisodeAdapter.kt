package com.yoyiyi.soleil.adapter.bangumi

import android.content.Intent
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yoyiyi.soleil.R
import com.yoyiyi.soleil.bean.bangumi.BangumiDetail
import com.yoyiyi.soleil.module.app.video.VideoPlayerActivity

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/6/12 17:31
 * * 描述:
 */

class BangumiDetailEpisodeAdapter(data: List<BangumiDetail.EpisodesBean>?) : BaseQuickAdapter<BangumiDetail.EpisodesBean, BaseViewHolder>(R.layout.item_bangumi_detail_episodes, data) {

    private var mOldPos: Int = 0
    private var mNewPos: Int = 0

    override fun convert(holder: BaseViewHolder, episodesBean: BangumiDetail.EpisodesBean) {
        holder.setText(R.id.tv_index, "第${episodesBean.index}话")
        holder.setText(R.id.tv_index_title, episodesBean.index_title)
        holder.itemView.setOnClickListener {
            mNewPos = holder.adapterPosition//新位置
            mOldPos = mNewPos
            notifyDataSetChanged()
        }
        if (holder.adapterPosition == mNewPos) {
            holder.getView<View>(R.id.tv_index_title).isEnabled = true
            holder.getView<View>(R.id.tv_index).isEnabled = true
            holder.getView<View>(R.id.ll_root).isEnabled = true
        } else {
            holder.getView<View>(R.id.tv_index_title).isEnabled = false
            holder.getView<View>(R.id.tv_index).isEnabled = false
            holder.getView<View>(R.id.ll_root).isEnabled = false
        }
        if (mNewPos != mOldPos) {
            holder.getView<View>(R.id.tv_index_title).isEnabled = false
            holder.getView<View>(R.id.tv_index).isEnabled = false
            holder.getView<View>(R.id.ll_root).isEnabled = false
        }
        if (holder.adapterPosition == itemCount - 1) {
            holder.setVisible(R.id.space, true)
        } else {
            holder.setVisible(R.id.space, false)
        }
        //跳转到播放界面
        holder.itemView.setOnClickListener { mContext.startActivity(Intent(mContext, VideoPlayerActivity::class.java)) }
    }
}
