package com.example.lenovo.reader.di

import android.content.Context
import com.example.lenovo.reader.ReaderApplication
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class ApplicationModule {

    @Binds
    @Singleton
    abstract fun provideApplicationContext(application: ReaderApplication): Context

}