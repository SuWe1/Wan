package com.github.wan.bean

data class BannerBean(
        val data: List<BannerItemBean>?,
        val errorCode: Int,
        val errorMsg: String
)

data class BannerItemBean(
        val desc: String?,
        val id: Int,
        val imagePath: String,
        val isVisible: Int,
        val order: Int,
        val title: String,
        val type: Int,
        val url: String
)