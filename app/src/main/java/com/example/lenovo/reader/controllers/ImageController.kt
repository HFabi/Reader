package com.example.lenovo.reader.controllers

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import android.util.Log
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import timber.log.Timber
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.net.URL
import java.util.Date
import javax.inject.Inject

/**
 * @author appcom interactive GmbH on 2019-05-02
 */
class ImageController @Inject constructor(var context: Context) {

  fun downloadAndSaveImage(albumName: String, data: List<Pair<String, String>>): Completable {
    return Observable.fromIterable(data)
      .flatMapCompletable { (url, localPath) -> downloadAndSaveImage(url, localPath) }
  }

  //  fun d(albumName: String, data: List<Pair<String, String>>): Completable {
//    return Observable.fromIterable(data)
//      .map { (url, localPath) -> downloadAndSaveImage(url,localPath) }
//      .onE
//      .toList()
//  }

  fun testErrorHandling(data: List<String>): Completable {
    return Observable.fromIterable(data)
      .flatMapCompletable { operation() }
  }

  fun testsdfErrorHandling(data: List<String>): Completable {
    return Observable.fromIterable(data)
      .map { operation() }
      .onErrorReturn { Completable.complete() }
      .toList()
      .flatMapCompletable { list -> Completable.complete() }
  }

//  fun testErrorHandling(data: List<String>): Completable {
//    return Observable.fromIterable(data)
//      .map { operation() }
//      .onErrorReturn { Completable.complete() }
//      .toList()
//      .flatMapCompletable { list -> Completable.complete() }
//  }

  fun operation(): Completable {
    Log.d("TTT", "doing operation" + Date().time.toString())
    return Completable.error(Throwable("MY Error"))
  }

//  fun testErrorHandling(data: List<String>): Completable {
//    return Observable.fromIterable(data)
//      .map { operation() }
//      .onErrorReturn { Completable.complete()}
//      .toList()
//      .flatMapCompletable { list -> Completable.complete() }
//  }

  // provide path
  fun downloadAndSaveImage(imagePath: String, url: String): Completable {
    return Completable.create { emitter ->
      if (!isValidUrl(url)) {
        emitter.onError(Throwable("Url is not Valid"))
        return@create
      }
      if (isExternalStorageWritable()) {
        emitter.onError(Throwable("No access to storage"))
        return@create
      }
      val file = File(imagePath)
      val bitmap = downloadBitmap(url)
      if (bitmap == null) {
        emitter.onError(Throwable("Download not successful"))
      } else {
        if (saveImageToFile(bitmap, file)) {
          emitter.onComplete()
        } else {
          emitter.onError(Throwable("Could not save Image"))
        }
      }
    }
  }

  // get path
  fun downloadAndSaveImage(albumName: String, imageName: String, url: String): Single<String> {
    return Single.create { emitter ->
      if (!isValidUrl(url)) {
        emitter.onError(Throwable("Url is not Valid"))
        return@create
      }
      if (isExternalStorageWritable()) {
        emitter.onError(Throwable("No access to storage"))
        return@create
      }
      val albumDir = getPrivateAlbumStorageDir(albumName)
      if (albumDir != null) {
        val file = File(albumDir.absolutePath, imageName)
        val bitmap = downloadBitmap(url)
        if (bitmap == null) {
          emitter.onError(Throwable("Download not successful"))
        } else {
          if (saveImageToFile(bitmap, file)) {
            emitter.onSuccess(file.absolutePath)
          } else {
            emitter.onError(Throwable("Could not save Image"))
          }
        }
      }
    }
  }

  fun deleteAlbum(albumName: String): Completable {
    return Completable.create { emitter ->
      if (isExternalStorageWritable()) {
        emitter.onError(Throwable("No access to storage"))
        return@create
      }
      val file = getPrivateAlbumStorageDir(albumName, false)
      if (file == null) {
        // There was no file with this name
        emitter.onComplete()
      } else {
        if (file.delete()) emitter.onComplete() else emitter.onError(Throwable("File could not be deleted"))
      }
    }
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

  private fun saveImageToFile(bitmap: Bitmap, file: File): Boolean {
    var stream: FileOutputStream? = null
    var savedSuccessfully: Boolean = true
    try {
      Timber.d(String.format("Save image %s", file.name))
      stream = FileOutputStream(file)
      bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
      stream.flush()
    } catch (e: Exception) {
      savedSuccessfully = false
      e.printStackTrace()
    } finally {
      stream?.close()
    }
    return savedSuccessfully
  }

  private fun getPrivateAlbumStorageDir(albumName: String, createIfNotExists: Boolean = true): File? {
    val file = File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), albumName)
    if (!file.exists() && createIfNotExists) {
      Timber.d(String.format("Create album %s", albumName))
      file.mkdirs()
    }
    return file
  }

  private fun isValidUrl(url: String): Boolean {
    // TODO: Implement url validation -> should point to images (png, jpeg, gif, ..) only
    return true
  }

  private fun isExternalStorageWritable(): Boolean = Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED

  private fun isExternalStorageReadable(): Boolean {
    return Environment.getExternalStorageState() in
        setOf(Environment.MEDIA_MOUNTED, Environment.MEDIA_MOUNTED_READ_ONLY)
  }
}