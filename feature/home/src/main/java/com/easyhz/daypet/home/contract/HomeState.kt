package com.easyhz.daypet.home.contract

import com.easyhz.daypet.common.base.UiState
import com.easyhz.daypet.common.extension.toHashMap
import com.easyhz.daypet.domain.model.home.Thumbnail
import com.easyhz.daypet.domain.model.memory.Memory
import com.easyhz.daypet.domain.model.todo.Todo
import java.time.LocalDate

data class HomeState(
    val groupId: String,
    val thumbnail: Thumbnail,
    val memoryList: List<Memory>,
    val todoList: List<Todo>,
    val isLoading: Boolean,
    val selection: LocalDate,
    val monthSelection: LocalDate,
    val showMonthCalendar: Boolean,
) : UiState() {
    companion object {
        fun init() = HomeState(
            groupId = "",
            thumbnail = Thumbnail(month = hashSetOf(), thumbnailUrls = hashMapOf()),
            memoryList = emptyList(),
            todoList = emptyList(),
            isLoading = false,
            selection = LocalDate.now(),
            monthSelection = LocalDate.now(),
            showMonthCalendar = false
        )
    }

    fun updateThumbnail(newThumbnail: Thumbnail): HomeState {
        val updatedThumbnail = this.thumbnail.copy(
            thumbnailUrls = (this.thumbnail.thumbnailUrls + newThumbnail.thumbnailUrls).toHashMap(),
            month = (this.thumbnail.month + newThumbnail.month).toHashSet()
        )
        return this.copy(thumbnail = updatedThumbnail)
    }
}
