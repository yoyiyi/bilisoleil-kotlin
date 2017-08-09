package com.yoyiyi.soleil.di.module

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.yoyiyi.soleil.di.qualifier.*
import com.yoyiyi.soleil.network.api.*
import com.yoyiyi.soleil.network.helper.OkHttpHelper
import com.yoyiyi.soleil.network.helper.RetrofitHelper
import com.yoyiyi.soleil.network.support.ApiConstants
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/4/27 16:41
 * * 描述:Api网络模型
 */
@Module
class ApiModule {
    fun createRetrofit(builder: Retrofit.Builder, client: OkHttpClient, url: String): Retrofit
            = builder
            .baseUrl(url)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpHelper.getOkHttpClient()

    @Singleton
    @Provides
    fun provideRetrofitBuilder(): Retrofit.Builder = Retrofit.Builder()

    @Singleton
    @Provides
    fun provideRetrofitHelper(appService: AppService, liveService: LiveService, bangumiService: BangumiService, rankService: RankService, apiService: ApiService, im9Service: Im9Service): RetrofitHelper
            = RetrofitHelper(appService, liveService, bangumiService, rankService, apiService, im9Service)


    @Singleton
    @Provides
    @AppUrl
    fun provideAppRetrofit(builder: Retrofit.Builder, client: OkHttpClient): Retrofit =
            createRetrofit(builder, client, ApiConstants.APP_BASE_URL)


    @Singleton
    @Provides
    fun provideAppService(@AppUrl retrofit: Retrofit): AppService
            = retrofit.create(AppService::class.java)


    @Singleton
    @Provides
    @LiveUrl
    fun provideLiveRetrofit(builder: Retrofit.Builder, client: OkHttpClient): Retrofit
            = createRetrofit(builder, client, ApiConstants.LIVE_BASE_URL)


    @Singleton
    @Provides
    fun provideLiveService(@LiveUrl retrofit: Retrofit): LiveService
            = retrofit.create<LiveService>(LiveService::class.java)


    @Singleton
    @Provides
    @BangumiUrl
    fun provideBangumiRetrofit(builder: Retrofit.Builder, client: OkHttpClient): Retrofit
            = createRetrofit(builder, client, ApiConstants.BANGUMI_BASE_URL)


    @Singleton
    @Provides
    fun provideBangumiService(@BangumiUrl retrofit: Retrofit): BangumiService
            = retrofit.create<BangumiService>(BangumiService::class.java)


    @Singleton
    @Provides
    @RankUrl
    fun provideRankRetrofit(builder: Retrofit.Builder, client: OkHttpClient): Retrofit
            = createRetrofit(builder, client, ApiConstants.RANK_BASE_URL)


    @Singleton
    @Provides
    fun provideRankService(@RankUrl retrofit: Retrofit): RankService
            = retrofit.create<RankService>(RankService::class.java)


    @Singleton
    @Provides
    @ApiUrl
    fun provideApiRetrofit(builder: Retrofit.Builder, client: OkHttpClient): Retrofit
            = createRetrofit(builder, client, ApiConstants.API_BASE_URL)


    @Singleton
    @Provides
    fun provideApiService(@ApiUrl retrofit: Retrofit): ApiService
            = retrofit.create<ApiService>(ApiService::class.java)


    @Singleton
    @Provides
    @Im9Url
    fun provideIm9Retrofit(builder: Retrofit.Builder, client: OkHttpClient): Retrofit
            = createRetrofit(builder, client, ApiConstants.IM9_BASE_URL)


    @Singleton
    @Provides
    fun provideIm9Service(@Im9Url retrofit: Retrofit): Im9Service
            = retrofit.create<Im9Service>(Im9Service::class.java)

}
