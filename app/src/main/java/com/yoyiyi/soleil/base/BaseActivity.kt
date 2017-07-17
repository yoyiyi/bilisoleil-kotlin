package com.yoyiyi.soleil.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.Toast

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.yoyiyi.soleil.BiliSoleilApplication
import com.yoyiyi.soleil.R
import com.yoyiyi.soleil.di.component.ActivityComponent
import com.yoyiyi.soleil.di.component.DaggerActivityComponent
import com.yoyiyi.soleil.di.module.ActivityModule
import com.yoyiyi.soleil.utils.AppUtils
import com.yoyiyi.soleil.widget.statusbar.StatusBarUtil

import javax.inject.Inject


/**
 * 基础Activity
 * Created by zzq on 2016/12/5.
 */
abstract class BaseActivity<T : BaseContract.BasePresenter<*>> : RxAppCompatActivity(), BaseContract.BaseView {

    @Inject
    protected lateinit var mPresenter: T
    protected var mToolbar: Toolbar? = null//Toolbar
    protected var mContext: Context? = null//上下文环境
    protected var mBack = true
    private var mError: ConstraintLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        mContext = this
        mToolbar = this.findViewById(R.id.toolbar) as Toolbar?
        mError = this.findViewById(R.id.cl_error) as ConstraintLayout?
        initStatusBar()
        initInject()
        initPresenter()
        initVariables()
        BiliSoleilApplication.instance.addActivity(this)
        mToolbar.let {
            //初始化Toolbar
            initToolbar()
            //让组件支持Toolbar
            setSupportActionBar(mToolbar)
            if (mBack) mToolbar?.setNavigationOnClickListener { finish() }
        }
        initWidget()
        initDatas()
    }

    protected fun toast(msg: String, length: Int = Toast.LENGTH_LONG) {
        Toast.makeText(this, msg, length).show()
    }


    protected fun getActivityModule(): ActivityModule = ActivityModule(this)

    protected fun getActivityComponent(): ActivityComponent =
            DaggerActivityComponent.builder()
                    .appComponent(BiliSoleilApplication.appComponent)
                    .activityModule(getActivityModule())
                    .build()

    /**
     * 注入依赖
     */
    protected fun initInject() {

    }

    protected fun initRecyclerView() {

    }

    /**
     * 完成请求
     */
    protected fun finishTask() {}

    /**
     * 初始化StatusBar
     */
    protected fun initStatusBar() {
        StatusBarUtil.setColorNoTranslucent(mContext as Activity, AppUtils.getColor(R.color.colorPrimary))
    }

    override fun onSaveInstanceState(outState: Bundle) {

    }

    /**
     * 初始化Presenter
     */
    private fun initPresenter() {
        mPresenter?.attachView(this as Nothing)

    }


    override fun showError(msg: String) {
        mError?.visibility = View.VISIBLE
    }

    override fun complete() {
        mError?.visibility = View.GONE
    }

    /**
     * 销毁
     */
    override fun onDestroy() {
        mPresenter?.detachView()
        BiliSoleilApplication.instance.removeActivity(this)
        super.onDestroy()
    }

    /**
     * 初始化Toolbar
     */
    protected fun initToolbar() {
        if (mBack) mToolbar?.setNavigationIcon(R.drawable.ic_clip_back_white)
    }

    /**
     * 布局文件

     * @return 布局文件
     */

    protected abstract fun getLayoutId(): Int

    /**
     * 初始化控件
     */
    protected fun initWidget() {}

    /**
     * 加载数据
     */
    protected fun loadData() {}

    /**
     * 初始化数据
     */
    protected fun initDatas() {
        loadData()
    }

    /**
     * 初始化变量
     */
    protected fun initVariables() {}


    /**
     * 隐藏View
     * @param views 视图
     */
    protected fun gone(vararg views: View) {
        if (views != null && views.size > 0) {
            for (view in views) {
                view?.visibility = View.GONE
            }
        }
    }

    /**
     * 显示View
     * @param views 视图
     */
    protected fun visible(vararg views: View) {
        if (views != null && views.size > 0) {
            for (view in views) {
                view?.visibility = View.VISIBLE
            }
        }
    }
}


