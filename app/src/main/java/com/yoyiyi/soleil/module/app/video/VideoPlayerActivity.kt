package com.yoyiyi.soleil.module.app.video

import android.annotation.SuppressLint
import android.graphics.drawable.AnimationDrawable
import android.net.Uri
import android.view.View
import com.yoyiyi.soleil.R
import com.yoyiyi.soleil.base.BaseInjectActivity
import com.yoyiyi.soleil.bean.app.video.VideoPlayer
import com.yoyiyi.soleil.media.MediaController
import com.yoyiyi.soleil.media.VideoPlayerView
import com.yoyiyi.soleil.media.callback.DanmukuSwitchListener
import com.yoyiyi.soleil.media.callback.VideoBackListener
import com.yoyiyi.soleil.mvp.contract.app.video.VideoPlayerContract
import com.yoyiyi.soleil.mvp.presenter.app.video.VideoPlayerPresenter
import kotlinx.android.synthetic.main.activity_video_player.*
import master.flame.danmaku.controller.DrawHandler
import master.flame.danmaku.danmaku.model.BaseDanmaku
import master.flame.danmaku.danmaku.model.DanmakuTimer
import master.flame.danmaku.danmaku.model.IDisplayer
import master.flame.danmaku.danmaku.model.android.DanmakuContext
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser
import tv.danmaku.ijk.media.player.IMediaPlayer
import java.util.*

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/6/28 14:36
 * * 描述:视频播放界面
 */

class VideoPlayerActivity : BaseInjectActivity<VideoPlayerPresenter>(), DanmukuSwitchListener, VideoBackListener, VideoPlayerContract.View {


    private var danmakuContext: DanmakuContext? = null
    private var mStartText = "初始化播放器..."
    private var mLoadingAnim: AnimationDrawable? = null
    private var mLastPosition = 0L
    private var mMaxLinesPair: HashMap<Int, Int>? = null// 弹幕最大行数
    private var mOverlappingEnablePair: HashMap<Int, Boolean>? = null// 设置是否重叠

    override fun getLayoutId(): Int = R.layout.activity_video_player


    override fun initWidget() {
        super.initWidget()
        initDanmaku()//初始化弹幕库
        initMediaPlayer()//初始化播放器
    }

    private fun initDanmaku() {
        danmakuContext = DanmakuContext.create()
        // 设置最大行数,从右向左滚动(有其它方向可选)
        mMaxLinesPair = HashMap<Int, Int>()
        mMaxLinesPair?.put(BaseDanmaku.TYPE_SCROLL_RL, 3)
        //配置弹幕库
        danmaku.enableDanmakuDrawingCache(true)
        // 设置是否禁止重叠
        mOverlappingEnablePair = HashMap<Int, Boolean>()
        mOverlappingEnablePair?.put(BaseDanmaku.TYPE_SCROLL_LR, true)
        mOverlappingEnablePair?.put(BaseDanmaku.TYPE_FIX_BOTTOM, true)
        danmakuContext?.setDanmakuStyle(IDisplayer.DANMAKU_STYLE_STROKEN, 3F)
                ?.setDuplicateMergingEnabled(false)
                ?.setScrollSpeedFactor(1.2f) //是否启用合并重复弹幕
                ?.setScaleTextSize(1.2f) //设置弹幕滚动速度系数,只对滚动弹幕有效
                // 默认使用{@link SimpleTextCacheStuffer}只支持纯文字显示,
                // 如果需要图文混排请设置{@link SpannedCacheStuffer}
                // 如果需要定制其他样式请扩展{@link SimpleTextCacheStuffer}|{@link SpannedCacheStuffer}
                ?.setMaximumLines(mMaxLinesPair) //设置最大显示行数
                ?.preventOverlapping(mOverlappingEnablePair) //设置防弹幕重叠，null为允许重叠

    }

    override fun initToolbar() {
        super.initToolbar()
    }

