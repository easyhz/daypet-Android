package com.easyhz.daypet.memory_detail.contract

import com.easyhz.daypet.common.base.UiSideEffect

sealed class DetailSideEffect: UiSideEffect() {
    data object NavigateToUp: DetailSideEffect()
    data class NavigateToComment(val id: String, val title: String, val url: String): DetailSideEffect()
}