package com.yoyiyi.soleil.module.home

import android.content.Intent
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.yoyiyi.soleil.R
import com.yoyiyi.soleil.adapter.home.MainAdapter
import com.yoyiyi.soleil.event.Event
import com.yoyiyi.soleil.module.discover.GameCenterActivity
import com.yoyiyi.soleil.rx.RxBus
import kotlinx.android.synthetic.main.layout_main_toolbar.*

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * @date 创建时间：2017/6/14 12:20
 *  描述:首页Fragment
 */

class HomeFragment : BaseHomeFragment() {


    companion object {
        fun newInstance(): HomeFragment = HomeFragment()
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_main_home
    }

    override fun initWidget() {
        super.initWidget()
        initViewPager()
        llNavigation.setOnClickListener {
            val event = Event.StartNavigationEvent()
            event.start = true
            RxBus.post(event)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        when (id) {
          R.id.menu_game -> startActivity(Intent(mContext, GameCenterActivity::class.java))
        }

        return super.onOptionsItemSelected(item)
    }

    private fun initViewPager() {
        val adapter = MainAdapter(childFragmentManager)
        viewPager.offscreenPageLimit = 5
        viewPager.adapter = adapter
        stlTabs.setViewPager(viewPager)
        viewPager.currentItem = 0

    }
}
