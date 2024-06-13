package com.easyhz.daypet.home.contract

import com.easyhz.daypet.common.base.UiIntent
import com.easyhz.daypet.domain.model.event.Archive
import com.easyhz.daypet.domain.model.event.Task
import com.easyhz.daypet.home.view.event.Event
import java.time.LocalDate

sealed class HomeIntent: UiIntent() {
    data class ChangeDate(val clickedDay: LocalDate): HomeIntent()
    data class ArchiveClick(val archive: Archive): HomeIntent()
    data class TaskClick(val task: Task): HomeIntent()

}