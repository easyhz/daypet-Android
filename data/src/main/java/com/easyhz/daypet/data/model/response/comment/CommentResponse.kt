package com.easyhz.daypet.data.model.response.comment

import com.google.firebase.Timestamp
import com.google.firebase.firestore.PropertyName

data class CommentResponse(
    @PropertyName("content")
    val content: String = "",
    @PropertyName("creationTime")
    val creationTime: Timestamp = Timestamp.now(),
    @get:PropertyName("groupID")
    @set:PropertyName("groupID")
    var groupId: String = "",
    @get:PropertyName("memoryID")
    @set:PropertyName("memoryID")
    var memoryId: String = "",
    @PropertyName("memoryTitle")
    val memoryTitle: String = "",
    @get:PropertyName("thumbnailURL")
    @set:PropertyName("thumbnailURL")
    var thumbnailUrl: String = "",
    @get:PropertyName("uploaderID")
    @set:PropertyName("uploaderID")
    var uploaderId: String = "",
)
