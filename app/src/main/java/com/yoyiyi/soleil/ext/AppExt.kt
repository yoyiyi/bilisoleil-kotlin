package com.yoyiyi.soleil.ext


import android.content.Context
import android.support.v4.app.Fragment
import android.view.View
import com.yoyiyi.soleil.BiliSoleilApplication

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * @date 创建时间：2017/8/21 10:30
 * 描述:
 */

fun Context.getComponent() = BiliSoleilApplication.instance.appComponent


/**
 * 隐藏View
 * @param views 视图
 */
fun Context.gone(vararg views: View) {
    if (views.isNotEmpty()) {
        for (view in views) {
            view.visibility = View.GONE
        }
    }
}

/**
 * 显示View
 * @param views 视图
 */
fun Context.visible(vararg views: View) {
    if (views.isNotEmpty()) {
        for (view in views) {
            view.visibility = View.VISIBLE
        }
    }
}


/**
 * 隐藏View
 * @param views 视图
 */
fun Fragment.gone(vararg views: View) {
    if (views.isNotEmpty()) {
        for (view in views) {
            view.visibility = View.GONE
        }
    }
}

/**
 * 显示View
 * @param views 视图
 */
fun Fragment.visible(vararg views: View) {
    if (views.isNotEmpty()) {
        for (view in views) {
            view.visibility = View.VISIBLE
        }
    }
}