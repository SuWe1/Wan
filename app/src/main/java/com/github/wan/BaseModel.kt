package com.github.wan

/**
 * Create by swyww on 2018/7/25
 */
open class BaseModel {

    var hasMore: Boolean = true

    fun noMoreData(): Boolean = hasMore
}
