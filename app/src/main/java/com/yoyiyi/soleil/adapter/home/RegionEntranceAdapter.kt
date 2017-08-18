package com.yoyiyi.soleil.adapter.home

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yoyiyi.soleil.R
import com.yoyiyi.soleil.bean.region.RegionEnter
import com.yoyiyi.soleil.bean.region.RegionTagType

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
      /*  helper.itemView.setOnClickListener {
            when (helper.adapterPosition) {
            *//* "直播",
                        "番剧",
                        "动画",
                        "国创",
                        "音乐",
                        "舞蹈",
                        "游戏",
                        "科技",
                        "生活",
                        "鬼畜",
                        "时尚",
                        "广告",
                        "娱乐",
                        "电影",
                        "电视剧",
                        "游戏中心"*//*
                0//直播
                -> {
                }
                1 -> RegionTypeActivity.startActivity(mContext, mRegionTypeList[1])
                2 -> RegionTypeActivity.startActivity(mContext, mRegionTypeList[2])
                3//国创
                -> {
                }
                4 -> RegionTypeActivity.startActivity(mContext, mRegionTypeList[3])
                5 -> RegionTypeActivity.startActivity(mContext, mRegionTypeList[4])
                6 -> RegionTypeActivity.startActivity(mContext, mRegionTypeList[5])
                7 -> RegionTypeActivity.startActivity(mContext, mRegionTypeList[6])
                8 -> RegionTypeActivity.startActivity(mContext, mRegionTypeList[7])
                9 -> RegionTypeActivity.startActivity(mContext, mRegionTypeList[8])
                10 -> RegionTypeActivity.startActivity(mContext, mRegionTypeList[9])
                11//广告
                -> mContext.startActivity(Intent(mContext, AdActivity::class.java))
                12 -> RegionTypeActivity.startActivity(mContext, mRegionTypeList[10])
                13 -> RegionTypeActivity.startActivity(mContext, mRegionTypeList[11])
                14 -> RegionTypeActivity.startActivity(mContext, mRegionTypeList[12])
                15 ->
                    //游戏中心
                    mContext.startActivity(Intent(mContext, GameCenterActivity::class.java))
            }//LiveRegionActivity.startActivity(mContext);
            //RegionTypeActivity.startActivity(mContext, mRegionTypeList.get(0));
            // RegionTypeActivity.startActivity(mContext, mRegionTypeList.get(1), "国创");


        }*/
    }

}