package com.example.lenovo.reader.fragments.article

import android.content.Intent
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.ShareActionProvider
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.MenuItemCompat
import androidx.navigation.fragment.navArgs
import com.example.lenovo.reader.R
import com.example.lenovo.reader.activities.mainactivity.MainActivity
import com.example.lenovo.reader.annotations.Layout
import com.example.lenovo.reader.fragments.base.BaseFragment
import com.example.lenovo.reader.fragments.base.BasePresenter
import com.example.lenovo.reader.getActionBarHeight
import com.example.lenovo.reader.pxFromDp
import com.example.model.models.Article
import com.example.model.models.Category
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_article.article_appbarlayout
import kotlinx.android.synthetic.main.fragment_article.article_chipgroup
import kotlinx.android.synthetic.main.fragment_article.article_iamgeview
import kotlinx.android.synthetic.main.fragment_article.article_nestedscrollview
import kotlinx.android.synthetic.main.fragment_article.article_subtitle
import kotlinx.android.synthetic.main.fragment_article.article_title
import kotlinx.android.synthetic.main.fragment_article.article_toolbar
import kotlinx.android.synthetic.main.fragment_article.article_webview
import kotlinx.android.synthetic.main.fragment_article.test_content
import javax.inject.Inject

@Layout(R.layout.fragment_article)
class ArticleFragment : BaseFragment(), ArticleView {

  val args: ArticleFragmentArgs by lazy {
    ArticleFragmentArgs.fromBundle(requireArguments())
  }

  @Inject
  lateinit var presenter: ArticlePresenter

  private var isFavorite = false

  private var shareActionProvider: ShareActionProvider? = null

  override fun providePresenter(): BasePresenter = presenter

  override fun articleId(): Long = args.articleId

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    requireContext()
    super.onViewCreated(view, savedInstanceState)
    setUpToolbar(article_toolbar, true)
    setUpAdaptiveToolbarElevation(article_appbarlayout, article_nestedscrollview, pxFromDp(30.0f, context!!))
    // Remove any title
    article_toolbar.title = ""
    Log.d("AAAA", "Passed id: " + args.articleId)
  }

  override fun onResume() {
    super.onResume()
    (activity as MainActivity).setBottomNavigationEnabled(false)

    val myShareIntent = Intent(Intent.ACTION_SEND)
    myShareIntent.type = "image/*"
    myShareIntent.putExtra(Intent.EXTRA_STREAM, "")
    shareActionProvider?.setShareIntent(myShareIntent)
  }

  /**
   * Called only once for creation
   */
  override fun onCreateOptionsMenu(
    menu: Menu,
    inflater: MenuInflater
  ) {
    inflater.inflate(R.menu.menu_article, menu)

    // Locate MenuItem with ShareActionProvider
    menu.findItem(R.id.action_article_share).also { menuItem ->
      // Fetch and store ShareActionProvider
      shareActionProvider = MenuItemCompat.getActionProvider(menuItem) as ShareActionProvider
    }


    super.onCreateOptionsMenu(menu, inflater)
  }

