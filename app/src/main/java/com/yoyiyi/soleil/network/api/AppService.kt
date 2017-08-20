package com.yoyiyi.soleil.network.api

import com.yoyiyi.soleil.bean.app.Splash
import com.yoyiyi.soleil.bean.app.video.VideoDetail
import com.yoyiyi.soleil.bean.app.video.VideoPlayer
import com.yoyiyi.soleil.bean.discover.HotSearchTag
import com.yoyiyi.soleil.bean.region.Region
import com.yoyiyi.soleil.bean.region.RegionRecommend
import com.yoyiyi.soleil.bean.region.RegionType
import com.yoyiyi.soleil.bean.user.UpDetail
import com.yoyiyi.soleil.network.response.HttpResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * @date 创建时间：2017/7/13 14:32
 * 描述:
 */
interface AppService {
    /**
     * splash界面
     * @return
     */
    @GET("/x/v2/splash?mobi_app=android&build=505000&channel=360&width=1080&height=1920&ver=4344558841496142006")
    fun getSplash(): Flowable<Splash>

    /**
     * 首页分区

     * @return
     */
    @GET("/x/v2/show/index?access_key=fcbe0b2d947971fd3cc2b9e759d63097&appkey=1d8b6e7d45233436&build=505000&mobi_app=android&platform=android&ts=1495780436&sign=93ebfdf6018d866239977af373d45dba")
    fun getRegion(): Flowable<HttpResponse<List<Region>>>

    /**
     * 首页发现 热门搜索标签

     * @return
     */
    @GET("/x/v2/search/hot?appkey=1d8b6e7d45233436&build=506000&limit=50&mobi_app=android&platform=android&ts=1495949781&sign=9bc4cea15aa9de8a0b142db86634f11f\n")
    fun getHotSearchTag(): Flowable<HttpResponse<HotSearchTag>>

    /**
     * 分区类型详情
     */
    @GET("x/v2/region/show/child?build=3600")
    fun getRegionType(@Query("rid") rid: Int): Flowable<HttpResponse<RegionType>>

    /**
     * 分区推荐
     */
    @GET("x/v2/region/show?access_key=67cbf6a1e9ad7d7f11bfbd918e50c837&actionKey=appkey&appkey=27eb53fc9058f8c3&build=3600&device=phone&mobi_app=iphone&plat=1&platform=ios&sign=959d7b8c09c65e7a66f7e58b1a2bdab9&ts=1472310694")
    fun getRegionRecommend(@Query("rid") rid: Int): Flowable<HttpResponse<RegionRecommend>>

    /**
     * 获取视频播放

     * @return
     */
    @GET("/playurl?device=android&qn=16&cid=6191437&appkey=iVGUTjsxvpLeuDCf&access_key=18b6350cf0e8fb0cacb6cf323fc2feba&otype=json&mid=117143614&build=505000&buvid=0ADC5B25-8C0A-4F6E-AE0C-83A3089CCEE223963infoc&platform=android&sign=153cb8fda95e5b1400cc3729d9a8afce")
    fun getVideoPlayer(): Flowable<VideoPlayer>

    /**
     * up主详情界面

     * @return
     */
    @GET("/x/v2/space?access_key=91c3f4aa6761385fd99998b4f07e193f&appkey=1d8b6e7d45233436&build=505000&mobi_app=android&platform=android&ps=20&ts=1497595431&vmid=12617707&sign=465dc5c1d330999eb86514482cc7a1f3")
    fun getUpDetail(): Flowable<UpDetail>


    /**
     * 视频详情
     */
    @GET("/x/v2/view?access_key=18b6350cf0e8fb0cacb6cf323fc2feba&aid=3852397&appkey=1d8b6e7d45233436&build=505000&from=3&mobi_app=android&plat=0&platform=android&trackid=14859045423998458858&ts=1498645383&sign=94a3c4d143f44f023558ea52a33be403")
    fun getVideoDetail(): Flowable<VideoDetail>
}