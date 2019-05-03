package com.example.model.controllers

import com.example.model.models.Article
import com.example.model.models.DownloadTask
import java.util.Date

/**
 * @author appcom interactive GmbH on 2019-05-03
 */
class ArticleParserImpl(var article: Article, var localPath: String) : ArticleParser {

  // find img tags
  private val regexImg = Regex("<img[^>]*src=[^>]*>")
  // find src attribute
  private val regexSrc = Regex("src=\"[^\"]*\"")

  var downloadTaskList: MutableList<DownloadTask> =  mutableListOf()

  override fun parse(): Article {
    article.leadImageUrl = replaceLeadImagePaths()
    article.content = replaceImagePaths()
    return article
  }

  private fun replaceLeadImagePaths(): String {
    val leadImageUrl = article.leadImageUrl
    val destinationPath = localPath + "/" + generateUniqueFileName(leadImageUrl)
    downloadTaskList.add(DownloadTask(leadImageUrl, destinationPath))
    return destinationPath
  }

  private fun replaceImagePaths(): String {
    return regexImg.replace(article.content) { match ->
      regexSrc.find(match.value)?.value?.let { it ->
        val downloadUrl = it.substring(5, it.length - 1)
        val destinationPath = localPath + "/" + generateUniqueFileName(downloadUrl)
        downloadTaskList.add(DownloadTask(downloadUrl, destinationPath))
        match.value.replace(regexSrc, "src=\"$destinationPath\"")
      } ?: match.value
    }
  }

  private fun generateUniqueFileName(url: String): String {
    val type = url.substringAfterLast(".")
    val date = Date().time.toString()
    return "$date.$type"
  }
}