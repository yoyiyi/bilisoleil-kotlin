package com.yoyiyi.soleil.bean.live

import com.chad.library.adapter.base.entity.MultiItemEntity

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/6/7 11:03
 * * 描述:直播多布局
 */

class MulLive : MultiItemEntity {

    var title: String? = null
    var url: String? = null
    var count: Int? = null
    var itemTypez: Int = 0
    var hasMore: Boolean = false //底部
    var bannerData: LiveRecommend.RecommendData.BannerData? = null

    var bannerBeanList: List<LivePartition.Banner>? = null
    var recommendLives: List<LiveRecommend.RecommendData.Lives>? = null
    var partityLives: List<LivePartition.Partitions.Lives>? = null


    companion object {

        val TYPR_HEADER = 1//
        val TYPE_RECOMMEND_ITEM = 2
        val TYPE_PARTY_ITEM = 3
        val TYPE_FOOTER = 4//
        val TYPE_RECOMMEND_BANNER = 9

        val TYPE_BANNER = 5//
        val TYPE_ENTRANCE = 6//

    }

    override fun getItemType(): Int = itemTypez

    fun title(title: String): MulLive {
        this.title = title
        return this
    }

    fun url(url: String): MulLive {
        this.url = url
        return this
    }

    fun count(count: Int): MulLive {
        this.count = count
        return this
    }


    fun itemTypez(itemTypez: Int): MulLive {
        this.itemTypez = itemTypez
        return this
    }
    fun hasMore(hasMore: Boolean): MulLive {
        this.hasMore = hasMore
        return this
    }

    fun bannerData(bannerData: LiveRecommend.RecommendData.BannerData?): MulLive {
        this.bannerData = bannerData
        return this
    }

    fun bannerBeanList(bannerBeanList: List<LivePartition.Banner>?): MulLive {
        this.bannerBeanList = bannerBeanList
        return this
    }

    fun recommendLives(recommendLives: List<LiveRecommend.RecommendData.Lives>?): MulLive {
        this.recommendLives = recommendLives
        return this
    }

    fun partityLives(partityLives: List<LivePartition.Partitions.Lives>?): MulLive {
        this.partityLives = partityLives
        return this
    }

}
