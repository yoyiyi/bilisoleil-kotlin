package com.yoyiyi.soleil.adapter.bangumi

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yoyiyi.soleil.R
import com.yoyiyi.soleil.bean.bangumi.BangumiDetail
import com.yoyiyi.soleil.bean.bangumi.MulBangumiDetail
import com.yoyiyi.soleil.utils.AppUtils
import com.yoyiyi.soleil.utils.EmptyUtils
import com.yoyiyi.soleil.utils.NumberUtils
import com.yoyiyi.soleil.utils.SpanUtils
import com.yoyiyi.soleil.utils.time.TimeUtils
import com.yoyiyi.soleil.widget.CircleImageView
import com.yoyiyi.soleil.widget.flowlayout.FlowLayout
import com.yoyiyi.soleil.widget.flowlayout.TagAdapter
import com.yoyiyi.soleil.widget.flowlayout.TagFlowLayout
import jp.wasabeef.glide.transformations.BlurTransformation

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/6/11 22:02
 * * 描述:番剧详情
 */
class BangumiDetailAdapter(data: List<MulBangumiDetail>) : BaseMultiItemQuickAdapter<MulBangumiDetail, BaseViewHolder>(data) {


    init {
        addItemType(MulBangumiDetail.TYPE_HEAD, R.layout.layout_item_bangumi_detail_info)//头部信息
        addItemType(MulBangumiDetail.TYPE_SEASON, R.layout.layout_item_bangumi_detail_recycler)//分季
        addItemType(MulBangumiDetail.TYPE_EPISODE_HEAD, R.layout.layout_item_bangumi_detail_head)//分集头部
        addItemType(MulBangumiDetail.TYPE_EPISODE_ITEM, R.layout.layout_item_bangumi_detail_recycler)//分集
        addItemType(MulBangumiDetail.TYPE_CONTRACTED, R.layout.layout_bangumi_detail_contracted)//承包
        addItemType(MulBangumiDetail.TYPE_DES, R.layout.layout_item_bangumi_detail_des)//简介
        addItemType(MulBangumiDetail.TYPE_RECOMMEND_HEAD, R.layout.layout_item_bangumi_detail_head)//推荐头部
        addItemType(MulBangumiDetail.TYPE_RECOMMEND_ITEM, R.layout.layout_item_bangumi_detail_recommend)//推荐item
        addItemType(MulBangumiDetail.TYPE_COMMENT_HEAD, R.layout.layout_item_bangumi_detail_head)//评论头部
        addItemType(MulBangumiDetail.TYPE_COMMENT_HOT_ITEM, R.layout.layout_item_bangumi_detail_comment)//热门评论
        addItemType(MulBangumiDetail.TYPE_COMMENT_MORE, R.layout.layout_item_bangumi_detail_more)//更多推荐
        addItemType(MulBangumiDetail.TYPE_COMMENT_NOMAL_ITEM, R.layout.layout_item_bangumi_detail_comment)//评论

    }

