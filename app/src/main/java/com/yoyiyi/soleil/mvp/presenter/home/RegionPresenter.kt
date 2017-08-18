package com.yoyiyi.soleil.mvp.presenter.home

import com.google.gson.Gson
import com.google.gson.JsonParser
import com.yoyiyi.soleil.base.BaseListSubscriber
import com.yoyiyi.soleil.base.RxPresenter
import com.yoyiyi.soleil.bean.region.Region
import com.yoyiyi.soleil.bean.region.RegionTagType
import com.yoyiyi.soleil.mvp.contract.home.RegionContract
import com.yoyiyi.soleil.network.helper.RetrofitHelper
import com.yoyiyi.soleil.rx.rxSchedulerHelper
import com.yoyiyi.soleil.utils.JsonUtils
import io.reactivex.Flowable
import java.util.*
import javax.inject.Inject

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/23 22:04
 * * 描述:首页直播Presenter
 */
class RegionPresenter @Inject constructor(private val mRetrofitHelper: RetrofitHelper) : RxPresenter<RegionContract.View>(), RegionContract.Presenter<RegionContract.View> {

    override fun getRegionData() {
        val subscriber = Flowable.just(JsonUtils.readJson("region.json"))
                .flatMap {
                    val gson = Gson()
                    val `object` = JsonParser().parse(it).asJsonObject
                    val array = `object`.getAsJsonArray("data")
                    val regionTypes = array.mapTo(ArrayList<RegionTagType>()) { gson.fromJson(it, RegionTagType::class.java) }
                    mView?.showRegionType(regionTypes)
                    mRetrofitHelper.getRegion()
                }
                .compose(rxSchedulerHelper())
                .subscribeWith(object : BaseListSubscriber<Region>(mView) {
                    override fun onSuccess(t: List<Region>) {
                        mView?.showRegion(t)
                    }
                })
        addSubscribe(subscriber)
    }

}
