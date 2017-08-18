package com.yoyiyi.soleil.bean.region

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * @date 创建时间：2017/8/18 11:51
 * 描述:
 */
data class Region(var param: String,
                  var `type`: String,
                  var style: String,
                  var title: String,
                  var banner: Banner,
                  var body: List<Body>) {
    data class Banner(var top: List<Top>) {
        data class Top(var id: Int,
                       var title: String,
                       var image: String,
                       var hash: String,
                       var uri: String,
                       var resource_id: Int,
                       var request_id: String,
                       var is_ad: Boolean,
                       var cm_mark: Int,
                       var index: Int,
                       var server_type: Int)
    }

    data class Body(var title: String,
                    var cover: String,
                    var uri: String,
                    var param: String,
                    var goto: String,
                    var play: Int,
                    var index: String,
                    var total_count: String,
                    var mtime: String,
                    var danmaku:Int,
                    var status: Int,
                    var favourite: Int,
                    var is_ad: Boolean,
                    var cm_mark: Int)
}