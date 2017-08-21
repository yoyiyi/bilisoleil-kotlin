package com.yoyiyi.soleil.mvp.contract.search

import com.yoyiyi.soleil.base.BaseContract

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/6/19 9:19
 * * 描述:基础的查找
 */

interface BaseSearchContract {
    interface View : BaseContract.BaseView {

        fun showLoading()
    }

    interface Presenter<in T> : BaseContract.BasePresenter<T>
}
