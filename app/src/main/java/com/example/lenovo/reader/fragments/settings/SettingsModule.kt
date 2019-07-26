package com.example.lenovo.reader.fragments.settings

import com.example.lenovo.reader.annotations.FragmentScope
import dagger.Binds
import dagger.Module

@Module
abstract class SettingsModule {

  @Binds
  @FragmentScope
  abstract fun provideSettingsPresenter(settingsPresenterImpl: SettingsPresenterImpl): SettingsPresenter


  @Binds
  @FragmentScope
  abstract fun provideSettingsView(settingsFragment: SettingsFragment): SettingsView
}