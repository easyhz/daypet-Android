package com.easyhz.daypet.home.util

import com.kizitonwose.calendar.core.Week
import java.time.DayOfWeek
import java.time.Month
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("d")
internal fun YearMonth.displayText(short: Boolean = false): String =
    "${year}년 ${month.displayText(short = short)}"


internal fun Month.displayText(short: Boolean = true): String {
    val style = if (short) TextStyle.SHORT else TextStyle.FULL
    return getDisplayName(style, Locale.KOREA)
}

internal fun DayOfWeek.displayText(uppercase: Boolean = false): String =
    getDisplayName(TextStyle.SHORT, Locale.KOREA).let { value ->
        if (uppercase) value.uppercase(Locale.KOREA) else value
    }


internal fun getWeekPageTitle(week: Week): String =
    week.days.first().date.month.displayText()
