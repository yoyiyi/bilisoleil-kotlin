package com.yoyiyi.soleil.adapter.home.section.chase

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ImageView

import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.yoyiyi.soleil.R
import com.yoyiyi.soleil.adapter.home.ChaseRecommendCNAdapter
import com.yoyiyi.soleil.bean.chase.RecommendBangumi
import com.yoyiyi.soleil.widget.section.StatelessSection
import com.yoyiyi.soleil.widget.section.ViewHolder

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/26 21:59
 * * 描述:
 */
class ChaseRecommendCNSection(private val list: List<RecommendBangumi.RecommendCn.Recommend>, private val foot: RecommendBangumi.RecommendCn.Foot) : StatelessSection<Nothing>(R.layout.layout_item_home_chase_head, R.layout.layout_item_home_chase_footer, R.layout.layout_item_home_chase_body) {


    override fun onBindHeaderViewHolder(holder: ViewHolder) {
        holder.setText(R.id.tv_title, "国产动画推荐")
                .setImageResource(R.id.iv_icon, R.drawable.bangumi_follow_home_ic_domestic)

    }

    override fun onBindItemViewHolder(holder: ViewHolder, position: Int) {
        val recyclerView = holder.getView<RecyclerView>(R.id.recycler)
        recyclerView.setHasFixedSize(true)
        recyclerView.isNestedScrollingEnabled = false
        val layoutManager = GridLayoutManager(mContext, 3)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = ChaseRecommendCNAdapter(list)


    }

    override fun onBindFooterViewHolder(holder: ViewHolder) {
        Glide.with(mContext)
                .load(foot.cover)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.bili_default_image_tv)
                .dontAnimate()
                .into(holder.getView<ImageView>(R.id.iv_video_preview))
        holder.setText(R.id.tv_title, foot.title)
                .setText(R.id.tv_des, foot.desc)
        if (foot.is_new == 1) {
            holder.setVisible(R.id.tv_new_tag, true)
        } else {
            holder.setVisible(R.id.tv_new_tag, false)
        }
    }
}
