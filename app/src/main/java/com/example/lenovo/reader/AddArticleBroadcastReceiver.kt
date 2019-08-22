package com.example.lenovo.reader

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import timber.log.Timber

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