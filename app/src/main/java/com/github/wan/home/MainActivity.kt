package com.github.wan.home

import android.app.ProgressDialog.show
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.github.wan.base.BaseActivity
import com.github.wan.R
import com.github.wan.collect.CollectActivity
import com.github.wan.extentions.*
import com.github.wan.home.category.CategoryFragment
import com.github.wan.home.category.CategoryPresenter
import com.github.wan.home.home.HomeFragment
import com.github.wan.home.home.HomePresenter
import com.github.wan.login.LRActivity
import com.github.wan.net.RetrofitClient
import com.github.wan.other.LoginManage
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.bar_main_toolbar.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class MainActivity : BaseActivity() {

    private var homeFragment: HomeFragment? = null
    private var homePresenter: HomePresenter? = null
    private var categoryFragment: CategoryFragment? = null
    private var categoryPresenter: CategoryPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeFragment = supportFragmentManager.findFragmentByTag("homeFragment") as HomeFragment? ?: HomeFragment.newInstance().also {
            addFragmentToActivity(it, R.id.fragment_content, "homeFragment")
        }
        categoryFragment = supportFragmentManager.findFragmentByTag("categoryFragment") as CategoryFragment? ?: CategoryFragment.newInstance().also {
            addFragmentToActivity(it, R.id.fragment_content, "categoryFragment")
        }
        homePresenter = HomePresenter(this, homeFragment!!)
        categoryPresenter = CategoryPresenter(this, categoryFragment!!)
        showFragment(homeFragment)
    }

    override fun getContentLayout(): Int = R.layout.activity_main


    override fun initParams() {

    }

    override fun initView() {
        setSupportActionBar(tool_bar)
//        supportActionBar!!.setDisplayShowTitleEnabled(false)
        val toggle = object : ActionBarDrawerToggle(this, drawer_layout, tool_bar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
                left_navigation.menu.findItem(R.id.menu_login).setTitle(if (LoginManage.isLogin()) R.string.loginout else R.string.login)
            }
        }
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        left_navigation.setNavigationItemSelectedListener(mLeftNavigationItemSelectedListener)
        bottom_navigation.setOnNavigationItemSelectedListener(mBottomNavigationItemSelectedListener)
    }

    override fun initData() {
        left_navigation.menu.findItem(R.id.menu_login).setTitle(if (LoginManage.isLogin()) R.string.loginout else R.string.login)
    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_tool_bar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.search -> {

            }
        }
        return super.onOptionsItemSelected(item)
    }

    private val mBottomNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener finish@{ item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                tool_bar.setTitle(R.string.title_home)
                showFragment(homeFragment)
                return@finish true
            }
            R.id.navigation_dashboard -> {
                tool_bar.setTitle(R.string.title_category)
                showFragment(categoryFragment)
                return@finish true
            }
            R.id.navigation_notifications -> {
                tool_bar.setTitle(R.string.title_todo)
                showFragment(null)
                return@finish true
            }
        }
        false
    }

    private val mLeftNavigationItemSelectedListener = NavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.menu_login -> {
                if (LoginManage.isLogin()) {
                    loginout()
                } else {
                    startActivity(Intent(this, genericClass<LRActivity>()))
                }
            }
            R.id.menu_collect -> {
                startActivity(Intent(this, genericClass<CollectActivity>()))
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        false
    }

    fun loginout() {
        LoginManage.clearUserInfo()
        LoginManage.updateLoginStatus(false)
        RetrofitClient.getWanApi()!!.logout()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    showSnackbar(drawer_layout, R.string.success)
                }
    }


    private fun showFragment(fragment: Fragment?) {
        supportFragmentManager.beginTransaction().apply {
            homeFragment?.let {
                hide(it)
            }
            categoryFragment?.let {
                hide(it)
            }
            fragment?.let {
                show(it)
            }
        }.commit()
    }


}
