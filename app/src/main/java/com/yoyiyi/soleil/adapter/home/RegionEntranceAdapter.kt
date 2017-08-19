package com.yoyiyi.soleil.adapter.home

import android.content.Intent
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yoyiyi.soleil.R
import com.yoyiyi.soleil.bean.region.RegionEnter
import com.yoyiyi.soleil.bean.region.RegionTagType
import com.yoyiyi.soleil.module.region.AdActivity
import com.yoyiyi.soleil.module.region.RegionTypeActivity

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/23 23:30
 * * 描述:
 */
class RegionEntranceAdapter(data: List<RegionEnter>?, private val regionTypeList: List<RegionTagType>) : BaseQuickAdapter<RegionEnter, BaseViewHolder>(R.layout.item_home_region_entrance, data) {


    override fun convert(helper: BaseViewHolder, item: RegionEnter) {
        helper.setText(R.id.tv_title, item.title)
                .setImageResource(R.id.iv_icon, item.img)
       helper.itemView.setOnClickListener {
            when (helper.adapterPosition) {
                0//直播
                -> {
                }
                1 -> RegionTypeActivity.startActivity(mContext, regionTypeList[1])
                2 -> RegionTypeActivity.startActivity(mContext, regionTypeList[2])
                3//国创
                -> {
                }
                4 -> RegionTypeActivity.startActivity(mContext, regionTypeList[3])
                5 -> RegionTypeActivity.startActivity(mContext, regionTypeList[4])
                6 -> RegionTypeActivity.startActivity(mContext, regionTypeList[5])
                7 -> RegionTypeActivity.startActivity(mContext, regionTypeList[6])
                8 -> RegionTypeActivity.startActivity(mContext, regionTypeList[7])
                9 -> RegionTypeActivity.startActivity(mContext, regionTypeList[8])
                10 -> RegionTypeActivity.startActivity(mContext, regionTypeList[9])
                11//广告
                -> mContext.startActivity(Intent(mContext, AdActivity::class.java))
                12 -> RegionTypeActivity.startActivity(mContext, regionTypeList[10])
                13 -> RegionTypeActivity.startActivity(mContext, regionTypeList[11])
                14 -> RegionTypeActivity.startActivity(mContext, regionTypeList[12])
                15 ->{

                }
                    //游戏中心
                   // mContext.startActivity(Intent(mContext, GameCenterActivity::class.java))
            }//LiveRegionActivity.startActivity(mContext);
            //RegionTypeActivity.startActivity(mContext, mRegionTypeList.get(0));
            // RegionTypeActivity.startActivity(mContext, mRegionTypeList.get(1), "国创");


        }
    }

}