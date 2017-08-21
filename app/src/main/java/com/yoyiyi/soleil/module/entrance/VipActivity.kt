package com.yoyiyi.soleil.module.entrance

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import cn.sharesdk.framework.ShareSDK
import cn.sharesdk.onekeyshare.OnekeyShare
import com.yoyiyi.soleil.R
import com.yoyiyi.soleil.constant.Constants
import com.yoyiyi.soleil.utils.ClipboardUtils
import com.yoyiyi.soleil.utils.ToastUtils
import com.yoyiyi.soleil.widget.statusbar.StatusBarUtil
import kotlinx.android.synthetic.main.activity_vip.*

/**
 * @author zzq  作者 E-mail:   soleilyoyiyi@gmail.com
 * *
 * @date 创建时间：2017/6/10 0:21
 * * 描述:大会员界面
 */
class VipActivity : AppCompatActivity() {
  /*  @BindView(R.id.toolbar)
    internal var mToolbar: Toolbar? = null
    @BindView(R.id.collapsing_toolbar)
    internal var mCollapsingToolbar: CollapsingToolbarLayout? = null
    @BindView(R.id.web_view)
    internal var mWebView: WebView? = null*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vip)
        initWedgit()
        web_view.loadUrl(Constants.VIP_URL)
        //调用shareSDK
        ShareSDK.initSDK(this, "")


    }

    private fun initWedgit() {
        initToolar()
        StatusBarUtil.setTranslucentForCoordinatorLayout(this, 0)
    }

    private fun initToolar() {
        toolbar.title = ""
        toolbar.setNavigationIcon(R.drawable.ic_clip_back_white)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { finish() }
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
                intent.data = Uri.parse(Constants.VIP_URL)
                startActivity(intent)
            }
            R.id.menu_share -> showShare()
            R.id.menu_copy -> {
                ClipboardUtils.copyText(Constants.VIP_URL)
                ToastUtils.showSingleLongToast("复制成功")
            }
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && web_view.canGoBack()) {
            web_view.goBack()// 返回前一个页面
            return true
        }
        return super.onKeyDown(keyCode, event)
    }


    override fun onDestroy() {
        web_view.destroy()
        super.onDestroy()
    }

    private fun showShare() {
        ShareSDK.initSDK(this)
        val oks = OnekeyShare()
        //关闭sso授权
        oks.disableSSOWhenAuthorize()
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle("来自bili-soleil的分享")
        // titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl(Constants.VIP_URL)
        // text是分享文本，所有平台都需要这个字段
        oks.text = "大会员"
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(Constants.VIP_URL)
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本")
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name))
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn")
        // 启动分享GUI
        oks.show(this)
    }
}
