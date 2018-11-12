package com.github.wan.other

import com.github.wan.bean.UserInfo
import com.github.wan.extentions.genericClass

/**
 * Created by swyww on 2018/11/10
 */

object LoginManage {

    fun isLogin(): Boolean {
        return PreferenceUtils.getBoolean(PreferenceUtils.ISLOGING, false)
    }

    fun updateLoginStatus(status: Boolean) {
        PreferenceUtils.putBoolean(PreferenceUtils.ISLOGING, status)
    }

    fun saveUserInfo(userInfo: String) {
        PreferenceUtils.putString(PreferenceUtils.USERINFO, userInfo)
    }

    fun clearUserInfo() {
        PreferenceUtils.putString(PreferenceUtils.USERINFO, "")
    }

    fun getUserInfo(): UserInfo? {
        val userInfoStr = PreferenceUtils.getString(PreferenceUtils.USERINFO, "")
        if (userInfoStr.isNotEmpty()) {
            return GsonUtils.fromLocalJson(userInfoStr, genericClass<UserInfo>())
        }
        return null
    }
}