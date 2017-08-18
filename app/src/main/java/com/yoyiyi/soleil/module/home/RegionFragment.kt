package com.yoyiyi.soleil.module.home

import android.support.v7.widget.GridLayoutManager
import com.yoyiyi.soleil.R
import com.yoyiyi.soleil.adapter.home.section.region.RegionActivityCenterSection
import com.yoyiyi.soleil.adapter.home.section.region.RegionEntranceSection
import com.yoyiyi.soleil.adapter.home.section.region.RegionSection
import com.yoyiyi.soleil.adapter.home.section.region.RegionTopicSection
import com.yoyiyi.soleil.base.BaseRefreshFragment
import com.yoyiyi.soleil.bean.region.Region
import com.yoyiyi.soleil.bean.region.RegionTagType
import com.yoyiyi.soleil.mvp.contract.home.RegionContract
import com.yoyiyi.soleil.mvp.presenter.home.RegionPresenter
import com.yoyiyi.soleil.widget.section.SectionedRVAdapter

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/23 14:23
 * * 描述:分区
 */

class RegionFragment : BaseRefreshFragment<RegionPresenter, Region>(), RegionContract.View {
    private var mSectionedAdapter: SectionedRVAdapter? = null
    private val mRegionTypeList = arrayListOf<RegionTagType>()

    companion object {
        fun newInstance(): RegionFragment {
            return RegionFragment()
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_home_region


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

    override fun lazyLoadData() {
        mPresenter.getRegionData()
    }

    override fun initInject() {
        fragmentComponent.inject(this)
    }

    override fun initPresenter() {
        mPresenter.attachView(this)
    }

    override fun showRegion(regions: List<Region>) {
        mList.addAll(regions)
        finishTask()
    }

    override fun showRegionType(regionTypes: List<RegionTagType>) {
        mRegionTypeList.addAll(regionTypes)
    }

    override fun finishTask() {
        mSectionedAdapter?.addSection(RegionEntranceSection(mRegionTypeList))
        mList.forEach {
            when (it.type) {
                "topic" -> mSectionedAdapter?.addSection(RegionTopicSection(it.body[0]))//话题
                "activity" -> mSectionedAdapter?.addSection(RegionActivityCenterSection(it.body))//活动中心
                else -> mSectionedAdapter?.addSection(RegionSection(it.title, it.body))//分区和番剧区

            }
        }
        mSectionedAdapter?.notifyDataSetChanged()
    }


}
