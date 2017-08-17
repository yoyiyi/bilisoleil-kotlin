package com.yoyiyi.soleil.mvp.presenter.home

import com.google.gson.Gson
import com.google.gson.JsonParser
import com.yoyiyi.soleil.base.BaseSubscriber
import com.yoyiyi.soleil.base.RxPresenter
import com.yoyiyi.soleil.bean.recommend.Recommend
import com.yoyiyi.soleil.mvp.contract.home.RecommendContract
import com.yoyiyi.soleil.network.helper.RetrofitHelper
import com.yoyiyi.soleil.rx.rxSchedulerHelper
import com.yoyiyi.soleil.utils.JsonUtils
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/23 22:04
 * * 描述:首页直播Presenter
 */
class RecommendPresenter @Inject constructor(private val retrofitHelper: RetrofitHelper) : RxPresenter<RecommendContract.View>(), RecommendContract.Presenter<RecommendContract.View> {

    override fun getRecommendData() {
        //需登录
        /*BaseListSubscriber<Recommend> subscriber = mRetrofitHelper.getRecommend()
                .compose(RxUtils.rxSchedulerHelper())
                .subscribeWith(new BaseListSubscriber<Recommend>(mView) {
                    @Override
                    public void onSuccess(List<Recommend> recommends) {
                        mView.showRecommend(recommends);
                    }
                });
        addSubscribe(subscriber);*/
        addSubscribe(Flowable.just(JsonUtils.readJson("recommend.json"))
                .map<List<Recommend>> {
                    val gson = Gson()
                    val `object` = JsonParser().parse(it).asJsonObject
                    val array = `object`.getAsJsonArray("data")
                    val recommends = array.map { gson.fromJson(it, Recommend::class.java) }
                    recommends
                }
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseSubscriber<List<Recommend>>(mView) {
                    override fun onSuccess(t: List<Recommend>) {
                        mView?.showRecommend(t)
                    }
                }))
    }
}
