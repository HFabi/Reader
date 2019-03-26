package com.example.lenovo.reader.activities.mainactivity

import android.os.Bundle
import androidx.annotation.ContentView
import com.example.lenovo.reader.R
import com.example.lenovo.reader.activities.base.BaseActivity
import com.example.lenovo.reader.navigation.Router
import javax.inject.Inject

@ContentView(R.layout.activity_main)
class MainActivity : BaseActivity() {

  @Inject lateinit var router: Router

  override fun provideContainerId(): Int = R.id.nav_host_fragment //TODO: this is not correct

}
