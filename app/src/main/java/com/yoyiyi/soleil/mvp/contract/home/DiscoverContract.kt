package com.yoyiyi.soleil.mvp.contract.home

import com.yoyiyi.soleil.base.BaseContract
import com.yoyiyi.soleil.bean.discover.HotSearchTag

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/12 10:09
 * * 描述:发现Contract
 */

interface DiscoverContract {

    interface View : BaseContract.BaseView {

        fun showHotSearchTag(recommend: HotSearchTag)
    }

    interface Presenter<in T> : BaseContract.BasePresenter<T> {

        fun getHotSearchTagData()
    }
}
