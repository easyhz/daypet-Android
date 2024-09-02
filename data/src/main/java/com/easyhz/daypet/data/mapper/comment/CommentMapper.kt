package com.easyhz.daypet.data.mapper.comment

import com.easyhz.daypet.common.extension.convertToTimeStamp
import com.easyhz.daypet.common.extension.toLocalDate
import com.easyhz.daypet.data.model.response.comment.CommentResponse
import com.easyhz.daypet.domain.manager.UserManager
import com.easyhz.daypet.domain.model.comment.Comment
import java.time.LocalDate
import java.time.LocalTime

fun CommentResponse.toModel(id: String): Comment {
    val user = UserManager.groupInfo?.groupUsers?.find { it.userId == this.uploaderId }
    val profileImage = user?.thumbnailUrl ?: ""
    val uploaderName = user?.name ?: ""

    return Comment(
        commentId = id,
        content = this.content,
        creationTime = this.creationTime.toLocalDate().toString(),
        groupId = this.groupId,
        memoryId = this.memoryId,
        memoryTitle = this.memoryTitle,
        thumbnailUrl = this.thumbnailUrl,
        profileImageUrl = profileImage,
        uploaderId = this.uploaderId,
        uploaderName = uploaderName,
        isOwner = this.uploaderId == UserManager.userId
    )
}

fun Comment.toRequest(): CommentResponse =  CommentResponse(
    content = this.content,
    creationTime = convertToTimeStamp(LocalDate.now(), LocalTime.now()), // FIXME
    groupId = this.groupId,
    memoryId = this.memoryId,
    memoryTitle = this.memoryTitle,
    thumbnailUrl = this.thumbnailUrl,
    uploaderId = this.uploaderId
)
