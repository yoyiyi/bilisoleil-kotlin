package com.yoyiyi.soleil.module.region

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.yoyiyi.soleil.R
import com.yoyiyi.soleil.constant.Constants
import com.yoyiyi.soleil.mvp.presenter.app.NothingPresenter
import javax.annotation.Nullable

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/30 18:11
 * * 描述:全区排行界面
 */
class AllRegionRankActivity : BaseRegionActivity<NothingPresenter, Nullable>() {

    private var mTitle: String? = null
    private val mTitlesArr = mutableListOf("番剧", "动画", "音乐", "舞蹈", "游戏", "科技", "生活", "鬼畜", "时尚", "娱乐", "电影", "电视剧")

    private val mTypesArr = mutableListOf("all-03-13.json", "all-03-1.json", "all-03-3.json", "all-03-129.json", "all-03-4.json", "all-03-36.json", "all-03-160.json", "all-03-155.json", "all-03-5.json", "all-03-119.json", "all-03-23.json", "all-03-11.json")




    override fun getLayoutId(): Int = R.layout.activity_region_type


    override fun initVariables() {
        intent?.let {
            val bundle = it.getBundleExtra(Constants.EXTRA_BUNDLE)
            mTitle = bundle.getString(Constants.EXTRA_TITLE)

        }

    }

    override fun initTitle() {
        for (index in mTitlesArr.indices) {
            mTitles.add(mTitlesArr[index])
            mFragment.add(AllRegionRankFragment.newInstance(mTypesArr[index]))
        }
    }

    override fun initViewPager() {
        super.initViewPager()
        when (mTitle) {
            "番剧" -> mViewPager?.currentItem = 0
            "动画" -> mViewPager?.currentItem = 1
            "音乐" -> mViewPager?.currentItem = 2
            "舞蹈" -> mViewPager?.currentItem = 3
            "游戏" -> mViewPager?.currentItem = 4
            "科技" -> mViewPager?.currentItem = 5
            "生活" -> mViewPager?.currentItem = 6
            "鬼畜" -> mViewPager?.currentItem = 7
            "时尚" -> mViewPager?.currentItem = 8
            "电影" -> mViewPager?.currentItem = 9
            "电视剧" -> mViewPager?.currentItem = 10
        }
    }

    override fun initWidget() {
        super.initWidget()
        finishTask()
    }

    override fun initToolbar() {
        super.initToolbar()
        setTitle("全区排行榜")
    }

    override fun initInject() {
        activityComponent.inject(this)
    }

    companion object {


        fun startActivity(context: Context, title: String) {
            val bundle = Bundle()
            val intent = Intent(context, AllRegionRankActivity::class.java)
            bundle.putString(Constants.EXTRA_TITLE, title)
            intent.putExtra(Constants.EXTRA_BUNDLE, bundle)
            context.startActivity(intent)
        }
    }


}
