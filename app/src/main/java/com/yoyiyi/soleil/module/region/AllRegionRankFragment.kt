package com.yoyiyi.soleil.module.region

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager

import com.yoyiyi.soleil.R
import com.yoyiyi.soleil.adapter.region.AllRegionRankAdapter
import com.yoyiyi.soleil.base.BaseRefreshFragment
import com.yoyiyi.soleil.bean.region.AllRegionRank
import com.yoyiyi.soleil.constant.Constants
import com.yoyiyi.soleil.mvp.contract.region.AllRegionRankContract
import com.yoyiyi.soleil.mvp.presenter.region.AllRegionRankPresenter

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/30 18:35
 * * 描述:分区Tag
 */
class AllRegionRankFragment : BaseRefreshFragment<AllRegionRankPresenter, AllRegionRank.RankBean.ListBean>(), AllRegionRankContract.View {

    private var mType: String? = null
    private var mAdapter: AllRegionRankAdapter? = null

    companion object {
        fun newInstance(type: String): AllRegionRankFragment {
            val bundle = Bundle()
            bundle.putString(Constants.EXTRA_TYPE, type)
            val fragment = AllRegionRankFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_region_type


    override fun initVariables() {
        arguments?.let {
            mType = it.getString(Constants.EXTRA_TYPE)

        }

    }

    override fun initInject() {
        fragmentComponent.inject(this)
    }

    override fun initPresenter() {
        mPresenter.attachView(this)
    }

    override fun lazyLoadData() {
        mType?.let {
            mPresenter.getAllRegionRankData(it)
        }
    }

    override fun initRecyclerView() {
        mAdapter = AllRegionRankAdapter(mList)
        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,
                false)
        mRecycler?.layoutManager = layoutManager
        mRecycler?.adapter = mAdapter
    }


    override fun finishTask() {
        mAdapter?.notifyDataSetChanged()
    }


    override fun showAllRegionRank(regionRank: List<AllRegionRank.RankBean.ListBean>) {
        mList.addAll(regionRank)
        finishTask()
    }

}
