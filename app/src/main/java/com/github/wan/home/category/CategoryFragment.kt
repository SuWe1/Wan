package com.github.wan.home.category

import android.view.View
import com.github.wan.R
import com.github.wan.base.BaseFragment

/**
 * Created by swyww on 2018/10/13
 */

class CategoryFragment : BaseFragment(), CategoryContract.View {

    override lateinit var presenter: CategoryContract.Presenter

    companion object {
        fun newInstance() = CategoryFragment()
    }

    override fun getContentLayout(): Int = R.layout.fragment_category_layout

    override fun initParams() {
    }

    override fun initView(view: View) {
    }

    override fun initData() {
    }

}