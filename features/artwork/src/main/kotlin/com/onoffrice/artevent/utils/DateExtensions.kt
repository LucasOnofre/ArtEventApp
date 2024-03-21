package com.onoffrice.artevent.utils

import com.onoffrice.domain.model.ArtEvent
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import java.util.Locale

const val DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss-HH:mm"
const val HOUR_PATTER = "HH:mm"

fun String.toDate(): Date {
    val dateFormat = SimpleDateFormat(DATE_PATTERN, Locale.getDefault())
    val calendar = Calendar.getInstance()
    try {
        calendar.time = dateFormat.parse(this) ?: Date()
    } catch (e: Exception) {
        calendar.time = Date()
    }
    return calendar.time
}

fun String.getTime(): LocalTime {

    val formatter = DateTimeFormatter.ofPattern("HH:mm")

    // Parse the time string into a LocalTime object
    val time: LocalTime = LocalTime.parse(this, formatter)

    return time

}


//"start_date": "2018-06-28T00:00:00-05:00",
//"end_date": "2018-06-28T00:00:00-05:00",
//"start_time": "10:30",
//"end_time": "20:00",

