package com.github.wan.login

import android.content.Context
import com.github.wan.bean.UserInfo
import com.github.wan.other.GsonUtils
import com.github.wan.other.LoginManage

/**
 * Created by swyww on 2018/11/1
 */

class LRPresenter(context: Context, pageView: LRContract.View) : LRContract.Presenter, ILRModelView {

    private val mContext: Context = context
    private var mPage: Int = 0
    private val mPageView: LRContract.View = pageView
    private val model: LRModel

    init {
        pageView.presenter = this
        model = LRModel(this)
    }

    override fun login() {
        val username = mPageView.getUsername()
        val password = mPageView.getPassword()
        if (username.isEmpty() || password.isEmpty()) {
            mPageView.showErrorInput()
            return
        }
        val params = hashMapOf("username" to username, "password" to password)
        model.login(params)
    }

    override fun register() {
        val username = mPageView.getUsername()
        val password = mPageView.getPassword()
        if (username.isEmpty() || password.isEmpty()) {
            mPageView.showErrorInput()
            return
        }
        val params = hashMapOf("username" to username, "password" to password, "repassword" to password)
        model.register(params)
    }

    override fun start() {
    }

    override fun loginResult(successful: Boolean, errorMsg: String) {
        if (successful) {
            mPageView.loginSuccess()
            LoginManage.updateLoginStatus(successful)
        } else {
            mPageView.loginFail(errorMsg)
        }
    }

    override fun registerResult(successful: Boolean, errorMsg: String) {
        if (successful) {
            mPageView.registerSuccess()
            LoginManage.updateLoginStatus(successful)
        } else {
            mPageView.registerFail(errorMsg)
        }
    }

    override fun setData(data: UserInfo) {
        val userInfoStr = GsonUtils.toJson(data)
        LoginManage.saveUserInfo(userInfoStr)
    }

}