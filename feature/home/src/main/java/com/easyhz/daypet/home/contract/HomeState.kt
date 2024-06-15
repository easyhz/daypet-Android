package com.easyhz.daypet.home.contract

import com.easyhz.daypet.common.base.UiState
import com.easyhz.daypet.domain.model.event.Archive
import com.easyhz.daypet.domain.model.event.Task
import java.time.LocalDate

data class HomeState(
    val archiveList: List<Archive>,
    val taskList: List<Task>,
    val isLoading: Boolean,
    val selection: LocalDate,
    val monthSelection: LocalDate,
    val showMonthCalendar: Boolean,
) : UiState() {
    companion object {
        fun init() = HomeState(
            archiveList = emptyList(),
            taskList = emptyList(),
            isLoading = false,
            selection = LocalDate.now(),
            monthSelection = LocalDate.now(),
            showMonthCalendar = false
        )

    }
}
