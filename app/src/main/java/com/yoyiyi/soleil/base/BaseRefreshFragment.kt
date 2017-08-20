package com.yoyiyi.soleil.base

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import com.yoyiyi.soleil.R
import com.yoyiyi.soleil.utils.AppUtils
import org.jetbrains.anko.toast

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/15 11:13
 * * 描述:基础刷新的Fragment
 */

abstract class BaseRefreshFragment<T : BaseContract.BasePresenter<*>, K> : BaseInjectFragment<T>(), SwipeRefreshLayout.OnRefreshListener {
    protected var mRecycler: RecyclerView? = null
    protected var mRefresh: SwipeRefreshLayout? = null
    protected var mIsRefreshing = false
    protected var mList = mutableListOf<K>()

    override fun initRefreshLayout() {
        mRefresh?.let {
            it.setColorSchemeResources(R.color.colorPrimary)
            mRecycler?.post {
                it.isRefreshing = true
                lazyLoadData()
            }
            it.setOnRefreshListener(this)
        }
    }

    override fun onRefresh() {
        clearData()
        lazyLoadData()
    }


    override fun clearData() {
        mIsRefreshing = true

    }

    override fun finishCreateView(state: Bundle?) {
        mRefresh = mRootView?.findViewById(R.id.refresh) as SwipeRefreshLayout?
        mRecycler = mRootView?.findViewById(R.id.recycler) as RecyclerView?
        mIsPrepared = true
        lazyLoad()
    }

    override fun lazyLoad() {
        if (!mIsPrepared || !mIsVisible) return@lazyLoad
        initRefreshLayout()
        initRecyclerView()
        mRefresh ?: lazyLoadData()
        mIsPrepared = false
    }

    override fun complete() {
        super.complete()
        AppUtils.runOnUIDelayed({ mRefresh?.let { it.isRefreshing = false } }, 650)
        if (mIsRefreshing) {
            mList.clear()
            clear()
            getApplicationContext()?.toast("刷新成功")
        }
        mIsRefreshing = false
    }

    protected open fun clear() {

    }


    override fun initWidget() {

    }
}
