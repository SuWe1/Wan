package com.github.wan.collect

import com.github.wan.base.BaseModel
import com.github.wan.base.IModelView
import com.github.wan.bean.ArticleItem
import com.github.wan.bean.ArticleItemBean
import com.github.wan.net.RetrofitClient
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by swyww on 2018/11/17
 */

class CollectModel(iCollectModelView: ICollectModelView) : BaseModel() {

    private val listener = iCollectModelView

    fun getCollectList(page: Int) {
        RetrofitClient.getWanApi()!!.collectList(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if (it != null) {
                        if (page * 20 + it.data.size >= it.data.total) {
                            hasMore = false
                            listener.noMoreData()
                        }
                        listener.setData(it.data.datas)
                    }
                }
    }

}

interface ICollectModelView : IModelView<List<ArticleItemBean>, CollectModel> {


    fun noMoreData()
}