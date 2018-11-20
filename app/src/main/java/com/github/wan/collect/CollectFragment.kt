package com.github.wan.collect

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.github.wan.R
import com.github.wan.adapter.WanAdapter
import com.github.wan.base.BaseFragment
import com.github.wan.bean.ArticleItemBean
import com.scwang.smartrefresh.header.WaterDropHeader
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.footer.ClassicsFooter

/**
 * Created by swyww on 2018/11/17
 */
class CollectFragment : BaseFragment(), CollectContract.View {

    override lateinit var presenter: CollectContract.Presenter

    private lateinit var collectListView: RecyclerView
    private lateinit var refreshLayout: SmartRefreshLayout

    companion object {
        fun newInstance(): CollectFragment {
            return CollectFragment()
        }
    }

    override fun getContentLayout(): Int = R.layout.fragment_collect_layout

    override fun initParams() {
    }

    override fun initView(view: View) {
        with(view) {
            collectListView = this.findViewById(R.id.collect_list_view)
            refreshLayout = this.findViewById(R.id.collect_refresh_layout)
        }

        collectListView.apply {
            this.layoutManager = LinearLayoutManager(context)
            this.adapter = WanAdapter()
        }
        refreshLayout.apply {
            setRefreshHeader(WaterDropHeader(context))
            setRefreshFooter(ClassicsFooter(context))
            setOnRefreshListener {
                presenter.refresh()
                finishRefresh()
            }
            setOnLoadMoreListener {
                presenter.loadNext()
                finishLoadMore()
            }
        }
    }

    override fun initData() {
        presenter.start()
    }

    override fun setData(datas: List<ArticleItemBean>, isRefresh: Boolean) {
        val adapter: WanAdapter? = collectListView.adapter as WanAdapter
        if (adapter != null) {
            adapter.setData(datas, isRefresh)
        }
    }

    override fun cantLoadMore(isCan: Boolean) {
        refreshLayout.setEnableLoadMore(isCan)
    }

}