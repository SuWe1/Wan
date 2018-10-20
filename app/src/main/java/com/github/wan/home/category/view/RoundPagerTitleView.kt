package com.github.wan.home.category.view

import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import com.github.wan.R
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView

/**
 * Created by swyww on 2018/10/19
 */
class RoundPagerTitleView : LinearLayout, IPagerTitleView {
    private var mContentTv: TextView? = null
    private var mSelectBg: Drawable? = null
    private var mUnSelectBg: Drawable? = null
    private var mSelectColor: Int = 0
    private var mUnSelectColor: Int = 0

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        inflate(context, R.layout.view_round_pager_title, this)
        mContentTv = findViewById(R.id.content_tv)
        mSelectColor = resources.getColor(R.color.blue_5A73F6)
        mUnSelectColor = resources.getColor(R.color.grey_717273)
    }


    fun setSelectBg(selectBg: Drawable) {
        mSelectBg = selectBg
    }

    fun setUnSelectBg(unSelectBg: Drawable) {
        mUnSelectBg = unSelectBg
    }

    fun setSelectColor(selectColor: Int) {
        mSelectColor = selectColor
    }

    fun setUnSelectColor(unSelectColor: Int) {
        mUnSelectColor = unSelectColor
    }

    override fun onSelected(i: Int, i1: Int) {
        if (mSelectBg != null) {
            mContentTv!!.background = mSelectBg
        }
        mContentTv!!.setTextColor(mSelectColor)
        mContentTv!!.typeface = Typeface.DEFAULT_BOLD
    }

    override fun onDeselected(i: Int, i1: Int) {
        if (mUnSelectBg != null) {
            mContentTv!!.background = mUnSelectBg
        }
        mContentTv!!.setTextColor(mUnSelectColor)
        mContentTv!!.typeface = Typeface.DEFAULT
    }

    override fun onLeave(i: Int, i1: Int, v: Float, b: Boolean) {

    }

    override fun onEnter(i: Int, i1: Int, v: Float, b: Boolean) {

    }

    fun setText(charSequence: CharSequence) {
        mContentTv!!.text = charSequence
    }
}
