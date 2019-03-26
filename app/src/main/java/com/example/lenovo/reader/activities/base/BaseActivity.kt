package com.example.lenovo.reader.activities.base

import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity : DaggerAppCompatActivity() {

  abstract fun provideContainerId(): Int

}
