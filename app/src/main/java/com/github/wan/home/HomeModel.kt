package com.github.wan.home

import com.github.wan.BaseModel
import com.github.wan.bean.ArticleItem
import com.github.wan.bean.ArticleItemBean
import com.github.wan.bean.BannerBean
import com.github.wan.net.RetrofitClient
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.ArrayList

/**
 * Create by swyww on 2018/7/25
 */

class HomeModel(iHomeModelView: IHomeModelView) : BaseModel() {

    private val bannerUrl: MutableList<String> = ArrayList()
    private val listener: IHomeModelView = iHomeModelView


    fun getArticleList(page: Int) {
        RetrofitClient.getWanApi()!!.getArticleList(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { it: ArticleItem? ->
                    if (it != null) {
                        listener.setArticleList(it.data.datas)
                    }
                }
    }

    fun getBanner() {
        RetrofitClient.getWanApi()!!.getBanner()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { it: BannerBean? ->
                    if (it?.data != null) {
                        filterImageAndTitle(it)
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
        listener.setBanner(images, titles, bannerUrl)
    }


    interface IHomeModelView {
        fun setArticleList(articles: List<ArticleItemBean>)

        fun setBanner(images: List<Any>, titles: List<String>, bannerUrls: List<String>)
    }
}