package com.yoyiyi.soleil.adapter.home.section.region

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager

import com.yoyiyi.soleil.R
import com.yoyiyi.soleil.adapter.home.RegionActivityCenterAdapter
import com.yoyiyi.soleil.bean.region.Region
import com.yoyiyi.soleil.widget.section.StatelessSection
import com.yoyiyi.soleil.widget.section.ViewHolder

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/26 21:59
 * * 描述:
 */
class RegionActivityCenterSection(private val list: List<Region.Body>) : StatelessSection<Nothing>(R.layout.layout_item_home_region_head, R.layout.layout_item_home_region_activity_center, R.layout.layout_empty) {


    override fun onBindHeaderViewHolder(holder: ViewHolder) {
        holder.setText(R.id.tv_title, "活动中心")
                .setImageResource(R.id.iv_icon, R.drawable.ic_header_activity_center)
    }

    override fun onBindFooterViewHolder(holder: ViewHolder) {
        val recyclerView = holder.getView<RecyclerView>(R.id.recycler)
        recyclerView.setHasFixedSize(true)
        recyclerView.isNestedScrollingEnabled = false
        val layoutManager = StaggeredGridLayoutManager(1,
                StaggeredGridLayoutManager.HORIZONTAL)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = RegionActivityCenterAdapter(list)
    }
}
