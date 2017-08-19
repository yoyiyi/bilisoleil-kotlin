package com.yoyiyi.soleil.module.home

import android.support.v7.widget.LinearLayoutManager
import com.yoyiyi.soleil.R
import com.yoyiyi.soleil.adapter.home.section.chase.*
import com.yoyiyi.soleil.base.BaseRefreshFragment
import com.yoyiyi.soleil.bean.chase.ChaseBangumi
import com.yoyiyi.soleil.bean.chase.RecommendBangumi
import com.yoyiyi.soleil.mvp.contract.home.ChaseBangumiContract
import com.yoyiyi.soleil.mvp.presenter.home.ChaseBangumiPresenter
import com.yoyiyi.soleil.widget.section.SectionedRVAdapter

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/23 14:23
 * * 描述:首页追番
 */

class ChaseBangumiFragment : BaseRefreshFragment<ChaseBangumiPresenter, ChaseBangumi.Follows>(), ChaseBangumiContract.View {
    private var mSectionedAdapter: SectionedRVAdapter? = null
    @Volatile private var mChaseBangumi: ChaseBangumi? = null
    private var mRecommendCnBean: RecommendBangumi.RecommendCn? = null
    private var mRecommendJpBean: RecommendBangumi.RecommendJp? = null
    private var mRecommendBangumi: RecommendBangumi? = null

    companion object {

        fun newInstance(): ChaseBangumiFragment {
            return ChaseBangumiFragment()
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_home_chase_bangumi


    override fun clear() {
        mSectionedAdapter?.removeAllSections()
    }

    override fun lazyLoadData() {
        mPresenter.getChaseBangumiData()
    }

    override fun initInject() {
        fragmentComponent.inject(this)
    }

    override fun initPresenter() {
        mPresenter.attachView(this)
    }

    override fun initRecyclerView() {
        mSectionedAdapter = SectionedRVAdapter()
        mRecycler?.setHasFixedSize(true)
        val mLayoutManager = LinearLayoutManager(activity)
        mRecycler?.layoutManager = mLayoutManager
        mRecycler?.adapter = mSectionedAdapter
    }

    override fun showChaseBangumi(chaseBangumi: ChaseBangumi) {
        mChaseBangumi = chaseBangumi
    }

    override fun showRecommendBangumi(recommendBangumi: RecommendBangumi) {
        mChaseBangumi?.follows?.let { mList.addAll(it) }
        mRecommendBangumi = recommendBangumi
        mRecommendCnBean = recommendBangumi.recommend_cn
        mRecommendJpBean = recommendBangumi.recommend_jp
        finishTask()
    }


    override fun finishTask() {
        mSectionedAdapter?.addSection(ChaseIndexSection())
        mSectionedAdapter?.addSection(ChaseFollowSection("${mChaseBangumi?.update_count}", mList))
        mRecommendBangumi?.ad?.let {
                if (it.isNotEmpty()) {
                    mSectionedAdapter?.addSection(ChaseAdSection(it[0]))
            }
        }
        mSectionedAdapter?.addSection(mRecommendJpBean?.recommend?.let { mRecommendJpBean?.foot?.get(0)?.let { it1 -> ChaseRecommendJPSection(it, it1) } })
        mSectionedAdapter?.addSection(mRecommendCnBean?.recommend?.let { mRecommendCnBean?.foot?.get(0)?.let { it1 -> ChaseRecommendCNSection(it, it1) } })
        mSectionedAdapter?.notifyDataSetChanged()
    }


}
