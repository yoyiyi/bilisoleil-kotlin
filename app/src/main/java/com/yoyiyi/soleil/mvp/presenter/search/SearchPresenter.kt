package com.yoyiyi.soleil.mvp.presenter.search

import com.yoyiyi.soleil.base.BaseSubscriber
import com.yoyiyi.soleil.base.RxPresenter
import com.yoyiyi.soleil.bean.search.Search
import com.yoyiyi.soleil.mvp.contract.search.SearchContract
import com.yoyiyi.soleil.network.helper.RetrofitHelper
import com.yoyiyi.soleil.rx.rxSchedulerHelper
import javax.inject.Inject

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/6/18 14:00
 * * 描述:
 */

class SearchPresenter @Inject
constructor(private val retrofitHelper: RetrofitHelper) : RxPresenter<SearchContract.View>(), SearchContract.Presenter<SearchContract.View> {

    override fun getSearchData() {
        val subscriber = retrofitHelper.getSearch()
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseSubscriber<Search>(mView) {
                    override fun onSuccess(t: Search) {
                        mView?.showSearch(t)
                    }
                })
        addSubscribe(subscriber)
    }
}
