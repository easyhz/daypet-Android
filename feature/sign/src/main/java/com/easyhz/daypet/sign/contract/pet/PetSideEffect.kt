package com.easyhz.daypet.sign.contract.pet

import com.easyhz.daypet.common.base.UiSideEffect

sealed class PetSideEffect: UiSideEffect() {
    data object ScrollToBottom: PetSideEffect()
    data object RequestFocus: PetSideEffect()
    data object OpenKeyboard: PetSideEffect()
}