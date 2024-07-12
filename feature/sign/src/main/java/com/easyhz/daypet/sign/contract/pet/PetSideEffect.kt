package com.easyhz.daypet.sign.contract.pet

import androidx.annotation.StringRes
import androidx.compose.foundation.ScrollState
import androidx.compose.ui.focus.FocusRequester
import com.easyhz.daypet.common.base.UiSideEffect

sealed class PetSideEffect: UiSideEffect() {
    data object NavigateToGallery: PetSideEffect()
    data class ScrollToBottom(val scrollState: ScrollState): PetSideEffect()
    data class RequestFocus(val focusRequester: FocusRequester): PetSideEffect()
    data object OpenKeyboard: PetSideEffect()
    data object HideKeyboard: PetSideEffect()
    data class ShowSnackBar(@StringRes val stringId: Int): PetSideEffect()
    data class NavigateToHome(val groupId: String): PetSideEffect()
}