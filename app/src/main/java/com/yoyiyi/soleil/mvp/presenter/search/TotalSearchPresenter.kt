package com.yoyiyi.soleil.mvp.presenter.search


import com.yoyiyi.soleil.base.BaseSubscriber
import com.yoyiyi.soleil.base.RxPresenter
import com.yoyiyi.soleil.bean.search.SearchArchive
import com.yoyiyi.soleil.mvp.contract.search.TotalSearchContract
import com.yoyiyi.soleil.network.helper.RetrofitHelper
import com.yoyiyi.soleil.rx.handleResult
import com.yoyiyi.soleil.rx.rxSchedulerHelper
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/17 18:00
 * * 描述:直播Presenter
 */

class TotalSearchPresenter @Inject constructor(private val retrofitHelper: RetrofitHelper) : RxPresenter<TotalSearchContract.View>(), TotalSearchContract.Presenter<TotalSearchContract.View> {

    override fun getSearchNavData(keyword: String, page: Int, pagesize: Int) {
        val subscriber = retrofitHelper.getSearchArchive(keyword, page, pagesize)
                .doOnSubscribe { mView?.showLoading() }
                .delay(1, TimeUnit.SECONDS)
                .compose(handleResult())
                .map(SearchArchive::nav)
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseSubscriber<List<SearchArchive.NavBean>>(mView) {
                    override fun onSuccess(t: List<SearchArchive.NavBean>) {
                        mView?.showSearchNav(t)
                    }
                })
        addSubscribe(subscriber)
    }
}
