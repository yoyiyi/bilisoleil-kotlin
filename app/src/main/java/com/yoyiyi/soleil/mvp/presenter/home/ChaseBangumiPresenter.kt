package com.yoyiyi.soleil.mvp.presenter.home

import com.google.gson.Gson
import com.google.gson.JsonParser
import com.yoyiyi.soleil.base.BaseSubscriber
import com.yoyiyi.soleil.base.RxPresenter
import com.yoyiyi.soleil.bean.chase.ChaseBangumi
import com.yoyiyi.soleil.bean.chase.RecommendBangumi
import com.yoyiyi.soleil.mvp.contract.home.ChaseBangumiContract
import com.yoyiyi.soleil.network.helper.RetrofitHelper
import com.yoyiyi.soleil.utils.JsonUtils
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/23 22:04
 * * 描述:首页直播Presenter
 */
class ChaseBangumiPresenter @Inject constructor(private val retrofitHelper: RetrofitHelper) : RxPresenter<ChaseBangumiContract.View>(), ChaseBangumiContract.Presenter<ChaseBangumiContract.View> {

    override fun getChaseBangumiData() {
        //需要测试账号登录
        /*BaseObjectSubscriber<RecommendBangumi> subscriber = mRetrofitHelper.getChaseBangumi()
                .compose(RxUtils.handleResult())
                .flatMap(chaseBangumi -> {
                    mView.showChaseBangumi(chaseBangumi);
                    return mRetrofitHelper.getRecommendBangumi();
                })
                .compose(RxUtils.rxSchedulerHelper())
                .subscribeWith(new BaseObjectSubscriber<RecommendBangumi>(mView) {
                    @Override
                    public void onSuccess(RecommendBangumi recommendBangumi) {
                        mView.showRecommendBangumi(recommendBangumi);
                    }
                });
        addSubscribe(subscriber);*/
        //本地json
        addSubscribe(Flowable.just(JsonUtils.readJson("user_chase.json"))
                .flatMap {
                    val gson = Gson()
                    val `object` = JsonParser().parse(it).asJsonObject
                    val result = `object`.getAsJsonObject("result")
                    val chaseBangumi = gson.fromJson(result, ChaseBangumi::class.java)
                    mView?.showChaseBangumi(chaseBangumi)
                    Flowable.just(JsonUtils.readJson("recommend_chase.json"))
                }
                .map {
                    val gson = Gson()
                    val `object` = JsonParser().parse(it).asJsonObject
                    val result = `object`.getAsJsonObject("result")
                    val recommendBangumi = gson.fromJson(result, RecommendBangumi::class.java)
                    recommendBangumi
                }
                .subscribeWith(object : BaseSubscriber<RecommendBangumi>(mView) {
                    override fun onSuccess(t: RecommendBangumi) {
                        mView?.showRecommendBangumi(t)
                    }
                }))
    }

}
