package com.github.wan.base

import com.github.wan.bean.ArticleItem

/**
 * Created by swyww on 2018/10/13
 */
interface IModelView<T, S : BaseModel> {

    /**
     * 目前还没用到  先不必必须重写 先让其可选
     */
    fun onLoadFinish(model: S, errorCode: Int, errorMsg: String, isEmpty: Boolean, isFirstPage: Boolean, hasNextPage: Boolean){

    }

    fun setData(data: T)
}