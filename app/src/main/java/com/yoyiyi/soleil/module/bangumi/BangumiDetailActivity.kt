package com.yoyiyi.soleil.module.bangumi

import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.yoyiyi.soleil.R
import com.yoyiyi.soleil.adapter.bangumi.BangumiDetailAdapter
import com.yoyiyi.soleil.base.BaseRefreshActivity
import com.yoyiyi.soleil.bean.bangumi.MulBangumiDetail
import com.yoyiyi.soleil.mvp.contract.bangumi.BangumiDetailContract
import com.yoyiyi.soleil.mvp.presenter.bangumi.BangumiDetailPresenter
import com.yoyiyi.soleil.widget.statusbar.StatusBarUtil
import kotlinx.android.synthetic.main.activity_bangumi_detail.*

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/6/10 14:39
 * * 描述:番剧详情界面
 */
class BangumiDetailActivity : BaseRefreshActivity<BangumiDetailPresenter, MulBangumiDetail>(), BangumiDetailContract.View {


    private var mDistanceY: Int = 0
    private var mAdapter: BangumiDetailAdapter? = null

    override fun getLayoutId(): Int = R.layout.activity_bangumi_detail


    override fun initInject() {
        activityComponent.inject(this)
    }

    override fun initPresenter() {
        mPresenter.attachView(this)
    }

    override fun initToolbar() {
        super.initToolbar()
        mToolbar?.title = ""//设置标题

    }

    override fun initRecyclerView() {
        mList?.let {
            mAdapter = BangumiDetailAdapter(it)
        }
        mRecycler?.setHasFixedSize(true)
        mRecycler?.isNestedScrollingEnabled = false
        val mLayoutManager = LinearLayoutManager(mContext)
        mRecycler?.layoutManager = mLayoutManager
        mRecycler?.adapter = mAdapter
        initHead()
        mAdapter?.notifyDataSetChanged()
    }

    private fun initHead() {
        mList?.add(MulBangumiDetail(itemTypez = MulBangumiDetail.TYPE_HEAD, isPrepare = true))

    }

    override fun loadData() {
        mPresenter.getBangumiDetailData()
    }

    override fun initWidget() {
        super.initWidget()
        mRecycler?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                //滑动的距离
                mDistanceY += dy
                //toolbar的高度
                val toolbarHeight = mToolbar?.bottom
                toolbarHeight?.let {
                    if (mDistanceY <= it && toolbarHeight != 0) {
                        val scale = mDistanceY / toolbarHeight
                        val alpha = scale * 255
                        mToolbar?.setBackgroundColor(Color.argb(alpha, 251, 114, 153))
                    }
                } ?: mToolbar?.setBackgroundResource(R.color.colorPrimary)

              /*  //当滑动的距离 <= toolbar高度的时候，改变Toolbar背景色的透明度，达到渐变的效果
                if (mDistanceY <= toolbarHeight) {
                    val scale = mDistanceY / toolbarHeight
                    val alpha = scale * 255
                    mToolbar?.setBackgroundColor(Color.argb(alpha.toInt(), 251, 114, 153))
                } else {
                    //上述虽然判断了滑动距离与toolbar高度相等的情况，但是实际测试时发现，标题栏的背景色
                    //很少能达到完全不透明的情况，所以这里又判断了滑动距离大于toolbar高度的情况，
                    //将标题栏的颜色设置为完全不透明状态
                    mToolbar?.setBackgroundResource(R.color.colorPrimary)
                }*/

            }
        })
    }

    override fun initStatusBar() {
        StatusBarUtil.setTranslucentForCoordinatorLayout(this, 0)
    }

    override fun finishTask() {
        mAdapter?.notifyDataSetChanged()
    }

    override fun showMulBangumiDetail(mulBangumiDetails: List<MulBangumiDetail>, title: String) {
        mList?.clear()
        mList?.addAll(mulBangumiDetails)
        tv_title?.text = title
        finishTask()
    }
}
