package com.example.lenovo.reader.activities.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity(@LayoutRes val contentLayoutId: Int) : DaggerAppCompatActivity() {

  abstract fun provideContainerId(): Int

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    if (contentLayoutId != 0) {
      setContentView(contentLayoutId)
    }
  }

}
