package com.yoyiyi.soleil.network.api

import com.yoyiyi.soleil.bean.live.LiveEntrance
import com.yoyiyi.soleil.bean.live.LivePartition
import com.yoyiyi.soleil.bean.live.LiveRecommend
import com.yoyiyi.soleil.network.response.HttpResponse
import io.reactivex.Flowable
import retrofit2.http.GET

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/23 16:25
 * * 描述:直播
 */

interface LiveService {
    @GET("/AppNewIndex/recommend?_device=android&access_key=5b0032c681c2233870c8edcee410b6c6&appkey=1d8b6e7d45233436&build=505000&mobi_app=android&platform=android&scale=xxhdpi&ts=1495639021&sign=9d024a5b09edddd51636d17d860622d2")
    fun getLiveRecommend(): Flowable<HttpResponse<LiveRecommend>>


    @GET("/AppNewIndex/common?_device=android&access_key=5b0032c681c2233870c8edcee410b6c6&appkey=1d8b6e7d45233436&build=505000&mobi_app=android&platform=android&scale=xxhdpi&ts=1495639884&sign=74b510ce56ef302742aafad2e20f9899")
    fun getLivePartition(): Flowable<HttpResponse<LivePartition>>


    /**
     * 获取直播分区的tag标题

     * @return
     */
    @GET("/AppIndex/areas?_device=android&access_key=21073183486ba556121c1160f107f0c5&appkey=1d8b6e7d45233436&build=506000&mobi_app=android&platform=android&scale=xxhdpi&ts=1496116760&sign=e2231dc84bc33bc1a7c6d8eddf13da9d")
     fun getLiveEntrance(): Flowable<HttpResponse<List<LiveEntrance>>>

    /*  */
    /**
     * 首页推荐直播

     * @return
     */
    /*
        @get:GET("/AppNewIndex/recommend?_device=android&access_key=5b0032c681c2233870c8edcee410b6c6&appkey=1d8b6e7d45233436&build=505000&mobi_app=android&platform=android&scale=xxhdpi&ts=1495639021&sign=9d024a5b09edddd51636d17d860622d2")
        val liveRecommend: Flowable<HttpResponse<LiveRecommend>>

        */
    /**
     * 直播分区

     * @return
     */
    /*
        @get:GET("/AppNewIndex/common?_device=android&access_key=5b0032c681c2233870c8edcee410b6c6&appkey=1d8b6e7d45233436&build=505000&mobi_app=android&platform=android&scale=xxhdpi&ts=1495639884&sign=74b510ce56ef302742aafad2e20f9899")
        val livePartition: Flowable<HttpResponse<LivePartition>>

        */
    /**
     * 获取直播分区的tag标题

     * @return
     */
    /*
        @get:GET("/AppIndex/areas?_device=android&access_key=21073183486ba556121c1160f107f0c5&appkey=1d8b6e7d45233436&build=506000&mobi_app=android&platform=android&scale=xxhdpi&ts=1496116760&sign=e2231dc84bc33bc1a7c6d8eddf13da9d")
        val liveEntrance: Flowable<HttpResponse<List<LiveEntrance>>>


        */
    /**
     * 获取直播详情

     * @return
     */
    /*
        @get:GET("/AppRoom/index?_device=android&_hwid=b008d5bd523c9447&access_key=a0ce768f3b27a34121d8f8835c02d4f4&appkey=1d8b6e7d45233436&build=508000&buld=508000&jumpFrom=24000&mobi_app=android&platform=android&room_id=4350043&scale=xxhdpi&src=360&trace_id=20170629140500046&ts=1498716346&version=5.8.0.508000&sign=6c0abb24615ecddcdaa808b0a51f6b0d")
        val liveDetail: Flowable<LiveDetail>

        */
    /**
     * 获取礼物TOP

     * @return
     */
    /*
        @get:GET("/AppRoom/getGiftTop?_device=android&_hwid=b008d5bd523c9447&access_key=a0ce768f3b27a34121d8f8835c02d4f4&appkey=1d8b6e7d45233436&build=508000&mobi_app=android&platform=android&room_id=4350043&src=360&trace_id=20170629140500046&ts=1498716346&version=5.8.0.508000&sign=40e20d3b8ea070b23f12dafbdc128d3e")
        val giftTop: Flowable<GiftTop>

        */
    /**
     * 获取舰队

     * @return
     */
    /*
        @get:GET("/AppRoom/guardRank?_device=android&_hwid=b008d5bd523c9447&access_key=a0ce768f3b27a34121d8f8835c02d4f4&appkey=1d8b6e7d45233436&build=508000&mobi_app=android&page=1&page_size=15&platform=android&ruid=19738891&src=360&trace_id=20170629140800018&ts=1498716498&version=5.8.0.508000&sign=a3388dfae8b2e41a60665c006e8ed241")
        val guardRank: Flowable<GuardRank>


        */
    /**
     * 成就

     * @return
     */
    /*
        @get:GET("/appUser/getTitle?_device=android&_hwid=b008d5bd523c9447&access_key=a0ce768f3b27a34121d8f8835c02d4f4&appkey=1d8b6e7d45233436&build=508000&mobi_app=android&platform=android&scale=xxhdpi&src=360&trace_id=20170629140500046&ts=1498716346&version=5.8.0.508000&sign=d86a9f6594e5e75c7890e5250d367fcd")
        val title: Flowable<HttpResponse<List<Title>>>*/
}
