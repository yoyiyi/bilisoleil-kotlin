package com.yoyiyi.soleil.mvp.presenter.region


import com.yoyiyi.soleil.base.BaseObjectSubscriber
import com.yoyiyi.soleil.base.RxPresenter
import com.yoyiyi.soleil.bean.region.RegionRecommend
import com.yoyiyi.soleil.mvp.contract.region.RegionTypeRecommendContract
import com.yoyiyi.soleil.network.helper.RetrofitHelper

import com.yoyiyi.soleil.rx.rxSchedulerHelper

import javax.inject.Inject

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/17 18:00
 * * 描述:分区推荐presenter
 */

class RegionTypeRecommendPresenter @Inject constructor(private val retrofitHelper: RetrofitHelper) : RxPresenter<RegionTypeRecommendContract.View>(), RegionTypeRecommendContract.Presenter<RegionTypeRecommendContract.View> {

    override fun getRegionRecommendData(tid: Int) {
        val subscriber = retrofitHelper.getRegionRecommend(tid)
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseObjectSubscriber<RegionRecommend>(mView) {
                    override fun onSuccess(t: RegionRecommend) {
                        mView?.showRegionRecommend(t)
                    }
                })
        addSubscribe(subscriber)
    }

}
