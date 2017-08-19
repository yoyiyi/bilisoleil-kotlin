package com.yoyiyi.soleil.mvp.presenter.region

import com.yoyiyi.soleil.base.BaseObjectSubscriber
import com.yoyiyi.soleil.base.RxPresenter
import com.yoyiyi.soleil.bean.region.RegionType
import com.yoyiyi.soleil.mvp.contract.region.RegionTypeContract
import com.yoyiyi.soleil.network.helper.RetrofitHelper

import com.yoyiyi.soleil.rx.rxSchedulerHelper

import javax.inject.Inject

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/23 22:04
 * * 描述:分区Type Presenter
 */
class RegionTypePresenter @Inject constructor(private val retrofitHelper: RetrofitHelper) : RxPresenter<RegionTypeContract.View>(), RegionTypeContract.Presenter<RegionTypeContract.View> {

    override fun getRegionTypeData(rid: Int) {
        addSubscribe(retrofitHelper.getRegionType(rid)
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseObjectSubscriber<RegionType>(mView) {
                    override fun onSuccess(t: RegionType) {
                        mView?.showRegionType(t)
                    }
                }))
    }
}
