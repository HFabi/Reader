package com.example.model.datasources

import androidx.room.TypeConverter
import java.util.Date

/**
 * @author appcom interactive GmbH on 04.04.19
 */
class Converters {

  @TypeConverter
  fun toDate(value: Long?): Date? {
    return value?.let { Date(it) }
  }

  @TypeConverter
  fun toTimestamo(date: Date?): Long? {
    return date?.time?.toLong()
  }

}