package com.yoyiyi.soleil.module.app.up

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.yoyiyi.soleil.R
import com.yoyiyi.soleil.adapter.app.up.SubmitedVideoAdapter
import com.yoyiyi.soleil.base.BaseInjectFragment
import com.yoyiyi.soleil.bean.user.MulUpDetail
import com.yoyiyi.soleil.constant.Constants
import com.yoyiyi.soleil.mvp.contract.app.up.SubmitedVideoContract
import com.yoyiyi.soleil.mvp.presenter.app.up.SubmitedVideoPresenter
import kotlinx.android.synthetic.main.common_recycler.*
import kotlinx.android.synthetic.main.fragment_up_submited_video.*

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/6/16 15:09
 * * 描述:投稿
 */

class SubmitedVideoFragment : BaseInjectFragment<SubmitedVideoPresenter>(), SubmitedVideoContract.View {



    private var mSetting: Int = 0
    private val mList = mutableListOf<MulUpDetail>()
    private var mAdapter: SubmitedVideoAdapter? = null


    override fun getLayoutId(): Int = R.layout.fragment_up_submited_video


    override fun initVariables() {
        arguments?.let {
            mSetting = it.getInt(Constants.EXTRA_SETTING)
        }
        if (mSetting == 0) {
            visible(iv_empty)
        } else {
            gone(iv_empty)
        }
    }

    override fun initInject() {
        fragmentComponent.inject(this)
    }

    override fun initPresenter() {
        mPresenter.attachView(this)
    }

    override fun loadData() {
        if (mSetting == 1)
            mPresenter.getSubmitedVideoData()
    }

    override fun initRecyclerView() {
        mAdapter = SubmitedVideoAdapter(mList)
        recycler.setHasFixedSize(true)
        //mRecycler.setNestedScrollingEnabled(false);
        val mLayoutManager = LinearLayoutManager(mContext)
        recycler.layoutManager = mLayoutManager
        recycler.adapter = mAdapter
    }

    override fun showSubmitedVideo(mulUpDetailList: List<MulUpDetail>) {
        mList.addAll(mulUpDetailList)
        finishTask()
    }

    override fun initWidget() {
        initRecyclerView()
    }

    override fun finishTask() {
        gone(iv_empty, cl_error)
        mAdapter?.notifyDataSetChanged()
    }

    override fun showError(msg: String) {
        visible(cl_error)
        gone(iv_empty)
    }

    companion object {

        fun newInstance(setting: Int): SubmitedVideoFragment {
            val fragment = SubmitedVideoFragment()
            val bundle = Bundle()
            bundle.putInt(Constants.EXTRA_SETTING, setting)
            fragment.arguments = bundle
            return fragment
        }
    }
}
