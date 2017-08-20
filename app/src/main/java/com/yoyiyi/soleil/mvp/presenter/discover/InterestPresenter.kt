package com.yoyiyi.soleil.mvp.presenter.discover


import com.yoyiyi.soleil.base.BaseObjectSubscriber
import com.yoyiyi.soleil.base.RxPresenter
import com.yoyiyi.soleil.bean.discover.Community
import com.yoyiyi.soleil.mvp.contract.discover.InterestContract
import com.yoyiyi.soleil.network.helper.RetrofitHelper
import com.yoyiyi.soleil.rx.handleResult
import com.yoyiyi.soleil.rx.rxSchedulerHelper
import javax.inject.Inject

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/17 18:00
 * * 描述:活动中心resenter
 */

class InterestPresenter @Inject constructor(private val retrofitHelper: RetrofitHelper) : RxPresenter<InterestContract.View>(), InterestContract.Presenter<InterestContract.View> {
    override fun getInterestData() {
        addSubscribe(retrofitHelper.getInterestAd()
                .compose(handleResult())
                .flatMap { interestAd ->
                    mView?.showInterestAd(interestAd)
                    retrofitHelper.getInterestCategrory()
                }
                .compose(handleResult())
                .flatMap { interestCategrory ->
                    mView?.showInterestCategrory(interestCategrory.result)
                    retrofitHelper.getCommunity()
                }
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseObjectSubscriber<Community>(mView) {
                    override fun onSuccess(t: Community) {
                        mView?.onComplete()
                        mView?.showCommunity(t)
                    }
                }))

    }
}
