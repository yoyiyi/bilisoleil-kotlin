package com.yoyiyi.soleil.module.bangumi

import android.support.v7.widget.GridLayoutManager

import com.yoyiyi.soleil.R
import com.yoyiyi.soleil.adapter.bangumi.BangumiIndexSection
import com.yoyiyi.soleil.base.BaseRefreshActivity
import com.yoyiyi.soleil.bean.bangumi.BangumiIndex
import com.yoyiyi.soleil.mvp.contract.bangumi.BangumiIndexContract
import com.yoyiyi.soleil.mvp.presenter.bangumi.BangumiIndexPresenter
import com.yoyiyi.soleil.widget.section.SectionedRVAdapter

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/6/8 16:35
 * * 描述:番剧索引
 */

class BangumiIndexActivity : BaseRefreshActivity<BangumiIndexPresenter, BangumiIndex.CategoryBean>(), BangumiIndexContract.View {

    private var mSectionedAdapter: SectionedRVAdapter? = null


    override fun getLayoutId(): Int = R.layout.activity_bangumi_schedule


    override fun loadData() {
        mPresenter.getBangumiIndex()
    }

    override fun initInject() {
        activityComponent.inject(this)
    }

    override fun initPresenter() {
        mPresenter.attachView(this)
    }

    override fun initRecyclerView() {
        mRecycler?.setHasFixedSize(true)
        mSectionedAdapter = SectionedRVAdapter()
        val mLayoutManager = GridLayoutManager(mContext, 3)
        mLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                when (mSectionedAdapter?.getSectionItemViewType(position)) {
                    SectionedRVAdapter.VIEW_TYPE_HEADER -> return 3//2格
                    else -> return 1
                }
            }
        }
        mRecycler?.layoutManager = mLayoutManager
        mRecycler?.adapter = mSectionedAdapter
    }


    override fun showBangumiIndex(bangumiIndex: BangumiIndex) {
        mList?.addAll(bangumiIndex.category)
        finishTask()
    }

    override fun finishTask() {
        mList?.let {
            mSectionedAdapter?.addSection(BangumiIndexSection(it))
            mSectionedAdapter?.notifyDataSetChanged()
        }

    }

    override fun initToolbar() {
        super.initToolbar()
        mToolbar?.title = "索引"
    }


}
