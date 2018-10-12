package com.github.wan.home

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.wan.R
import com.github.wan.adapter.WanAdapter
import com.github.wan.bean.ArticleItemBean
import com.github.wan.common.BaseFragment
import com.github.wan.extentions.inflate
import com.github.wan.other.PicassoImageLoader
import com.scwang.smartrefresh.header.WaterDropHeader
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.youth.banner.Banner
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.fragment_home_layout.*

class HomeFragment : BaseFragment(), HomeContract.View {

    override lateinit var presenter: HomeContract.Presenter

    private lateinit var articleListView: RecyclerView
    private lateinit var refreshLayout: SmartRefreshLayout
    private lateinit var banner: Banner

    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun getContentLayout(): Int = R.layout.fragment_home_layout

    override fun initParams() {
    }

    override fun initView(view: View) {
        with(view) {
            articleListView = this.findViewById(R.id.article_recycler_view)
            refreshLayout = this.findViewById(R.id.smart_refresh_layout)
            banner = this.findViewById(R.id.article_banner)
        }
//        val button:Button = view.findViewById(R.id.button)
//        button.setOnClickListener(object : View.OnClickListener {
//            override fun onClick(p0: View?) {
//                presenter.start()
//            }
//        })
        initSubView()
    }

    override fun initData() {
        init()
    }

    private fun init() {
        presenter.start()
        presenter.getBannerData()
    }

    private fun initSubView() {
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
        banner.apply {
            //设置banner样式
            setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
            ////设置图片加载器
            setImageLoader(PicassoImageLoader())
            //设置banner动画效果
            setBannerAnimation(Transformer.DepthPage)
            //设置自动轮播，默认为true
            isAutoPlay(true)
            //设置轮播时间
            setDelayTime(5000)
            //设置指示器位置（当banner模式中有指示器时）
            setIndicatorGravity(BannerConfig.RIGHT)
            //设置是否允许手动滑动轮播图（默认true）
            setViewPagerIsScroll(true)
        }
    }

    override fun onStart() {
        super.onStart()
        banner.startAutoPlay()
    }

    override fun onPause() {
        super.onPause()
        banner.stopAutoPlay()
    }

    override fun setData(items: List<ArticleItemBean>?) {
        val adapter: WanAdapter? = article_recycler_view.adapter as WanAdapter
        if (adapter != null && items != null) {
            adapter.setData(items)
        }
    }

    override fun setBannerData(images: List<Any> , titles:List<String>) {
        banner.setImages(images)
        banner.setBannerTitles(titles)
        banner.start()
    }

}