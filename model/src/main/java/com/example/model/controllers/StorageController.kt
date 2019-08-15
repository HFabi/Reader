package com.example.model.controllers

import android.graphics.Bitmap
import com.example.model.models.StorageInfo
import io.reactivex.Completable
import io.reactivex.Single
import java.io.File

interface StorageController {

  fun providePath(album: String): String

  fun provideStorageInfo(): Single<StorageInfo>

  fun writeImageToFile(bitmap: Bitmap, file: File): Single<Boolean>

  fun deleteAlbum(albumName: String): Completable
}