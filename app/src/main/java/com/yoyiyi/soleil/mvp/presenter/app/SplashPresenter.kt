package com.yoyiyi.soleil.mvp.presenter.app

import com.yoyiyi.soleil.base.RxPresenter
import com.yoyiyi.soleil.mvp.contract.app.SplashContract
import com.yoyiyi.soleil.network.helper.RetrofitHelper
import javax.inject.Inject


/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * @date 创建时间：2017/7/14 11:14
 * 描述:欢迎界面Presenter
 */
class SplashPresenter @Inject constructor(retrofitHelper: RetrofitHelper) : RxPresenter<SplashContract.View>(), SplashContract.Presenter<SplashContract.View> {

    val retrofitHelper = retrofitHelper

    override fun setCountDown() {

    }

    override fun getSplashData() {

    }
}
