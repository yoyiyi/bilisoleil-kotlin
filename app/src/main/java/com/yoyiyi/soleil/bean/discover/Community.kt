package com.yoyiyi.soleil.bean.discover

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/6/10 21:36
 * * 描述:兴趣圈
 */
data class Community(var result: List<ResultBean>,
                     var total_count: Int,
                     var total_page: Int) {
    /*  total_count":1996,"total_page":67,"result":[*/


    /**
     * community_info : {"certification":0,"community_avatar":"http://img.yo9.com/ffb5c320978711e6bd4a00163e000128","community_id":12,"community_name":"Bilibili萌战日本动画场","community_url":"http://www.im9.com/community.html?community_id=12","member_num":22608,"post_count":4900}
     * post_info : {"author_avatar":"http://i1.hdslb.com/bfs/face/d5feee58476452c0c43ef71fab38c50e4119294f.jpg","author_mid":3061924,"author_name":"路过的小轩","chosen_time":1481107581000,"image_count":2,"last_reply_author":"51xx点info","last_reply_mid":82581258,"last_reply_time":1496979724000,"level":4,"modify_time":1481049163000,"post_id":11636,"post_image_list":[{"height":155,"id":43197,"image_id":"49e55c70bbe211e6b4bb00163e00043c","image_url":"http://img.yo9.com/49e55c70bbe211e6b4bb00163e00043c","img_suffix":"jpg","praise_count":0,"width":220},{"height":359,"id":43198,"image_id":"3e471e30bbe211e6b4bb00163e00043c","image_url":"http://img.yo9.com/3e471e30bbe211e6b4bb00163e00043c","img_suffix":"jpg","praise_count":0,"width":640}],"post_summary":"灰与幻想的格林姆迦尔我觉得是一部良番了（虽然推图进度慢的可以）画风清新音乐好听，和素晴（没有日）是同一时间的番，据说销量也是黑马，在此强推没有看过的可以去看看。...","post_time":1481049163000,"post_title":"为哈尔希洛而拉票（没想到我居然会为男的拉选票）长文本注意！","praise_count":17,"reply_count":80,"sex":0,"tags":[]}
     */
    data class ResultBean(var community_info: CommunityInfoBean,
                          var post_info: PostInfoBean) {


        data class CommunityInfoBean(var certification: Int,
                                     var community_avatar: String,
                                     var community_id: Int,
                                     var community_name: String,
                                     var community_url: String,
                                     var member_num: Int,
                                     var post_count: Int) {
            /**
             * certification : 0
             * community_avatar : http://img.yo9.com/ffb5c320978711e6bd4a00163e000128
             * community_id : 12
             * community_name : Bilibili萌战日本动画场
             * community_url : http://www.im9.com/community.html?community_id=12
             * member_num : 22608
             * post_count : 4900
             */


        }

        class PostInfoBean(var author_avatar: String,
                           var author_mid: Int,
                           var author_name: String,
                           var chosen_time: Long,
                           var image_count: Int,
                           var last_reply_author: String,
                           var last_reply_mid: Int,
                           var last_reply_time: Long,
                           var level: Int,
                           var modify_time: Long,
                           var post_id: Int,
                           var post_summary: String,
                           var post_time: Long,
                           var post_title: String,
                           var praise_count: Int,
                           var reply_count: Int,
                           var sex: Int,
                           var post_image_list: List<PostImageListBean>,
                           var tags: List<*>) {
            /**
             * author_avatar : http://i1.hdslb.com/bfs/face/d5feee58476452c0c43ef71fab38c50e4119294f.jpg
             * author_mid : 3061924
             * author_name : 路过的小轩
             * chosen_time : 1481107581000
             * image_count : 2
             * last_reply_author : 51xx点info
             * last_reply_mid : 82581258
             * last_reply_time : 1496979724000
             * level : 4
             * modify_time : 1481049163000
             * post_id : 11636
             * post_image_list : [{"height":155,"id":43197,"image_id":"49e55c70bbe211e6b4bb00163e00043c","image_url":"http://img.yo9.com/49e55c70bbe211e6b4bb00163e00043c","img_suffix":"jpg","praise_count":0,"width":220},{"height":359,"id":43198,"image_id":"3e471e30bbe211e6b4bb00163e00043c","image_url":"http://img.yo9.com/3e471e30bbe211e6b4bb00163e00043c","img_suffix":"jpg","praise_count":0,"width":640}]
             * post_summary : 灰与幻想的格林姆迦尔我觉得是一部良番了（虽然推图进度慢的可以）画风清新音乐好听，和素晴（没有日）是同一时间的番，据说销量也是黑马，在此强推没有看过的可以去看看。...
             * post_time : 1481049163000
             * post_title : 为哈尔希洛而拉票（没想到我居然会为男的拉选票）长文本注意！
             * praise_count : 17
             * reply_count : 80
             * sex : 0
             * tags : []
             */


            data class PostImageListBean(var height: Int,
                                         var id: Int,
                                         var image_id: String,
                                         var image_url: String,
                                         var img_suffix: String,
                                         var praise_count: Int,
                                         var width: Int) {
                /**
                 * height : 155
                 * id : 43197
                 * image_id : 49e55c70bbe211e6b4bb00163e00043c
                 * image_url : http://img.yo9.com/49e55c70bbe211e6b4bb00163e00043c
                 * img_suffix : jpg
                 * praise_count : 0
                 * width : 220
                 */


            }
        }
    }


}
