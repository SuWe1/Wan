package com.github.wan.login

import com.github.wan.base.BaseModel
import com.github.wan.base.IModelView
import com.github.wan.home.home.HomeModel
import com.github.wan.net.RetrofitClient
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by swyww on 2018/11/1
 */

class LRModel(ilrModelView: ILRModelView) : BaseModel() {

    private val listener: ILRModelView = ilrModelView

    fun login(params: HashMap<String, String>) {
        RetrofitClient.getWanApi()!!.login(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    listener.loginResult(it?.errorCode == 0)
                }
    }

    fun register(params: HashMap<String, String>) {
        RetrofitClient.getWanApi()!!.register(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    listener.loginResult(it?.errorCode == 0)
                }
    }

    fun logout() {
        RetrofitClient.getWanApi()!!.logout()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
    }
}

interface ILRModelView : IModelView<Any, LRModel> {

    fun loginResult(successful: Boolean)

    fun registerResult(successful: Boolean)
}