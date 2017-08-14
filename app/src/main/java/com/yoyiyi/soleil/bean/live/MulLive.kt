package com.yoyiyi.soleil.bean.live

import com.chad.library.adapter.base.entity.MultiItemEntity

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/6/7 11:03
 * * 描述:直播多布局
 */

class MulLive : MultiItemEntity {

    var mTitle: String? = null
    var mUrl: String? = null
    var mCount: String? = null
    var mItemType: Int = 0
    var mSpanSize: Int = 0
    var mHasMore: Boolean = false //底部
    var mBannerDataBean: LiveRecommend.RecommendDataBean.BannerDataBean? = null
    var mBannerBeanList: List<LivePartition.BannerBean>? = null

    var mRecommendLives: LiveRecommend.RecommendDataBean.LivesBean? = null
    var mPartityLives: LivePartition.PartitionsBean.LivesBean? = null


    companion object {

        val TYPR_HEADER = 1//
        val TYPE_RECOMMEND_ITEM = 2
        val TYPE_PARTY_ITEM = 3
        val TYPE_FOOTER = 4//
        val TYPE_RECOMMEND_BANNER = 9

        val TYPE_BANNER = 5//
        val TYPE_ENTRANCE = 6//

        val TWO_SPAN_SIZE = 2// 占2
        val ONE_SPAN_SIZE = 1// 占1
    }

    override fun getItemType(): Int = mItemType




}
