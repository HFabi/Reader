package com.example.lenovo.reader.di

import android.content.Context
import com.example.lenovo.reader.ReaderApplication
import com.example.lenovo.reader.controllers.DayNightController
import com.example.lenovo.reader.controllers.DayNightControllerImpl
import dagger.Binds
import dagger.Module
import javax.inject.Named
import javax.inject.Singleton

@Module()
abstract class ApplicationModule {

  @Binds
  @Singleton
  @Named("Application")
  abstract fun provideApplicationContext(application: ReaderApplication): Context


  @Singleton
  @Binds
  abstract fun provideDayNightController(dayNightControllerImpl: DayNightControllerImpl): DayNightController


}