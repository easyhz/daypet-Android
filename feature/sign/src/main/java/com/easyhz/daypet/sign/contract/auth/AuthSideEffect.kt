package com.easyhz.daypet.sign.contract.auth

import com.easyhz.daypet.common.base.UiSideEffect

sealed class AuthSideEffect: UiSideEffect() {
    data object NavigateToProfile: AuthSideEffect()
    data class NavigateToGroup(val name: String): AuthSideEffect()
    data object ClearFocus: AuthSideEffect()
}