package com.easyhz.daypet.upload_memory.contract

import android.net.Uri
import com.easyhz.daypet.common.base.UiIntent
import java.time.LocalDate
import java.time.LocalTime

sealed class UploadIntent: UiIntent() {
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
}