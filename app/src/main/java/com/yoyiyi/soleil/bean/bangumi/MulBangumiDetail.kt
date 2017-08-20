package com.yoyiyi.soleil.bean.bangumi

import com.chad.library.adapter.base.entity.MultiItemEntity

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/6/11 21:52
 * * 描述:番剧详情
 */
class MulBangumiDetail(var itemTypez: Int = 0,
                       var hotsBean: BangumiDetailComment.DataBean.HotsBean?=null,
                       var repliesBean: BangumiDetailComment.DataBean.RepliesBean?=null,
                       var isPrepare: Boolean = false,
                       var bangumiRecommendList: List<BangumiDetailRecommend.ListBean>?=null,
                       var evaluate: String?=null,
                       var tagsBeanList: List<BangumiDetail.TagsBean>?=null,
                       var totalCount: String?=null,
                       var playCount: String?=null,
                       var favorites: String?=null,
                       var isFinish: String?=null,
                       var cover: String?=null,
                       var episodesBeans: List<BangumiDetail.EpisodesBean>?=null,
                       var seasonsBeanList: List<BangumiDetail.SeasonsBean>?=null,
                       var seasonsTitle: String?=null,
                       var listBeanList: List<BangumiDetail.RankBean.ListBean>?=null,
                       var num: Int = 0,
                       var account: Int = 0,
                       var totalBpCount: Int = 0,
                       var weekBpCount: Int = 0
) : MultiItemEntity {

    override fun getItemType(): Int = itemTypez


    companion object {
        val TYPE_HEAD = 1

        val TYPE_SEASON = 2

        val TYPE_EPISODE_ITEM = 3

        val TYPE_EPISODE_HEAD = 4

        val TYPE_CONTRACTED = 5

        val TYPE_DES = 6

        val TYPE_RECOMMEND_HEAD = 7

        val TYPE_RECOMMEND_ITEM = 8

        val TYPE_COMMENT_HEAD = 9

        val TYPE_COMMENT_HOT_ITEM = 10

        val TYPE_COMMENT_MORE = 11

        val TYPE_COMMENT_NOMAL_ITEM = 12
    }

}
