package com.github.wan.collect

import com.github.wan.base.BasePresenter
import com.github.wan.base.BaseView
import com.github.wan.bean.ArticleItemBean

/**
 * Created by swyww on 2018/11/17
 */
interface CollectContract {
    interface View : BaseView<Presenter> {
        fun setData(datas: List<ArticleItemBean>, isRefresh: Boolean)

        fun cantLoadMore(isCan: Boolean = false)
    }

    interface Presenter : BasePresenter {
        fun getData(page: Int)

        fun loadNext()

        fun refresh()
    }
}