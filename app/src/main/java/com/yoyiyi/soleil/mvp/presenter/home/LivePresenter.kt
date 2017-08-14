package com.yoyiyi.soleil.mvp.presenter.home


import com.yoyiyi.soleil.base.BaseObjectSubscriber
import com.yoyiyi.soleil.base.RxPresenter
import com.yoyiyi.soleil.bean.live.LivePartition
import com.yoyiyi.soleil.bean.live.LiveRecommend
import com.yoyiyi.soleil.bean.live.MulLive
import com.yoyiyi.soleil.bean.live.MulLive.Companion.TYPR_HEADER
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
                .flatMap({ it ->
                    livePartition = it
                    retrofitHelper.getLiveRecommend()
                })
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseObjectSubscriber<LiveRecommend>(mView) {
                    override fun onSuccess(t: LiveRecommend) {
                        val allot = t.recommend_data.lives.size.div(2)

                        mulLives.add(MulLive(itemTypez = MulLive.TYPE_BANNER, bannerBeanList = livePartition?.banner))//轮播条

                        mulLives.add(MulLive(itemTypez = MulLive.TYPE_ENTRANCE))//入口

                        if (t.recommend_data.banner_data == null) {
                            mulLives.add(MulLive(itemTypez = MulLive.TYPR_HEADER,
                                    title = t.recommend_data.partition.name,
                                    url = t.recommend_data.partition.sub_icon.src,
                                    count = t.recommend_data.partition.count))
                            mulLives.add(MulLive(itemTypez = MulLive.TYPE_RECOMMEND_ITEM,
                                    recommendLives = t.recommend_data.lives))
                            mulLives.add(MulLive(hasMore = false, itemTypez = MulLive.TYPE_FOOTER))
                        } else {
                            if (t.recommend_data.banner_data.size == 1) {
                                mulLives.add(MulLive(itemTypez = MulLive.TYPR_HEADER,
                                        title = t.recommend_data.partition.name,
                                        url = t.recommend_data.partition.sub_icon.src,
                                        count = t.recommend_data.partition.count))

                                val part1 = t.recommend_data.lives.subList(0, allot) //主体
                                mulLives.add(MulLive(itemTypez = MulLive.TYPE_RECOMMEND_ITEM,
                                        recommendLives = part1))

                                mulLives.add(MulLive(itemTypez = MulLive.TYPE_RECOMMEND_BANNER,
                                        bannerData = (t.recommend_data.banner_data)[0]))


                                val part2 = t.recommend_data.lives.subList(allot, (t.recommend_data.lives.size))
                                mulLives.add(MulLive(itemTypez = MulLive.TYPE_RECOMMEND_ITEM,
                                        recommendLives = part2))


                                mulLives.add(MulLive(hasMore = false, itemTypez = MulLive.TYPE_FOOTER))

                            } else {


                                mulLives.add(MulLive(itemTypez = TYPR_HEADER,
                                        title = t.recommend_data.partition.name,
                                        url = t.recommend_data.partition.sub_icon.src,
                                        count = t.recommend_data.partition.count))

                                mulLives.add(MulLive(itemTypez = MulLive.TYPE_RECOMMEND_BANNER,
                                        bannerData = (t.recommend_data.banner_data)[0]))

                                val part1 = t.recommend_data.lives.subList(0, allot) //主体
                                mulLives.add(MulLive(itemTypez = MulLive.TYPE_RECOMMEND_ITEM,
                                        recommendLives = part1))

                                mulLives.add(MulLive(itemTypez = MulLive.TYPE_RECOMMEND_BANNER,
                                        bannerData = (t.recommend_data.banner_data)[1]))

                                val part2 = t.recommend_data.lives.subList(allot, (t.recommend_data.lives.size))
                                mulLives.add(MulLive(itemTypez = MulLive.TYPE_RECOMMEND_ITEM,
                                        recommendLives = part2))

                                mulLives.add(MulLive(hasMore = false, itemTypez = MulLive.TYPE_FOOTER))

                            }
                        }

                        livePartition?.partitions?.let {
                            for ((index, element) in livePartition?.partitions?.withIndex()!!) {
                                mulLives.add(MulLive(itemTypez = MulLive.TYPR_HEADER,
                                        title = element.partition.name,
                                        url = element.partition.sub_icon.src,
                                        count = element.partition.count))
                                val part = element.lives.subList(0, 4)
                                mulLives.add(MulLive(itemTypez = MulLive.TYPE_PARTY_ITEM,
                                        partityLives = part))

                                if (index == livePartition?.partitions?.size?.minus(1)) {
                                    mulLives.add(MulLive(hasMore = true, itemTypez = MulLive.TYPE_FOOTER))
                                } else {
                                    mulLives.add(MulLive(hasMore = false, itemTypez = MulLive.TYPE_FOOTER))
                                }
                            }
                        }
                        mView?.showMulLive(mulLives)
                    }
                }))
    }


}