    override fun convert(holder: BaseViewHolder, mulBangumiDetail: MulBangumiDetail) {
        when (mulBangumiDetail.itemType) {
            MulBangumiDetail.TYPE_HEAD//头部信息
            -> if (!mulBangumiDetail.isPrepare) {
                holder.setText(R.id.tv_play, "播放:${NumberUtils.format("${mulBangumiDetail.playCount}")}")
                        .setText(R.id.tv_follow, "追番${NumberUtils.format("${mulBangumiDetail.favorites}")}")
                        .setText(R.id.tv_state, if (mulBangumiDetail.isFinish == "0") "连载中" else "已完结")
                Glide.with(mContext)
                        .load(mulBangumiDetail.cover)
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .dontAnimate()
                        .into(holder.getView<ImageView>(R.id.iv_pic))

                Glide.with(mContext)
                        .load(mulBangumiDetail.cover)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(R.drawable.bili_default_image_tv)
                        .bitmapTransform(BlurTransformation(mContext, 26))
                        .dontAnimate()
                        .into(holder.getView<ImageView>(R.id.iv_pic_big))
            }

            MulBangumiDetail.TYPE_SEASON//分季
            -> {
                val recyclerSeason = holder.getView<RecyclerView>(R.id.recycler)
                recyclerSeason.setHasFixedSize(true)
                val layoutManagerSeason = LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)
                recyclerSeason.layoutManager = layoutManagerSeason
                mulBangumiDetail.seasonsTitle?.let {
                    recyclerSeason.adapter = BangumiDetailSeasonAdapter(mulBangumiDetail.seasonsBeanList, it)
                }
            }
            MulBangumiDetail.TYPE_EPISODE_ITEM//选集
            -> {
                val recyclerEpisode = holder.getView<RecyclerView>(R.id.recycler)
                recyclerEpisode.setHasFixedSize(true)
                val layoutManagerEpisode = LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)
                recyclerEpisode.layoutManager = layoutManagerEpisode
                recyclerEpisode.adapter = BangumiDetailEpisodeAdapter(mulBangumiDetail.episodesBeans)
            }
            MulBangumiDetail.TYPE_EPISODE_HEAD//选集头部
            -> {
                holder.setText(R.id.tv_title, "选集")
                if (TextUtils.equals(mulBangumiDetail.isFinish, "1")) {
                    holder.setText(R.id.tv_online, "一共 ${mulBangumiDetail.totalCount} 话")
                } else {
                    holder.setText(R.id.tv_online, "更新至第 ${mulBangumiDetail.totalCount} 话")
                }
            }
            MulBangumiDetail.TYPE_CONTRACTED//承包
            -> {
                holder.setText(R.id.tv_pay_count, "已有${mulBangumiDetail.totalBpCount}人承包了这部番")
                        .setText(R.id.tv_week_count, "等${mulBangumiDetail.weekBpCount}人七日内承包了这部番")
                val ids = intArrayOf(R.id.iv_avatar1, R.id.iv_avatar2, R.id.iv_avatar3, R.id.iv_avatar1, R.id.iv_avatar4)
                val beanList = mulBangumiDetail.listBeanList
                if (EmptyUtils.isNotEmpty(beanList)) {
                    beanList?.forEach {
                        Glide.with(mContext)
                                .load(it.face)
                                .centerCrop()
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .placeholder(R.drawable.bili_default_avatar)
                                .dontAnimate()
                                .into(holder.getView<CircleImageView>(ids[beanList.indexOf(it)]))
                    }
                }
            }
            MulBangumiDetail.TYPE_DES//简介
            -> {
                holder.setText(R.id.tv_des, mulBangumiDetail.evaluate)
                        .setText(R.id.tv_title, "简介")
                        .setText(R.id.tv_online, "更多")
                val tagsLayout = holder.getView<TagFlowLayout>(R.id.tags_layout)
                tagsLayout.adapter = object : TagAdapter<BangumiDetail.TagsBean>(mulBangumiDetail.tagsBeanList) {
                    override fun getView(flowLayout: FlowLayout, i: Int, listBean: BangumiDetail.TagsBean): View {
                        val mTags = LayoutInflater.from(mContext)
                                .inflate(R.layout.layout_hot_tags_item, flowLayout, false) as TextView
                        mTags.text = listBean.tag_name
                       // mTags.setOnClickListener{ TotalSearchActivity.startActivity(mContext, listBean.keyword)}
                        return mTags
                    }
                }
            }
            MulBangumiDetail.TYPE_RECOMMEND_HEAD//推荐头部
            -> holder.setText(R.id.tv_title, "更多推荐")
                    .setText(R.id.tv_online, "换一换")
                    .setVisible(R.id.iv_trans, true)
                    .setVisible(R.id.iv_arrow, false)

