package com.yoyiyi.soleil.mvp.contract.app


import com.yoyiyi.soleil.base.BaseContract
import com.yoyiyi.soleil.bean.app.Splash

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/23 9:45
 * * 描述:欢迎界面Contract
 */

interface SplashContract {
    interface View : BaseContract.BaseView {
        fun showSplash(splash: Splash)

        fun showCountDown(count: Int)

    }


    interface Presenter<in T> : BaseContract.BasePresenter<T> {

        fun getSplashData()

        fun setCountDown()

    }
}
