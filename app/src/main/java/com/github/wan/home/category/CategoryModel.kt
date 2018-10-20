package com.github.wan.home.category

import com.github.wan.base.BaseModel
import com.github.wan.base.IModelView
import com.github.wan.bean.ArticleItemBean
import com.github.wan.bean.Category
import com.github.wan.net.RetrofitClient
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by swyww on 2018/10/13
 */
class CategoryModel(iCategoryModelView: ICategoryModelView) : BaseModel() {

    private val listener: ICategoryModelView = iCategoryModelView

    fun getCategory() {
        RetrofitClient.getWanApi()?.getCategory()
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe {
                    if (it != null) {
                        listener.setData(it)
                    }
                }
    }

    fun getCategoryArticle(cid: Int, page: Int) {
        RetrofitClient.getWanApi()!!.getCategoryArticle(page, cid.toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if (it?.data != null && it.data.datas.isNotEmpty()) {
                        if (page * 20 + it.data.size >= it.data.total) {
                            hasMore = false
                            listener.noMoreData()
                        }
                        listener.setArticle(it.data.datas)
                    }
                }
    }


    interface ICategoryModelView : IModelView<Category, CategoryModel> {
        fun setArticle(articles: List<ArticleItemBean>)

        fun noMoreData()
    }

}