package com.github.wan.adapter

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.github.wan.R
import com.github.wan.adapter.common.AdapterConstants
import com.github.wan.adapter.common.DelegateAdapter
import com.github.wan.adapter.common.ViewType
import com.github.wan.bean.ArticleItemBean
import com.github.wan.extentions.inflate
import kotlinx.android.synthetic.main.item_home_list_layout.view.*

class HomeListDelegateAdapter : DelegateAdapter {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder = HomeItemViewHolder(parent)

    override fun onBindViewHolder(item: ViewType, holder: RecyclerView.ViewHolder, position: Int) {
        holder as HomeItemViewHolder
        holder.setData(item as ArticleItemBean)
    }

    override fun getViewType(): Int = AdapterConstants.ARTICLE

    class HomeItemViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(parent.inflate(R.layout.item_home_list_layout)) {
        private val time = itemView.article_time_tv
        private val title = itemView.article_title_tv
        private val author = itemView.article_author_tv
        private val type = itemView.article_type_tv

        @SuppressLint("SetTextI18n")
        fun setData(articleItem: ArticleItemBean) {
            time.text = articleItem.niceDate
            title.text = articleItem.title
            author.text = articleItem.author
            type.text = "${articleItem.superChapterName}/${articleItem.chapterName}"
        }

    }

}