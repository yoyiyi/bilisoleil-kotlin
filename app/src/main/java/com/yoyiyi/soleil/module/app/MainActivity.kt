package com.yoyiyi.soleil.module

import android.os.Bundle
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.yoyiyi.soleil.R
import com.yoyiyi.soleil.bean.app.Splash
import com.yoyiyi.soleil.mvp.contract.app.SplashContract
import kotlinx.android.synthetic.main.activity_main.*
class MainActivity : RxAppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }



}
