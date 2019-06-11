package com.example.lenovo.reader.fragments.dashboard

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.lenovo.reader.R
import com.example.lenovo.reader.activities.mainactivity.MainActivity
import com.example.lenovo.reader.annotations.Layout
import com.example.lenovo.reader.fragments.base.BaseFragment
import com.example.lenovo.reader.fragments.base.BasePresenter
import com.example.lenovo.reader.fragments.dashboard.adapters.LastAddedArticleAdapter
import com.example.lenovo.reader.navigation.Router
import com.example.lenovo.reader.pxFromDp
import com.example.model.models.LastAddedArticle
import kotlinx.android.synthetic.main.fragment_dashboard.dashboard_floatingactionbutton
import kotlinx.android.synthetic.main.fragment_dashboard.dashboard_last_added_recycler
import javax.inject.Inject

@Layout(R.layout.fragment_dashboard)
class DashboardFragment : BaseFragment(), DashboardView {

  @Inject
  lateinit var dashboardPresenter: DashboardPresenter
  @Inject
  lateinit var router: Router

  lateinit var lastAddedArticleAdapter: LastAddedArticleAdapter

  override fun providePresenter(): BasePresenter = dashboardPresenter

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)
    setHasOptionsMenu(true)
    setUp()
  }

  override fun onResume() {
    super.onResume()
    (activity as MainActivity).currentFragment = this
    (activity as MainActivity).setBottomNavigationEnabled(true)
  }

  private fun setUp() {
    dashboard_floatingactionbutton.setOnClickListener {
      router.goToAddArticle(this)
    }

    lastAddedArticleAdapter = LastAddedArticleAdapter()
    dashboard_last_added_recycler.adapter = lastAddedArticleAdapter
    dashboard_last_added_recycler.layoutManager =
      StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
    dashboard_last_added_recycler.isNestedScrollingEnabled = false
    lastAddedArticleAdapter.addListener { article, view ->
      router.goToArticle(
        this,
        article.id,
        view
      )
    }

    dashboard_last_added_recycler.addItemDecoration(
      ArticleItemDecorator(
        pxFromDp(
          8.0f,
          context!!
        ).toInt()
      )
    )

//    val snapHelperLastArticle = StartSnapHelper()
//    snapHelperLastArticle.attachToRecyclerView(dashboard_last_added_recycler)
  }

  override fun updateLastAddedArticles(lastAddedArticles: List<LastAddedArticle>) {
    lastAddedArticleAdapter.replace(lastAddedArticles)
  }
}
//lastAddedArticleAdapter = LastAddedArticleAdapter()
//dashboard_last_added_recycler.adapter = lastAddedArticleAdapter
//dashboard_last_added_recycler.layoutManager =
//LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
//dashboard_last_added_recycler.isNestedScrollingEnabled = false
//lastAddedArticleAdapter.addListener { article, view ->
//  router.goToArticle(
//    this,
//    article.id,
//    view
//  )
//}