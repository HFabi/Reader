package com.example.lenovo.reader.fragments.settings

import com.example.lenovo.reader.annotations.FragmentScope
import com.example.lenovo.reader.fragments.settings.interactors.GetStorageInfoInteractor
import com.example.lenovo.reader.fragments.settings.interactors.GetStorageInfoInteractorImpl
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

  @Binds
  @FragmentScope
  abstract fun provideGetStorageInfoInteractor(getStorageInfoInteractorImpl: GetStorageInfoInteractorImpl): GetStorageInfoInteractor

}