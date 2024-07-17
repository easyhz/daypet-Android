package com.easyhz.daypet.upload_memory

import android.net.Uri
import androidx.lifecycle.viewModelScope
import com.easyhz.daypet.common.base.BaseViewModel
import com.easyhz.daypet.domain.manager.UserManager
import com.easyhz.daypet.domain.param.member.GroupMemberParam
import com.easyhz.daypet.domain.usecase.member.FetchGroupMemberUseCase
import com.easyhz.daypet.domain.usecase.upload.GetTakePictureUriUseCase
import com.easyhz.daypet.upload_memory.contract.MemberState
import com.easyhz.daypet.upload_memory.contract.UploadIntent
import com.easyhz.daypet.upload_memory.contract.UploadSideEffect
import com.easyhz.daypet.upload_memory.contract.UploadState
import com.easyhz.daypet.upload_memory.contract.toMember
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class UploadMemoryViewModel @Inject constructor(
    private val getTakePictureUriUseCase: GetTakePictureUriUseCase,
    private val fetchGroupMemberUseCase: FetchGroupMemberUseCase,
) : BaseViewModel<UploadState, UploadIntent, UploadSideEffect>(
    initialState = UploadState.init()
) {
    override fun handleIntent(intent: UploadIntent) {
        when (intent) {
            is UploadIntent.InitScreen -> { initScreen() }
            is UploadIntent.ClickToGallery -> { onClickGallery() }
            is UploadIntent.ClickToCamera -> { onClickCamera() }
            is UploadIntent.ChangeTitleText -> { onChangeTitleValue(intent.newText) }
            is UploadIntent.ChangeContentText -> { onChangeContentValue(intent.newText) }
            is UploadIntent.ChangeDate -> { onChangeDateValue(intent.newDate) }
            is UploadIntent.ChangeTime -> { onChangeTimeValue(intent.newTime) }
            is UploadIntent.PickImages -> { updatedSelectedImages(intent.images) }
            is UploadIntent.TakePicture -> { updateTakePicture(intent.isUsed) }
            is UploadIntent.DeleteImage -> { deleteImage(intent.image) }
            is UploadIntent.ShowMemberBottomSheet -> { showMemberBottomSheet() }
            is UploadIntent.HideMemberBottomSheet -> { hideMemberBottomSheet() }
            is UploadIntent.SelectMember -> { onSelectMember(intent.member) }
            is UploadIntent.ClickSelectedSuccessButton -> { onClickSelectedSuccessButton() }
            is UploadIntent.DeleteSelectedMember -> { onDeleteSelectedMember(intent.member)}
        }
    }

    private fun initScreen() {
        UserManager.groupInfo?.let {
            reduce { copy(members = it.toMember()) }
        } ?: run {
            fetchGroupMember()
        }
    }

    private fun onClickGallery() {
        postSideEffect { UploadSideEffect.NavigateToGallery }
    }

    private fun onClickCamera() = viewModelScope.launch {
        getTakePictureUriUseCase.invoke(Unit)
            .onSuccess {
                val uri = Uri.parse(it)
                reduce { copy(takePictureUri = it) }
                postSideEffect { UploadSideEffect.NavigateToCamera(uri) }
            }.onFailure {
                // TODO : Fail 처리
            }
    }


    private fun onChangeTitleValue(newText: String) {
        reduce { copy(title = newText) }
    }

    private fun onChangeContentValue(newText: String) {
        reduce { copy(content = newText) }
        postSideEffect { UploadSideEffect.ScrollToBottom }
    }

    private fun onChangeDateValue(newDate: LocalDate) {
        reduce { copy(date = newDate) }
    }

    private fun onChangeTimeValue(newTime: LocalTime) {
        reduce { copy(time = newTime) }
    }

    private fun updatedSelectedImages(newImage: List<Uri>) {
        reduce { updateImages(newImages = newImage) }
    }

    private fun updateTakePicture(isUsed: Boolean) {
        if (!isUsed) return
        val uri = Uri.parse(uiState.value.takePictureUri)
        reduce { updateImages(newImages = listOf(uri)) }
    }

    private fun deleteImage(image: Uri) {
        reduce { deleteImage(image = image) }
    }

    private fun showMemberBottomSheet() {
        if (currentState.selectedMembers.isEmpty()) {
            reduce { copy(isShowMemberBottomSheet = true) }
        } else {
            val updatedMembers = currentState.members.map { member ->
                member.apply {
                    if (this in currentState.selectedMembers) {
                        isChecked.value = true
                    }
                }
            }
            reduce { copy(members = updatedMembers, isShowMemberBottomSheet = true) }
        }
    }

    private fun fetchGroupMember() = viewModelScope.launch {
        UserManager.groupId?.let {
            val param = GroupMemberParam(it)
            fetchGroupMemberUseCase.invoke(param).onSuccess {
                reduce { copy(members = it.toMember()) }
            }.onFailure {
                println("fail > > $it")
            }
        } ?: {
            // TODO 예외처리
        }
    }

    private fun hideMemberBottomSheet() {
        reduce {
            copy(
                isShowMemberBottomSheet = false,
                members = currentState.members.map {
                    it.isChecked.value = false
                    it
                }
            )
        }
    }

    private fun onSelectMember(member: MemberState) {
        val index = currentState.members.indexOf(member)
        if (index != -1) {
            currentState.members[index].isChecked.value = !currentState.members[index].isChecked.value
        }
    }

    private fun onClickSelectedSuccessButton() {
        reduce { copy(selectedMembers = currentState.members.filter { it.isChecked.value }) }
        hideMemberBottomSheet()
    }

    private fun onDeleteSelectedMember(member: MemberState) {
        reduce { deleteSelectedMember(member) }
    }

}