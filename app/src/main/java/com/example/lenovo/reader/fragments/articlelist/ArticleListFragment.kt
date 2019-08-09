package com.example.lenovo.reader.fragments.articlelist

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lenovo.reader.R
import com.example.lenovo.reader.R.drawable
import com.example.lenovo.reader.activities.mainactivity.MainActivity
import com.example.lenovo.reader.annotations.Layout
import com.example.lenovo.reader.fragments.articlelist.adapters.ExcerptArticleAdapter
import com.example.lenovo.reader.fragments.base.BaseFragment
import com.example.lenovo.reader.fragments.base.BasePresenter
import com.example.lenovo.reader.fragments.dashboard.DrawableItemDecorator
import com.example.lenovo.reader.navigation.Router
import com.example.model.models.Category
import com.example.model.models.ExcerptArticle
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.article_list_filter.filter_bottom_sheet
import kotlinx.android.synthetic.main.article_list_filter.filter_chipgroup
import kotlinx.android.synthetic.main.article_list_filter.filter_clear_button
import kotlinx.android.synthetic.main.fragment_article_list.article_list_recyclerview
import kotlinx.android.synthetic.main.fragment_article_list.article_list_toolbar
import javax.inject.Inject

@Layout(R.layout.fragment_article_list)
class ArticleListFragment : BaseFragment(), ArticleListView {

  @Inject
  lateinit var presenter: ArticleListPresenter

  @Inject
  lateinit var router: Router

  lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>
  lateinit var excerptArticleAdapter: ExcerptArticleAdapter

  override fun providePresenter(): BasePresenter? = presenter

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)
    setUpToolbar(article_list_toolbar, false, titleRes = R.string.articles)
    setUpFilter()
    setUpList()
    setUpAdaptiveToolbarElevation(article_list_toolbar, article_list_recyclerview)
  }

  override fun onResume() {
    super.onResume()
    (activity as MainActivity).setBottomNavigationEnabled(true)
    (activity as MainActivity).currentFragment = this
  }

  /**
   * Called only once for creation
   */
  override fun onCreateOptionsMenu(
    menu: Menu,
    inflater: MenuInflater
  ) {
    inflater.inflate(R.menu.menu_article_list, menu)
    super.onCreateOptionsMenu(menu, inflater)
  }

  /**
   * Called when invalidate
   */
  override fun onPrepareOptionsMenu(menu: Menu) {
    var b = bottomSheetBehavior.state == BottomSheetBehavior.STATE_HIDDEN
    Log.d("GGG", "Called onPrepareOptionsMenu" + b)

    val filterItem: MenuItem = menu.findItem(R.id.action_article_list_filter)
    filterItem.icon = context!!.getDrawable(
      if (bottomSheetBehavior.state == BottomSheetBehavior.STATE_HIDDEN)
        R.drawable.ic_filter_list_black_24dp else R.drawable.ic_filter_list_color_24dp
    )
    super.onPrepareOptionsMenu(menu)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      R.id.action_article_list_filter -> {
        onToggleFilter()
        activity?.invalidateOptionsMenu()
      }
      R.id.action_article_list_search -> {
        router.goToSearch(this)
      }
    }
    return super.onOptionsItemSelected(item)
  }

  fun onToggleFilter() {
    bottomSheetBehavior.state =
      if (bottomSheetBehavior.state == BottomSheetBehavior.STATE_HIDDEN)
        BottomSheetBehavior.STATE_COLLAPSED else BottomSheetBehavior.STATE_HIDDEN
  }

  fun setUpFilter() {
    bottomSheetBehavior = BottomSheetBehavior.from(filter_bottom_sheet)
    // hide on start
    bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
    bottomSheetBehavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {

      override fun onSlide(bottomSheet: View, slideOffset: Float) {

      }

      override fun onStateChanged(bottomSheet: View, newState: Int) {
        if (newState == BottomSheetBehavior.STATE_HIDDEN) {
          activity?.invalidateOptionsMenu()
        }
      }
    })

    filter_chipgroup.onFilterChangeListener = { categories ->
      presenter.loadExcerptArticles(categories)
    }

    filter_clear_button.setOnClickListener {
      filter_chipgroup.clearCheck()
    }

    filter_chipgroup.onActiveStateChangeListener = { isActive ->
      filter_clear_button.visibility = if (isActive) View.VISIBLE else View.GONE
    }

  }

  fun setUpList() {
    excerptArticleAdapter = ExcerptArticleAdapter()
    excerptArticleAdapter.onItemClickListener = { excerptArticle, view ->
      router.goToArticle(this, excerptArticle.id)
    }
    article_list_recyclerview.adapter = excerptArticleAdapter
    article_list_recyclerview.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
//    article_list_recyclerview.addItemDecoration(
//      ExcerptArticleItemDecorator(
//        pxFromDp(
//          8.0f,
//          context!!
//        ).toInt()
//      )
//    )
    article_list_recyclerview.addItemDecoration(
      DrawableItemDecorator(
        ContextCompat.getDrawable(context!!, drawable.divider)
      )
    )
  }

  override fun replaceExcerptArticles(excerptArticles: List<ExcerptArticle>) {
    excerptArticleAdapter.replace(excerptArticles)
  }

  override fun appendExcerptArticles(excerptArticles: List<ExcerptArticle>) {
    excerptArticleAdapter.append(excerptArticles)
  }

  override fun updateCategories(categories: List<Category>) {
    filter_chipgroup.replaceCategories(categories)
  }
}