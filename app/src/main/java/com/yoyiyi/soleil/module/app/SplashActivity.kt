package com.yoyiyi.soleil.module

import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.jakewharton.rxbinding2.view.RxView
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.yoyiyi.soleil.App
import com.yoyiyi.soleil.R
import com.yoyiyi.soleil.bean.app.Splash
import com.yoyiyi.soleil.constant.Constants
import com.yoyiyi.soleil.di.component.DaggerActivityComponent
import com.yoyiyi.soleil.di.module.ActivityModule
import com.yoyiyi.soleil.module.app.LoginActivity
import com.yoyiyi.soleil.mvp.contract.app.SplashContract
import com.yoyiyi.soleil.mvp.presenter.app.SplashPresenter
import com.yoyiyi.soleil.utils.PrefsUtils
import com.yoyiyi.soleil.widget.statusbar.StatusBarUtil
import kotlinx.android.synthetic.main.activity_splash.*
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SplashActivity : RxAppCompatActivity(), SplashContract.View {

    @Inject
    lateinit var mPresenter: SplashPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 隐藏标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        // 隐藏状态栏
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash)
        //设置透明
        StatusBarUtil.setTransparent(this)
        if (!isTaskRoot && intent.hasCategory(Intent.CATEGORY_LAUNCHER) && intent.action != null && intent.action == Intent.ACTION_MAIN) {//防止重复实例化app
            finish()
            return@onCreate
        }
        initInject()
        initWidget()
        loadData()

    }

    fun initInject() {
        DaggerActivityComponent.builder()
                .appComponent(App.instance.appComponent)
                .activityModule(ActivityModule(this))
                .build()
                .inject(this)
        mPresenter.attachView(this)//依赖 保持p和v生命周期一致
    }

    override fun onDestroy() {
        mPresenter.detachView()
        super.onDestroy()
    }

    /**
     * 跳转
     */
    fun redirect() {
        var flag = PrefsUtils.getInstance().getBoolean(Constants.IS_LOGINED_FLAG, false)
        flag = true
        if (flag) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        } else {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    /**
     * 初始化组件
     */
    fun initWidget() {
        RxView.clicks(llCountDown)
                .throttleFirst(3, TimeUnit.SECONDS)//3秒内响应第一次发射数据
                .compose(bindToLifecycle())
                .subscribe { _ -> redirect() }
    }

    /**
     * 请求数据
     */
    fun loadData() {
        mPresenter.getSplashData()
        mPresenter.setCountDown()
    }


    override fun showError(msg: String) {
        ivSplash.setImageResource(R.mipmap.ic_default_bg)
    }

    override fun complete() {

    }

    override fun showSplash(splash: Splash) {
        val urls = splash.data
        urls.let {
            if (it.isNotEmpty()) {
                val pos = Random().nextInt(urls.size)
                Glide.with(this)
                        .load(it[pos].thumb)
                        //.load("http://i0.hdslb.com/bfs/archive/ba17d4df28fb0c28c8f596082d7328b4415ee28b.png")
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .dontAnimate()
                        .into(ivSplash)
            } else {
                ivSplash.setImageResource(R.mipmap.ic_default_bg)
            }
        } ?: ivSplash.setImageResource(R.mipmap.ic_default_bg)

    }

    override fun showCountDown(count: Int) {
        tvCountDown.text = count.toString()
        if (count == 0) {
            redirect()
        }
    }

}
