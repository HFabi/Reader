package com.example.lenovo.reader.fragments.search

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lenovo.reader.R
import com.example.lenovo.reader.activities.mainactivity.MainActivity
import com.example.lenovo.reader.annotations.Layout
import com.example.lenovo.reader.fragments.base.BaseFragment
import com.example.lenovo.reader.fragments.base.BasePresenter
import com.example.lenovo.reader.fragments.search.adapters.ExcerptArticleAdapter
import com.example.lenovo.reader.navigation.Router
import com.example.lenovo.reader.pxFromDp
import com.example.lenovo.reader.view.KeyboardUtils
import com.example.lenovo.reader.view.ToolbarSearchView
import com.example.model.models.ExcerptArticle
import kotlinx.android.synthetic.main.fragment_search.search_recyclerview
import kotlinx.android.synthetic.main.fragment_search.search_toolbar
import javax.inject.Inject

@Layout(R.layout.fragment_search)
class SearchFragment : BaseFragment(), SearchView {

  @Inject
  lateinit var presenter: SearchPresenter
  @Inject
  lateinit var router: Router

  override fun providePresenter(): BasePresenter = presenter

  val excerptArticleAdapter: ExcerptArticleAdapter = ExcerptArticleAdapter()

  var toolbarSearchView: ToolbarSearchView? = null

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    toolbarSearchView = ToolbarSearchView(context)
    Log.d("CALL","Call Viewcreated")
    toolbarSearchView?.let {
      setUpCustomToolbar(search_toolbar, true, it)
    }
    setUpAdaptiveToolbarElevation(search_toolbar, search_recyclerview, pxFromDp(30.0f, requireContext()))
  }

  override fun onResume() {
    super.onResume()
    (activity as MainActivity).setBottomNavigationEnabled(false)
    (activity as MainActivity).currentFragment = this
    setUp()
    toolbarSearchView?.requestFocus()
    showKeyboard(toolbarSearchView)
  }

  fun setUp() {
    search_recyclerview.adapter = excerptArticleAdapter
    search_recyclerview.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
    toolbarSearchView?.onSearchChange = { text ->
      presenter.searchExcerptArticles(text)
    }
    excerptArticleAdapter.onItemClickListener = { excerptArticle, view ->
      router.goToArticle(this, excerptArticle.id)
    }
  }

  override fun updateExcerptArticles(excerptArticles: List<ExcerptArticle>) {
    excerptArticleAdapter.replace(excerptArticles)
  }

  override fun onCreateOptionsMenu(
    menu: Menu,
    inflater: MenuInflater
  ) {
    inflater.inflate(R.menu.menu_search, menu)
    super.onCreateOptionsMenu(menu, inflater)
  }

  override fun onPause() {
    super.onPause()
    hideKeyboard(toolbarSearchView)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      android.R.id.home -> router.goBack()
    }
    return super.onOptionsItemSelected(item)
  }
}