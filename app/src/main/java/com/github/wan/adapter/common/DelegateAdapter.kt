package com.github.wan.adapter.common

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.github.wan.adapter.common.ViewType
import org.jetbrains.annotations.NotNull

interface DelegateAdapter {
    fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder

    fun onBindViewHolder(item: ViewType, holder: RecyclerView.ViewHolder, position: Int)

    fun getViewType(): Int
}