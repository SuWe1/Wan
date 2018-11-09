package com.github.wan.net

import android.text.TextUtils
import android.util.Log
import com.github.wan.other.PreferenceUtils
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * Created by swyww on 2018/11/1
 */

private const val PREF_COOKIE: String = "pref_cookie"
private const val SAVE_USER_LOGIN_KEY = "user/login"
private const val SAVE_USER_REGISTER_KEY = "user/register"

class AddCookiesInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder: Request.Builder = chain.request().newBuilder()
        val cookies: HashSet<String> = PreferenceUtils.getStringSet(PREF_COOKIE, HashSet()) as HashSet<String>
        for (cookie in cookies) {
            builder.addHeader("Cookie", cookie)
            Log.v("OkHttp", "Adding Header: $cookie")
        }
        return chain.proceed(builder.build())
    }
}

class ReceivedCookiesInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalResponse: Response = chain.proceed(chain.request())
        val url = chain.request().url().toString()
        //只对登录注册做cookie保存
        val pingUrl = url.contains(SAVE_USER_LOGIN_KEY) || url.contains(SAVE_USER_REGISTER_KEY)
        if (pingUrl && !TextUtils.isEmpty(originalResponse.header("Set-Cookie"))) {
            val cookies = HashSet<String>()
            for (header in originalResponse.headers("Set-Cookie")) {
                cookies.add(header)
            }
            Log.v("OkHttp", "Save Header: $cookies")
            PreferenceUtils.putStringSet(PREF_COOKIE, cookies)
        }
        return originalResponse
    }

}