package com.github.wan.detail

import com.github.wan.BasePresenter
import com.github.wan.BaseView

/**
 * Created by swyww on 2018/10/12
 */

interface DetailContract {
    interface View : BaseView<Presenter> {

    }


    interface Presenter : BasePresenter {

    }

}