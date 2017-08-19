package com.yoyiyi.soleil.module.region

import android.support.v7.widget.GridLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.yoyiyi.soleil.R
import com.yoyiyi.soleil.adapter.region.sectiton.RegionRecommendBannerSection
import com.yoyiyi.soleil.adapter.region.sectiton.RegionRecommendDynamicSection
import com.yoyiyi.soleil.adapter.region.sectiton.RegionRecommendNewSection
import com.yoyiyi.soleil.adapter.region.sectiton.RegionRecommendRecommendSection
import com.yoyiyi.soleil.base.BaseRefreshActivity
import com.yoyiyi.soleil.bean.region.RegionRecommend
import com.yoyiyi.soleil.mvp.contract.region.RegionTypeRecommendContract
import com.yoyiyi.soleil.mvp.presenter.region.RegionTypeRecommendPresenter
import com.yoyiyi.soleil.widget.section.SectionedRVAdapter
import java.util.*

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/30 18:35
 * * 描述:广告界面
 */
class AdActivity : BaseRefreshActivity<RegionTypeRecommendPresenter, RegionRecommend.NewBean>(), RegionTypeRecommendContract.View {

    private val mTopBeanList = ArrayList<RegionRecommend.BannerBean.TopBean>()
    private val mDynamicBeanList = ArrayList<RegionRecommend.DynamicBean>()
    private val mRecommendBeanList = ArrayList<RegionRecommend.RecommendBean>()

    private var mSectionedAdapter: SectionedRVAdapter? = null

    override fun getLayoutId(): Int = R.layout.activity_ad


    override fun initInject() {
        activityComponent.inject(this)
    }

    override fun initPresenter() {
        mPresenter?.attachView(this)
    }
    override fun initRecyclerView() {
        mSectionedAdapter = SectionedRVAdapter()
        val mLayoutManager = GridLayoutManager(mContext, 2)
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

    override fun loadData() {
        mPresenter?.getRegionRecommendData(165)

    }

    override fun initToolbar() {
        super.initToolbar()
        mToolbar?.title = "广告"
    }

    override fun showRegionRecommend(regionRecommend: RegionRecommend) {
        mList?.addAll(regionRecommend.new)
        mRecommendBeanList.addAll(regionRecommend.recommend)
        mTopBeanList.addAll(regionRecommend.banner.top)
        mDynamicBeanList.addAll(regionRecommend.dynamic)
        finishTask()
    }


    override fun finishTask() {
        mSectionedAdapter?.addSection(RegionRecommendBannerSection(mTopBeanList))
        mSectionedAdapter?.addSection(RegionRecommendRecommendSection(mRecommendBeanList))
        mList?.let {
            mSectionedAdapter?.addSection(RegionRecommendNewSection(it))
        }
        mSectionedAdapter?.addSection(RegionRecommendDynamicSection(mDynamicBeanList))
        mSectionedAdapter?.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_region, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {

        }

        return super.onOptionsItemSelected(item)
    }
}
