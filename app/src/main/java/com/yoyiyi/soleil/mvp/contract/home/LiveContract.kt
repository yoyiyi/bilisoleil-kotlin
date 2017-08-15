package com.yoyiyi.soleil.mvp.contract.home

import com.yoyiyi.soleil.base.BaseContract
import com.yoyiyi.soleil.bean.live.MulLive

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 *
 * @date 创建时间：2017/5/23 22:02
 * 描述:
 */
interface LiveContract {
    interface View : BaseContract.BaseView {

        fun showMulLive(mulLives: List<MulLive>)

    }

    interface Presenter<in T> : BaseContract.BasePresenter<T> {

        fun getLiveData()

    }
}
