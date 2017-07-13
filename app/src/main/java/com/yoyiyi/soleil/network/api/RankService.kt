package com.yoyiyi.soleil.network.api

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/23 16:25
 * * 描述:排行
 */

interface RankService {
/*
    *//**
     * 全站排行
     * @param type
     * *
     * @return
     *//*
    @GET("index/rank/{type}")
    fun getAllStationRank(@Path("type") type: String): Flowable<AllStationRank>

    *//**
     * 全区排行

     * @param type
     * *
     * @return
     *//*
    @GET("index/rank/{type}")
    fun getAllRegionRank(@Path("type") type: String): Flowable<AllRegionRank>*/
}
