package com.yoyiyi.soleil.module.bangumi

import android.support.v7.widget.GridLayoutManager
import android.text.TextUtils
import com.yoyiyi.soleil.R
import com.yoyiyi.soleil.adapter.bangumi.BangumiScheduleSection
import com.yoyiyi.soleil.base.BaseRefreshActivity
import com.yoyiyi.soleil.bean.bangumi.BangumiSchedule
import com.yoyiyi.soleil.mvp.contract.bangumi.BangumiScheduleContract
import com.yoyiyi.soleil.mvp.presenter.bangumi.BangumiSchedulePresenter
import com.yoyiyi.soleil.utils.TimeUtils
import com.yoyiyi.soleil.widget.section.HeadOrFooterSection
import com.yoyiyi.soleil.widget.section.SectionedRVAdapter
import java.util.*

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/6/8 16:35
 * * 描述:番剧时间表
 */

class BangumiScheduleActivity : BaseRefreshActivity<BangumiSchedulePresenter, BangumiSchedule>(), BangumiScheduleContract.View {

    private var mSectionedAdapter: SectionedRVAdapter? = null
    private val mMonList = ArrayList<BangumiSchedule>()
    private val mTuesList = ArrayList<BangumiSchedule>()
    private val mWedList = ArrayList<BangumiSchedule>()
    private val mThurList = ArrayList<BangumiSchedule>()
    private val mFriList = ArrayList<BangumiSchedule>()
    private val mSatList = ArrayList<BangumiSchedule>()
    private val mSunList = ArrayList<BangumiSchedule>()

    private enum class Week constructor(val week: String) {
        MON("周一"), TUES("周二"), WEB("周三"), THUR("周四"), FRI("周五"), SAT("周六"), SUN("周日")
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_bangumi_schedule
    }

    override fun loadData() {
        mPresenter.getBangumiSchedule()
    }

    override fun initInject() {
        activityComponent.inject(this)
    }

    override fun initPresenter() {
        mPresenter.attachView(this)
    }

    override fun initRecyclerView() {
        mSectionedAdapter = SectionedRVAdapter()
        val mLayoutManager = GridLayoutManager(mContext, 3)
        mLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                when (mSectionedAdapter?.getSectionItemViewType(position)) {
                    SectionedRVAdapter.VIEW_TYPE_HEADER -> return 3//2格
                    else -> return 1
                }
            }
        }
        mRecycler?.layoutManager = mLayoutManager
        mRecycler?.adapter = mSectionedAdapter
    }

    override fun showBangumiSchedule(bangumiScheduleList: List<BangumiSchedule>) {
        mList?.addAll(bangumiScheduleList)
        finishTask()
    }

    override fun finishTask() {
        mList?.forEach { group(it) }

        mSectionedAdapter?.addSection(HeadOrFooterSection(R.layout.layout_item_bangumi_schedule_header))
        mSectionedAdapter?.addSection(
                BangumiScheduleSection(Week.SUN.week, mSunList, TimeUtils.formatDate(mSunList[0].pub_date)))
        mSectionedAdapter?.addSection(
                BangumiScheduleSection(Week.MON.week, mMonList, TimeUtils.formatDate(mMonList[0].pub_date)))
        mSectionedAdapter?.addSection(
                BangumiScheduleSection(Week.TUES.week, mTuesList, TimeUtils.formatDate(mTuesList[0].pub_date)))
        mSectionedAdapter?.addSection(
                BangumiScheduleSection(Week.WEB.week, mWedList, TimeUtils.formatDate(mWedList[0].pub_date)))
        mSectionedAdapter?.addSection(
                BangumiScheduleSection(Week.THUR.week, mTuesList, TimeUtils.formatDate(mTuesList[0].pub_date)))
        mSectionedAdapter?.addSection(
                BangumiScheduleSection(Week.FRI.week, mFriList, TimeUtils.formatDate(mFriList[0].pub_date)))
        mSectionedAdapter?.addSection(
                BangumiScheduleSection(Week.SAT.week, mSatList, TimeUtils.formatDate(mSatList[0].pub_date)))
        mSectionedAdapter?.addSection(HeadOrFooterSection(R.layout.layout_item_bangumi_schedule_footer))
        mSectionedAdapter?.notifyDataSetChanged()
    }

    override fun initToolbar() {
        super.initToolbar()
        mToolbar?.title = "时间表"
    }

    private fun group(schedule: BangumiSchedule) {
        val week = TimeUtils.getWeek(schedule.pub_date)
        if (TextUtils.equals(Week.MON.week, week)) {
            mMonList.add(schedule)
        }
        if (TextUtils.equals(Week.TUES.week, week)) {
            mTuesList.add(schedule)
        }
        if (TextUtils.equals(Week.WEB.week, week)) {
            mWedList.add(schedule)
        }
        if (TextUtils.equals(Week.FRI.week, week)) {
            mFriList.add(schedule)
        }
        if (TextUtils.equals(Week.THUR.week, week)) {
            mThurList.add(schedule)
        }
        if (TextUtils.equals(Week.SAT.week, week)) {
            mSatList.add(schedule)
        }
        if (TextUtils.equals(Week.SUN.week, week)) {
            mSunList.add(schedule)
        }
    }
}
