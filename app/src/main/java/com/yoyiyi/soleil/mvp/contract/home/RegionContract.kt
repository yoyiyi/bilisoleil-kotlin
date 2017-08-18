package com.yoyiyi.soleil.mvp.contract.home

import com.yoyiyi.soleil.base.BaseContract
import com.yoyiyi.soleil.bean.region.Region
import com.yoyiyi.soleil.bean.region.RegionTagType

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 *
 * @date 创建时间：2017/5/23 22:02
 * 描述:分区
 */
interface RegionContract {
    interface View : BaseContract.BaseView {
        fun showRegion(regions: List<Region>)

        fun showRegionType(regionTypes: List<RegionTagType>)
    }

    interface Presenter<in T> : BaseContract.BasePresenter<T> {
        fun getRegionData()
    }
}
