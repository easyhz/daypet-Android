package com.easyhz.daypet.home.contract

import com.easyhz.daypet.common.base.UiIntent
import com.easyhz.daypet.domain.model.memory.Memory
import com.easyhz.daypet.domain.model.todo.Todo
import java.time.LocalDate

sealed class HomeIntent: UiIntent() {
    data class ChangeDate(val clickedDay: LocalDate): HomeIntent()
    data class ClickMemory(val memory: Memory): HomeIntent()
    data class ClickTodo(val todo: Todo): HomeIntent()
    data object ShowMonthCalendar: HomeIntent()
    data object HideMonthCalendar: HomeIntent()
    data class ClickMonthCalendarDay(val localDate: LocalDate): HomeIntent()
}