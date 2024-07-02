package com.easyhz.daypet.sign.contract.auth

import com.easyhz.daypet.common.base.UiState

data class AuthState(
    val isLoading: Boolean,
    val name: String,
    val isProfileButtonEnabled: Boolean,
): UiState() {
    companion object {
        fun init() = AuthState(
            isLoading = false,
            name = "",
            isProfileButtonEnabled = false
        )
    }
}
