package com.yoyiyi.soleil.bean.live

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * @date 创建时间：2017/8/14 17:04
 * 描述:直播推荐
 */
data class LiveRecommend(var recommend_data: RecommendData) {
    data class RecommendData(var partition: Partition,
                             var banner_data: List<BannerData>,
                             var lives: List<Lives>) {
        data class Partition(var area: String,
                             var count: Int,
                             var id: Int,
                             var name: String,
                             var sub_icon: SubIcon) {
            data class SubIcon(var height: String,
                               var src: String,
                               var width: String)
        }

        data class BannerData(var accept_quality: String,
                              var area: String,
                              var area_id: Int,
                              var broadcast_type: Int,
                              var check_version: Int,
                              var cover: Cover,
                              var is_tv: Int,
                              var online: Int,
                              var owner: Owner,
                              var playurl: String,
                              var room_id: Int,
                              var title: String) {
            data class Cover(var height: Int,
                             var src: String,
                             var width: Int)

            data class Owner(var face: String,
                             var mid: Int,
                             var name: String)
        }

        data class Lives(var accept_quality: String,
                         var area: String,
                         var area_id: Int,
                         var broadcast_type: Int,
                         var check_version: Int,
                         var cover: Cover,
                         var is_tv: Int,
                         var online: Int,
                         var owner: Owner,
                         var playurl: String,
                         var room_id: Int,
                         var title: String) {
            data class Cover(var height: Int,
                             var src: String,
                             var width: Int)

            data class Owner(var face: String,
                             var mid: Int,
                             var name: String)
        }
    }
}