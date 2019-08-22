package com.example.lenovo.reader.controllers

import com.example.model.controllers.DownloadController
import javax.inject.Inject

class ExtractImageColorControllerImpl @Inject constructor() : ExtractImageColorController {

  @Inject
  lateinit var downloadController: DownloadController

//  fun extractColorFromImage(path: String): Single<Int> {
//    val default = 0x000000
//    return downloadController.readImage(path)
//      .map { bitmap ->
//        val palette = Palette.Builder(bitmap).apply {
//          maximumColorCount(6)
//        }.generate()
//        val vibrant = palette.getVibrantColor(default)
//        val vibrantLight = palette.getLightVibrantColor(default)
//        val vibrantDark = palette.getDarkVibrantColor(default)
//        Log.d("PALETTE","$vibrant $vibrantLight $vibrantDark")
//        vibrant
//      }
//  }
}
