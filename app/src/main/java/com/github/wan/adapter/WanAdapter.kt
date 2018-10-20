package com.github.wan.adapter

import android.support.v4.util.SparseArrayCompat
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.github.wan.adapter.common.AdapterConstants
import com.github.wan.adapter.common.DelegateAdapter
import com.github.wan.adapter.common.ViewType
import com.github.wan.bean.ArticleItemBean

class WanAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var datas: ArrayList<ViewType>

    private var delegateAdapters = SparseArrayCompat<DelegateAdapter>()


    init {
        delegateAdapters.put(AdapterConstants.ARTICLE, HomeListDelegateAdapter())
        datas = ArrayList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return delegateAdapters.get(viewType).onCreateViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return if (datas.isEmpty()) 0 else datas.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegateAdapters.get(getItemViewType(position)).onBindViewHolder(datas[position], holder, position)
    }

    override fun getItemViewType(position: Int): Int = datas[position].getViewType()

    fun setData(items: List<ArticleItemBean>, clear: Boolean = false) {
        var oldSize: Int = datas.size
        if (clear) {
            datas.clear()
            oldSize = 0
        }
        datas.addAll(items)
        notifyDataSetChanged()
        // notifyItemRangeChanged 原来有20个 最新数据小于20个 就会有问题
        // 因为导致外部数据集和recyclerview内部数据集不一致。
//        notifyItemRangeChanged(oldSize, datas.size)
        // 可以先notifyItemRangeRemoved(0, previousSize);然后notifyItemRangeInserted(0, items.size());
    }
}