            MulBangumiDetail.TYPE_RECOMMEND_ITEM//推荐内容
            -> {
                val recyclerRecommend = holder.getView<RecyclerView>(R.id.recycler)
                recyclerRecommend.setHasFixedSize(true)
                recyclerRecommend.isNestedScrollingEnabled = false
                val layoutManager = GridLayoutManager(mContext, 3)
                recyclerRecommend.layoutManager = layoutManager
                mulBangumiDetail.bangumiRecommendList?.let {
                    recyclerRecommend.adapter = BangumiDetailRecommendAdapter(it.subList(0, 6))
                }

            }

            MulBangumiDetail.TYPE_COMMENT_HEAD//评论头部
            -> holder.setText(R.id.tv_title, SpanUtils()
                    .append("评论  ")
                    .append("第")
                    .append("${mulBangumiDetail.num}")
                    .append("话")
                    .append("(${mulBangumiDetail.account})").setForegroundColor(AppUtils.getColor(R.color.black_alpha_30))
                    .create())
                    .setText(R.id.tv_online, "选集")
            MulBangumiDetail.TYPE_COMMENT_HOT_ITEM//热门评论
            -> {
                mulBangumiDetail.hotsBean?.let {
                    holder.setText(R.id.tv_uname, SpanUtils()
                            .append(it.member.uname)
                            .setForegroundColor(AppUtils.getColor(R.color.gray_20))
                            .appendSpace(10)
                            .appendImage(getIdRes(it.member.level_info.current_level), SpanUtils.ALIGN_CENTER)
                            .create())
                            .setText(R.id.tv_like, "${it.like}")
                            .setText(R.id.tv_floor, "#${it.floor}")
                            .setText(R.id.tv_time, TimeUtils.millis2String((it.ctime * Math.pow(10.0, 3.0)).toLong()))
                            .setText(R.id.tv_message, it.content.message)
                            .setText(R.id.tv_rcount, "共有${it.rcount}条回复 >")
                    Glide.with(mContext)
                            .load(it.member.avatar)
                            .centerCrop()
                            .placeholder(R.drawable.bili_default_avatar)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .dontAnimate()
                            .into(holder.getView<View>(R.id.iv_avatar) as CircleImageView)
                }

            }
            MulBangumiDetail.TYPE_COMMENT_MORE//更多评论
            -> {
            }
            MulBangumiDetail.TYPE_COMMENT_NOMAL_ITEM//普通评论
            -> {
                mulBangumiDetail.repliesBean?.let {

                    holder.setVisible(R.id.tv_rcount, false)
                            .setText(R.id.tv_like, "${it.like}")
                            .setText(R.id.tv_uname, SpanUtils()
                                    .append(it.member.uname)
                                    .setForegroundColor(AppUtils.getColor(R.color.gray_20))
                                    .appendSpace(10)
                                    .appendImage(getIdRes(it.member.level_info.current_level), SpanUtils.ALIGN_CENTER)
                                    .create())
                            .setText(R.id.tv_floor, "#${it.floor}")
                            .setText(R.id.tv_time, com.yoyiyi.soleil.utils.time.TimeUtils.millis2String((it.ctime * Math.pow(10.0, 3.0)).toLong()))
                            .setText(R.id.tv_message, it.content.message)
                    Glide.with(mContext)
                            .load(it.member.avatar)
                            .centerCrop()
                            .placeholder(R.drawable.bili_default_avatar)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .dontAnimate()
                            .into(holder.getView<CircleImageView>(R.id.iv_avatar))


                }
            }
        }
    }

    private fun getIdRes(lv: Int): Int = when (lv) {
        1 -> R.drawable.ic_lv1
        2 -> R.drawable.ic_lv2
        3 -> R.drawable.ic_lv3
        4 -> R.drawable.ic_lv4
        5 -> R.drawable.ic_lv5
        6 -> R.drawable.ic_lv6
        7 -> R.drawable.ic_lv7
        8 -> R.drawable.ic_lv8
        9 -> R.drawable.ic_lv9
        else -> R.drawable.ic_lv0
    }
}