    @SuppressLint("UseSparseArrays")
    private fun initMediaPlayer() {
        //配置播放器
        val mMediaController = MediaController(this)
        mMediaController.setTitle("测试视频")
        palyer_view.setMediaController(mMediaController)
        palyer_view.setMediaBufferingIndicator(rl_loading)
        palyer_view.requestFocus()
        palyer_view.setOnInfoListener(onInfoListener)
        palyer_view.setOnSeekCompleteListener(onSeekCompleteListener)
        palyer_view.setOnCompletionListener(onCompletionListener)
        palyer_view.setOnControllerEventsListener(onControllerEventsListener)
        //设置弹幕开关监听
        mMediaController.setDanmakuSwitchListener(this)
        //设置返回键监听
        mMediaController.setVideoBackEvent(this)


    }

    /**
     * 初始化加载动画
     */
    private fun initAnimation() {
        rl_start.visibility = View.VISIBLE
        mStartText = "$mStartText【完成】\n解析视频地址...【完成】\n全舰弹幕填装..."
        tv_start.text = mStartText
        mLoadingAnim = iv_bili_loading?.background as AnimationDrawable
        mLoadingAnim?.start()
    }


    /**
     * 视频缓冲事件回调
     */
    private val onInfoListener = IMediaPlayer.OnInfoListener { _, what, _ ->
        if (what == IMediaPlayer.MEDIA_INFO_BUFFERING_START) {
            if (danmaku != null && danmaku.isPrepared) {
                danmaku.pause()
                rl_loading.visibility = View.VISIBLE
            }
        } else if (what == IMediaPlayer.MEDIA_INFO_BUFFERING_END) {
            if (danmaku != null && danmaku.isPaused) {
                danmaku.resume()
            }
            rl_loading?.visibility = View.GONE
        }
        true
    }
    /**
     * 视频跳转事件回调
     */
    private val onSeekCompleteListener = IMediaPlayer.OnSeekCompleteListener { mp ->
        if (danmaku != null && danmaku.isPrepared) {
            danmaku?.seekTo(mp.currentPosition)
        }
    }

    /**
     * 视频播放完成事件回调
     */
    private val onCompletionListener = IMediaPlayer.OnCompletionListener {
        if (danmaku != null && danmaku.isPrepared) {
            danmaku.seekTo(0.toLong())
            danmaku.pause()
        }
        palyer_view.pause()
    }

