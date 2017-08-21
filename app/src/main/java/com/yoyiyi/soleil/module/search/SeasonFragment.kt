package com.yoyiyi.soleil.module.search

import android.support.v7.widget.LinearLayoutManager

import com.yoyiyi.soleil.R
import com.yoyiyi.soleil.adapter.search.SeasonAdapter
import com.yoyiyi.soleil.bean.search.Season
import com.yoyiyi.soleil.mvp.contract.search.SeasonContract
import com.yoyiyi.soleil.mvp.presenter.search.SeasonPresenter

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/29 11:46
 * * 描述:番剧
 */
class SeasonFragment : BaseSearchFragment<SeasonPresenter, Season.DataBean.ItemsBean>(), SeasonContract.View {
    private var mAdapter: SeasonAdapter? = null

    override fun getLayoutId(): Int = R.layout.fragment_search_season


    override fun lazyLoadData() {
        mPresenter.getSearchSeasonData()
    }

    override fun initInject() {
        fragmentComponent.inject(this)
    }
    override fun initPresenter() {
        mPresenter.attachView(this)
    }

    override fun finishTask() {
        mAdapter?.notifyDataSetChanged()
    }

    override fun initRecyclerView() {
        mAdapter = SeasonAdapter(mList)
        val mLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        mRecycler?.layoutManager = mLayoutManager
        mRecycler?.adapter = mAdapter
    }

    override fun showSearchSeason(season: Season) {
        mList.addAll(season.data.items)
        finishTask()
    }

    companion object {

        fun newsInstance(): SeasonFragment {

            return SeasonFragment()
        }
    }
}
