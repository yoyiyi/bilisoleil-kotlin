package com.yoyiyi.soleil.mvp.presenter.region


import com.yoyiyi.soleil.base.BaseSubscriber
import com.yoyiyi.soleil.base.RxPresenter
import com.yoyiyi.soleil.bean.region.AllRegionRank
import com.yoyiyi.soleil.mvp.contract.region.AllRegionRankContract
import com.yoyiyi.soleil.network.helper.RetrofitHelper
import com.yoyiyi.soleil.rx.rxSchedulerHelper
import javax.inject.Inject

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/17 18:00
 * * 描述:全区排行presenter
 */
class AllRegionRankPresenter @Inject constructor(private val retrofitHelper: RetrofitHelper) : RxPresenter<AllRegionRankContract.View>(), AllRegionRankContract.Presenter<AllRegionRankContract.View> {


    override fun getAllRegionRankData(type: String) {
        addSubscribe(retrofitHelper.getAllRegionRank(type)
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseSubscriber<AllRegionRank>(mView) {
                    override fun onSuccess(t: AllRegionRank) {
                        mView?.showAllRegionRank(t.rank.list)
                    }
                }))

    }
}
