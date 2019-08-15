package com.example.lenovo.reader.di

import com.example.lenovo.reader.annotations.ActivityScope
import com.example.lenovo.reader.controllers.ExtractImageColorController
import com.example.lenovo.reader.controllers.ExtractImageColorControllerImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class ControllerModule {

  @Binds
  @Singleton
  abstract fun provideExtractImageColorController(extractImageControllerImpl: ExtractImageColorControllerImpl): ExtractImageColorController
}
