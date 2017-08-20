package com.yoyiyi.soleil.module.discover

import android.view.Menu
import android.view.MenuItem
import com.yoyiyi.soleil.R
import com.yoyiyi.soleil.module.region.BaseRegionActivity
import com.yoyiyi.soleil.mvp.presenter.app.NothingPresenter
import javax.annotation.Nullable

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/6/10 22:25
 * * 描述:兴趣圈
 */
class InterestActivity : BaseRegionActivity<NothingPresenter, Nullable>() {
    private val mTitlesArr = arrayOf("首页", "发现", "我的")

    override fun getLayoutId(): Int = R.layout.activity_interest


    override fun initWidget() {
        super.initWidget()
        mViewPager?.offscreenPageLimit = mTitlesArr.size + 1
        mViewPager?.adapter = BaseRegionTypeAdapter(supportFragmentManager)
        mSlidingTabLayout?.setViewPager(mViewPager)
        mViewPager?.currentItem = 1

    }

    override fun initInject() {
        activityComponent.inject(this)
    }



    override fun initVariables() {
        for (i in mTitlesArr.indices) {
            mTitles.add(mTitlesArr[i])
            if (i == 1) {
                mFragment.add(InterestFragment())
            } else if (i == 0) {
                mFragment.add(HomeFragment())
            } else {
                mFragment.add(MineFragment())
            }
        }
    }

    override fun initToolbar() {
        super.initToolbar()
        mToolbar?.title = "兴趣圈"
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_interest, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {

        }

        return super.onOptionsItemSelected(item)
    }
}
