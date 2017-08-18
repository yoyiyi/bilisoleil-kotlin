package com.yoyiyi.soleil.adapter.home.section.chase

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView

import com.yoyiyi.soleil.R
import com.yoyiyi.soleil.adapter.home.ChaseFllowAdapter
import com.yoyiyi.soleil.bean.chase.ChaseBangumi
import com.yoyiyi.soleil.utils.AppUtils
import com.yoyiyi.soleil.utils.SpanUtils
import com.yoyiyi.soleil.widget.section.StatelessSection
import com.yoyiyi.soleil.widget.section.ViewHolder

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/21 11:57
 * * 描述:首页直播推荐主播section
 */
class ChaseFollowSection(private val count: String, private val mList: List<ChaseBangumi.Follows>) : StatelessSection<Nothing>(R.layout.layout_item_home_chase_head, R.layout.layout_item_home_chase_body) {


    override fun onBindHeaderViewHolder(holder: ViewHolder) {
        //设置标题图片
        holder.setText(R.id.tv_title, "我的追番")
                .setImageResource(R.id.iv_icon, R.drawable.bangumi_follow_home_ic_mine)
        if ("0" == count) {
            holder.setText(R.id.tv_more, "查看更多")
        } else {
            holder.setText(R.id.tv_more, SpanUtils()
                    .append("最近更新 ")
                    .append(count)
                    .setForegroundColor(AppUtils.getColor(R.color.pink_text_color)).create())
        }
    }

    override fun onBindItemViewHolder(holder: ViewHolder, position: Int) {
        val recyclerView = holder.getView<RecyclerView>(R.id.recycler)
        recyclerView.setHasFixedSize(true)
        recyclerView.isNestedScrollingEnabled = false
        val layoutManager = GridLayoutManager(mContext, 3)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = ChaseFllowAdapter(mList)
    }

}
