package com.github.wan.net.api

import com.github.wan.bean.ArticleItem
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable

interface WanApi {
    @GET("/article/list/{page}/json")
    fun getArticleList(@Path("page") page: Int): Observable<ArticleItem>
}