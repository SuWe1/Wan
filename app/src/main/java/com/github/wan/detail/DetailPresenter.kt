package com.github.wan.detail

import android.content.Context
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.github.wan.bean.CommonBean

/**
 * Created by swyww on 2018/10/13
 */

class DetailPresenter(context: Context, pageView: DetailContract.View) : DetailContract.Presenter, IDetailModelView {

    private val mContext = context
    private val mPageView = pageView
    private val model: DetailModel

    private var webView: WebView? = null

    init {
        model = DetailModel(this)
    }

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

    override fun setData(data: CommonBean) {

    }

    override fun collectResult(successful: Boolean, errorMsg: String) {
        if (successful) {
            mPageView.collectSuccess()
        } else {
            mPageView.collectFail(errorMsg)
        }
    }

    override fun unCollectResult(successful: Boolean, errorMsg: String) {
        if (successful) {
            mPageView.unCollectSuccess()
        } else {
            mPageView.unCollectFail(errorMsg)
        }
    }

    override fun collectArticle(id: Int) {
        model.collectArticle(id)
    }

    override fun unCollectArticle(id: Int) {
        model.unCollectArticle(id)
    }

}