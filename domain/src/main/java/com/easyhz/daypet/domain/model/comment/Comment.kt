package com.easyhz.daypet.domain.model.comment

data class Comment(
    val commentId: String,
    val content: String,
    val creationTime: String,
    val groupId: String,
    val memoryId: String,
    val memoryTitle: String,
    val thumbnailUrl: String,
    val profileImageUrl: String,
    val uploaderId: String,
    val uploaderName: String,
    val isOwner: Boolean
)