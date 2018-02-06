package com.yoyiyi.soleil.ext

import android.widget.Toast
import com.yoyiyi.soleil.App

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * @date 创建时间：2017/10/9 12:05
 * 描述:弹吐司
 */
fun String.toast(): Unit {
    Toast.makeText(App.instance, this, Toast.LENGTH_LONG).show()
}