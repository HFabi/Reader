package com.example.lenovo.reader.services

import android.app.IntentService
import android.content.Intent
import android.os.IBinder
import com.example.lenovo.reader.DOWNLOAD_ARTICLE_NAME
import timber.log.Timber

/**
 * @author appcom interactive GmbH on 2019-08-01
 */
class DownloadArticleService : IntentService(DOWNLOAD_ARTICLE_NAME) {


  override fun onHandleIntent(intent: Intent?) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun onCreate() {
    Timber.d("Download Article Service in onCreate")
    super.onCreate()
  }

  override fun onDestroy() {
    Timber.d("Download Article Service in onDestroy")
    super.onDestroy()
  }

  override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
    Timber.d("Download Article Service in onStartCommand")
    return super.onStartCommand(intent, flags, startId)
  }

  override fun onBind(intent: Intent?): IBinder? {
    Timber.d("Download Article Service in onBind")
    return super.onBind(intent)
  }
}