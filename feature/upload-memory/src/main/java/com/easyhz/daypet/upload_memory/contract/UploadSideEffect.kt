package com.easyhz.daypet.upload_memory.contract

import android.net.Uri
import androidx.annotation.StringRes
import com.easyhz.daypet.common.base.UiSideEffect

sealed class UploadSideEffect: UiSideEffect() {
    data object NavigateToGallery: UploadSideEffect()
    data class NavigateToCamera(val uri: Uri): UploadSideEffect()
    data object ScrollToBottom: UploadSideEffect()
    data class ShowSnackBar(@StringRes val stringId: Int): UploadSideEffect()
    data class NavigateToHome(val groupId: String, val userId: String): UploadSideEffect()
    data object NavigateToUp: UploadSideEffect()
    data object ClearFocus: UploadSideEffect()
}

enum class FocusType {
    NONE, TITLE, CONTENT
}