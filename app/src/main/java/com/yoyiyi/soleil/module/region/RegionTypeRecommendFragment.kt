package com.yoyiyi.soleil.module.region

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.yoyiyi.soleil.R
import com.yoyiyi.soleil.adapter.region.sectiton.*
import com.yoyiyi.soleil.base.BaseRefreshFragment
import com.yoyiyi.soleil.bean.region.RegionRecommend
import com.yoyiyi.soleil.constant.Constants
import com.yoyiyi.soleil.mvp.contract.region.RegionTypeRecommendContract
import com.yoyiyi.soleil.mvp.presenter.region.RegionTypeRecommendPresenter
import com.yoyiyi.soleil.widget.section.SectionedRVAdapter
import java.util.*

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/30 18:35
 * * 描述:分区推荐界面
 */
class RegionTypeRecommendFragment : BaseRefreshFragment<RegionTypeRecommendPresenter, RegionRecommend.NewBean>(), RegionTypeRecommendContract.View {

    private var mTid: Int = 0
    private val mTopBeanList = ArrayList<RegionRecommend.BannerBean.TopBean>()
    private val mDynamicBeanList = ArrayList<RegionRecommend.DynamicBean>()
    private val mRecommendBeanList = ArrayList<RegionRecommend.RecommendBean>()

    private var mSectionedAdapter: SectionedRVAdapter? = null

    override fun getLayoutId(): Int = R.layout.fragment_region_type_recommend
    

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
    
    override fun initRecyclerView() {
        mSectionedAdapter = SectionedRVAdapter()
        val mLayoutManager = GridLayoutManager(activity, 2)
        mLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                when (mSectionedAdapter?.getSectionItemViewType(position)) {
                    SectionedRVAdapter.VIEW_TYPE_HEADER -> return 2//2格
                    SectionedRVAdapter.VIEW_TYPE_FOOTER -> return 2//2格
                    else -> return 1
                }
            }
        }
        mRecycler?.layoutManager = mLayoutManager
        mRecycler?.adapter = mSectionedAdapter
    }

    override fun clear() {
        mRecommendBeanList.clear()
        mDynamicBeanList.clear()
        mTopBeanList.clear()
        mSectionedAdapter?.removeAllSections()
    }

    override fun lazyLoadData() {
        mPresenter.getRegionRecommendData(mTid)
    }

    override fun showRegionRecommend(regionRecommend: RegionRecommend) {
        mList.addAll(regionRecommend.new)
        mRecommendBeanList.addAll(regionRecommend.recommend)
        mTopBeanList.addAll(regionRecommend.banner.top)
        mDynamicBeanList.addAll(regionRecommend.dynamic)
        finishTask()
    }


    override fun finishTask() {
        mSectionedAdapter?.addSection(RegionRecommendBannerSection(mTopBeanList))
        mSectionedAdapter?.addSection(RegionRecommendEntranceSection(mTid))
        mSectionedAdapter?.addSection(RegionRecommendRecommendSection(mRecommendBeanList))
        mSectionedAdapter?.addSection(RegionRecommendNewSection(mList))
        mSectionedAdapter?.addSection(RegionRecommendDynamicSection(mDynamicBeanList))
        mSectionedAdapter?.notifyDataSetChanged()
    }

    companion object {

        fun newInstance(tid: Int): RegionTypeRecommendFragment {
            val fragment = RegionTypeRecommendFragment()
            val bundle = Bundle()
            bundle.putInt(Constants.EXTRA_TID, tid)
            fragment.arguments = bundle
            return fragment
        }
    }

}
