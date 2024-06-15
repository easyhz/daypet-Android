package com.easyhz.daypet.home.view.calendar

import com.easyhz.daypet.common.base.BaseViewModel
import com.easyhz.daypet.home.contract.calendar.MonthCalendarIntent
import com.easyhz.daypet.home.contract.calendar.MonthCalendarSideEffect
import com.easyhz.daypet.home.contract.calendar.MonthCalendarState
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class MonthCalendarViewModel @Inject constructor(

): BaseViewModel<MonthCalendarState, MonthCalendarIntent, MonthCalendarSideEffect>(
    initialState = MonthCalendarState.init()
) {
    override fun handleIntent(intent: MonthCalendarIntent) {
        when(intent) {
            is MonthCalendarIntent.ChangeDate -> {
                changeDate(intent.clickedDay)
            }
        }
    }

    private fun changeDate(clickedDay: LocalDate) {
        if (uiState.value.selection != clickedDay) {
            reduce { copy(selection = clickedDay) }
        }
    }
}