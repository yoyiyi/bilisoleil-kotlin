package com.yoyiyi.soleil.bean.discover

import com.chad.library.adapter.base.entity.MultiItemEntity

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/6/10 22:35
 * * 描述:
 */
class MulInterest(var itemTypez: Int = 0,
                  var interestCategroryList: List<InterestCategrory.ResultBean>? = null,
                  var community: Community.ResultBean? = null,
                  var interestAdList: InterestAd? = null) : MultiItemEntity {


    override fun getItemType(): Int = itemTypez


    companion object {
        val TYPR_HEADER = 1//
        val TYPE_BANNER = 2
        val TYPE_CATEGRORY = 3
        val TYPR_ITEM = 4
    }
}
