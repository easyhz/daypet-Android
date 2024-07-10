package com.easyhz.daypet.design_system.util.picker

import java.time.LocalDate
import java.time.format.DateTimeFormatter

object DatePickerUtil {
    val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
    fun toString(year: Int, month: Int, dayOfMonth: Int): String {
        val date = LocalDate.of(year, month, dayOfMonth)
        return date.format(dateFormatter)
    }
    fun toLocalDate(date: String): LocalDate =
        LocalDate.parse(date, dateFormatter)
}