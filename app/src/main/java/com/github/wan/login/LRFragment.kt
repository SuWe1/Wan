package com.github.wan.login

import android.view.View
import android.widget.Button
import com.github.wan.R
import com.github.wan.base.BaseFragment
import com.github.wan.extentions.showSnackbar
import kotlinx.android.synthetic.main.fragment_lr_layout.*

/**
 * Created by swyww on 2018/11/1
 */
class LRFragment : BaseFragment(), LRContract.View {

    override lateinit var presenter: LRContract.Presenter

    companion object {
        fun newInstance() = LRFragment()
    }

    override fun getContentLayout(): Int = R.layout.fragment_lr_layout

    override fun initParams() {
    }

    override fun initView(view: View) {
        val submitBtn = view.findViewById<Button>(R.id.submit_btn)
        submitBtn.setOnClickListener {
            presenter.login()
        }
    }

    override fun initData() {
    }

    override fun getUsername(): String {
        return username_edit.text.toString()
    }

    override fun getPassword(): String {
        return password_edit.text.toString()
    }

    override fun loginSuccess() {
        activity?.finish()
    }

    override fun loginFail(errorMsg: String) {
        if (errorMsg.isEmpty()) {
            showSnackbar(content_layout, R.string.login_fail)
        } else {
            showSnackbar(content_layout, errorMsg)
        }
    }

    override fun registerSuccess() {
        activity?.finish()
    }

    override fun registerFail(errorMsg: String) {
        if (errorMsg.isEmpty()) {
            showSnackbar(content_layout, R.string.register_fail)
        } else {
            showSnackbar(content_layout, errorMsg)
        }
    }

    override fun showErrorInput() {
        showSnackbar(content_layout, R.string.error_input)
    }

}