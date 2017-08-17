package com.yoyiyi.soleil.mvp.contract.home

import com.yoyiyi.soleil.base.BaseContract
import com.yoyiyi.soleil.bean.recommend.Recommend

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/23 22:02
 * * 描述:
 */
interface RecommendContract {
    interface View : BaseContract.BaseView {
        fun showRecommend(recommend: List<Recommend>)
    }

    interface Presenter<in T> : BaseContract.BasePresenter<T> {
        fun getRecommendData()
    }
}
