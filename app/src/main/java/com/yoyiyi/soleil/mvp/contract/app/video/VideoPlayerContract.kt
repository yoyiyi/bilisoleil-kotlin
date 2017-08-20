package com.yoyiyi.soleil.mvp.contract.app.video

import com.yoyiyi.soleil.base.BaseContract
import com.yoyiyi.soleil.bean.app.video.VideoPlayer

import master.flame.danmaku.danmaku.parser.BaseDanmakuParser

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/6/14 14:30
 * * 描述:
 */

interface VideoPlayerContract {
    interface View : BaseContract.BaseView {

        fun showVideoPlayer(videoPlayer: VideoPlayer)

        fun showAnimLoading()

        fun showDanmaku(baseDanmakuParser: BaseDanmakuParser)
    }

    interface Presenter<in T> : BaseContract.BasePresenter<T> {
        fun getVideoPlayerData()

    }
}
