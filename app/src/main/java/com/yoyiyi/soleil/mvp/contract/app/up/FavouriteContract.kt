package com.yoyiyi.soleil.mvp.contract.app.up


import com.yoyiyi.soleil.base.BaseContract
import com.yoyiyi.soleil.bean.user.MulUpDetail

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/23 9:45
 * * 描述:欢迎界面Contract
 */

interface FavouriteContract {
    interface View : BaseContract.BaseView {

        fun showFavourite(mulUpDetailList: List<MulUpDetail>)
    }

    interface Presenter<in T> : BaseContract.BasePresenter<T> {

        fun getFavouriteData()

    }
}
