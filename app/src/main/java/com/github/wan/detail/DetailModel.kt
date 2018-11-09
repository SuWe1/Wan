package com.github.wan.detail

import com.github.wan.base.BaseModel
import com.github.wan.base.IModelView
import com.github.wan.bean.CommonBean
import com.github.wan.login.ILRModelView
import com.github.wan.net.RetrofitClient
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by swyww on 2018/11/9
 */

class DetailModel(iDetailModelView: IDetailModelView) : BaseModel() {

    private val listener: IDetailModelView = iDetailModelView

    fun collectArticle(id: Int) {
        RetrofitClient.getWanApi()!!.collect(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    listener.collectResult(it?.errorCode == 0, it?.errorMsg ?: "")
                }
    }

    fun unCollectArticle(id: Int) {
        RetrofitClient.getWanApi()!!.unCollect(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    listener.unCollectResult(it?.errorCode == 0, it?.errorMsg ?: "")
                }
    }
}

interface IDetailModelView : IModelView<CommonBean, DetailModel> {
    fun collectResult(successful: Boolean, errorMsg: String = "")

    fun unCollectResult(successful: Boolean, errorMsg: String = "")
}