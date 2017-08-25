package com.yoyiyi.soleil.module.app


import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import cn.sharesdk.onekeyshare.OnekeyShare
import com.yoyiyi.soleil.R
import com.yoyiyi.soleil.constant.Constants
import com.yoyiyi.soleil.utils.AppUtils
import com.yoyiyi.soleil.utils.ClipboardUtils
import com.yoyiyi.soleil.utils.ToastUtils
import com.yoyiyi.soleil.widget.statusbar.StatusBarUtil
import kotlinx.android.synthetic.main.activity_brower.*
import kotlinx.android.synthetic.main.common_toolbar.*
import kotlinx.android.synthetic.main.layout_loading.*

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/6/9 21:56
 * * 描述:浏览器界面
 */
class BrowerActivity : AppCompatActivity() {

    private var mTitle: String? = null
    private var mUrl: String? = null
    private var mImg: String? = null


    companion object {

        fun startActivity(context: Context, url: String, title: String, img: String) {
            val intent = Intent(context, BrowerActivity::class.java)
            intent.putExtra(Constants.EXTRA_TITLE, title)
            intent.putExtra(Constants.EXTRA_URL, url)
            intent.putExtra(Constants.EXTRA_IMAGE, img)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_brower)
        initVariables()
        initWedgit()


    }


    private fun initToolar() {
        toolbar?.let {
            it.setNavigationIcon(R.drawable.ic_clip_back_white)
            it.title = if (TextUtils.isEmpty(mTitle)) "详情" else mTitle
            setSupportActionBar(it)
            it.setNavigationOnClickListener { finish() }
        }
    }

    private fun initWedgit() {
        initToolar()
        initWebView()
        StatusBarUtil.setColorNoTranslucent(this, AppUtils.getColor(R.color.colorPrimary))
        //强制隐藏加载框
        // AppUtils.runOnUIDelayed(() -> mPwLoading.setVisibility(View.GONE), 650);
    }

    private fun initVariables() {
        intent?.let {
            mTitle = intent.getStringExtra(Constants.EXTRA_TITLE)
            mUrl = intent.getStringExtra(Constants.EXTRA_URL)
            mImg = intent.getStringExtra(Constants.EXTRA_IMAGE)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_brower, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
            R.id.menu_open -> {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(mUrl)
                startActivity(intent)
            }
            R.id.menu_share -> showShare()
            R.id.menu_copy -> {
                ClipboardUtils.copyText(mUrl)
                ToastUtils.showSingleLongToast("复制成功")
            }
        }
        return super.onOptionsItemSelected(item)
    }

    internal inner class WebClientBase : WebViewClient() {

        override fun onPageFinished(webView: WebView, s: String) {
            super.onPageFinished(webView, s)
            pw_loading?.visibility = View.GONE
            web_view?.settings?.blockNetworkImage = false
            val h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
            val w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
            web_view?.measure(w, h)
        }

        override fun onReceivedError(webView: WebView, i: Int, s: String, s1: String) {
            super.onReceivedError(webView, i, s, s1)
            pw_loading?.visibility = View.GONE
            val errorHtml = "<html><body><h2>找不到网页</h2></body></html>"
            web_view?.loadDataWithBaseURL(null, errorHtml, "text/html", "UTF-8", null)
        }

    }

    @SuppressLint("SetJavaScriptEnabled")
    internal fun initWebView() {
        val webChromeClient = WebClient()
        val webViewClient = WebClientBase()
        val webSettings = web_view.settings
        //设置js支持
        webSettings.javaScriptEnabled = true
        // 设置支持javascript脚本
        webSettings.javaScriptCanOpenWindowsAutomatically = false
        //设置缓存
        webSettings.cacheMode = WebSettings.LOAD_NO_CACHE
        webSettings.domStorageEnabled = true
        webSettings.setGeolocationEnabled(true)
        webSettings.useWideViewPort = true//关键点
        webSettings.loadWithOverviewMode = true//全屏
        webSettings.builtInZoomControls = true// 设置显示缩放按钮
        webSettings.setSupportZoom(true)//支持缩放
        webSettings.displayZoomControls = false
        webSettings.setAppCacheEnabled(true)
        webSettings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
        web_view.isDrawingCacheEnabled = true
        web_view.settings.blockNetworkImage = true
        web_view.setWebViewClient(webViewClient)
        web_view.requestFocus(View.FOCUS_DOWN)
        web_view.settings.defaultTextEncodingName = "UTF-8"
        web_view.setWebChromeClient(webChromeClient)
        web_view.loadUrl(mUrl)
    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && web_view!!.canGoBack()) {
            web_view.goBack()// 返回前一个页面
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    internal inner class WebClient : WebChromeClient() {

        override fun onProgressChanged(webView: WebView, i: Int) {
            if (i >= 40) {
                pw_loading.visibility = View.GONE
            } else {
                pw_loading.visibility = View.VISIBLE
            }
            web_view?.settings?.blockNetworkImage = false
            super.onProgressChanged(webView, i)
        }
    }

    override fun onDestroy() {
        web_view?.destroy()
        super.onDestroy()
    }

    private fun showShare() {
        //  ShareSDK.initSDK(this);
        val oks = OnekeyShare()
        //关闭sso授权
        oks.disableSSOWhenAuthorize()
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle("来自" + getString(R.string.app_name) + "的分享")
        // titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl(mUrl)
        // text是分享文本，所有平台都需要这个字段
        oks.text = mTitle + "" + mUrl
        oks.setImageUrl(mImg)
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(mUrl)
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("文本")
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name))
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(mUrl)
        // 启动分享GUI
        oks.show(this)
    }


}
