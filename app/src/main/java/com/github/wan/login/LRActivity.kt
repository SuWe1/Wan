package com.github.wan.login

import android.os.Bundle
import com.github.wan.R
import com.github.wan.base.BaseActivity
import com.github.wan.extentions.addFragmentToActivity
import kotlinx.android.synthetic.main.activity_lr_layout.*
import kotlinx.android.synthetic.main.bar_main_toolbar.*

/**
 * Created by swyww on 2018/11/1
 */
class LRActivity : BaseActivity() {

    private lateinit var lrPresenter: LRPresenter

    override fun getContentLayout(): Int = R.layout.activity_lr_layout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val lrFragment = supportFragmentManager.findFragmentByTag("lrFragment") as LRFragment?
                ?: LRFragment.newInstance().also {
                    addFragmentToActivity(it, R.id.content_layout, "lrFragment")
                }
        lrPresenter = LRPresenter(this, lrFragment)
    }

    override fun initParams() {
    }

    override fun initView() {
        supportActionBar?.hide()
    }

    override fun initData() {
    }
}