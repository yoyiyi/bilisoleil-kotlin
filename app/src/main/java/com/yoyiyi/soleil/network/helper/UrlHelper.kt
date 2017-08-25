package com.yoyiyi.soleil.network.helper

import android.util.Log

import com.yoyiyi.soleil.bean.user.UserDetail


/**
 * Created by hcc on 16/8/4 21:18
 * 100332338@qq.com
 *
 *
 * 根据B站返回的数据 个人参数由于返回限制
 * 需要进行特殊处理，如视频封面
 * 用户头像等url 进行url改装
 * 才能进行展示.
 */
object UrlHelper {
    private val HDSLB_HOST = "http://i2.hdslb.com"

    private fun isVideoUrl(url: String): Boolean {
        return url.contains("bilibili.com/video/av")
    }

    fun getAVfromVideoUrl(url: String): Int {
        if (!isVideoUrl(url)) {
            return -1
        }
        var av = url
        av = av.substring(av.indexOf("bilibili.com/video/av") + "bilibili.com/video/av".length)
        Log.i("test", av)
        av = av.substring(0, av.indexOf("/"))
        Log.i("test", av)
        return Integer.parseInt(av)
    }

    fun getFaceUrl(info: UserDetail): String {
        if (info.card.face.contains(".hdslb.com")) {
            return info.card.face
        }
        var face = HDSLB_HOST + info.card.face
        if (face.contains("{SIZE}")) {
            face = face.replace("{SIZE}", "")
        }
        return face
    }

    fun getFaceUrlByUrl(url: String): String {
        if (url.contains("/52_52")) {
            return url.replace("/52_52", "")
        }
        return url
    }

    fun getClearVideoPreviewUrl(url: String): String {
        var url = url
        if (!url.contains("/320_180")) {
            return url
        }
        url = url.replace("/320_180", "")
        return url
    }
}
