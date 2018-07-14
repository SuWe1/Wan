package com.github.wan.net

import com.github.wan.net.api.WanApi
import com.orhanobut.logger.Logger
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {

    private val TAG = "Retrofit Response :"

    private val WAN_ANDROID_BASE_URL: String = "http://www.wanandroid.com/"

    private var mArticleListApi: WanApi? = null

    private val gsonConverterFactory = GsonConverterFactory.create()
    private val rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create()


    val logging: HttpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
        Logger.i("$TAG $it")
    }).setLevel(HttpLoggingInterceptor.Level.BODY)

    val client: OkHttpClient = OkHttpClient.Builder()
            .addNetworkInterceptor(logging)
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .build()

    fun getWanApi(): WanApi? {
        if (mArticleListApi == null) {
            val retrofit: Retrofit = Retrofit.Builder()
                    .baseUrl(WAN_ANDROID_BASE_URL)
                    .client(client)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build()
            mArticleListApi = retrofit.create(WanApi::class.java)
        }
        return mArticleListApi
    }


}