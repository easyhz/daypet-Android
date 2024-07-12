package com.easyhz.daypet.sign.contract.auth

import android.net.Uri
import com.easyhz.daypet.common.base.UiState

data class AuthState(
    val isLoading: Boolean,
    val uid: String,
    val name: String,
    val profileThumbnail: Uri,
    val isProfileButtonEnabled: Boolean,
    val isShowBottomSheet: Boolean,
): UiState() {
    companion object {
        fun init() = AuthState(
            isLoading = false,
            uid = "",
            name = "",
            isProfileButtonEnabled = false,
            profileThumbnail = Uri.EMPTY,
            isShowBottomSheet = false
        )
    }
}
