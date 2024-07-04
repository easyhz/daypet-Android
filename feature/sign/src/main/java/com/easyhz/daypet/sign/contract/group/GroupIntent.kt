package com.easyhz.daypet.sign.contract.group

import com.easyhz.daypet.common.base.UiIntent

sealed class GroupIntent: UiIntent() {
    data class ChangeGroupNameText(val newText: String): GroupIntent()
    data class ClickCreateGroup(val ownerId: String): GroupIntent()
    data object ClickEnterGroup: GroupIntent()
    data object ClickDialogPositiveButton: GroupIntent()
    data object ClickDialogNegativeButton: GroupIntent()
}