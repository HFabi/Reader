package com.example.lenovo.reader.di

import android.content.Context
import com.example.lenovo.reader.ReaderApplication
import com.example.model.datastores.ArticlesDataStore
import com.example.model.datastores.ArticlesDataStoreImpl
import com.example.model.di.DataComponent
import com.example.model.di.DataModule
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module()
abstract class ApplicationModule {

    @Binds
    @Singleton
    abstract fun provideApplicationContext(application: ReaderApplication): Context

//    @Binds
//    @Singleton
//    abstract fun provideArticlesDataStore(articlesDataStoreImpl: ArticlesDataStoreImpl): ArticlesDataStore


    @Binds
    @Singleton
    abstract fun provideMyClass(mc: MyClass): MyClassInterface

}