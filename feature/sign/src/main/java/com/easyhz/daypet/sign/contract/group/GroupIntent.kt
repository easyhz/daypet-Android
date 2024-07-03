package com.easyhz.daypet.sign.contract.group

import com.easyhz.daypet.common.base.UiIntent

sealed class GroupIntent: UiIntent() {
    data class ChangeNameText(val newText: String): GroupIntent()
    data object ClickCreateGroup: GroupIntent()
    data object ClickEnterGroup: GroupIntent()
}