package com.example.lenovo.reader.controllers

import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import androidx.appcompat.app.AppCompatDelegate
import com.example.model.datastores.UserPreferencesDataStore
import com.example.model.schedule
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

/**
 * @author appcom interactive GmbH on 2019-07-26
 */
class DayNightControllerImpl @Inject constructor() : DayNightController {

  @Inject
  lateinit var userPreferencesDataStore: UserPreferencesDataStore

  companion object {
    val DAYNIGHT_LIGHT: Int = 0
    val DAYNIGHT_DARK: Int = 1
    val DAYNIGHT_AUTO: Int = 2
  }

  var currentDayNightMode = DAYNIGHT_AUTO

  override fun applyPreferredDayNightMode(): Completable {
    return loadPreferredDayNightMode()
      .flatMapCompletable { mode ->
        Completable.fromAction {
          currentDayNightMode = mode
          AppCompatDelegate.setDefaultNightMode(
            defaultModeFromMode(mode)
          )
        }
      }
  }

  override fun applyDayNightMode(selectedMode: Int): Completable {
    if (selectedMode == DAYNIGHT_LIGHT || selectedMode == DAYNIGHT_DARK || selectedMode == DAYNIGHT_AUTO) {
      return userPreferencesDataStore.setDayNightMode(selectedMode)
        .schedule()
        .andThen(Completable.fromAction {
          currentDayNightMode = selectedMode
          AppCompatDelegate.setDefaultNightMode(defaultModeFromMode(selectedMode))
        })
    } else {
      return Completable.error(Throwable("Mode not valid"))
    }
  }

  override fun loadPreferredDayNightMode(): Single<Int> {
    return userPreferencesDataStore.getDayNightMode()
  }

  private fun defaultModeFromMode(index: Int): Int {
    return when (index) {
      DAYNIGHT_LIGHT -> AppCompatDelegate.MODE_NIGHT_NO
      DAYNIGHT_DARK -> AppCompatDelegate.MODE_NIGHT_YES
      else -> if (VERSION.SDK_INT >= VERSION_CODES.P) AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM else AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY
    }
  }
}