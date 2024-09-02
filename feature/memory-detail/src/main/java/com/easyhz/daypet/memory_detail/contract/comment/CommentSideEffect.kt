package com.easyhz.daypet.memory_detail.contract.comment

import com.easyhz.daypet.common.base.UiSideEffect

sealed class CommentSideEffect: UiSideEffect() {
    data object NavigateToUp: CommentSideEffect()
}