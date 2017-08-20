package com.yoyiyi.soleil.mvp.contract.discover

import com.yoyiyi.soleil.base.BaseContract
import com.yoyiyi.soleil.bean.discover.TopicCenter

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/12 10:09
 * * 描述:话题中心Contract
 */

interface TopicCenterContract {

    interface View : BaseContract.BaseView {

        fun showTopicCenter(topicCenterList: List<TopicCenter.ListBean>)
    }

    interface Presenter<in T> : BaseContract.BasePresenter<T> {

        fun getTopicCenterData()
    }
}
