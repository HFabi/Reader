package com.example.model.controllers

import com.example.model.Utils
import com.example.model.models.DownloadTask
import com.example.model.models.ImageParseResult
import timber.log.Timber
import javax.inject.Inject

/**
 * @author appcom interactive GmbH on 2019-05-09
 */
class HtmlParserImpl @Inject constructor(var utils: Utils) : HtmlParser {

  // find img tags
  private val regexImg = Regex("<img[^>]*src=[^>]*>")
  // find src attribute
  private val regexSrc = Regex("src=\"[^\"]*\"")

  private val allowedEndings = arrayOf("jpg", "png", "gif", "tiff", "svg", "webp")

  override fun replaceImagePaths(html: String, localPath: String): ImageParseResult {
    val downloadTaskList: MutableList<DownloadTask> = mutableListOf()
    val parsedHtml = regexImg.replace(html) { imgMatch ->
      val srcMatch = regexSrc.find(imgMatch.value)
      if (srcMatch != null) {
        val downloadUrl = srcMatch.value.substring(5, srcMatch.value.length - 1)
        val type = downloadUrl.substringAfterLast(".")
        if (allowedEndings.contains(type)) {
          val destinationPath = utils.generateUniqueFileName(type, localPath)
          downloadTaskList.add(DownloadTask(downloadUrl, destinationPath))
          imgMatch.value.replace(regexSrc, "src=\"file://$destinationPath\"")
        } else {
          // Ending not allowed
          imgMatch.value
        }
      } else {
        // No src match found
        imgMatch.value
      }
    }
    Timber.d(parsedHtml)
    return ImageParseResult(parsedHtml, downloadTaskList)
  }
}


