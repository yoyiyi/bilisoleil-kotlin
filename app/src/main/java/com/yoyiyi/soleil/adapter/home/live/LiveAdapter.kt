package com.yoyiyi.soleil.adapter.home.live

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.animation.LinearInterpolator
import android.widget.Button
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.youth.banner.Banner
import com.youth.banner.BannerConfig
import com.youth.banner.loader.ImageLoader
import com.yoyiyi.soleil.R
import com.yoyiyi.soleil.bean.live.MulLive
import com.yoyiyi.soleil.bean.live.support.LiveEnter
import com.yoyiyi.soleil.module.app.BrowerActivity
import com.yoyiyi.soleil.utils.AppUtils
import com.yoyiyi.soleil.utils.SpanUtils
import java.util.*

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 *
 * @date 创建时间：2017/6/7 11:38
 * 描述:直播界面的Adapter
 */

class LiveAdapter(data: List<MulLive>) : BaseMultiItemQuickAdapter<MulLive, BaseViewHolder>(data) {

    init {
        addItemType(MulLive.TYPE_BANNER, R.layout.layout_banner)
        addItemType(MulLive.TYPE_ENTRANCE, R.layout.layout_item_home_live_entrance)
        addItemType(MulLive.TYPR_HEADER, R.layout.layout_item_home_live_head)
        addItemType(MulLive.TYPE_RECOMMEND_ITEM, R.layout.common_item_recycler)
        addItemType(MulLive.TYPE_RECOMMEND_BANNER, R.layout.layout_item_home_live_body)
        addItemType(MulLive.TYPE_PARTY_ITEM, R.layout.common_item_recycler)
        addItemType(MulLive.TYPE_FOOTER, R.layout.layout_item_home_live_footer)
    }

    override fun convert(holder: BaseViewHolder, mulLive: MulLive) {
        when (holder.itemViewType) {
            MulLive.TYPE_BANNER -> {
                val banner = holder.getView<Banner>(R.id.banner)
                val bannerBeanList = mulLive.bannerBeanList
                val urls = bannerBeanList?.map({ (img) -> img })
                banner.setIndicatorGravity(BannerConfig.RIGHT)
                        .setImages(urls)
                        .setImageLoader(GlideImageLoader())
                        .start()

                banner?.setOnBannerListener { i ->
                    val bannerBean = bannerBeanList?.get(i)
                    BrowerActivity.startActivity(mContext, bannerBean?.link ?: "", bannerBean?.title ?: "", bannerBean?.img ?: "")
                }

            }
            MulLive.TYPE_ENTRANCE -> {
                val liveEnterList = initEntrance()
                val recyclerView = holder.getView<RecyclerView>(R.id.recycler)
                recyclerView.setHasFixedSize(false)
                recyclerView.isNestedScrollingEnabled = false
                val layoutManager = GridLayoutManager(mContext, 5)
                recyclerView.layoutManager = layoutManager
                recyclerView.adapter = LiveEntranceAdapter(liveEnterList)
            }
            MulLive.TYPE_RECOMMEND_BANNER -> {
                val bannerDataBean = mulLive.bannerData
                Glide.with(mContext)
                        .load(bannerDataBean?.cover?.src)
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(R.drawable.bili_default_image_tv)
                        .dontAnimate()
                        .into(holder.getView<ImageView>(R.id.iv_video_preview))

                holder.setText(R.id.tv_video_live_up, bannerDataBean?.owner?.name ?: "未知")//up
                        .setText(R.id.tv_video_online, "${bannerDataBean?.online}")//在线人数;

                holder.setText(R.id.tv_video_title, SpanUtils()
                        .append("#${bannerDataBean?.area}#")
                        .append("${bannerDataBean?.title}")
                        .setForegroundColor(AppUtils.getColor(R.color.pink_text_color))
                        .create())
            }
            MulLive.TYPR_HEADER -> {
                Glide.with(mContext)
                        .load(mulLive.url)
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .dontAnimate()
                        .into(holder.getView<ImageView>(R.id.iv_icon))
                holder.setText(R.id.tv_title, mulLive.title)
                holder.setText(R.id.tv_online, SpanUtils()
                        .append("当前")
                        .append("${mulLive.count}")
                        .setForegroundColor(AppUtils.getColor(R.color.pink_text_color))
                        .append("个直播")
                        .create())
            }
            MulLive.TYPE_RECOMMEND_ITEM -> {
                val recyclerView = holder.getView<RecyclerView>(R.id.recycler)
                recyclerView.setHasFixedSize(false)
                recyclerView.isNestedScrollingEnabled = false
                val layoutManager = GridLayoutManager(mContext, 2)
                recyclerView.layoutManager = layoutManager
                recyclerView.adapter = LiveRecommendAdapter(mulLive.recommendLives!!)

            }
            MulLive.TYPE_PARTY_ITEM -> {
                val recyclerView = holder.getView<RecyclerView>(R.id.recycler)
                recyclerView.setHasFixedSize(false)
                recyclerView.isNestedScrollingEnabled = false
                val layoutManager = GridLayoutManager(mContext, 2)
                recyclerView.layoutManager = layoutManager
                recyclerView.adapter = LivePartitionAdapter(mulLive.partityLives!!)

            }
            MulLive.TYPE_FOOTER -> {
                val random = Random()
                mulLive.hasMore?.let {
                    if (it) {
                        holder.setVisible(R.id.bt_more_live, true)
                    } else {
                        holder.setVisible(R.id.bt_more_live, false)
                        holder.getView<Button>(R.id.bt_more_live).setOnClickListener({ })
                    }
                }

                holder.setText(R.id.tv_dynamic, "${random.nextInt(200)}条新动态，点击这里刷新")
                holder.getView<ImageView>(R.id.iv_refresh).setOnClickListener({ view ->
                    view.animate()
                            .rotation(360f)
                            .setInterpolator(LinearInterpolator())
                            .setDuration(1000)
                            .start()
                })
            }
        }
    }


    internal class GlideImageLoader : ImageLoader() {
        override fun displayImage(context: Context, path: Any, imageView: ImageView) {
            Glide.with(context)
                    .load(path as String)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .dontAnimate()
                    .into(imageView)
        }
    }

    /**
     * 初始化入口
     */
    private fun initEntrance(): List<LiveEnter> = arrayListOf(
            LiveEnter("关注", R.drawable.live_home_follow_anchor),
            LiveEnter("中心", R.drawable.live_home_live_center),
            LiveEnter("小视频", R.drawable.live_home_clip_video),
            LiveEnter("搜索", R.drawable.live_home_search_room),
            LiveEnter("分类", R.drawable.live_home_all_category))
}
