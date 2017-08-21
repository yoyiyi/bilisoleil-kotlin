package com.yoyiyi.soleil.mvp.presenter.search


import com.yoyiyi.soleil.base.BaseSubscriber
import com.yoyiyi.soleil.base.RxPresenter
import com.yoyiyi.soleil.bean.search.MulSearchArchive
import com.yoyiyi.soleil.event.Event
import com.yoyiyi.soleil.mvp.contract.search.ArchiveContract
import com.yoyiyi.soleil.rx.RxBus
import com.yoyiyi.soleil.rx.rxSchedulerHelper

import java.util.ArrayList

import javax.inject.Inject

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/17 18:00
 * * 描述:综合界面主页presenter
 */

class ArchivePresenter @Inject constructor() : RxPresenter<ArchiveContract.View>(), ArchiveContract.Presenter<ArchiveContract.View> {

    override fun getSearchArchiveData() {
        addSubscribe(RxBus.toFlowable(Event.SearchArchiveEvent::class.java)
                .doOnSubscribe { mView?.showLoading() }//设置加载动画
                .map {
                    val seasonCount = it.seasonCount
                    val movieCount = it.movieCount
                    val itemBean = it.itemBean
                    val archive = itemBean?.archive//首页推荐
                    val movie = itemBean?.movie//电影
                    val season = itemBean?.season//番剧
                    val mulSearchArchiveList = ArrayList<MulSearchArchive>()
                    season?.forEach { seasonBean ->
                        mulSearchArchiveList.add(//番剧
                                MulSearchArchive(itemTypez = MulSearchArchive.TYPE_SEASON, season = seasonBean))

                    }

                    mulSearchArchiveList.add(
                            MulSearchArchive(itemTypez = MulSearchArchive.TYPE_SEASON_MORE, seasonCount = seasonCount))//更多番剧
                    movie?.forEach {
                        mulSearchArchiveList.add(
                                MulSearchArchive(itemTypez = MulSearchArchive.TYPE_MOVIE, movie = it))

                    }

                    mulSearchArchiveList.add(
                            MulSearchArchive(itemTypez = MulSearchArchive.TYPE_MOVIE_MORE, movieCount = movieCount))//更多影视

                    archive?.forEach {
                        mulSearchArchiveList.add(
                                MulSearchArchive(itemTypez = MulSearchArchive.TYPE_ARCHIVE, archive = it))
                    }//主页推荐
                    mulSearchArchiveList
                }
                //  .delay(5, TimeUnit.SECONDS)
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseSubscriber<List<MulSearchArchive>>(mView) {
                    override fun onSuccess(t: List<MulSearchArchive>) {
                        mView?.showSearchArchive(t)
                    }
                }))
    }
}
