package com.yoyiyi.soleil.mvp.contract.discover

import com.yoyiyi.soleil.base.BaseContract
import com.yoyiyi.soleil.bean.discover.Community
import com.yoyiyi.soleil.bean.discover.InterestAd
import com.yoyiyi.soleil.bean.discover.InterestCategrory

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/12 10:09
 * * 描述:话题中心Contract
 */

interface InterestContract {

    interface View : BaseContract.BaseView {

        fun showInterestAd(interestAdList: InterestAd)

        fun showCommunity(community: Community)

        fun showInterestCategrory(interestCategroryList: List<InterestCategrory.ResultBean>)

        fun onComplete()
    }

    interface Presenter<in T> : BaseContract.BasePresenter<T> {

        fun getInterestData()
    }
}
