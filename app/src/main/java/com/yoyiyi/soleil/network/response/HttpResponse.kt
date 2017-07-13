package com.yoyiyi.soleil.network.response

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/4/27 14:20
 * * 描述:统一处理HttpResponse
 */

class HttpResponse<T> {
    var data: T? = null//数据
    var result: T? = null//数据
    var message: String? = null//信息
    var code: Int = 0//服务器状态
    //只有全区排行 json 有这个 字段
    var rank: T? = null//数据
}
