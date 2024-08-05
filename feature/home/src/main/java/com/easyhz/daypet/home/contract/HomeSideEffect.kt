package com.easyhz.daypet.home.contract

import com.easyhz.daypet.common.base.UiSideEffect
import java.time.LocalDate

sealed class HomeSideEffect: UiSideEffect() {
    data class ChangeWeekCalendar(val localDate: LocalDate): HomeSideEffect()
    data object NavigateToUploadMemory: HomeSideEffect()
    data class NavigateToMemoryDetail(val id: String, val title: String): HomeSideEffect()
}