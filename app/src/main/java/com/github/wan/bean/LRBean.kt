package com.github.wan.bean

/**
 * Created by swyww on 2018/11/1
 */

data class LRBean(
        val data: UserInfo,
        val errorMsg: String,
        val errorCode: Int

)

data class UserInfo(
        val chapterTops: List<Any>?,
        val collectIds: List<Int>,
        val email: String,
        val icon: String,
        val id: Int,
        val password: String,
        val username: String,
        val type: Int,
        val token: String
)