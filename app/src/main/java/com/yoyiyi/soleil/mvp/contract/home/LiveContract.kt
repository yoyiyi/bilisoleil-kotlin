package com.yoyiyi.soleil.mvp.contract.home

import com.yoyiyi.soleil.base.BaseContract
import com.yoyiyi.soleil.bean.live.LivePartition
import com.yoyiyi.soleil.bean.live.LiveRecommend

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/23 22:02
 * * 描述:
 */
interface LiveContract {
    interface View : BaseContract.BaseView {
        fun showLiveRecommend(liveRecommend: LiveRecommend)

        fun showLivePartition(livePartition: LivePartition)

    }

    interface Presenter<T> : BaseContract.BasePresenter<T> {
        fun getLiveData()

    }
}