//  // Call to update the share intent
//  private fun setShareIntent(shareIntent: Intent) {
//    shareActionProvider?.setShareIntent(shareIntent)
//  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      android.R.id.home -> presenter.goBack()
      R.id.action_article_favorite -> {
        onFavoriteButtonClicked()
      }
      R.id.action_article_fontsize -> presenter.onFontSizeChanged() //TODO
      R.id.action_article_share -> Log.d("TODO", "NOT IMPLEMENTED") //TODO
      R.id.action_article_delete -> showDeleteDialog()
      R.id.action_fullscreen -> showFullScreen()
    }
    return super.onOptionsItemSelected(item)
  }

  private fun onFavoriteButtonClicked() {
    isFavorite = !isFavorite
  }

  override fun setContentFontSize(index: Int) {
    val fontSize = when (index) {
      0 -> context?.resources?.getDimension(R.dimen.articleFontSizeSmall) ?: 17.0f
      1 -> context?.resources?.getDimension(R.dimen.articleFontSizeMedium) ?: 17.0f
      2 -> context?.resources?.getDimension(R.dimen.articleFontSizeLarge) ?: 17.0f
      else -> 17.0f
    }
    test_content.setTextSize(TypedValue.COMPLEX_UNIT_PX, fontSize)
  }

  override fun showArticle(article: Article) {
    article_title.text = article.title
    article_subtitle.text = article.author

    val encodedHtml = article.content
    article_webview.loadDataWithBaseURL("file:///", encodedHtml, "text/html", "UTF-8", null)
    article_webview.setVerticalScrollBarEnabled(false)

    if (article.leadImagePath.isEmpty()) {
      article_appbarlayout.setExpanded(false, false)
      val lp = article_appbarlayout.getLayoutParams() as CoordinatorLayout.LayoutParams
      lp.height = getActionBarHeight(context!!)
    } else {
      article_appbarlayout.setExpanded(true, false)
      val lp = article_appbarlayout.getLayoutParams() as CoordinatorLayout.LayoutParams
      lp.height = context?.resources?.getDimension(R.dimen.appbarlayout_height)?.toInt() ?: 400
      Picasso.get()
        .load("file://" + article.leadImagePath)
        .resize(article_iamgeview.measuredWidth, article_iamgeview.measuredHeight)
        .centerCrop()
        .into(article_iamgeview)
    }
  }

  override fun setCategories(categories: List<Category>) {
    article_chipgroup.setCategories(categories)
  }

  fun showDeleteDialog() {
    val alertDialog: AlertDialog? = context?.let {
      AlertDialog.Builder(it).apply {
        setTitle(getString(R.string.delete_article))
          .setMessage(getString(R.string.message_delete))
        setPositiveButton(R.string.ok, { dialog, id ->
          presenter.onDeleteArticleClicked()
        })
        setNegativeButton(R.string.cancel, { dialog, id ->
          // User cancelled the dialog
        })
      }.create()
    }
    alertDialog?.show()
  }

  override fun showErrorDialog(message: String) {
    val alertDialog: AlertDialog? = context?.let {
      AlertDialog.Builder(it).apply {
        setPositiveButton(R.string.ok, { dialog, id ->

        })
      }.create()
    }
    alertDialog?.show()
  }

  fun showFullScreen() {
    // Enables regular immersive mode.
    // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
    // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
    activity?.window?.decorView?.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
        // Set the content to appear under the system bars so that the
        // content doesn't resize when the system bars hide and show.
        or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        // Hide the nav bar and status bar
        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        or View.SYSTEM_UI_FLAG_FULLSCREEN)

    activity?.window?.decorView?.setOnSystemUiVisibilityChangeListener { visibility ->
      // Note that system bars will only be "visible" if none of the
      // LOW_PROFILE, HIDE_NAVIGATION, or FULLSCREEN flags are set.
      if (visibility and View.SYSTEM_UI_FLAG_FULLSCREEN == 0) {
        Log.d("WIND", "if - - -")
        // TODO: The system bars are visible. Make any desired
        // adjustments to your UI, such as showing the action bar or
        // other navigational controls.
        activity?.window?.decorView?.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
        article_toolbar.visibility = View.VISIBLE
        if (VERSION.SDK_INT >= VERSION_CODES.M) {
          if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO) {
            // if light
            activity?.window?.decorView?.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
          }
          if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            // ifDark
            activity?.window?.clearFlags(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
          }
          //if auto?????
        }
        //hell
        //dunkel
        //was ist mit auto ??

        //TODO
        //bug tobar light/dark not correct restored

      } else {
        Log.d("WIND", "else - - ")
        // TODO: The system bars are NOT visible. Make any desired
        //TODO hide actionbar
        article_toolbar.visibility = View.GONE
//        article_appbarlayout.visibility = View.GONE
//        article_collapstingtoolbarlayout.visibility = View.GONE

//        (activity as MainActivity).supportActionBar?.hide()
//        article_appbarlayout.visibility = View.GONE
//        article_iamgeview.visibility = View.GONE
//        article_toolbar

//        activity?.actionBar?.hide()
//        activity?.actionBar?.hide()
        // adjustments to your UI, such as hiding the action bar or
        // other navigational controls.
      }
    }
  }
}