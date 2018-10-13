package com.github.wan.home.category

import android.content.Context
import com.github.wan.bean.Category

/**
 * Created by swyww on 2018/10/13
 */
class CategoryPresenter(context: Context, pageView: CategoryContract.View) : CategoryContract.Presenter, CategoryModel.ICategoryModelView {

    private val mContext: Context = context
    private val mPageView: CategoryContract.View = pageView
    private val model: CategoryModel

    init {
        mPageView.presenter = this
        model = CategoryModel(this)
    }

    override fun setData(data: Category) {

    }


    override fun start() {

    }
}