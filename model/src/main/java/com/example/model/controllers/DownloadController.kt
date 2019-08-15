package com.example.model.controllers

import android.graphics.Bitmap
import com.example.model.models.DownloadResult
import com.example.model.models.DownloadTask
import com.example.model.models.StorageInfo
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

/**
 * @author appcom interactive GmbH on 2019-05-03
 */
interface DownloadController {

//  fun download(data: Collection<DownloadTask>): Observable<DownloadResult>
  fun downloadAll(data: Collection<DownloadTask>): Observable<DownloadResult>

  fun download(url: String, imagePath: String): Single<DownloadResult>
}