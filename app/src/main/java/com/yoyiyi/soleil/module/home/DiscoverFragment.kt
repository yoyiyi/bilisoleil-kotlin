package com.yoyiyi.soleil.module.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.yoyiyi.soleil.R
import com.yoyiyi.soleil.base.BaseInjectFragment
import com.yoyiyi.soleil.bean.discover.HotSearchTag
import com.yoyiyi.soleil.constant.Constants

import com.yoyiyi.soleil.module.app.BrowerActivity
import com.yoyiyi.soleil.module.discover.ActivityCenterActivity
import com.yoyiyi.soleil.module.discover.GameCenterActivity
import com.yoyiyi.soleil.module.discover.InterestActivity
import com.yoyiyi.soleil.module.discover.TopicCenterActivity
import com.yoyiyi.soleil.module.recommend.AllStationRankActivity
import com.yoyiyi.soleil.module.region.AllRegionRankActivity
import com.yoyiyi.soleil.module.search.SearchActivity
import com.yoyiyi.soleil.module.search.TotalSearchActivity
import com.yoyiyi.soleil.mvp.contract.home.DiscoverContract
import com.yoyiyi.soleil.mvp.presenter.home.DiscoverPresenter
import com.yoyiyi.soleil.widget.flowlayout.FlowLayout
import com.yoyiyi.soleil.widget.flowlayout.TagAdapter
import kotlinx.android.synthetic.main.fragment_home_discover.*

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/23 14:23
 * * 描述:首页发现界面
 */

@Suppress("DEPRECATION")
class DiscoverFragment : BaseInjectFragment<DiscoverPresenter>(), DiscoverContract.View, View.OnClickListener {


    internal var isShowMore = false

    private val mList = mutableListOf<HotSearchTag.ListBean>()

    override fun getLayoutId(): Int = R.layout.fragment_home_discover


    override fun initInject() {
        fragmentComponent.inject(this)
    }

    override fun initPresenter() {
        mPresenter.attachView(this)
    }

    override fun lazyLoadData() {
        mPresenter.getHotSearchTagData()
    }

    override fun showHotSearchTag(recommend: HotSearchTag) {
        mList.addAll(recommend.list)
        finishTask()
    }

    override fun finishTask() {
        tags_layout?.adapter = object : TagAdapter<HotSearchTag.ListBean>(mList.subList(0, 9)) {
            override fun getView(flowLayout: FlowLayout, i: Int, listBean: HotSearchTag.ListBean): View {
                val mTags = LayoutInflater.from(activity)
                        .inflate(R.layout.layout_hot_tags_item, flowLayout, false) as TextView
                mTags.text = listBean.keyword
                mTags.setOnClickListener { mContext?.startActivity(Intent(mContext, SearchActivity::class.java)) }

                return mTags
            }
        }
        hide_tags_layout?.adapter = object : TagAdapter<HotSearchTag.ListBean>(mList) {
            override fun getView(parent: FlowLayout, position: Int, listBean: HotSearchTag.ListBean): View {
                val mTags = LayoutInflater.from(activity)
                        .inflate(R.layout.layout_hot_tags_item, parent, false) as TextView
                mTags.text = listBean.keyword
                mTags.setOnClickListener { TotalSearchActivity.startActivity(mContext!!, listBean.keyword) };
                mTags.setOnClickListener { mContext?.startActivity(Intent(mContext, SearchActivity::class.java)) }
                return mTags
            }
        }
    }

    override fun initSetListener() {
        ll_more.setOnClickListener(this)
        rl_rank_original.setOnClickListener(this)
        rl_rank_all.setOnClickListener(this)
        rl_topic_center.setOnClickListener(this)
        rl_activity_center.setOnClickListener(this)
        rl_game.setOnClickListener(this)
        rl_mall.setOnClickListener(this)
        rl_black_list.setOnClickListener(this)
        rl_group.setOnClickListener(this)
    }


    override fun onClick(v: View) {
        when (v.id) {
            R.id.ll_more -> {
                if (isShowMore) {
                    isShowMore = false
                    visible(hide_scroll_view)
                    tv_more?.text = "收起"
                    gone(tags_layout)
                    val upDrawable = resources.getDrawable(R.drawable.ic_arrow_up_gray_round)
                    upDrawable.setBounds(0, 0, upDrawable.minimumWidth, upDrawable.minimumHeight)
                    tv_more?.setCompoundDrawables(upDrawable, null, null, null)
                } else {
                    isShowMore = true
                    gone(hide_scroll_view)
                    tv_more?.text = "查看更多"
                    visible(tags_layout)
                    val downDrawable = resources.getDrawable(R.drawable.ic_arrow_down_gray_round)
                    downDrawable.setBounds(0, 0, downDrawable.minimumWidth, downDrawable.minimumHeight)
                    tv_more?.setCompoundDrawables(downDrawable, null, null, null)
                }

            }
            R.id.rl_mall//周边商城
            -> BrowerActivity.startActivity(activity, Constants.SHOP_URL, "bilibili - 周边商城", "")
            R.id.rl_black_list//小黑屋
            -> BrowerActivity.startActivity(activity, Constants.BLACK_URL, "小黑屋", "")
            R.id.rl_rank_all//全站排行
            -> AllRegionRankActivity.startActivity(activity, "番剧")
            R.id.rl_game//游戏中心
            -> startActivity(Intent(getApplicationContext(), GameCenterActivity::class.java))
            R.id.rl_topic_center//话题中心
            -> startActivity(Intent(getApplicationContext(), TopicCenterActivity::class.java))
            R.id.rl_activity_center//活动中心
            -> startActivity(Intent(getApplicationContext(), ActivityCenterActivity::class.java))
            R.id.rl_group
            -> startActivity(Intent(getApplicationContext(), InterestActivity::class.java))
            R.id.rl_rank_original//原创排行
            -> startActivity(Intent(getApplicationContext(), AllStationRankActivity::class.java))

        }

    }

    companion object {

        fun newInstance(): DiscoverFragment {
            return DiscoverFragment()
        }
    }
}
