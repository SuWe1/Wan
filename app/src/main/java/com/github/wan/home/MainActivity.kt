package com.github.wan.home

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.support.constraint.ConstraintLayout
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.LinearLayout
import com.github.wan.BaseActivity
import com.github.wan.R
import com.github.wan.extentions.hideFragmentToActivity
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
                hideFragmentToActivity(homeFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                hideFragmentToActivity(homeFragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

}
