package com.yoyiyi.soleil.adapter.home.live

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yoyiyi.soleil.R
import com.yoyiyi.soleil.bean.live.support.LiveEnter

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 *
 * @date 创建时间：2017/5/23 23:30
 * 描述:
 */
class LiveEntranceAdapter(data: List<LiveEnter>?) : BaseQuickAdapter<LiveEnter, BaseViewHolder>(R.layout.item_live_entrance, data) {

    override fun convert(helper: BaseViewHolder, item: LiveEnter) {
        helper.setText(R.id.tv_title, item.title)
                .setImageResource(R.id.iv_icon, item.img)
    }
}