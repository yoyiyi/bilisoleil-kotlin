package com.yoyiyi.soleil.mvp.presenter.recommend


import com.yoyiyi.soleil.base.BaseSubscriber
import com.yoyiyi.soleil.base.RxPresenter
import com.yoyiyi.soleil.bean.recommend.AllStationRank
import com.yoyiyi.soleil.mvp.contract.recommend.AllStationRankContract
import com.yoyiyi.soleil.network.helper.RetrofitHelper
import com.yoyiyi.soleil.rx.rxSchedulerHelper
import javax.inject.Inject

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/17 18:00
 * * 描述:全区排行presenter
 */
class AllStationRankPresenter @Inject constructor(private val retrofitHelper: RetrofitHelper) : RxPresenter<AllStationRankContract.View>(), AllStationRankContract.Presenter<AllStationRankContract.View> {


    /* @Override
    public void getAllRegionRankData(String type) {
        BaseSubscriber<AllRegionRank> subscriber = mRetrofitHelper.getAllRegionRank(type)
                .compose(RxUtils.rxSchedulerHelper())
                .subscribeWith(new BaseSubscriber<AllRegionRank>(mView) {
                    @Override
                    public void onSuccess(AllRegionRank allRegionRank) {
                        mView.showAllRegionRank(allRegionRank.rank.list);
                    }
                });
        addSubscribe(subscriber);

    }*/

    override fun getAllStationRankData(type: String) {
        addSubscribe(retrofitHelper.getAllStationRank(type)
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseSubscriber<AllStationRank>(mView) {
                    override fun onSuccess(t: AllStationRank) {
                        mView?.showAllStationRank(t.rank.list)
                    }
                }))
    }
}
