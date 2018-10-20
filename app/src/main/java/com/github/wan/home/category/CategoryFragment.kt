package com.github.wan.home.category

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.github.wan.R
import com.github.wan.adapter.WanAdapter
import com.github.wan.base.BaseFragment
import com.github.wan.bean.ArticleItemBean
import com.github.wan.bean.CategoryItem
import com.github.wan.extentions.addDataToList
import com.github.wan.home.category.view.RoundPagerTitleView
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import kotlinx.android.synthetic.main.fragment_home_layout.*
import net.lucode.hackware.magicindicator.MagicIndicator
import net.lucode.hackware.magicindicator.buildins.UIUtil
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import java.util.ArrayList
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator


/**
 * Created by swyww on 2018/10/13
 */

class CategoryFragment : BaseFragment(), CategoryContract.View {

    override lateinit var presenter: CategoryContract.Presenter
    private lateinit var indicatorH1: MagicIndicator
    private lateinit var indicatorH2: MagicIndicator
    private lateinit var articleListView: RecyclerView
    private lateinit var refreshLayout: SmartRefreshLayout

    private lateinit var navigatorH1: CommonNavigator
    private lateinit var navigatorH2: CommonNavigator

    private val h1Titles: MutableList<CategoryItem> = ArrayList()
    private val h2Titles: MutableList<CategoryItem> = ArrayList()

    private var currentH1 = -1
    private var currentH2 = -1


    companion object {
        fun newInstance() = CategoryFragment()
    }

    override fun getContentLayout(): Int = R.layout.fragment_category_layout

    override fun initParams() {
    }

    override fun initView(view: View) {
        navigatorH1 = CommonNavigator(activity)
        navigatorH2 = CommonNavigator(activity)
        with(view) {
            indicatorH1 = this.findViewById(R.id.indicator_h1)
            indicatorH2 = this.findViewById(R.id.indicator_h2)
            articleListView = this.findViewById(R.id.category_list_view)
            refreshLayout = this.findViewById(R.id.category_refresh_layout)
        }
        articleListView.apply {
            this.layoutManager = LinearLayoutManager(activity)
            this.adapter = WanAdapter()
        }
        refreshLayout.apply {
            setEnableRefresh(false)
            setRefreshFooter(ClassicsFooter(context))
            setOnLoadMoreListener {
                presenter.loadNext()
                finishLoadMore()
            }
        }
        navigatorH1.adapter = object : CommonNavigatorAdapter() {
            override fun getTitleView(p0: Context?, p1: Int): IPagerTitleView {
                val titleView = RoundPagerTitleView(context!!)
                titleView.setText(h1Titles[p1].name)
                titleView.setOnClickListener finish@{
                    if (currentH1 == p1) {
                        return@finish
                    }
                    currentH1 = p1
                    currentH2 = -1
                    presenter.h1Click(p1)
                    navigatorH1.onPageScrolled(p1, 0f, 0)
                    navigatorH1.onPageSelected(p1)
                    scrollToFirst()
                }
                return titleView
            }

            override fun getCount(): Int {
                return h1Titles.size
            }

            override fun getIndicator(p0: Context?): IPagerIndicator {
                return getIndicator()
            }
        }
        navigatorH2.adapter = object : CommonNavigatorAdapter() {
            override fun getTitleView(p0: Context?, p1: Int): IPagerTitleView {
                val titleView = RoundPagerTitleView(context!!)
                titleView.setText(h2Titles[p1].name)
                titleView.setOnClickListener finish@{
                    if (currentH2 == p1) {
                        return@finish
                    }
                    currentH2 = p1
                    presenter.h2Click(h2Titles[p1].id)
                    navigatorH2.onPageScrolled(p1, 0f, 0)
                    navigatorH2.onPageSelected(p1)
                }
                return titleView
            }

            override fun getCount(): Int {
                return h2Titles.size
            }

            override fun getIndicator(p0: Context?): IPagerIndicator {
                return getIndicator()
            }
        }
        indicatorH1.navigator = navigatorH1
        indicatorH2.navigator = navigatorH2
    }

    private fun scrollToFirst(){
        navigatorH2.onPageScrolled(0,0.0f,0)
        navigatorH2.onPageSelected(0)
    }

    private fun getIndicator(): IPagerIndicator {
        val indicator = LinePagerIndicator(context)
        indicator.mode = LinePagerIndicator.MODE_EXACTLY
        indicator.yOffset = UIUtil.dip2px(context, 3.0).toFloat()
        indicator.lineWidth = UIUtil.dip2px(context, 16.0).toFloat()
        indicator.lineHeight = UIUtil.dip2px(context, 3.0).toFloat()
        indicator.xOffset = UIUtil.dip2px(context, 0.5).toFloat()
        indicator.setColors(resources.getColor(R.color.blue_5A73F6))
        return indicator
    }

    override fun initData() {
        presenter.start()
    }

    override fun setData(articles: List<ArticleItemBean>, isRefresh: Boolean) {
        val adapter: WanAdapter? = articleListView.adapter as WanAdapter
        adapter?.setData(articles, isRefresh)
    }

    override fun setH1Data(categoryH1: List<CategoryItem>) {
        addDataToList(h1Titles, categoryH1, true)
        navigatorH1.adapter.notifyDataSetChanged()
    }

    override fun setH2Data(categoryH1: List<CategoryItem>) {
        addDataToList(h2Titles, categoryH1, true)
        navigatorH2.adapter.notifyDataSetChanged()
    }

}