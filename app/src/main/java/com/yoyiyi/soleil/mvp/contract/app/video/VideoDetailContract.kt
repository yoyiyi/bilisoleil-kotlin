package com.yoyiyi.soleil.mvp.contract.app.video

import com.yoyiyi.soleil.base.BaseContract
import com.yoyiyi.soleil.bean.app.video.VideoDetail
import com.yoyiyi.soleil.bean.app.video.VideoDetailComment

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/6/14 14:30
 * * 描述:
 */

interface VideoDetailContract {
    interface View : BaseContract.BaseView {
        fun showVideoDetail(videoDetail: VideoDetail.DataBean)

        fun showVideoDetailComment(videoDetailComment: VideoDetailComment.DataBean)
    }

    interface Presenter<in T> : BaseContract.BasePresenter<T> {
        fun getVideoDetailData()

    }
}
