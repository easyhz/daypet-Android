package com.easyhz.daypet.home.contract.calendar

import com.easyhz.daypet.common.base.UiIntent
import java.time.LocalDate


sealed class MonthCalendarIntent: UiIntent() {
    data class ChangeDate(val clickedDay: LocalDate): MonthCalendarIntent()
}
