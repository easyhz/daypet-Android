package com.easyhz.daypet.home.contract

import com.easyhz.daypet.common.base.UiState
import com.easyhz.daypet.domain.model.event.Task
import com.easyhz.daypet.home.view.event.Event
import java.time.LocalDate

data class HomeState(
    val eventList: List<Event>,
    val taskList: List<Task>,
    val isLoading: Boolean,
    val selection: LocalDate
) : UiState() {
    companion object {
        fun init() = HomeState(
            eventList = emptyList(),
            taskList = emptyList(),
            isLoading = false,
            selection = LocalDate.now()
        )

    }
}
