package com.yoyiyi.soleil.bean.dynamic


/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * @date 创建时间：2017/8/18 14:07
 * 描述:
 */
data class Dynamic(var item: List<ItemBean>) {
    data class ItemBean(
            var isRecent: Int,
            var position: Int,
            var title: String,
            var cover: String,
            var uri: String,
            var param: String,
            var gotoX: String,
            var desc: String,
            var play: Int,
            var danmaku: Int,
            var reply: Int,
            var favorite: Int,
            var tid: Int,
            var tname: String,
            var tag: RecentBean.TagBean,
            var ctime: Long,
            var duration: Int,
            var mid: Int,
            var name: String,
            var face: String,
            var is_atten: Int,
            var recent_count: Int,
            var coin: Int,
            var share: Int,
            var count: Int,
            var type: Int,
            var index: String,
            var index_title: String,
            var updates: Int,
            var recent: List<RecentBean>

            ) {

        data class RecentBean(var coin: Int,
                              var cover: String,
                              var ctime: Int,
                              var danmaku: Int,
                              var desc: String,
                              var duration: Int,
                              var face: String,
                              var favorite: Int,
                              var goto: String,
                              var is_atten: Int,
                              var mid: Int,
                              var name: String,
                              var param: String,
                              var play: Int,
                              var reply: Int,
                              var share: Int,
                              var tag: TagBean,
                              var tid: Int,
                              var title: String,
                              var tname: String,
                              var uri: String) {
            data class TagBean(var count: CountBean,
                               var tag_id: Int,
                               var tag_name: String) {
                data class CountBean(var atten: Int)
            }
        }
    }
}