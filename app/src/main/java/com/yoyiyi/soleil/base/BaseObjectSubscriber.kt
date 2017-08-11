package com.yoyiyi.soleil.base

import com.yoyiyi.soleil.network.exception.ApiException
import com.yoyiyi.soleil.network.response.HttpResponse
import com.yoyiyi.soleil.utils.AppUtils
import com.yoyiyi.soleil.utils.LogUtils
import com.yoyiyi.soleil.utils.NetworkUtils
import io.reactivex.subscribers.ResourceSubscriber
import retrofit2.HttpException
import java.net.SocketTimeoutException

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/12 11:40
 * * 描述:统一处理订阅者
 */

abstract class BaseObjectSubscriber<T>(private val view: BaseContract.BaseView?) : ResourceSubscriber<HttpResponse<T>>() {


    private var msg: String? = null


    constructor(view: BaseContract.BaseView?, msg: String?) : this(view) {
        this.msg = msg
    }

    abstract fun onSuccess(t: T)

    fun onFailure(code: Int, message: String) {

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

    override fun onNext(response: HttpResponse<T>) {
        view ?: return
        view.complete()
        if (response.code == 0) {
            response.data?.let { onSuccess(response.data!!) }
            response.result?.let { onSuccess(response.result!!) }
        } else {
            //可以不处理任何东西
            onFailure(response.code, if (response.message == null) "未知错误" else response.message!!)
        }
    }


    override fun onError(e: Throwable) {
        view?.let {
            if (!msg.isNullOrEmpty()) view.showError(msg!!)
            else {
                when (e) {
                    is ApiException -> view.showError(e.toString())
                    is SocketTimeoutException -> view.showError("服务器响应超时ヽ(≧Д≦)ノ")
                    is HttpException -> view.showError("数据加载失败ヽ(≧Д≦)ノ")
                    else -> {
                        view.showError("未知错误ヽ(≧Д≦)ノ")
                        LogUtils.e("MYERROR:" + e.toString())
                    }
                }
            }
        } ?: return
    }
}
