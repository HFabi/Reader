package com.example.lenovo.reader.fragments.article

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.navigation.fragment.navArgs
import com.example.lenovo.reader.R
import com.example.lenovo.reader.annotations.Layout
import com.example.lenovo.reader.fragments.base.BaseFragment
import com.example.lenovo.reader.fragments.base.BasePresenter
import com.example.lenovo.reader.getActionBarHeight
import com.example.lenovo.reader.getStatusBarHeight
import com.example.lenovo.reader.navigation.Router
import com.example.lenovo.reader.pxFromDp
import com.example.model.models.Article
import com.example.model.models.Category
import com.google.android.material.appbar.AppBarLayout
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_article.article_appbarlayout
import kotlinx.android.synthetic.main.fragment_article.article_chipgroup
import kotlinx.android.synthetic.main.fragment_article.article_collapstingtoolbarlayout
import kotlinx.android.synthetic.main.fragment_article.article_favorite_floatingactionbutton
import kotlinx.android.synthetic.main.fragment_article.article_iamgeview
import kotlinx.android.synthetic.main.fragment_article.article_nestedscrollview
import kotlinx.android.synthetic.main.fragment_article.article_subtitle
import kotlinx.android.synthetic.main.fragment_article.article_title
import kotlinx.android.synthetic.main.fragment_article.article_toolbar
import kotlinx.android.synthetic.main.fragment_article.article_webview
import kotlinx.android.synthetic.main.fragment_article.test_content
import kotlinx.android.synthetic.main.fragment_dashboard.dashboard_toolbar
import javax.inject.Inject

@Layout(R.layout.fragment_article)
class ArticleFragment : BaseFragment(), ArticleView {

  val args: ArticleFragmentArgs by navArgs()

  @Inject
  lateinit var presenter: ArticlePresenter
  @Inject
  lateinit var router: Router

  private var showActionFavorite = false
  private var isFavorite = false

  override fun providePresenter(): BasePresenter = presenter

