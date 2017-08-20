package com.yoyiyi.soleil.bean.user

import com.chad.library.adapter.base.entity.MultiItemEntity

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/6/17 11:40
 * * 描述:up详情
 */

class MulUpDetail(var title: String? = null,
                  var itemTypez: Int = 0,
                  var position: Int = 0,
                  var count: Int = 0,
                  var spanSize: Int = 0,
                  var archiveBean: UpDetail.DataBean.ArchiveBean.ItemBean? = null,
                  var favouriteBean: UpDetail.DataBean.FavouriteBean.ItemBeanX? = null,
                  var favourite: UpDetail.DataBean.FavouriteBean? = null,
                  var archive: UpDetail.DataBean.ArchiveBean? = null,
                  var setting: UpDetail.DataBean.SettingBean? = null,
                  var live: UpDetail.DataBean.LiveBean? = null,
                  var state: Int = 0) : MultiItemEntity {


    override fun getItemType(): Int = itemTypez


    companion object {
        val ONE_SPAN_SIZE = 1// 占2
        val TWO_SPAN_SIZE = 2// 占1

        val TYPE_SUBMITED_VIDEO_ELEC = 1
        val TYPE_SUBMITED_VIDEO_ITEM = 2
        val TYPE_FAVOURITE_ITEM = 3

        val TYPE_ARCHIVE_LIVE = 4//直播
        val TYPE_ARCHIVE_HEAD = 5//头部
        val TYPE_ARCHIVE_ALL_SUBMIT_VIDEO = 6//全部投稿
        val TYPE_ARCHIVE_FAVOURITE = 7//收藏夹
    }
}
