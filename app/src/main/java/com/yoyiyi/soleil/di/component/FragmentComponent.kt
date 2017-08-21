package com.yoyiyi.soleil.di.component

import android.app.Activity
import com.yoyiyi.soleil.di.module.FragmentModule
import com.yoyiyi.soleil.di.scope.FragmentScope
import com.yoyiyi.soleil.module.app.up.ArchiveFragment
import com.yoyiyi.soleil.module.app.up.FavouriteFragment
import com.yoyiyi.soleil.module.app.up.SubmitedVideoFragment
import com.yoyiyi.soleil.module.app.video.CommentFragment
import com.yoyiyi.soleil.module.app.video.SummaryFragment
import com.yoyiyi.soleil.module.discover.InterestFragment
import com.yoyiyi.soleil.module.home.*
import com.yoyiyi.soleil.module.recommend.AllStationRankFragment
import com.yoyiyi.soleil.module.region.AllRegionRankFragment
import com.yoyiyi.soleil.module.region.RegionTypeFragment
import com.yoyiyi.soleil.module.region.RegionTypeRecommendFragment
import com.yoyiyi.soleil.module.search.MovieFragment
import com.yoyiyi.soleil.module.search.SeasonFragment
import com.yoyiyi.soleil.module.search.UpFragment
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

    fun getActivity(): Activity

    fun inject(recommendFragment: RecommendFragment)

    fun inject(liveFragment: LiveFragment)

    fun inject(chaseBangumiFragment: ChaseBangumiFragment)

    fun inject(regionFragment: RegionFragment)

    fun inject(dynamicFragment: DynamicFragment)

    fun inject(discoverFragment: DiscoverFragment)

    fun inject(allRegionRankFragment: AllRegionRankFragment)

    fun inject(regionTypeFragment: RegionTypeFragment)

    fun inject(regionTypeRecommendFragment: RegionTypeRecommendFragment)

    fun inject(interestFragment: InterestFragment)

    fun  inject(allStationRankFragment: AllStationRankFragment)

    fun inject(archiveFragment: ArchiveFragment)

    fun inject(favouriteFragment: FavouriteFragment)

    fun inject(submitedVideoFragment: SubmitedVideoFragment)

    fun inject(commentFragment: CommentFragment)

    fun inject(summaryFragment: SummaryFragment)

    fun inject(upFragment: UpFragment)

    fun inject(seasonFragment: SeasonFragment)

    fun inject(movieFragment: MovieFragment)

    fun inject(archiveFragment: com.yoyiyi.soleil.module.search.ArchiveFragment)



}
