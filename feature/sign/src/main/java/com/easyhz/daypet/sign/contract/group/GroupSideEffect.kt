package com.easyhz.daypet.sign.contract.group

import androidx.annotation.StringRes
import com.easyhz.daypet.common.base.UiSideEffect

sealed class GroupSideEffect: UiSideEffect() {
    data object NavigateToEnterGroup: GroupSideEffect()
    data class NavigateToHome(val groupId: String, val userId: String): GroupSideEffect()
    data class NavigateToPet(val groupId: String, val userId: String): GroupSideEffect()
    data class ShowSnackBar(@StringRes val stringId: Int): GroupSideEffect()
}