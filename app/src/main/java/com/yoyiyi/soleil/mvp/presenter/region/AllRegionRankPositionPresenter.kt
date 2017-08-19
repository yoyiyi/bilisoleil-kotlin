package com.yoyiyi.soleil.mvp.presenter.region


import com.yoyiyi.soleil.base.RxPresenter
import com.yoyiyi.soleil.event.Event
import com.yoyiyi.soleil.mvp.contract.region.AllRegionRankPositionContract
import io.reactivex.functions.Consumer
import javax.inject.Inject

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/17 18:00
 * * 描述:综合界面搜索presenter
 */
class AllRegionRankPositionPresenter @Inject constructor() : RxPresenter<AllRegionRankPositionContract.View>(), AllRegionRankPositionContract.Presenter<AllRegionRankPositionContract.View> {

    override fun getEventPosition() {
        addRxBusSubscribe(Event.RegionEntrancePositionEvent::class.java, Consumer { mView?.showEventPosition(it.position) })
    }

}

