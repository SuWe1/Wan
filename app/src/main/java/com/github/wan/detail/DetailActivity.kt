package com.github.wan.detail

import android.support.design.widget.BottomSheetDialog
import android.support.v7.widget.AppCompatImageView
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import com.github.wan.base.BaseActivity
import com.github.wan.R
import com.github.wan.extentions.showSnackbar
import kotlinx.android.synthetic.main.activity_webview_detail_layout.*

/**
 * Created by swyww on 2018/10/12
 */
class DetailActivity : BaseActivity(), DetailContract.View {

    override lateinit var presenter: DetailContract.Presenter
    private var url: String? = null
    private var title: String? = null
    private var id: Int = -1
    private var collect: Boolean = false

    override fun getContentLayout(): Int = R.layout.activity_webview_detail_layout

    override fun initParams() {
        presenter = DetailPresenter(this, this)
        presenter.setWebView(detail_web_view)
        setSupportActionBar(detail_tool_bar)
        url = intent?.getStringExtra("url")
        title = intent?.getStringExtra("title")
        id = intent?.getIntExtra("id", -1) ?: -1
        collect = intent?.getBooleanExtra("collect", false) ?: false
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
                showBottoSheetDialog()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun showBottoSheetDialog() {
        val bottomSheetDialog = BottomSheetDialog(this)
        val contentView = LayoutInflater.from(this).inflate(R.layout.view_bottom_sheet_dialog_layout, null)
        val collectView = contentView.findViewById<View>(R.id.layout_bookmark)
        if (collect) {
            collectView.findViewById<AppCompatImageView>(R.id.imageView).setImageDrawable(getDrawable(R.drawable.ic_like))
            collectView.findViewById<TextView>(R.id.textView).text = getString(R.string.uncollect)
        } else {
            collectView.findViewById<AppCompatImageView>(R.id.imageView).setImageDrawable(getDrawable(R.drawable.ic_unlike))
            collectView.findViewById<TextView>(R.id.textView).text = getString(R.string.collect)
        }
        collectView.setOnClickListener {
            if (id > 0) {
                if (collect) {
                    presenter.unCollectArticle(id)
                } else {
                    presenter.collectArticle(id)
                }
            }
            bottomSheetDialog.dismiss()
        }
        bottomSheetDialog.setContentView(contentView)
        bottomSheetDialog.show()
    }

    override fun onBackPressed() {
        if (detail_web_view.canGoBack()) {
            detail_web_view.goBack()
        } else {
            super.onBackPressed()
        }
    }

    override fun collectSuccess() {
        showSnackbar(content_layout, R.string.collect_success)
    }

    override fun collectFail(errorMsg: String) {
        if (errorMsg.isNotEmpty()) {
            showSnackbar(content_layout, R.string.collect_success)
        } else {
            showSnackbar(content_layout, errorMsg)
        }
    }

    override fun unCollectSuccess() {
        showSnackbar(content_layout, R.string.uncollect_success)
    }

    override fun unCollectFail(errorMsg: String) {
        if (errorMsg.isNotEmpty()) {
            showSnackbar(content_layout, R.string.uncollect_fail)
        } else {
            showSnackbar(content_layout, errorMsg)
        }
    }
}