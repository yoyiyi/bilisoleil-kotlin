package com.yoyiyi.soleil.module.home

import android.support.v7.widget.LinearLayoutManager
import com.yoyiyi.soleil.R
import com.yoyiyi.soleil.adapter.home.live.LiveAdapter
import com.yoyiyi.soleil.base.BaseRefreshFragment
import com.yoyiyi.soleil.bean.live.MulLive
import com.yoyiyi.soleil.mvp.contract.home.LiveContract
import com.yoyiyi.soleil.mvp.presenter.home.LivePresenter

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 *
 * @date 创建时间：2017/5/23 14:23
 * 描述:推荐
 */

class LiveFragment : BaseRefreshFragment<LivePresenter, MulLive>(), LiveContract.View {


    private var mAdapter: LiveAdapter? = null

    companion object {
        fun newInstance(): LiveFragment {
            return LiveFragment()
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_home_live

    override fun lazyLoadData() {
        mPresenter.getLiveData()
    }


    override fun initPresenter() {
        mPresenter.attachView(this)
    }

    override fun initInject() {
        fragmentComponent.inject(this)
    }

    override fun initRecyclerView() {
        mAdapter = LiveAdapter(mList)
        mRecycler?.setHasFixedSize(true)
        val mLayoutManager = LinearLayoutManager(activity)
        mRecycler?.layoutManager = mLayoutManager
        mRecycler?.adapter = mAdapter

    }


    override fun finishTask() {
        mAdapter?.notifyDataSetChanged()
    }

    override fun showMulLive(mulLives: List<MulLive>) {
        mList.addAll(mulLives)
        finishTask()
    }
}


