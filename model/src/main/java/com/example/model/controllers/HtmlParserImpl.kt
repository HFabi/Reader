package com.example.model.controllers

import android.util.Log
import com.example.model.generateUniqueFileName
import com.example.model.models.DownloadTask
import com.example.model.models.ImageParseResult
import timber.log.Timber
import javax.inject.Inject

/**
 * @author appcom interactive GmbH on 2019-05-09
 */
class HtmlParserImpl @Inject constructor() : HtmlParser {

  // find img tags
  private val regexImg = Regex("<img[^>]*src=[^>]*>")
  // find src attribute
  private val regexSrc = Regex("src=\"[^\"]*\"")

  override fun replaceImagePaths(html: String, localPath: String): ImageParseResult {
    val downloadTaskList: MutableList<DownloadTask> = mutableListOf()
    val parsedHtml = regexImg.replace(html) { match ->
      regexSrc.find(match.value)?.value?.let { it ->
        val downloadUrl = it.substring(5, it.length - 1)
        val destinationPath = generateUniqueFileName(downloadUrl, localPath)
        downloadTaskList.add(DownloadTask(downloadUrl, destinationPath))
        match.value.replace(regexSrc, "src=\"file://$destinationPath\"")
      } ?: match.value
    }
    Timber.d("AAAAAA"+parsedHtml)
    return ImageParseResult(parsedHtml, downloadTaskList)
  }
}


//  private fun replaceImagePaths(html: String, localPath: String): String {
//    return regexImg.replace(html) { match ->
//      regexSrc.find(match.value)?.value?.let { it ->
//        val downloadUrl = it.substring(5, it.length - 1)
//        val destinationPath = generateUniqueFileName(downloadUrl, localPath)
//        downloadTaskList.add(DownloadTask(downloadUrl, destinationPath))
//        match.value.replace(regexSrc, "src=\"$destinationPath\"")
//      } ?: match.value
//    }
//  }