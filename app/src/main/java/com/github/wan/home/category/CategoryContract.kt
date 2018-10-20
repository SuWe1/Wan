package com.github.wan.home.category

import com.github.wan.base.BasePresenter
import com.github.wan.base.BaseView
import com.github.wan.bean.ArticleItemBean
import com.github.wan.bean.CategoryItem

/**
 * Created by swyww on 2018/10/13
 */
interface CategoryContract {

    interface View : BaseView<Presenter> {
        fun setH1Data(categoryH1: List<CategoryItem>)

        fun setH2Data(categoryH1: List<CategoryItem>)

        fun setData(articles: List<ArticleItemBean>, isRefresh: Boolean = false)
    }

    interface Presenter : BasePresenter {

        fun h1Click(index: Int)

        fun h2Click(id: Int)

        fun getArticleList()

        fun loadNext()

        fun refresh()

    }
}