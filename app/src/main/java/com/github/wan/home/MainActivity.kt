package com.github.wan.home

import android.os.Bundle
import android.os.PersistableBundle
import android.support.design.widget.BottomNavigationView
import com.github.wan.BaseActivity
import com.github.wan.R
import com.github.wan.extentions.hideFragmentFromActivity
import com.github.wan.extentions.replaceFragmentInActivity
import com.github.wan.extentions.showFragmentToActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private lateinit var homeFragment: HomeFragment
    private lateinit var homePresenter: HomePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()

        homeFragment = supportFragmentManager.findFragmentByTag("homeFragment") as HomeFragment? ?: HomeFragment.newInstance().also {
            replaceFragmentInActivity(it, R.id.fragment_content)
        }

        homePresenter = HomePresenter(this, homeFragment)
    }

    private fun initView() {
        initStatusBar()
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)
        if (homeFragment.isAdded) {
            supportFragmentManager.putFragment(outState, "homeFragment", homeFragment)
        }
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                showFragmentToActivity(homeFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                hideFragmentFromActivity(homeFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                hideFragmentFromActivity(homeFragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

}
