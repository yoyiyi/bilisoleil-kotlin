package com.yoyiyi.soleil.mvp.contract.app.video


import com.yoyiyi.soleil.base.BaseContract
import com.yoyiyi.soleil.bean.app.video.MulComment

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/23 9:45
 * * 描述:欢迎界面Contract
 */

interface CommentContract {
    interface View : BaseContract.BaseView {
        fun showComment(mulComments: List<MulComment>)
    }

    interface Presenter<in T> : BaseContract.BasePresenter<T> {

        fun getCommentData()


    }
}
