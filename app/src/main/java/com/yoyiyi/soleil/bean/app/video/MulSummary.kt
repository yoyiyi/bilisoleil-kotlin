package com.yoyiyi.soleil.bean.app.video

import com.chad.library.adapter.base.entity.MultiItemEntity

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/6/15 10:08
 * * 描述:
 */

class MulSummary(var itemTypez: Int = 0,
                 var desc: String? = null,
                 var title: String? = null,
                 var tags: List<VideoDetail.DataBean.TagBean>? = null, //标签
                 var state: VideoDetail.DataBean.StatBean? = null, //硬币 播放相关
                 var relates: VideoDetail.DataBean.RelatesBean? = null, //视频推荐
                 var ctime: Long = 0,
                 var owner: VideoDetail.DataBean.OwnerBean?= null) : MultiItemEntity {


    override fun getItemType(): Int = itemTypez


    companion object {

        val TYPE_DES = 34
        val TYPE_OWNER = 35
        val TYPE_RELATE = 36
        val TYPE_RELATE_HEAD = 37
    }
}
