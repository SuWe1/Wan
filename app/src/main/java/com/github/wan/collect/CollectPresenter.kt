package com.github.wan.collect

import android.content.Context
import com.github.wan.bean.ArticleItemBean
import com.github.wan.extentions.networkConnected
import com.github.wan.other.PreferenceUtils.init

/**
 * Created by swyww on 2018/11/17
 */

class CollectPresenter(context: Context, pageview: CollectContract.View) : CollectContract.Presenter, ICollectModelView {


    private val mPageView: CollectContract.View = pageview
    private val mModel = CollectModel(this)
    private var mPage = 0

    init {
        mPageView.presenter = this
    }

    override fun start() {
        refresh()
    }

    override fun loadNext() {
        getData(++mPage)
    }

    override fun refresh() {
        mPage = 0
        mPageView.cantLoadMore(true)
        getData(0)
    }

    override fun noMoreData() {
        mPageView.cantLoadMore()
    }

    override fun getData(page: Int) {
        mModel.getCollectList(page)
    }

    override fun setData(data: List<ArticleItemBean>) {
        mPageView.setData(data, mPage == 0)
    }

}