package com.yoyiyi.soleil.module.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.trello.rxlifecycle2.components.support.RxFragment
import com.yoyiyi.soleil.R

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * @date 创建时间：2017/7/19 15:08
 * 描述:
 */
class LiveFragment : RxFragment() {
    companion object {
        fun newInstance(): LiveFragment = LiveFragment()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_home_live, container, false)

    }
}