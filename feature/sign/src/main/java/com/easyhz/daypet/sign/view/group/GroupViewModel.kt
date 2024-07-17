package com.easyhz.daypet.sign.view.group

import androidx.lifecycle.viewModelScope
import com.easyhz.daypet.common.base.BaseViewModel
import com.easyhz.daypet.common.error.getMessageStringRes
import com.easyhz.daypet.domain.manager.UserManager
import com.easyhz.daypet.domain.param.member.GroupInfoParam
import com.easyhz.daypet.domain.param.member.GroupMemberParam
import com.easyhz.daypet.domain.usecase.member.FetchGroupMemberUseCase
import com.easyhz.daypet.domain.usecase.sign.SaveGroupInfoUseCase
import com.easyhz.daypet.sign.contract.group.GroupIntent
import com.easyhz.daypet.sign.contract.group.GroupSideEffect
import com.easyhz.daypet.sign.contract.group.GroupState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GroupViewModel @Inject constructor(
    private val saveGroupInfoUseCase: SaveGroupInfoUseCase,
    private val fetchGroupMemberUseCase: FetchGroupMemberUseCase,
): BaseViewModel<GroupState, GroupIntent, GroupSideEffect>(
    initialState = GroupState.init()
) {
    override fun handleIntent(intent: GroupIntent) {
        when(intent) {
            is GroupIntent.InitScreen -> { initScreen(intent.ownerId) }
            is GroupIntent.ChangeGroupNameText -> { onChangeGroupNameText(intent.newText) }
            is GroupIntent.ClickCreateGroup -> { createGroup(intent.ownerId) }
            is GroupIntent.ClickEnterGroup -> { /* TODO */}
            is GroupIntent.ClickDialogPositiveButton -> { onClickDialogPositiveButton() }
            is GroupIntent.ClickDialogNegativeButton -> { onClickDialogNegativeButton() }
        }
    }

    private fun initScreen(userId: String) {
        reduce { copy(userId = userId) }
    }
    private fun onChangeGroupNameText(newText: String) {
        val isButtonEnabled = newText.isNotBlank()
        reduce { copy(groupName = newText, isButtonEnabled = isButtonEnabled) }
    }

    private fun createGroup(ownerId: String) = viewModelScope.launch {
        val param = GroupInfoParam(
            ownerId = ownerId,
            groupName = uiState.value.groupName
        )
        saveGroupInfoUseCase.invoke(param)
            .onSuccess {
                reduce { copy(isOpenPetDialog = true, groupId = it) }
            }.onFailure { e ->
                postSideEffect { GroupSideEffect.ShowSnackBar(e.getMessageStringRes()) }
            }
    }

    private fun onClickDialogPositiveButton() = viewModelScope.launch {
        saveUserInfo().onSuccess {
            postSideEffect { GroupSideEffect.NavigateToPet(currentState.groupId) }
        }.also {
            reduce { copy(isOpenPetDialog = false) }
        }

    }

    private fun onClickDialogNegativeButton() = viewModelScope.launch {
        reduce { copy(isOpenPetDialog = false) }
        saveUserInfo().onSuccess {
            postSideEffect { GroupSideEffect.NavigateToHome(currentState.groupId) }
        }
    }

    private suspend fun saveUserInfo() =
        runCatching {
            val param = GroupMemberParam(currentState.groupId)
            UserManager.groupId = currentState.groupId
            UserManager.userId = currentState.userId
            UserManager.groupInfo = fetchGroupMemberUseCase.invoke(param).getOrNull()
        }.onFailure { e ->
            postSideEffect { GroupSideEffect.ShowSnackBar(e.getMessageStringRes()) }
        }


}