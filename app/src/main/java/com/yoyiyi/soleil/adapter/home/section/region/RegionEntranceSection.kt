package com.yoyiyi.soleil.adapter.home.section.region

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView

import com.yoyiyi.soleil.R
import com.yoyiyi.soleil.adapter.home.RegionEntranceAdapter
import com.yoyiyi.soleil.bean.region.RegionEnter
import com.yoyiyi.soleil.bean.region.RegionTagType
import com.yoyiyi.soleil.widget.section.StatelessSection
import com.yoyiyi.soleil.widget.section.ViewHolder

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/26 21:59
 * * 描述:
 */
class RegionEntranceSection(private val regionTypeList: List<RegionTagType>) : StatelessSection<Nothing>(R.layout.layout_item_home_region_entrance, R.layout.layout_empty) {

    private var mList: List<RegionEnter>? = null

    init {
        init()
    }

    private fun init() {
        mList = arrayListOf(
                RegionEnter("直播", R.mipmap.ic_category_live),
                RegionEnter("番剧", R.mipmap.ic_category_t13),
                RegionEnter("动画", R.mipmap.ic_category_t1),
                RegionEnter("国创", R.mipmap.ic_category_t167),
                RegionEnter("音乐", R.mipmap.ic_category_t3),
                RegionEnter("舞蹈", R.mipmap.ic_category_t129),
                RegionEnter("游戏", R.mipmap.ic_category_t4),
                RegionEnter("科技", R.mipmap.ic_category_t36),
                RegionEnter("生活", R.mipmap.ic_category_t160),
                RegionEnter("鬼畜", R.mipmap.ic_category_t11),
                RegionEnter("时尚", R.mipmap.ic_category_t155),
                RegionEnter("广告", R.mipmap.ic_category_t165),
                RegionEnter("娱乐", R.mipmap.ic_category_t5),
                RegionEnter("电影", R.mipmap.ic_category_t23),
                RegionEnter("电视剧", R.mipmap.ic_category_t11),
                RegionEnter("游戏中心", R.mipmap.ic_category_game_center))
    }

    override fun onBindHeaderViewHolder(holder: ViewHolder) {
        val recyclerView = holder.getView<RecyclerView>(R.id.recycler)
        recyclerView.setHasFixedSize(false)
        recyclerView.isNestedScrollingEnabled = false
        val layoutManager = GridLayoutManager(mContext, 4)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = RegionEntranceAdapter(mList, regionTypeList)
    }
}
