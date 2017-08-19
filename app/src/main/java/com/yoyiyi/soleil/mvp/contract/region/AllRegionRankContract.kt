package com.yoyiyi.soleil.mvp.contract.region

import com.yoyiyi.soleil.base.BaseContract
import com.yoyiyi.soleil.bean.region.AllRegionRank

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/12 10:09
 * * 描述:全区排行Contract
 */

interface AllRegionRankContract {

    interface View : BaseContract.BaseView {

        fun showAllRegionRank(regionRank: List<AllRegionRank.RankBean.ListBean>)

    }

    interface Presenter<in T> : BaseContract.BasePresenter<T> {

        fun getAllRegionRankData(type: String)
    }
}
