package com.yoyiyi.soleil.module.discover

import android.support.v7.widget.LinearLayoutManager
import com.yoyiyi.soleil.R
import com.yoyiyi.soleil.adapter.discover.section.GameCenterBookGiftSection
import com.yoyiyi.soleil.adapter.discover.section.GameCenterGameListSection
import com.yoyiyi.soleil.adapter.discover.section.GameCenterUserSection
import com.yoyiyi.soleil.base.BaseRefreshActivity
import com.yoyiyi.soleil.bean.discover.GameCenter
import com.yoyiyi.soleil.mvp.contract.discover.GameCenterContract
import com.yoyiyi.soleil.mvp.presenter.discover.GameCenterPresenter
import com.yoyiyi.soleil.widget.section.SectionedRVAdapter
import java.util.*

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/6/5 22:28
 * * 描述:游戏中心
 */
class GameCenterActivity : BaseRefreshActivity<GameCenterPresenter, GameCenter.GameListBean>(), GameCenterContract.View {
    private var mSectionedAdapter: SectionedRVAdapter? = null
    private val mBookGiftBeanList = ArrayList<GameCenter.BookGiftBean>()

    override fun getLayoutId(): Int = R.layout.activity_game_center


    override fun initToolbar() {
        super.initToolbar()
        mToolbar?.title = "游戏中心"
    }

    override fun loadData() {
        mPresenter.getGameCenterData()
    }


    override fun initInject() {
        activityComponent.inject(this)
    }

    override fun initPresenter() {
        mPresenter.attachView(this)
    }

    override fun initRecyclerView() {
        mSectionedAdapter = SectionedRVAdapter()
        val layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)
        mRecycler?.layoutManager = layoutManager
        mRecycler?.adapter = mSectionedAdapter
    }

    override fun showGameCenter(gameCenter: GameCenter) {
        if (!gameCenter.game_list.isEmpty() && gameCenter.game_list.size >= 20) {
            mList?.addAll(gameCenter.game_list.subList(0, 20))
        } else {
            mList?.addAll(gameCenter.game_list)
        }
        mBookGiftBeanList.addAll(gameCenter.book_gift)
        finishTask()
    }

    override fun finishTask() {
        mSectionedAdapter?.addSection(GameCenterUserSection())
        mSectionedAdapter?.addSection(GameCenterBookGiftSection(mBookGiftBeanList))
        mList?.let {
            mSectionedAdapter?.addSection(GameCenterGameListSection(true, it))
        }
        mSectionedAdapter?.notifyDataSetChanged()
    }
}
