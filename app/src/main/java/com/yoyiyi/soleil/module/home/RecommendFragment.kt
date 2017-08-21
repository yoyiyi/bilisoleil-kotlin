package com.yoyiyi.soleil.module.home

import android.content.Intent
import android.support.v7.widget.GridLayoutManager
import com.yoyiyi.soleil.R
import com.yoyiyi.soleil.adapter.home.RecommendAdapter
import com.yoyiyi.soleil.base.BaseRefreshFragment
import com.yoyiyi.soleil.bean.recommend.MulRecommend
import com.yoyiyi.soleil.bean.recommend.Recommend
import com.yoyiyi.soleil.module.recommend.AllStationRankActivity
import com.yoyiyi.soleil.mvp.contract.home.RecommendContract
import com.yoyiyi.soleil.mvp.presenter.home.RecommendPresenter
import com.yoyiyi.soleil.utils.AppUtils
import com.yoyiyi.soleil.widget.divider.VerticalDividerItemDecoration
import kotlinx.android.synthetic.main.fragment_home_recommend.*

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/23 14:23
 * * 描述:推荐
 */

class RecommendFragment : BaseRefreshFragment<RecommendPresenter, MulRecommend>(), RecommendContract.View {

    private var mAdapter: RecommendAdapter? = null

    override fun getLayoutId(): Int = R.layout.fragment_home_recommend


    override fun initInject() {
        fragmentComponent.inject(this)
    }

    override fun initPresenter() {
        mPresenter.attachView(this)
    }

    override fun lazyLoadData() {
        mPresenter.getRecommendData()
    }

    override fun initWidget() {
        super.initWidget()
        ivRank?.setOnClickListener { startActivity(Intent(activity, AllStationRankActivity::class.java)) }
    }

    override fun initRecyclerView() {
        mAdapter = RecommendAdapter(mList)
        val mLayoutManager = GridLayoutManager(activity, 2)
        mAdapter?.setSpanSizeLookup({ _, i -> mList[i].spanSize })
        mRecycler?.layoutManager = mLayoutManager
        mRecycler?.adapter = mAdapter
        //添加分割条
        val build = VerticalDividerItemDecoration.Builder(activity)
                .color(AppUtils.getColor(R.color.transparent))
                // .color(AppUtils.getColor(R.color.colorPrimary))
                .sizeResId(R.dimen.dp10)
                .showLastDivider()
                .build()
        mRecycler?.addItemDecoration(build)
    }

    override fun showRecommend(recommend: List<Recommend>) {
        recommend.forEach {
            if (it.banner_item != null && it.banner_item.isNotEmpty()) {
                mList.add(MulRecommend(itemTypez = MulRecommend.TYPE_HEADER, spanSize = MulRecommend.HEADER_SPAN_SIZE, data = it.banner_item))
            } else {
                mList.add(MulRecommend(itemTypez = MulRecommend.TYPE_ITEM, spanSize = MulRecommend.ITEM_SPAN_SIZE, recommend = it))
            }

        }
        finishTask()
    }

    override fun finishTask() {
        mAdapter?.notifyDataSetChanged()
    }

    companion object {

        fun newInstance(): RecommendFragment = RecommendFragment()

    }

}
