package com.easyhz.daypet.sign.contract.pet

import androidx.annotation.StringRes
import com.easyhz.daypet.common.base.UiSideEffect

sealed class PetSideEffect: UiSideEffect() {
    data object ScrollToBottom: PetSideEffect()
    data object RequestFocus: PetSideEffect()
    data object OpenKeyboard: PetSideEffect()
    data class ShowSnackBar(@StringRes val stringId: Int): PetSideEffect()
    data class NavigateToHome(val groupId: String): PetSideEffect()
}