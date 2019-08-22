package com.example.lenovo.reader.activities.mainactivity

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.lenovo.reader.R
import com.example.lenovo.reader.activities.base.BaseActivity
import com.example.lenovo.reader.activities.base.BasePresenter
import com.example.lenovo.reader.navigation.Router
import com.example.lenovo.reader.receivers.AddArticleBroadcastReceiver
import kotlinx.android.synthetic.main.activity_main.main_bottom_navigation
import javax.inject.Inject

class MainActivity : BaseActivity(R.layout.activity_main) {

  @Inject lateinit var router: Router

  var currentFragment: Fragment? = null
  var addArticleBroadcastReceiver: AddArticleBroadcastReceiver? = null

  override fun providePresenter(): BasePresenter? {
    return null
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
    navController.addOnDestinationChangedListener(NavController.OnDestinationChangedListener { controller, destination, arguments ->
      // kein erneutes Aufrufen der SEite, auf der man sich bereits befindet
    })

    NavigationUI.setupWithNavController(main_bottom_navigation, navController)
  }

  fun setBottomNavigationEnabled(isEnabled: Boolean) {
    main_bottom_navigation.visibility = if (isEnabled) View.VISIBLE else View.GONE
  }

  override fun onResume() {
    super.onResume()
    addArticleBroadcastReceiver = AddArticleBroadcastReceiver(this)
    addArticleBroadcastReceiver?.let { receiver ->
      registerReceiver(receiver, receiver.provideIntentFilter())
    }
  }

  override fun onDestroy() {
    super.onDestroy()
    addArticleBroadcastReceiver?.let { receiver ->
      unregisterReceiver(receiver)
    }
  }

}
