package com.example.model.controllers

import com.example.model.models.DownloadResult
import com.example.model.models.DownloadTask
import io.reactivex.Observable

/**
 * @author appcom interactive GmbH on 2019-05-03
 */
interface DownloadController {

  fun load(data: Collection<DownloadTask>): Observable<DownloadResult>

  fun providePath(album: String): String

}