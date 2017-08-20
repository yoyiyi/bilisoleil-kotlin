package com.yoyiyi.soleil.bean.bangumi

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/6/9 9:30
 * * 描述:番剧索引
 */

data class BangumiIndex(var category: List<CategoryBean>,

                        var years: List<String>) {

    /**
     * cover : http://i2.hdslb.com/u_user/f7a16e4a28fe524ceddfe0860b52d057.jpg
     * tag_id : 117
     * tag_name : 轻改
     */


    data class CategoryBean(var cover: String,

                            var tag_id: String,

                            var tag_name: String)

}
