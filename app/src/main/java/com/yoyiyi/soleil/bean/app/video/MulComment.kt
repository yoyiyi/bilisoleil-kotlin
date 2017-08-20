package com.yoyiyi.soleil.bean.app.video

import com.chad.library.adapter.base.entity.MultiItemEntity

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/6/15 10:08
 * * 描述:
 */

class MulComment(var itemTypez: Int = 0,
        var hotsBean: VideoDetailComment.DataBean.HotsBean? = null,
        var repliesBean: VideoDetailComment.DataBean.RepliesBean? = null)
    : MultiItemEntity {



    override fun getItemType(): Int = itemTypez


    companion object {


        val TYPE_COMMENT_HOT_ITEM = 2

        val TYPE_COMMENT_MORE = 3

        val TYPE_COMMENT_NOMAL_ITEM = 4
    }
}
