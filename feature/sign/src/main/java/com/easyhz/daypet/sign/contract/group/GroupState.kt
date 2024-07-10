package com.easyhz.daypet.sign.contract.group

import com.easyhz.daypet.common.base.UiState

data class GroupState(
    val isLoading: Boolean,
    val groupName: String,
    val groupId: String,
    val isButtonEnabled: Boolean,
    val isOpenPetDialog: Boolean,
): UiState() {
    companion object {
        fun init() = GroupState(
            isLoading = false,
            groupName = "",
            groupId = "",
            isButtonEnabled = false,
            isOpenPetDialog = false
        )
    }
}
