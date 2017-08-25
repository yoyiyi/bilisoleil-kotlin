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

    fun getAllStationRank(type: String): Flowable<AllStationRank> = rankService.getAllStationRank(type)

    fun getChaseBangumi(): Flowable<HttpResponse<ChaseBangumi>> = bangumiService.getChaseBangumi()

    fun getRecommendBangumi(): Flowable<HttpResponse<RecommendBangumi>> = bangumiService.getRecommendBangumi()

    fun getBangumiSchedule(): Flowable<HttpResponse<List<BangumiSchedule>>> = bangumiService.getBangumiSchedule()

    fun getBangumiIndex(): Flowable<HttpResponse<BangumiIndex>> = bangumiService.getBangumiIndex()

    fun getBangumiDetail(): Flowable<HttpResponse<BangumiDetail>> = bangumiService.getBangumiDetail()

    fun getBangumiDetailRecommend(): Flowable<HttpResponse<BangumiDetailRecommend>> = bangumiService.getBangumiDetailRecommend()

    fun getBangumiDetailComment(): Flowable<BangumiDetailComment> = apiService.getBangumiDetailComment()

    fun getInterestCategrory(): Flowable<HttpResponse<InterestCategrory>> = im9Service.getInterestCategrory()

    fun getCommunity(): Flowable<HttpResponse<Community>> = im9Service.getCommunity()

    fun getInterestAd(): Flowable<HttpResponse<InterestAd>> = im9Service.getInterestAd()


}
