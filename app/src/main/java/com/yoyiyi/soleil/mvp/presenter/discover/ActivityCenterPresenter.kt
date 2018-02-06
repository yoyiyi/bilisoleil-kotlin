package com.yoyiyi.soleil.mvp.presenter.discover


import com.yoyiyi.soleil.base.BaseSubscriber
import com.yoyiyi.soleil.base.RxPresenter
import com.yoyiyi.soleil.bean.discover.ActivityCenter
import com.yoyiyi.soleil.mvp.contract.discover.ActivityCenterContract
import com.yoyiyi.soleil.network.helper.RetrofitHelper
import com.yoyiyi.soleil.rx.rxSchedulerHelper
import javax.inject.Inject

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 *
 * @date 创建时间：2017/5/17 18:00
 * 描述:活动中心resenter
 */

class ActivityCenterPresenter @Inject constructor(private val retrofitHelper: RetrofitHelper) : RxPresenter<ActivityCenterContract.View>(),
        ActivityCenterContract.Presenter<ActivityCenterContract.View> {

    override fun getActivityCenterData(page: Int, pageSize: Int) {
        addSubscribe(retrofitHelper.getActivityCenter(page, pageSize)
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseSubscriber<ActivityCenter>(mView) {
                    override fun onSuccess(t: ActivityCenter) {
                        mView?.showActivityCenter(t.list, t.total)
                    }
                }))
    }
}
