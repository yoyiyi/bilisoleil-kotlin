package com.yoyiyi.soleil.bean.recommend

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * @date 创建时间：2017/8/17 15:25
 * 描述:
 */
data class Recommend/*(var code: Int,
                     var message: String,
                     var ttl: Int,
                     var data: List<Data>)*/
   /* data class Data*/(var param: String,
                    var goto: String,
                    var idx: Int,
                    var title: String,
                    var cover: String,
                    var uri: String,
                    var desc: String,
                    var play: Int,
                    var danmaku: Int,
                    var reply: Int,
                    var favorite: Int,
                    var coin: Int,
                    var share: Int,
                    var tid: Int,
                    var tname: String,
                    var tag: Tag,
                    var ctime: Int,
                    var duration: Int,
                    var mid: Int,
                    var name: String,
                    var face: String,
                    var online: Int,
                    var area: String,
                    var area_id: Int,
                    var `open`: Int,
                    var banner_item: List<BannerItem>,
                    var dislike_reasons: List<DislikeReasons>) {
        data class Tag(var tag_id: Int,
                       var tag_name: String,
                       var count: Count) {
            data class Count(var atten: Int)
        }

        data class BannerItem(var id: Int,
                              var title: String,
                              var image: String,
                              var hash: String,
                              var uri: String,
                              var request_id: String,
                              var server_type: Int,
                              var resource_id: Int,
                              var index: Int,
                              var cm_mark: Int,
                              var creative_id: Int,
                              var src_id: Int,
                              var is_ad_loc: Boolean,
                              var ad_cb: String,
                              var client_ip: String,
                              var is_ad: Boolean,
                              var click_url: String)

        data class DislikeReasons(var reason_id: Int,
                                  var reason_name: String)
    }
