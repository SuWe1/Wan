package com.github.wan.login

import android.view.View
import com.github.wan.R
import com.github.wan.base.BaseFragment

/**
 * Created by swyww on 2018/11/1
 */
class LRFragment :BaseFragment(),LRContract.View {

    override lateinit var presenter: LRContract.Presenter

    override fun getContentLayout(): Int = R.layout.fragment_lr_layout

    override fun initParams() {
    }

    override fun initView(view: View) {

    }

    override fun initData() {
    }

    override fun getUsername(): String {

        return ""
    }

    override fun getPassword(): String {
        return ""
    }
}