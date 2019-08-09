package com.example.lenovo.reader.di

import com.example.lenovo.reader.ReaderApplication
import com.example.model.di.DataModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Component(
    modules = [AndroidInjectionModule::class, ApplicationModule::class, ActivityBuilder::class, ServiceBuilder::class, DataModule::class]
)
@Singleton
interface ApplicationComponent : AndroidInjector<ReaderApplication> {

  @Component.Builder
  abstract class Builder : AndroidInjector.Builder<ReaderApplication>()

}