

package com.yoyiyi.soleil.rx

import com.yoyiyi.soleil.network.exception.ApiException
import com.yoyiyi.soleil.network.response.HttpResponse
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.FlowableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 *
 * @date 创建时间：2017/8/14 9:58
 *  描述:
 */


/**
 * 统一线程处理
 * @param <T>
 * @return
 */
fun <T> rxSchedulerHelper(): FlowableTransformer<T, T> =
        FlowableTransformer { observable ->
            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }  //compose简化线程 统一处理线程


/**
 * 生成Flowable
 * @param <T>
 * @return
</T> */
fun <T> createData(t: T): Flowable<T> =
        Flowable.create<T>({ emitter ->
            try {
                emitter.onNext(t)
                emitter.onComplete()
            } catch (e: Exception) {
                emitter.onError(e)
            }
        }, BackpressureStrategy.BUFFER)


/**
 * 生成Flowable
 * @param <T>
 * @return <T>
 */
fun <T> createData(t: List<T>): Flowable<List<T>> =
        Flowable.create<List<T>>({ emitter ->
            try {
                emitter.onNext(t)
                emitter.onComplete()
            } catch (e: Exception) {
                emitter.onError(e)
            }
        }, BackpressureStrategy.BUFFER)


/**
 * 统一返回结果处理

 * @param <T>
 * *
 * @return
</T> */
fun <T> handleResult(): FlowableTransformer<HttpResponse<T>, T> =
        FlowableTransformer { httpResponseFlowable ->
            httpResponseFlowable
                    .flatMap<T> { httpResponse ->
                        if (httpResponse.code == 0) {
                            httpResponse.data?.let { return@flatMap createData(it) }
                            httpResponse.result?.let { return@flatMap createData(it) }
                            return@flatMap Flowable.error <T>(ApiException("服务器返回error"))
                        } else {
                            return@flatMap Flowable.error <T>(ApiException("服务器返回error"))
                        }
                    }
        }


/**
 * 统一返回结果处理
 * @param <T>
 * @return
 */
fun <T> handleListResult(): FlowableTransformer<HttpResponse<List<T>>, List<T>> =
        FlowableTransformer { httpResponseFlowable ->
            httpResponseFlowable
                    .flatMap<List<T>> { httpResponse ->
                        if (httpResponse.code == 0) {
                            httpResponse.data?.let { return@flatMap createData(it) }
                            httpResponse.result?.let { return@flatMap createData(it) }
                            return@flatMap Flowable.error <List<T>>(ApiException("服务器返回error"))
                        } else {
                            return@flatMap Flowable.error <List<T>>(ApiException("服务器返回error"))
                        }
                    }
        }
