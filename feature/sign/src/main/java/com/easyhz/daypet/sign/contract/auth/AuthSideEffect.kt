package com.easyhz.daypet.sign.contract.auth

import androidx.annotation.StringRes
import com.easyhz.daypet.common.base.UiSideEffect

sealed class AuthSideEffect: UiSideEffect() {
    data object NavigateToProfile: AuthSideEffect()
    data class NavigateToGroup(val name: String, val uid: String): AuthSideEffect()
    data object NavigateToHome: AuthSideEffect()
    data object ClearFocus: AuthSideEffect()
    data class ShowSnackBar(@StringRes val stringId: Int): AuthSideEffect()
}