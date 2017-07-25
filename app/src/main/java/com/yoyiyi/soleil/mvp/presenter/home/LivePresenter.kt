package com.yoyiyi.soleil.mvp.presenter.home


import com.yoyiyi.soleil.base.BaseObjectSubscriber
import com.yoyiyi.soleil.base.RxPresenter
import com.yoyiyi.soleil.bean.live.LiveRecommend
import com.yoyiyi.soleil.mvp.contract.home.LiveContract
import com.yoyiyi.soleil.network.helper.RetrofitHelper
import com.yoyiyi.soleil.rx.RxUtils
import javax.inject.Inject

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/23 22:04
 * * 描述:首页直播Presenter
 */
class LivePresenter @Inject constructor(val retrofitHelper: RetrofitHelper) : RxPresenter<LiveContract.View>(), LiveContract.Presenter<LiveContract.View> {
    override fun getLiveData() {
        val subscriber = retrofitHelper.getLivePartition()
                .compose(RxUtils.handleResult())
                .flatMap({ livePartition ->
                    mView?.showLivePartition(livePartition)
                    retrofitHelper.getLiveRecommend()
                })
                .compose(RxUtils.rxSchedulerHelper())
                .subscribeWith(object : BaseObjectSubscriber<LiveRecommend>(mView) {
                    override fun onSuccess(liveRecommend: LiveRecommend) {
                        mView?.showLiveRecommend(liveRecommend)
                    }
                })
        addSubscribe(subscriber)
    }

}
