package com.yoyiyi.soleil

import android.app.Application
import com.yoyiyi.soleil.utils.AppUtils
import com.yoyiyi.soleil.utils.CrashHandler
import com.yoyiyi.soleil.utils.LogUtils

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * @date 创建时间：2017/7/13 11:23
 * 描述:APP
 * #                                                   #
 * #                       _oo0oo_                     #
 * #                      o8888888o                    #
 * #                      88" . "88                    #
 * #                      (| -_- |)                    #
 * #                      0\  =  /0                    #
 * #                    ___/`---'\___                  #
 * #                  .' \\|     |# '.                 #
 * #                 / \\|||  :  |||# \                #
 * #                / _||||| -:- |||||- \              #
 * #               |   | \\\  -  #/ |   |              #
 * #               | \_|  ''\---/''  |_/ |             #
 * #               \  .-\__  '-'  ___/-. /             #
 * #             ___'. .'  /--.--\  `. .'___           #
 * #          ."" '<  `.___\_<|>_/___.' >' "".         #
 * #         | | :  `- \`.;`\ _ /`;.`/ - ` : | |       #
 * #         \  \ `_.   \_ __\ /__ _/   .-` /  /       #
 * #     =====`-.____`.___ \_____/___.-`___.-'=====    #
 * #                       `=---='                     #
 * #     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~   #
 * #                                                   #
 * #               佛祖保佑         永无BUG             #
 * #                                                   #
 */
class BiliSoleilApplication : Application() {

    companion object {
        lateinit var instance: BiliSoleilApplication
            private set
    }


    override fun onCreate() {
        super.onCreate()
        AppUtils.init(this)
        instance = this
    }

    private fun exitApp() {

    }


    /**
     * 初始化崩溃日志
     */
    private fun initCrashHandler() {
        CrashHandler.getInstance().init(this)
    }

    /**
     * 初始化log
     */
    private fun initLog() {
        LogUtils.init(this)
    }

}
