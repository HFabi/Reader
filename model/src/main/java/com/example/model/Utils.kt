package com.example.model

import java.util.Date
import javax.inject.Inject

/**
 * @author appcom interactive GmbH on 2019-05-16
 */
class Utils @Inject constructor() {

  fun generateUniqueFileName(type: String, localPath: String): String {
    val date = Date().time.toString()
    return "$localPath/$date.$type"
  }


  fun generateUniqueDirectoryName(): String {
    return Date().time.toString()
  }
}