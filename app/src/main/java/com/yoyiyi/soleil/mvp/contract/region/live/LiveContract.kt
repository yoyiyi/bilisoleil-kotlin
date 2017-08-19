package com.yoyiyi.soleil.mvp.contract.region.live

import com.yoyiyi.soleil.base.BaseContract
import com.yoyiyi.soleil.bean.live.LiveEntrance

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/12 10:09
 * * 描述:发现Contract
 */

interface LiveContract {

    interface View : BaseContract.BaseView {

        fun showLiveEntrance(liveEntrances: List<LiveEntrance>)

    }

    interface Presenter<in T> : BaseContract.BasePresenter<T> {

        fun getLiveEntranceData()
    }
}
