package com.yoyiyi.soleil.mvp.presenter.app

import com.yoyiyi.soleil.base.BaseSubscriber
import com.yoyiyi.soleil.base.RxPresenter
import com.yoyiyi.soleil.bean.app.Splash
import com.yoyiyi.soleil.mvp.contract.app.SplashContract
import com.yoyiyi.soleil.network.helper.RetrofitHelper
import com.yoyiyi.soleil.rx.rxSchedulerHelper
import io.reactivex.Flowable
import java.util.concurrent.TimeUnit
import javax.inject.Inject


/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * @date 创建时间：2017/7/14 11:14
 * 描述:欢迎界面Presenter
 */
class SplashPresenter @Inject constructor(private val retrofitHelper: RetrofitHelper) : RxPresenter<SplashContract.View>(), SplashContract.Presenter<SplashContract.View> {

    override fun setCountDown() {
        val count = 5L
        addSubscribe(Flowable.interval(0, 1, TimeUnit.SECONDS)
                .map { count - it }
                .take(count + 1)
                .compose(rxSchedulerHelper())
                .subscribe { mView?.showCountDown(it.toInt()) })
    }

    override fun getSplashData() {
        addSubscribe(retrofitHelper.getSplash()
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseSubscriber<Splash>(mView) {
                    override fun onSuccess(t: Splash) {
                        if (t.code == 0) mView?.showSplash(t)
                    }

                    override fun onFailure(code: Int, message: String) {
                        mView?.showError(message)
                    }
                }))
    }
}
