package com.yoyiyi.soleil.adapter.home.section.chase

import android.content.Intent
import android.view.View
import com.yoyiyi.soleil.R
import com.yoyiyi.soleil.module.bangumi.BangumiIndexActivity
import com.yoyiyi.soleil.module.bangumi.BangumiScheduleActivity
import com.yoyiyi.soleil.widget.section.StatelessSection
import com.yoyiyi.soleil.widget.section.ViewHolder


/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/26 21:59
 * * 描述:
 */
class ChaseIndexSection : StatelessSection<Nothing>(R.layout.layout_item_home_chase_bangumi_index, R.layout.layout_empty) {

    override fun onBindHeaderViewHolder(holder: ViewHolder) {
        holder.getView<View>(R.id.ll_bangumi_timeline)
                .setOnClickListener({ mContext.startActivity(Intent(mContext, BangumiScheduleActivity::class.java)) })
        holder.getView<View>(R.id.ll_bangumi_index)
                .setOnClickListener({ mContext.startActivity(Intent(mContext, BangumiIndexActivity::class.java))})
    }
}
