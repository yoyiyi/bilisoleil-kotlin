package com.yoyiyi.soleil.base

import com.yoyiyi.soleil.rx.RxBus
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer

/**
 * Created by zzq on 2016/12/20.
 * 基于Rx的Presenter封装,控制订阅的生命周期
 */
open class RxPresenter<T : BaseContract.BaseView> : BaseContract.BasePresenter<T> {

    var mView: T? = null
    var mCompositeDisposable: CompositeDisposable? = null

    fun unSubscribe() {
        mCompositeDisposable?.dispose()
    }

    fun addSubscribe(disposable: Disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = CompositeDisposable()
        } else {
            mCompositeDisposable?.add(disposable)
        }
    }

    fun <K> addRxBusSubscribe(eventType: Class<K>, act: Consumer<K>) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = CompositeDisposable()
        } else {
            mCompositeDisposable?.add(RxBus.toDefaultFlowable(eventType, act))
        }
    }

    override fun detachView() {
        this.mView = null
        unSubscribe()
    }

    override fun attachView(view: T) {
        this.mView = view
    }
}