  override fun articleId(): Int = args.articleId

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)
    setUpToolbar(article_toolbar, true)

    // Remove any title
    article_toolbar.title = ""

    setUp()
    Log.d("AAAA", "Passed id: " + args.articleId)

    article_chipgroup.setCategories(
      arrayListOf(
        Category(1, "Design"),
        Category(2, "Programming"),
        Category(3, "Art"),
        Category(4, "Learning"),
        Category(5, "Test")
      )
    )
  }

  fun setUp() {
    //Collapsing Tollbar
    // article_appbarlayout.totalScrollRange
    // Test

    article_appbarlayout.addOnOffsetChangedListener(
      AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
        if (verticalOffset == -1 * article_appbarlayout.totalScrollRange && !showActionFavorite) {
          showActionFavorite = true
          activity?.invalidateOptionsMenu()
          Log.d("off", "invalidate MENU")
        }
        if (verticalOffset != -1 * article_appbarlayout.totalScrollRange && showActionFavorite) {
          showActionFavorite = false
          activity?.invalidateOptionsMenu()
          Log.d("off", "invalidate MENU")
        }

      })

    //Floating Action Button
    article_favorite_floatingactionbutton.setOnClickListener { onFavoriteButtonClicked() }

    // ist elevation in dep -> auch umrechnen
    article_appbarlayout.elevation = 0f
    article_nestedscrollview.viewTreeObserver.addOnScrollChangedListener {
      if (article_nestedscrollview != null) {
        if (article_nestedscrollview.scrollY > pxFromDp(30.0f, context!!)) {
          article_appbarlayout?.elevation = pxFromDp(5f, context!!)
        } else {
          article_appbarlayout?.elevation = 0f
        }
      }
    }

  }

  /**
   * Called only once for creation
   */
  override fun onCreateOptionsMenu(
    menu: Menu,
    inflater: MenuInflater
  ) {
    inflater.inflate(R.menu.menu_article, menu)
    val item: MenuItem = menu.findItem(R.id.action_article_favorite)
    item.isVisible = showActionFavorite
    item.icon = favoriteIcon()
    super.onCreateOptionsMenu(menu, inflater)
  }

  /**
   * Called when invalidate
   */
  override fun onPrepareOptionsMenu(menu: Menu) {
    val item: MenuItem = menu.findItem(R.id.action_article_favorite)
    item.isVisible = showActionFavorite
    item.icon = favoriteIcon()
    super.onPrepareOptionsMenu(menu)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      android.R.id.home -> router.goBack()
      R.id.action_article_favorite -> {
        onFavoriteButtonClicked()
        activity?.invalidateOptionsMenu()
      }
      R.id.action_article_fontsize -> presenter.onFontSizeChanged() //TODO
      R.id.action_article_share -> Log.d("TODO", "NOT IMPLEMENTED") //TODO
      R.id.action_article_delete -> Log.d("TODO", "NOT IMPLEMENTED") //TODO
    }
    return super.onOptionsItemSelected(item)
  }

  private fun onFavoriteButtonClicked() {
    isFavorite = !isFavorite
    //TODO: Refactor when bug is removed from support library
    //https://issuetracker.google.com/issues/117476935
    if (isFavorite) {
      article_favorite_floatingactionbutton.hide();
      article_favorite_floatingactionbutton.setImageDrawable(
        getResources().getDrawable(
          R.drawable.ic_favorite_white_24dp,
          context?.getTheme()
        )
      )
      article_favorite_floatingactionbutton.show();
    } else {
      article_favorite_floatingactionbutton.hide();
      article_favorite_floatingactionbutton.setImageDrawable(
        getResources().getDrawable(
          R.drawable.ic_favorite_border_white_24dp,
          context?.getTheme()
        )
      )
      article_favorite_floatingactionbutton.show();
    }
  }

  private fun favoriteIcon(): Drawable? {
    return context?.getDrawable(
      if (isFavorite) R.drawable.ic_favorite_black_24dp else R.drawable.ic_favorite_border_black_24dp
    )
  }

  override fun setContentFontSize(fontSize: Float) {
    Log.d("TAGG", "setContentFontSize in View " + fontSize)
    test_content.textSize = fontSize
  }

  override fun showArticle(article: Article) {
    article_title.text = article.title
    article_subtitle.text = article.author
    article_appbarlayout.setExpanded(false, false)

    val lp = article_appbarlayout.getLayoutParams() as CoordinatorLayout.LayoutParams
//    lp.height = pxFromDp(getStatusBarHeight(context!!).toFloat(), context!!).toInt()
//    lp.height = pxFromDp(getStatusBarHeight(context!!).toFloat(), context!!).toInt()
    lp.height = getActionBarHeight(context!!)

//    lp.behavior
//    lp.anchorGravity
//    lp.keyline
//    lp.
//    lp.height = 600;
    Log.d("ABC","test "+pxFromDp(getStatusBarHeight(context!!).toFloat(), context!!).toInt())
    Log.d("ABC","test "+getStatusBarHeight(context!!).toFloat())
    Log.d("ABC","test "+getActionBarHeight(context!!))


//    lp.height = resources.getDimension(R.dimen.toolbar_height).toInt()
//    lp.height = getStatusBarHeight(context!!) + pxFromDp(24.0f, context!!).toInt()


//    val encodedHtml = Base64.encodeToString(article.content.toByteArray(), Base64.NO_PADDING)
//    article_webview.loadData(encodedHtml, "text/html", "base64")

    val encodedHtml = article.content
    article_webview.loadDataWithBaseURL("file:///", encodedHtml, "text/html", "UTF-8", null)
    article_webview.setVerticalScrollBarEnabled(false)

    if (article.leadImageUrl.isEmpty()) {
      article_appbarlayout.setExpanded(false, false)
    } else {
      Picasso.get()
        .load("file://" + article.leadImagePath)
        .resize(article_iamgeview.measuredWidth, article_iamgeview.measuredHeight)
        .centerCrop()
        .into(article_iamgeview)
    }
  }


  private fun enableCollapsingToolbar(isEnabled: Boolean) {

  }


}