package com.yoyiyi.soleil.mvp.presenter.app.up

import com.yoyiyi.soleil.base.BaseSubscriber
import com.yoyiyi.soleil.base.RxPresenter
import com.yoyiyi.soleil.bean.user.MulUpDetail
import com.yoyiyi.soleil.event.Event
import com.yoyiyi.soleil.mvp.contract.app.up.ArchiveContract
import com.yoyiyi.soleil.rx.RxBus
import com.yoyiyi.soleil.rx.rxSchedulerHelper
import javax.inject.Inject

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/6/17 12:37
 * * 描述:
 */

class ArchivePresenter @Inject constructor() : RxPresenter<ArchiveContract.View>(), ArchiveContract.Presenter<ArchiveContract.View> {

    override fun getArchiveData() {
        val subscriber = RxBus.toFlowable(Event.UpDetailArchiveEvent::class.java)
                .map{
                    val archivList = it.archive?.item
                    val mulUpDetailList = mutableListOf<MulUpDetail>()

                    //正在直播
                    mulUpDetailList.add(MulUpDetail(itemTypez = MulUpDetail.TYPE_ARCHIVE_LIVE,
                            live = it.live,
                            spanSize = MulUpDetail.TWO_SPAN_SIZE)
                    )

                    mulUpDetailList.add(MulUpDetail(
                            spanSize = MulUpDetail.TWO_SPAN_SIZE,
                            state = 1,
                            title = "全部投稿",
                            count = it.archive?.count ?: 0,
                            itemTypez = MulUpDetail.TYPE_ARCHIVE_HEAD))//全部投稿

                    val position = intArrayOf(0)
                    //全部投稿内容
                    archivList?.take(2)
                            ?.forEach {
                                mulUpDetailList.add(MulUpDetail(position = position[0],
                                        itemTypez = MulUpDetail.TYPE_ARCHIVE_ALL_SUBMIT_VIDEO,
                                        spanSize = MulUpDetail.ONE_SPAN_SIZE,
                                        archiveBean = it))
                                position[0]++
                            }

                    mulUpDetailList.add(MulUpDetail(
                            title = "最近投硬币的视频",
                            spanSize = MulUpDetail.TWO_SPAN_SIZE,
                            itemTypez = MulUpDetail.TYPE_ARCHIVE_HEAD,
                            state = it.setting?.coins_video ?: 0)

                    )//最近硬币

                    mulUpDetailList.add(MulUpDetail(
                            title = "TA的收藏夹",
                            spanSize = MulUpDetail.TWO_SPAN_SIZE,
                            itemTypez = MulUpDetail.TYPE_ARCHIVE_HEAD,
                            state = it.setting?.fav_video ?: 0)
                    )//收藏夹

                    mulUpDetailList.add(MulUpDetail(
                            spanSize = MulUpDetail.TWO_SPAN_SIZE,
                            itemTypez = MulUpDetail.TYPE_ARCHIVE_FAVOURITE,
                            favourite = it.favourite))


                    mulUpDetailList.add(MulUpDetail(
                            title = "TA的追番",
                            spanSize = MulUpDetail.TWO_SPAN_SIZE,
                            itemTypez = MulUpDetail.TYPE_ARCHIVE_HEAD,
                            state = it.setting?.bangumi ?: 0))//追番


                    mulUpDetailList.add(MulUpDetail(
                            title = "TA的圈子",
                            spanSize = MulUpDetail.TWO_SPAN_SIZE,
                            itemTypez = MulUpDetail.TYPE_ARCHIVE_HEAD,
                            state = it.setting?.groups ?: 0))//圈子


                    mulUpDetailList.add(MulUpDetail(
                            title = "TA玩的游戏",
                            spanSize = MulUpDetail.TWO_SPAN_SIZE,
                            itemTypez = MulUpDetail.TYPE_ARCHIVE_HEAD,
                            state = it.setting?.played_game ?: 0))//游戏


                    mulUpDetailList
                }
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseSubscriber<List<MulUpDetail>>(mView) {
                    override fun onSuccess(t: List<MulUpDetail>) {
                        mView?.showArchive(t)
                    }
                })
        addSubscribe(subscriber)
    }
}
