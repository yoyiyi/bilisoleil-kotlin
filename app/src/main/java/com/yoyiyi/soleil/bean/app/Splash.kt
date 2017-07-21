package com.yoyiyi.soleil.bean.app

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * @date 创建时间：2017/7/20 17:15
 * 描述:欢迎界面
 */
data class Splash(var code: Int,
                  var message: String,
                  var ttl: Int,
                  var ver: String,
                  var data: List<Data>) {
    data class Data(var id: Int,
                    var `type`: Int,
                    var animate: Int,
                    var duration: Int,
                    var start_time: Int,
                    var end_time: Int,
                    var thumb: String,
                    var hash: String,
                    var times: Int,
                    var skip: Int,
                    var uri: String)
}