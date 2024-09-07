package com.easyhz.daypet.home.contract.upload

import com.easyhz.daypet.common.base.UiIntent
import com.easyhz.daypet.design_system.util.color.TodoColor
import java.time.LocalDate

sealed class UploadTodoIntent: UiIntent() {
    data class ChangeDate(val date: LocalDate): UploadTodoIntent()
    data class ChangeColor(val color: TodoColor): UploadTodoIntent()
    data class ChangeText(val text: String): UploadTodoIntent()
    data class ChangeDateExpanded(val expanded: Boolean): UploadTodoIntent()
}