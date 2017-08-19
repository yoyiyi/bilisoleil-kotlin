package com.yoyiyi.soleil.mvp.contract.region

import com.yoyiyi.soleil.base.BaseContract

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/12 10:09
 * * 描述:发现Contract
 */

interface AllRegionRankPositionContract {

    interface View : BaseContract.BaseView {

        fun showEventPosition(postion: Int)

    }

    interface Presenter<in T> : BaseContract.BasePresenter<T> {

        fun getEventPosition()
    }
}
