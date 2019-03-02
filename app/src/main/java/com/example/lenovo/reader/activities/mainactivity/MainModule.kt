package com.example.lenovo.reader.activities.mainactivity

import android.content.Context
import com.example.lenovo.reader.activities.base.BaseActivity
import com.example.lenovo.reader.annotations.ActivityScope
import com.example.lenovo.reader.di.ControllerModule
import com.example.lenovo.reader.di.FragmentBuilder
import com.example.lenovo.reader.navigation.Router
import com.example.lenovo.reader.navigation.RouterImpl
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [ControllerModule::class, FragmentBuilder::class])
abstract class MainModule {

  @Binds
  @ActivityScope
  abstract fun provideActivityContext(mainActivity: MainActivity): BaseActivity

  @Binds
  @ActivityScope
  abstract fun provideRouter(router: RouterImpl): Router

}