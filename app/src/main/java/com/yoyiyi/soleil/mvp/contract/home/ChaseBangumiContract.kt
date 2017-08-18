package com.yoyiyi.soleil.mvp.contract.home

import com.yoyiyi.soleil.base.BaseContract
import com.yoyiyi.soleil.bean.chase.ChaseBangumi
import com.yoyiyi.soleil.bean.chase.RecommendBangumi

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/23 22:02
 * * 描述:
 */
interface ChaseBangumiContract {
    interface View : BaseContract.BaseView {
        fun showChaseBangumi(chaseBangumi: ChaseBangumi)

        fun showRecommendBangumi(recommendBangumi: RecommendBangumi)

    }

    interface Presenter<in T> : BaseContract.BasePresenter<T> {

        fun getChaseBangumiData()

    }
}
