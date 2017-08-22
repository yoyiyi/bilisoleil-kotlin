package com.yoyiyi.soleil.di.component

import android.app.Activity
import com.yoyiyi.soleil.di.module.ActivityModule
import com.yoyiyi.soleil.di.scope.ActivityScope
import com.yoyiyi.soleil.module.SplashActivity
import com.yoyiyi.soleil.module.app.up.UpDetailActivity
import com.yoyiyi.soleil.module.app.video.VideoDetailActivity
import com.yoyiyi.soleil.module.app.video.VideoPlayerActivity
import com.yoyiyi.soleil.module.bangumi.BangumiDetailActivity
import com.yoyiyi.soleil.module.bangumi.BangumiIndexActivity
import com.yoyiyi.soleil.module.bangumi.BangumiScheduleActivity
import com.yoyiyi.soleil.module.discover.*
import com.yoyiyi.soleil.module.recommend.AllStationRankActivity
import com.yoyiyi.soleil.module.region.AdActivity
import com.yoyiyi.soleil.module.region.AllRegionRankActivity
import com.yoyiyi.soleil.module.region.RegionTypeActivity
import com.yoyiyi.soleil.module.search.SearchActivity
import com.yoyiyi.soleil.module.search.TotalSearchActivity
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

    fun inject(splashActivity: SplashActivity)

    fun inject(adActivity: AdActivity)

    fun inject(regionTypeActivity: RegionTypeActivity)

    fun inject(allRegionRankActivity: AllRegionRankActivity)

    fun inject(activityCenterActivity: ActivityCenterActivity)

    fun inject(allGameActivity: AllGameActivity)

    fun inject(gameCenterActivity: GameCenterActivity)

    fun inject(interestActivity: InterestActivity)

    fun inject(topicCenterActivity: TopicCenterActivity)

    fun inject(bangumiDetailActivity: BangumiDetailActivity)

    fun inject(bangumiScheduleActivity: BangumiScheduleActivity)

    fun inject(bangumiIndexActivity: BangumiIndexActivity)

    fun inject(upDetailActivity: UpDetailActivity)

    fun inject(videoPlayerActivity: VideoPlayerActivity)

    fun inject(videoDetailActivity: VideoDetailActivity)

    fun inject(totalSearchActivity: TotalSearchActivity)

    fun inject(searchActivity: SearchActivity)

    fun inject(allStationRankActivity: AllStationRankActivity)


}
