package com.yoyiyi.soleil

import android.app.Activity
import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import com.facebook.stetho.Stetho
import com.yoyiyi.soleil.di.component.AppComponent
import com.yoyiyi.soleil.di.component.DaggerAppComponent
import com.yoyiyi.soleil.di.module.ApiModule
import com.yoyiyi.soleil.di.module.AppModule
import com.yoyiyi.soleil.utils.AppUtils
import com.yoyiyi.soleil.utils.CrashHandler
import com.yoyiyi.soleil.utils.LogUtils
import com.yoyiyi.soleil.utils.PrefsUtils
import com.yoyiyi.soleil.utils.net.NetworkUtils
import java.util.*

@Suppress("DEPRECATION")
/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/4/28 11:27
 * * 描述:APP
 * * #                                                   #
 * * #                       _oo0oo_                     #
 * * #                      o8888888o                    #
 * * #                      88" . "88                    #
 * * #                      (| -_- |)                    #
 * * #                      0\  =  /0                    #
 * * #                    ___/`---'\___                  #
 * * #                  .' \\|     |# '.                 #
 * * #                 / \\|||  :  |||# \                #
 * * #                / _||||| -:- |||||- \              #
 * * #               |   | \\\  -  #/ |   |              #
 * * #               | \_|  ''\---/''  |_/ |             #
 * * #               \  .-\__  '-'  ___/-. /             #
 * * #             ___'. .'  /--.--\  `. .'___           #
 * * #          ."" '<  `.___\_<|>_/___.' >' "".         #
 * * #         | | :  `- \`.;`\ _ /`;.`/ - ` : | |       #
 * * #         \  \ `_.   \_ __\ /__ _/   .-` /  /       #
 * * #     =====`-.____`.___ \_____/___.-`___.-'=====    #
 * * #                       `=---='                     #
 * * #     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~   #
 * * #                                                   #
 * * #               佛祖保佑         永无BUG             #
 * * #                                                   #
 */
class App : Application() {

    private var allActivities: HashSet<Activity>? = null
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
                .apiModule(ApiModule())
                .appModule(AppModule(this))
                .build()
    }

    companion object {
        lateinit var instance: App

    }


    override fun onCreate() {
        super.onCreate()
        AppUtils.init(this)
        instance = this
        initNetwork()
        initStetho()
        initCrashHandler()
        initLog()
        initPrefs()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }


    /**
     * 增加Activity
     * @param act act
     */
    fun addActivity(act: Activity) {
        if (allActivities == null) {
            allActivities = HashSet<Activity>()
        } else {
            allActivities?.add(act)
        }
    }

    /**
     * 移除Activity
     * @param act act
     */
    fun removeActivity(act: Activity) {
        allActivities?.remove(act)
    }

    /**
     * 退出应用
     */
    @Synchronized fun exitApp() {
        allActivities?.let {
            for (act in it) {
                act.finish()
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid())
        System.exit(0)
    }

    /**
     * 初始化sp
     */
    private fun initPrefs() {
        PrefsUtils.init(this, packageName + "_preference", Context.MODE_MULTI_PROCESS)
    }

    /**
     * 初始化调试
     */
    private fun initStetho() {
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build())
    }

    /**
     * 开启网络监听
     */
    private fun initNetwork() {
        NetworkUtils.startNetService(this)

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
