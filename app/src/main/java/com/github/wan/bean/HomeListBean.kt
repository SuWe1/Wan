package com.github.wan.bean

import com.github.wan.adapter.common.AdapterConstants
import com.github.wan.adapter.common.ViewType

data class ArticleItem(
        val data: ArticleItemData,
        val errorCode: Int,
        val errorMsg: String
)

data class ArticleItemData(
        val curPage: Int,
        val datas: List<ArticleItemBean>,
        val offset: Int,
        val over: Boolean,
        val pageCount: Int,
        val size: Int,
        val total: Int
)

data class ArticleItemBean(
        val apkLink: String?,
        val author: String,
        val chapterId: Int,
        val chapterName: String,
        val collect: Boolean,
        val courseId: Int,
        val desc: String?,
        val envelopePic: String?,
        val fresh: Boolean,
        val id: Int,
        val link: String,
        val niceDate: String,
        val origin: String?,
        val projectLink: String?,
        val publishTime: Long,
        val superChapterId: Int,
        val superChapterName: String,
        val tags: List<Any>?,
        val title: String,
        val type: Int,
        val userId: Int,
        val visible: Int,
        val zan: Int
) : ViewType {
    override fun getViewType(): Int = AdapterConstants.ARTICLE
}