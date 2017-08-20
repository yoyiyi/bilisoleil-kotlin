package com.yoyiyi.soleil.adapter.discover.section

import android.content.Intent
import android.view.View
import android.widget.ImageView

import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.yoyiyi.soleil.R
import com.yoyiyi.soleil.bean.discover.GameCenter
import com.yoyiyi.soleil.module.discover.AllGameActivity
import com.yoyiyi.soleil.widget.section.StatelessSection
import com.yoyiyi.soleil.widget.section.ViewHolder

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/30 21:44
 * * 描述:精品推荐游戏
 */
class GameCenterGameListSection(private val mFlag: Boolean, data: List<GameCenter.GameListBean>) : StatelessSection<GameCenter.GameListBean>(R.layout.layout_item_game_center_head, R.layout.layout_item_game_center_footer, R.layout.layout_item_game_center_body, data) {

    override fun convert(holder: ViewHolder, gameListBean: GameCenter.GameListBean, position: Int) {
        Glide.with(mContext)
                .load(gameListBean.icon)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
                .into(holder.getView<View>(R.id.iv_preview) as ImageView)
        holder.setText(R.id.tv_title, gameListBean.title)
                .setText(R.id.tv_des, gameListBean.summary)
        if (gameListBean.hot == 1) {
            holder.setImageResource(R.id.iv_state, R.drawable.ic_game_center_hot)
        } else if (gameListBean.new == 1) {
            holder.setImageResource(R.id.iv_state, R.drawable.ic_game_center_new)
        } else {
            holder.setImageResource(R.id.iv_state, R.color.transparent)
        }
    }

    override fun onBindHeaderViewHolder(holder: ViewHolder) {
        if (mFlag) {
            holder.setText(R.id.tv_title, "精品推荐游戏")
        } else {
            gone(holder.itemView)
        }
    }

    override fun onBindFooterViewHolder(holder: ViewHolder) {
        if (mFlag) {
            holder.itemView.visibility = View.VISIBLE
            holder.itemView.setOnClickListener { mContext.startActivity(Intent(mContext, AllGameActivity::class.java)) }
        } else {
            gone(holder.itemView)
        }
    }
}
