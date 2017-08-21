package com.yoyiyi.soleil.module.app.up

import android.annotation.SuppressLint
import android.view.Menu
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.yoyiyi.soleil.R
import com.yoyiyi.soleil.bean.user.UpDetail
import com.yoyiyi.soleil.event.Event

import com.yoyiyi.soleil.module.region.BaseRegionActivity
import com.yoyiyi.soleil.mvp.contract.app.up.UpDetailContract
import com.yoyiyi.soleil.mvp.presenter.app.up.UpDetailPresenter
import com.yoyiyi.soleil.rx.RxBus
import com.yoyiyi.soleil.utils.NumberUtils
import com.yoyiyi.soleil.utils.SpanUtils
import com.yoyiyi.soleil.widget.statusbar.StatusBarUtil
import kotlinx.android.synthetic.main.activity_up_detail.*
import java.util.*
import javax.annotation.Nullable

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/6/16 14:50
 * * 描述:up主详情街界面
 */

class UpDetailActivity : BaseRegionActivity<UpDetailPresenter, Nullable>(), UpDetailContract.View {

    private lateinit var mUpDetail: UpDetail
    var mName: String = ""

    override fun getLayoutId(): Int {
        return R.layout.activity_up_detail
    }


    override fun showUpDetail(upDetail: UpDetail) {
        mUpDetail = upDetail
        finishTask()
    }

    override fun loadData() {
        mPresenter.getUpDetailData()
    }

    override fun initInject() {
        activityComponent.inject(this)
    }

    override fun initWidget() {
        super.initWidget()
        visible(pw_loading)
        app_bar_layout.addOnOffsetChangedListener { _, verticalOffset ->
            if (verticalOffset == 0) {
                toolbar.title = mName
            } else {
                toolbar.title = ""
            }
        }
    }

    override fun showError(msg: String) {
        super.showError(msg)
        gone(pw_loading)
    }

    override fun complete() {
        super.complete()
        gone(pw_loading)
    }

    override fun finishTask() {
        initUpInfo()
        super.finishTask()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        //  getMenuInflater().inflate(R.menu.menu_region, menu);
        return false
    }

    override fun initTitle() {
        mTitles.addAll(Arrays.asList(
                "主页",
                "投稿(${mUpDetail.data.archive.count})",
                "收藏(${mUpDetail.data.favourite.count})",
                "追番",
                "兴趣圈",
                "投币",
                "游戏"
        ))
    }

    override fun initFragment() {
        mFragment.add(ArchiveFragment.newInstance(1))
        mFragment.add(SubmitedVideoFragment.newInstance(mUpDetail.data.setting.submited_video))
        mFragment.add(FavouriteFragment.newInstance(mUpDetail.data.setting.fav_video))
        mFragment.add(BangumiFragment.newInstance(mUpDetail.data.setting.bangumi))
        mFragment.add(GroupFragment.newInstance(mUpDetail.data.setting.groups))
        mFragment.add(CoinsVideoFragment.newInstance(mUpDetail.data.setting.coins_video))
        mFragment.add(PlayGamesFragment.newInstance(mUpDetail.data.setting.played_game))
    }

    override fun initViewPager() {
        super.initViewPager()
        setCurrentItem(0)
    }

    /**
     * 初始化发射事件
     */
    override fun initEvent() {
        //投稿
        val upDetailSubmitedVideoEvent = Event.UpDetailSubmitedVideoEvent()
        upDetailSubmitedVideoEvent.archivList = mUpDetail.data.archive.item
        RxBus.post(upDetailSubmitedVideoEvent)
        //收藏
        val upDetailFavourteEvent = Event.UpDetailFavourteEvent()
        upDetailFavourteEvent.favouriteList = mUpDetail.data.favourite.item
        RxBus.post(upDetailFavourteEvent)
        //主页
        val upDetailArchiveEvent = Event.UpDetailArchiveEvent()
        upDetailArchiveEvent.archive = mUpDetail.data.archive
        upDetailArchiveEvent.setting = mUpDetail.data.setting
        upDetailArchiveEvent.favourite = mUpDetail.data.favourite
        upDetailArchiveEvent.live = mUpDetail.data.live
        RxBus.post(upDetailArchiveEvent)
        //追番
        //兴趣圈
        //投币

    }


    @SuppressLint("SetTextI18n")
    private fun initUpInfo() {

        mName = mUpDetail.data.card.face
        //设置图片
        Glide.with(mContext)
                .load(mUpDetail.data.card.face)
                .centerCrop()
                .placeholder(R.drawable.bili_default_avatar)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
                .into(iv_avatar)
        //设置基本信息
        tv_uname?.text = SpanUtils()
                .append(mUpDetail.data.card.name)
                .appendSpace(18)
                .appendImage(getSex(mUpDetail.data.card.sex), SpanUtils.ALIGN_CENTER)
                .appendSpace(18)
                .appendImage(getLv(mUpDetail.data.card.level_info.current_level), SpanUtils.ALIGN_CENTER)
                .create()
        tv_fans?.text = "${NumberUtils.format("${mUpDetail.data.card.fans}")} 粉丝"
        tv_favourite?.text = "${NumberUtils.format("${mUpDetail.data.card.attention}")} 关注"
        tv_user_des?.text = mUpDetail.data.card.sign
    }


    override fun initToolbar() {
        mToolbar?.setNavigationIcon(R.drawable.ic_clip_back_white)
        mToolbar?.title = ""
    }

    override fun initStatusBar() {
        StatusBarUtil.setTranslucentForCoordinatorLayout(this, 0)
    }

    private fun getSex(sex: String): Int {
        when (sex) {
            "男" -> return R.drawable.ic_user_male
            "女" -> return R.drawable.ic_user_female
            else//握草 gay
            -> return R.drawable.ic_user_gay_border
        }
    }


    private fun getLv(lv: Int): Int {
        val lvRes: Int
        when (lv) {
            1 -> lvRes = R.drawable.ic_lv1_large
            2 -> lvRes = R.drawable.ic_lv2_large
            3 -> lvRes = R.drawable.ic_lv3_large
            4 -> lvRes = R.drawable.ic_lv4_large
            5 -> lvRes = R.drawable.ic_lv5_large
            6 -> lvRes = R.drawable.ic_lv6_large
            7 -> lvRes = R.drawable.ic_lv7_large
            8 -> lvRes = R.drawable.ic_lv8_large
            9 -> lvRes = R.drawable.ic_lv9_large
            else -> lvRes = R.drawable.ic_lv0_large
        }
        return lvRes
    }
}
