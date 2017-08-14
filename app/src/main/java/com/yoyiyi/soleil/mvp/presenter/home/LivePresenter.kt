package com.yoyiyi.soleil.mvp.presenter.home


import com.yoyiyi.soleil.base.BaseObjectSubscriber
import com.yoyiyi.soleil.base.RxPresenter
import com.yoyiyi.soleil.bean.live.LivePartition
import com.yoyiyi.soleil.bean.live.LiveRecommend
import com.yoyiyi.soleil.bean.live.MulLive
import com.yoyiyi.soleil.mvp.contract.home.LiveContract
import com.yoyiyi.soleil.network.helper.RetrofitHelper
import com.yoyiyi.soleil.network.response.HttpResponse
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
    /*override fun getLiveData() {
        addSubscribe(retrofitHelper.getLivePartition()
                .compose(handleResult<LivePartition>())
                .flatMap { livePartition ->
                    mView?.showLivePartition(livePartition)
                    retrofitHelper.getLiveRecommend()
                }
                .compose(rxSchedulerHelper<HttpResponse<LiveRecommend>>())
                .subscribeWith(object : BaseObjectSubscriber<LiveRecommend>(mView) {
                    override fun onSuccess(liveRecommend: LiveRecommend) {
                        mView?.showLiveRecommend(liveRecommend)
                    }
                }))
    }*/

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
                     override fun onSuccess(liveRecommend: LiveRecommend) {
                         val allot = liveRecommend.recommend_data?.lives?.size?.div(2)
                         val mulLive = MulLive().setTitle()
                         mulLive.mItemType = MulLive.TYPE_BANNER

                         mulLives.add(MulLive(MulLive.TYPE_BANNER, MulLive.TWO_SPAN_SIZE, livePartition?.banner))//轮播条
                         mulLives.add(MulLive(MulLive.TYPE_ENTRANCE, MulLive.TWO_SPAN_SIZE))//入口

                         if (liveRecommend.recommend_data?.banner_data == null) {
                             mulLives.add(MulLive(MulLive.TYPR_HEADER,
                                     MulLive.TWO_SPAN_SIZE,
                                     liveRecommend.recommend_data?.partition?.name,
                                     liveRecommend.recommend_data?.partition?.sub_icon?.src,
                                     "${liveRecommend.recommend_data?.partition?.count}"))//头部

                             val part = liveRecommend.recommend_data?.lives //主体
                             part?.forEach { it ->
                                 mulLives.add(MulLive(MulLive.TYPE_RECOMMEND_ITEM,
                                         MulLive.ONE_SPAN_SIZE,
                                         it))
                             }
                             mulLives.add(MulLive(MulLive.TYPE_FOOTER, //尾部
                                     MulLive.TWO_SPAN_SIZE,
                                     false))

                         } else {
                             if (liveRecommend.recommend_data?.banner_data?.size == 1) {
                                 mulLives.add(MulLive(MulLive.TYPR_HEADER,
                                         MulLive.TWO_SPAN_SIZE,
                                         liveRecommend.recommend_data?.partition?.name,
                                         liveRecommend.recommend_data?.partition?.sub_icon?.src,
                                         "${liveRecommend.recommend_data?.partition?.count}"))//头部

                                 val part1 = liveRecommend.recommend_data?.lives?.subList(0, allot as Int) //主体
                                 part1?.forEach({ it ->
                                     mulLives.add(MulLive(MulLive.TYPE_RECOMMEND_ITEM,
                                             MulLive.ONE_SPAN_SIZE,
                                             it))
                                 })
                                 mulLives.add(MulLive(MulLive.TYPE_RECOMMEND_BANNER, //推荐
                                         MulLive.TWO_SPAN_SIZE,
                                         (liveRecommend.recommend_data?.banner_data)?.get(0)))

                                 val part2 = liveRecommend.recommend_data?.lives?.subList(allot as Int, (liveRecommend.recommend_data?.lives?.size as Int))
                                 part2?.forEach({ it ->
                                     mulLives.add(MulLive(MulLive.TYPE_RECOMMEND_ITEM,
                                             MulLive.ONE_SPAN_SIZE,
                                             it))
                                 })

                                 mulLives.add(MulLive(MulLive.TYPE_FOOTER, //尾部
                                         MulLive.TWO_SPAN_SIZE,
                                         false))

                             } else {
                                 mulLives.add(MulLive(MulLive.TYPE_RECOMMEND_BANNER, //推荐
                                         MulLive.TWO_SPAN_SIZE,
                                         (liveRecommend.recommend_data?.banner_data)?.get(0)))

                                 mulLives.add(MulLive(MulLive.TYPR_HEADER,
                                         MulLive.TWO_SPAN_SIZE,
                                         liveRecommend.recommend_data?.partition?.name,
                                         liveRecommend.recommend_data?.partition?.sub_icon?.src,
                                         "${liveRecommend.recommend_data?.partition?.count}"))//头部

                                 val part1 = liveRecommend.recommend_data?.lives?.subList(0, allot as Int) //主体

                                 part1?.forEach({ it ->
                                     mulLives.add(MulLive(MulLive.TYPE_RECOMMEND_ITEM,
                                             MulLive.ONE_SPAN_SIZE,
                                             it))
                                 })

                                 mulLives.add(MulLive(MulLive.TYPE_RECOMMEND_BANNER, //推荐
                                         MulLive.TWO_SPAN_SIZE,
                                         (liveRecommend.recommend_data?.banner_data)?.get(1)))

                                 val part2 = liveRecommend.recommend_data?.lives?.subList(allot as Int, (liveRecommend.recommend_data?.lives?.size as Int))
                                 part2?.forEach({ it ->
                                     mulLives.add(MulLive(MulLive.TYPE_RECOMMEND_ITEM,
                                             MulLive.ONE_SPAN_SIZE,
                                             it))
                                 })

                                 mulLives.add(MulLive(MulLive.TYPE_FOOTER, //尾部
                                         MulLive.TWO_SPAN_SIZE,
                                         false))
                             }
                         }

                         livePartition?.partitions?.let {
                             for ((index, element) in livePartition?.partitions?.withIndex()!!) {
                                 mulLives.add(MulLive(MulLive.TYPR_HEADER,
                                         MulLive.TWO_SPAN_SIZE,
                                         element.partition?.name,
                                         element.partition?.sub_icon?.src,
                                         "${element.partition?.count}"))//头部
                                 element.lives?.subList(0, 4)?.forEach {
                                     mulLives.add(MulLive(MulLive.TYPE_PARTY_ITEM,
                                             MulLive.ONE_SPAN_SIZE,
                                             it))//头部

                                 }
                                 if (index == livePartition?.partitions?.size?.minus(1)) {
                                     mulLives.add(MulLive(MulLive.TYPE_FOOTER, //尾部
                                             MulLive.TWO_SPAN_SIZE,
                                             true))
                                 } else {
                                     mulLives.add(MulLive(MulLive.TYPE_FOOTER, //尾部
                                             MulLive.TWO_SPAN_SIZE,
                                             false))
                                 }
                             }

                         }
                         mView?.showMulLive(mulLives)
                     }
                 }))
     }


}
