package com.example.model.di

import com.example.model.BuildConfig
import com.example.model.api.baseUrl
import com.example.model.datastores.ArticlesDataStore
import com.example.model.datastores.ArticlesDataStoreImpl
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber
import javax.inject.Singleton

@Module
class DataModule {

  @Provides
  @Singleton
  fun provideArticlesDataStore(articlesDataStoreImpl: ArticlesDataStoreImpl): ArticlesDataStore {
    return articlesDataStoreImpl
  }

  @Provides
  @Singleton
  fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
      .client(okHttpClient)
      .baseUrl(baseUrl)
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .addConverterFactory(MoshiConverterFactory.create())
      .build()
  }

  @Provides
  @Singleton
  fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
      .apply {
        if (BuildConfig.DEBUG) {
          addInterceptor(HttpLoggingInterceptor { text -> Timber.d(text) }.setLevel(BODY))
        }
      }.build()
  }
}