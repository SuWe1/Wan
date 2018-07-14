package com.github.wan.home

import android.content.Context
import com.github.wan.bean.ArticleItem
import com.github.wan.bean.BannerBean
import com.github.wan.extentions.networkConnected
import com.github.wan.net.RetrofitClient
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.ArrayList

class HomePresenter(context: Context, pageView: HomeContract.View) : HomeContract.Presenter {

    private val mContext: Context = context
    private var mPage: Int = 0
    private val mPageView: HomeContract.View = pageView
    private val bannerUrl: MutableList<String> = ArrayList()

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
            RetrofitClient.getWanApi()!!.getArticleList(page)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { it: ArticleItem? ->
                        if (it != null) {
                            mPageView.setData(it.data.datas)
                        }
                    }
        }
    }

    override fun getBannerData() {
        if (networkConnected(mContext)) {
            RetrofitClient.getWanApi()!!.getBanner()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { it: BannerBean? ->
                        if (it?.data != null) {
                            filterImageAndTitle(it)
                        }
                    }
        }
    }

    private fun filterImageAndTitle(bannerBean: BannerBean) {
        val images: MutableList<Any> = ArrayList()
        val titles: MutableList<String> = ArrayList()
        val i = 0
        for (item in bannerBean.data!!) {
            images.add(i, item.imagePath)
            titles.add(i, item.title)
            bannerUrl.add(i, item.url)
            i.inc()
        }
        mPageView.setBannerData(images, titles)
    }

}

