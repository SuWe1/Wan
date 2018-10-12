package com.github.wan.detail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebView
import com.github.wan.BaseActivity
import com.github.wan.R
import kotlinx.android.synthetic.main.activity_webview_detail_layout.*

/**
 * Created by swyww on 2018/10/12
 */
class DetailActivity : BaseActivity() {

    private var url: String? = null
    private lateinit var webView: WebView

    override fun getContentLayout(): Int = R.layout.activity_webview_detail_layout

    override fun initParams() {
        url = intent?.getStringExtra("url")
    }

    override fun initView() {
        webView = detail_web_view
    }

    override fun initData() {
        if (url != null) {
            webView.loadUrl(url)
        }
    }
}