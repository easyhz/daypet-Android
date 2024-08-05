package com.easyhz.daypet.memory_detail.contract

import com.easyhz.daypet.common.base.UiIntent

sealed class DetailIntent: UiIntent() {
    data object ClickBackButton: DetailIntent()
    data class InitScreen(val id: String, val title: String): DetailIntent()
    data object ClickComment: DetailIntent()
}