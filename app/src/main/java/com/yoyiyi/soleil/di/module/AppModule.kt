package com.yoyiyi.soleil.di.module

import android.content.Context

import dagger.Module
import dagger.Provides

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/4/27 14:41
 * * 描述:App模型
 */
@Module
class AppModule(private val mContext: Context) {

    @Provides
    fun provideContext(): Context {
        return mContext
    }
}
