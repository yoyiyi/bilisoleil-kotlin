package com.yoyiyi.soleil.module.search

import android.graphics.drawable.AnimationDrawable
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import com.yoyiyi.soleil.R
import com.yoyiyi.soleil.base.BaseContract
import com.yoyiyi.soleil.base.BaseInjectFragment

import com.yoyiyi.soleil.mvp.contract.search.BaseSearchContract

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/5/29 11:46
 * * 描述:基础搜索
 */
abstract class BaseSearchFragment<T : BaseContract.BasePresenter<*>, K> : BaseInjectFragment<T>(), BaseSearchContract.View {

    protected var mList = mutableListOf<K>()
    protected var mIvSearchLoad: ImageView? = null
    protected var mAnimationDrawable: AnimationDrawable? = null
    protected var mRecycler: RecyclerView? = null

    override fun initWidget() {
        mIvSearchLoad = mRootView?.findViewById(R.id.iv_search_load) as ImageView?
        mRecycler = mRootView?.findViewById(R.id.recycler) as RecyclerView?
        initRecyclerView()
    }


    override fun showError(msg: String) {
        //显示搜索不到
        mIvSearchLoad?.let {
            it.setImageResource(R.drawable.search_failed)
            visible(it)
            mRecycler?.let { it1 -> gone(it1) }

        }
        mAnimationDrawable?.stop()

    }

    override fun complete() {
        mIvSearchLoad?.let {
            gone(it)
            mRecycler?.let { it1 -> visible(it1) }
        }
        mAnimationDrawable?.stop()

    }

    override fun showLoading() {
        mIvSearchLoad?.setImageResource(R.drawable.anim_search_loading)
        if (mAnimationDrawable != null)
            mAnimationDrawable = mIvSearchLoad?.drawable as AnimationDrawable
        mIvSearchLoad?.visibility = View.VISIBLE
        mRecycler?.visibility = View.GONE
        mAnimationDrawable?.start()
    }
}
