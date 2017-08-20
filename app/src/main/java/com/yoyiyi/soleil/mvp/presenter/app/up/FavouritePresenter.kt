package com.yoyiyi.soleil.mvp.presenter.app.up

import com.yoyiyi.soleil.base.BaseSubscriber
import com.yoyiyi.soleil.base.RxPresenter
import com.yoyiyi.soleil.bean.user.MulUpDetail
import com.yoyiyi.soleil.event.Event
import com.yoyiyi.soleil.mvp.contract.app.up.FavouriteContract
import com.yoyiyi.soleil.rx.RxBus
import com.yoyiyi.soleil.rx.rxSchedulerHelper
import javax.inject.Inject

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/6/17 12:37
 * * 描述:
 */

class FavouritePresenter @Inject
constructor() : RxPresenter<FavouriteContract.View>(), FavouriteContract.Presenter<FavouriteContract.View> {

    override fun getFavouriteData() {
        val subscriber = RxBus.toFlowable(Event.UpDetailFavourteEvent::class.java)
                .map {
                    val favouriteList = it.favouriteList
                    val mulUpDetailList = mutableListOf<MulUpDetail>()
                    favouriteList?.forEach {
                        mulUpDetailList.add(MulUpDetail(itemTypez = MulUpDetail.TYPE_FAVOURITE_ITEM,
                                favouriteBean = it))
                    }
                    mulUpDetailList
                }
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseSubscriber<List<MulUpDetail>>(mView) {
                    override fun onSuccess(t: List<MulUpDetail>) {
                        mView?.showFavourite(t)
                    }
                })
        addSubscribe(subscriber)
    }

}
