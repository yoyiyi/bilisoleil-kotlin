package com.yoyiyi.soleil.di.component

import com.yoyiyi.soleil.di.module.FragmentModule
import com.yoyiyi.soleil.di.scope.FragmentScope
import dagger.Component

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/4/27 19:30
 * * 描述:FragmentComponent
 */
@FragmentScope
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(FragmentModule::class))
interface FragmentComponent {

  /*  val activity: Activity

    fun inject(liveFragment: LiveFragment)

    fun inject(recommendFragment: RecommendFragment)

    fun inject(regionFragment: RegionFragment)

    fun inject(chaseBangumiFragment: ChaseBangumiFragment)

    fun inject(discoverFragment: DiscoverFragment)

    fun inject(archiveFragment: ArchiveFragment)

    fun inject(regionTypeRecommendFragment: RegionTypeRecommendFragment)

    fun inject(regionTypeFragment: RegionTypeFragment)

    fun inject(allRegionRankFragment: AllRegionRankFragment)

    fun inject(allStationRankFragment: AllStationRankFragment)

    fun inject(interestFragment: InterestFragment)

    fun inject(dynamicFragment: DynamicFragment)

    fun inject(summaryFragment: SummaryFragment)

    fun inject(commentFragment: CommentFragment)

    fun inject(archiveFragment: com.yoyiyi.soleil.module.app.up.ArchiveFragment)

    fun inject(submitedVideoFragment: SubmitedVideoFragment)

    fun inject(favouriteFragment: FavouriteFragment)

    fun inject(movieFragment: MovieFragment)

    fun inject(upFragment: UpFragment)

    fun inject(seasonFragment: SeasonFragment)*/


}
