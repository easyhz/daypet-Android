package com.easyhz.daypet.memory_detail.contract.comment

import androidx.annotation.StringRes
import com.easyhz.daypet.common.base.UiSideEffect

sealed class CommentSideEffect: UiSideEffect() {
    data object NavigateToUp: CommentSideEffect()
    data class ShowSnackBar(@StringRes val stringId: Int): CommentSideEffect()
    data object HideKeyboard: CommentSideEffect()
}