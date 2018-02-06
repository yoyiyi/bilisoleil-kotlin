package com.yoyiyi.soleil.module.discover

import android.support.v7.widget.LinearLayoutManager

import com.chad.library.adapter.base.BaseQuickAdapter
import com.yoyiyi.soleil.R
import com.yoyiyi.soleil.adapter.discover.ActivityCenterAdapter
import com.yoyiyi.soleil.base.BaseRefreshActivity
import com.yoyiyi.soleil.bean.discover.ActivityCenter
import com.yoyiyi.soleil.mvp.contract.discover.ActivityCenterContract
import com.yoyiyi.soleil.mvp.presenter.discover.ActivityCenterPresenter
import com.yoyiyi.soleil.utils.AppUtils
import com.yoyiyi.soleil.utils.LogUtils
import com.yoyiyi.soleil.utils.net.NetworkUtils
import com.yoyiyi.soleil.widget.CustomLoadMoreView

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 *
 * @date 创建时间：2017/6/5 22:28
 * 描述:活动中心
 */
class ActivityCenterActivity : BaseRefreshActivity<ActivityCenterPresenter, ActivityCenter.ListBean>(), ActivityCenterContract.View, BaseQuickAdapter.RequestLoadMoreListener {

    private var mAdapter: ActivityCenterAdapter? = null
    private var mPage = 1
    private var mTotal = 0
    private var mIsError = false
    private var mIsLoadMore = false

    override fun getLayoutId(): Int = R.layout.activity_topic_center

    companion object {
        private val PS = 20
    }

    override fun initToolbar() {
        super.initToolbar()
        mToolbar?.title = "活动中心"
    }

    override fun loadData() {
        mPresenter.getActivityCenterData(mPage, PS)
    }

    override fun clearData() {
        super.clearData()
        mPage = 1
        mIsLoadMore = false
        mIsError = false
        //刷新时候关闭上拉加载
        mAdapter?.setEnableLoadMore(false)
    }

    override fun initInject() {
        activityComponent.inject(this)
    }

    override fun initPresenter() {
        mPresenter.attachView(this)
    }

    override fun initRecyclerView() {
        mAdapter = ActivityCenterAdapter(mList)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mRecycler?.layoutManager = layoutManager
        mRecycler?.adapter = mAdapter
        mAdapter?.setLoadMoreView(CustomLoadMoreView())
        //设置加载更多监听
        mAdapter?.setOnLoadMoreListener(this, mRecycler)
    }


    override fun finishTask() {
        mAdapter?.setNewData(mList)
    }

    override fun showActivityCenter(listBeanList: List<ActivityCenter.ListBean>, total: Int) {
        if (!mIsLoadMore) {
            mList?.addAll(listBeanList)
            mTotal = total//总数
            finishTask()
        } else {
            //加载更多
            mAdapter?.addData(listBeanList)
            mAdapter?.loadMoreComplete()//加载完成
        }
    }

    override fun onLoadMoreRequested() {
        AppUtils.runOnUIDelayed({
            //加载更多
            mAdapter?.itemCount?.let {
                if (it > mTotal) {
                    mAdapter?.loadMoreEnd()//结束加载
                } else {
                    if (!mIsError) {
                        mPage++
                        loadData()
                    } else {
                        mIsError = true
                        mAdapter?.loadMoreFail()//加载失败
                    }
                }
            }
        }, 650)


    }

    override fun showError(msg: String) {
        super.showError(msg)
        mPage--
        mIsError = true
    }

    override fun complete() {
        super.complete()
        //需要重新开启监听
        mAdapter?.setEnableLoadMore(true)
    }

    override fun initWidget() {
        super.initWidget()
        NetworkUtils.setOnChangeInternetListener {
            LogUtils.d("ceshi", it)
            mIsError = !it
            if (!mIsError) {
                mAdapter?.setEnableLoadMore(true)
            }
        }

        NetworkUtils.setOnChangeInternetListener {
            LogUtils.d("ceshi", it)
            mIsError = !it
            if (!mIsError) {

            }
        }
    }
}
