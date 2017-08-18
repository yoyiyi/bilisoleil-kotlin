package com.yoyiyi.soleil.mvp.contract.home

import com.yoyiyi.soleil.base.BaseContract
import com.yoyiyi.soleil.bean.dynamic.MulDynamic

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/23 22:02
 * * 描述:分区
 */
interface DynamicContract {
    interface View : BaseContract.BaseView {
        fun showMulDynamic(mulDynamic: List<MulDynamic>)


    }

    interface Presenter<T> : BaseContract.BasePresenter<T> {
        fun getMulDynamicData()
    }
}
