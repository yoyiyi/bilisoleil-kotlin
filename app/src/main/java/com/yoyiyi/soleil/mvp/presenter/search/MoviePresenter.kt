package com.yoyiyi.soleil.mvp.presenter.search


import com.yoyiyi.soleil.base.BaseSubscriber
import com.yoyiyi.soleil.base.RxPresenter
import com.yoyiyi.soleil.bean.search.Movie
import com.yoyiyi.soleil.mvp.contract.search.MovieContract
import com.yoyiyi.soleil.network.helper.RetrofitHelper

import com.yoyiyi.soleil.rx.rxSchedulerHelper

import javax.inject.Inject

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/17 18:00
 * * 描述:综合界面主页presenter
 */

class MoviePresenter @Inject constructor(private val retrofitHelper: RetrofitHelper) : RxPresenter<MovieContract.View>(), MovieContract.Presenter<MovieContract.View> {

    override fun getSearchMovieData() {
        val subscriber = retrofitHelper.getMovie()
                .compose(rxSchedulerHelper())
                .doOnSubscribe{ mView?.showLoading() }
                //   .delay(5, TimeUnit.SECONDS
                .subscribeWith(object : BaseSubscriber<Movie>(mView) {
                    override fun onSuccess(movie: Movie) {
                        mView?.showSearchMovie(movie)
                    }
                })
        addSubscribe(subscriber)
    }

}
