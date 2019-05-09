package com.example.model.controllers

import com.example.model.models.ImageParseResult

/**
 * @author appcom interactive GmbH on 2019-05-09
 */
interface HtmlParser {
  fun replaceImagePaths(html: String, localPath: String): ImageParseResult
}