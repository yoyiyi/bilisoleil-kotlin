package com.yoyiyi.soleil.adapter.region.sectiton

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/15 16:28
 * * 描述:分区入口Section
 */

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.yoyiyi.soleil.R
import com.yoyiyi.soleil.adapter.region.RegionRecommendEntranceAdapter
import com.yoyiyi.soleil.bean.region.RegionEnter
import com.yoyiyi.soleil.widget.section.StatelessSection
import com.yoyiyi.soleil.widget.section.ViewHolder
import java.util.*

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/26 21:59
 * * 描述:
 */
class RegionRecommendEntranceSection(private val tid: Int) : StatelessSection<Nothing>(R.layout.layout_item_region_recommend_entrance, R.layout.layout_empty) {

    private var mList: List<RegionEnter> = ArrayList()

    init {
        init()
    }

    private fun init() {
        when (tid) {
            13//番剧
            -> mList = arrayListOf(RegionEnter("连载动画", R.mipmap.ic_category_t33),
                    RegionEnter("完结动画", R.mipmap.ic_category_t32),
                    RegionEnter("国产动画", R.mipmap.ic_category_t153),
                    RegionEnter("资讯", R.mipmap.ic_category_t51),
                    RegionEnter("官方延伸", R.mipmap.ic_category_t152))
            1//动画
            -> mList = arrayListOf(RegionEnter("MAD·AMV", R.mipmap.ic_category_t24),
                    RegionEnter("MMD·3D", R.mipmap.ic_category_t25),
                    RegionEnter("短片·手书·配音", R.mipmap.ic_category_t47),
                    RegionEnter("综合", R.mipmap.ic_category_t27))
            3//音乐
            -> mList = arrayListOf(RegionEnter("翻唱", R.mipmap.ic_category_t31),
                    RegionEnter("VOCALOID·UTAU", R.mipmap.ic_category_t30),
                    RegionEnter("演奏", R.mipmap.ic_category_t59),
                    RegionEnter("OP/ED/OST", R.mipmap.ic_category_t54),
                    RegionEnter("原创音乐", R.mipmap.ic_category_t28),
                    RegionEnter("三次元音乐", R.mipmap.ic_category_t29),
                    RegionEnter("音乐选集", R.mipmap.ic_category_t130))
            129//舞蹈
            -> mList = arrayListOf(RegionEnter("宅舞", R.mipmap.ic_category_t20),
                    RegionEnter("三次元舞蹈", R.mipmap.ic_category_t154),
                    RegionEnter("舞蹈教程", R.mipmap.ic_category_t156))
            4//游戏
            -> mList = arrayListOf(RegionEnter("单机联机", R.mipmap.ic_category_t17),
                    RegionEnter("网游·电竞", R.mipmap.ic_category_t65),
                    RegionEnter("音游", R.mipmap.ic_category_t136),
                    RegionEnter("MUGEN", R.mipmap.ic_category_t19),
                    RegionEnter("GMV", R.mipmap.ic_category_t121),
                    RegionEnter("游戏中心", R.mipmap.ic_category_game_center2))
            36//科技
            -> mList = arrayListOf(RegionEnter("纪录片", R.mipmap.ic_category_t37),
                    RegionEnter("趣味科普人文", R.mipmap.ic_category_t124),
                    RegionEnter("野生技术协会", R.mipmap.ic_category_t122),
                    RegionEnter("演讲·公开课", R.mipmap.ic_category_t39),
                    RegionEnter("星海", R.mipmap.ic_category_t96),
                    RegionEnter("数码", R.mipmap.ic_category_t95),
                    RegionEnter("机械", R.mipmap.ic_category_t98))
            160//生活
            -> mList = arrayListOf(RegionEnter("搞笑", R.mipmap.ic_category_t138),
                    RegionEnter("日常", R.mipmap.ic_category_t21),
                    RegionEnter("美食圈", R.mipmap.ic_category_t76),
                    RegionEnter("动物圈", R.mipmap.ic_category_t75),
                    RegionEnter("手工", R.mipmap.ic_category_t161),
                    RegionEnter("绘画", R.mipmap.ic_category_t162),
                    RegionEnter("运动", R.mipmap.ic_category_t163))
            119//鬼畜
            -> mList = arrayListOf(RegionEnter("鬼畜调教", R.mipmap.ic_category_t22),
                    RegionEnter("音MAD", R.mipmap.ic_category_t26),
                    RegionEnter("人力VOCALOID", R.mipmap.ic_category_t126),
                    RegionEnter("教程演示", R.mipmap.ic_category_t127))
            155//时尚
            -> mList = arrayListOf(RegionEnter("美妆", R.mipmap.ic_category_t157),
                    RegionEnter("服饰", R.mipmap.ic_category_t158),
                    RegionEnter("资讯", R.mipmap.ic_category_t159),
                    RegionEnter("健身", R.mipmap.ic_category_t164))
            5//娱乐
            -> mList = arrayListOf(RegionEnter("综艺", R.mipmap.ic_category_t71),
                    RegionEnter("明星", R.mipmap.ic_category_t137),
                    RegionEnter("KOREA相关", R.mipmap.ic_category_t131))
            23//电影
            -> mList = arrayListOf(RegionEnter("电影相关", R.mipmap.ic_category_t82),
                    RegionEnter("短片", R.mipmap.ic_category_t85),
                    RegionEnter("欧美电影", R.mipmap.ic_category_t145),
                    RegionEnter("日本电影", R.mipmap.ic_category_t146),
                    RegionEnter("国产电影", R.mipmap.ic_category_t147),
                    RegionEnter("其他国家", R.mipmap.ic_category_t83))
            11//电视剧
            -> mList = arrayListOf(RegionEnter("连载剧集", R.mipmap.ic_category_t15),
                    RegionEnter("完结剧集", R.mipmap.ic_category_t34),
                    RegionEnter("特辑·布袋戏", R.mipmap.ic_category_t86),
                    RegionEnter("电视剧相关", R.mipmap.ic_category_t128))
        }

    }

    override fun onBindHeaderViewHolder(holder: ViewHolder) {
        val recyclerView = holder.getView<RecyclerView>(R.id.recycler)
        recyclerView.setHasFixedSize(false)
        recyclerView.isNestedScrollingEnabled = false
        val layoutManager = GridLayoutManager(mContext, if (mList.size >= 4) 4 else mList.size)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = RegionRecommendEntranceAdapter(mList)
    }
}

