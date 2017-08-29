package com.yoyiyi.soleil.adapter.app.video

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yoyiyi.soleil.R
import com.yoyiyi.soleil.bean.app.video.MulSummary
import com.yoyiyi.soleil.module.app.up.UpDetailActivity
import com.yoyiyi.soleil.utils.NumberUtils
import com.yoyiyi.soleil.utils.time.TimeUtils
import com.yoyiyi.soleil.widget.flowlayout.FlowLayout
import com.yoyiyi.soleil.widget.flowlayout.TagAdapter
import com.yoyiyi.soleil.widget.flowlayout.TagFlowLayout
import java.util.*

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/6/15 10:07
 * * 描述:
 */

class SummaryAdapter(data: List<MulSummary>) : BaseMultiItemQuickAdapter<MulSummary, BaseViewHolder>(data) {

    init {
        addItemType(MulSummary.TYPE_DES, R.layout.layout_item_video_detail_summary_des)
        addItemType(MulSummary.TYPE_OWNER, R.layout.layout_item_video_detail_summary_owner)
        addItemType(MulSummary.TYPE_RELATE, R.layout.layout_item_video_detail_summary_relate)
        addItemType(MulSummary.TYPE_RELATE_HEAD, R.layout.layout_item_video_detail_summary_relate_head)
    }

    override fun convert(holder: BaseViewHolder, mulSummary: MulSummary) {
        when (mulSummary.itemType) {
            MulSummary.TYPE_DES ->

                mulSummary.state?.let {

                    holder.setText(R.id.tv_title, mulSummary.title)
                            .setText(R.id.tv_video_play_num, NumberUtils.format("${it.view}"))
                            .setText(R.id.tv_video_danmaku, NumberUtils.format("${it.danmaku}"))
                            .setText(R.id.tv_share, NumberUtils.format("${it.share}"))
                            .setText(R.id.tv_coin, NumberUtils.format("${it.coin}"))
                            .setText(R.id.tv_favourite, NumberUtils.format("${it.favorite}"))
                            .setText(R.id.tv_down, "缓存")
                            .setText(R.id.tv_des, mulSummary.desc)

                }


            MulSummary.TYPE_OWNER -> {
                mulSummary.owner?.let {
                    Glide.with(mContext)
                            .load(it.face)
                            .centerCrop()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .dontAnimate()
                            .into(holder.getView<ImageView>(R.id.iv_avatar))
                    holder.getView<View>(R.id.iv_avatar).setOnClickListener { mContext.startActivity(Intent(mContext, UpDetailActivity::class.java)) }

                    val date = TimeUtils.millis2String((mulSummary.ctime * Math.pow(10.0, 3.0)).toLong())
                    val split = date.split("-".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

                    holder.setText(R.id.tv_name, it.name)
                            .setText(R.id.tv_time, split[0] + "年" + split[1] + "月" + split[2].split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0] + "日" + "投递")

                    val tagsLayout = holder.getView<TagFlowLayout>(R.id.tags_layout)

                    val tag = ArrayList<String>()

                    mulSummary.tags?.forEach { tagBean -> tag.add(tagBean.tag_name) }

                    tagsLayout.adapter = object : TagAdapter<String>(tag) {
                        override fun getView(flowLayout: FlowLayout, i: Int, listBean: String): View {
                            val mTags = LayoutInflater.from(mContext).inflate(R.layout.layout_hot_tags_item, flowLayout, false) as TextView
                            mTags.text = listBean
                           //   mTags.setOnClickListener{ TotalSearchActivity.startActivity(mContext, listBean.key)}
                            return mTags
                        }
                    }
                }
            }

            MulSummary.TYPE_RELATE_HEAD -> {
            }
            MulSummary.TYPE_RELATE -> {
                mulSummary.relates?.let {
                    Glide.with(mContext)
                            .load<Any>(it.pic)
                            .centerCrop()
                            .placeholder(R.drawable.bili_default_image_tv)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .dontAnimate()
                            .into(holder.getView<View>(R.id.iv_video_preview) as ImageView)
                    holder.setText(R.id.tv_video_title, it.title)
                            .setText(R.id.tv_video_up, it.owner.name)
                            .setText(R.id.tv_video_play, NumberUtils.format("${it.stat.view}"))
                            .setText(R.id.tv_video_danmaku, NumberUtils.format("${it.stat.danmaku}"))

                }
            }
        }
    }
}
