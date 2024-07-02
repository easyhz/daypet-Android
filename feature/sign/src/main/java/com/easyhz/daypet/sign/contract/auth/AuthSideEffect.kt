package com.easyhz.daypet.sign.contract.auth

import com.easyhz.daypet.common.base.UiSideEffect

sealed class AuthSideEffect: UiSideEffect() {
    data object NavigateToProfile: AuthSideEffect()
    data object NavigateToGroup: AuthSideEffect()
}