package com.example.lenovo.reader.services

import android.app.Notification
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.example.lenovo.reader.DOWNLOAD_ARTICLE_NAME
import com.example.model.controllers.DownloadController
import com.example.model.controllers.HtmlParser
import com.example.model.datasources.db.ArticlesDbDataSource
import com.example.model.datasources.web.ArticlesWebDataSource
import com.example.model.generateUniqueDirectoryName
import com.example.model.generateUniqueFileName
import com.example.model.models.Article
import com.example.model.models.Category
import com.example.model.models.DownloadTask
import dagger.android.DaggerIntentService
import io.reactivex.Completable
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Inject

/**
 * @author appcom interactive GmbH on 2019-08-01
 */
class DownloadArticleService @Inject constructor() : DaggerIntentService(DOWNLOAD_ARTICLE_NAME) {

  @Inject
  lateinit var articlesWebDataSource: ArticlesWebDataSource
  @Inject
  lateinit var articlesDbDataSource: ArticlesDbDataSource
  @Inject
  lateinit var htmlParser: HtmlParser
  @Inject
  lateinit var downloadController: DownloadController

  var downloadTaskList: MutableList<DownloadTask> = mutableListOf()

  override fun onHandleIntent(intent: Intent?) {
    if (intent != null && intent.hasExtra("url")) {
      val url: String = intent.getStringExtra("url")
      var categories: List<Category>? = null
      if (intent.hasExtra("categories")) {
        categories = intent.getParcelableArrayListExtra<Category>("categories")
      }
      loadArticle(url, categories)
      showServiceInForeground("Artikel laden");
    } else {
      Timber.d("Wrong parameter for Download Service Intent")
    }
  }

  override fun onCreate() {
    Timber.d("Download Article Service in onCreate")
    super.onCreate()
  }

  override fun onDestroy() {
    Timber.d("Download Article Service in onDestroy")
    super.onDestroy()
  }

  override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
    Timber.d("Download Article Service in onStartCommand")
    return super.onStartCommand(intent, flags, startId)
  }

  override fun onBind(intent: Intent?): IBinder? {
    Timber.d("Download Article Service in onBind")
    return super.onBind(intent)
  }

  private fun loadArticle(url: String, categories: List<Category>?) {
    articlesWebDataSource.getArticle(url)
      .flatMap(this::extractImages)
      .flatMapCompletable { article -> saveArticle(article, categories) }
      .andThen(downloadController.load(downloadTaskList))
      .subscribe(
        { downloadResult -> Log.d("", "DownloadResult " + downloadResult) }
      )
  }

  private fun saveArticle(article: Article, categories: List<Category>?): Completable {
    return if (categories != null) {
      articlesDbDataSource.addArticle(article, categories)
    } else {
      articlesDbDataSource.addArticle(article)
    }
  }

  private fun extractImages(article: Article): Single<Article> {
    return Single.fromCallable {
      article.localPath = downloadController.providePath(generateUniqueDirectoryName())
      // replace img with local paths
      val (parsedHtml, downloadTasks) = htmlParser.replaceImagePaths(article.content, article.localPath)
      downloadTaskList.addAll(downloadTasks)
      article.content = parsedHtml

      // handle lead image Url
      val leadImageUrl = article.leadImagePath
      article.leadImagePath = generateUniqueFileName(leadImageUrl, article.localPath)
      downloadTaskList.add(DownloadTask(leadImageUrl, article.leadImagePath))
      article
    }
  }

  private fun showServiceInForeground(recipientName: String) {
    val builder = Notification.Builder(this)
//            .setSmallIcon(R.drawable.ic_file_upload_white_18dp)
//            .setContentTitle(getString(R.string.start_upload_ticker))
//            .setTicker(getString(R.string.start_upload_ticker))
      .setContentText("Notification Content text")
//            .setProgress(100, 10, true)
    val notification = builder.build()
    startForeground(1, notification)
  }
}