package com.yoyiyi.soleil.network.api

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/23 12:01
 * * 描述:
 */

interface BangumiService {

  /*  *//**
     * 首页追番

     * @return
     *//*
    @get:GET("/appindex/follow_index_mine?access_key=640efdbe3a382e5522491d7f913118fa&appkey=1d8b6e7d45233436&build=505000&mid=117143614&mobi_app=android&platform=android&ts=1495878887&sign=26909b825ee9aa3136c82f192688829d")
    val chaseBangumi: Flowable<HttpResponse<ChaseBangumi>>

    *//**
     * 首页追番推荐

     * @return
     *//*
    @get:GET("/appindex/follow_index_page?appkey=1d8b6e7d45233436&build=505000&mobi_app=android&platform=android&ts=1495878887&sign=1b069620b35b65619bd18566dacc6342")
    val recommendBangumi: Flowable<HttpResponse<RecommendBangumi>>

    *//**
     * 番剧时间表
     *//*
    @get:GET("api/timeline_v4?access_key=19946e1ef3b5cad1a756c475a67185bb&actionKey=appkey&appkey=27eb53fc9058f8c3&area_id=2&build=3940&device=phone&mobi_app=iphone&platform=ios&see_mine=0&sign=d8cbbacab2e5fd0196b4883201e2103e&ts=1477981526")
    val bangumiSchedule: Flowable<HttpResponse<List<BangumiSchedule>>>

    *//**
     * 番剧索引
     *//*
    @get:GET("api/bangumi_index_cond?access_key=19946e1ef3b5cad1a756c475a67185bb&actionKey=appkey&appkey=27eb53fc9058f8c3&build=3940&device=phone&mobi_app=iphone&platform=ios&sign=cfc6903a13ba89e81c904b4c589e5369&ts=1477974966&type=0")
    val bangumiIndex: Flowable<HttpResponse<BangumiIndex>>


    *//**
     * 番剧详情番剧推荐
     *//*
    @get:GET("api/season/recommend/rnd/6066.json?appkey=1d8b6e7d45233436&build=505000&mobi_app=android&platform=android&ts=1497169314&sign=da4d668fe4aaf97de55541f8d05ac57f")
    val bangumiDetailRecommend: Flowable<HttpResponse<BangumiDetailRecommend>>

    *//**
     * 番剧详情
     *//*
    @get:GET("api/season_v5?access_key=ccfbb1b10ce8ab8418a2e00b9ca9a3a0&appkey=1d8b6e7d45233436&build=505000&mobi_app=android&platform=android&season_id=6066&ts=1497169313&type=bangumi&sign=c6796f6ea4a6cae28a4d8fc555fde2da")
    val bangumiDetail: Flowable<HttpResponse<BangumiDetail>>*/
}
