package com.github.wan.login

import com.github.wan.base.BaseModel
import com.github.wan.base.IModelView
import com.github.wan.bean.UserInfo
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
                    listener.loginResult(it?.errorCode == 0, it?.errorMsg ?: "")
                }
    }

    fun register(params: HashMap<String, String>) {
        RetrofitClient.getWanApi()!!.register(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    listener.loginResult(it?.errorCode == 0, it?.errorMsg ?: "")
                    if (it?.errorCode == 0) {
                        listener.setData(it.data)
                    }
                }
    }

    fun logout() {
        RetrofitClient.getWanApi()!!.logout()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
    }
}

interface ILRModelView : IModelView<UserInfo, LRModel> {

    fun loginResult(successful: Boolean, errorMsg: String = "")

    fun registerResult(successful: Boolean, errorMsg: String = "")

    override fun setData(data: UserInfo) {

    }
}