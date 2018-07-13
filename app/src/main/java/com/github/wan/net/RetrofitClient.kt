package com.github.wan.net

import com.github.wan.net.api.WanApi
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private val WAN_ANDROID_BASE_URL: String = "http://www.wanandroid.com/"

    private var mArticleListApi: WanApi? = null

    private val gsonConverterFactory = GsonConverterFactory.create()
    private val rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create()


        fun getArticleApi(): WanApi? {
            if (mArticleListApi == null) {
                val retrofit: Retrofit = Retrofit.Builder()
                        .baseUrl(WAN_ANDROID_BASE_URL)
                        .addConverterFactory(gsonConverterFactory)
                        .addCallAdapterFactory(rxJavaCallAdapterFactory)
                        .build()
                mArticleListApi = retrofit.create(WanApi::class.java)
            }
            return mArticleListApi
        }
}