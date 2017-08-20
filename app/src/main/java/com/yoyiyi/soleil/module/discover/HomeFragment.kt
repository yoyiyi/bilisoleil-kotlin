package com.yoyiyi.soleil.module.discover

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.trello.rxlifecycle2.components.support.RxFragment
import com.yoyiyi.soleil.R
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/6/11 12:04
 * * 描述:主页
 */
class HomeFragment : RxFragment() {



    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_home, container, false)
        return view

    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        iv_empty?.setOnClickListener {
            val activity = activity
            if (activity is InterestActivity) {
                activity.mViewPager?.currentItem = 1
            }
        }
    }

}
