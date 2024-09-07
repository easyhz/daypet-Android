package com.easyhz.daypet.home

import androidx.lifecycle.viewModelScope
import com.easyhz.daypet.common.base.BaseViewModel
import com.easyhz.daypet.domain.manager.UserManager
import com.easyhz.daypet.domain.param.home.ThumbnailParam
import com.easyhz.daypet.domain.param.member.GroupMemberParam
import com.easyhz.daypet.domain.param.memory.MemoryParam
import com.easyhz.daypet.domain.param.todo.TodoParam
import com.easyhz.daypet.domain.usecase.home.FetchThumbnailUseCase
import com.easyhz.daypet.domain.usecase.member.FetchGroupMemberUseCase
import com.easyhz.daypet.domain.usecase.memory.FetchMemoriesUseCase
import com.easyhz.daypet.domain.usecase.todo.FetchTodosUseCase
import com.easyhz.daypet.home.contract.HomeIntent
import com.easyhz.daypet.home.contract.HomeSideEffect
import com.easyhz.daypet.home.contract.HomeState
import com.easyhz.daypet.home.view.fab.SubMenu
import com.kizitonwose.calendar.core.yearMonth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchMemoriesUseCase: FetchMemoriesUseCase,
    private val fetchTodosUseCase: FetchTodosUseCase,
    private val fetchThumbnailUseCase: FetchThumbnailUseCase,
    private val fetchGroupMemberUseCase: FetchGroupMemberUseCase,
) : BaseViewModel<HomeState, HomeIntent, HomeSideEffect>(
    initialState = HomeState.init()
) {
    override fun handleIntent(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.InitScreen -> { initScreen(intent.groupId, intent.userId) }
            is HomeIntent.ChangeDate -> { changeDate(intent.clickedDay) }
            is HomeIntent.ChangeDateOnMonth -> { changeDateOnMonth(intent.clickedDay) }
            is HomeIntent.ClickMemory -> { onClickMemory(intent.index) }
            is HomeIntent.ClickTodo -> {}
            is HomeIntent.ShowMonthCalendar -> { showMonthCalendar() }
            is HomeIntent.HideMonthCalendar -> { hideMonthCalendar() }
            is HomeIntent.ClickMonthCalendarDay -> { onClickMonthCalendarDay(intent.localDate) }
            is HomeIntent.ClickFabSubMenu -> { onClickFabSubMenu(intent.subMenu) }
            is HomeIntent.ScrollWeek -> {
                onScroll(intent.firstDate, intent.lastDate)
            }
            is HomeIntent.ScrollMonth -> {
                onScroll(intent.firstDate, intent.lastDate)
            }
            is HomeIntent.HideUploadTodoBottomSheet -> { hideUploadTodoBottomSheet() }
            is HomeIntent.CompleteHideUploadTodoBottomSheet -> { completeHideUploadBottomSheet() }
        }
    }

    private fun initScreen(groupId: String, userId: String) {
        reduce { copy(groupId = groupId, userId = userId) }
        fetchUserInfo()
        fetchMemoires(uiState.value.selection)
        fetchTodos(uiState.value.selection)
        fetchThumbnail(uiState.value.selection)
    }

    private fun fetchUserInfo() = viewModelScope.launch {
        runCatching {
            val param = GroupMemberParam(currentState.groupId)
            val groupInfo = fetchGroupMemberUseCase.invoke(param).getOrThrow()
            UserManager.setUserInfo(currentState.userId, currentState.groupId, groupInfo)
            UserManager.printInfo()
        }.onFailure { e ->
            println("> $e")
            // TODO Fail 처리 스낵바
        }
    }

    private fun fetchMemoires(selection: LocalDate) = viewModelScope.launch {
        val param = MemoryParam(startDate = selection, endDate = selection, groupId = currentState.groupId)
        fetchMemoriesUseCase.invoke(param)
            .onSuccess {
                reduce { copy(selection = selection, memoryList = it) }
            }.onFailure {
                // TODO: Fail 처리 스낵바?
            }
    }

    private fun fetchTodos(selection: LocalDate) = viewModelScope.launch {
        val param = TodoParam(startDate = selection, endDate = selection, groupId = currentState.groupId)
        fetchTodosUseCase.invoke(param)
            .onSuccess {
                reduce { copy(selection = selection, todoList = it) }
            }.onFailure {
                // TODO: Fail 처리
            }
    }

    private fun fetchThumbnail(date: LocalDate) = viewModelScope.launch {
        val param = ThumbnailParam(
            startDate = date.withDayOfMonth(1),
            endDate = date.withDayOfMonth(date.lengthOfMonth()),
            groupId = currentState.groupId
        )
        fetchThumbnailUseCase.invoke(param)
            .onSuccess {
                reduce { updateThumbnail(newThumbnail = it) }
            }.onFailure {
                // TODO : Fail 처리
            }
    }

    private fun changeDate(clickedDay: LocalDate) {
        if (uiState.value.selection != clickedDay) {
            fetchMemoires(clickedDay)
            fetchTodos(clickedDay)
        }
    }

    private fun changeDateOnMonth(clickedDay: LocalDate) {
        if (uiState.value.monthSelection != clickedDay) {
            reduce { copy(monthSelection = clickedDay) }
        }
    }

    private fun showMonthCalendar() {
        reduce { copy(showMonthCalendar = true) }
    }

    private fun hideMonthCalendar() {
        reduce { copy(showMonthCalendar = false) }
    }

    private fun onClickMonthCalendarDay(localDate: LocalDate) {
        fetchMemoires(localDate)
        fetchTodos(localDate)
        reduce { copy(showMonthCalendar = false, selection = localDate) }
        postSideEffect { HomeSideEffect.ChangeWeekCalendar(localDate) }
    }

    private fun onClickFabSubMenu(subMenu: SubMenu) {
        when (subMenu) {
            is SubMenu.Memory -> {
                postSideEffect { HomeSideEffect.NavigateToUploadMemory }
            }
            is SubMenu.Todo -> {
                reduce { copy(isShowUploadTodoBottomSheet = true)}
            }
        }
    }

    private fun onScroll(firstDate: LocalDate, lastDate: LocalDate) {
        val thumbnail = uiState.value.thumbnail
        val datesToFetch = listOf(firstDate, lastDate).filter { date ->
            date.yearMonth.toString() !in thumbnail.month
        }
        datesToFetch.forEach { date ->
            fetchThumbnail(date)
        }
    }

    private fun onClickMemory(index: Int) {
        val memory = currentState.memoryList[index]
        postSideEffect { HomeSideEffect.NavigateToMemoryDetail(memory.documentId, memory.title) }
    }

    private fun hideUploadTodoBottomSheet() {
        postSideEffect { HomeSideEffect.HideUploadTodoBottomSheet }
    }

    private fun completeHideUploadBottomSheet() {
        reduce { copy(isShowUploadTodoBottomSheet = false) }
    }
}