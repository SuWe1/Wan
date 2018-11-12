package com.github.wan.login

import android.support.v7.widget.AppCompatImageView
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.github.wan.R
import com.github.wan.base.BaseFragment
import com.github.wan.extentions.showSnackbar
import kotlinx.android.synthetic.main.bar_main_toolbar.*
import kotlinx.android.synthetic.main.fragment_lr_layout.*

/**
 * Created by swyww on 2018/11/1
 */
class LRFragment : BaseFragment(), LRContract.View {

    override lateinit var presenter: LRContract.Presenter
    private var login = true

    companion object {
        fun newInstance() = LRFragment()
    }

    override fun getContentLayout(): Int = R.layout.fragment_lr_layout

    override fun initParams() {
    }

    override fun initView(view: View) {
        val submitBtn = view.findViewById<Button>(R.id.submit_btn)
        submitBtn.setOnClickListener {
            if (login) presenter.login() else presenter.register()
        }
        view.findViewById<AppCompatImageView>(R.id.rl_back).setOnClickListener {
            activity?.finish()
        }
        val rightText = view.findViewById<TextView>(R.id.rl_menu_title)
        val title = view.findViewById<TextView>(R.id.lr_title)
        rightText.setOnClickListener {
            title.setText(if (login) R.string.login else R.string.register)
            submitBtn.setText(if (login) R.string.login else R.string.register)
            login = !login
        }
    }

    override fun initData() {
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.login_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.lr_check -> {
                item.setTitle(if (login) R.string.register else R.string.login)
                login = !login
            }
        }
        return super.onOptionsItemSelected(item)
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