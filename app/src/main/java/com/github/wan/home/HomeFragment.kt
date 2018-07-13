package com.github.wan.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.github.wan.R
import com.github.wan.adapter.WanAdapter
import com.github.wan.bean.ArticleItemBean
import com.github.wan.common.BaseFragment
import com.github.wan.extentions.inflate
import com.scwang.smartrefresh.header.WaterDropHeader
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.BezierRadarHeader
import kotlinx.android.synthetic.main.fragment_home_layout.*

class HomeFragment : BaseFragment(), HomeContract.View {

    override lateinit var presenter: HomeContract.Presenter

    private lateinit var articleListView: RecyclerView
    private lateinit var refreshLayout: SmartRefreshLayout

    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = container?.inflate(R.layout.fragment_home_layout)!!
        with(view) {
            articleListView = this.findViewById(R.id.article_recycler_view)
            refreshLayout = this.findViewById(R.id.smart_refresh_layout)
        }
//        val button:Button = view.findViewById(R.id.button)
//        button.setOnClickListener(object : View.OnClickListener {
//            override fun onClick(p0: View?) {
//                presenter.start()
//            }
//        })
        initView()
        init()
        return view
    }

    private fun init() {
        presenter.start()
    }

    private fun initView() {
        articleListView.apply {
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

    override fun setData(items: List<ArticleItemBean>?) {
        val adapter: WanAdapter? = article_recycler_view.adapter as WanAdapter
        if (adapter != null && items != null) {
            adapter.setData(items)
        }
    }


}