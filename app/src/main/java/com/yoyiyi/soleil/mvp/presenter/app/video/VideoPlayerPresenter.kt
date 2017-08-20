package com.yoyiyi.soleil.mvp.presenter.app.video


import com.yoyiyi.soleil.base.BaseSubscriber
import com.yoyiyi.soleil.base.RxPresenter
import com.yoyiyi.soleil.media.danmuku.BiliDanmukuDownloadUtil
import com.yoyiyi.soleil.mvp.contract.app.video.VideoPlayerContract
import com.yoyiyi.soleil.network.helper.RetrofitHelper
import com.yoyiyi.soleil.utils.AppUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser
import javax.inject.Inject

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/23 9:44
 * * 描述:启动界面Presenter
 */

class VideoPlayerPresenter @Inject constructor(private val retrofitHelper: RetrofitHelper) : RxPresenter<VideoPlayerContract.View>(), VideoPlayerContract.Presenter<VideoPlayerContract.View> {


    override fun getVideoPlayerData() {
        addSubscribe(retrofitHelper.getVideoPlayer()
                .doOnSubscribe({ mView?.showAnimLoading() })
                //.subscribeOn(Schedulers.io())
                .flatMap({ videoPlayer ->
                    AppUtils.runOnUI { mView?.showVideoPlayer(videoPlayer) }
                    val url = "http://comment.bilibili.com/2143345.xml"
                    BiliDanmukuDownloadUtil.downloadXML(url)//下载弹幕
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : BaseSubscriber<BaseDanmakuParser>(mView) {
                    override fun onSuccess(baseDanmakuParser: BaseDanmakuParser) {
                        mView?.showDanmaku(baseDanmakuParser)
                    }

                    override fun onFailure(code: Int, message: String) {
                        mView?.showError(message)
                    }
                }))
    }
}
