package com.yoyiyi.soleil.base

import com.yoyiyi.soleil.network.exception.ApiException
import com.yoyiyi.soleil.network.response.HttpResponse
import com.yoyiyi.soleil.utils.AppUtils
import com.yoyiyi.soleil.utils.LogUtils
import com.yoyiyi.soleil.utils.net.NetworkUtils
import io.reactivex.subscribers.ResourceSubscriber
import retrofit2.HttpException
import java.net.SocketTimeoutException

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/12 11:40
 * * 描述:统一处理订阅者
 */

abstract class BaseListSubscriber<T>(private val view: BaseContract.BaseView?) : ResourceSubscriber<HttpResponse<List<T>>>() {
    private val msg: String? = null


    override fun onStart() {
        super.onStart()
        if (!NetworkUtils.isConnected(AppUtils.getAppContext())) {
            // Logger.d("没有网络");
        } else {

        }
    }

    override fun onComplete() {

    }

    override fun onNext(response: HttpResponse<List<T>>) {
        view ?: return
        view.complete()
        if (response.code == 0) {
            response.data?.let { onSuccess(it) }
            response.result?.let { onSuccess(it) }
        } else {
            //可以不处理任何东西
            onFailure(response.code, response.message ?: "未知错误")
        }
    }

    abstract fun onSuccess(t: List<T>)

    fun onFailure(code: Int, message: String) {

    }


    override fun onError(e: Throwable) {
        view?.let {
            if (!msg.isNullOrEmpty()) it.showError(msg!!)
            else {
                when (e) {
                    is ApiException -> it.showError(e.toString())
                    is SocketTimeoutException -> it.showError("服务器响应超时ヽ(≧Д≦)ノ")
                    is HttpException -> view.showError("数据加载失败ヽ(≧Д≦)ノ")
                    else -> {
                        it.showError("未知错误ヽ(≧Д≦)ノ")
                        LogUtils.e("MYERROR:" + e.toString())
                    }
                }
            }
        } ?: return
    }
}
