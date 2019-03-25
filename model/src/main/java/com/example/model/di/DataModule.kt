package com.example.model.di

import com.example.model.datastores.ArticlesDataStore
import com.example.model.datastores.ArticlesDataStoreImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class DataModule {

    @Binds
    @Singleton
    abstract fun provideArticlesDataStore(articlesDataStoreImpl: ArticlesDataStoreImpl): ArticlesDataStore

}