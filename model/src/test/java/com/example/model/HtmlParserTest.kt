package com.example.model

import com.example.model.controllers.HtmlParser
import com.example.model.controllers.HtmlParserImpl
import com.example.model.models.DownloadTask
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito

/**
 * @author appcom interactive GmbH on 2019-05-16
 */
@RunWith(Parameterized::class)
class HtmlParserTest(
  private val inputHtml: String,
  private val inputFileName: String,
  private val expectedHtml: String,
  private val expectedDownloadTasks: List<DownloadTask>
) {

  private val inputPath = "new/path/to"
  lateinit var htmlParser: HtmlParser

  companion object {
    @JvmStatic
    @Parameterized.Parameters
    fun html() = listOf(
      arrayOf(
        "<!DOCTYPE html><html><img src=\"https://postlight.com/wp-content/uploads/2016/10/03-building-awesome-cms-0.png\"></html>",
        "image.png",
        "<!DOCTYPE html><html><img src=\"file://new/path/to/image.png\"></html>",
        listOf(
          DownloadTask(
            "https://postlight.com/wp-content/uploads/2016/10/03-building-awesome-cms-0.png",
            "new/path/to/image.png"
          )
        )
      ),
      arrayOf(
        "<!DOCTYPE html><html><img alt=\"src\" src=\"www.test.de/image.gif\"></html>",
        "image.gif",
        "<!DOCTYPE html><html><img alt=\"src\" src=\"file://new/path/to/image.gif\"></html>",
        listOf(DownloadTask("www.test.de/image.gif", "new/path/to/image.gif"))
      ),
      arrayOf(
        "<!DOCTYPE html><img src=\"https://test.img1.png\"><img src=\"https://test.img2.png\"></html>",
        "image.png",
        "<!DOCTYPE html><img src=\"file://new/path/to/image.png\"><img src=\"file://new/path/to/image.png\"></html>",
        listOf(
          DownloadTask("https://test.img1.png", "new/path/to/image.png"),
          DownloadTask("https://test.img2.png", "new/path/to/image.png")
        )
      )
    )
  }

  /**
   * Runs before all tests.
   * - mock Utils class
   * - initialize html-parser
   */
  @Before
  fun setUp() {
    val utils = Mockito.mock(Utils::class.java)
    Mockito.`when`(utils.generateUniqueFileName(anyString(), anyString())).thenReturn("$inputPath/$inputFileName")
    htmlParser = HtmlParserImpl(utils)
  }

  /**
   *
   */
  @Test
  fun testParsedHtml() {
    val imageParseResult = htmlParser.replaceImagePaths(inputHtml, inputPath)
    Assert.assertEquals(expectedHtml, imageParseResult.parsedHtml)
  }

  /**
   *
   */
  @Test
  fun testDownloadTaskList() {
    val imageParseResult = htmlParser.replaceImagePaths(inputHtml, inputPath)
    val downloadTasks = imageParseResult.downloadTasks
    for (i in 0 until expectedDownloadTasks.size) {
      Assert.assertEquals(expectedDownloadTasks[i].downloadUrl, downloadTasks[i].downloadUrl)
      Assert.assertEquals(expectedDownloadTasks[i].destinationPath, downloadTasks[i].destinationPath)
    }
  }
}
