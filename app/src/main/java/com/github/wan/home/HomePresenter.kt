package com.github.wan.home

import android.content.Context
import com.github.wan.bean.ArticleItem
import com.github.wan.extentions.networkConnected
import com.github.wan.net.RetrofitClient
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.logging.Logger

class HomePresenter(context: Context, pageView: HomeContract.View) : HomeContract.Presenter {

    private val mContext: Context = context
    private var mPage: Int = 0
    private val mPageView: HomeContract.View = pageView

    init {
        mPageView.presenter = this
    }

    override fun start() {
        load(mPage)
    }

    override fun loadNext() {
        load(mPage.inc())
    }

    override fun refresh() {
        load(0)
    }


    private fun load(page: Int) {
        if (networkConnected(mContext)) {
            RetrofitClient.getArticleApi()!!.getArticleList(page)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { t: ArticleItem? ->
                        if (t != null) {
                            mPageView.setData(t.data.datas)
                        }
                    }
        }

    }
}

