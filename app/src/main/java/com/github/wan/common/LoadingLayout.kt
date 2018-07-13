package com.github.wan.common

import android.content.Context
import android.content.res.TypedArray
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.AttributeSet
import android.widget.LinearLayout
import com.github.wan.R
import com.github.wan.extentions.inflate

class LoadingLayout(context: Context?) : LinearLayout(context) {

    /**
     * 空数据View
     */
    var mEmptyView: Int = 0
    /**
     * 状态View
     */
    var mErrorView: Int = 0
    /**
     * 加载View
     */
    var mLoadingView: Int = 0

    constructor(context: Context, attrs: AttributeSet) : this(context) {

    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : this(context, attrs) {

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : this(context, attrs, defStyleAttr) {
        val a: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.LoadingLayout)
        mEmptyView = a.getResourceId(R.styleable.LoadingLayout_emptyView, R.layout.view_empty_layout)
        mErrorView = a.getResourceId(R.styleable.LoadingLayout_stateView, R.layout.view_error_layout)
        mLoadingView = a.getResourceId(R.styleable.LoadingLayout_loadingView, R.layout.view_loading_layout)
        inflate(mEmptyView, true)
        inflate(mErrorView, true)
        inflate(mLoadingView, true)
        a.recycle()
    }


}