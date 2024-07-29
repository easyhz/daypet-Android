package com.easyhz.daypet.upload_memory.contract

import android.net.Uri
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.easyhz.daypet.common.base.UiState
import com.easyhz.daypet.design_system.component.image.MemberType
import com.easyhz.daypet.domain.model.member.GroupMember
import com.easyhz.daypet.domain.model.member.GroupUser
import com.easyhz.daypet.domain.model.member.Pet
import java.time.LocalDate
import java.time.LocalTime

data class UploadState(
    val title: String,
    val content: String,
    val date: LocalDate,
    val time: LocalTime,
    val selectedImages: List<Uri>,
    val selectedMembers: List<MemberState>,
    val members: List<MemberState>,
    val isShowMemberBottomSheet: Boolean,
    val takePictureUri: String,
    val isVisibleDialog: Boolean,
    val isLoading: Boolean
): UiState() {
    companion object {
        fun init() = UploadState(
            title = "",
            content = "",
            date = LocalDate.now(),
            time = LocalTime.now(),
            selectedImages = emptyList(),
            selectedMembers = emptyList(),
            members = emptyList(),
            isShowMemberBottomSheet = false,
            takePictureUri = "",
            isVisibleDialog = false,
            isLoading = false
        )
    }

    fun UploadState.updateImages(newImages: List<Uri>): UploadState =
        this.copy(selectedImages = this.selectedImages + newImages)

    fun UploadState.deleteImage(image: Uri): UploadState =
        this.copy(selectedImages = this.selectedImages.filter { it != image })

    fun UploadState.deleteSelectedMember(member: MemberState): UploadState =
        this.copy(selectedMembers = this.selectedMembers.filter { it != member })
}

data class MemberState(
    val id: String,
    val memberType: MemberType,
    val thumbnailUrl: String,
    val name: String,
    val isChecked: MutableState<Boolean>
)

internal fun GroupMember.toMember(): List<MemberState> {
    return (this.pets.map { it.toMemberState() } + this.groupUsers.map { it.toMemberState() }).toList()
}

fun Pet.toMemberState() = MemberState(
    id = this.id,
    memberType = MemberType.PET,
    thumbnailUrl = this.thumbnailUrl,
    name = this.name,
    isChecked = mutableStateOf(false)
)

fun GroupUser.toMemberState() = MemberState(
    id = this.userId,
    memberType = MemberType.PERSON,
    thumbnailUrl = this.thumbnailUrl,
    name = this.name,
    isChecked = mutableStateOf(false)
)