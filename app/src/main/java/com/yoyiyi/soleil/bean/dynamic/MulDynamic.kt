package com.yoyiyi.soleil.bean.dynamic

import com.chad.library.adapter.base.entity.AbstractExpandableItem
import com.chad.library.adapter.base.entity.MultiItemEntity

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/6/13 18:40
 * * 描述:
 */

class MulDynamic(var itemTypez: Int = 0,
                 var group: Dynamic.ItemBean? = null,
                 var flag: Boolean = false,
                 var recent: Dynamic.ItemBean.RecentBean? = null,
                 var lv: Int = 0) : AbstractExpandableItem<MulDynamic>(), MultiItemEntity {


    var child: List<MulDynamic>? = null
        set(value) {
            field?.let {
                if (it.isNotEmpty()) {
                    it.forEach { item -> addSubItem(item) }
                }
            }
        }


    /* fun setChild(child: List<MulDynamic>): MulDynamic {
         this.child = child
         addSubItem()
         return this
     }


     fun addSubItem(child: List<MulDynamic>): MulDynamic {
         this.child = child
         if (EmptyUtils.isNotEmpty(child)) {
             for (mulDynamic in child)
                 addSubItem(mulDynamic)
         }
         return this
     }*/

    override fun getItemType(): Int = itemTypez


    override fun getLevel(): Int = lv


    companion object {
        val TYPE_LV0 = 0
        val TYPE_LV1 = 1
    }
}
