package com.yoyiyi.soleil.module.app.video

import android.support.v7.widget.LinearLayoutManager
import com.yoyiyi.soleil.R
import com.yoyiyi.soleil.adapter.app.video.SummaryAdapter
import com.yoyiyi.soleil.base.BaseInjectFragment
import com.yoyiyi.soleil.bean.app.video.MulSummary
import com.yoyiyi.soleil.mvp.contract.app.video.SummaryContract
import com.yoyiyi.soleil.mvp.presenter.app.video.SummaryPresenter
import kotlinx.android.synthetic.main.common_recycler.*

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/6/14 15:57
 * * 描述:简介
 */

class SummaryFragment : BaseInjectFragment<SummaryPresenter>(), SummaryContract.View {

    private val mList = mutableListOf<MulSummary>()
    private var mAdapter: SummaryAdapter? = null

    override fun getLayoutId(): Int = R.layout.fragment_summary


    override fun loadData() {
        mPresenter.getSummaryData()
    }

    override fun initInject() {
        fragmentComponent.inject(this)
    }

    override fun initPresenter() {
        mPresenter.attachView(this)
    }

    override fun initRecyclerView() {
        mAdapter = SummaryAdapter(mList)
        recycler.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(mContext)
        recycler.layoutManager = layoutManager
        recycler.adapter = mAdapter
    }

    override fun finishTask() {
        mAdapter?.notifyDataSetChanged()
    }

    override fun showSummary(mulSummaries: List<MulSummary>) {
        mList.addAll(mulSummaries)
        finishTask()
    }


    override fun initWidget() {
        initRecyclerView()
    }

    companion object {

        fun newInstance(): SummaryFragment {
            return SummaryFragment()
        }
    }

}
