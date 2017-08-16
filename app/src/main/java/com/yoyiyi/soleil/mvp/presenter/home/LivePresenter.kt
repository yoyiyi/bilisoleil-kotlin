package com.yoyiyi.soleil.mvp.presenter.home


import com.yoyiyi.soleil.base.RxPresenter
import com.yoyiyi.soleil.bean.live.LivePartition
import com.yoyiyi.soleil.bean.live.MulLive

import com.yoyiyi.soleil.mvp.contract.home.LiveContract
import com.yoyiyi.soleil.network.helper.RetrofitHelper
import com.yoyiyi.soleil.rx.handleResult
import com.yoyiyi.soleil.rx.rxSchedulerHelper
import javax.inject.Inject

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 *
 * @date 创建时间：2017/5/23 22:04
 * 描述:首页直播Presenter
 */
class LivePresenter @Inject constructor(val retrofitHelper: RetrofitHelper) : RxPresenter<LiveContract.View>(), LiveContract.Presenter<LiveContract.View> {

    override fun getLiveData() {
        var livePartition: LivePartition? = null
        val mulLives: MutableList<MulLive> = mutableListOf()
        addSubscribe(retrofitHelper.getLivePartition()
                .compose(handleResult())
                .flatMap({
                    livePartition = it
                    retrofitHelper.getLiveRecommend()
                })
                .compose(handleResult())
                .map {
                    with(it.recommend_data) {
                        val allot = lives.size.div(2)
                        mulLives += MulLive(itemTypez = MulLive.TYPE_BANNER, bannerBeanList = livePartition?.banner)//轮播条
                        mulLives += MulLive(itemTypez = MulLive.TYPE_ENTRANCE)//入口
                        if (banner_data == null) {
                            mulLives += MulLive(itemTypez = MulLive.TYPR_HEADER,
                                    title = partition.name,
                                    url = partition.sub_icon.src,
                                    count = partition.count)
                            mulLives += MulLive(itemTypez = MulLive.TYPE_RECOMMEND_ITEM,
                                    recommendLives = lives)
                            mulLives += MulLive(hasMore = false, itemTypez = MulLive.TYPE_FOOTER)
                        } else {
                            if (banner_data.size == 1) {
                                mulLives += MulLive(itemTypez = MulLive.TYPR_HEADER,
                                        title = partition.name,
                                        url = partition.sub_icon.src,
                                        count = partition.count)

                                val part1 = lives.subList(0, allot) //主体
                                mulLives += MulLive(itemTypez = MulLive.TYPE_RECOMMEND_ITEM,
                                        recommendLives = part1)

                                mulLives += MulLive(itemTypez = MulLive.TYPE_RECOMMEND_BANNER,
                                        bannerData = (banner_data)[0])


                                val part2 = lives.subList(allot, (lives.size))
                                mulLives += MulLive(itemTypez = MulLive.TYPE_RECOMMEND_ITEM,
                                        recommendLives = part2)


                                mulLives += MulLive(hasMore = false, itemTypez = MulLive.TYPE_FOOTER)

                            } else {

                                mulLives += MulLive(itemTypez = MulLive.Companion.TYPR_HEADER,
                                        title = partition.name,
                                        url = partition.sub_icon.src,
                                        count = partition.count)

                                mulLives += MulLive(itemTypez = MulLive.TYPE_RECOMMEND_BANNER,
                                        bannerData = (banner_data)[0])

                                val part1 = lives.subList(0, allot) //主体
                                mulLives += MulLive(itemTypez = MulLive.TYPE_RECOMMEND_ITEM,
                                        recommendLives = part1)

                                mulLives += MulLive(itemTypez = MulLive.TYPE_RECOMMEND_BANNER,
                                        bannerData = (banner_data)[1])

                                val part2 = lives.subList(allot, (lives.size))
                                mulLives += MulLive(itemTypez = MulLive.TYPE_RECOMMEND_ITEM,
                                        recommendLives = part2)

                                mulLives += MulLive(hasMore = false, itemTypez = MulLive.TYPE_FOOTER)

                            }
                        }
                    }

                    livePartition?.partitions?.let {
                        for ((index, element) in it.withIndex()) {
                            mulLives += MulLive(itemTypez = MulLive.TYPR_HEADER,
                                    title = element.partition.name,
                                    url = element.partition.sub_icon.src,
                                    count = element.partition.count)
                            val part = element.lives.subList(0, 4)
                            mulLives += MulLive(itemTypez = MulLive.TYPE_PARTY_ITEM,
                                    partityLives = part)
                            if (index == it.size.minus(1)) {
                                mulLives += MulLive(hasMore = true, itemTypez = MulLive.TYPE_FOOTER)
                            } else {
                                mulLives += MulLive(hasMore = false, itemTypez = MulLive.TYPE_FOOTER)
                            }
                        }
                    }
                    mulLives
                }
                .compose(rxSchedulerHelper())
                .subscribe {
                    mView?.complete()
                    mView?.showMulLive(it)
                }
        )
    }
}
