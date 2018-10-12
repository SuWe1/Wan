package com.github.wan.home

import android.os.Bundle
import android.os.PersistableBundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.NavigationView
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Menu
import android.view.MenuItem
import com.github.wan.BaseActivity
import com.github.wan.R
import com.github.wan.extentions.hideFragmentFromActivity
import com.github.wan.extentions.replaceFragmentInActivity
import com.github.wan.extentions.showFragmentToActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.bar_main_toolbar.*

class MainActivity : BaseActivity() {

    private lateinit var homeFragment: HomeFragment
    private lateinit var homePresenter: HomePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeFragment = supportFragmentManager.findFragmentByTag("homeFragment") as HomeFragment? ?: HomeFragment.newInstance().also {
            replaceFragmentInActivity(it, R.id.fragment_content)
        }
        homePresenter = HomePresenter(this, homeFragment)
    }

    override fun getContentLayout(): Int = R.layout.activity_main


    override fun initParams() {

    }

    override fun initView() {
        setSupportActionBar(tool_bar)
//        supportActionBar!!.setDisplayShowTitleEnabled(false)
        val toggle = ActionBarDrawerToggle(this, drawer_layout, tool_bar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        left_navigation.setNavigationItemSelectedListener(mLeftNavigationItemSelectedListener)
        bottom_navigation.setOnNavigationItemSelectedListener(mBottomNavigationItemSelectedListener)
    }

    override fun initData() {
    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)
        if (homeFragment.isAdded) {
            supportFragmentManager.putFragment(outState, "homeFragment", homeFragment)
        }
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

    private val mBottomNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                tool_bar.setTitle(R.string.title_home)
                showFragmentToActivity(homeFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                tool_bar.setTitle(R.string.title_dashboard)
                hideFragmentFromActivity(homeFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                tool_bar.setTitle(R.string.title_notifications)
                hideFragmentFromActivity(homeFragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private val mLeftNavigationItemSelectedListener = NavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {

        }
    }


}
