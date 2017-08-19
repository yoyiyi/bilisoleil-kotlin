package com.yoyiyi.soleil.module.region

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.yoyiyi.soleil.R
import com.yoyiyi.soleil.adapter.region.sectiton.RegionTypeNewSection
import com.yoyiyi.soleil.adapter.region.sectiton.RegionTypeRecommendSection
import com.yoyiyi.soleil.base.BaseRefreshFragment
import com.yoyiyi.soleil.bean.region.RegionType
import com.yoyiyi.soleil.constant.Constants
import com.yoyiyi.soleil.mvp.contract.region.RegionTypeContract
import com.yoyiyi.soleil.mvp.presenter.region.RegionTypePresenter
import com.yoyiyi.soleil.widget.section.SectionedRVAdapter
import java.util.*

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/30 18:35
 * * 描述:分区Tag
 */
class RegionTypeFragment : BaseRefreshFragment<RegionTypePresenter, RegionType.RecommendBean>(), RegionTypeContract.View {
    private var mTid: Int = 0
    private val mNewBeanList = ArrayList<RegionType.NewBean>()
    private var mSectionedAdapter: SectionedRVAdapter? = null

    override fun getLayoutId(): Int {
        return R.layout.fragment_region_type
    }


    override fun initVariables() {

        arguments?.let {
            mTid = it.getInt(Constants.EXTRA_TID)

        }

    }

    override fun initInject() {
        fragmentComponent.inject(this)
    }

    override fun initPresenter() {
        mPresenter.attachView(this)
    }

    override fun lazyLoadData() {
        mPresenter.getRegionTypeData(mTid)
    }

    override fun clear() {
        mNewBeanList.clear()
        mSectionedAdapter?.removeAllSections()
    }

    override fun initRecyclerView() {
        mSectionedAdapter = SectionedRVAdapter()
        val mLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,
                false)
        mRecycler?.layoutManager = mLayoutManager
        mRecycler?.adapter = mSectionedAdapter
    }

    override fun showRegionType(regionType: RegionType) {
        mList.addAll(regionType.recommend)
        mNewBeanList.addAll(regionType.new)
        finishTask()
    }

    override fun finishTask() {
        mSectionedAdapter?.addSection(RegionTypeRecommendSection(mList))
        mSectionedAdapter?.addSection(RegionTypeNewSection(mNewBeanList))
        mSectionedAdapter?.notifyDataSetChanged()
    }

    companion object {

        fun newInstance(tid: Int): RegionTypeFragment {
            val bundle = Bundle()
            bundle.putInt(Constants.EXTRA_TID, tid)
            val fragment = RegionTypeFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}
