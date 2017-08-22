package com.yoyiyi.soleil.module.recommend

import com.yoyiyi.soleil.R
import com.yoyiyi.soleil.module.region.BaseRegionActivity
import com.yoyiyi.soleil.mvp.presenter.app.NothingPresenter
import javax.annotation.Nullable

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/30 18:11
 * * 描述:全站排行
 */
class AllStationRankActivity : BaseRegionActivity<NothingPresenter, Nullable>() {

    private val mTitlesArr = arrayOf("原创", "全站", "番剧")

    private val mTypesArr = arrayOf("origin-03.json", "all-03.json", "all-3-33.json")

    override fun getLayoutId(): Int = R.layout.activity_region_type


    override fun initWidget() {
        super.initWidget()
        mViewPager?.offscreenPageLimit = mTitlesArr.size + 1
        mViewPager?.adapter = BaseRegionTypeAdapter(supportFragmentManager)
        mSlidingTabLayout?.setViewPager(mViewPager)
        mViewPager?.currentItem = 0

    }

    override fun initVariables() {
        for (i in mTitlesArr.indices) {
            mTitles.add(mTitlesArr[i])
            mFragment.add(AllStationRankFragment.newInstance(mTypesArr[i]))
        }
    }

    override fun initToolbar() {
        super.initToolbar()
        setTitle("排行榜")
    }

    override fun initInject() {
        activityComponent.inject(this)
    }




}
