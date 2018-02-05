package com.yoyiyi.soleil.base

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v4.app.FragmentActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.trello.rxlifecycle2.components.support.RxFragment
import com.yoyiyi.soleil.R

/**
 * 基础Fragment
 * Created by zzq on 2016/12/27.
 */

abstract class BaseFragment : RxFragment() {


    protected var mRootView: View? = null
    protected var mActivity: Activity? = null
    protected var mInflater: LayoutInflater? = null
    protected var mContext: Context? = null
    // 标志位 标志已经初始化完成。
    protected var mIsPrepared: Boolean = false
    //标志位 fragment是否可见
    protected var mIsVisible: Boolean = false
    protected var mError: ConstraintLayout? = null



    override fun onAttach(context: Context?) {
        mActivity = context as? Activity
        mContext = context
        super.onAttach(context)
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, state: Bundle?): View? {
        if (mRootView != null) {
            val parent = mRootView?.parent as ViewGroup
            parent.removeView(mRootView)
        } else {
            mRootView = inflater?.inflate(getLayoutId(), container, false)
            mActivity = getSupportActivity()
            mContext = mActivity
            this.mInflater = inflater
        }
        return mRootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initInject()
        initPresenter()
        initVariables()
        mError = mRootView?.findViewById(R.id.cl_error) as ConstraintLayout?
        initWidget()
        initSetListener()
        finishCreateView(savedInstanceState)
        initDatas()
    }

    protected open fun initSetListener() {

    }

    protected open fun lazyLoadData() {

    }

    protected open fun initDatas() {
        loadData()
    }

    open fun finishCreateView(state: Bundle?) {
        mIsPrepared = true
        lazyLoad()
    }



    /**
     * 分离
     */
    override fun onDetach() {
        this.mActivity = null
        super.onDetach()
    }


    /**
     * 初始化RV
     */
    protected open fun initRecyclerView() {

    }

    /**
     * 初始化刷新
     */
    @SuppressLint("CheckResult")
    protected open fun initRefreshLayout() {

    }

    /**
     * 清除数据
     */
    protected open fun clearData() {

    }

    /**
     * 初始化Presenter
     */
    protected open fun initPresenter() {
//        mPresenter?.attachView(this as Nothing)
    }


    /**
     * 初始化变量
     */
    open fun initVariables() {}

    /**
     * 懒加载
     */
    protected open fun lazyLoad() {
        if (!mIsPrepared || !mIsVisible) return@lazyLoad
        lazyLoadData()
        mIsPrepared = false
    }

    protected open fun onInvisible() {

    }

    /**
     * 加载数据
     */
    protected open fun loadData() {}

    /**
     * 注入dagger2依赖
     */
    protected open fun initInject() {

    }





    protected open fun finishTask() {

    }

    /**
     * 布局
     * @return int
     */
    abstract fun getLayoutId(): Int

    /**
     * 对各种控件进行设置、适配、填充数据
     */
    open fun initWidget() {

    }

    protected open fun onVisible() {
        lazyLoad()
    }

    /**
     * Fragment数据的懒加载

     * @param isVisibleToUser
     */
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (userVisibleHint) {
            mIsVisible = true
            onVisible()
        } else {
            mIsVisible = false
            onInvisible()
        }
    }


    /**
     * 获取Activity

     * @return FragmentActivity
     */
    fun getSupportActivity(): FragmentActivity = super.getActivity()


    /**
     * 获取ApplicationContext 信息
     * @return Context
     */
    fun getApplicationContext(): Context? =
            if (this.mContext == null) {
                if (activity == null) {
                    null
                } else {
                    activity.application
                }
            } else {
                this.mContext?.applicationContext
            }
    /**
     * 隐藏View
     * @param views 视图
     */
    fun gone(vararg views: View) {
        if (views.isNotEmpty()) {
            for (view in views) {
                view.visibility = View.GONE
            }
        }
    }

    /**
     * 显示View
     * @param views 视图
     */
    fun visible(vararg views: View) {
        if (views.isNotEmpty()) {
            for (view in views) {
                view.visibility = View.VISIBLE
            }
        }
    }
}
