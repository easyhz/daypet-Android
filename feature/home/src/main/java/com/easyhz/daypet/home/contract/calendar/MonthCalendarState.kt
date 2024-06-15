package com.easyhz.daypet.home.contract.calendar

import com.easyhz.daypet.common.base.UiState
import java.time.LocalDate

data class MonthCalendarState(
    val selection: LocalDate,
) : UiState() {
    companion object {
        fun init() = MonthCalendarState(
            selection = LocalDate.now(),
        )
    }
}
