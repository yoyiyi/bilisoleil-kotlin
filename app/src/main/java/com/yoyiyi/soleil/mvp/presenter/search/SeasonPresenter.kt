package com.yoyiyi.soleil.mvp.presenter.search


import com.yoyiyi.soleil.base.BaseSubscriber
import com.yoyiyi.soleil.base.RxPresenter
import com.yoyiyi.soleil.bean.search.Season
import com.yoyiyi.soleil.mvp.contract.search.SeasonContract
import com.yoyiyi.soleil.network.helper.RetrofitHelper
import com.yoyiyi.soleil.rx.rxSchedulerHelper
import javax.inject.Inject

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/17 18:00
 * * 描述:综合界面主页presenter
 */

class SeasonPresenter @Inject constructor(private val retrofitHelper: RetrofitHelper) : RxPresenter<SeasonContract.View>(), SeasonContract.Presenter<SeasonContract.View> {


    override fun getSearchSeasonData() {
        val subscriber = retrofitHelper.getSeason()
                .compose(rxSchedulerHelper())
                .doOnSubscribe { mView?.showLoading() }
                // .delay(5, TimeUnit.SECONDS)
                .subscribeWith(object : BaseSubscriber<Season>(mView) {
                    override fun onSuccess(t: Season) {
                        mView?.showSearchSeason(t)
                    }
                })
        addSubscribe(subscriber)
    }
}
