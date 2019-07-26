package com.example.lenovo.reader.controllers

import io.reactivex.Completable
import io.reactivex.Single

/**
 * @author appcom interactive GmbH on 2019-07-26
 */
interface DayNightController {
  fun applyPreferredDayNightMode(): Completable
  fun applyDayNightMode(selectedMode: Int): Completable
  fun loadPreferredDayNightMode(): Single<Int>
}