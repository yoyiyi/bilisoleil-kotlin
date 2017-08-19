package com.yoyiyi.soleil.adapter.region.sectiton

import com.youth.banner.Banner
import com.yoyiyi.soleil.R
import com.yoyiyi.soleil.bean.region.RegionRecommend
import com.yoyiyi.soleil.ext.startAnim
import com.yoyiyi.soleil.module.app.BrowerActivity
import com.yoyiyi.soleil.widget.section.StatelessSection
import com.yoyiyi.soleil.widget.section.ViewHolder

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/21 11:57
 * * 描述:分区推荐主播section
 */
class RegionRecommendBannerSection(private val list: List<RegionRecommend.BannerBean.TopBean>) : StatelessSection<Nothing>(R.layout.layout_banner, R.layout.layout_empty) {

    override fun onBindHeaderViewHolder(holder: ViewHolder) {
        val banner = holder.getView<Banner>(R.id.banner)
        val urls = list.map { it.image }
        banner.startAnim(urls)
        banner.setOnBannerListener { i ->
            val bannerBean = list[i]
            BrowerActivity.startActivity(mContext, bannerBean.uri, bannerBean.title, bannerBean.image)
        }
    }



}
