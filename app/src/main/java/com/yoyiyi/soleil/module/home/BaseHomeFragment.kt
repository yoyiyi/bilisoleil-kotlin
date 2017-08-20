package com.yoyiyi.soleil.module.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.yoyiyi.soleil.R
import com.yoyiyi.soleil.base.BaseFragment

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 *
 * @date 创建时间：2017/6/14 14:12
 * 描述:首页基础base
 */

abstract class BaseHomeFragment : BaseFragment() {
    var mToolbar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)//支持menu
    }

    override fun initWidget() {
        initToolbar()
    }

    @SuppressLint("CheckResult")
    private fun initToolbar() {
        mToolbar = mRootView?.findViewById(R.id.toolbar) as Toolbar
        mToolbar?.let {
            it.title = ""
            (activity as AppCompatActivity).setSupportActionBar(mToolbar)
            //换成下面这句就OK了
            it.inflateMenu(R.menu.menu_main)
        }

    }
}
