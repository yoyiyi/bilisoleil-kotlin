package com.yoyiyi.soleil.bean.region

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * @date 创建时间：2017/8/18 11:47
 * 描述:
 */
data class RegionTagType(var tid: Int,
                         var reid: Int,
                         var name: String,
                         var logo: String,
                         var goto: String,
                         var param: String,
                         var children: List<Children>) {
    data class Children(var tid: Int,
                        var reid: Int,
                        var name: String,
                        var logo: String,
                        var goto: String,
                        var param: String)
}