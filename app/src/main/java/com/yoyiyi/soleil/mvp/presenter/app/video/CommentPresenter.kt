package com.yoyiyi.soleil.mvp.presenter.app.video


import com.yoyiyi.soleil.base.BaseSubscriber
import com.yoyiyi.soleil.base.RxPresenter
import com.yoyiyi.soleil.bean.app.video.MulComment
import com.yoyiyi.soleil.event.Event
import com.yoyiyi.soleil.mvp.contract.app.video.CommentContract
import com.yoyiyi.soleil.rx.RxBus
import com.yoyiyi.soleil.rx.rxSchedulerHelper
import javax.inject.Inject

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/23 9:44
 * * 描述:视频详情Presenter
 */

class CommentPresenter @Inject constructor() : RxPresenter<CommentContract.View>(), CommentContract.Presenter<CommentContract.View> {

    override fun getCommentData() {
        addSubscribe(RxBus.toFlowable(Event.VideoDetailCommentEvent::class.java)
                .map {
                    val videoDetailComment = it.videoDetailComment
                    val mulComments = mutableListOf<MulComment>()
                    videoDetailComment?.hots?.forEach {
                        mulComments.add(MulComment(itemTypez = MulComment.TYPE_COMMENT_HOT_ITEM, hotsBean = it))
                    }
                    mulComments.add(MulComment(itemTypez = MulComment.TYPE_COMMENT_MORE))

                    videoDetailComment?.replies?.forEach {
                        mulComments.add(MulComment(
                                itemTypez = MulComment.TYPE_COMMENT_NOMAL_ITEM,
                                repliesBean = it))
                    }
                    mulComments
                }
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseSubscriber<List<MulComment>>(mView) {
                    override fun onSuccess(t: List<MulComment>) {
                        mView!!.showComment(t)
                    }
                }))

    }
}
