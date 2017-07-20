package com.yoyiyi.soleil.bean.app

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * @date 创建时间：2017/7/20 17:10
 * 描述:
 */
data class Recommend(var code: Int,
                     var message: String,
                     var ttl: Int,
                     var data: List<Data>) {
    data class Data(var param: String,
                    var `type`: String,
                    var style: String,
                    var title: String,
                    var body: List<Body>) {
        data class Body(var title: String,
                        var cover: String,
                        var uri: String,
                        var param: String,
                        var goto: String,
                        var play: Int,
                        var index: String,
                        var total_count: String,
                        var mtime: String,
                        var status: Int,
                        var favourite: Int,
                        var is_ad: Boolean,
                        var cm_mark: Int)
    }
}