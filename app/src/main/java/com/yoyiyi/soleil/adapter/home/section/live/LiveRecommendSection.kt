package com.yoyiyi.soleil.adapter.home.section.live

import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.yoyiyi.soleil.R
import com.yoyiyi.soleil.bean.live.LiveRecommend
import com.yoyiyi.soleil.utils.AppUtils
import com.yoyiyi.soleil.utils.NumberUtils
import com.yoyiyi.soleil.utils.SpanUtils
import com.yoyiyi.soleil.utils.ToastUtils
import com.yoyiyi.soleil.widget.section.StatelessSection
import com.yoyiyi.soleil.widget.section.ViewHolder
import java.util.*

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/21 11:57
 * * 描述:首页直播推荐主播section
 */
class LiveRecommendSection(val mHasHead: Boolean,
                           val mHasFooter: Boolean,
                           val mTitle: String,
                           val mUrl: String,
                           val mCount: String,
                           data: List<LiveRecommend.RecommendDataBean.LivesBean>) : StatelessSection<LiveRecommend.RecommendDataBean.LivesBean>(R.layout.layout_item_home_live_head, R.layout.layout_item_home_live_footer, R.layout.layout_item_home_live_body, data) {
    private val mRandom: Random = Random()
    private var mBannerDataBean: LiveRecommend.RecommendDataBean.BannerDataBean? = null

    constructor(hasHead: Boolean, hasFooter: Boolean, title: String, url: String, count: String,
                data: List<LiveRecommend.RecommendDataBean.LivesBean>, bannerDataBean: LiveRecommend.RecommendDataBean.BannerDataBean) : this(hasHead, hasFooter, title, url, count, data) {
        this.mBannerDataBean = bannerDataBean
    }


    override fun onBindHeaderViewHolder(holder: ViewHolder) {
        if (mHasHead) {
            holder.setVisible(R.id.cl_type_root, true)
            Glide.with(mContext)
                    .load(mUrl)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .dontAnimate()
                    .into(holder.getView<View>(R.id.iv_icon) as ImageView)
            holder.setText(R.id.tv_title, mTitle)
            val stringBuilder = SpannableStringBuilder("当前" + mCount + "个直播")
            val foregroundColorSpan = ForegroundColorSpan(
                    mContext.resources.getColor(R.color.pink_text_color))
            stringBuilder.setSpan(foregroundColorSpan, 2,
                    stringBuilder.length - 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            holder.setText(R.id.tv_online, stringBuilder)

            mBannerDataBean?.let {
                holder.setVisible(R.id.cl_video_root, true)
                Glide.with(mContext)
                        .load(mBannerDataBean?.cover?.src)
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .dontAnimate()
                        .into(holder.getView<View>(R.id.iv_video_preview) as ImageView)
                holder.setText(R.id.tv_video_live_up, mBannerDataBean?.owner?.name)//up
                        .setText(R.id.tv_video_online, "${mBannerDataBean?.online}")//在线人数;
                holder.setText(R.id.tv_video_title, SpanUtils()
                        .append("#${mBannerDataBean?.area}#")
                        .setForegroundColor(AppUtils.getColor(R.color.pink_text_color))
                        .append("${mBannerDataBean?.title}")
                        .create())
            } ?: holder.setVisible(R.id.card_view, false)
        } else {
            val params = RelativeLayout.LayoutParams(0, 0)
            holder.itemView.layoutParams = params
        }

    }

    override fun convert(holder: ViewHolder, livesBean: LiveRecommend.RecommendDataBean.LivesBean, position: Int) {
        Glide.with(mContext)
                .load(livesBean.cover?.src)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.bili_default_image_tv)
                .dontAnimate()
                .into(holder.getView<View>(R.id.iv_video_preview) as ImageView)
        holder.setText(R.id.tv_video_live_up, livesBean.owner!!.name)//up
                .setText(R.id.tv_video_online, NumberUtils.format(livesBean.online.toString() + ""))//在线人数;
        holder.setText(R.id.tv_video_title, SpanUtils()
                .append("#" + livesBean.area + "#")
                .setForegroundColor(AppUtils.getColor(R.color.pink_text_color))
                .append("${livesBean?.title}").create())
        if (position % 2 == 0) {
            setMargins(holder.itemView, AppUtils.getDimension(R.dimen.dp10).toInt(),
                    AppUtils.getDimension(R.dimen.dp5).toInt(),
                    AppUtils.getDimension(R.dimen.dp5).toInt(),
                    AppUtils.getDimension(R.dimen.dp5).toInt())
        } else {
            setMargins(holder.itemView, AppUtils.getDimension(R.dimen.dp5).toInt(),
                    AppUtils.getDimension(R.dimen.dp5).toInt(),
                    AppUtils.getDimension(R.dimen.dp10).toInt(),
                    AppUtils.getDimension(R.dimen.dp5).toInt())
        }
    }

    override fun onBindFooterViewHolder(holder: ViewHolder) {
        if (mHasFooter) {
            holder.setVisible(R.id.bt_more, true)
                    .setText(R.id.tv_dynamic, "${mRandom.nextInt(200)}条新动态，点击这里刷新")
            holder.getView<View>(R.id.iv_refresh).setOnClickListener({ view ->
                view.animate()
                        .rotation(360f)
                        .setInterpolator(LinearInterpolator())
                        .setDuration(1000).start()
            })
            holder.getView<View>(R.id.iv_refresh).setOnClickListener({ view ->
                view.animate()
                        .rotation(360f)
                        .setInterpolator(LinearInterpolator())
                        .setDuration(1000).start()
            })
        } else {
            val params = LinearLayout.LayoutParams(0, 0)
            holder.itemView.layoutParams = params
        }

    }
}
