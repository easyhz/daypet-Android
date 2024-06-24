package com.easyhz.daypet.upload_memory.contract

import android.net.Uri
import com.easyhz.daypet.common.base.UiSideEffect

sealed class UploadSideEffect: UiSideEffect() {
    data object NavigateToGallery: UploadSideEffect()
    data class NavigateToCamera(val uri: Uri): UploadSideEffect()

}