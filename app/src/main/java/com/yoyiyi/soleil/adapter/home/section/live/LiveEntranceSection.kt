package com.yoyiyi.soleil.adapter.home.section.live

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.yoyiyi.soleil.R
import com.yoyiyi.soleil.adapter.home.live.LiveEntranceAdapter
import com.yoyiyi.soleil.bean.live.support.LiveEnter
import com.yoyiyi.soleil.widget.section.StatelessSection
import com.yoyiyi.soleil.widget.section.ViewHolder

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/15 16:28
 * * 描述:首页直播入口Section
 */

class LiveEntranceSection: StatelessSection<Nothing>(R.layout.layout_item_home_live_entrance, R.layout.layout_empty) {
     private var mList: List<LiveEnter>? = null

    init {
        init()
    }

    private fun init() {
        mList = arrayListOf(
                LiveEnter("关注", R.drawable.live_home_follow_anchor),
                LiveEnter("中心", R.drawable.live_home_live_center),
                LiveEnter("小视频", R.drawable.live_home_clip_video),
                LiveEnter("搜索", R.drawable.live_home_search_room),
                LiveEnter("分类", R.drawable.live_home_all_category))
    }

    override fun onBindHeaderViewHolder(holder: ViewHolder) {
        val recyclerView = holder.getView<RecyclerView>(R.id.recycler)
        recyclerView.setHasFixedSize(false)
        recyclerView.isNestedScrollingEnabled = false
        val layoutManager = GridLayoutManager(mContext, 5)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = LiveEntranceAdapter(mList)

    }


}
