package com.github.wan.collect

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.github.wan.R
import com.github.wan.base.BaseActivity
import com.github.wan.extentions.addFragmentToActivity
import kotlinx.android.synthetic.main.bar_main_toolbar.*

class CollectActivity : BaseActivity() {

    private lateinit var collectPresenter: CollectPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val collectFragment = supportFragmentManager.findFragmentByTag("collectFragment") as CollectFragment?
                ?: CollectFragment.newInstance().also {
                    addFragmentToActivity(it, R.id.content_layout, "collectFragment")
                }
        collectPresenter = CollectPresenter(this, collectFragment)
    }

    override fun getContentLayout(): Int = R.layout.activity_collect

    override fun initParams() {
    }

    override fun initView() {
        setSupportActionBar(tool_bar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle(R.string.collect)
    }

    override fun initData() {
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
