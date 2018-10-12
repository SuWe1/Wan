package com.github.wan

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager

/**
 * getContentLayout -> initParams -> initView -> initData
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getContentLayout())
        initParams()
        initView()
        initData()
    }

    abstract fun getContentLayout():Int

    abstract fun initParams()

    abstract fun initView()

    abstract fun initData()

    //状态栏透明
    fun initStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0 全透明实现
            val window = window
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT
        } else {//4.4 全透明状态栏
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
    }

    /**
     * 添加状态栏占位视图
     */
    fun addStatusViewWithColor(color: Int) {
        val activityContent: ViewGroup = findViewById(android.R.id.content)
        val statusBar: View = View(this)
        val layoutParams: ViewGroup.LayoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight())
        statusBar.setBackgroundColor(color)
        activityContent.addView(statusBar, layoutParams)
    }

    /**
     * 利用反射获取状态栏高度
     * @return
     */
    fun getStatusBarHeight(): Int {
        var result: Int = 0;
        //获取状态栏高度的资源id
        var resourceId: Int = getResources().getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId)
        }
        return result
    }
}