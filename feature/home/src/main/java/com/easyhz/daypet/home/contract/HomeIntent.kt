package com.easyhz.daypet.home.contract

import com.easyhz.daypet.common.base.UiIntent
import com.easyhz.daypet.domain.model.event.Archive
import com.easyhz.daypet.domain.model.event.Task
import java.time.LocalDate

internal sealed class HomeIntent: UiIntent() {
    data class ChangeDate(val clickedDay: LocalDate): HomeIntent()
    data class ClickArchive(val archive: Archive): HomeIntent()
    data class ClickTask(val task: Task): HomeIntent()

}