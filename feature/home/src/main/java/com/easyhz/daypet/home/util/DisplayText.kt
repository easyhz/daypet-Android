package com.easyhz.daypet.home.util

import com.kizitonwose.calendar.core.Week
import java.time.DayOfWeek
import java.time.Month
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("d")
fun YearMonth.displayText(short: Boolean = false): String =
    "${year}년 ${month.displayText(short = short)}"


fun Month.displayText(short: Boolean = true): String {
    val style = if (short) TextStyle.SHORT else TextStyle.FULL
    return getDisplayName(style, Locale.KOREA)
}

fun DayOfWeek.displayText(uppercase: Boolean = false): String =
    getDisplayName(TextStyle.SHORT, Locale.KOREA).let { value ->
        if (uppercase) value.uppercase(Locale.KOREA) else value
    }


fun getWeekPageTitle(week: Week): String {
    val firstDate = week.days.first().date
    val lastDate = week.days.last().date

    return firstDate.month.displayText()
//    return when {
//        firstDate.yearMonth == lastDate.yearMonth -> firstDate.month.displayText()
//        firstDate.year == lastDate.year -> "${firstDate.month.displayText(short = false)} - ${lastDate.month.displayText()}"
//        else -> "${firstDate.yearMonth.displayText()} - ${lastDate.month.displayText()}"
//    }
}
