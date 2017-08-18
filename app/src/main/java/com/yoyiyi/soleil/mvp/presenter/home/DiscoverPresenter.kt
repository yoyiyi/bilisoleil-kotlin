package com.yoyiyi.soleil.mvp.presenter.home


import com.yoyiyi.soleil.base.BaseObjectSubscriber
import com.yoyiyi.soleil.base.RxPresenter
import com.yoyiyi.soleil.bean.discover.HotSearchTag
import com.yoyiyi.soleil.mvp.contract.home.DiscoverContract
import com.yoyiyi.soleil.network.helper.RetrofitHelper
import com.yoyiyi.soleil.rx.rxSchedulerHelper
import javax.inject.Inject

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/17 18:00
 * * 描述:直播Presenter
 */

class DiscoverPresenter @Inject constructor(private val retrofitHelper: RetrofitHelper) : RxPresenter<DiscoverContract.View>(), DiscoverContract.Presenter<DiscoverContract.View> {

    override fun getHotSearchTagData() {
        /* BaseSubscriber<HotSearchTag> subscriber = mRetrofitHelper.getHotSearchTag()
                .compose(RxUtils.rxSchedulerHelper())
                .subscribeWith(new BaseObjectSubscriber<HotSearchTag>(mView) {
                    @Override
                    public void onNext(HotSearchTag hotSearchTag) {
                        super.onNext(hotSearchTag);
                        mView.showHotSearchTag(hotSearchTag);
                    }
                });*/
        addSubscribe(retrofitHelper.getHotSearchTag()
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseObjectSubscriber<HotSearchTag>(mView) {
                    override fun onSuccess(hotSearchTag: HotSearchTag) {
                        mView?.showHotSearchTag(hotSearchTag)
                    }
                }))
    }
}
