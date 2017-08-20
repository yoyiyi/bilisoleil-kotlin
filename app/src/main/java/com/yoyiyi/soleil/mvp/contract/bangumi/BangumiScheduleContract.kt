package com.yoyiyi.soleil.mvp.contract.bangumi

import com.yoyiyi.soleil.base.BaseContract
import com.yoyiyi.soleil.bean.bangumi.BangumiSchedule

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/12 10:09
 * * 描述:全区排行Contract
 */

interface BangumiScheduleContract {

    interface View : BaseContract.BaseView {

        fun showBangumiSchedule(bangumiScheduleList: List<BangumiSchedule>)

    }

    interface Presenter<in T> : BaseContract.BasePresenter<T> {

        fun getBangumiSchedule()
    }
}
