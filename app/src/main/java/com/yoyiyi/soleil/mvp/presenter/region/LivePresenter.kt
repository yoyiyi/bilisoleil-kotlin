package com.yoyiyi.soleil.mvp.presenter.region


import com.yoyiyi.soleil.base.BaseListSubscriber
import com.yoyiyi.soleil.base.RxPresenter
import com.yoyiyi.soleil.bean.live.LiveEntrance
import com.yoyiyi.soleil.mvp.contract.region.live.LiveContract
import com.yoyiyi.soleil.network.helper.RetrofitHelper
import com.yoyiyi.soleil.rx.rxSchedulerHelper
import javax.inject.Inject

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/17 18:00
 * * 描述:综合界面搜索presenter
 */

class LivePresenter @Inject constructor(private val retrofitHelper: RetrofitHelper) : RxPresenter<LiveContract.View>(), LiveContract.Presenter<LiveContract.View> {

    override fun getLiveEntranceData() {
        addSubscribe(retrofitHelper.getLiveEntrance()
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseListSubscriber<LiveEntrance>(mView) {
                   override fun onSuccess(t: List<LiveEntrance>) {
                        mView?.showLiveEntrance(t)
                    }
                }))

    }

}
