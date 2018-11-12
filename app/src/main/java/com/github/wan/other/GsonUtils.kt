package com.github.wan.other

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonSyntaxException
import java.lang.reflect.Type

/**
 * Created by swyww on 2018/11/10
 */
object GsonUtils {

    val localGson = createLocalGson()
    private val sRemoteGson = createRemoteGson()

    private fun createLocalGsonBuilder(): GsonBuilder {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setLenient()
        return gsonBuilder
    }

    private fun createLocalGson(): Gson {
        return createLocalGsonBuilder().create()
    }

    private fun createRemoteGson(): Gson {
        return createLocalGsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
    }

    @Throws(JsonSyntaxException::class)
    fun <T> fromLocalJson(json: String, clazz: Class<T>): T? {
        try {
            return localGson.fromJson(json, clazz)
        } catch (e: JsonSyntaxException) {
            return null
        }

    }

    fun <T> fromLocalJson(json: String, typeOfT: Type): T {
        return localGson.fromJson(json, typeOfT)
    }

    fun toJson(src: Any): String {
        return localGson.toJson(src)
    }

    fun toRemoteJson(src: Any): String {
        return sRemoteGson.toJson(src)
    }
}