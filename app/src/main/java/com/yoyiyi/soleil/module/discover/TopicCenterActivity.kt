package com.yoyiyi.soleil.module.discover

import android.support.v7.widget.LinearLayoutManager

import com.yoyiyi.soleil.R
import com.yoyiyi.soleil.adapter.discover.TopicCenterAdapter
import com.yoyiyi.soleil.base.BaseRefreshActivity
import com.yoyiyi.soleil.bean.discover.TopicCenter
import com.yoyiyi.soleil.mvp.contract.discover.TopicCenterContract
import com.yoyiyi.soleil.mvp.presenter.discover.TopicCenterPresenter

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/6/5 22:28
 * * 描述:话题中心
 */
class TopicCenterActivity : BaseRefreshActivity<TopicCenterPresenter, TopicCenter.ListBean>(), TopicCenterContract.View {

    private var mAdapter: TopicCenterAdapter? = null

    override fun getLayoutId(): Int {
        return R.layout.activity_topic_center
    }

    override fun initToolbar() {
        super.initToolbar()
        mToolbar?.title = "话题中心"
    }

    override fun loadData() {
        mPresenter.getTopicCenterData()
    }


    override fun initPresenter() {
        mPresenter.attachView(this)
    }

    override fun initInject() {
        activityComponent.inject(this)
    }

    override fun initRecyclerView() {
        mAdapter = TopicCenterAdapter(mList)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mRecycler?.layoutManager = layoutManager
        mRecycler?.adapter = mAdapter
    }

    override fun showTopicCenter(topicCenterList: List<TopicCenter.ListBean>) {
        mList?.addAll(topicCenterList)
        finishTask()
    }

    override fun finishTask() {
        mAdapter?.notifyDataSetChanged()
    }
}
