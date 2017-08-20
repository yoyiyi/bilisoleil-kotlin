package com.yoyiyi.soleil.mvp.presenter.app.up

import com.yoyiyi.soleil.base.BaseSubscriber
import com.yoyiyi.soleil.base.RxPresenter
import com.yoyiyi.soleil.bean.user.MulUpDetail
import com.yoyiyi.soleil.event.Event
import com.yoyiyi.soleil.mvp.contract.app.up.SubmitedVideoContract
import com.yoyiyi.soleil.rx.RxBus
import com.yoyiyi.soleil.rx.rxSchedulerHelper
import javax.inject.Inject

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/6/17 12:37
 * * 描述:
 */

class SubmitedVideoPresenter @Inject
constructor() : RxPresenter<SubmitedVideoContract.View>(), SubmitedVideoContract.Presenter<SubmitedVideoContract.View> {

    override fun getSubmitedVideoData() {
        val subscriber = RxBus.toFlowable(Event.UpDetailSubmitedVideoEvent::class.java)
                .map{
                    val archivList = it.archivList
                    val mulUpDetailList = mutableListOf<MulUpDetail>()
                    mulUpDetailList += MulUpDetail(itemTypez = MulUpDetail.TYPE_SUBMITED_VIDEO_ELEC)
                    archivList?.forEach {
                        mulUpDetailList.add(MulUpDetail(itemTypez = MulUpDetail.TYPE_SUBMITED_VIDEO_ITEM, archiveBean = it))
                    }
                    mulUpDetailList
                }
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseSubscriber<List<MulUpDetail>>(mView) {
                    override fun onSuccess(t: List<MulUpDetail>) {
                        mView?.showSubmitedVideo(t)
                    }
                })
        addSubscribe(subscriber)
    }
}
