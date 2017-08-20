package com.yoyiyi.soleil.module.discover

import android.support.v7.widget.LinearLayoutManager
import com.yoyiyi.soleil.R
import com.yoyiyi.soleil.adapter.discover.InterestAdapter
import com.yoyiyi.soleil.base.BaseRefreshFragment
import com.yoyiyi.soleil.bean.discover.Community
import com.yoyiyi.soleil.bean.discover.InterestAd
import com.yoyiyi.soleil.bean.discover.InterestCategrory
import com.yoyiyi.soleil.bean.discover.MulInterest
import com.yoyiyi.soleil.mvp.contract.discover.InterestContract
import com.yoyiyi.soleil.mvp.presenter.discover.InterestPresenter
import com.yoyiyi.soleil.utils.AppUtils
import com.yoyiyi.soleil.utils.ToastUtils

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/6/10 22:25
 * * 描述:兴趣圈
 */
class InterestFragment : BaseRefreshFragment<InterestPresenter, MulInterest>(), InterestContract.View {
    private var mAdapter: InterestAdapter? = null
    private var mInterestAdList: InterestAd? = null
    private var mInterestCategroryList: List<InterestCategrory.ResultBean>? = null

    override fun getLayoutId(): Int {
        return R.layout.fragment_interest
    }

    override fun initInject() {
        fragmentComponent.inject(this)
    }

    override fun initPresenter() {
        mPresenter.attachView(this)
    }

    override fun lazyLoadData() {
        mPresenter.getInterestData()
    }

    override fun initRecyclerView() {
        mAdapter = InterestAdapter(mList)
        mRecycler?.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(mContext)
        mRecycler?.layoutManager = layoutManager
        mRecycler?.adapter = mAdapter
    }


    override fun showInterestAd(interestAdList: InterestAd) {
        mInterestAdList = interestAdList
    }

    override fun showCommunity(community: Community) {
        mList.add(MulInterest(MulInterest.TYPE_BANNER, interestAdList = mInterestAdList))
        mList.add(MulInterest(MulInterest.TYPE_CATEGRORY, mInterestCategroryList))
        mList.add(MulInterest(MulInterest.TYPR_HEADER))
        community.result.forEach { mList.add(MulInterest(MulInterest.TYPR_ITEM, community = it)) }
        finishTask()
    }

    override fun showInterestCategrory(interestCategroryList: List<InterestCategrory.ResultBean>) {
        mInterestCategroryList = interestCategroryList
    }

    override fun onComplete() {
        AppUtils.runOnUIDelayed({ mRefresh?.isRefreshing = false }, 650)
        if (mIsRefreshing) {
            mList.clear()
            clear()
            ToastUtils.showSingleLongToast("刷新成功")
        }
        mIsRefreshing = false
    }

    override fun finishTask() {
        mAdapter?.notifyDataSetChanged()
    }

    override fun complete() {
        mError?.let {
            gone(it)
        }
    }
}
