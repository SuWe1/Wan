package com.github.wan.detail

import android.view.MenuItem
import com.github.wan.BaseActivity
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
        presenter.setWebView(detail_web_view)
        setSupportActionBar(detail_tool_bar)
        url = intent?.getStringExtra("url")
        title = intent?.getStringExtra("title")
        presenter = DetailPresenter(this, this)
    }

    override fun initView() {

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = title ?: ""
    }

    override fun initData() {
        presenter.showWebView(url)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}