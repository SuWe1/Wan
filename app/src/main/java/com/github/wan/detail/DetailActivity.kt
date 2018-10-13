package com.github.wan.detail

import android.view.Menu
import android.view.MenuItem
import com.github.wan.base.BaseActivity
import com.github.wan.R
import kotlinx.android.synthetic.main.activity_webview_detail_layout.*

/**
 * Created by swyww on 2018/10/12
 */
class DetailActivity : BaseActivity(), DetailContract.View {

    override lateinit var presenter: DetailContract.Presenter
    private var url: String? = null
    private var title: String? = null

    override fun getContentLayout(): Int = R.layout.activity_webview_detail_layout

    override fun initParams() {
        presenter = DetailPresenter(this, this)
        presenter.setWebView(detail_web_view)
        setSupportActionBar(detail_tool_bar)
        url = intent?.getStringExtra("url")
        title = intent?.getStringExtra("title")
    }

    override fun initView() {

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = title ?: ""
    }

    override fun initData() {
        presenter.showWebView(url)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_popup_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
            R.id.popup_more -> {
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (detail_web_view.canGoBack()) {
            detail_web_view.goBack()
        } else {
            super.onBackPressed()
        }
    }
}