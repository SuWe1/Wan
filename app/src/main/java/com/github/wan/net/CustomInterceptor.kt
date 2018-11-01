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

const val PREF_COOKIE: String = "pref_cookie"

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
        if (!TextUtils.isEmpty(originalResponse.header("Set-Cookie"))) {
            val cookies = HashSet<String>()
            for (header in originalResponse.headers("Set-Cookie")) {
                cookies.add(header)
            }
            PreferenceUtils.putStringSet(PREF_COOKIE, cookies)
        }
        return originalResponse
    }

}