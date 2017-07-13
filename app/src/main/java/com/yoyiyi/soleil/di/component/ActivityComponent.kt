package com.yoyiyi.soleil.di.component

import android.app.Activity
import com.yoyiyi.soleil.di.module.ActivityModule
import com.yoyiyi.soleil.di.scope.ActivityScope
import dagger.Component

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/4/27 19:39
 * * 描述:ActivityComponent
 */
@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    fun getActivity(): Activity

    /* fun inject(splashActivity: SplashActivity)

     fun inject(totalSearchActivity: TotalSearchActivity)

     fun inject(regionTypeActivity: RegionTypeActivity)

     fun inject(topicCenterActivity: TopicCenterActivity)

     fun inject(activityCenterActivity: ActivityCenterActivity)

     fun inject(gameCenterActivity: GameCenterActivity)

     fun inject(allGameActivity: AllGameActivity)

     fun inject(bangumiScheduleActivity: BangumiScheduleActivity)

     fun inject(bangumiIndexActivity: BangumiIndexActivity)

     fun inject(adActivity: AdActivity)

     fun inject(bangumiDetailActivity: BangumiDetailActivity)

     fun inject(videoDetailActivity: VideoDetailActivity)

     fun inject(upDetailActivity: UpDetailActivity)

     fun inject(searchActivity: SearchActivity)

     fun inject(videoPlayerActivity: VideoPlayerActivity)*/

}
