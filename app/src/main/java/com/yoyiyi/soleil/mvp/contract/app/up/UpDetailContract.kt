package com.yoyiyi.soleil.mvp.contract.app.up


import com.yoyiyi.soleil.base.BaseContract
import com.yoyiyi.soleil.bean.user.UpDetail

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/23 9:45
 * * 描述:up主详情Contract
 */

interface UpDetailContract {
    interface View : BaseContract.BaseView {

        fun showUpDetail(upDetail: UpDetail)

    }

    interface Presenter<in T> : BaseContract.BasePresenter<T> {

        fun getUpDetailData()

    }
}
