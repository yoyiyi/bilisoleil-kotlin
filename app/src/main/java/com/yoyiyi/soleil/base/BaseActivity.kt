package com.yoyiyi.soleil.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.Toolbar
import android.view.View
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.yoyiyi.soleil.App
import com.yoyiyi.soleil.R
import com.yoyiyi.soleil.utils.AppUtils
import com.yoyiyi.soleil.widget.statusbar.StatusBarUtil


/**
 * 基础Activity
 * Created by zzq on 2016/12/5.
 */
abstract class BaseActivity : RxAppCompatActivity() {

    protected var mToolbar: Toolbar? = null//Toolbar
    protected var mContext: Context? = null//上下文环境
    protected open val mBack = true //是否返回
    protected var mError: ConstraintLayout? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        mContext = this
        mToolbar = findViewById(R.id.toolbar) as? Toolbar
        mError = findViewById(R.id.cl_error) as? ConstraintLayout
        initStatusBar()
        initInject()
        initPresenter()
        initVariables()
        App.instance.addActivity(this)
        mToolbar?.let {
            //初始化Toolbar
            initToolbar()
            //让组件支持Toolbar
            setSupportActionBar(it)
            // supportActionBar
            if (mBack) it.setNavigationOnClickListener { finish() }
        }
        initWidget()
        initDatas()
    }


    /**
     * 注入依赖
     */
    open fun initInject() {

    }

    open fun initRecyclerView() {
    }

    /**
     * 完成请求
     */
    open fun finishTask() {}

    /**
     * 初始化StatusBar
     */
    open fun initStatusBar() {
        StatusBarUtil.setColorNoTranslucent(mContext as Activity, AppUtils.getColor(R.color.colorPrimary))
    }

    override fun onSaveInstanceState(outState: Bundle) {

    }

    /**
     * 初始化Presenter
     */
    open fun initPresenter() {
        //  mPresenter?.attachView(this)
    }




    /**
     * 初始化Toolbar
     */
    open fun initToolbar() {
        if (mBack) mToolbar?.setNavigationIcon(R.drawable.ic_clip_back_white)
    }

    /**
     * 布局文件

     * @return 布局文件
     */

    abstract fun getLayoutId(): Int

    /**
     * 初始化控件
     */
    open fun initWidget() {}

    /**
     * 加载数据
     */
    open fun loadData() {}

    /**
     * 初始化数据
     */
    open fun initDatas() {
        loadData()
    }

    /**
     * 初始化变量
     */
    open fun initVariables() {}

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
