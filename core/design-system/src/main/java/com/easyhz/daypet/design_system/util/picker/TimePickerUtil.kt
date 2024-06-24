package com.easyhz.daypet.design_system.util.picker

import java.time.LocalTime
import java.time.format.DateTimeFormatter

object TimePickerUtil {
    val timeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("a hh:mm")
    fun toString(hour: Int, minute: Int): String {
        val time = LocalTime.of(hour, minute).withHour(hour).withMinute(minute)
        return timeFormatter.format(time)
    }

    fun toLocalTime(date: String): LocalTime =
        LocalTime.parse(date, timeFormatter)
}