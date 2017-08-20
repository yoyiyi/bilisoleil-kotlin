package com.yoyiyi.soleil.mvp.presenter.discover


import com.yoyiyi.soleil.base.BaseSubscriber
import com.yoyiyi.soleil.base.RxPresenter
import com.yoyiyi.soleil.bean.discover.TopicCenter
import com.yoyiyi.soleil.mvp.contract.discover.TopicCenterContract
import com.yoyiyi.soleil.network.helper.RetrofitHelper
import com.yoyiyi.soleil.rx.rxSchedulerHelper
import javax.inject.Inject

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/17 18:00
 * * 描述:直播Presenter
 */

class TopicCenterPresenter @Inject
constructor(private val retrofitHelper: RetrofitHelper) : RxPresenter<TopicCenterContract.View>(), TopicCenterContract.Presenter<TopicCenterContract.View> {

    override fun getTopicCenterData() {
        addSubscribe(retrofitHelper.getTopicCenter()
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseSubscriber<TopicCenter>(mView) {
                    override fun onSuccess(t: TopicCenter) {
                        mView?.showTopicCenter(t.list)
                    }
                }))
    }

}
