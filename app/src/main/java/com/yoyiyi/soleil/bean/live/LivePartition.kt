package com.yoyiyi.soleil.bean.live

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * @date 创建时间：2017/8/14 17:02
 * 描述:直播分区
 */
data class LivePartition(var banner: List<Banner>,
                         var entranceIcons: List<EntranceIcons>,
                         var navigator: List<Navigator>,
                         var partitions: List<Partitions>) {
    data class Banner(var img: String,
                      var link: String,
                      var remark: String,
                      var title: String)

    data class EntranceIcons(var entrance_icon: EntranceIcon,
                             var id: Int,
                             var name: String) {
        data class EntranceIcon(var height: String,
                                var src: String,
                                var width: String)
    }

    data class Navigator(var entrance_icon: EntranceIcon,
                         var id: Int,
                         var name: String) {
        data class EntranceIcon(var height: String,
                                var src: String,
                                var width: String)
    }

    data class Partitions(var partition: Partition,
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