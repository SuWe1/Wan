package com.github.wan.common

import android.support.v4.app.Fragment
import io.reactivex.disposables.CompositeDisposable

open class BaseFragment : Fragment() {
    protected var subscriptions: CompositeDisposable = CompositeDisposable()

    override fun onResume() {
        super.onResume()
        subscriptions = CompositeDisposable()
    }

    override fun onPause() {
        super.onPause()
        subscriptions.clear()
    }
}