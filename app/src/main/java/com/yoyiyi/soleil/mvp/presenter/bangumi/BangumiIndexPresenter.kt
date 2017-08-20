package com.yoyiyi.soleil.mvp.presenter.bangumi


import com.yoyiyi.soleil.base.BaseObjectSubscriber
import com.yoyiyi.soleil.base.RxPresenter
import com.yoyiyi.soleil.bean.bangumi.BangumiIndex
import com.yoyiyi.soleil.mvp.contract.bangumi.BangumiIndexContract
import com.yoyiyi.soleil.network.helper.RetrofitHelper
import com.yoyiyi.soleil.rx.rxSchedulerHelper
import javax.inject.Inject

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/17 18:00
 * * 描述:番剧索引presenter
 */

class BangumiIndexPresenter @Inject
constructor(private val mRetrofitHelper: RetrofitHelper) : RxPresenter<BangumiIndexContract.View>(), BangumiIndexContract.Presenter<BangumiIndexContract.View> {

    override fun getBangumiIndex() {
        val subscriber = mRetrofitHelper.getBangumiIndex()
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseObjectSubscriber<BangumiIndex>(mView) {
                    override fun onSuccess(t: BangumiIndex) {
                        mView?.showBangumiIndex(t)
                    }
                })
        addSubscribe(subscriber)

    }

}
