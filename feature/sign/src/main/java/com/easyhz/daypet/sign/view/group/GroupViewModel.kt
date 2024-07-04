package com.easyhz.daypet.sign.view.group

import androidx.lifecycle.viewModelScope
import com.easyhz.daypet.common.base.BaseViewModel
import com.easyhz.daypet.domain.param.member.GroupInfoParam
import com.easyhz.daypet.domain.usecase.sign.SaveGroupInfoUseCase
import com.easyhz.daypet.sign.contract.group.GroupIntent
import com.easyhz.daypet.sign.contract.group.GroupSideEffect
import com.easyhz.daypet.sign.contract.group.GroupState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GroupViewModel @Inject constructor(
    private val saveGroupInfoUseCase: SaveGroupInfoUseCase
): BaseViewModel<GroupState, GroupIntent, GroupSideEffect>(
    initialState = GroupState.init()
) {
    override fun handleIntent(intent: GroupIntent) {
        when(intent) {
            is GroupIntent.ChangeGroupNameText -> { onChangeGroupNameText(intent.newText) }
            is GroupIntent.ClickCreateGroup -> { createGroup(intent.ownerId) }
            is GroupIntent.ClickEnterGroup -> { /* TODO */}
        }
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
        println(">> $param")
        saveGroupInfoUseCase.invoke(param)
            .onSuccess {
                reduce { copy(isOpenPetDialog = true) }
            }.onFailure {
                println("실패 ㅋㅋㅋ")
                // TODO: Fail 처리
            }
    }
}