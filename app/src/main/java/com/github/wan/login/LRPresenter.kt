package com.github.wan.login

import android.content.Context

/**
 * Created by swyww on 2018/11/1
 */

class LRPresenter(context: Context, pageView: LRContract.View) : LRContract.Presenter, ILRModelView {

    private val mContext: Context = context
    private var mPage: Int = 0
    private val mPageView: LRContract.View = pageView
    private val model: LRModel

    init {
        pageView.presenter = this
        model = LRModel(this)
    }

    override fun login() {
    }

    override fun register() {
    }

    override fun start() {
    }

    override fun loginResult(successful: Boolean) {

    }

    override fun registerResult(successful: Boolean) {
    }

    override fun setData(data: Any) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}