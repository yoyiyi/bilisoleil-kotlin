package com.yoyiyi.soleil.bean.chase

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * @date 创建时间：2017/8/18 10:49
 * 描述:
 */
data class RecommendBangumi(var recommend_cn: RecommendCn,
                            var recommend_jp: RecommendJp,
                            var ad: List<Ad>) {

    data class Ad(var img: String,
                  var index: Int,
                  var link: String,
                  var title: String) {
        /**
         * img : http://i0.hdslb.com/bfs/bangumi/095df0181442362ac1bc445d7f542eb6bf66f7da.jpg
         * index : 1
         * link : http://bangumi.bilibili.com/anime/5997
         * title : 黄漫老师
         */


    }

    data class RecommendCn(var foot: List<Foot>,
                           var recommend: List<Recommend>) {
        data class Foot(var cover: String,
                        var cursor: Long,
                        var desc: String,
                        var id: Int,
                        var is_new: Int,
                        var link: String,
                        var onDt: String,
                        var title: String)

        data class Recommend(var cover: String,
                             var favourites: String,
                             var is_auto: Int,
                             var is_finish: Int,
                             var is_started: Int,
                             var last_time: Int,
                             var newest_ep_index: String,
                             var pub_time: Int,
                             var season_id: Int,
                             var season_status: Int,
                             var title: String,
                             var total_count: Int,
                             var watching_count: Int)
    }

    data class RecommendJp(var foot: List<Foot>,
                           var recommend: List<Recommend>) {
        data class Foot(var cover: String,
                        var cursor: Long,
                        var desc: String,
                        var id: Int,
                        var is_new: Int,
                        var link: String,
                        var onDt: String,
                        var title: String)

        data class Recommend(var cover: String,
                             var favourites: String,
                             var is_auto: Int,
                             var is_finish: Int,
                             var is_started: Int,
                             var last_time: Int,
                             var newest_ep_index: String,
                             var pub_time: Int,
                             var season_id: Int,
                             var season_status: Int,
                             var title: String,
                             var total_count: Int,
                             var watching_count: Int)
    }
}