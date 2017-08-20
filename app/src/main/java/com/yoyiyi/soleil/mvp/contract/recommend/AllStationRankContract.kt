package com.yoyiyi.soleil.mvp.contract.recommend

import com.yoyiyi.soleil.base.BaseContract
import com.yoyiyi.soleil.bean.recommend.AllStationRank

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/12 10:09
 * * 描述:全区排行Contract
 */

interface AllStationRankContract {

    interface View : BaseContract.BaseView {

        fun showAllStationRank(regionRank: List<AllStationRank.RankBean.ListBean>)

    }

    interface Presenter<in T> : BaseContract.BasePresenter<T> {

        fun getAllStationRankData(type: String)
    }
}
