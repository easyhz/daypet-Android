package com.easyhz.daypet.home

import com.easyhz.daypet.common.base.BaseViewModel
import com.easyhz.daypet.common.base.UiIntent
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
    override fun handleIntent(intent: UiIntent) {
        when(intent) {
            is HomeIntent.ChangeDate -> { onChangeDate(intent.clickedDay) }
            is HomeIntent.TaskClick -> { }
            is HomeIntent.EventClick -> { }
        }
    }

    private fun onChangeDate(clickedDay: LocalDate) {
        if (uiState.value.selection != clickedDay) {
            reduce { copy(selection = clickedDay) }
        }
    }
}