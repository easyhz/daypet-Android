package com.easyhz.daypet.upload_memory.contract

import android.net.Uri
import com.easyhz.daypet.common.base.UiState
import com.easyhz.daypet.domain.model.member.GroupUser
import com.easyhz.daypet.domain.model.member.Pet
import com.easyhz.daypet.domain.model.upload.Member
import java.time.LocalDate
import java.time.LocalTime

data class UploadState(
    val title: String,
    val date: LocalDate,
    val time: LocalTime,
    val selectedImages: List<Uri>,
    val selectedMembers: List<Member>,
    val pets: List<Pet>,
    val users: List<GroupUser>,
    val showMemberBottomSheet: Boolean,
    val takePictureUri: String,
): UiState() {
    companion object {
        fun init() = UploadState(
            title = "",
            date = LocalDate.now(),
            time = LocalTime.now(),
            selectedImages = emptyList(),
            selectedMembers = emptyList(),
            pets = emptyList(),
            users = emptyList(),
            showMemberBottomSheet = false,
            takePictureUri = ""
        )
    }

    fun UploadState.updateImages(newImages: List<Uri>): UploadState =
        this.copy(selectedImages = this.selectedImages + newImages)

    fun UploadState.deleteImage(image: Uri): UploadState {
        return this.copy(selectedImages = this.selectedImages.filter { it != image })
    }
}
