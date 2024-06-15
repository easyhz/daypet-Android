package com.easyhz.daypet.home

import com.easyhz.daypet.common.base.BaseViewModel
import com.easyhz.daypet.home.contract.HomeIntent
import com.easyhz.daypet.home.contract.HomeSideEffect
import com.easyhz.daypet.home.contract.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(

): BaseViewModel<HomeState, HomeIntent, HomeSideEffect>(
    initialState = HomeState.init()
) {
    override fun handleIntent(intent: HomeIntent) {
        when(intent) {
            is HomeIntent.ChangeDate -> { changeDate(intent.clickedDay) }
            is HomeIntent.ClickArchive -> { }
            is HomeIntent.ClickTask -> { }
            is HomeIntent.ShowMonthCalendar -> { showMonthCalendar() }
            is HomeIntent.HideMonthCalendar -> { hideMonthCalendar() }
        }
    }

    private fun changeDate(clickedDay: LocalDate) {
        if (uiState.value.selection != clickedDay) {
            reduce { copy(selection = clickedDay) }
        }
    }

    private fun showMonthCalendar() {
        reduce { copy(showMonthCalendar = true) }
    }

    private fun hideMonthCalendar() {
        reduce { copy(showMonthCalendar = false) }
    }
}