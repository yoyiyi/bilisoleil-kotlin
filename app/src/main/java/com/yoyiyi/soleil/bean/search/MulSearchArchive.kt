package com.yoyiyi.soleil.bean.search

import com.chad.library.adapter.base.entity.MultiItemEntity

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/6/18 15:06
 * * 描述:
 */

class MulSearchArchive(
        var itemTypez: Int = 0,
        var seasonCount: Int = 0,
        var movieCount: Int = 0,
        var archive: Search.DataBean.ItemsBean.ArchiveBean? = null,
        var movie: Search.DataBean.ItemsBean.MovieBean? = null,
        var season: Search.DataBean.ItemsBean.SeasonBean? = null
) : MultiItemEntity {



    override fun getItemType(): Int= itemTypez


    companion object {
        val TYPE_SEASON = 1
        val TYPE_SEASON_MORE = 2
        val TYPE_MOVIE = 3
        val TYPE_MOVIE_MORE = 4
        val TYPE_ARCHIVE = 5
    }
}
