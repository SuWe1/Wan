package com.github.wan.bean

/**
 * Created by swyww on 2018/10/13
 */
data class Category(
        val data: List<CategoryItem>,
        val errorCode: Int,
        val errorMsg: String
)

data class CategoryItem(
        val children: List<CategoryItem>?,
        val courseId: Int,
        val id: Int,
        val name: String,
        val order: Int,
        val parentChapterId: Int,
        val userControlSetTop: Boolean,
        val visible: Int

)