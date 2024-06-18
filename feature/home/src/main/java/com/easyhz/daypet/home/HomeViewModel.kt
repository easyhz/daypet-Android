package com.easyhz.daypet.home

import androidx.lifecycle.viewModelScope
import com.easyhz.daypet.common.base.BaseViewModel
import com.easyhz.daypet.domain.param.memory.MemoryParam
import com.easyhz.daypet.domain.param.todo.TodoParam
import com.easyhz.daypet.domain.usecase.memory.FetchMemoriesUseCase
import com.easyhz.daypet.domain.usecase.todo.FetchTodosUseCase
import com.easyhz.daypet.home.contract.HomeIntent
import com.easyhz.daypet.home.contract.HomeSideEffect
import com.easyhz.daypet.home.contract.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchMemoriesUseCase: FetchMemoriesUseCase,
    private val fetchTodosUseCase: FetchTodosUseCase,
): BaseViewModel<HomeState, HomeIntent, HomeSideEffect>(
    initialState = HomeState.init()
) {
    override fun handleIntent(intent: HomeIntent) {
        when(intent) {
            is HomeIntent.ChangeDate -> { changeDate(intent.clickedDay) }
            is HomeIntent.ClickMemory -> { }
            is HomeIntent.ClickTodo -> { }
            is HomeIntent.ShowMonthCalendar -> { showMonthCalendar() }
            is HomeIntent.HideMonthCalendar -> { hideMonthCalendar() }
            is HomeIntent.ClickMonthCalendarDay -> { onClickMonthCalendarDay(intent.localDate) }
        }
    }
    init {
        fetchMemoires(uiState.value.selection)
        fetchTodos(uiState.value.selection)
    }

    private fun fetchMemoires(selection: LocalDate) = viewModelScope.launch {
        val param = MemoryParam(startDate = selection, endDate = selection)
        fetchMemoriesUseCase.invoke(param)
            .onSuccess {
                reduce { copy(selection = selection, memoryList = it) }
            }.onFailure {
                // TODO: Fail 처리 스낵바?
            }
    }

    private fun fetchTodos(selection: LocalDate) = viewModelScope.launch {
        val param = TodoParam(startDate = selection, endDate = selection)
        fetchTodosUseCase.invoke(param)
            .onSuccess {
                reduce { copy(selection = selection, todoList = it) }
            }.onFailure {
                // TODO: Fail 처리
            }
    }

    private fun changeDate(clickedDay: LocalDate) {
        if (uiState.value.selection != clickedDay) {
            fetchMemoires(clickedDay)
            fetchTodos(clickedDay)
        }
    }

    private fun showMonthCalendar() {
        reduce { copy(showMonthCalendar = true) }
    }

    private fun hideMonthCalendar() {
        reduce { copy(showMonthCalendar = false) }
    }

    private fun onClickMonthCalendarDay(localDate: LocalDate) {
        fetchMemoires(localDate)
        fetchTodos(localDate)
        reduce { copy(showMonthCalendar = false, selection = localDate) }
        postSideEffect { HomeSideEffect.ChangeWeekCalendar(localDate) }
    }
}