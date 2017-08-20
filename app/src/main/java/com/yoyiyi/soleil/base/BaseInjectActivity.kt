package com.yoyiyi.soleil.base

import android.view.View
import com.yoyiyi.soleil.BiliSoleilApplication
import com.yoyiyi.soleil.di.component.ActivityComponent
import com.yoyiyi.soleil.di.component.DaggerActivityComponent
import com.yoyiyi.soleil.di.module.ActivityModule
import javax.inject.Inject


/**
 * 基础Activity
 * Created by zzq on 2016/12/5.
 */
abstract class BaseInjectActivity<T : BaseContract.BasePresenter<*>> : BaseActivity(), BaseContract.BaseView {

    @Inject
    lateinit var mPresenter: T
    //优先使用属性
    protected val activityModule: ActivityModule get() = ActivityModule(this)

    protected val activityComponent: ActivityComponent get() = DaggerActivityComponent.builder()
            .appComponent(BiliSoleilApplication.instance.appComponent)
            .activityModule(activityModule)
            .build()

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

}
