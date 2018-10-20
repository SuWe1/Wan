package com.github.wan.home.category

import android.content.Context
import com.github.wan.bean.ArticleItemBean
import com.github.wan.bean.Category
import com.github.wan.bean.CategoryItem
import com.github.wan.extentions.addDataToList
import com.github.wan.extentions.networkConnected
import java.util.ArrayList

/**
 * Created by swyww on 2018/10/13
 */
class CategoryPresenter(context: Context, pageView: CategoryContract.View) : CategoryContract.Presenter, CategoryModel.ICategoryModelView {

    private val mContext: Context = context
    private val mPageView: CategoryContract.View = pageView
    private val model: CategoryModel

    private val h1: MutableList<CategoryItem> = ArrayList()
    private val h2: MutableList<CategoryItem> = ArrayList()

    private var h2Index = 0
    private var page = 0
    private var cid = -1

    init {
        mPageView.presenter = this
        model = CategoryModel(this)
    }

    override fun setData(data: Category) {
        if (data.data.isEmpty()) {
            return
        }
        addDataToList(h1, data.data, true)
        addDataToList(h2, h1[h2Index].children)
        if (h1[h2Index].children != null && h1[h2Index].children!!.isNotEmpty()) {
            cid = h1[h2Index].children!![0].id
            getArticleList()
        }
        mPageView.setH1Data(h1)
        mPageView.setH2Data(h2)
    }

    override fun noMoreData() {

    }

    override fun setArticle(articles: List<ArticleItemBean>) {
        mPageView.setData(articles, page == 0)
    }

    override fun start() {
        model.getCategory()
    }

    override fun h1Click(index: Int) {
        h2Index = index
        page = 0
        addDataToList(h2, h1[h2Index].children, true)
        mPageView.setH2Data(h2)
        h2Click(h2[0].id)
    }

    override fun h2Click(id: Int) {
        cid = id
        refresh()
    }

    override fun getArticleList() {
        if (networkConnected(mContext))
            model.getCategoryArticle(cid, page)
    }

    override fun loadNext() {
        page++
        getArticleList()
    }

    override fun refresh() {
        page = 0
        getArticleList()
    }
}