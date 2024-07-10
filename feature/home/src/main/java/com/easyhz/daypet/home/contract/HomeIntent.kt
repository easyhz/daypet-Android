package com.easyhz.daypet.home.contract

import com.easyhz.daypet.common.base.UiIntent
import com.easyhz.daypet.domain.model.memory.Memory
import com.easyhz.daypet.domain.model.todo.Todo
import com.easyhz.daypet.home.view.fab.SubMenu
import java.time.LocalDate

sealed class HomeIntent: UiIntent() {
    data class InitScreen(val groupId: String): HomeIntent()
    data class ChangeDate(val clickedDay: LocalDate): HomeIntent()
    data class ChangeDateOnMonth(val clickedDay: LocalDate): HomeIntent()
    data class ClickMemory(val memory: Memory): HomeIntent()
    data class ClickTodo(val todo: Todo): HomeIntent()
    data object ShowMonthCalendar: HomeIntent()
    data object HideMonthCalendar: HomeIntent()
    data class ClickMonthCalendarDay(val localDate: LocalDate): HomeIntent()
    data class ClickFabSubMenu(val subMenu: SubMenu): HomeIntent()
    data class ScrollWeek(val firstDate: LocalDate, val lastDate: LocalDate): HomeIntent()
    data class ScrollMonth(val firstDate: LocalDate, val lastDate: LocalDate): HomeIntent()
}