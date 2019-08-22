package com.example.lenovo.reader.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.lenovo.reader.R
import com.example.lenovo.reader.activities.mainactivity.MainActivity
import com.example.lenovo.reader.fragments.dashboard.DashboardFragment
import timber.log.Timber

class AddArticleBroadcastReceiver(val mainActivity: MainActivity) : BroadcastReceiver() {

  val ACTION: String = "com.example.lenovo.reader.ADD_ARTICLE_COMPLETE"

  init {
    Log.d("BRAODCAST", "inits receiver")
  }

  override fun onReceive(context: Context?, intent: Intent?) {
    Timber.d("Broadcast onReceive is Called !!")
    Log.d("BROADCAST", "Receiving Broadcast onReceive is Called")

    //correct activity and correct fragment

    // a
    val navHostFragment =
      mainActivity.supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
    val currentFragment: Fragment? =
      navHostFragment?.childFragmentManager?.primaryNavigationFragment

    // b
    //val currentFragment:Fragment? = mainActivity.currentFragment

    if (currentFragment is DashboardFragment) {
      currentFragment.dashboardPresenter.reloadLastAddedArticles()
    }
  }

  fun provideIntentFilter(): IntentFilter {
    return IntentFilter().apply { addAction(ACTION) }
  }
}