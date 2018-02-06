package com.yoyiyi.soleil.mvp.presenter.app.video


import com.yoyiyi.soleil.base.BaseSubscriber
import com.yoyiyi.soleil.base.RxPresenter
import com.yoyiyi.soleil.bean.app.video.VideoDetailComment
import com.yoyiyi.soleil.mvp.contract.app.video.VideoDetailContract
import com.yoyiyi.soleil.network.helper.RetrofitHelper
import com.yoyiyi.soleil.rx.rxSchedulerHelper
import javax.inject.Inject

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/23 9:44
 * * 描述:视频详情Presenter
 */

class VideoDetailPresenter @Inject constructor(private val retrofitHelper: RetrofitHelper) : RxPresenter<VideoDetailContract.View>(), VideoDetailContract.Presenter<VideoDetailContract.View> {

    override fun getVideoDetailData() {
        /* BaseObjectSubscriber<VideoDetailComment> subscriber = mRetrofitHelper.getVideoDetail()
                .flatMap(videoDetail -> {
                    mView.showVideoDetail(videoDetail);
                    return mRetrofitHelper.getVideoDetailComment();
                })
                .compose(RxUtils.rxSchedulerHelper())
                .subscribeWith(new BaseObjectSubscriber<VideoDetailComment>(mView) {
                    @Override
                    public void onSuccess(VideoDetailComment videoDetailComment) {
                        mView.showVideoDetailComment(videoDetailComment);
                    }
                });*/
        addSubscribe(retrofitHelper.getVideoDetail()
                .flatMap {
                    mView?.showVideoDetail(it.data)
                    retrofitHelper.getVideoDetailComment()
                }
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseSubscriber<VideoDetailComment>(mView) {
                    override fun onSuccess(t: VideoDetailComment) {
                        mView?.showVideoDetailComment(t.data)
                    }
                }))
    }
}
