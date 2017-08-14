package com.yoyiyi.soleil.di.module

import android.app.Activity
import android.support.v4.app.Fragment

import com.yoyiyi.soleil.di.scope.FragmentScope

import dagger.Module
import dagger.Provides

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/4/27 16:41
 * * 描述:Fragment模型
 */
@Module
 class FragmentModule(val fragment: Fragment) {

    @Provides
    @FragmentScope
    fun provideActivity(): Activity = fragment.activity

}
