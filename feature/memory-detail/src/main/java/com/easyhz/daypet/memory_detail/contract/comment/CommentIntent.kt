package com.easyhz.daypet.memory_detail.contract.comment

import com.easyhz.daypet.common.base.UiIntent

sealed class CommentIntent : UiIntent() {
    data object ClickBackButton : CommentIntent()
    data class InitScreen(
        val memoryId: String,
        val memoryTitle: String,
        val thumbnailUrl: String
    ) : CommentIntent()

    data class ChangeValueTextField(val text: String) : CommentIntent()
    data object ClickSendButton : CommentIntent()
}