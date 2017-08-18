package com.yoyiyi.soleil.network.api

import com.yoyiyi.soleil.bean.app.Splash
import com.yoyiyi.soleil.bean.region.Region
import com.yoyiyi.soleil.network.response.HttpResponse
import io.reactivex.Flowable
import retrofit2.http.GET

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

}