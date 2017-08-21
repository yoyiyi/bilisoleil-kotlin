package com.yoyiyi.soleil.mvp.contract.search

import com.yoyiyi.soleil.bean.search.Movie

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/12 10:09
 * * 描述:发现Contract
 */

interface MovieContract {

    interface View : BaseSearchContract.View {

        fun showSearchMovie(movie: Movie)

    }

    interface Presenter<in T> : BaseSearchContract.Presenter<T> {

        // void getSearchArchiveData(String keyword, int page, int pagesize);

        fun getSearchMovieData()

    }
}
