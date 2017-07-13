package com.yoyiyi.soleil.network.helper


import com.yoyiyi.soleil.bean.app.Splash
import com.yoyiyi.soleil.bean.app.video.VideoDetail
import com.yoyiyi.soleil.bean.app.video.VideoDetailComment
import com.yoyiyi.soleil.bean.app.video.VideoPlayer
import com.yoyiyi.soleil.bean.bangumi.BangumiDetail
import com.yoyiyi.soleil.bean.bangumi.BangumiDetailComment
import com.yoyiyi.soleil.bean.bangumi.BangumiDetailRecommend
import com.yoyiyi.soleil.bean.bangumi.BangumiIndex
import com.yoyiyi.soleil.bean.bangumi.BangumiSchedule
import com.yoyiyi.soleil.bean.chase.ChaseBangumi
import com.yoyiyi.soleil.bean.chase.RecommendBangumi
import com.yoyiyi.soleil.bean.discover.ActivityCenter
import com.yoyiyi.soleil.bean.discover.Community
import com.yoyiyi.soleil.bean.discover.HotSearchTag
import com.yoyiyi.soleil.bean.discover.InterestAd
import com.yoyiyi.soleil.bean.discover.InterestCategrory
import com.yoyiyi.soleil.bean.discover.TopicCenter
import com.yoyiyi.soleil.bean.live.LiveEntrance
import com.yoyiyi.soleil.bean.live.LivePartition
import com.yoyiyi.soleil.bean.live.LiveRecommend
import com.yoyiyi.soleil.bean.recommend.AllStationRank
import com.yoyiyi.soleil.bean.recommend.Recommend
import com.yoyiyi.soleil.bean.region.AllRegionRank
import com.yoyiyi.soleil.bean.region.Region
import com.yoyiyi.soleil.bean.region.RegionRecommend
import com.yoyiyi.soleil.bean.region.RegionType
import com.yoyiyi.soleil.bean.search.Movie
import com.yoyiyi.soleil.bean.search.Search
import com.yoyiyi.soleil.bean.search.SearchArchive
import com.yoyiyi.soleil.bean.search.Season
import com.yoyiyi.soleil.bean.search.Up
import com.yoyiyi.soleil.bean.user.UpDetail
import com.yoyiyi.soleil.network.api.ApiService
import com.yoyiyi.soleil.network.api.AppService
import com.yoyiyi.soleil.network.api.BangumiService
import com.yoyiyi.soleil.network.api.Im9Service
import com.yoyiyi.soleil.network.api.LiveService
import com.yoyiyi.soleil.network.api.RankService
import com.yoyiyi.soleil.network.response.HttpResponse

import io.reactivex.Flowable


/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/10 17:57
 * * 描述:RetrofitHelper 帮助类
 */

class RetrofitHelper(private val mAppService: AppService, private val mLiveService: LiveService, private val mBangumiService: BangumiService, private val mRankService: RankService, private val mApiService: ApiService, private val mIm9Service: Im9Service) {


    /*******************************AppApi */
    val splash: Flowable<Splash>
        get() = mAppService.getSplash()

    val recommend: Flowable<HttpResponse<List<Recommend>>>
        get() = mAppService.getRecommend()

    val region: Flowable<HttpResponse<List<Region>>>
        get() = mAppService.getRegion()

    val hotSearchTag: Flowable<HttpResponse<HotSearchTag>>
        get() = mAppService.getHotSearchTag()

    fun getSearchArchive(keyword: String, page: Int, pagesize: Int): Flowable<HttpResponse<SearchArchive>> {
        return mAppService.getSearchArchive(keyword, page, pagesize)
    }

    fun getRegionRecommend(rid: Int): Flowable<HttpResponse<RegionRecommend>> {
        return mAppService.getRegionRecommend(rid)
    }

    fun getRegionType(rid: Int): Flowable<HttpResponse<RegionType>> {
        return mAppService.getRegionType(rid)
    }

    val videoDetail: Flowable<VideoDetail>
        get() = mAppService.getVideoDetail()

    val videoDetailComment: Flowable<VideoDetailComment>
        get() = mApiService.getVideoDetailComment()

    val upDetail: Flowable<UpDetail>
        get() = mAppService.getUpDetail()

    val search: Flowable<Search>
        get() = mAppService.getSearch()

    val up: Flowable<Up>
        get() = mAppService.getUp()

    val movie: Flowable<Movie>
        get() = mAppService.getMovie()

    val season: Flowable<Season>
        get() = mAppService.getSeason()
    val videoPlayer: Flowable<VideoPlayer>
        get() = mAppService.getVideoPlayer()
    /*******************************LiveApi */

    val liveRecommend: Flowable<HttpResponse<LiveRecommend>>
        get() = mLiveService.getLiveRecommend()

    val livePartition: Flowable<HttpResponse<LivePartition>>
        get() = mLiveService.getLivePartition()

    val liveEntrance: Flowable<HttpResponse<List<LiveEntrance>>>
        get() = mLiveService.getLiveEntrance()

    /*******************************BangumiApi */

    val chaseBangumi: Flowable<HttpResponse<ChaseBangumi>>
        get() = mBangumiService.getChaseBangumi()

    val recommendBangumi: Flowable<HttpResponse<RecommendBangumi>>
        get() = mBangumiService.getRecommendBangumi()

    val bangumiSchedule: Flowable<HttpResponse<List<BangumiSchedule>>>
        get() = mBangumiService.getBangumiSchedule()

    val bangumiIndex: Flowable<HttpResponse<BangumiIndex>>
        get() = mBangumiService.getBangumiIndex()

    val bangumiDetail: Flowable<HttpResponse<BangumiDetail>>
        get() = mBangumiService.getBangumiDetail()

    val bangumiDetailRecommend: Flowable<HttpResponse<BangumiDetailRecommend>>
        get() = mBangumiService.getBangumiDetailRecommend()

    /*******************************RankApi */
    fun getAllRegionRank(type: String): Flowable<AllRegionRank> {
        return mRankService.getAllRegionRank(type)
    }

    fun getAllStationRank(type: String): Flowable<AllStationRank> {
        return mRankService.getAllStationRank(type)
    }

    /*******************************ApiApi */
    val topicCenter: Flowable<TopicCenter>
        get() = mApiService.getTopicCenter()

    fun getActivityCenter(page: Int, pageSize: Int): Flowable<ActivityCenter> {
        return mApiService.getActivityCenter(page, pageSize)
    }

    val bangumiDetailComment: Flowable<BangumiDetailComment>
        get() = mApiService.getBangumiDetailComment()

    /*******************************Im9Api */

    val interestCategrory: Flowable<HttpResponse<InterestCategrory>>
        get() = mIm9Service.getInterestCategrory()

    val community: Flowable<HttpResponse<Community>>
        get() = mIm9Service.getCommunity()

    val interestAd: Flowable<HttpResponse<InterestAd>>
        get() = mIm9Service.getInterestAd()


}
