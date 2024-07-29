package com.easyhz.daypet.upload_memory.contract

import android.net.Uri
import com.easyhz.daypet.common.base.UiIntent
import java.time.LocalDate
import java.time.LocalTime

sealed class UploadIntent: UiIntent() {
    data object InitScreen : UploadIntent()
    data object ClickToGallery : UploadIntent()
    data object ClickToCamera : UploadIntent()
    data class ChangeTitleText(val newText: String) : UploadIntent()
    data class ChangeContentText(val newText: String) : UploadIntent()
    data class ChangeDate(val newDate: LocalDate) : UploadIntent()
    data class ChangeTime(val newTime: LocalTime) : UploadIntent()
    data class PickImages(val images: List<Uri>) : UploadIntent()
    data class TakePicture(val isUsed: Boolean) : UploadIntent()
    data class DeleteImage(val image: Uri) : UploadIntent()
    data object ShowMemberBottomSheet: UploadIntent()
    data object HideMemberBottomSheet: UploadIntent()
    data class SelectMember(val member: MemberState): UploadIntent()
    data object ClickSelectedSuccessButton: UploadIntent()
    data class DeleteSelectedMember(val member: MemberState): UploadIntent()
    data object ClickUploadButton: UploadIntent()
    data object ClickDialogButton: UploadIntent()
    data object ClickBackButton: UploadIntent()
    data object ClearFocus: UploadIntent()
}