package com.example.model

import java.util.Date

/**
 * @author appcom interactive GmbH on 2019-05-09
 */

fun generateUniqueFileName(url: String, localPath: String): String {
  val type = url.substringAfterLast(".")
  val date = Date().time.toString()
  return "$localPath/$date.$type"
}

fun generateUniqueDirectoryName(): String {
  // domain uebergeben?
//  val type = domain.substringBefore(".")
  return Date().time.toString()
}