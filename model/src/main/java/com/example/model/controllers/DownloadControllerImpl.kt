package com.example.model.controllers

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.model.models.DownloadResult
import com.example.model.models.DownloadTask
import io.reactivex.Emitter
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.SingleOnSubscribe

import timber.log.Timber
import java.io.File
import java.io.InputStream
import java.net.URL
import javax.inject.Inject
import javax.inject.Named

/**
 * @author appcom interactive GmbH on 2019-05-03
 */
class DownloadControllerImpl @Inject constructor(@Named("Application") var context: Context) : DownloadController {

  @Inject
  lateinit var storageController: StorageController

  override fun downloadAll(data: Collection<DownloadTask>): Observable<DownloadResult> {
    return Observable.fromIterable(data)
      .flatMapSingle { (url, localPath) -> download(url, localPath) }
  }

  override fun download(url: String, imagePath: String): Single<DownloadResult> {
    val a: Single<Bitmap> = Single.create { emitter ->
      if (!isValidUrl(url)) {
        emitter.onError(Throwable("Url is not Valid"))
        return@create
      }
      val bitmap = downloadBitmap(url)
      if (bitmap == null) {
        emitter.onError(Throwable("Download not successful"))
      } else {
        emitter.onSuccess(bitmap)
      }
    }
    return a.flatMap { bitmap -> storageController.writeImageToFile(bitmap, File(imagePath))  }
      .map { b -> DownloadResult() }
  }

  private fun downloadBitmap(url: String): Bitmap? {
    var inputStream: InputStream? = null
    var bitmap: Bitmap? = null
    try {
      Timber.d(String.format("Download image from %s", url))
      inputStream = URL(url).openStream()
      bitmap = BitmapFactory.decodeStream(inputStream)
    } catch (e: Exception) {
      e.printStackTrace()
    } finally {
      inputStream?.close()
    }
    return bitmap
  }

  private fun isValidUrl(url: String): Boolean {
    // TODO: Implement url validation -> should point to images (png, jpeg, gif, ..) only
    return true
  }

}


//  override fun download(url: String, imagePath: String): Single<DownloadResult> {
//    return Single.create { emitter ->
//      if (!isValidUrl(url)) {
//        emitter.onError(Throwable("Url is not Valid"))
//        return@create
//      }
//      if (!isExternalStorageWritable()) {
//        emitter.onError(Throwable("No access to storage"))
//        return@create
//      }
//      val file = File(imagePath)
//      val bitmap = downloadBitmap(url)
//      if (bitmap == null) {
//        emitter.onError(Throwable("Download not successful"))
//      } else {
//        if (saveImageToFile(bitmap, file)) {
//          emitter.onSuccess(DownloadResult())
//        } else {
//          emitter.onError(Throwable("Could not save Image"))
//        }
//      }
//    }
//  }

//
//  override fun download(url: String, imagePath: String): Single<DownloadResult> {
//    return Single.create { emitter ->
//      if (!isValidUrl(url)) {
//        emitter.onError(Throwable("Url is not Valid"))
//        return@create
//      }
////      if (!isExternalStorageWritable()) {
////        emitter.onError(Throwable("No access to storage"))
////        return@create
////      }
//      val bitmap = downloadBitmap(url)
//      if (bitmap == null) {
//        emitter.onError(Throwable("Download not successful"))
//      } else {
//        if (saveImageToFile(bitmap, File(imagePath))) {
//          emitter.onSuccess(DownloadResult())
//        } else {
//          emitter.onError(Throwable("Could not save Image"))
//        }
//      }
//    }
//  }

//private val quality: Int = 100
//
//override fun providePath(album: String): String {
//  return getPrivateAlbumStorageDir(album)?.absolutePath ?: ""
//}
//
//override fun download(data: Collection<DownloadTask>): Observable<DownloadResult> {
//  return Observable.fromIterable(data)
//    .flatMapSingle { (url, localPath) -> downloadAndSaveImage(url, localPath) }
//}
//
//override fun deleteAlbum(albumName: String): Completable {
//  return Completable.create { emitter ->
//    val file = File(albumName)
//    if (file.exists()) {
//      deleteDirectoryWithContent(file)
//    }
//    emitter.onComplete()
//  }
//}
//
//override fun readImage(path: String): Single<Bitmap> {
//  return Single.create{emitter ->
//    val options = BitmapFactory.Options()
//    options.inSampleSize = 8
//    val bitmap = BitmapFactory.decodeFile(path, options)
//    emitter.onSuccess(bitmap)
//  }
//}
//
//private fun deleteDirectoryWithContent(parentFile: File) {
//  if (parentFile.isDirectory) {
//    parentFile.listFiles()?.let { filesInDirectory ->
//      for (childFile in filesInDirectory) {
//        deleteDirectoryWithContent(childFile)
//      }
//    }
//  }
//  parentFile.delete()
//}
//
//private fun downloadAndSaveImage(url: String, imagePath: String): Single<DownloadResult> {
//  return Single.create { emitter ->
//    if (!isValidUrl(url)) {
//      emitter.onError(Throwable("Url is not Valid"))
//      return@create
//    }
//    if (!isExternalStorageWritable()) {
//      emitter.onError(Throwable("No access to storage"))
//      return@create
//    }
//    val file = File(imagePath)
//    val bitmap = downloadBitmap(url)
//    if (bitmap == null) {
//      emitter.onError(Throwable("Download not successful"))
//    } else {
//      if (saveImageToFile(bitmap, file)) {
//        emitter.onSuccess(DownloadResult())
//      } else {
//        emitter.onError(Throwable("Could not save Image"))
//      }
//    }
//  }
//}
//
//private fun downloadBitmap(url: String): Bitmap? {
//  var inputStream: InputStream? = null
//  var bitmap: Bitmap? = null
//  try {
//    Timber.d(String.format("Download image from %s", url))
//    inputStream = URL(url).openStream()
//    bitmap = BitmapFactory.decodeStream(inputStream)
//  } catch (e: Exception) {
//    e.printStackTrace()
//  } finally {
//    inputStream?.close()
//  }
//  return bitmap
//}
//
//private fun saveImageToFile(bitmap: Bitmap, file: File): Boolean {
//  var stream: FileOutputStream? = null
//  var savedSuccessfully: Boolean = true
//  try {
//    Timber.d(String.format("Save image %s", file.name))
//    stream = FileOutputStream(file)
//    bitmap.compress(Bitmap.CompressFormat.JPEG, quality, stream)
//    stream.flush()
//  } catch (e: Exception) {
//    savedSuccessfully = false
//    e.printStackTrace()
//  } finally {
//    stream?.close()
//  }
//  return savedSuccessfully
//}
//
//private fun getPrivateAlbumStorageDir(albumName: String, createIfNotExists: Boolean = true): File? {
//  val file = File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), albumName)
//  if (!file.exists() && createIfNotExists) {
//    Timber.d(String.format("Create album %s", albumName))
//    file.mkdirs()
//  }
//  return file
//}
//
//private fun isValidUrl(url: String): Boolean {
//  // TODO: Implement url validation -> should point to images (png, jpeg, gif, ..) only
//  return true
//}
//
//private fun isExternalStorageWritable(): Boolean = Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
//
//private fun isExternalStorageReadable(): Boolean {
//  return Environment.getExternalStorageState() in
//      setOf(Environment.MEDIA_MOUNTED, Environment.MEDIA_MOUNTED_READ_ONLY)
//}