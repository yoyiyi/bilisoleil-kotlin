package com.yoyiyi.soleil.base

/**
 * 基础契约类 用来管理 presenter 与 view
 * Created by zzq on 2016/12/20.
 */

interface BaseContract {

    interface BaseView {

        /**
         * 请求出错
         */
        fun showError(msg: String)

        /**
         * 请求完成
         */
        fun complete()
    }

    interface BasePresenter<in T> {

        /**
         * 绑定

         * @param view view
         */
        fun  attachView(view: T)

        /**
         * 解绑
         */
        fun detachView()
    }

}
