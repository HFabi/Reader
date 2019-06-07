package com.example.model.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.model.BuildConfig
import com.example.model.api.baseUrl
import com.example.model.api.databaseName
import com.example.model.controllers.ArticleController
import com.example.model.controllers.ArticleControllerImpl
import com.example.model.controllers.DownloadController
import com.example.model.controllers.DownloadControllerImpl
import com.example.model.controllers.HtmlParser
import com.example.model.controllers.HtmlParserImpl
import com.example.model.datasources.db.ArticleCategoryDao
import com.example.model.datasources.db.ArticleDao
import com.example.model.datasources.db.ArticlesDbDataSource
import com.example.model.datasources.db.ArticlesDbDataSourceImpl
import com.example.model.datasources.db.CategoryDao
import com.example.model.datasources.db.ReaderDatabase
import com.example.model.datasources.local.SharedPreferencesDataSource
import com.example.model.datasources.local.SharedPreferencesDataSourceImpl
import com.example.model.datasources.web.ArticlesWebDataSource
import com.example.model.datasources.web.ArticlesWebDataSourceImpl
import com.example.model.datastores.ArticlesDataStore
import com.example.model.datastores.ArticlesDataStoreImpl
import com.example.model.transformers.ArticleTransformer
import com.example.model.transformers.ArticleTransformerImpl
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber
import javax.inject.Named
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

  @Provides
  @Singleton
  fun provideArticleDao(readerDatabase: ReaderDatabase): ArticleDao {
    return readerDatabase.articleDao()
  }

  @Provides
  @Singleton
  fun provideCategoryDao(readerDatabase: ReaderDatabase): CategoryDao {
    return readerDatabase.categoryDao()
  }

  @Provides
  @Singleton
  fun provideArticleCategoryDao(readerDatabase: ReaderDatabase): ArticleCategoryDao {
    return readerDatabase.articleCategoryDao()
  }

  @Provides
  @Singleton
  fun provideArticleDatabase(@Named("Application") context: Context): ReaderDatabase {
    return Room.databaseBuilder(context, ReaderDatabase::class.java, databaseName).build()
  }

  @Provides
  @Singleton
  fun provideDownloadController(downloadControllerImpl: DownloadControllerImpl): DownloadController {
    return downloadControllerImpl
  }

  @Provides
  @Singleton
  fun provideArticleParser(articleParserImpl: ArticleControllerImpl): ArticleController {
    return articleParserImpl
  }

  @Provides
  @Singleton
  fun provideArticleTransformer(articleTransformerImpl: ArticleTransformerImpl): ArticleTransformer {
    return articleTransformerImpl
  }

  @Provides
  @Singleton
  fun provideArticlesWebDataSource(articlesWebDataSourceImpl: ArticlesWebDataSourceImpl): ArticlesWebDataSource {
    return articlesWebDataSourceImpl
  }

  @Provides
  @Singleton
  fun provideArticlesDbDataSource(articlesDbDataSourceImpl: ArticlesDbDataSourceImpl): ArticlesDbDataSource {
    return articlesDbDataSourceImpl
  }

  @Provides
  @Singleton
  fun provideHtmlParser(htmlParserImpl: HtmlParserImpl): HtmlParser {
    return htmlParserImpl
  }

  @Provides
  @Singleton
  fun provideSharedPreferences(@Named("Application") context: Context): SharedPreferences {
    return context.getSharedPreferences("com.example.reader", Context.MODE_PRIVATE)
  }

  @Provides
  @Singleton
  fun provideSharedPreferencesDataSource(sharedPreferencesDataSourceImpl: SharedPreferencesDataSourceImpl): SharedPreferencesDataSource {
    return sharedPreferencesDataSourceImpl
  }
}