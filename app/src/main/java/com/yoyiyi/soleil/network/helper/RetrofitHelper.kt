package com.yoyiyi.soleil.network.helper


import com.yoyiyi.soleil.bean.app.Splash
import com.yoyiyi.soleil.bean.app.video.VideoDetail
import com.yoyiyi.soleil.bean.app.video.VideoDetailComment
import com.yoyiyi.soleil.bean.app.video.VideoPlayer
import com.yoyiyi.soleil.bean.bangumi.*
import com.yoyiyi.soleil.bean.chase.ChaseBangumi
import com.yoyiyi.soleil.bean.chase.RecommendBangumi
import com.yoyiyi.soleil.bean.discover.*
import com.yoyiyi.soleil.bean.live.LiveEntrance
import com.yoyiyi.soleil.bean.live.LivePartition
import com.yoyiyi.soleil.bean.live.LiveRecommend
import com.yoyiyi.soleil.bean.recommend.AllStationRank
import com.yoyiyi.soleil.bean.region.AllRegionRank
import com.yoyiyi.soleil.bean.region.Region
import com.yoyiyi.soleil.bean.region.RegionRecommend
import com.yoyiyi.soleil.bean.region.RegionType
import com.yoyiyi.soleil.bean.search.*
import com.yoyiyi.soleil.bean.user.UpDetail
import com.yoyiyi.soleil.network.api.*
import com.yoyiyi.soleil.network.response.HttpResponse
import io.reactivex.Flowable


/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/10 17:57
 * * 描述:RetrofitHelper 帮助类
 */

class RetrofitHelper(private val appService: AppService,
                     private val liveService: LiveService,
                     private val bangumiService: BangumiService,
                     private val rankService: RankService,
                     private val apiService: ApiService,
                     private val im9Service: Im9Service) {


    /*******************************AppApi ****************************************************/
    fun getSplash(): Flowable<Splash> = appService.getSplash()

    fun getLivePartition(): Flowable<HttpResponse<LivePartition>> = liveService.getLivePartition()

    fun getLiveRecommend(): Flowable<HttpResponse<LiveRecommend>> = liveService.getLiveRecommend()

    fun getRegion(): Flowable<HttpResponse<List<Region>>> = appService.getRegion()

    fun getHotSearchTag(): Flowable<HttpResponse<HotSearchTag>> = appService.getHotSearchTag()

    fun getActivityCenter(page: Int, pageSize: Int): Flowable<ActivityCenter> = apiService.getActivityCenter(page, pageSize)

    fun getVideoPlayer(): Flowable<VideoPlayer> = appService.getVideoPlayer()

    fun getUpDetail(): Flowable<UpDetail> = appService.getUpDetail()

    fun getVideoDetail(): Flowable<VideoDetail> = appService.getVideoDetail()

    fun getVideoDetailComment(): Flowable<VideoDetailComment> = apiService.getVideoDetailComment()
    /*******************************RankApi */

    fun getAllRegionRank(type: String): Flowable<AllRegionRank> = rankService.getAllRegionRank(type)


    fun getLiveEntrance(): Flowable<HttpResponse<List<LiveEntrance>>> = liveService.getLiveEntrance()

    fun getRegionType(rid: Int): Flowable<HttpResponse<RegionType>> = appService.getRegionType(rid)

    fun getRegionRecommend(rid: Int): Flowable<HttpResponse<RegionRecommend>> = appService.getRegionRecommend(rid)

    fun getTopicCenter(): Flowable<TopicCenter> = apiService.getTopicCenter()

    fun getSearch(): Flowable<Search> = appService.getSearch()

    fun getUp(): Flowable<Up> = appService.getUp()

    fun getMovie(): Flowable<Movie> = appService.getMovie()

    fun getSeason(): Flowable<Season> = appService.getSeason()


    fun getSearchArchive(keyword: String, page: Int, pagesize: Int): Flowable<HttpResponse<SearchArchive>> = appService.getSearchArchive(keyword, page, pagesize)

    /*******************************RankApi */


    fun getAllStationRank(type: String): Flowable<AllStationRank> = rankService.getAllStationRank(type)

    /*******************************BangumiApi */

    fun getChaseBangumi(): Flowable<HttpResponse<ChaseBangumi>> = bangumiService.getChaseBangumi()


    fun getRecommendBangumi(): Flowable<HttpResponse<RecommendBangumi>> = bangumiService.getRecommendBangumi()


    fun getBangumiSchedule(): Flowable<HttpResponse<List<BangumiSchedule>>> = bangumiService.getBangumiSchedule()


    fun getBangumiIndex(): Flowable<HttpResponse<BangumiIndex>> = bangumiService.getBangumiIndex()


    fun getBangumiDetail(): Flowable<HttpResponse<BangumiDetail>> = bangumiService.getBangumiDetail()


    fun getBangumiDetailRecommend(): Flowable<HttpResponse<BangumiDetailRecommend>> = bangumiService.getBangumiDetailRecommend()

    fun getBangumiDetailComment(): Flowable<BangumiDetailComment> = apiService.getBangumiDetailComment()

/*
  public Flowable<TopicCenter> getTopicCenter() {
        return mApiService.getTopicCenter();
    }

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
    */
    /*******************************LiveApi */
    /*

        val liveRecommend: Flowable<HttpResponse<LiveRecommend>>
            get() = mLiveService.getLiveRecommend()

        val livePartition: Flowable<HttpResponse<LivePartition>>
            get() = mLiveService.getLivePartition()

        val liveEntrance: Flowable<HttpResponse<List<LiveEntrance>>>
            get() = mLiveService.getLiveEntrance()

        */
    /*******************************BangumiApi */
    /*

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

        */
    /*******************************RankApi */
    /*
        fun getAllRegionRank(type: String): Flowable<AllRegionRank> {
            return mRankService.getAllRegionRank(type)
        }

        fun getAllStationRank(type: String): Flowable<AllStationRank> {
            return mRankService.getAllStationRank(type)
        }

        */
    /*******************************ApiApi */
    /*
        val topicCenter: Flowable<TopicCenter>
            get() = mApiService.getTopicCenter()

        fun getActivityCenter(page: Int, pageSize: Int): Flowable<ActivityCenter> {
            return mApiService.getActivityCenter(page, pageSize)
        }

        val bangumiDetailComment: Flowable<BangumiDetailComment>
            get() = mApiService.getBangumiDetailComment()

        */


    /*******************************Im9Api */

    fun getInterestCategrory(): Flowable<HttpResponse<InterestCategrory>> = im9Service.getInterestCategrory()


    fun getCommunity(): Flowable<HttpResponse<Community>> = im9Service.getCommunity()


    fun getInterestAd(): Flowable<HttpResponse<InterestAd>> = im9Service.getInterestAd()


}
