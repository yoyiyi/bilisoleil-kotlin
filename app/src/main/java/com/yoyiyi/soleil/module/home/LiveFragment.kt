package com.yoyiyi.soleil.module.home

import android.support.v7.widget.GridLayoutManager
import com.yoyiyi.soleil.R
import com.yoyiyi.soleil.adapter.home.section.live.LiveBannerSection
import com.yoyiyi.soleil.base.BaseRefreshFragment
import com.yoyiyi.soleil.bean.live.LivePartition
import com.yoyiyi.soleil.bean.live.LiveRecommend
import com.yoyiyi.soleil.mvp.contract.home.LiveContract
import com.yoyiyi.soleil.mvp.presenter.home.LivePresenter
import com.yoyiyi.soleil.widget.section.SectionedRVAdapter

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/23 14:23
 * * 描述:推荐
 */

class LiveFragment : BaseRefreshFragment<LivePresenter, LiveRecommend.RecommendDataBean.LivesBean>(), LiveContract.View {

    private var mSectionedAdapter: SectionedRVAdapter? = null

    private val mBannerList = arrayListOf<LivePartition.BannerBean>()//轮播条
    private val mBannerRecommendList = arrayListOf<LiveRecommend.RecommendDataBean.BannerDataBean>()//推荐
    private val mPartitionsBeanList = arrayListOf<LivePartition.PartitionsBean>()//推荐直播

    private var mPartitionBean: LiveRecommend.RecommendDataBean.PartitionBean? = null
    @Volatile private var mLivePartition: LivePartition? = null

    companion object {
        fun newInstance(): LiveFragment {
            return LiveFragment()
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_home_live

    override fun lazyLoadData() {
        mPresenter.getLiveData()
    }


    override fun initPresenter() {
        mPresenter.attachView(this)
    }

    override fun initInject() {
        fragmentComponent.inject(this)
    }

    override fun initRecyclerView() {
        mSectionedAdapter = SectionedRVAdapter()
        val mLayoutManager = GridLayoutManager(activity, 2)
        mLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                when (mSectionedAdapter?.getSectionItemViewType(position)) {
                    SectionedRVAdapter.VIEW_TYPE_HEADER -> return 2//2格
                    SectionedRVAdapter.VIEW_TYPE_FOOTER -> return 2//2格
                    else -> return 1
                }
            }
        }
        mRecycler?.layoutManager = mLayoutManager
        mRecycler?.adapter = mSectionedAdapter
    }


    override fun clear() {
        mBannerRecommendList.clear()
        mBannerList.clear()
        mPartitionsBeanList.clear()
        mSectionedAdapter?.removeAllSections()
    }

    override fun showLiveRecommend(liveRecommend: LiveRecommend) {
        mLivePartition?.banner.let { mBannerList.addAll(mLivePartition?.banner!!) }
        liveRecommend.recommend_data?.lives.let { mList.addAll(liveRecommend.recommend_data!!.lives!!) }
        /*  mPartitionsBeanList.addAll(mLivePartition?.partitions)
          mList?.addAll()

          mList?.addAll(liveRecommend?.recommend_data?.lives)
          mBannerRecommendList.addAll(liveRecommend.recommend_data.banner_data)*/
        mPartitionBean = liveRecommend.recommend_data?.partition
        finishTask()
    }

    override fun showLivePartition(livePartition: LivePartition) {
        mLivePartition = livePartition
    }

    override fun finishTask() {
        mSectionedAdapter?.addSection(LiveBannerSection(mBannerList))
        /* mSectionedAdapter!!.addSection(LiveEntranceSection())
         //推荐主播
         if (mBannerRecommendList.size != 0) {
             val allot = mList!!.size / 2
             if (mBannerRecommendList.size == 1) {
                 mSectionedAdapter!!.addSection(LiveRecommendSection(true, false,
                         mPartitionBean!!.name,
                         mPartitionBean!!.sub_icon.src, mPartitionBean!!.count + "",
                         mList!!.subList(0, allot)))
                 mSectionedAdapter!!.addSection(LiveRecommendBannerSection(mBannerRecommendList.get(0)))
                 mSectionedAdapter!!.addSection(LiveRecommendSection(false, true,
                         mPartitionBean!!.name,
                         mPartitionBean!!.sub_icon.src, mPartitionBean!!.count + "",
                         mList!!.subList(allot, mList!!.size)))
             } else {
                 mSectionedAdapter!!.addSection(LiveRecommendSection(true, false, mPartitionBean!!.name,
                         mPartitionBean!!.sub_icon.src, mPartitionBean!!.count + "",
                         mList!!.subList(0, allot),
                         mBannerRecommendList.get(0)))
                 mSectionedAdapter!!.addSection(LiveRecommendBannerSection(mBannerRecommendList.get(1)))
                 mSectionedAdapter!!.addSection(LiveRecommendSection(false, true, mPartitionBean!!.name,
                         mPartitionBean!!.sub_icon.src, mPartitionBean!!.count + "",
                         mList!!.subList(allot, mList!!.size)))
             }
         } else {
             mSectionedAdapter!!.addSection(LiveRecommendSection(true, true, mPartitionBean!!.name,
                     mPartitionBean!!.sub_icon.src, mPartitionBean!!.count + "", mList))
         }
         //分区
         Stream.of(mPartitionsBeanList.subList(0, mPartitionsBeanList.size - 1)).forEach { partitionsBean ->
             mSectionedAdapter!!.addSection(LiveRecommendPartitionSection(partitionsBean.partition.name,
                     partitionsBean.partition.sub_icon.src,
                     partitionsBean.partition.count + "", partitionsBean.lives.subList(0, 4)))
         }
         //显示最后---更多直播
         mSectionedAdapter!!.addSection(
                 LiveRecommendPartitionSection(true, mPartitionsBeanList.get(mPartitionsBeanList.size - 1).partition.name,
                         mPartitionsBeanList.get(mPartitionsBeanList.size - 1).partition.sub_icon.src,
                         mPartitionsBeanList.get(mPartitionsBeanList.size - 1).partition.count + "",
                         mPartitionsBeanList.get(mPartitionsBeanList.size - 1).lives.subList(0, 4)))*/
        mSectionedAdapter?.notifyDataSetChanged()
    }


}
