package com.github.wan.home.home

import android.content.Context
import android.content.Intent
import com.github.wan.bean.ArticleItem
import com.github.wan.bean.ArticleItemBean
import com.github.wan.detail.DetailActivity
import com.github.wan.extentions.addDataToList
import com.github.wan.extentions.genericClass
import com.github.wan.extentions.networkConnected
import java.util.ArrayList

class HomePresenter(context: Context, pageView: HomeContract.View) : HomeContract.Presenter, HomeModel.IHomeModelView {

    private val mContext: Context = context
    private var mPage: Int = 0
    private val mPageView: HomeContract.View = pageView
    private val bannerUrl: MutableList<String> = ArrayList()
    private val allTitle: MutableList<String> = ArrayList()
    private val model: HomeModel

    init {
        mPageView.presenter = this
        model = HomeModel(this)
    }

    override fun start() {
        load(mPage)
    }

    override fun loadNext() {
        load(++mPage)
    }

    override fun refresh() {
        mPage = 0
        load(0)
    }


    private fun load(page: Int) {
        if (networkConnected(mContext)) {
            model.getArticleList(page)
        }
    }

    override fun getBannerData() {
        if (networkConnected(mContext)) {
            model.getBanner()
        }
    }

    override fun setData(data: List<ArticleItemBean>) {
        mPageView.setData(data, mPage == 0)
    }

    override fun setBanner(images: List<Any>, titles: List<String>, bannerUrls: List<String>) {
        addDataToList(bannerUrl, bannerUrls, true)
        addDataToList(allTitle, titles, true)
        mPageView.setBannerData(images, titles)
    }

    override fun noMoreData() {

    }

    override fun intoBannerDetail(position: Int) {
        val intent = Intent(mContext, genericClass<DetailActivity>())
        intent.putExtra("url", bannerUrl[position])
        intent.putExtra("title", allTitle[position])
        mContext.startActivity(intent)
    }

}

