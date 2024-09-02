package com.easyhz.daypet.memory_detail.contract.comment

import com.easyhz.daypet.common.base.UiState
import com.easyhz.daypet.domain.model.comment.Comment

data class CommentState(
    val comment: List<Comment>,
    val commentString: String,
    val memoryId: String,
    val memoryTitle: String,
    val thumbnailUrl: String
): UiState() {
    companion object {
        fun init() = CommentState(
            comment = emptyList(),
            commentString = "",
            memoryId = "",
            memoryTitle = "",
            thumbnailUrl = ""
        )
    }
}
