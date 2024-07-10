package com.easyhz.daypet.upload_memory

import android.net.Uri
import androidx.lifecycle.viewModelScope
import com.easyhz.daypet.common.base.BaseViewModel
import com.easyhz.daypet.domain.param.member.GroupMemberParam
import com.easyhz.daypet.domain.usecase.member.FetchGroupMemberUseCase
import com.easyhz.daypet.domain.usecase.upload.GetTakePictureUriUseCase
import com.easyhz.daypet.upload_memory.contract.FocusType
import com.easyhz.daypet.upload_memory.contract.UploadIntent
import com.easyhz.daypet.upload_memory.contract.UploadSideEffect
import com.easyhz.daypet.upload_memory.contract.UploadState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class UploadMemoryViewModel @Inject constructor(
    private val getTakePictureUriUseCase: GetTakePictureUriUseCase,
    private val fetchGroupMemberUseCase: FetchGroupMemberUseCase,
): BaseViewModel<UploadState, UploadIntent, UploadSideEffect>(
    initialState = UploadState.init()
) {
    override fun handleIntent(intent: UploadIntent) {
        when (intent) {
            is UploadIntent.ClickToGallery -> { onClickGallery() }
            is UploadIntent.ClickToCamera -> { onClickCamera() }
            is UploadIntent.ChangeTitleText -> { onChangeTitleValue(intent.newText) }
            is UploadIntent.ChangeContentText -> { onChangeContentValue(intent.newText) }
            is UploadIntent.ChangeDate -> { onChangeDateValue(intent.newDate) }
            is UploadIntent.ChangeTime -> { onChangeTimeValue(intent.newTime) }
            is UploadIntent.PickImages -> { updatedSelectedImages(intent.images) }
            is UploadIntent.TakePicture -> { updateTakePicture(intent.isUsed) }
            is UploadIntent.DeleteImage -> { deleteImage(intent.image)}
            is UploadIntent.ShowMemberBottomSheet -> { showMemberBottomSheet() }
            is UploadIntent.HideMemberBottomSheet -> { hideMemberBottomSheet() }
            is UploadIntent.ChangeTitleFocus -> { changeTitleFocus(intent.isFocused) }
            is UploadIntent.ChangeContentFocus -> { changeContentFocus(intent.isFocused) }
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

    private fun onChangeTimeValue(newTime: LocalTime){
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
    private fun showMemberBottomSheet() = viewModelScope.launch {
        val param = GroupMemberParam("groupId") // FIXME
        fetchGroupMemberUseCase.invoke(param).onSuccess {
            reduce { copy(pets = it.pets, users = it.groupUsers, showMemberBottomSheet = true) }
        }.onFailure { println("fail > > $it") }
    }

    private fun hideMemberBottomSheet() {
        reduce { copy(showMemberBottomSheet = false) }
    }

    private fun changeTitleFocus(isFocused: Boolean) {
        if (isFocused) {
            postSideEffect { UploadSideEffect.ChangeFocus(FocusType.TITLE) }
        }
    }

    private fun changeContentFocus(isFocused: Boolean) {
        if (isFocused) {
            postSideEffect { UploadSideEffect.ChangeFocus(FocusType.CONTENT) }
        }
    }

}