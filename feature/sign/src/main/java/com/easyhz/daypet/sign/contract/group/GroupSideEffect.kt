package com.easyhz.daypet.sign.contract.group

import com.easyhz.daypet.common.base.UiSideEffect

sealed class GroupSideEffect: UiSideEffect() {
    data object NavigateToEnterGroup: GroupSideEffect()
    data object NavigateToHome: GroupSideEffect()
    data object NavigateToPet: GroupSideEffect()
}