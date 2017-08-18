package com.yoyiyi.soleil.module.home

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.View.OnClickListener

import com.yoyiyi.soleil.R
import com.yoyiyi.soleil.adapter.home.DynamicAdapter
import com.yoyiyi.soleil.base.BaseRefreshFragment
import com.yoyiyi.soleil.bean.dynamic.MulDynamic
import com.yoyiyi.soleil.mvp.contract.home.DynamicContract
import com.yoyiyi.soleil.mvp.presenter.home.DynamicPresenter
import kotlinx.android.synthetic.main.fragment_home_dynamic.*


/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 *
 * @date 创建时间：2017/5/23 14:23
 * 描述:推荐
 */

class DynamicFragment : BaseRefreshFragment<DynamicPresenter, MulDynamic>(), DynamicContract.View, OnClickListener {

    private var mAdapter: DynamicAdapter? = null

    override fun getLayoutId(): Int = R.layout.fragment_home_dynamic


    override fun initWidget() {
        tv_all_select.isSelected = true
    }

    override fun lazyLoadData() {
        mPresenter.getMulDynamicData()
    }

    override fun initInject() {
        fragmentComponent.inject(this)
    }


    override fun initPresenter() {
        mPresenter.attachView(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.cl_all -> {
                expand?.let {
                    if (it.isExpanded) {
                        it.collapse()
                    } else {
                        it.expand()
                    }
                }
            }
            R.id.tv_all_select -> {
                tv_all_select?.isSelected = true
                tv_up_select?.isSelected = false
                tv_bangumi_select?.isSelected = false
                expand?.collapse()
            }
            R.id.tv_up_select -> {
                tv_all_select?.isSelected = false
                tv_up_select?.isSelected = true
                tv_bangumi_select?.isSelected = false
                expand?.collapse()
            }
            R.id.tv_bangumi_select -> {
                tv_all_select?.isSelected = false
                tv_up_select?.isSelected = false
                tv_bangumi_select?.isSelected = true
                expand?.collapse()
            }


        }
    }

    override fun initRecyclerView() {
        mAdapter = DynamicAdapter(mList)
        mRecycler?.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(activity)
        mRecycler?.layoutManager = layoutManager
        mRecycler?.adapter = mAdapter
    }

    override fun showMulDynamic(mulDynamic: List<MulDynamic>) {
        mList.addAll(mulDynamic)
        finishTask()
    }

    override fun finishTask() {
        mAdapter?.notifyDataSetChanged()
    }

    companion object {

        fun newInstance(): DynamicFragment {
            return DynamicFragment()
        }
    }
}
