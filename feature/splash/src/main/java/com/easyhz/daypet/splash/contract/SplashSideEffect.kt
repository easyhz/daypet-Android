package com.easyhz.daypet.splash.contract

import com.easyhz.daypet.common.base.UiSideEffect

sealed class SplashSideEffect: UiSideEffect() {
    data class NavigateToHome(val groupId: String): SplashSideEffect()
    data class NavigateToGroup(val name: String, val ownerId: String): SplashSideEffect()
    data object NavigateToLogin: SplashSideEffect()
}