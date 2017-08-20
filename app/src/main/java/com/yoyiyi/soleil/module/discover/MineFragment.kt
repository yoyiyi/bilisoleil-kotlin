package com.yoyiyi.soleil.module.discover

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.trello.rxlifecycle2.components.support.RxFragment
import com.yoyiyi.soleil.R

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/6/11 12:04
 * * 描述:我的
 */
class MineFragment : RxFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_mine, container, false)
        return view
    }
}
