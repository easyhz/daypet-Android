package com.easyhz.daypet.home.contract

import com.easyhz.daypet.common.base.UiState
import com.easyhz.daypet.domain.model.memory.Memory
import com.easyhz.daypet.domain.model.todo.Todo
import java.time.LocalDate

data class HomeState(
    val memoryList: List<Memory>,
    val todoList: List<Todo>,
    val isLoading: Boolean,
    val selection: LocalDate,
    val monthSelection: LocalDate,
    val showMonthCalendar: Boolean,
) : UiState() {
    companion object {
        fun init() = HomeState(
            memoryList = emptyList(),
            todoList = emptyList(),
            isLoading = false,
            selection = LocalDate.now(),
            monthSelection = LocalDate.now(),
            showMonthCalendar = false
        )

    }
}
