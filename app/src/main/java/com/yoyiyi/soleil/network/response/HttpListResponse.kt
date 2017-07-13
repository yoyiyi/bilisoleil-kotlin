package com.yoyiyi.soleil.network.response

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/4/27 14:20
 * * 描述:统一处理HttpListResponse
 */

class HttpListResponse<T> {
    var data: List<T>? = null//数据
    var result: List<T>? = null//数据
    var message: String? = null//信息
    var code: Int = 0//服务器状态
}
