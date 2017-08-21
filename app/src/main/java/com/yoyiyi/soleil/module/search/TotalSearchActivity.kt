package com.yoyiyi.soleil.module.search

import android.content.Context
import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.jakewharton.rxbinding2.view.RxView
import com.yoyiyi.soleil.R
import com.yoyiyi.soleil.base.BaseInjectActivity
import com.yoyiyi.soleil.bean.search.SearchArchive
import com.yoyiyi.soleil.constant.Constants

import com.yoyiyi.soleil.mvp.contract.search.TotalSearchContract
import com.yoyiyi.soleil.mvp.presenter.search.TotalSearchPresenter
import com.yoyiyi.soleil.utils.AppUtils
import com.yoyiyi.soleil.utils.StatusBarFontUtil
import com.yoyiyi.soleil.widget.statusbar.StatusBarUtil
import kotlinx.android.synthetic.main.activity_total_search.*
import kotlinx.android.synthetic.main.layout_search_view.*
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/29 10:44
 * * 描述:搜索界面
 */
class TotalSearchActivity : BaseInjectActivity<TotalSearchPresenter>(), TotalSearchContract.View {

    private var mKeyword = ""

    private val mList = ArrayList<SearchArchive.NavBean>()
    private var mAnimationDrawable: AnimationDrawable? = null
    private val mTitles = ArrayList<String>()
    private val mFragments = ArrayList<Fragment>()

    override fun getLayoutId(): Int = R.layout.activity_total_search


    override fun initStatusBar() {
        //设置状态栏颜色
        StatusBarUtil.setColorNoTranslucent(this, AppUtils.getColor(R.color.gray_light_30))
        //设置状态栏字体
        StatusBarFontUtil.from(this).setLightStatusBar(true).process()
    }

    override fun initInject() {
        activityComponent.inject(this)
    }

    override fun initPresenter() {
        mPresenter.attachView(this)
    }

    override fun initVariables() {
        intent?.let {
            mKeyword = it.getStringExtra(Constants.EXTRA_KEYWORD)

        }
    }

    override fun initWidget() {
        initSearchView()
    }


    private fun initSearchView() {
        et_search.setText(mKeyword)
        iv_search_back.setOnClickListener { onBackPressed() }
        RxView.clicks(iv_search)
                .compose<Any>(bindToLifecycle<Any>())
                .throttleFirst(5, TimeUnit.SECONDS)
                .subscribe { o ->
                    //TODO  发起搜索请求

                }
    }

    override fun showSearchNav(navBeans: List<SearchArchive.NavBean>) {
        mList.clear()
        mList.addAll(navBeans)
        finishTask()
    }

    override fun showLoading() {
        iv_search_load.setImageResource(R.drawable.anim_search_loading)
        mAnimationDrawable = iv_search_load.drawable as AnimationDrawable
        visible(iv_search_load)
        gone(rl_search_content)
        mAnimationDrawable?.start()
    }


    override fun finishTask() {
        mTitles.add("综合")
        mTitles.add(mList[0].name + formatTotal(mList[0].total))
        mTitles.add(mList[1].name + formatTotal(mList[1].total))
        mTitles.add(mList[2].name + formatTotal(mList[2].total))

        mFragments.add(ArchiveFragment.newsInstance())
        mFragments.add(SeasonFragment.newsInstance())
        mFragments.add(UpFragment.newsInstance())
        mFragments.add(MovieFragment.newsInstance())

        val navAdapter = SearchNavAdapter(getSupportFragmentManager(), mTitles, mFragments)
        view_pager.adapter = navAdapter
        view_pager.offscreenPageLimit = mTitles.size
        sliding_tabs.setViewPager(view_pager)
        //设置指示条宽度
        sliding_tabs.currentTab = 0
        navAdapter.notifyDataSetChanged()
        sliding_tabs.notifyDataSetChanged()
    }

    private fun formatTotal(total: Int): String {
        if (total == 0)
            return ""
        else if (total > 99)
            return "(99+)"
        else
            return "($total)"
    }

    override fun complete() {
        gone(iv_search_load)
        visible(rl_search_content)
        mAnimationDrawable?.stop()
    }

    override fun showError(msg: String) {
        iv_search_load.setImageResource(R.drawable.search_failed)
        visible(iv_search_load)
        gone(rl_search_content)
        mAnimationDrawable?.stop()
    }

    override fun loadData() {
        mPresenter.getSearchNavData(mKeyword, 1, 20)
    }

    private class SearchNavAdapter(fm: FragmentManager, private val titles: List<String>, private val fragments: List<Fragment>) : FragmentStatePagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            return fragments[position]
        }

        override fun getCount(): Int {
            return fragments.size
        }

        override fun getPageTitle(position: Int): CharSequence {
            return titles[position]
        }
    }

    override fun onBackPressed() {
        if (mAnimationDrawable != null && mAnimationDrawable!!.isRunning) {
            mAnimationDrawable!!.stop()
            mAnimationDrawable = null
        }
        super.onBackPressed()
    }

    companion object {

        fun startActivity(context: Context, keyword: String) {
            val intent = Intent(context, TotalSearchActivity::class.java)
            intent.putExtra(Constants.EXTRA_KEYWORD, keyword)
            context.startActivity(intent)
        }
    }
}
