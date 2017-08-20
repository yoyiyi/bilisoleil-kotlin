package com.yoyiyi.soleil.mvp.presenter.bangumi


import com.yoyiyi.soleil.base.BaseSubscriber
import com.yoyiyi.soleil.base.RxPresenter
import com.yoyiyi.soleil.bean.bangumi.MulBangumiDetail
import com.yoyiyi.soleil.mvp.contract.bangumi.BangumiDetailContract
import com.yoyiyi.soleil.network.helper.RetrofitHelper
import com.yoyiyi.soleil.rx.handleResult
import com.yoyiyi.soleil.rx.rxSchedulerHelper
import java.util.*
import javax.inject.Inject

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/17 18:00
 * * 描述:番剧详情presenter
 */

class BangumiDetailPresenter @Inject constructor(private val retrofitHelper: RetrofitHelper) : RxPresenter<BangumiDetailContract.View>(), BangumiDetailContract.Presenter<BangumiDetailContract.View> {

    override fun getBangumiDetailData() {
        val mulBangumiDetails = mutableListOf<MulBangumiDetail>()
        val title = StringBuilder()
        val subscriber = retrofitHelper.getBangumiDetail()
                .compose(handleResult())
                .flatMap {
                    title.append(it.title)
                    val episodes = it.episodes
                    Collections.reverse(episodes)//反转

                    mulBangumiDetails += MulBangumiDetail(itemTypez = MulBangumiDetail.TYPE_HEAD,
                            playCount = it.play_count,
                            cover = it.cover,
                            favorites = it.favorites,
                            isFinish = it.is_finish)
                    mulBangumiDetails += MulBangumiDetail(itemTypez = MulBangumiDetail.TYPE_SEASON,
                            seasonsTitle = it.season_title,
                            seasonsBeanList = it.seasons)//分季节

                    mulBangumiDetails += MulBangumiDetail(itemTypez = MulBangumiDetail.TYPE_EPISODE_HEAD,
                            totalCount = it.total_count,
                            isFinish = it.is_finish)//分集头部

                    mulBangumiDetails += MulBangumiDetail(itemTypez = MulBangumiDetail.TYPE_EPISODE_ITEM, //分集
                            episodesBeans = episodes)

                    mulBangumiDetails += MulBangumiDetail(itemTypez = MulBangumiDetail.TYPE_CONTRACTED,
                            listBeanList = it.rank.list,
                            totalBpCount = it.rank.total_bp_count,
                            weekBpCount = it.rank.week_bp_count) //承包

                    mulBangumiDetails += MulBangumiDetail(
                            itemTypez = MulBangumiDetail.TYPE_DES,
                            evaluate = it.evaluate,
                            tagsBeanList = it.tags)//简介v

                    retrofitHelper.getBangumiDetailRecommend()
                }
                .compose(handleResult())
                .flatMap {
                    mulBangumiDetails += MulBangumiDetail(itemTypez = MulBangumiDetail.TYPE_RECOMMEND_HEAD)
                    mulBangumiDetails += MulBangumiDetail(itemTypez = MulBangumiDetail.TYPE_RECOMMEND_ITEM,
                            bangumiRecommendList = it.list)//推荐
                    retrofitHelper.getBangumiDetailComment()
                }
                .map {
                    mulBangumiDetails += MulBangumiDetail(itemTypez = MulBangumiDetail.TYPE_COMMENT_HEAD,
                            num = it.data.page.num,
                            account = it.data.page.acount)

                    it.data.hots.forEach { hotsBean ->
                        mulBangumiDetails += MulBangumiDetail(//热门评论
                                itemTypez = MulBangumiDetail.TYPE_COMMENT_HOT_ITEM,
                                hotsBean = hotsBean)
                    }
                    mulBangumiDetails += MulBangumiDetail(itemTypez = MulBangumiDetail.TYPE_COMMENT_MORE)
                    it.data.replies.forEach {
                        mulBangumiDetails += MulBangumiDetail(itemTypez = MulBangumiDetail.TYPE_COMMENT_NOMAL_ITEM, repliesBean = it)//普通评论
                    }
                    mulBangumiDetails
                }
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseSubscriber<List<MulBangumiDetail>>(mView) {
                    override fun onSuccess(t: List<MulBangumiDetail>) {
                        mView?.showMulBangumiDetail(t, title.toString())
                    }
                })
        addSubscribe(subscriber)
    }
}
