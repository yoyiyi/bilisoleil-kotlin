package com.yoyiyi.soleil.mvp.presenter.app.up


import com.yoyiyi.soleil.base.BaseSubscriber
import com.yoyiyi.soleil.base.RxPresenter
import com.yoyiyi.soleil.bean.user.UpDetail
import com.yoyiyi.soleil.mvp.contract.app.up.UpDetailContract
import com.yoyiyi.soleil.network.helper.RetrofitHelper
import com.yoyiyi.soleil.rx.rxSchedulerHelper
import javax.inject.Inject

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/23 9:44
 * * 描述:up详情Presenter
 */

class UpDetailPresenter @Inject constructor(private val mRetrofitHelper: RetrofitHelper) : RxPresenter<UpDetailContract.View>(), UpDetailContract.Presenter<UpDetailContract.View> {

    override fun getUpDetailData() {
        val subscriber = mRetrofitHelper.getUpDetail()
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseSubscriber<UpDetail>(mView) {
                    override fun onSuccess(t: UpDetail) {
                        mView?.showUpDetail(t)
                    }
                })
        addSubscribe(subscriber)

    }
}
