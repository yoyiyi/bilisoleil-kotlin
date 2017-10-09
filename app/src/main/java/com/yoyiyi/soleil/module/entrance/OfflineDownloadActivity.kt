package com.yoyiyi.soleil.module.entrance

import android.annotation.SuppressLint
import android.view.Menu
import android.view.MenuItem
import com.yoyiyi.soleil.R
import com.yoyiyi.soleil.base.BaseActivity
import com.yoyiyi.soleil.ext.toast
import com.yoyiyi.soleil.utils.sdcard.SDCardUtils
import kotlinx.android.synthetic.main.activity_offline_download.*
import kotlinx.android.synthetic.main.common_toolbar.*

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/6/30 22:41
 * * 描述:离线缓存
 */
class OfflineDownloadActivity : BaseActivity() {


     override fun getLayoutId(): Int {
        return R.layout.activity_offline_download
    }

    @SuppressLint("SetTextI18n")
    override fun initWidget() {
        val allSpace = SDCardUtils.getAllSpace()
        val freeSpace = SDCardUtils.getFreeSpace()
        val progress = countProgress(allSpace.replace("GB".toRegex(), "")
                .replace("MB".toRegex(), "")
                .replace("KB".toRegex(), "")
                .toFloat(),
        freeSpace.replace("GB".toRegex(), "")
                .replace("MB".toRegex(), "")
                .replace("KB".toRegex(), "")
                .toFloat())

        progress_bar.progress = progress
        tv_cache_size.text = "主存储:$allSpace/可用:$freeSpace"
    }

    private fun countProgress(allSpace: Float, freeSpace: Float): Int {
        //取整相减
        val size = (Math.floor(allSpace.toDouble()) - Math.floor(freeSpace.toDouble())).toInt()
        val v = size / Math.floor(allSpace.toDouble()) * 100
        return Math.floor(v).toInt()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_off, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_more) {
            "离线设置".toast()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun initToolbar() {
        super.initToolbar()
        toolbar.title = "离线缓存"
    }
}
