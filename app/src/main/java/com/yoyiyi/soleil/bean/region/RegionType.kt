package com.yoyiyi.soleil.bean.region

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/6/4 18:46
 * * 描述:分区tag详情
 */
data class RegionType(var recommend: List<RecommendBean>,
                      var new: List<NewBean>) {
    /**
     * title : 【AMV/60fps】视觉的极致盛宴 Umbrella Corp【Nostromo】
     * cover : http://i0.hdslb.com/bfs/archive/70315d46e396d55cc6785a1bf8caf114cef0cbcc.jpg
     * uri : bilibili://video/6733923
     * param : 6733923
     * goto : av
     * name : ここにいるよ
     * play : 224795
     * danmaku : 1858
     * reply : 1280
     * favourite : 40890
     */


    /**
     * title : 【超燃/AMV】• Runnin ♫♪
     * cover : http://i1.hdslb.com/bfs/archive/ddbd80d656994ff6d9b1250ca8da7c3fc9154fc1.jpg
     * uri : bilibili://video/6770288
     * param : 6770288
     * goto : av
     * name : 来自火星的小火龙
     */


    data class RecommendBean(var title: String,
                             var cover: String,
                             var uri: String,
                             var param: String,
                             var goto: String,
                             var name: String,
                             var play: Int,
                             var danmaku: Int,
                             var reply: Int,
                             var favourite: Int)

    data class NewBean(var title: String,
                       var cover: String,
                       var uri: String,
                       var param: String,

                       var goto: String,
                       var name: String,
                       var play: Int,
                       var danmaku: Int)
}
