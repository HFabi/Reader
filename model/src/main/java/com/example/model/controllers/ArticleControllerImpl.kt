package com.example.model.controllers

import android.util.Log
import com.example.model.generateUniqueDirectoryName
import com.example.model.generateUniqueFileName
import com.example.model.models.Article
import com.example.model.models.DownloadTask
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Inject

/**
 * @author appcom interactive GmbH on 2019-05-03
 */
class ArticleControllerImpl @Inject constructor() : ArticleController {

  @Inject
  lateinit var htmlParser: HtmlParser
  @Inject
  lateinit var downloadController: DownloadController

  var downloadTaskList: MutableList<DownloadTask> = mutableListOf()

  override fun parse(article: Article): Single<Pair<Article, List<DownloadTask>>> {
    return Single.fromCallable {

      article.localPath = downloadController.providePath(generateUniqueDirectoryName())

      // replace img with local paths
      val (parsedHtml, downloadTasks) = htmlParser.replaceImagePaths(article.content, article.localPath)
      downloadTaskList.addAll(downloadTasks)
      article.content = parsedHtml

      // handle lead image Url
      article.leadImagePath = generateUniqueFileName(article.leadImageUrl, article.localPath)
      downloadTaskList.add(DownloadTask(article.leadImageUrl, article.leadImagePath))

      Pair(article, downloadTaskList)
    }
  }
}


