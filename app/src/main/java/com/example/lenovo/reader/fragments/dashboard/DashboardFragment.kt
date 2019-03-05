package com.example.lenovo.reader.fragments.dashboard

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lenovo.reader.R
import com.example.lenovo.reader.activities.base.BaseActivity
import com.example.lenovo.reader.annotations.Layout
import com.example.lenovo.reader.fragments.base.BaseFragment
import com.example.lenovo.reader.fragments.base.LifecycleObserverPresenter
import com.example.lenovo.reader.navigation.Router
import com.example.model.models.Category
import com.example.model.models.FavoriteArticle
import com.example.model.models.LastAddedArticle
import kotlinx.android.synthetic.main.fragment_dashboard.*
import javax.inject.Inject

@Layout(R.layout.fragment_dashboard)
class DashboardFragment : BaseFragment(), DashboardView {

    @Inject
    lateinit var dashboardPresenter: DashboardPresenter
    @Inject
    lateinit var router: Router
    @Inject
    lateinit var bottomNavigationFragment: BottomNavigationFragment

    lateinit var lastAddedArticleAdapter: LastAddedArticleAdapter
    lateinit var favoriteArticleAdapter: FavoriteArticleAdapter
    lateinit var categoriesAdapter: CategoriesAdapter

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("LOGT", "Dashboardfragment onViewCreated")
        (activity as BaseActivity).setSupportActionBar(dashboard_bottomappbar)
        setHasOptionsMenu(true)
        setUp()
    }

    private fun setUp() {
        dashboard_floatingactionbutton.setOnClickListener {
            router.goToAddArticle()
        }

        //TODO: Remove this
        dashboard_detail_button.setOnClickListener {
            router.goToArticle()
        }

        lastAddedArticleAdapter = LastAddedArticleAdapter()
        dashboard_last_added_recycler.adapter = lastAddedArticleAdapter
        dashboard_last_added_recycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
//        lastAddedArticleAdapter.addListener { router.goToArticle() }
        dashboard_last_added_recycler.isNestedScrollingEnabled = false

        favoriteArticleAdapter = FavoriteArticleAdapter()
        dashboard_favorites_recycler.adapter = favoriteArticleAdapter
        dashboard_favorites_recycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        dashboard_favorites_recycler.isNestedScrollingEnabled = false

        categoriesAdapter =
            CategoriesAdapter(dashboard_chipgroup, context, { s -> Log.d("TAG", "NOT IMPLEMENTED") })
    }

    override fun providePresenter(): LifecycleObserverPresenter = dashboardPresenter

    override fun onCreateOptionsMenu(
        menu: Menu,
        inflater: MenuInflater
    ) {
        inflater.inflate(R.menu.menu_dashboard, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_main_search -> {
                Log.d("CLICK", "onOptionsItemSelected:: Search")
                router.goToSearch()
            }
            android.R.id.home -> {
                Log.d("CLICK", "onOptionsItemSelected:: Navi")
                bottomNavigationFragment.show(
                    (activity as BaseActivity).supportFragmentManager,
                    bottomNavigationFragment.javaClass.simpleName
                )
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun updateCategories(categories: List<Category>) {
        categoriesAdapter.replaceAll(categories)
    }

    override fun updateFavoriteArticles(favoriteArticles: List<FavoriteArticle>) {
        favoriteArticleAdapter.replace(favoriteArticles)
    }

    override fun updateLastAddedArticles(lastAddedArticles: List<LastAddedArticle>) {
        lastAddedArticleAdapter.replace(lastAddedArticles)
    }
}
