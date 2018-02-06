package com.yoyiyi.soleil.base

import android.view.View
import com.yoyiyi.soleil.App
import com.yoyiyi.soleil.di.component.DaggerFragmentComponent
import com.yoyiyi.soleil.di.component.FragmentComponent
import com.yoyiyi.soleil.di.module.FragmentModule
import javax.inject.Inject

/**
 * 基础Fragment
 * Created by zzq on 2016/12/27.
 */

abstract class BaseInjectFragment<T : BaseContract.BasePresenter<*>> : BaseFragment(), BaseContract.BaseView {

    @Inject
    lateinit var mPresenter: T


    val fragmentModule: FragmentModule get() = FragmentModule(this)

    val fragmentComponent: FragmentComponent get() = DaggerFragmentComponent.builder()
            .appComponent(App.instance.appComponent)
            .fragmentModule(fragmentModule)
            .build()


    override fun onDestroy() {
        mPresenter.detachView()
        super.onDestroy()
    }



    override fun showError(msg: String) {
        mError?.visibility = View.VISIBLE
    }

    override fun complete() {
        mError?.visibility = View.GONE
    }


}
