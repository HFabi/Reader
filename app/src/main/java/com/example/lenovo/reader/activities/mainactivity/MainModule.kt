package com.example.lenovo.reader.activities.mainactivity

import com.example.lenovo.reader.activities.base.BaseActivity
import com.example.lenovo.reader.annotations.ActivityScope
import com.example.lenovo.reader.navigation.Router
import com.example.lenovo.reader.navigation.RouterImpl
import dagger.Binds
import dagger.Module

@Module()
abstract class MainModule {

  @Binds
  @ActivityScope
  abstract fun provideActivityContext(mainActivity: MainActivity): BaseActivity

  @Binds
  @ActivityScope
  abstract fun provideRouter(router: RouterImpl): Router

}