    /**
     * 控制条控制状态事件回调
     */
    private val onControllerEventsListener = object : VideoPlayerView.OnControllerEventsListener {
        override fun onVideoPause() {
            if (danmaku != null && danmaku.isPrepared) {
                danmaku.pause()
            }
        }

        override fun OnVideoResume() {
            if (danmaku != null && danmaku.isPaused) {
                danmaku.resume()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (danmaku != null && danmaku.isPrepared && danmaku.isPaused) {
            danmaku?.seekTo(mLastPosition)
        }
        if (palyer_view != null && !palyer_view.isPlaying) {
            palyer_view?.seekTo(mLastPosition)
        }
    }

    override fun onPause() {
        super.onPause()
        if (palyer_view != null) {
            mLastPosition = (palyer_view.currentPosition).toLong()
            palyer_view.pause()
        }
        if (danmaku != null && danmaku.isPrepared) {
            danmaku.pause()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (danmaku != null) {
            danmaku.release()
        }
        if (mLoadingAnim != null) {
            mLoadingAnim?.stop()
            mLoadingAnim = null
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (palyer_view != null && palyer_view.isDrawingCacheEnabled) {
            palyer_view?.destroyDrawingCache()
        }
        if (danmaku != null && danmaku.isPaused) {
            danmaku?.release()//释放弹幕库
        }
        if (mLoadingAnim != null) {
            mLoadingAnim?.stop()
            mLoadingAnim = null
        }
    }

    /**
     * 弹幕开关回调

     * @param isShow
     */
    override fun setDanmakushow(isShow: Boolean) {
        if (danmaku != null) {
            if (isShow) {
                danmaku.show()
            } else {
                danmaku.hide()
            }
        }
    }


    /**
     * 退出界面回调
     */
    override fun back() {
        onBackPressed()
    }

    override fun initInject() {
        activityComponent.inject(this)
    }

    override fun initPresenter() {
        mPresenter.attachView(this)
    }

    override fun loadData() {
        mPresenter.getVideoPlayerData()
    }

    override fun showVideoPlayer(videoPlayer: VideoPlayer) {
        //String uri1 = videoPlayer.durl.get(0).url;
        /* uri1 = "http://122.228.103.130/vg5/2/c0/6191437-1.mp4?expires=1498716900&platform=android&ssig=hKQiMO6c9Bj_OJfhu3K3vA&oi=3080483261" +
                "&nfa=zn2OTN7O9p3rqnr0+3S2RQ==&dynamic=1&hfa=2070368267";*/
        // http://115.231.179.113/6572787CE774C845BDFF103E5C/03000804005948C7BE58C6011BA6A93F75FF26-AA49-0A30-9D4B-7871503F37CE.mp4?ccode=0501&duration=395&expire=18000&psid=862909d3e3467a9b0efff6cf8f704e5f&ups_client_netip=183.156.113.189&ups_ts=1498705420&ups_userid=&utid=c1rbEdI5mFcCAbeccb16JLgD&vid=XMjgzNzQ2MDIyMA%3D%3D&vkey=A4e5ad15f9e348b316246f54a6ecf4552

        val uri = "http://112.25.47.111/6571EA9C4C13D776444793BD0/0300080400594FCDDF4780011BA6A94356A652-5121-05EF-7A49-F7F133184645.mp4?ccode=0501&duration=390&expire=18000&psid=0c186136db0b550753f4e15897a344b7&ups_client_netip=112.10.94.233&ups_ts=1498743715&ups_userid=&utid=3erbEXmBygUCAXAKXulsTPfb&vid=XMjg0OTI4ODEyOA%3D%3D&vkey=A55ec5ed693a02bd1aa246fb9c7700277"
        // String uri = "http://115.231.179.113/6572787CE774C845BDFF103E5C/03000804005948C7BE58C6011BA6A93F75FF26-AA49-0A30-9D4B-7871503F37CE.mp4?ccode=0501&duration=395" +
        //      "&expire=18000&psid=862909d3e3467a9b0efff6cf8f704e5f&ups_client_netip=183.156.113.189&";

        val uri2 = "http://api1.ckmov.com/data/youku%7D/2_XMTYzNTQ5Njk2OA_data.m3u8"
        val uri1 = "http://liveal.quanmin.tv/live/1578745132.flv"

        val uri3 = "http://115.231.178.214/youku/6771C614C954884205FCB635D3/0300080200595F10A2C83E011BA6A954FFBC75-10A1-FA2F-4B19-2BA6A02BE6B7.mp4?sid=049994114462312aaff0f&ctype=12&ccode=0590&duration=390&expire=18000&psid=c61df9c484b2d303fcb5e1b81523aae4&ups_client_netip=183.159.183.142&ups_ts=1499941144&ups_userid=&utid=c1rbEdI5mFcCAbeccb16JLgD&vid=XNjcyNjI0MjIw&vkey=A74cc899cf52a437b4bee85888cacc1bc"

        palyer_view.setVideoURI(Uri.parse(uri3))
        palyer_view.setOnPreparedListener({
            mLoadingAnim?.stop()
            mStartText = "$mStartText【完成】\n视频缓冲中..."
            tv_start?.text = mStartText
            rl_loading?.visibility = View.GONE
        })
    }

    override fun showAnimLoading() {
        initAnimation()
    }

    override fun showDanmaku(baseDanmakuParser: BaseDanmakuParser) {
        gone(rl_loading, rl_start)
        danmaku.prepare(baseDanmakuParser, danmakuContext)
        danmaku?.showFPS(false)//是否显示FPS
        danmaku?.enableDanmakuDrawingCache(true)
        danmaku?.setCallback(object : DrawHandler.Callback {
            override fun prepared() {
                danmaku.start()
            }

            override fun updateTimer(danmakuTimer: DanmakuTimer) {

            }

            override fun danmakuShown(danmaku: BaseDanmaku) {

            }

            override fun drawingFinished() {

            }
        })
        palyer_view.start()
    }

    override fun showError(msg: String) {
        mStartText = "$mStartText【失败】\n视频缓冲中..."
        tv_start.text = mStartText
        mStartText = mStartText + "【失败】\n" + msg
        tv_start.text = mStartText
    }

}
