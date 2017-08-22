package com.yoyiyi.soleil.mvp.presenter.bangumi


import com.yoyiyi.soleil.base.BaseListSubscriber
import com.yoyiyi.soleil.base.RxPresenter
import com.yoyiyi.soleil.bean.bangumi.BangumiSchedule
import com.yoyiyi.soleil.mvp.contract.bangumi.BangumiScheduleContract
import com.yoyiyi.soleil.network.helper.RetrofitHelper
import com.yoyiyi.soleil.rx.rxSchedulerHelper
import javax.inject.Inject

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/17 18:00
 * * 描述:番剧时间表presenter
 */

class BangumiSchedulePresenter @Inject constructor(private val mRetrofitHelper: RetrofitHelper) : RxPresenter<BangumiScheduleContract.View>(), BangumiScheduleContract.Presenter<BangumiScheduleContract.View> {

    override fun getBangumiSchedule() {
        val subscriber = mRetrofitHelper.getBangumiSchedule()
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseListSubscriber<BangumiSchedule>(mView) {
                    override fun onSuccess(t: List<BangumiSchedule>) {
                        mView?.showBangumiSchedule(t)
                    }
                })
        addSubscribe(subscriber)
    }
}
