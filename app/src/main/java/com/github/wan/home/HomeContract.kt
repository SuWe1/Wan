package com.github.wan.home

import com.github.wan.BasePresenter
import com.github.wan.BaseView
import com.github.wan.bean.ArticleItemBean

interface HomeContract {
    interface View : BaseView<Presenter> {
        fun setData(items: List<ArticleItemBean>?)
    }

    interface Presenter : BasePresenter {
        fun loadNext()

        fun refresh()
    }
}