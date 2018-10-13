package com.github.wan.detail

import android.content.Context
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient

/**
 * Created by swyww on 2018/10/13
 */

class DetailPresenter(context: Context, pageView: DetailContract.View) : DetailContract.Presenter {

    private val mContext = context
    private val mPageView = pageView

    private var webView: WebView? = null

    override fun start() {

    }

    override fun setWebView(wv: WebView) {
        webView = wv
        webView!!.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                view?.loadUrl(request?.url.toString())
                return true
            }
        }
    }

    override fun showWebView(url: String?) {
        if (url != null) {
            webView?.loadUrl(url)
        }
    }


}