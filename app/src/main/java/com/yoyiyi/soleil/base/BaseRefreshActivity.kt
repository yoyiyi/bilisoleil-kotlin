package com.yoyiyi.soleil.base

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import com.yoyiyi.soleil.R
import com.yoyiyi.soleil.utils.AppUtils
import com.yoyiyi.soleil.utils.ToastUtils
import com.yoyiyi.soleil.widget.ProgressWheel
import java.util.*

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/6/5 22:43
 * * 描述:基础刷新Activity
 */
abstract class BaseRefreshActivity<T : BaseContract.BasePresenter<*>, K> : BaseActivity<T>(), SwipeRefreshLayout.OnRefreshListener {
    protected var mRecycler: RecyclerView? = null
    protected var mRefresh: SwipeRefreshLayout? = null
    protected var mIsRefreshing = false
    protected var mList: MutableList<K>? = ArrayList()
    private var mLoading: ProgressWheel? = null

    protected fun initRefreshLayout() {
        if (mRefresh != null) {
            mRefresh?.setColorSchemeResources(R.color.colorPrimary)
            mRecycler?.post {
                mRefresh?.isRefreshing = true
                loadData()
            }
            mRefresh?.setOnRefreshListener(this)
        }
    }


    override fun initWidget() {
        findViewById(R.id.refresh)
        findViewById(R.id.recycler)
        findViewById(R.id.pw_loading)

      /*  mRefresh = ButterKnife.findById(this, R.id.refresh)
        mRecycler = ButterKnife.findById(this, R.id.recycler)
        //加载框
        mLoading = ButterKnife.findById(this, R.id.pw_loading)*/
        initRefreshLayout()
        initRecyclerView()
    }

    override fun onRefresh() {
        clearData()
        loadData()
    }

    protected fun clearData() {
        mIsRefreshing = true
    }

    override fun complete() {
        super.complete()
        AppUtils.runOnUIDelayed({
            if (mRefresh != null)
                mRefresh!!.isRefreshing = false
        }, 650)
        if (mIsRefreshing) {
            if (mList != null) mList!!.clear()
            clear()
            ToastUtils.showSingleLongToast("刷新成功")
        }
        mIsRefreshing = false

        if (mLoading != null) {
            gone(mLoading!!)
        }
    }

    protected fun clear() {

    }

     override fun initDatas() {
        if (mRefresh == null) {//
            if (mLoading != null) {
                visible(mLoading!!)
                AppUtils.runOnUIDelayed({ loadData() }, 650)
            } else {
                super.initDatas()
            }
        }
    }
}
