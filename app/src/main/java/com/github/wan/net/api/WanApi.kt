package com.github.wan.net.api

import com.github.wan.bean.ArticleItem
import com.github.wan.bean.BannerBean
import com.github.wan.bean.Category
import com.github.wan.bean.LRBean
import retrofit2.http.*
import rx.Observable

interface WanApi {
    @GET("/article/list/{page}/json")
    fun getArticleList(@Path("page") page: Int): Observable<ArticleItem>

    @GET("/banner/json")
    fun getBanner(): Observable<BannerBean>

    @POST("article/query/{page}/json")
    fun searchArticle(@Path("page") page: Int, @Field("k") k: String)

    @GET("tree/json")
    fun getCategory(): Observable<Category>

    @GET("/article/list/{page}/json")
    fun getCategoryArticle(@Path("page") page: Int, @Query("cid") cid: String): Observable<ArticleItem>

    @POST("/user/login")
    @FormUrlEncoded
    fun login(@FieldMap params: HashMap<String, String>): Observable<LRBean>

    @POST("/user/register")
    @FormUrlEncoded
    fun register(@FieldMap params: HashMap<String, String>): Observable<LRBean>

    @GET("user/logout/json")
    fun logout(): Observable<Void>
}