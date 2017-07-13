package com.yoyiyi.soleil.di.component

import android.content.Context
import com.yoyiyi.soleil.di.module.ApiModule
import com.yoyiyi.soleil.di.module.AppModule
import com.yoyiyi.soleil.network.helper.RetrofitHelper
import dagger.Component
import javax.inject.Singleton

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 *
 * @date 创建时间：2017/4/27 16:39
 *  描述:AppComponent
 */
@Singleton
@Component(modules = arrayOf(AppModule::class, ApiModule::class))
interface AppComponent {
    fun getContext(): Context

    fun getRetrofitHelper(): RetrofitHelper

}
