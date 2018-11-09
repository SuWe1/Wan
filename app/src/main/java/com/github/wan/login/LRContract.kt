package com.github.wan.login

import com.github.wan.base.BasePresenter
import com.github.wan.base.BaseView

/**
 * Created by swyww on 2018/11/1
 */
interface LRContract {

    interface View : BaseView<Presenter> {
        fun getUsername(): String

        fun getPassword(): String

        fun showErrorInput()

        fun loginSuccess()

        fun loginFail(errorMsg:String)

        fun registerSuccess()

        fun registerFail(errorMsg:String)
    }

    interface Presenter : BasePresenter {
        fun login()

        fun register()
    }
}