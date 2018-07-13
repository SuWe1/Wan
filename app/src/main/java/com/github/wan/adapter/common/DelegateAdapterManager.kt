package com.github.wan.adapter.common

import android.support.v4.util.SparseArrayCompat
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import org.jetbrains.annotations.NotNull

open class DelegateAdapterManager<T> {

    private var delegateAdapters = SparseArrayCompat<DelegateAdapter>()

    fun addDelegate(delegate: DelegateAdapter): DelegateAdapterManager<T> {
        val viewType: Int = delegate.getViewType()
        delegateAdapters.put(viewType, delegate)
        return this
    }

    fun removeDelegate(delegate: DelegateAdapter?): DelegateAdapterManager<T> {
        if (delegate == null) {
            throw  NullPointerException("AdapterDelegate is null")
        }
        val viewType: Int = delegate.getViewType()
        delegateAdapters.remove(viewType)
        return this
    }

    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return delegateAdapters.get(viewType).onCreateViewHolder(parent)
    }

    fun onBindViewHolder(item: T, holder: RecyclerView.ViewHolder, position: Int, viewType: Int) {
        val delegate: DelegateAdapter = delegateAdapters.get(viewType)
//        delegate.onBindViewHolder(item, holder, position)
    }

    fun getItemViewType(items: List<ViewType>, position: Int): Int = items.get(position).getViewType()
}