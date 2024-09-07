package com.easyhz.daypet.home.contract.upload

import com.easyhz.daypet.common.base.UiState
import com.easyhz.daypet.design_system.util.color.TodoColor
import java.time.LocalDate

data class UploadTodoState(
    val todoText: String,
    val selectedColor: TodoColor,
    val selectedDate: LocalDate,
    val isDateExpanded: Boolean
): UiState() {
    companion object {
        fun init() = UploadTodoState(
            todoText = "",
            selectedColor = TodoColor.LIGHT_YELLOW,
            selectedDate = LocalDate.now(),
            isDateExpanded = false
        )
    }
}