package com.yoyiyi.soleil.module

import android.support.design.internal.NavigationMenuView
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.view.KeyEvent
import android.view.MenuItem
import com.yoyiyi.soleil.BiliSoleilApplication
import com.yoyiyi.soleil.R
import com.yoyiyi.soleil.base.BaseActivity
import com.yoyiyi.soleil.event.Event
import com.yoyiyi.soleil.rx.RxBus
import com.yoyiyi.soleil.utils.AppUtils
import com.yoyiyi.soleil.utils.ToastUtils
import com.yoyiyi.soleil.widget.statusbar.StatusBarUtil
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<Nothing>(), NavigationView.OnNavigationItemSelectedListener {
    internal var exitTime = 0L

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun onNavigationItemSelected(item: MenuItem): Boolean {


        return false
    }

    override fun initWidget() {
        disableNavigationViewScrollbars(mNavView)
        mNavView.setNavigationItemSelectedListener(this)
        switchFragmentIndex(0)//初始化位置
    }

    fun switchFragmentIndex(i: Int) {

    }

    /**
     * 去掉滚动条

     * @param navigationView navigationView
     */
    private fun disableNavigationViewScrollbars(navigationView: NavigationView?) {
        navigationView.let {
            val navigationMenuView = navigationView!!.getChildAt(0) as NavigationMenuView
            navigationMenuView?.isVerticalFadingEdgeEnabled = false
        }
    }

    override fun initStatusBar() {
        StatusBarUtil.setColorNoTranslucentForDrawerLayout(this, mDrawerLayout, AppUtils.getColor(R.color.colorPrimary))
    }

    override fun initVariables() {
        initFragment()
        //监听事件
        RxBus.INSTANCE.toFlowable(Event.StartNavigationEvent::class.java!!)
                .compose(bindToLifecycle())
                .subscribe({ it ->
                    if (it.start) {
                        toggleDrawer()//打开
                    }
                })
    }

    fun initFragment() {

    }

    /**
     * DrawerLayout侧滑菜单开关
     */
    fun toggleDrawer() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START)
        } else {
            mDrawerLayout.openDrawer(GravityCompat.START)
        }
    }

    /**
     * 监听back键处理
     */
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mDrawerLayout.isDrawerOpen(mDrawerLayout.getChildAt(1))) {
                mDrawerLayout.closeDrawers()
            } else {
                exitApp()
            }
        }
        return true
    }

    /**
     * 双击退出App
     */
    private fun exitApp() {
        if (System.currentTimeMillis() - exitTime > 2000) {
            ToastUtils.showToast("再按一次退出")
            exitTime = System.currentTimeMillis()
        } else {
            BiliSoleilApplication.instance.exitApp()
        }
    }
}
