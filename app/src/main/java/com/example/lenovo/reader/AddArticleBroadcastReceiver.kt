package com.example.lenovo.reader

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import timber.log.Timber
import javax.inject.Inject

class AddArticleBroadcastReceiver : BroadcastReceiver() {




  override fun onReceive(context: Context?, intent: Intent?) {
    Timber.d("Bradcast onReceive")

    //correct activity and correct fragment

//    val currentFragment = activity.getSupportFragmentManager().findFragmentByTag
//    if (currentFragment != null && currentFragment is MenuFragment) {
//      (currentFragment as MenuFragment).updateAdapter()
//    }
  }

}