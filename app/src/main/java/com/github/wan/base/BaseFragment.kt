package com.github.wan.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.wan.R
import com.github.wan.extentions.inflate
import io.reactivex.disposables.CompositeDisposable
/**
 * getContentLayout -> initParams -> initView -> initData
 */
abstract class BaseFragment : Fragment() {
    protected var subscriptions: CompositeDisposable = CompositeDisposable()

    override fun onResume() {
        super.onResume()
        subscriptions = CompositeDisposable()
    }

    override fun onPause() {
        super.onPause()
        subscriptions.clear()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initParams()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = container?.inflate(getContentLayout())!!
        initView(view)
        initData()
        return view
    }

    abstract fun getContentLayout(): Int

    abstract fun initParams()

    abstract fun initView(view: View)

    abstract fun initData()
}