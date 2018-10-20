package com.github.wan.home.home

import com.github.wan.base.BasePresenter
import com.github.wan.base.BaseView
import com.github.wan.bean.ArticleItemBean

interface HomeContract {
    interface View : BaseView<Presenter> {
        fun setData(items: List<ArticleItemBean>?, isRefresh: Boolean = false)

        fun setBannerData(images: List<Any>, titles: List<String>)
    }

    interface Presenter : BasePresenter {
        fun loadNext()

        fun refresh()

        fun getBannerData()

        fun intoBannerDetail(position: Int)

    }
}