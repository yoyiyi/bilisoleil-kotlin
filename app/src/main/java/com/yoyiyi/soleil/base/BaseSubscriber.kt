package com.yoyiyi.soleil.base

import com.yoyiyi.soleil.network.exception.ApiException
import com.yoyiyi.soleil.utils.AppUtils
import com.yoyiyi.soleil.utils.LogUtils
import com.yoyiyi.soleil.utils.net.NetworkUtils
import io.reactivex.subscribers.ResourceSubscriber
import retrofit2.HttpException
import java.net.SocketTimeoutException

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * @date 创建时间：2017/5/12 11:40
 * 描述:统一处理订阅者
 */

abstract class BaseSubscriber<T>(private val view: BaseContract.BaseView?) : ResourceSubscriber<T>() {

    private var msg: String? = null

    abstract fun onSuccess(t: T)

    constructor(view: BaseContract.BaseView?, msg: String?) : this(view) {
        this.msg = msg
    }

    open fun onFailure(code: Int, message: String) {

    }

    override fun onStart() {
        super.onStart()
        if (!NetworkUtils.isConnected(AppUtils.getAppContext())) {
            // Logger.d("没有网络");
        } else {

        }
    }

    override fun onComplete() {

    }

    override fun onNext(response: T) {
        view?.let {
            it.complete()
            onSuccess(response)
        } ?: return@onNext
    }


    override fun onError(e: Throwable) {
        view?.let {
            if (!msg.isNullOrEmpty()) it.showError(msg!!)
            else {
                when (e) {
                    is ApiException -> it.showError(e.toString())
                    is SocketTimeoutException -> it.showError("服务器响应超时ヽ(≧Д≦)ノ")
                    is HttpException -> it.showError("数据加载失败ヽ(≧Д≦)ノ")
                    else -> {
                        view.showError("未知错误ヽ(≧Д≦)ノ")
                        LogUtils.e("MYERROR:" + e.toString())
                    }
                }
            }
        } ?: return
    }
}
