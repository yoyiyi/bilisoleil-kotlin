package com.yoyiyi.soleil.network.api

import com.yoyiyi.soleil.bean.app.Splash
import com.yoyiyi.soleil.bean.discover.HotSearchTag
import com.yoyiyi.soleil.bean.region.Region
import com.yoyiyi.soleil.bean.region.RegionRecommend
import com.yoyiyi.soleil.bean.region.RegionType
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

}