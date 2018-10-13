package com.github.wan.detail

import android.webkit.WebView
import com.github.wan.base.BasePresenter
import com.github.wan.base.BaseView

/**
 * Created by swyww on 2018/10/12
 */

interface DetailContract {
    interface View : BaseView<Presenter> {

    }


    interface Presenter : BasePresenter {

        fun setWebView(wv: WebView)

        fun showWebView(url: String?)
    }

}