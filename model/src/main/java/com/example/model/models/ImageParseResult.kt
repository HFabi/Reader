package com.example.model.models

/**
 * @author appcom interactive GmbH on 2019-05-09
 */
data class ImageParseResult(
  val parsedHtml: String,
  val downloadTasks: List<DownloadTask>
)
