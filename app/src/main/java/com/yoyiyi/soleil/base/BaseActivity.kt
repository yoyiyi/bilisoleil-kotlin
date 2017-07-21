package com.yoyiyi.soleil.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.Toolbar
import android.view.View
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
    lateinit var mPresenter: T
    protected var mToolbar: Toolbar? = null//Toolbar
    protected var mContext: Context? = null//上下文环境
    protected var mBack = true //是否返回
    protected var mError: ConstraintLayout? = null
    //优先使用属性
    val activityModule: ActivityModule
        get() = ActivityModule(this)

    val activityComponent: ActivityComponent
        get() = DaggerActivityComponent.builder()
                .appComponent(BiliSoleilApplication.appComponent)
                .activityModule(activityModule)
                .build()

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
            // supportActionBar
            if (mBack) mToolbar?.setNavigationOnClickListener { finish() }
        }
        initWidget()
        initDatas()
    }


    /* fun getActivityModule(): ActivityModule = ActivityModule(this)

     fun getActivityComponent(): ActivityComponent =
             DaggerActivityComponent.builder()
                     .appComponent(BiliSoleilApplication.appComponent)
                     .activityModule(getActivityModule())
                     .build()*/

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
       // mPresenter?.attachView(this)
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
        mPresenter.detachView()
        BiliSoleilApplication.instance.removeActivity(this)
        super.onDestroy()
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


