package com.github.wan.home.category

import com.github.wan.base.BaseModel
import com.github.wan.base.IModelView
import com.github.wan.bean.Category
import com.github.wan.home.home.HomeModel
import com.github.wan.net.RetrofitClient
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by swyww on 2018/10/13
 */
class CategoryModel(iCategoryModelView: ICategoryModelView) : BaseModel() {

    private val listener: ICategoryModelView = iCategoryModelView

    fun getCategory() {
        RetrofitClient.getWanApi()?.getCategory()
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe {
                    if (it != null) {
                        listener.setData(it)
                    }
                }
    }


    interface ICategoryModelView : IModelView<Category> {

    }

}