package com.yoyiyi.soleil.bean.chase

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * @date 创建时间：2017/8/18 10:48
 * 描述:
 */
data class ChaseBangumi(var follow_count: Int,
                        var update_count: Int,
                        var delay_notice: List<*>,
                        var follows: List<Follows>) {
    data class Follows(var brief: String,
                       var cover: String,
                       var ed_jump: Int,
                       var is_finish: String,
                       var is_started: Int,
                       var limitGroupId: Int,
                       var new_ep: NewEp,
                       var newest_ep_id: String,
                       var newest_ep_index: String,
                       var pub_time: String,
                       var season_id: String,
                       var season_status: Int,
                       var squareCover: String,
                       var title: String,
                       var total_count: String,
                       var trailerAid: String,
                       var user_season: UserSeason,
                       var weekday: String) {
        data class NewEp(var episode_id: String,
                         var episode_status: Int,
                         var index: String,
                         var update_time: String)

        data class UserSeason(var attention: String,
                              var bp: Int,
                              var last_ep_id: String,
                              var last_ep_index: String,
                              var last_time: String,
                              var report_ts: Long)
    }
}