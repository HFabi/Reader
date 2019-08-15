package com.example.model.controllers

import android.content.Context
import android.graphics.Bitmap
import android.os.Environment
import android.os.StatFs
import android.util.Log
import com.example.model.models.StorageInfo
import io.reactivex.Completable
import io.reactivex.Single
import timber.log.Timber
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject
import javax.inject.Named

class StorageControllerImpl @Inject constructor(@Named("Application") var context: Context) : StorageController {

  private val imageQuality: Int = 100

  override fun providePath(album: String): String {
    return getPrivateAlbumStorageDir(album)?.absolutePath ?: ""
  }

  override fun writeImageToFile(bitmap: Bitmap, file: File): Single<Boolean> {
    return Single.create{emitter ->
      var stream: FileOutputStream? = null
      var savedSuccessfully: Boolean = true
      try {
        Timber.d(String.format("Save image %s", file.name))
        stream = FileOutputStream(file)
        bitmap.compress(Bitmap.CompressFormat.JPEG, imageQuality, stream)
        stream.flush()
      } catch (e: Exception) {
        savedSuccessfully = false
        e.printStackTrace()
      } finally {
        stream?.close()
      }
      emitter.onSuccess(savedSuccessfully)
    }
  }

//  fun readImage(path: String): Single<Bitmap> {
//    return Single.create { emitter ->
//      val options = BitmapFactory.Options()
//      options.inSampleSize = 8
//      val bitmap = BitmapFactory.decodeFile(path, options)
//      emitter.onSuccess(bitmap)
//    }
//  }

  override fun provideStorageInfo(): Single<StorageInfo> {
    return Single.create { emitter ->
      val totalSpace = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES).totalSpace
      val freeSpace = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES).freeSpace

      val path = Environment.getExternalStorageDirectory()
      val stat: StatFs = StatFs(path.path)

      val availableBytes =
        stat.availableBytes // number of bytes that are free on the file system and available to applications
      val totalBytes = stat.totalBytes // total number of bytes supported by the file system

      var s: String = ""
      s += "TotalSpace $totalSpace    freeSpace:$freeSpace   ${totalSpace - freeSpace}   availiableBytes:${availableBytes}    totalBytes:${totalBytes}"
      Log.d("STORAGE", s)
      // availiable Bytes passt zu freiem Speicherplatz auf dem Geraet
      emitter.onSuccess(StorageInfo(0, 0, true))
    }
  }

  /**
   * Delete the an article image album
   */
  override fun deleteAlbum(albumName: String): Completable {
    return Completable.create { emitter ->
      val file = File(albumName)
      if (file.exists()) {
        deleteDirectoryWithContent(file)
      }
      emitter.onComplete()
    }
  }

  private fun deleteDirectoryWithContent(parentFile: File) {
    if (parentFile.isDirectory) {
      parentFile.listFiles()?.let { filesInDirectory ->
        for (childFile in filesInDirectory) {
          deleteDirectoryWithContent(childFile)
        }
      }
    }
    parentFile.delete()
  }

  private fun getPrivateAlbumStorageDir(albumName: String, createIfNotExists: Boolean = true): File? {
    val file = File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), albumName)
    if (!file.exists() && createIfNotExists) {
      Timber.d(String.format("Create album %s", albumName))
      file.mkdirs()
    }
    return file
  }

  private fun isExternalStorageWritable(): Boolean = Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED

  private fun isExternalStorageReadable(): Boolean {
    return Environment.getExternalStorageState() in
        setOf(Environment.MEDIA_MOUNTED, Environment.MEDIA_MOUNTED_READ_ONLY)
  }

}