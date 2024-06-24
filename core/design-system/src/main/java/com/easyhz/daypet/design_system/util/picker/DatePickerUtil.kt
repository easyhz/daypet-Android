package com.easyhz.daypet.design_system.util.picker

import java.time.LocalDate
import java.time.format.DateTimeFormatter

object DatePickerUtil {
    val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
    fun toString(year: Int, month: Int, dayOfMonth: Int): String =
        "$year.${month.toString().padStart(2, '0')}.${dayOfMonth.toString().padStart(2, '0')}"

    fun toLocalDate(date: String): LocalDate =
        LocalDate.parse(date, dateFormatter)
}