package com.github.wan.home

import android.content.Context
import android.content.Intent
import com.github.wan.bean.ArticleItem
import com.github.wan.bean.ArticleItemBean
import com.github.wan.bean.BannerBean
import com.github.wan.detail.DetailActivity
import com.github.wan.extentions.addDataToList
import com.github.wan.extentions.genericClass
import com.github.wan.extentions.networkConnected
import com.github.wan.net.RetrofitClient
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.ArrayList

class HomePresenter(context: Context, pageView: HomeContract.View) : HomeContract.Presenter, HomeModel.IHomeModelView {

    private val mContext: Context = context
    private var mPage: Int = 0
    private val mPageView: HomeContract.View = pageView
    private val bannerUrl: MutableList<String> = ArrayList()
    private val model: HomeModel

    init {
        mPageView.presenter = this
        model = HomeModel(this)
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
            model.getArticleList(page)
        }
    }

    override fun getBannerData() {
        if (networkConnected(mContext)) {
            model.getBanner()
        }
    }

    override fun setArticleList(articles: List<ArticleItemBean>) {
        mPageView.setData(articles)
    }

    override fun setBanner(images: List<Any>, titles: List<String>, bannerUrls: List<String>) {
        addDataToList(bannerUrl, bannerUrls, true)
        mPageView.setBannerData(images, titles)
    }

    override fun noMoreData() {

    }

    override fun intoBannerDetail(position: Int) {
        val intent = Intent(mContext, genericClass<DetailActivity>())
        intent.putExtra("url", bannerUrl[position])
        mContext.startActivity(intent)
    }

}

